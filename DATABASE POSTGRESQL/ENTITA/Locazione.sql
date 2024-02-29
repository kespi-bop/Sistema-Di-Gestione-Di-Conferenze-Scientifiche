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