--Creao la tabella Organizzare_L: 
CREATE TABLE Organizzare_L
(
emailL VARCHAR(100) NOT NULL,
CodConferenza INTEGER NOT NULL
);

--Aggiungo i vincoli:

--chiavi esterne:
--CodConferenza
--se viene eliminata una conferenza, vengono cancellate
--anche le coppie di organizzatore-conferenza(cancellata)
ALTER TABLE ORGANIZZARE_L ADD
CONSTRAINT Organizzare_L_Conferenza FOREIGN KEY(CodConferenza)
REFERENCES CONFERENZA(CodConferenza)ON DELETE CASCADE;
--emailL
--se viene eliminato un Organizzatore Locale, vengono cancellate
--anche le coppie di organizzatore(cancellato)-conferenza
ALTER TABLE ORGANIZZARE_L ADD
CONSTRAINT Organizzatore_L FOREIGN KEY(emailL)
REFERENCES Organizzatore_Locale(emailL)ON DELETE CASCADE;