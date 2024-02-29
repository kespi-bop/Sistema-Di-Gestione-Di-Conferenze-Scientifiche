--IndirizzoUnico
--non posso avere due sedi diverse con lo stesso
--nome della via, numero civico e città
ALTER TABLE SEDE ADD
CONSTRAINT IndirizzoUnico UNIQUE(NomeVia,NumeroCivico,Città);