--ConflittoIntervalloEventoSociale
--un intervallo non può iniziare o finire mentre è in corso
--un evento sociale che avviene nella sua stessa data


CREATE OR REPLACE FUNCTION ConflittoIntervalloEventoSociale() RETURNS TRIGGER AS
$check$
DECLARE
	TempEventoSociale EVENTO_SOCIALE.CodEvento%TYPE;
BEGIN
	SELECT CodEvento FROM (INTERVALLO NATURAL JOIN EVENTO_SOCIALE)
	INTO TempEventoSociale	--estraggo il codice del potenziale evento sociale che mi da conflitto
	WHERE((((NEW.OrarioInizioIntervallo >= OrarioInizioEvento) AND (NEW.OrarioInizioIntervallo < OrarioFineEvento)) OR
		  ((NEW.OrarioFineIntervallo > OrarioInizioEvento) AND (NEW.OrarioFineIntervallo <= OrarioFineEvento))) AND
		  (NEW.CodProgramma = CodProgramma));
	
	IF(TempEventoSociale IS NOT NULL) THEN		--se trovo qualcosa, allora c'è un intervallo che da conflitto
		RAISE EXCEPTION 'Errore, in questa data e orario è in corso un evento sociale';
	END IF;
	RETURN NEW;
END;
$check$ LANGUAGE PLPGSQL;	

CREATE OR REPLACE TRIGGER ConflittoIntervalloEventoSocialeInsert
BEFORE INSERT ON INTERVALLO
FOR EACH ROW
EXECUTE PROCEDURE ConflittoIntervalloEventoSociale();

CREATE OR REPLACE TRIGGER ConflittoIntervalloEventoSocialeUpdate
BEFORE UPDATE ON INTERVALLO
FOR EACH ROW
EXECUTE PROCEDURE ConflittoIntervalloEventoSociale();