--ConflittoIntervalloSessione
--un intervallo non può iniziare o finire mentre è in corso
--una sessione che avviene nella stesa data


CREATE OR REPLACE FUNCTION ConflittoIntervalloSessione() RETURNS TRIGGER AS
$check$
DECLARE
	TempSessione SESSIONE.CodSessione%TYPE;
BEGIN
	SELECT CodSessione FROM ((INTERVALLO NATURAL JOIN PROGRAMMA)NATURAL JOIN SESSIONE)
	INTO TempSessione	--estraggo il codice della potenziale sessione che mi da conflitto
	WHERE((((NEW.OrarioInizioIntervallo >= OrarioInizioSessione) AND (NEW.OrarioInizioIntervallo < OrarioFineSessione)) OR
		  ((NEW.OrarioFineIntervallo > OrarioInizioSessione) AND (NEW.OrarioFineIntervallo <= OrarioFineSessione))) AND
		  (NEW.CodProgramma = CodProgramma));
	
	IF(TempSessione IS NOT NULL) THEN		--se trovo qualcosa, allora c'è un intervallo che da conflitto
		RAISE EXCEPTION 'Errore, in questa data e orario è in corso una sessione';
	END IF;
	RETURN NEW;
END;
$check$ LANGUAGE PLPGSQL;	

CREATE OR REPLACE TRIGGER ConflittoIntervalloSessioneInsert
BEFORE INSERT ON INTERVALLO
FOR EACH ROW
EXECUTE PROCEDURE ConflittoIntervalloSessione();

CREATE OR REPLACE TRIGGER ConflittoIntervalloSessioneUpdate
BEFORE UPDATE ON INTERVALLO
FOR EACH ROW
EXECUTE PROCEDURE ConflittoIntervalloSessione();