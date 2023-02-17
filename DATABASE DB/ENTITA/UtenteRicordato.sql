CREATE TABLE UtenteRicordato
(
	emailU VARCHAR(100) PRIMARY KEY CHECK(emailU LIKE '__%@___%.__%'),
	password VARCHAR(25) NOT NULL
);