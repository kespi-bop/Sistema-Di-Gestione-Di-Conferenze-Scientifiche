----RIEPILOGOKS
----questa view ci permette di ottenere un riepilogo annuale sul
----numero di KeynoteSpeaker per ogni Istituzione di Afferenza che
----ha partecipato a organizzazione di conferenze in quell'anno.

CREATE VIEW RiepilogoKS AS
SELECT istituzione_di_afferenza, count(keynoteSpeaker) 
FROM PARTECIPANTE NATURAL JOIN(
	SELECT DISTINCT KeynoteSpeaker FROM SESSIONE NATURAL JOIN PROGRAMMA  --seleziono in modo distinto tutti i KS dell'anno
	WHERE extract (year from DataProgramma) = extract(year from CURRENT_DATE)) AS T
WHERE emailp = KeynoteSpeaker
GROUP BY istituzione_di_afferenza;

----Esempio di utilizzo della VIEW:

SELECT * FROM RiepilogoKS;

