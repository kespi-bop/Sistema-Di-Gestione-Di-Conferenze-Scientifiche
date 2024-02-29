--OrarioInterventoValido
--l'orario di inizio intervento deve essere minore dell'orario di fine intervento
ALTER TABLE INTERVENTO ADD
CONSTRAINT OrarioInterventoValido CHECK(OrarioInizioIntervento < OrarioFineIntervento);