--ConflittoIntervalli
--un intervallo non può iniziare o finire mentre è in corso
--un altro intervallo nello stesso programma
CREATE OR REPLACE FUNCTION ConflittoIntervalli() RETURNS TRIGGER AS
$check$
DECLARE
	TempIntervallo INTERVALLO.CodIntervallo%TYPE;
BEGIN
	SELECT CodIntervallo FROM INTERVALLO
	INTO TempIntervallo	--estraggo il codice del potenziale intervallo che mi da conflitto
	WHERE((((NEW.OrarioInizioIntervallo >= OrarioInizioIntervallo) AND (NEW.OrarioInizioIntervallo < OrarioFineIntervallo)) OR
		  ((NEW.OrarioFineIntervallo > OrarioInizioIntervallo) AND (NEW.OrarioFineIntervallo <= OrarioFineIntervallo))) AND
		  (NEW.CodProgramma = CodProgramma));
	
	IF(TempIntervallo IS NOT NULL) THEN		--se trovo qualcosa, allora c'è un intervallo che da conflitto
		RAISE EXCEPTION 'Errore, in questa data e orario è in corso un evento sociale';
	END IF;
	RETURN NEW;
END;

$check$ LANGUAGE PLPGSQL;	

CREATE OR REPLACE TRIGGER ConflittoIntervalli
BEFORE INSERT ON INTERVALLO
FOR EACH ROW
EXECUTE PROCEDURE ConflittoIntervalli();

CREATE OR REPLACE TRIGGER ConflittoIntervalli
BEFORE UPDATE ON INTERVALLO
FOR EACH ROW
EXECUTE PROCEDURE ConflittoIntervalli();