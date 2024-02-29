package DAO;

import java.util.ArrayList;

public interface SessioneDAO {
	
	//restituisce la descrizione di una sessione dato il suo codice
	String getDescrizioneDB(String codSessione);
	
	//restituisce tutti i partecipanti che possono essere KeynoteSpeaker
	ArrayList<String> getKeynoteDB();
}
