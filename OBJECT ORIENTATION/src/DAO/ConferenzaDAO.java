package DAO;

import java.util.ArrayList;

public interface ConferenzaDAO {
	//restituisce tutte le conferenze con i relativi programmi filtrati per Data e Sede
	void getConferenzeAndProgrammiDB(ArrayList<Integer> listaCodici, ArrayList<String> listaTitoli, ArrayList<String> listaDate, 
									 ArrayList<String> listaSedi, String data, String sede);
}
