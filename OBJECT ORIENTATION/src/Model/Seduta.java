package Model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Seduta {
	private Integer codSeduta;
	private Date orarioInizio;
	private Date orarioFine;
	SimpleDateFormat tipoTempo = new SimpleDateFormat("HH:mm");
	private String titolo;
	public Programma sedutaInProgramma;//Chiave esterna
	public Partecipante KSpresente = null;//Chiave esterna 
	public Locazione locazione = null;
	
	//Costruttori
	
	public Seduta() {
	}
	
	//Metodi gets
	public Date getOrarioInizio(){
		return orarioInizio;
	}
	
	public Date getOrarioFine(){
		return orarioFine;
	}
	
	public String getTitolo(){
		return titolo;
	}
	
	public Integer getCodSeduta() {
		return codSeduta;
	}
	
	public Locazione getLocazione()
	{
		return locazione;
	}

	//Metodi sets
	public void setOrarioInizio(Date orarioInizio) {
	this.orarioInizio = orarioInizio;
	}
	
	public void setOrarioFine(Date orarioFine) {
		this.orarioFine = orarioFine;
	}
	
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public void setCodSeduta(Integer codSeduta) {
		this.codSeduta = codSeduta;
	}
	
	
}
