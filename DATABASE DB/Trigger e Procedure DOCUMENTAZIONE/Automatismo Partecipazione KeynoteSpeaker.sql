--AUTOMATISMO PARTECIPAZIONE KEYNOTESPEAKER

--Per garantire che non esistano KS che non partecipano a una sessione di cui sono KS,
--alla creazione di una sessione, nel caso in cui sia stato inserito un KS,
--questo viene autmoaticamente impostato come partecipante della sessione appena creata.
--La partecipazione è quindi garantita dall'insert nella tabella ponte della coppia
--di Primary Key di Sessione(appena creata) e di KeynoteSpeaker(della sessione appena creata).
--Automatizzando questo inseritmento non è necessario quindi andare a lanciare alcuna eccezione
--che controlli la presenza del KS nella conferenza in cui interviene.

--Struttura del trigger:

CREATE OR REPLACE FUNCTION KSPartecipante() RETURNS TRIGGER AS
$check$
DECLARE 
	TempKS SESSIONE.CodSessione%TYPE;
BEGIN
	IF(NEW.KeynoteSpeaker IS NOT NULL) THEN				--controllo che sia stato realmente inserito un KS per la sessione
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