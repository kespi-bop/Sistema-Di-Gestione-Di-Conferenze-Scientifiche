--ConflittoEventoSocialeIntervallo
--un evento sociale non può iniziare o finire mentre è
--in corso un intervallo che avviene nella stessa data


CREATE OR REPLACE FUNCTION ConflittoEventoSocialeIntervallo() RETURNS TRIGGER AS
$check$
DECLARE
	TempIntervallo INTERVALLO.CodIntervallo%TYPE;
BEGIN
	SELECT CodIntervallo FROM (EVENTO_SOCIALE NATURAL JOIN INTERVALLO)
	INTO TempIntervallo	--estraggo il codice del potenziale intervallo che mi da conflitto
	WHERE((((NEW.OrarioInizioEvento >= OrarioInizioIntervallo) AND (NEW.OrarioInizioEvento < OrarioFineIntervallo)) OR
		  ((NEW.OrarioFineEvento > OrarioInizioIntervallo) AND (NEW.OrarioFineEvento <= OrarioFineIntervallo)))AND
		  (NEW.CodProgramma = CodProgramma));
	
	IF(TempIntervallo IS NOT NULL) THEN		--se trovo qualcosa, allora c'è un evento sociale che da conflitto
		RAISE EXCEPTION 'Errore, in questa data e orario è in corso un intervallo';
	END IF;
	RETURN NEW;
END;
$check$ LANGUAGE PLPGSQL;	

CREATE OR REPLACE TRIGGER ConflittoEventoSocialeIntervalloInsert
BEFORE INSERT ON EVENTO_SOCIALE
FOR EACH ROW
EXECUTE PROCEDURE ConflittoEventoSocialeIntervallo();

CREATE OR REPLACE TRIGGER ConflittoEventoSocialeIntervalloUpdate
BEFORE UPDATE ON EVENTO_SOCIALE
FOR EACH ROW
EXECUTE PROCEDURE ConflittoEventoSocialeIntervallo();