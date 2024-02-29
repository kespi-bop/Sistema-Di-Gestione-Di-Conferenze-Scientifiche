--LocazioneValida
--la locazione di una sessione deve appartenere alla sede
--che opsita la conferenza di quella sessione

CREATE OR REPLACE FUNCTION LocazioneValida() RETURNS TRIGGER AS
$check$
DECLARE
	TempSede SEDE.NomeSede%TYPE;
	TempLocazione LOCAZIONE.NomeLocazione%TYPE;
BEGIN
	SELECT NomeSede FROM ((PROGRAMMA NATURAL JOIN CONFERENZA)NATURAL JOIN SEDE)
	INTO TempSede		--estraggo la sede della sessione che sto inserendo
	WHERE(NEW.CodProgramma = CodProgramma);
	
	SELECT NomeLocazione FROM(LOCAZIONE NATURAL JOIN SEDE)		--estraggo la locazione che potrebbe dare conflitto 
	INTO TempLocazione
	WHERE(NomeSede = TempSede AND NEW.NomeLocazione = NomeLocazione);
	
	IF(TempLocazione IS  NULL) THEN		--se non trovo alcuna locazione, allora questa non esiste in quella sede
		RAISE EXCEPTION 'Errore, in questa sede non esiste questa locazione';
	END IF;
	RETURN NEW;
END;
$check$ LANGUAGE PLPGSQL;	

CREATE OR REPLACE TRIGGER LocazioneValidaInsert
BEFORE INSERT ON SESSIONE
FOR EACH ROW
EXECUTE PROCEDURE LocazioneValida();

