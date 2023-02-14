package Model;

public class Organizzatore_Scientifico {
	private String emailS; //formato email
	private String sescrizioneCurriculum;
	private String titolo;
	private String nome;
	private String cognome;
	private String istituzione_di_Afferenza;
	
	//Costruttori
	
	
	//Metodi gets
	public String getemailS() {
		return emailS;
	}
	public String getTitolo() {
		return titolo;
	}
	public String getNome() {
		return nome;
	}
	public String getCognome() {
		return cognome;
	}
	public String getIstituzione_di_Afferenza() {
		return istituzione_di_Afferenza;
	}
	
	//Metodi sets
	
	public void setemailL(String emailS) {
		this.emailS = emailS;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public void setIstituzione_di_Afferenza(String istituzione_di_Afferenza) {
		this.istituzione_di_Afferenza = istituzione_di_Afferenza;
	}
}
