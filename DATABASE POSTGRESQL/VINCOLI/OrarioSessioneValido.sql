--OrarioSessioneValido
--l'orario di inizio Sessione deve essere minore dell'orario di fine Sessione
ALTER TABLE SESSIONE ADD
CONSTRAINT OrarioSessioneValido CHECK(OrarioInizioSessione < OrarioFineSessione);