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
$check$ LANGUAGE PLPGSQL

--Definisco il Trigger per settare automaticamente la pk di INTERVENTO:
CREATE OR REPLACE TRIGGER InterventoPK
BEFORE INSERT ON INTERVENTO
FOR EACH ROW
EXECUTE PROCEDURE GenerateInterventoPK();

