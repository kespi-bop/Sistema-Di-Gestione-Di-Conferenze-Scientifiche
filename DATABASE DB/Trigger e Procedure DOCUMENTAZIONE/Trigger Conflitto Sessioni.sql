----CONFLITTO SESSIONI
----All'inserimento di una sessione, nello specificare la locazione, gli orari di inizio e di fine è necessario
----andare a controllare che in quel determinato lasso di tempo (definito da orario inizio e fine) la locazione non
----sia già occupata da altre sessioni in corso.
----Il controllo del trigger avviente tramite una SELECT la quale estrae all'interno di TempSessione lo specifico
----codice della sessione che da Conflitto; va a ricercare quindi le sessioni che sono in corso tra orario inizio e 
----orario fine appena inseriti, e che si svolgono nella locazione che è stata inserita.
----Nel caso in cui venga effettivamente trovata un CodSessione allora viene lanciata un'exception:
----'Errore, in questa data e orario la locazione è occupata';

----Definizione del trigger:


CREATE OR REPLACE FUNCTION ConflittoSessioni() RETURNS TRIGGER AS
$check$
DECLARE 
	TempSessione SESSIONE.CodSessione%TYPE;
	DataSessione PROGRAMMA.DataProgramma%TYPE;		
BEGIN
	SELECT CodSessione FROM SESSIONE AS S
	INTO TempSessione
	WHERE S.NomeLocazione = NEW.NomeLocazione AND			--controllo che la locazione non sia già occupata a quella data e ora
		  CodProgramma = NEW.CodProgramma AND
		 ( ( (NEW.OrarioInizioSessione  >= S.OrarioInizioSessione) AND (NEW.OrarioInizioSessione  < S.OrarioFineSessione) ) OR
		  ( (NEW.OrarioFineSessione > S.OrarioInizioSessione) AND (NEW.OrarioFineSessione <= S.OrarioFineSessione) ) );
		  
	
	IF(TempSessione IS NOT NULL) THEN
		RAISE EXCEPTION 'Errore, in questa data e orario la locazione è occupata';
	END IF;
	RETURN NEW;
END;

$check$ LANGUAGE PLPGSQL;		


--data una nuova sessione, bisogna controllare che questa
--possa essere ospitata in una locazione libera:
CREATE OR REPLACE TRIGGER ConflittoSessioniInsert
BEFORE INSERT ON SESSIONE
FOR EACH ROW
EXECUTE PROCEDURE ConflittoSessioni();  


----Esempio di attivazione del trigger:

--(per maggiore leggibilità non sarà riportata alcuna Descrizione della sessione).
INSERT INTO SESSIONE(OrarioInizioSessione,OrarioFineSessione,TitoloSessione,DescrizioneSessione,Chair,KeynoteSpeaker,CodProgramma,NomeLocazione)
VALUES('9:00','10:30','Parts can worth like the whole','.', 'FranciscoFlorez-Revuelta@gmail.com','NicolaLerme@unipa.it',0,'Teatrino');
	   
INSERT INTO SESSIONE(OrarioInizioSessione,OrarioFineSessione,TitoloSessione,DescrizioneSessione,Chair,KeynoteSpeaker,CodProgramma,NomeLocazione)
VALUES('9:30','10:30','HBAxSCES - Human Behaviour Analysis for Smart City Environment Safety','.','JonAnderGomezAdrian@gmail.com','GiovanniRana@gmail.com',0,'Teatrino');

ERROR:  Errore, in questa data e orario la locazione è occupata
CONTEXT:  funzione PL/pgSQL conflittosessioni() riga 14 a RAISE
SQL state: P0001

----tra le 9:30 e le 10:30 la locazione Teatrino è occupata dalla sessione "Parts can worth like the whole",
----non è quindi possibile aggiungere la sessione "HBAxSCES" in Teatrino; verrà quindi lancaita l'exception.