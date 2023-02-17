--DataConferenzaValida
--la data di inizio conferenza deve essere minore
--della data di fine conferenza
ALTER TABLE CONFERENZA ADD
CONSTRAINT DataConferenzaValida CHECK(DataInizio <= DataFine);