--DataProgrammValida
--la data di un programma deve essere compresa(o uguale) tra
--la data di inizio e la data di fine della conferenza che
--sta strutturando

CREATE OR REPLACE FUNCTION DataProgrammaValida() RETURNS TRIGGER AS
$check$
DECLARE
	TempProg PROGRAMMA.CodProgramma%TYPE;
BEGIN
SELECT CodProgramma FROM (PROGRAMMA NATURAL JOIN CONFERENZA) AS C  --prendo i programmi con le date non conformi
   	WHERE (((NEW.DataProgramma < C.DataInizio) OR (NEW.DataProgramma > C.DataFine))AND
			C.CodConferenza = NEW.CodConferenza)
	INTO TempProg;
	
	IF(TempProg IS NOT NULL) THEN				--se vengono trovati Programmi con date non conformi
		RAISE EXCEPTION 'Errore, questo programma non rientra nella conferenza';
	END IF;
	RETURN NEW;
END;
$check$ LANGUAGE PLPGSQL;

--aggiungendo un nuovo programma (in inserimento) devo verificare che questo rientri
--nella data della conferenza a cui è riferito
CREATE OR REPLACE TRIGGER DataProgrammValidaInsert
BEFORE INSERT ON PROGRAMMA
FOR EACH ROW
EXECUTE PROCEDURE DataProgrammaValida();

--aggiornando un programma, devo verificare che questo rientri nella data della
--conferenza a cui è riferito
CREATE OR REPLACE TRIGGER DataProgrammaValidaUpdate
BEFORE UPDATE ON PROGRAMMA
FOR EACH ROW
EXECUTE PROCEDURE DataProgrammaValida();