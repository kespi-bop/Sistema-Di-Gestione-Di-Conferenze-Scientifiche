# TRACCIA 3: SISTEMA DI GESTIONE DI CONFERENZE SCIENTIFICHE 

*"Si sviluppi un sistema informativo, composto da una base di dati relazionale e da un applicativo Java dotato 
di GUI (Swing o JavaFX), per la gestione di conferenze scientifiche. Ogni conferenza ha una data di inizio e di 
fine, una collocazione (sede, indirizzo), uno o più enti che la organizzano, degli sponsor (che coprono in parte 
le spese), una descrizione, ed un gruppo di organizzatori, che può essere distinto in comitato scientifico e 
comitato locale (che si occupa cioè della logistica). Di ognuno degli organizzatori, così come di tutti i 
partecipanti si riportano titolo, nome, cognome, email ed istituzione di afferenza. Ogni conferenza può avere 
una o più sessioni, anche in parallelo fra loro. Ogni sessione ha una locazione all'interno della sede. Per ogni 
sessione c'è un programma, che prevede la presenza di un coordinatore (chair) che gestisce la sessione, ed 
eventualmente di un keynote speaker (un partecipante di particolare rilievo invitato dagli organizzatori). Ogni 
sessione avrà quindi una successione di interventi, ad orari predefiniti e di specifici partecipanti. Per ogni 
intervento si conserva un abstract (un breve testo in cui viene spiegato il contenuto del lavoro presentato). Si deve poter considerare la presenza di spazi di intervallo (coffee breaks, pranzo) ma anche la presenza di 
eventi sociali (cene, gite, etc)."*

## *📂Files di progetto:*
- File Documentazione-BasiDiDati-OOBD_6.pdf prodotto in LATEX contenente una descrizione dettagliata di tutto il progetto.
- File "SQL CREAZIONE E VINCOLI.sql" contenente i codici di creazione del DB e per la realizzazione fisica dei vincoli.
- File "SQL POPOLAMENTO.sql" conentente i codici sql per il popolamento del db.
- readme.md(questo file).

## ⚒ *Realizzazione:*
- 🗀  Documentazione realizzata in LATEX con Texmaker.
- 🗀  DataBase realizzato con PostgreSQL.
- 🗀  Diagramma UML realizzato con StarUML.
- 🗀  Diagramma ER realizzato con https://app.diagrams.net/ .
- 🗀  Readme realizzato in Markdown con https://readme.so/it .


## 💻 *Funzionamento:*
Per la creazione del DataBase è necessario eseguire il file "SQL CREAZIONE E VINCOLI" tramite PostgreSQL.  
Nel file.sql sono stati inseriti gli appositi commenti sul funzionamento di trigger e procedure, è possibile però andare a visualizzare in modo più dettagliato la funzione di quelli più importanti all'interno della documentazione(vedi paragrafo 4.3).  
Sono state intraprese diverse scelte nella realizzazione del file.sql per fini esclusivamente scolastici.  
I dati delle insert nella popolazione sono dati casuali o dati estratti dalle seguenti fonti:  
https://www.ieee-csr.org/conference-program-overview/ e https://www.iciap2021.org/eventagenda/ .
