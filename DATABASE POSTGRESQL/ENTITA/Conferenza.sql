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