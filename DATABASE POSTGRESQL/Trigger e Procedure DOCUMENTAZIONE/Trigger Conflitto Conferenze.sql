----CONFLITTO CONFERENZE
----All'inserimento di una conferenza, nello specificare la data di inizio e di fine è necessario
----andare a controllare che in quel lasso di tempo(definito da data inizio e fine) la sede non sia 
----già occupata da altre conferenze in corso.
----Il controllo del trigger avviene tramite una SELECT la quale estrae al'interno di TempConferenza
----lo specifico codice della conferenza che da conflitto; va a ricercare quindi le conferenze che
----sono in corso tra data inizio e data fine(compresi)appena inseriti, e che si svolgono nella sede inserita.
----Nel caso in cui venga effettivamente trovato un CodConferenza allora viene lanciata un'exception:
----'Errore, la sede ospita già una conferenza';

----Definizione del trigger:

CREATE OR REPLACE FUNCTION NoConferenzaParallela() RETURNS TRIGGER AS
$check$
DECLARE 
	TempConferenza CONFERENZA.CodConferenza%TYPE;
BEGIN
	SELECT CodConferenza FROM CONFERENZA INTO TempConferenza
	WHERE NomeSede = NEW.NomeSede AND							--controllo che la nuova conferenza inserita(o updatata) non abbia
		  ((NEW.DataInizio  BETWEEN DataInizio AND DataFine) OR	--la sede occupata in quella data	
		  (NEW.DataFine  BETWEEN DataInizio AND DataFine));
	
	IF(TempConferenza IS NOT NULL) THEN
		RAISE EXCEPTION 'Errore, la sede ospita gia una conferenza';
	END IF;
	RETURN NEW;
END;
$check$ LANGUAGE PLPGSQL;		  


--data una nuova conferenza, bisogna controllare che questa
--possa essere ospitata in una sede libera(in inserimento)
CREATE OR REPLACE TRIGGER NoConferenzaParallelaInsert
BEFORE INSERT ON CONFERENZA
FOR EACH ROW
EXECUTE PROCEDURE NoConferenzaParallela();  

----Esempio di attivazione del trigger:
INSERT INTO CONFERENZA(TitoloConferenza,DataInizio,DataFine,Descrizione,NomeSede)
VALUES('21st International Conference on Image Analysis and Processing', '23/05/2022','27/05/2022',
'.','monte sant''angelo');

--(per maggiore leggibilità non sara' riportata alcuna Descrizione della conferenza).	   
INSERT INTO CONFERENZA(TitoloConferenza,DataInizio,DataFine,Descrizione,NomeSede)
VALUES('IEEE CSR CyberSecurity and Resilience', '26/05/2022','29/05/2022', 
'.','monte sant''angelo');

ERROR:  Errore, la sede ospita gia una conferenza
CONTEXT:  funzione PL/pgSQL noconferenzaparallela() riga 11 a RAISE
SQL state: P0001

----Nella sede monte sant'angelo, nel giorno 26/05/2022 si svolgera' la conferenza 
----'21st International...', non e' quindi possibile aggiungere la conferenza 'IEEE CSR CyberSecurity...'
----avente come data di svolgimento 26/05/2022 e come locazione monte sant'angelo; viene dunque lanciata l'exception.