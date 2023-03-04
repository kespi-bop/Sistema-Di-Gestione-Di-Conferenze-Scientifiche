----AUTOMATISMO AMMINISTRARE ORGANIZZATORE

----Per garantire che non esistano Organizzatori_locali il cui ente non amministra la conferenza
----gestita dall'organizzatore, all'inserimento della coppia di PK dell'Organizzatore_locale
----e della conferenza che sta amministrando, nella tabella ponte Organizzare_L viene controllato
----se l'ente dell'organizzatore amministra la conferenza e in caso negativo vengono aggiuniti nella
----tabella ponte l'Istituzione di afferenza dell'organizzatore e il Cod della conferenza in questione.
----Automatizzando questo inseritmento non è necessario quindi andare a lanciare alcuna eccezione
----che controlli la presenza dell'istituzione di un organizzatore_locale di una conferenza come 
----sua amministratrice.

----Struttura del trigger:

CREATE OR REPLACE FUNCTION Organizzare_L_Ente() RETURNS TRIGGER AS
$check$
DECLARE 
	TempEnte ENTE.NomeIstituzione%TYPE;
	TempIstituzione ENTE.NomeIstituzione%TYPE;
BEGIN
	SELECT Istituzione_di_Afferenza INTO TempIstituzione	--estraggo l'istituzione di afferenza dell'organizzatore
	FROM ORGANIZZATORE_LOCALE								--della conferenza appena aggiunto
	WHERE emailL = NEW.emailL;

	SELECT NomeIstituzione FROM AMMINISTRARE AS A INTO TempEnte		--verifico se l'ente amministra già la conferenza
	WHERE(A.NomeIstituzione = TempIstituzione AND
		  A.CodConferenza = NEW.CodConferenza);
	
	IF(TempEnte IS NULL) THEN		--se non la amministra, allora aggiungo l'ente dell'organizzatore a quelli che amministrano la conferenza
		INSERT INTO AMMINISTRARE(CodConferenza,NomeIstituzione)
						  VALUES(NEW.CodConferenza, TempIstituzione);
	END IF;
	RETURN NEW;
END;
$check$ LANGUAGE PLPGSQL;

CREATE OR REPLACE TRIGGER Organizzare_L_Ente_Insert
BEFORE INSERT ON ORGANIZZARE_L
FOR EACH ROW
EXECUTE PROCEDURE Organizzare_L_Ente();

----Lo stesso automatismo viene applicato per la tabella ponte Organizzare_S.