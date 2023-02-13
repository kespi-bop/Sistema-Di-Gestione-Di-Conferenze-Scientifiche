package Model;

public class Organizzatore_Scientifico {
	private String emailS; //formato email
	private String DescrizioneCurriculum;
	private String Titolo;
	private String Nome;
	private String Cognome;
	private String Istituzione_di_Afferenza;
	
	//Costruttori
	
	
	//Metodi gets
	public String getemailS() {
		return emailS;
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
		emailS=input;
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
