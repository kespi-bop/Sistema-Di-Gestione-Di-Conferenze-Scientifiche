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
$check$ LANGUAGE PLPGSQL

--Definisco il Trigger per settare automaticamente la pk di INTERVALLO:
CREATE OR REPLACE TRIGGER IntervalloPK
BEFORE INSERT ON INTERVALLO
FOR EACH ROW
EXECUTE PROCEDURE GenerateIntervalloPK();