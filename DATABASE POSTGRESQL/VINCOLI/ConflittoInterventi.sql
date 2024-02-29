--ConflittoInterventi
--in una sessione un intervento non può iniziare o 
--finire mentre è in corso un altro intervento


CREATE OR REPLACE FUNCTION ConflittoInterventi() RETURNS TRIGGER AS
$check$
DECLARE
	TempIntervento INTERVENTO.CodIntervento%TYPE;
BEGIN
	SELECT CodIntervento FROM INTERVENTO
	INTO TempIntervento	--estraggo il codice del potenziale intervallo che mi da conflitto
	WHERE((((NEW.OrarioInizioIntervento >= OrarioInizioIntervento) AND (NEW.OrarioInizioIntervento < OrarioFineIntervento)) OR
		  ((NEW.OrarioFineIntervento > OrarioInizioIntervento) AND (NEW.OrarioFineIntervento <= OrarioFineIntervento))) AND
		  (NEW.CodSessione = CodSessione));
	
	IF(TempIntervento IS NOT NULL) THEN		--se trovo qualcosa, allora c'è un intervallo che da conflitto
		RAISE EXCEPTION 'Errore, in questa sessione, a quest''orario è già in corso un altro intervento';
	END IF;
	RETURN NEW;
END;

$check$ LANGUAGE PLPGSQL;	

CREATE OR REPLACE TRIGGER ConflittoInterventiInsert
BEFORE INSERT ON INTERVENTO
FOR EACH ROW
EXECUTE PROCEDURE ConflittoInterventi();

CREATE OR REPLACE TRIGGER ConflittoInterventiUpdate
BEFORE UPDATE ON INTERVENTO
FOR EACH ROW
EXECUTE PROCEDURE ConflittoInterventi();