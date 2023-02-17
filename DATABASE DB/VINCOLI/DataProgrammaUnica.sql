--DataProgrammaUnica
--Può esserci un solo programma per un giorno della conferenza
ALTER TABLE PROGRAMMA ADD
CONSTRAINT DataProgrammaUnica UNIQUE(DataProgramma, CodConferenza);