--Creao la tabella AMMINISTRARE: 
CREATE TABLE AMMINISTRARE
(
NomeIstituzione VARCHAR(100) NOT NULL,
CodConferenza INTEGER NOT NULL
);

--Aggiungo i vincoli:

--chiavi esterne:
--NomeIstituzione
--se viene eliminata una conferenza, vengono cancellate
--tutte le amministrazioni (coppia conferenza-ente) di quella conferenza
ALTER TABLE AMMINISTRARE ADD
CONSTRAINT AmministrareConferenza FOREIGN KEY(CodConferenza)
REFERENCES CONFERENZA(CodConferenza) ON DELETE CASCADE;
--NomeIstituzione
ALTER TABLE AMMINISTRARE ADD
CONSTRAINT AmministrareEnte FOREIGN KEY(NomeIstituzione)
REFERENCES ENTE(NomeIstituzione);