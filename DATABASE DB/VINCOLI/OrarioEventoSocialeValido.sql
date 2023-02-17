--OrarioEventoSocialeValido
--l'orario di inizio evento sociale deve essere minore
--dell'orario di fine evento sociale
ALTER TABLE EVENTO_SOCIALE ADD
CONSTRAINT OrarioEventoSocialeValido CHECK(OrarioInizioEvento < OrarioFineEvento);