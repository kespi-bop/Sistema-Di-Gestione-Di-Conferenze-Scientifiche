--ConflittoEventiSociali
--un evento sociale non può iniziare o finire mentre è in corso
--un evento sociale nello stesso programma


CREATE OR REPLACE FUNCTION ConflittoEventiSociali() RETURNS TRIGGER AS
$check$
DECLARE
	TempEventoSociale EVENTO_SOCIALE.CodEvento%TYPE;
BEGIN
	SELECT CodEvento FROM EVENTO_SOCIALE
	INTO TempEventoSociale	--estraggo il codice del potenziale evento sociale che mi da conflitto
	WHERE((((NEW.OrarioInizioEvento >= OrarioInizioEvento) AND (NEW.OrarioInizioEvento < OrarioFineEvento)) OR
		  ((NEW.OrarioFineEvento > OrarioInizioEvento) AND (NEW.OrarioFineEvento <= OrarioFineEvento))) AND
		  (NEW.CodProgramma = CodProgramma));
	
	IF(TempEventoSociale IS NOT NULL) THEN		--se trovo qualcosa, allora c'è un evento sociale che da conflitto
		RAISE EXCEPTION 'Errore, in questa data e orario è in corso un evento sociale';
	END IF;
	RETURN NEW;
END;

$check$ LANGUAGE PLPGSQL;	

CREATE OR REPLACE TRIGGER ConflittoEventiSociali
BEFORE INSERT ON EVENTO_SOCIALE
FOR EACH ROW
EXECUTE PROCEDURE ConflittoEventiSociali();

CREATE OR REPLACE TRIGGER ConflittoEventiSociali
BEFORE UPDATE ON EVENTO_SOCIALE
FOR EACH ROW
EXECUTE PROCEDURE ConflittoEventiSociali();