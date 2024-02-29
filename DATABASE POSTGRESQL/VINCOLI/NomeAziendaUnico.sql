--NomeAziendaUnico
--il nome dell'azienda deve essere univoco
ALTER TABLE SPONSOR ADD				
CONSTRAINT NomeAziendaUnico UNIQUE(NomeAzienda);