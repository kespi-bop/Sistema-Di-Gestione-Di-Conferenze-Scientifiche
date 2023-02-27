package DAO;

import java.util.ArrayList;
import java.util.Date;

import Model.Conferenza;
import Model.Ente;
import Model.Evento_Sociale;
import Model.Intervallo;
import Model.Organizzatore_Scientifico;
import Model.Programma;
import Model.Pubblicità;
import Model.Sessione;

public interface ConferenzaDAO {
	
	//restituisce tutte le conferenze con i relativi programmi filtrati per Data e Sede
	void getConferenzeAndProgrammiDB(ArrayList<Integer> listaCodici, ArrayList<String> listaTitoli, ArrayList<String> listaDate, 
									 ArrayList<String> listaSedi, String dataInizio, String dataFine, String sede);

	//effettua la creazione della conferenza
	void commitCreateDB(Conferenza conferenzaCreata, ArrayList<Programma> listaProgrammi, ArrayList<Pubblicità> listaPubblicità);

	//restituisce la conferenza che da conflitto
	String getConflictConferenzaDB(Date dataInizio, Date dataFine, String nomeSede);

	//restituisce tutte le conferenze del DB
	ArrayList<Conferenza> getConferenzeDB();

	//cancella la conferenza passata come parametro da DB
	void removeConferenzaDB(Conferenza conferenza);

	/*ritorna per parametro l'array del numero di KS per ogni Conferenza
	 * e per riferimento l'array delle istituzioni*/
	ArrayList<Integer> getRiepilogoKSDB(ArrayList<Ente> istituzioni, String mese, String anno);

	//vado a settare in conferenza i vari programmi
	void getProgrammiDB(Conferenza conferenza);
	
	//aggiunge il programma alla conferenza che sto modificando e restituisce il codiceProgrammaNuovo
	Integer commitAddProgramma(String dataProgramma, Conferenza updateConferenza, ArrayList<Intervallo> listaIntervalli,
				ArrayList<Sessione> listaSessioni, ArrayList<Evento_Sociale> listaEventi);

	//cancella il programma selezionato
	void commitDeleteProgramDB(String codProgramma);

	//modifica i valori di titolo e/o descrizione della conferenza
	void commitUpdateDB(String titolo, String descrizione, Conferenza updateConferenza);

	//estrae tutti gli anni in cui si sono svolte conferenze
	ArrayList<String> getAnniConferenze();
}
