--ConflittoEventoSocialeSessione
--un evento sociale non può iniziare o finire mentre è
--in corso una sessione che avviene nella stessa data


CREATE OR REPLACE FUNCTION ConflittoEventoSocialeSessione() RETURNS TRIGGER AS
$check$
DECLARE
	TempSessione SESSIONE.CodSessione%TYPE;
BEGIN
	SELECT CodSessione FROM (PROGRAMMA NATURAL JOIN SESSIONE)
	INTO TempSessione	--estraggo il codice della potenziale sessione che mi da conflitto
	WHERE((((NEW.OrarioInizioEvento >= OrarioInizioSessione) AND (NEW.OrarioInizioEvento < OrarioFineSessione)) OR
		  ((NEW.OrarioFineEvento > OrarioInizioSessione) AND (NEW.OrarioFineEvento <= OrarioFineSessione)))AND
		   (NEW.CodProgramma = CodProgramma));
	
	IF(TempSessione IS NOT NULL) THEN		--se trovo qualcosa, allora c'è un evento sociale che da conflitto
		RAISE EXCEPTION 'Errore, in questa data e orario è in corso una sessione';
	END IF;
	RETURN NEW;
END;
$check$ LANGUAGE PLPGSQL;	

CREATE OR REPLACE TRIGGER ConflittoEventoSocialeSessioneInsert
BEFORE INSERT ON EVENTO_SOCIALE
FOR EACH ROW
EXECUTE PROCEDURE ConflittoEventoSocialeSessione();

CREATE OR REPLACE TRIGGER ConflittoEventoSocialeSessioneUpdate
BEFORE UPDATE ON EVENTO_SOCIALE
FOR EACH ROW
EXECUTE PROCEDURE ConflittoEventoSocialeSessione();