--il keynote speaker di una sessione deve essere necessariamente
--partecipante di quella sessione

  
--appena creo la mia sessione, se ho inserito un keynote speaker
--allora aggiungo questo ai partecipanti di quella sessione
CREATE OR REPLACE FUNCTION KSPartecipante() RETURNS TRIGGER AS
$check$
DECLARE 
	TempKS SESSIONE.CodSessione%TYPE;
BEGIN
	IF(NEW.KeynoteSpeaker IS NOT NULL) THEN				--controllo se è stato realmente inserito un KS per la sessione
		SELECT CodSessione FROM PARTECIPARE AS P INTO TempKS		--controllo che nei partecipanti non ci sia già 
		WHERE (P.CodSessione = NEW.CodSessione AND				--il KS della sessione
			   P.emailP = NEW.KeynoteSpeaker);
		
		IF(TempKS IS NULL) THEN								--se il KS non è presente tra i partecipanti,
				INSERT INTO PARTECIPARE (CodSessione, emailP) 	 --allora lo inserisco
								VALUES(NEW.CodSessione,New.KeynoteSpeaker); 
		END IF;
	END IF;
	RETURN NEW;
END;
$check$ LANGUAGE PLPGSQL;


CREATE OR REPLACE TRIGGER KSPartecipanteInsert
AFTER INSERT ON SESSIONE
FOR EACH ROW
EXECUTE PROCEDURE KSPartecipante();

CREATE OR REPLACE TRIGGER KSPartecipanteUpdate
AFTER UPDATE OF KeynoteSpeaker ON SESSIONE
FOR EACH ROW
EXECUTE PROCEDURE KSPartecipante();  