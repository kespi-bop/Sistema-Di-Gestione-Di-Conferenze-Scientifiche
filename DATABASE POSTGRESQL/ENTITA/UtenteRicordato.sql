--Creo la tabella UTENTERICORDATO:
CREATE TABLE UtenteRicordato
(
	nome VARCHAR(100) NOT NULL,
	emailU VARCHAR(100) PRIMARY KEY CHECK(emailU LIKE '__%@___%.__%'),
	password VARCHAR(25) NOT NULL
);

--vincolo di un unico utente ricordato
CREATE OR REPLACE FUNCTION UnicoUtenteRicordato() RETURNS TRIGGER AS
$check$
DECLARE
	Nupla INTEGER;
BEGIN
SELECT Count(*) as N_Tuple FROM UtenteRicordato
	INTO Nupla;
	
	IF(Nupla > 0) THEN	--se viene già trovato un utente ricordato
		RAISE EXCEPTION 'Errore, non puoi ricordare un altro utente';
	END IF;
	RETURN NEW;
END;
$check$ LANGUAGE PLPGSQL;

--posso ricordare soltanto un utente nel mio programma
--per ricordarne un altro, il vecchio deve prima fare un logout
CREATE OR REPLACE TRIGGER UtenteUnicoInsert
BEFORE INSERT ON UtenteRicordato
FOR EACH ROW
EXECUTE PROCEDURE UnicoUtenteRicordato();