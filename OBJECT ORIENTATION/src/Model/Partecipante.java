package Model;

public class Partecipante {
	private String emailP; //formato email
	private String Titolo;
	private String Nome;
	private String Cognome;
	private String Istituzione_di_Afferenza;
	
	//Costruttori
	
	
	//Metodi gets
	public String getemailP() {
		return emailP;
	}
	public String getTitolo() {
		return Titolo;
	}
	public String getNome() {
		return Nome;
	}
	public String getCognome() {
		return Cognome;
	}
	public String getIstituzione_Di_Afferenza() {
		return Istituzione_di_Afferenza;
	}
	
	//Metodi sets
	
	public void setemailL(String input) {
		emailP=input;
	}
	public void setTitolo(String input) {
		Titolo=input;
	}
	public void setNome(String input) {
		Nome=input;
	}
	public void setCognome(String input) {
		Cognome=input;
	}
	public void setIstituzione_Di_Afferenza(String input) {
		Istituzione_di_Afferenza=input;
	}
}
