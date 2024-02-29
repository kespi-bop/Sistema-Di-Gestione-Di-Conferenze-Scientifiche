--ConflittoSessioni
--Non possono esserci più sessioni con
--gli stessi orari e con la stessa locazione
--programmate nella stessa data


CREATE OR REPLACE FUNCTION ConflittoSessioni() RETURNS TRIGGER AS
$check$
DECLARE 
	TempSessione SESSIONE.CodSessione%TYPE;
	DataSessione PROGRAMMA.DataProgramma%TYPE;		
BEGIN
	SELECT CodSessione FROM SESSIONE AS S
	INTO TempSessione
	WHERE S.NomeLocazione = NEW.NomeLocazione AND							--controllo che la locazione non sia già occupata a quella data e ora
		  CodProgramma = NEW.CodProgramma AND
		 ( ( (NEW.OrarioInizioSessione  >= S.OrarioInizioSessione) AND (NEW.OrarioInizioSessione  < S.OrarioFineSessione) ) OR
		  ( (NEW.OrarioFineSessione > S.OrarioInizioSessione) AND (NEW.OrarioFineSessione <= S.OrarioFineSessione) ) );
		  
	
	IF(TempSessione IS NOT NULL) THEN
		RAISE EXCEPTION 'Errore, in questa data e orario la locazione è occupata';
	END IF;
	RETURN NEW;
END;

$check$ LANGUAGE PLPGSQL;		


--data una nuova sessione, bisogna controllare che questa
--possa essere ospitata in una locazione libera(in inserimento)
CREATE OR REPLACE TRIGGER ConflittoSessioniInsert
BEFORE INSERT ON SESSIONE
FOR EACH ROW
EXECUTE PROCEDURE ConflittoSessioni();  

--in caso di cambio locazione per una sessione, devo controllare
--che la nuova locazione sia libera(in update)
CREATE OR REPLACE TRIGGER ConflittoSessioniUpdate
BEFORE UPDATE ON SESSIONE
FOR EACH ROW
EXECUTE PROCEDURE ConflittoSessioni();  
