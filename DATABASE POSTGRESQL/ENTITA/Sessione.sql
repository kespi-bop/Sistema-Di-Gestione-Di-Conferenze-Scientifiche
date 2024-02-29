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