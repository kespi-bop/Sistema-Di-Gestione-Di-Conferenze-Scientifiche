package Model;

public class Organizzatore_Locale extends Utente{
	private String emailL; //formato email
	private String titolo;
	private String nome;
	private String cognome;
	public Ente appartenenzaEnteOL;//Chiave esterna
	//Costruttori
	public Organizzatore_Locale() {
		
	}
	public Organizzatore_Locale(Ente ref$Ente) {
		appartenenzaEnteOL=ref$Ente;
		ref$Ente.listaOrganizzatoriLocaleEnte.add(this);
	}
	
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
	
	//Metodi sets
	@Override
	public void setEmail(String emailL) {
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
}
