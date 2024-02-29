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