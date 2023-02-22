package DAO;

import java.util.ArrayList;
import java.util.Date;

import Model.Conferenza;
import Model.Programma;
import Model.Pubblicità;

public interface ConferenzaDAO {
	//restituisce tutte le conferenze con i relativi programmi filtrati per Data e Sede
	void getConferenzeAndProgrammiDB(ArrayList<Integer> listaCodici, ArrayList<String> listaTitoli, ArrayList<String> listaDate, 
									 ArrayList<String> listaSedi, String data, String sede);

	void commitCreateDB(Conferenza conferenzaCreata, ArrayList<Programma> listaProgrammi, ArrayList<Pubblicità> listaPubblicità);

	//restituisce la conferenza che da conflitto
	String getConflictConferenza(Date dataInizio, Date dataFine, String nomeSede);
}
