package DAO;

import java.util.ArrayList;

public interface Organizzare_S_DAO {

	//restituisce una lista di tutte le email degli organizzatori_Scientifici di quella conferenza
	ArrayList<String> getAllPossibleChair(int codConferenza);
}
