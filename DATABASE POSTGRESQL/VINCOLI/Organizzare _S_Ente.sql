--Organizzare_S_Ente
--L'organizzatore scientifico di una conferenza deve necessariamente
--appartenere a un ente che amministra la conferenza
CREATE OR REPLACE FUNCTION Organizzare_S_Ente() RETURNS TRIGGER AS
$check$
DECLARE 
	TempEnte ENTE.NomeIstituzione%TYPE;
	TempIstituzione ENTE.NomeIstituzione%TYPE;
BEGIN
	SELECT Istituzione_di_Afferenza INTO TempIstituzione	--estraggo l'istituzione di afferenza dell'organizzatore
	FROM ORGANIZZATORE_SCIENTIFICO								--della conferenza appena aggiunto
	WHERE emailS = NEW.emailS;

	SELECT NomeIstituzione FROM AMMINISTRARE AS A INTO TempEnte		--verifico se l'ente amministra già la conferenza
	WHERE(A.NomeIstituzione = TempIstituzione AND
		  A.CodConferenza = NEW.CodConferenza);
	
	IF(TempEnte IS NULL) THEN		--se non la amministra già, allora aggiungo l'ente dell'organizzatore a quelli che amministrano la conferenza
		INSERT INTO AMMINISTRARE(CodConferenza,NomeIstituzione)
						  VALUES(NEW.CodConferenza, TempIstituzione);
	END IF;
	RETURN NEW;
END;
$check$ LANGUAGE PLPGSQL;

CREATE OR REPLACE TRIGGER Organizzare_S_Ente_Insert
BEFORE INSERT ON ORGANIZZARE_S
FOR EACH ROW
EXECUTE PROCEDURE Organizzare_S_Ente();