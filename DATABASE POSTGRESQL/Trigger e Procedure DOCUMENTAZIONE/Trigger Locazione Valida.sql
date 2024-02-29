----LOCAZIONE VALIDA
----All'inserimento di una sessione, nello specificare la locazione in cui questa si svolga è necessario controllare che
----questa sia una delle locazioni interne alla Sede della conferenza che ospita la sessione appena creata.
----Il controllo del trigger avviene tramite una prima SELECT la quale estrae il nome della sede in cui 
----si svolge la conferenza della sessione creata.
----Una seconda SELECT estrae la locazione che abbia la stessa PK della locazione della sessione inserita ("NEW.NomeLocazione = NomeLocazione") 
----e che appartenga alla sede estratta dalla precedente SELECT.
----Nel caso in cui non viene estratta una locazione dalla seconda SELECT, vuol dire che questa non si trova
----all'interno della sede in cui viene svolta la conferenza della sessione appena inserita.
----Viene dunque chiamata una exception: 'Errore, in questa sede non esiste questa locazione'.

----Definizione del trigger:

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
		RAISE EXCEPTION 'Errore, in questa conferenza non esiste questa locazione';
	END IF;
	RETURN NEW;
END;
$check$ LANGUAGE PLPGSQL;	

CREATE OR REPLACE TRIGGER LocazioneValidaInsert
BEFORE INSERT ON SESSIONE
FOR EACH ROW
EXECUTE PROCEDURE LocazioneValida();

----Esempio di attivazione del trigger:

INSERT INTO LOCAZIONE (NomeLocazione, NomeSede)
VALUES('Sala sul Chiostro del 500','convitto palmieri');

--(per maggiore leggibilità non sarà riportata alcuna Descrizione della conferenza).
INSERT INTO CONFERENZA(CodConferenza, TitoloConferenza,DataInizio,DataFine,Descrizione,NomeSede)
VALUES('IEEE CSR CyberSecurity and Resilience', '27/07/2022','29/07/2022','.','monte sant''angelo');

INSERT INTO PROGRAMMA(CodProgramma, DataProgramma,CodConferenza)
VALUES(5,'27/07/2022',2);

INSERT INTO SESSIONE(OrarioInizioSessione,OrarioFineSessione,TitoloSessione,Chair,CodProgramma,NomeLocazione)
VALUES('9:00','10:00','Plenary session PL-1','FranciscoFlorez-Revuelta@gmail.com',5,'Sala sul Chiostro del 500 (First floor)');

ERROR:  Errore, in questa conferenza non esiste questa locazione
CONTEXT:  funzione PL/pgSQL locazionevalida() riga 15 a RAISE
SQL state: P0001

----La locazione Sala sul Chiostro del 500 (First floor) appartiene quindi alla sede Convitto Palmieri;
----la sessione (avente come locazione Sala sul Chiostro del 500 (First floor)) appartiene a una conferenza
----che ha come sede monte sant'angelo (che NON è la sede della Sala sul chiostro del 500), quindi viene lanciata l'exception.