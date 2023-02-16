package Model;

import java.util.ArrayList;

public class Partecipante {
	private String emailP; //formato email
	private String titolo;
	private String nome;
	private String cognome;
	private String istituzione_di_Afferenza;
	
	//Costruttori
	ArrayList<Intervento> interventiPartecipante = new ArrayList<Intervento>();
	ArrayList<Sessione> partecipantiKS = new ArrayList<Sessione>();
	public Partecipante() {
	}
	//Trasposizione delle entità intervento-partecipante (1..*,1)
	public Partecipante(Intervento ref$Intervento) {
		interventiPartecipante.add(ref$Intervento);
		ref$Intervento.partecipanteIntervenuto=this;
	}
	//Trasposizione delle entità sessione-partecipante (1..*,0..1)
	public Partecipante(Sessione ref$Sessione$KS) {
		partecipantiKS.add(ref$Sessione$KS);
		ref$Sessione$KS.
	}
	//Metodi gets
	public String getemailP() {
		return emailP;
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
	public String getIstituzione_Di_Afferenza() {
		return istituzione_di_Afferenza;
	}
	
	//Metodi sets
	
	public void setemailL(String emailP) {
		this.emailP = emailP;
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
	public void setIstituzione_Di_Afferenza(String istituzione_di_Afferenza) {
		this.istituzione_di_Afferenza = istituzione_di_Afferenza;
	}
}
