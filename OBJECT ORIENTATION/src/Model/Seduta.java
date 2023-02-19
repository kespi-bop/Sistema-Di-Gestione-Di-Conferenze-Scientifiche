package Model;

import java.sql.Time;

public class Seduta {
	private Integer codSeduta;
	private Time orarioInizio;
	private Time orarioFine;
	private String titolo;
	public Programma sedutaInProgramma;//Chiave esterna
	public Partecipante KSpresente = null;//Chiave esterna 
	
	//Costruttori
	
	public Seduta() {
	}
	
	//Metodi gets
	public Time getOrarioInizio(){
		return orarioInizio;
	}
	
	public Time getOrarioFine(){
		return orarioFine;
	}
	
	public String getTitolo(){
		return titolo;
	}
	
	public Integer getCodSeduta() {
		return codSeduta;
	}

	//Metodi sets
	public void setOrarioInizio(Time orarioInizio) {
	this.orarioInizio = orarioInizio;
	}
	
	public void setOrarioFine(Time orarioFine) {
		this.orarioFine = orarioFine;
	}
	
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public void setCodSeduta(Integer codSeduta) {
		this.codSeduta = codSeduta;
	}
}
