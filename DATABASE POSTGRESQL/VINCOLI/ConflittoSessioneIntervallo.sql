--ConflittoSessioneIntervallo
--un intervallo non può iniziare o finire mentre è
--in corso  un evento sociale che avviene nella sua stessa data


CREATE OR REPLACE FUNCTION ConflittoSessioneIntervallo() RETURNS TRIGGER AS
$check$
DECLARE
	TempIntervallo INTERVALLO.CodIntervallo%TYPE;
BEGIN
	SELECT CodIntervallo FROM (SESSIONE NATURAL JOIN INTERVALLo)
	INTO TempIntervallo		--estraggo il codice del potenziale intervallo che mi da conflitto
	WHERE(
			( ( (NEW.OrarioInizioSessione >= OrarioInizioIntervallo) AND (NEW.OrarioInizioSessione < OrarioFineIntervallo) )  OR
		    ( (NEW.OrarioFineSessione > OrarioInizioIntervallo) AND (NEW.OrarioFineSessione <= OrarioFineIntervallo) ) ) AND
		    (NEW.CodProgramma = CodProgramma)
		  );
	
	IF(TempIntervallo IS NOT NULL) THEN		--se trovo qualcosa, allora c'è un intervallo che da conflitto
		RAISE EXCEPTION 'Errore, in questa data e orario è in corso un intervallo';
	END IF;
	RETURN NEW;
END;
$check$ LANGUAGE PLPGSQL;	

CREATE OR REPLACE TRIGGER ConflittoSessioneIntervalloInsert
BEFORE INSERT ON SESSIONE
FOR EACH ROW
EXECUTE PROCEDURE ConflittoSessioneIntervallo();

CREATE OR REPLACE TRIGGER ConflittoSessioneIntervalloUpdate
BEFORE UPDATE ON SESSIONE
FOR EACH ROW
EXECUTE PROCEDURE ConflittoSessioneIntervallo();