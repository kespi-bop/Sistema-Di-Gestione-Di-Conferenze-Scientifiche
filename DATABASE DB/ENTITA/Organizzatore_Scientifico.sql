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