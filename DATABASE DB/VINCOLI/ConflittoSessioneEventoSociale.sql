--ConflittoSessioneEventoSociale
--una sessione non può iniziare o finire durante
--un evento sociale che avviene nella sua stessa data.


CREATE OR REPLACE FUNCTION ConflittoSessioneEventoSociale() RETURNS TRIGGER AS
$check$
DECLARE
	TempEvento EVENTO_SOCIALE.CodEvento%TYPE;
BEGIN
	SELECT CodEvento FROM (PROGRAMMA NATURAL JOIN EVENTO_SOCIALE)
	INTO TempEvento		--estraggo il codice del potenziale evento sociale che mi da conflitto
	WHERE(
			( ( (NEW.OrarioInizioSessione >= OrarioInizioEvento) AND (NEW.OrarioInizioSessione < OrarioFineEvento) ) OR
			( (NEW.OrarioFineSessione > OrarioInizioEvento) AND (NEW.OrarioFineSessione <= OrarioFineEvento) ) )AND
			(NEW.CodProgramma = CodProgramma)
		 );
	
	IF(TempEvento IS NOT NULL) THEN		--se trovo qualcosa, allora c'è un evento sociale che da conflitto
		RAISE EXCEPTION 'Errore, in questa data e orario è in corso un evento sociale';
	END IF;
	RETURN NEW;
END;
$check$ LANGUAGE PLPGSQL;	

CREATE OR REPLACE TRIGGER ConflittoSessioneEventoSocialeInsert
BEFORE INSERT ON SESSIONE
FOR EACH ROW
EXECUTE PROCEDURE ConflittoSessioneEventoSociale();

CREATE OR REPLACE TRIGGER ConflittoSessioneEventoSocialeUpdate
BEFORE UPDATE ON SESSIONE
FOR EACH ROW
EXECUTE PROCEDURE ConflittoSessioneEventoSociale();