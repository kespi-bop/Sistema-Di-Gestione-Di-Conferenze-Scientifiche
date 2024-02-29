--ChairValido
--il chair deve essere organizzatore scientifico della conferenza
--nella quale è presente la sessione che sta amminsitrando.
CREATE OR REPLACE FUNCTION ChairValido() RETURNS TRIGGER AS
$check$
DECLARE
	ConferenzaChair CONFERENZA.CodConferenza%TYPE;
	ConferenzaOrganizzatore CONFERENZA.CodConferenza%TYPE;
BEGIN
	SELECT CodConferenza FROM (CONFERENZA NATURAL JOIN PROGRAMMA)		--estraggo la conferenza della sessione che sto inserendo			  
	INTO ConferenzaChair
	WHERE(NEW.CodProgramma = CodProgramma);
	
	SELECT CodConferenza FROM ORGANIZZARE_S AS O INTO ConferenzaOrganizzatore	--controllo se il chair inserito organizza quella confernza estratta
	WHERE (O.CodConferenza = ConferenzaChair AND O.emailS = NEW.Chair);
	
	IF(ConferenzaOrganizzatore IS NULL) THEN
		RAISE EXCEPTION 'Errore, questo chair non partecipa all organizzazione scientifica di questa conferenza';
	END IF;
	RETURN NEW;
END;
$check$ LANGUAGE PLPGSQL;

CREATE OR REPLACE TRIGGER ChairValidoInsert
BEFORE INSERT ON SESSIONE
FOR EACH ROW
EXECUTE PROCEDURE ChairValido();

CREATE OR REPLACE TRIGGER ChairValidoUpdate
BEFORE UPDATE OF Chair ON SESSIONE
FOR EACH ROW
EXECUTE PROCEDURE ChairValido();