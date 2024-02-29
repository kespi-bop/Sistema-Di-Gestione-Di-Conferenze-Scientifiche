--Creao la tabella PARTECIPARE: 
CREATE TABLE PARTECIPARE
(
emailP VARCHAR(100) NOT NULL,
CodSessione INTEGER NOT NULL
);

--Aggiungo i vincoli:

--chiavi esterne:
--CodSessione
--se viene eliminato un partecipante allora vengono eliminate
--le coppie sessione-partecipante del partecipante appena eliminato
ALTER TABLE PARTECIPARE ADD
CONSTRAINT ParteciparePartecipante FOREIGN KEY(emailP)
REFERENCES PARTECIPANTE(emailP)ON DELETE CASCADE;
--CodSessione
--se viene eliminata una sessione allora vengono eliminate
--le coppie sessione-partecipante della sessione appena eliminata
ALTER TABLE PARTECIPARE ADD
CONSTRAINT PartecipareSessione FOREIGN KEY(CodSessione)
REFERENCES SESSIONE(CodSessione)ON DELETE CASCADE;
