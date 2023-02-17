--NoConfernzaParallela
--Non possono esserci più conferenze con la stessa data 
--nella stessa sede


CREATE OR REPLACE FUNCTION NoConferenzaParallela() RETURNS TRIGGER AS
$check$
DECLARE 
	TempConferenza CONFERENZA.CodConferenza%TYPE;
BEGIN
	SELECT CodConferenza FROM CONFERENZA INTO TempConferenza
	WHERE NomeSede = NEW.NomeSede AND							--controllo che la nuova conferenza inserita(o updatata) non abbia
		  ((NEW.DataInizio  BETWEEN DataInizio AND DataFine) OR	--la sede occupata in quella data	
		  (NEW.DataFine  BETWEEN DataInizio AND DataFine));
	
	IF(TempConferenza IS NOT NULL) THEN
		RAISE EXCEPTION 'Errore, la sede ospita già una conferenza';
	END IF;
	RETURN NEW;
END;
$check$ LANGUAGE PLPGSQL;		  


--data una nuova conferenza, bisogna controllare che questa
--possa essere ospitata in una sede libera(in inserimento)
CREATE OR REPLACE TRIGGER NoConferenzaParallelaInsert
BEFORE INSERT ON CONFERENZA
FOR EACH ROW
EXECUTE PROCEDURE NoConferenzaParallela();  

--in caso di cambio sede per una conferenza, devo controllare
--che la nuova sede sia libera(in update)
CREATE OR REPLACE TRIGGER NoConferenzaParallelaUpdate
BEFORE UPDATE ON CONFERENZA
FOR EACH ROW
EXECUTE PROCEDURE NoConferenzaParallela();