package DAO;

public interface UtenteDAO {
	
	//ritorna il nome dell'utente ricordato dal DB
	String ottieniUtenteRicordatoDB();
	
	/*	se le credenziali sono corrette e se è spuntata
	la checkbox, allora queste vengono inserite nel DB  */
	void ricordaPasswordDB(String email);
	
	/*	se viene eseguito un logout, vengono cancellate le credenziali
	 	ricordate dal DB   */
	void eliminaPasswordDB();
	
	/*	ritorna il nome dell'utente che accede e se non viene
	  	trovata alcuna corrispondenza ritorna una stringa vuota	  */
	String getAccessDB(String email, String password);
}
