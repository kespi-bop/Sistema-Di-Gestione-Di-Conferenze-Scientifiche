--Creao la tabella PUBBLICITÀ: 
CREATE TABLE PUBBLICITÀ
(
PartitaIva CHAR(11) NOT NULL,
CodConferenza INTEGER NOT NULL,
Spesa MONEY NOT NULL
);

--Aggiungo i vincoli:

--chiavi esterne:
--PartitaIva
ALTER TABLE Pubblicità ADD
CONSTRAINT PubblicitàSponsor FOREIGN KEY(PartitaIva)
REFERENCES SPONSOR(PartitaIva);
--CodConferenza
--se viene cancellata una conferenza, vengono cancellate
--anche tutte le pubblicità legate a quella conferenza
ALTER TABLE Pubblicità ADD
CONSTRAINT PubblicitàConferenza FOREIGN KEY(CodConferenza)
REFERENCES CONFERENZA(CodConferenza) ON DELETE CASCADE;
--UnicoSponsor
--ogni coppia di sponsor e conferenza sponsorizzata è unica
--e lo sponsor ha un'unica spesa per quella conferenza
ALTER TABLE Pubblicità ADD
CONSTRAINT UnicoSponsor UNIQUE(PartitaIva, CodConferenza);
