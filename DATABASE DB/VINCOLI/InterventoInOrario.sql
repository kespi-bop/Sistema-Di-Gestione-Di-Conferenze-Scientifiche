--InterventoInOrario
--L'orario di inizio e l'orario di fine intervento 
--devono essere compresi tra l'orario di inizio e
--l'orario di fine della sessione a cui si riferisce l'intervento

CREATE OR REPLACE FUNCTION InterventoInOrario() RETURNS TRIGGER AS
$check$
DECLARE 
	TempSessione SESSIONE.CodSessione%TYPE;
BEGIN
	SELECT CodSessione FROM SESSIONE AS S INTO TempSessione   --prendo gli interventi con gli orari non conformi
	WHERE ( ((NEW.OrarioInizioIntervento < S.OrarioInizioSessione) AND (NEW.OrarioInizioIntervento < S.OrarioFineSessione)) OR
		  ((NEW.OrarioFineIntervento > S.OrarioInizioSessione) AND (NEW.OrarioFineIntervento > S.OrarioFineSessione)) )AND
		   NEW.CodSessione = CodSessione;
		  
	IF(TempSessione IS NOT NULL) THEN				--se vengono trovati interventi con orari non conformi
		RAISE EXCEPTION 'Errore, orario intervento non idoneo alla sessione';
	END IF;
	RETURN NEW;
END;
$check$ LANGUAGE PLPGSQL;


--aggiungendo un nuovo intervento (in inserimento) devo verificare che questo rientri
--nell'orario della sessione a cui è riferito
CREATE OR REPLACE TRIGGER InterventoInOrarioInsert
BEFORE INSERT ON INTERVENTO
FOR EACH ROW
EXECUTE PROCEDURE InterventoInOrario();


--aggiornando un intervento, devo verificare che questo rientri nell'orario della
--sessione a cui è riferito
CREATE OR REPLACE TRIGGER InterventoInOrarioUpdate
BEFORE UPDATE ON INTERVENTO
FOR EACH ROW
EXECUTE PROCEDURE InterventoInOrario();