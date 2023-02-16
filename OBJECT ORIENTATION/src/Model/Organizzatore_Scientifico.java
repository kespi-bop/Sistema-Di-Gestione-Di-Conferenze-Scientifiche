package Model;

import java.util.ArrayList;

public class Organizzatore_Scientifico {
	private String emailS; //formato email
	private String descrizioneCurriculum;
	private String titolo;
	private String nome;
	private String cognome;
	public Ente appartenenzaEnteOS;//Chiave esterna
	
	//Costruttori
	public ArrayList<Sessione> sessioniChair= new ArrayList<Sessione>();
	public Organizzatore_Scientifico() {
		
	}
	public Organizzatore_Scientifico(Ente ref$Ente) {
		appartenenzaEnteOS=ref$Ente;
		ref$Ente.listaOrganizzatoriScientificoEnte.add(this);
	}
	//Trasposizione sessione-Organizzatore_Scientifico(0..*,1)
	public Organizzatore_Scientifico(Sessione ref$Sessione) {
		sessioniChair.add(ref$Sessione);
		ref$Sessione.chairSessione=this;
	}
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
	public String getDescrizioneCurriculum() {
		return descrizioneCurriculum;
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
	public void setDescrizioneCurriculum(String descrizioneCurriculum) {
		this.descrizioneCurriculum=descrizioneCurriculum;
	}
}
