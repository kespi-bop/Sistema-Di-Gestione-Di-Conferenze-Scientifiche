package Model;

public class Organizzatore_Locale {
	private String emailL; //formato email
	private String titolo;
	private String nome;
	private String cognome;
	private String istituzione_di_Afferenza;
	
	//Costruttori
	
	
	//Metodi gets
	public String getemailL() {
		return emailL;
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
	
	public void setemailL(String emailL) {
		this.emailL = emailL;
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
