CREATE TYPE TipoIntervallo AS ENUM ('CoffeBreak','Pranzo');

CREATE TYPE TipoEvento AS ENUM ('Cena','Gita');

CREATE TYPE TipoTitolo AS ENUM ('Mr','Mss','Dottor','Dottoressa','Professor','Professoressa');





--Creao la tabella SEDE: 
CREATE TABLE SEDE
(
NomeSede VARCHAR(100) PRIMARY KEY,
NomeVia	VARCHAR(100) NOT NULL,
NumeroCivico SMALLINT NOT NULL,
Città VARCHAR(100) NOT NULL
);





--Creao la tabella LOCAZIONE: 
CREATE TABLE LOCAZIONE
(
NomeLocazione VARCHAR(100) PRIMARY KEY,
NomeSede VARCHAR(100) NOT NULL
);

--Aggiungo i vincoli:
--chiave esterna:
--Se una sede viene cancellata, vengono cancellate anche
--tutte le sue locazioni
ALTER TABLE LOCAZIONE ADD
CONSTRAINT Composizione FOREIGN KEY(NomeSede)
REFERENCES SEDE(NomeSede) ON DELETE CASCADE;





--Creao la tabella CONFERENZA: 
CREATE TABLE CONFERENZA
(
CodConferenza SERIAL PRIMARY KEY,
DataInizio DATE NOT NULL,
DataFine DATE NOT NULL,
TitoloConferenza VARCHAR(64) NOT NULL,
Descrizione VARCHAR(1500) NOT NULL,
NomeSede VARCHAR(100) NOT NULL
);

--per l'attributo chiave CodConferenza è stato applicato il tipo SERIAL
--affinchè si autogenerasse automaticamente una chiave primaria, tuttavia
--per le prossime tabelle nelle quali è stato necessario introdurre
--chiavi autogenerate, per fini scolastici verranno introdotte delle automazioni
--create a mano implementate con dei TRIGGERS.

--chiave esterna:
ALTER TABLE CONFERENZA ADD
CONSTRAINT Ospitare FOREIGN KEY(NomeSede)
REFERENCES SEDE(NomeSede) ON DELETE CASCADE;

--imposto l'inizio della sequenza di generazione di codici conferenze a 1
ALTER SEQUENCE conferenza_codconferenza_seq RESTART WITH 1;





--Creao la tabella SPONSOR: 
CREATE TABLE SPONSOR
(
PartitaIVA CHAR(11) PRIMARY KEY,
NomeAzienda VARCHAR(50) NOT NULL
);





--Creao la tabella PROGRAMMA: 
CREATE TABLE PROGRAMMA
(
CodProgramma INTEGER DEFAULT 0,
DataProgramma DATE NOT NULL,
CodConferenza INTEGER NOT NULL
);

--Aggiungo i vincoli:
--chiave primaria:
ALTER TABLE PROGRAMMA ADD
CONSTRAINT Programma_pk PRIMARY KEY(CodProgramma);
--chiave esterna:
--se una conferenza viene cancellata, vengono cancellati
--tutti i programmi legati a essa
ALTER TABLE PROGRAMMA ADD
CONSTRAINT Strutturazione FOREIGN KEY(CodConferenza)
REFERENCES CONFERENZA(CodConferenza) ON DELETE CASCADE;
	
--PK AUTOMATICA
--Definisco la funzione richiamata dal Trigger:
CREATE OR REPLACE FUNCTION GenerateProgrammmaPK() RETURNS TRIGGER AS
$check$
DECLARE
	pk PROGRAMMA.CodProgramma%TYPE;
	Conta INTEGER;
BEGIN
	SELECT COUNT(CodProgramma) FROM PROGRAMMA		--nel caso in cui la tabella sia vuota
	INTO Conta										--la pk è 0
	LIMIT 2;
	
	IF(Conta=0)THEN				
		NEW.CodProgramma = 0;	
	RETURN NEW;
	END IF;
	
	SELECT MAX(CodProgramma) FROM PROGRAMMA
	INTO pk;
		NEW.CodProgramma = pk + 1;
	RETURN NEW;
END;
$check$ LANGUAGE PLPGSQL;

--Definisco il Trigger per settare automaticamente la pk di EVENTO_SOCIALE:
CREATE OR REPLACE TRIGGER ProgrammaPK
BEFORE INSERT ON PROGRAMMA
FOR EACH ROW
EXECUTE PROCEDURE GenerateProgrammmaPK();





--Creao la tabella INTERVALLO: 
CREATE TABLE INTERVALLO
(
CodIntervallo INTEGER DEFAULT 0,
TipoIntervallo TipoIntervallo NOT NULL,
OrarioInizioIntervallo TIME NOT NULL,
OrarioFineIntervallo TIME NOT NULL,
CodProgramma INTEGER NOT NULL
);

--Aggiungo vincoli:
--chiave primaria:
ALTER TABLE INTERVALLO ADD 
CONSTRAINT Intervallo_pk PRIMARY KEY(CodIntervallo);
--chiave esterna:
--CodProgramma
--se viene cancellato un programma, vengono cancellate
--tutte le pause che erano richieste nel programma
ALTER TABLE INTERVALLO ADD
CONSTRAINT Richiesta FOREIGN KEY(CodProgramma)
REFERENCES PROGRAMMA(CodProgramma) ON DELETE CASCADE;

--PK AUTOMATICA
--Definisco la funzione richiamata dal Trigger:
CREATE OR REPLACE FUNCTION GenerateIntervalloPK() RETURNS TRIGGER AS
$check$
DECLARE
	pk INTERVALLO.CodIntervallo%TYPE;
	Conta INTEGER;
BEGIN
	SELECT COUNT(CodIntervallo) FROM INTERVALLO		--nel caso in cui la tabella sia vuota
	INTO Conta										--la pk è 0
	LIMIT 2;
	
	IF(Conta=0)THEN				
		NEW.CodIntervallo = 0;	
	RETURN NEW;
	END IF;
	
	SELECT MAX(CodIntervallo) FROM INTERVALLO
	INTO pk;
		NEW.CodIntervallo = pk + 1;

	RETURN NEW;
END;
$check$ LANGUAGE PLPGSQL;

--Definisco il Trigger per settare automaticamente la pk di INTERVALLO:
CREATE OR REPLACE TRIGGER IntervalloPK
BEFORE INSERT ON INTERVALLO
FOR EACH ROW
EXECUTE PROCEDURE GenerateIntervalloPK();





--Creao la tabella EVENTO_SOCIALE: 
CREATE TABLE EVENTO_SOCIALE
(
CodEvento INTEGER DEFAULT 0,
TipoEvento TipoEvento NOT NULL,
OrarioInizioEvento TIME NOT NULL,
OrarioFineEvento TIME NOT NULL,
CodProgramma INTEGER NOT NULL
);


--Aggiungo i vincoli:
--chiave primaria:
ALTER TABLE EVENTO_SOCIALE ADD 
CONSTRAINT Evento_Sociale_pk PRIMARY KEY(CodEvento);
--chiave esterna:
--CodProgramma
--se viene cancellato un programma, vengono cancellati
--tutti gli eventi sociali presentati dal programma
ALTER TABLE EVENTO_SOCIALE ADD 
CONSTRAINT Presentare FOREIGN KEY(CodProgramma)
REFERENCES PROGRAMMA(CodProgramma) ON DELETE CASCADE;

--PK AUTOMATICA
--Definisco la funzione richiamata dal Trigger:
CREATE OR REPLACE FUNCTION GenerateEvento_SocialePK() RETURNS TRIGGER AS
$check$
DECLARE
	pk EVENTO_SOCIALE.CodEvento%TYPE;
	Conta INTEGER;
BEGIN
	SELECT COUNT(CodEvento) FROM EVENTO_SOCIALE		--nel caso in cui la tabella sia vuota
	INTO Conta										--la pk è 0
	LIMIT 2;
	
	IF(Conta=0)THEN				
		NEW.CodEvento = 0;	
	RETURN NEW;
	END IF;
	
	SELECT MAX(CodEvento) FROM EVENTO_SOCIALE
	INTO pk;
		NEW.CodEvento = pk + 1;
	RETURN NEW;
END;
$check$ LANGUAGE PLPGSQL;

--Definisco il Trigger per settare automaticamente la pk di EVENTO_SOCIALE:
CREATE OR REPLACE TRIGGER Evento_SocialePK
BEFORE INSERT ON EVENTO_SOCIALE
FOR EACH ROW
EXECUTE PROCEDURE GenerateEvento_SocialePK();





--Creao la tabella ENTE: 
CREATE TABLE ENTE
(
NomeIstituzione VARCHAR(100) PRIMARY KEY
);





--Creao la tabella Organizzatore_Locale: 
CREATE TABLE Organizzatore_Locale
(
emailL VARCHAR(100) PRIMARY KEY CHECK(emailL LIKE '__%@___%.__%'),
Titolo TipoTitolo NOT NULL,
Nome VARCHAR(100) NOT NULL,
Cognome VARCHAR(100) NOT NULL,
Istituzione_Di_Afferenza VARCHAR(100) NOT NULL          
);

--Aggiungo vincoli:
--chiavi esterne:
--Istituzione_Di_Afferenza
ALTER TABLE Organizzatore_Locale ADD
CONSTRAINT Appartenenza_L FOREIGN KEY(Istituzione_Di_Afferenza)
REFERENCES ENTE(NomeIstituzione);





--Creao la tabella Organizzatore_Scientifico: 
CREATE TABLE Organizzatore_Scientifico
(
emailS VARCHAR(100) PRIMARY KEY CHECK(emailS LIKE '__%@___%.__%'),
DescrizioneCurriculum VARCHAR(500),
Titolo TipoTitolo NOT NULL,
Nome VARCHAR(100) NOT NULL,
Cognome VARCHAR(100) NOT NULL,
Istituzione_Di_Afferenza VARCHAR(100)
);

--Aggiungo vincoli:
--chiavi esterne:
--Istituzione_Di_Afferenza
ALTER TABLE Organizzatore_Scientifico ADD
CONSTRAINT Appartenenza_S FOREIGN KEY(Istituzione_Di_Afferenza)
REFERENCES ENTE(NomeIstituzione);





--Creao la tabella PARTECIPANTE: 
CREATE TABLE PARTECIPANTE
(
emailP VARCHAR(100) PRIMARY KEY CHECK(emailP LIKE '__%@___%.__%'),
Titolo TipoTitolo NOT NULL,
Nome VARCHAR(100) NOT NULL,
Cognome VARCHAR(100) NOT NULL,
Istituzione_di_Afferenza VARCHAR(100) NOT NULL
);

--Aggiungo i vincoli:
--chiavi esterne:
--Istituzione_di_Afferenza
ALTER TABLE PARTECIPANTE ADD
CONSTRAINT Iscrizione FOREIGN KEY(Istituzione_di_Afferenza)
REFERENCES ENTE(NomeIstituzione);





--Creao la tabella SESSIONE: 
CREATE TABLE SESSIONE
(
CodSessione INTEGER DEFAULT 0,
OrarioInizioSessione TIME NOT NULL,
OrarioFineSessione TIME NOT NULL,
TitoloSessione VARCHAR(100) NOT NULL,
DescrizioneSessione VARCHAR(10000),
Chair VARCHAR(100) NOT NULL,
KeyNoteSpeaker VARCHAR(100),
CodProgramma INTEGER NOT NULL,
NomeLocazione VARCHAR(100) NOT NULL
);

--Aggiungo i vincoli:
--chiave primaria:
ALTER TABLE SESSIONE ADD
CONSTRAINT CodSessione_pk PRIMARY KEY(CodSessione);
--chiavi esterne:
--Chair
ALTER TABLE SESSIONE ADD
CONSTRAINT EssereChair FOREIGN KEY(Chair)
REFERENCES ORGANIZZATORE_SCIENTIFICO(emailS);
--KeyNoteSpeaker
--se viene cancellato il partecipante che era KS
--viene settato KS a NULL(la sessione non ha KS)
ALTER TABLE SESSIONE ADD
CONSTRAINT EssereKS FOREIGN KEY(KeyNoteSpeaker)
REFERENCES PARTECIPANTE(emailP) ON DELETE SET NULL;
--CodProgramma
--se viene cancellato un programma, vengono
--cancellate anche le sessioni legate al programma
ALTER TABLE SESSIONE ADD
CONSTRAINT Gestione FOREIGN KEY(CodProgramma)
REFERENCES PROGRAMMA(CodProgramma) ON DELETE CASCADE;
--NomeLocazione
ALTER TABLE SESSIONE ADD
CONSTRAINT AvereLuogo FOREIGN KEY(NomeLocazione)
REFERENCES LOCAZIONE(NomeLocazione);

--PK AUTOMATICA
--Definisco la funzione richiamata dal Trigger:
CREATE OR REPLACE FUNCTION GenerateSessionePK() RETURNS TRIGGER AS
$check$
DECLARE
	pk SESSIONE.CodSessione%TYPE;
	Conta INTEGER;
BEGIN
	SELECT COUNT(CodSessione) FROM SESSIONE		--nel caso in cui la tabella sia vuota
	INTO Conta										--la pk è 0
	LIMIT 2;				--limito 2 tuple per risparmiare efficienza di calcolo
	
	IF(Conta=0)THEN				
		NEW.CodSessione = 0;	
	RETURN NEW;
	END IF;
	
	SELECT MAX(CodSessione) FROM SESSIONE
	INTO pk;
		NEW.CodSessione = pk + 1;
	RETURN NEW;
END;
$check$ LANGUAGE PLPGSQL;

--Definisco il Trigger per settare automaticamente la pk di CONFERENZA:
CREATE OR REPLACE TRIGGER SessionePK
BEFORE INSERT ON SESSIONE
FOR EACH ROW
EXECUTE PROCEDURE GenerateSessionePK();





--Creao la tabella INTERVENTO: 
CREATE TABLE INTERVENTO
(
CodIntervento INTEGER DEFAULT 0,
OrarioInizioIntervento TIME NOT NULL,
OrarioFineIntervento TIME NOT NULL,
Abstract VARCHAR(500),
CodPartecipante VARCHAR(100) NOT NULL,
CodSessione INTEGER NOT NULL
);

--Aggiungo i vincoli:
--chiave primaria:
ALTER TABLE INTERVENTO ADD 
CONSTRAINT Intervento_pk PRIMARY KEY(CodIntervento);
--chiavi esterne:
--CodPartecipante
--se un partecipante viene cancellato,
--vengono cancellati anche gli interventi
--legati a quel partecipante
ALTER TABLE INTERVENTO ADD 
CONSTRAINT Esporre FOREIGN KEY(CodPartecipante)
REFERENCES PARTECIPANTE(emailP) ON DELETE CASCADE;
--CodSessione
--se una sessione viene cancellata, vengono
--cancellate tutti gli interventi proposti da
--quella sessione
ALTER TABLE INTERVENTO ADD 
CONSTRAINT Proporre FOREIGN KEY(CodSessione)
REFERENCES SESSIONE(CodSessione) ON DELETE CASCADE;

--PK AUTOMATICA
--Definisco la funzione richiamata dal Trigger:
CREATE OR REPLACE FUNCTION GenerateInterventoPK() RETURNS TRIGGER AS
$check$
DECLARE
	pk INTERVENTO.CodIntervento%TYPE;
	Conta INTEGER;
BEGIN
	SELECT COUNT(CodIntervento) FROM INTERVENTO		--nel caso in cui la tabella sia vuota
	INTO Conta										--la pk è 0
	LIMIT 2;
	
	IF(Conta=0)THEN				
		NEW.CodIntervento = 0;	
	RETURN NEW;
	END IF;
	
	SELECT MAX(CodIntervento) FROM INTERVENTO
	INTO pk;
		NEW.CodIntervento = pk + 1;
	RETURN NEW;
END;
$check$ LANGUAGE PLPGSQL;

--Definisco il Trigger per settare automaticamente la pk di INTERVENTO:
CREATE OR REPLACE TRIGGER InterventoPK
BEFORE INSERT ON INTERVENTO
FOR EACH ROW
EXECUTE PROCEDURE GenerateInterventoPK();





--Creao la tabella PUBBLICITÀ: 
CREATE TABLE Pubblicità
(
PartitaIva CHAR(11) NOT NULL,
CodConferenza INTEGER NOT NULL,
Spesa MONEY NOT NULL
);

--Aggiungo i vincoli:
--chiavi esterne:
--PartitaIva
ALTER TABLE Pubblicità ADD
CONSTRAINT PubblicitàSponsor FOREIGN KEY(PartitaIva)
REFERENCES SPONSOR(PartitaIva);
--CodConferenza
--se viene cancellata una conferenza, vengono cancellate
--anche tutte le pubblicità legate a quella conferenza
ALTER TABLE Pubblicità ADD
CONSTRAINT PubblicitàConferenza FOREIGN KEY(CodConferenza)
REFERENCES CONFERENZA(CodConferenza) ON DELETE CASCADE;
--UnicoSponsor
--ogni coppia di sponsor e conferenza sponsorizzata è unica
--e lo sponsor ha un'unica spesa per quella conferenza
ALTER TABLE Pubblicità ADD
CONSTRAINT UnicoSponsor UNIQUE(PartitaIva, CodConferenza);





--Creao la tabella AMMINISTRARE: 
CREATE TABLE AMMINISTRARE
(
NomeIstituzione VARCHAR(100) NOT NULL,
CodConferenza INTEGER NOT NULL
);

--Aggiungo i vincoli:
--chiavi esterne:
--NomeIstituzione
--se viene eliminata una conferenza, vengono cancellate
--tutte le amministrazioni (coppia conferenza-ente) di quella conferenza
ALTER TABLE AMMINISTRARE ADD
CONSTRAINT AmministrareConferenza FOREIGN KEY(CodConferenza)
REFERENCES CONFERENZA(CodConferenza) ON DELETE CASCADE;
--NomeIstituzione
ALTER TABLE AMMINISTRARE ADD
CONSTRAINT AmministrareEnte FOREIGN KEY(NomeIstituzione)
REFERENCES ENTE(NomeIstituzione);





--Creao la tabella Organizzare_L: 
CREATE TABLE Organizzare_L
(
emailL VARCHAR(100) NOT NULL,
CodConferenza INTEGER NOT NULL
);

--Aggiungo i vincoli:
--chiavi esterne:
--CodConferenza
--se viene eliminata una conferenza, vengono cancellate
--anche le coppie di organizzatore-conferenza(cancellata)
ALTER TABLE ORGANIZZARE_L ADD
CONSTRAINT Organizzare_L_Conferenza FOREIGN KEY(CodConferenza)
REFERENCES CONFERENZA(CodConferenza)ON DELETE CASCADE;
--emailL
--se viene eliminato un Organizzatore Locale, vengono cancellate
--anche le coppie di organizzatore(cancellato)-conferenza
ALTER TABLE ORGANIZZARE_L ADD
CONSTRAINT Organizzatore_L FOREIGN KEY(emailL)
REFERENCES Organizzatore_Locale(emailL)ON DELETE CASCADE;





--Creao la tabella Organizzare_S: 
CREATE TABLE Organizzare_S
(
emailS VARCHAR(100) NOT NULL,
CodConferenza INTEGER NOT NULL
);

--Aggiungo i vincoli:
--chiavi esterne:
--CodConferenza
--se viene eliminata una conferenza, vengono cancellate
--anche le coppie di organizzatore-conferenza(cancellata)
ALTER TABLE ORGANIZZARE_S ADD
CONSTRAINT Organizzare_S_Conferenza FOREIGN KEY(CodConferenza)
REFERENCES CONFERENZA(CodConferenza)ON DELETE CASCADE;
--emailS
--se viene eliminato un Organizzatore Scientifico, vengono cancellate
--anche le coppie di organizzatore(cancellato)-conferenza
ALTER TABLE ORGANIZZARE_S ADD
CONSTRAINT Organizzatore_S FOREIGN KEY(emailS)
REFERENCES Organizzatore_Scientifico(emailS)ON DELETE CASCADE;





--Creao la tabella PARTECIPARE: 
CREATE TABLE PARTECIPARE
(
emailP VARCHAR(100) NOT NULL,
CodSessione INTEGER NOT NULL
);

--Aggiungo i vincoli:
--chiavi esterne:
--CodSessione
--se viene eliminato un partecipante allora vengono eliminate
--le coppie sessione-partecipante del partecipante appena eliminato
ALTER TABLE PARTECIPARE ADD
CONSTRAINT ParteciparePartecipante FOREIGN KEY(emailP)
REFERENCES PARTECIPANTE(emailP)ON DELETE CASCADE;
--CodSessione
--se viene eliminata una sessione allora vengono eliminate
--le coppie sessione-partecipante della sessione appena eliminata
ALTER TABLE PARTECIPARE ADD
CONSTRAINT PartecipareSessione FOREIGN KEY(CodSessione)
REFERENCES SESSIONE(CodSessione)ON DELETE CASCADE;






--ChairValido
--il chair deve essere organizzatore scientifico della conferenza
--nella quale è presente la sessione che sta amminsitrando.
CREATE OR REPLACE FUNCTION ChairValido() RETURNS TRIGGER AS
$check$
DECLARE
	ConferenzaChair CONFERENZA.CodConferenza%TYPE;
	ConferenzaOrganizzatore CONFERENZA.CodConferenza%TYPE;
BEGIN
	SELECT CodConferenza FROM (CONFERENZA NATURAL JOIN PROGRAMMA)		--estraggo la conferenza della sessione che sto inserendo			  
	INTO ConferenzaChair
	WHERE(NEW.CodProgramma = CodProgramma);
	
	SELECT CodConferenza FROM ORGANIZZARE_S AS O INTO ConferenzaOrganizzatore	--controllo se il chair inserito organizza quella confernza estratta
	WHERE (O.CodConferenza = ConferenzaChair AND O.emailS = NEW.Chair);
	
	IF(ConferenzaOrganizzatore IS NULL) THEN
		RAISE EXCEPTION 'Errore, questo chair non partecipa all organizzazione scientifica di questa conferenza';
	END IF;
	RETURN NEW;
END;
$check$ LANGUAGE PLPGSQL;

CREATE OR REPLACE TRIGGER ChairValidoInsert
BEFORE INSERT ON SESSIONE
FOR EACH ROW
EXECUTE PROCEDURE ChairValido();

CREATE OR REPLACE TRIGGER ChairValidoUpdate
BEFORE UPDATE OF Chair ON SESSIONE
FOR EACH ROW
EXECUTE PROCEDURE ChairValido();





--ConflittoEventoSocialeIntervallo
--un evento sociale non può iniziare o finire mentre è
--in corso un evento sociale che avviene nella stessa data
CREATE OR REPLACE FUNCTION ConflittoEventoSocialeIntervallo() RETURNS TRIGGER AS
$check$
DECLARE
	TempIntervallo INTERVALLO.CodIntervallo%TYPE;
BEGIN
	SELECT CodIntervallo FROM (EVENTO_SOCIALE NATURAL JOIN INTERVALLO)
	INTO TempIntervallo	--estraggo il codice del potenziale intervallo che mi da conflitto
	WHERE((((NEW.OrarioInizioEvento >= OrarioInizioIntervallo) AND (NEW.OrarioInizioEvento < OrarioFineIntervallo)) OR
		  ((NEW.OrarioFineEvento > OrarioInizioIntervallo) AND (NEW.OrarioFineEvento <= OrarioFineIntervallo)))AND
		  (NEW.CodProgramma = CodProgramma));
	
	IF(TempIntervallo IS NOT NULL) THEN		--se trovo qualcosa, allora c'è un evento sociale che da conflitto
		RAISE EXCEPTION 'Errore, in questa data e orario è in corso un intervallo';
	END IF;
	RETURN NEW;
END;
$check$ LANGUAGE PLPGSQL;

CREATE OR REPLACE TRIGGER ConflittoEventoSocialeIntervalloInsert
BEFORE INSERT ON EVENTO_SOCIALE
FOR EACH ROW
EXECUTE PROCEDURE ConflittoEventoSocialeIntervallo();

CREATE OR REPLACE TRIGGER ConflittoEventoSocialeIntervalloUpdate
BEFORE UPDATE ON EVENTO_SOCIALE
FOR EACH ROW
EXECUTE PROCEDURE ConflittoEventoSocialeIntervallo();





--ConflittoEventoSocialeSessione
--un evento sociale non può iniziare o finire mentre è
--in corso una sessione che avviene nella stessa data
CREATE OR REPLACE FUNCTION ConflittoEventoSocialeSessione() RETURNS TRIGGER AS
$check$
DECLARE
	TempSessione SESSIONE.CodSessione%TYPE;
BEGIN
	SELECT CodSessione FROM (PROGRAMMA NATURAL JOIN SESSIONE)
	INTO TempSessione	--estraggo il codice della potenziale sessione che mi da conflitto
	WHERE((((NEW.OrarioInizioEvento >= OrarioInizioSessione) AND (NEW.OrarioInizioEvento < OrarioFineSessione)) OR
		  ((NEW.OrarioFineEvento > OrarioInizioSessione) AND (NEW.OrarioFineEvento <= OrarioFineSessione)))AND
		   (NEW.CodProgramma = CodProgramma));
	
	IF(TempSessione IS NOT NULL) THEN		--se trovo qualcosa, allora c'è un evento sociale che da conflitto
		RAISE EXCEPTION 'Errore, in questa data e orario è in corso una sessione';
	END IF;
	RETURN NEW;
END;
$check$ LANGUAGE PLPGSQL;	

CREATE OR REPLACE TRIGGER ConflittoEventoSocialeSessioneInsert
BEFORE INSERT ON EVENTO_SOCIALE
FOR EACH ROW
EXECUTE PROCEDURE ConflittoEventoSocialeSessione();

CREATE OR REPLACE TRIGGER ConflittoEventoSocialeSessioneUpdate
BEFORE UPDATE ON EVENTO_SOCIALE
FOR EACH ROW
EXECUTE PROCEDURE ConflittoEventoSocialeSessione();





--ConflittoIntervalloEventoSociale
--un intervallo non può iniziare o finire mentre è in corso
--un evento sociale che avviene nella sua stessa data
CREATE OR REPLACE FUNCTION ConflittoIntervalloEventoSociale() RETURNS TRIGGER AS
$check$
DECLARE
	TempEventoSociale EVENTO_SOCIALE.CodEvento%TYPE;
BEGIN
	SELECT CodEvento FROM (INTERVALLO NATURAL JOIN EVENTO_SOCIALE)
	INTO TempEventoSociale	--estraggo il codice del potenziale evento sociale che mi da conflitto
	WHERE((((NEW.OrarioInizioIntervallo >= OrarioInizioEvento) AND (NEW.OrarioInizioIntervallo < OrarioFineEvento)) OR
		  ((NEW.OrarioFineIntervallo > OrarioInizioEvento) AND (NEW.OrarioFineIntervallo <= OrarioFineEvento))) AND
		  (NEW.CodProgramma = CodProgramma));
	
	IF(TempEventoSociale IS NOT NULL) THEN		--se trovo qualcosa, allora c'è un intervallo che da conflitto
		RAISE EXCEPTION 'Errore, in questa data e orario è in corso un evento sociale';
	END IF;
	RETURN NEW;
END;
$check$ LANGUAGE PLPGSQL;	

CREATE OR REPLACE TRIGGER ConflittoIntervalloEventoSocialeInsert
BEFORE INSERT ON INTERVALLO
FOR EACH ROW
EXECUTE PROCEDURE ConflittoIntervalloEventoSociale();

CREATE OR REPLACE TRIGGER ConflittoIntervalloEventoSocialeUpdate
BEFORE UPDATE ON INTERVALLO
FOR EACH ROW
EXECUTE PROCEDURE ConflittoIntervalloEventoSociale();





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





--ConflittoSessioneEventoSociale
--una sessione non può iniziare o finire durante
--un evento sociale che avviene nella sua stessa data.
CREATE OR REPLACE FUNCTION ConflittoSessioneEventoSociale() RETURNS TRIGGER AS
$check$
DECLARE
	TempEvento EVENTO_SOCIALE.CodEvento%TYPE;
BEGIN
	SELECT CodEvento FROM (PROGRAMMA NATURAL JOIN EVENTO_SOCIALE)
	INTO TempEvento		--estraggo il codice del potenziale evento sociale che mi da conflitto
	WHERE(
			( ( (NEW.OrarioInizioSessione >= OrarioInizioEvento) AND (NEW.OrarioInizioSessione < OrarioFineEvento) ) OR
			( (NEW.OrarioFineSessione > OrarioInizioEvento) AND (NEW.OrarioFineSessione <= OrarioFineEvento) ) )AND
			(NEW.CodProgramma = CodProgramma)
		 );
	
	IF(TempEvento IS NOT NULL) THEN		--se trovo qualcosa, allora c'è un evento sociale che da conflitto
		RAISE EXCEPTION 'Errore, in questa data e orario è in corso un evento sociale';
	END IF;
	RETURN NEW;
END;
$check$ LANGUAGE PLPGSQL;	

CREATE OR REPLACE TRIGGER ConflittoSessioneEventoSocialeInsert
BEFORE INSERT ON SESSIONE
FOR EACH ROW
EXECUTE PROCEDURE ConflittoSessioneEventoSociale();

CREATE OR REPLACE TRIGGER ConflittoSessioneEventoSocialeUpdate
BEFORE UPDATE ON SESSIONE
FOR EACH ROW
EXECUTE PROCEDURE ConflittoSessioneEventoSociale();





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





--ConflittoSessioni
--Non possono esserci più sessioni con
--gli stessi orari e con la stessa locazione
--programmate nella stessa data
CREATE OR REPLACE FUNCTION ConflittoSessioni() RETURNS TRIGGER AS
$check$
DECLARE 
	TempSessione SESSIONE.CodSessione%TYPE;
	DataSessione PROGRAMMA.DataProgramma%TYPE;		
BEGIN
	SELECT CodSessione FROM SESSIONE AS S
	INTO TempSessione
	WHERE S.NomeLocazione = NEW.NomeLocazione AND							--controllo che la locazione non sia già occupata a quella data e ora
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
--possa essere ospitata in una locazione libera(in inserimento)
CREATE OR REPLACE TRIGGER ConflittoSessioniInsert
BEFORE INSERT ON SESSIONE
FOR EACH ROW
EXECUTE PROCEDURE ConflittoSessioni();  

--in caso di cambio locazione per una sessione, devo controllare
--che la nuova locazione sia libera(in update)
CREATE OR REPLACE TRIGGER ConflittoSessioniUpdate
BEFORE UPDATE ON SESSIONE
FOR EACH ROW
EXECUTE PROCEDURE ConflittoSessioni();  





--DataConferenzaValida
--la data di inizio conferenza deve essere minore
--della data di fine conferenza
ALTER TABLE CONFERENZA ADD
CONSTRAINT DataConferenzaValida CHECK(DataInizio <= DataFine);





--DataProgrammaUnica
--Può esserci un solo programma per un giorno della conferenza
ALTER TABLE PROGRAMMA ADD
CONSTRAINT DataProgrammaUnica UNIQUE(DataProgramma, CodConferenza);





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





--IndirizzoUnico
--non posso avere due sedi diverse con lo stesso
--nome della via, numero civico e città
ALTER TABLE SEDE ADD
CONSTRAINT IndirizzoUnico UNIQUE(NomeVia,NumeroCivico,Città);





--InterventoInOrario
--L'orario di inizio e l'orario di fine intervento 
--devono essere compresi tra l'orario di inizio e
--l'orario di fine della sessione a cui si riferisce l'intervento
CREATE OR REPLACE FUNCTION InterventoInOrario() RETURNS TRIGGER AS
$check$
DECLARE 
	TempSessione SESSIONE.CodSessione%TYPE;
BEGIN
	SELECT CodSessione FROM SESSIONE AS S INTO TempSessione   --prendo gli interventi con gli orari non conformi
	WHERE ( ((NEW.OrarioInizioIntervento < S.OrarioInizioSessione) AND (NEW.OrarioInizioIntervento < S.OrarioFineSessione)) OR
		  ((NEW.OrarioFineIntervento > S.OrarioInizioSessione) AND (NEW.OrarioFineIntervento > S.OrarioFineSessione)) )AND
		   NEW.CodSessione = CodSessione;
		  
	IF(TempSessione IS NOT NULL) THEN				--se vengono trovati interventi con orari non conformi
		RAISE EXCEPTION 'Errore, orario intervento non idoneo alla sessione';
	END IF;
	RETURN NEW;
END;
$check$ LANGUAGE PLPGSQL;


--aggiungendo un nuovo intervento (in inserimento) devo verificare che questo rientri
--nell'orario della sessione a cui è riferito
CREATE OR REPLACE TRIGGER InterventoInOrarioInsert
BEFORE INSERT ON INTERVENTO
FOR EACH ROW
EXECUTE PROCEDURE InterventoInOrario();


--aggiornando un intervento, devo verificare che questo rientri nell'orario della
--sessione a cui è riferito
CREATE OR REPLACE TRIGGER InterventoInOrarioUpdate
BEFORE UPDATE ON INTERVENTO
FOR EACH ROW
EXECUTE PROCEDURE InterventoInOrario();

--aggiungendo un nuovo intervento (in inserimento) devo verificare che questo rientri
--nell'orario della sessione a cui è riferito
CREATE OR REPLACE TRIGGER InterventoInOrarioInsert
BEFORE INSERT ON INTERVENTO
FOR EACH ROW
EXECUTE PROCEDURE InterventoInOrario();


--aggiornando un intervento, devo verificare che questo rientri nell'orario della
--sessione a cui è riferito
CREATE OR REPLACE TRIGGER InterventoInOrarioUpdate
BEFORE UPDATE ON INTERVENTO
FOR EACH ROW
EXECUTE PROCEDURE InterventoInOrario();





--il keynote speaker di una sessione deve essere necessariamente
--partecipante di quella sessione

--appena creo la mia sessione, se ho inserito un keynote speaker
--allora aggiungo questo ai partecipanti di quella sessione
CREATE OR REPLACE FUNCTION KSPartecipante() RETURNS TRIGGER AS
$check$
DECLARE 
	TempKS SESSIONE.CodSessione%TYPE;
BEGIN
	IF(NEW.KeynoteSpeaker IS NOT NULL) THEN				--controllo se è stato realmente inserito un KS per la sessione
		SELECT CodSessione FROM PARTECIPARE AS P INTO TempKS		--controllo che nei partecipanti non ci sia già 
		WHERE (P.CodSessione = NEW.CodSessione AND				--il KS della sessione
			   P.emailP = NEW.KeynoteSpeaker);
		
		IF(TempKS IS NULL) THEN								--se il KS non è presente tra i partecipanti,
				INSERT INTO PARTECIPARE (CodSessione, emailP) 	 --allora lo inserisco
								VALUES(NEW.CodSessione,New.KeynoteSpeaker); 
		END IF;
	END IF;
	RETURN NEW;
END;
$check$ LANGUAGE PLPGSQL;

CREATE OR REPLACE TRIGGER KSPartecipanteInsert
AFTER INSERT ON SESSIONE
FOR EACH ROW
EXECUTE PROCEDURE KSPartecipante();

CREATE OR REPLACE TRIGGER KSPartecipanteUpdate
AFTER UPDATE OF KeynoteSpeaker ON SESSIONE
FOR EACH ROW
EXECUTE PROCEDURE KSPartecipante(); 





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





--NoConfernzaParallela
--Non possono esserci più conferenze con la stessa data 
--nella stessa sede
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
		RAISE EXCEPTION 'Errore, la sede ospita già una conferenza';
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

--in caso di cambio sede per una conferenza, devo controllare
--che la nuova sede sia libera(in update)
CREATE OR REPLACE TRIGGER NoConferenzaParallelaUpdate
BEFORE UPDATE ON CONFERENZA
FOR EACH ROW
EXECUTE PROCEDURE NoConferenzaParallela();





--OrarioEventoSocialeValido
--l'orario di inizio evento sociale deve essere minore
--dell'orario di fine evento sociale
ALTER TABLE EVENTO_SOCIALE ADD
CONSTRAINT OrarioEventoSocialeValido CHECK(OrarioInizioEvento < OrarioFineEvento);





--OrarioIntervalloValido
--l'orario di inizio di un intervallo deve essere
--minore dell'orario di fine intervallo
ALTER TABLE INTERVALLO ADD
CONSTRAINT OrarioIntervalloValido CHECK(OrarioInizioIntervallo < OrarioFineIntervallo);





--OrarioInterventoValido
--l'orario di inizio intervento deve essere minore dell'orario di fine intervento
ALTER TABLE INTERVENTO ADD
CONSTRAINT OrarioInterventoValido CHECK(OrarioInizioIntervento < OrarioFineIntervento);





--OrarioSessioneValido
--l'orario di inizio Sessione deve essere minore dell'orario di fine Sessione
ALTER TABLE SESSIONE ADD
CONSTRAINT OrarioSessioneValido CHECK(OrarioInizioSessione < OrarioFineSessione);





--Organizzare_L_Ente
--L'organizzatore locale di una conferenza deve necessariamente
--appartenere a un ente che amministra la conferenza
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





--ConflittoEventiSociali
--un evento sociale non può iniziare o finire mentre è in corso
--un evento sociale nello stesso programma
CREATE OR REPLACE FUNCTION ConflittoEventiSociali() RETURNS TRIGGER AS
$check$
DECLARE
	TempEventoSociale EVENTO_SOCIALE.CodEvento%TYPE;
BEGIN
	SELECT CodEvento FROM EVENTO_SOCIALE
	INTO TempEventoSociale	--estraggo il codice del potenziale evento sociale che mi da conflitto
	WHERE((((NEW.OrarioInizioEvento >= OrarioInizioEvento) AND (NEW.OrarioInizioEvento < OrarioFineEvento)) OR
		  ((NEW.OrarioFineEvento > OrarioInizioEvento) AND (NEW.OrarioFineEvento <= OrarioFineEvento))) AND
		  (NEW.CodProgramma = CodProgramma));
	
	IF(TempEventoSociale IS NOT NULL) THEN		--se trovo qualcosa, allora c'è un evento sociale che da conflitto
		RAISE EXCEPTION 'Errore, in questa data e orario è in corso un evento sociale';
	END IF;
	RETURN NEW;
END;

$check$ LANGUAGE PLPGSQL;	

CREATE OR REPLACE TRIGGER ConflittoEventiSociali
BEFORE INSERT ON EVENTO_SOCIALE
FOR EACH ROW
EXECUTE PROCEDURE ConflittoEventiSociali();

CREATE OR REPLACE TRIGGER ConflittoEventiSociali
BEFORE UPDATE ON EVENTO_SOCIALE
FOR EACH ROW
EXECUTE PROCEDURE ConflittoEventiSociali();





--ConflittoIntervalli
--un intervallo non può iniziare o finire mentre è in corso
--un altro intervallo nello stesso programma
CREATE OR REPLACE FUNCTION ConflittoIntervalli() RETURNS TRIGGER AS
$check$
DECLARE
	TempIntervallo INTERVALLO.CodIntervallo%TYPE;
BEGIN
	SELECT CodIntervallo FROM INTERVALLO
	INTO TempIntervallo	--estraggo il codice del potenziale intervallo che mi da conflitto
	WHERE((((NEW.OrarioInizioIntervallo >= OrarioInizioIntervallo) AND (NEW.OrarioInizioIntervallo < OrarioFineIntervallo)) OR
		  ((NEW.OrarioFineIntervallo > OrarioInizioIntervallo) AND (NEW.OrarioFineIntervallo <= OrarioFineIntervallo))) AND
		  (NEW.CodProgramma = CodProgramma));
	
	IF(TempIntervallo IS NOT NULL) THEN		--se trovo qualcosa, allora c'è un intervallo che da conflitto
		RAISE EXCEPTION 'Errore, in questa data e orario è in corso un evento sociale';
	END IF;
	RETURN NEW;
END;

$check$ LANGUAGE PLPGSQL;	

CREATE OR REPLACE TRIGGER ConflittoIntervalli
BEFORE INSERT ON INTERVALLO
FOR EACH ROW
EXECUTE PROCEDURE ConflittoIntervalli();

CREATE OR REPLACE TRIGGER ConflittoIntervalli
BEFORE UPDATE ON INTERVALLO
FOR EACH ROW
EXECUTE PROCEDURE ConflittoIntervalli();





--NomeAziendaUnico
--il nome dell'azienda deve essere univoco
ALTER TABLE SPONSOR ADD				
CONSTRAINT NomeAziendaUnico UNIQUE(NomeAzienda);
