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