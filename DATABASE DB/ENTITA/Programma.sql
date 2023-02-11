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