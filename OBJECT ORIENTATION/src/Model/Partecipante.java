package Model;

import java.util.ArrayList;

public class Partecipante {
	private String emailP; //formato email
	private String titolo;
	private String nome;
	private String cognome;
	public Ente entePartecipante;//Chiave esterna
	
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
		ref$Sessione$KS.KSpresente=this;
	}
	public Partecipante(Ente ref$Ente) {
		entePartecipante=ref$Ente;
		ref$Ente.partecipantiIscritti.add(this);
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
}
