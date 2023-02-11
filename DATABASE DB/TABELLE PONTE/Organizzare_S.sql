--Creao la tabella Organizzare_S: 
CREATE TABLE Organizzare_S
(
emailS VARCHAR(100) NOT NULL,
CodConferenza INTEGER NOT NULL
);

--Aggiungo i vincoli:

--chiavi esterne:
--CodConferenza
--se viene eliminata una conferenza, vengono cancellate
--anche le coppie di organizzatore-conferenza(cancellata)
ALTER TABLE ORGANIZZARE_S ADD
CONSTRAINT Organizzare_S_Conferenza FOREIGN KEY(CodConferenza)
REFERENCES CONFERENZA(CodConferenza)ON DELETE CASCADE;
--emailS
--se viene eliminato un Organizzatore Scientifico, vengono cancellate
--anche le coppie di organizzatore(cancellato)-conferenza
ALTER TABLE ORGANIZZARE_S ADD
CONSTRAINT Organizzatore_S FOREIGN KEY(emailS)
REFERENCES Organizzatore_Scientifico(emailS)ON DELETE CASCADE;