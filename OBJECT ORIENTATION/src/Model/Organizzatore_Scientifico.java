package Model;

import java.util.ArrayList;

public class Organizzatore_Scientifico extends Utente{
	private String descrizioneCurriculum;
	private String titolo;
	private String nome;
	private String cognome;
	public Ente appartenenzaEnteOS;//Chiave esterna
	public Conferenza organizzatoreSConferenza;
	//Costruttori
	public ArrayList<Sessione> sessioniChair= new ArrayList<Sessione>();
	public ArrayList<Conferenza> organizzatoreSConferenze =  new ArrayList<Conferenza>();
	
	public Organizzatore_Scientifico() {
		
	}

	
	//Metodi gets
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
