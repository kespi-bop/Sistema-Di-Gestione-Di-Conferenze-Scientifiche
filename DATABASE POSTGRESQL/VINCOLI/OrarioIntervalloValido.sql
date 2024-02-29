--OrarioIntervalloValido
--l'orario di inizio di un intervallo deve essere
--minore dell'orario di fine intervallo
ALTER TABLE INTERVALLO ADD
CONSTRAINT OrarioIntervalloValido CHECK(OrarioInizioIntervallo < OrarioFineIntervallo);