package Model;

public class Organizzatore_Locale {
	private String emailL; //formato email
	private String Titolo;
	private String Nome;
	private String Cognome;
	private String Istituzione_di_Afferenza;
	
	//Costruttori
	
	
	//Metodi gets
	public String getemailL() {
		return emailL;
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
	public String getIstituzione_di_Afferenza() {
		return Istituzione_di_Afferenza;
	}
	
	//Metodi sets
	
	public void setemailL(String input) {
		emailL=input;
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
	public void setIstituzione_di_Afferenza(String input) {
		Istituzione_di_Afferenza=input;
	}
}
