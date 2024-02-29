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