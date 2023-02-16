package Model;

import java.sql.Time;

public class Intervento {
	private int codIntervento = 0;
	private Time orarioInizioIntervento;
	private Time orarioFineIntervento;
	private String aabstract;
	public Sessione interventoInSessione;//Chiave esterna
	public Partecipante partecipanteIntervenuto;//Chiave esterna
	//Costruttori
	public Intervento() {
		
	}
	
	public Intervento(Sessione ref$Sessione) {
		interventoInSessione=ref$Sessione;
		ref$Sessione.interventiSessione.add(this);
	}
	
	public Intervento(Partecipante ref$Partecipante) {
		partecipanteIntervenuto=ref$Partecipante;
		ref$Partecipante.interventiPartecipante.add(this);
		
	}
	
	//Metodi gets
	public int getCodIntervento()
	{
		return codIntervento;
	}
	public Time getOrarioInizioIntervento()
	{
		return orarioInizioIntervento;
	}
	public Time OrarioFineIntervento()
	{
		return orarioFineIntervento;
	}
	public String getAabstract()
	{
		return aabstract;
	}
	//Metodi sets
	
	public void setCodIntervento(int codIntervento) {
		this.codIntervento = codIntervento;
	}
	public void setOrarioInizioIntervento(Time orarioInizioIntervento) {
		this.orarioInizioIntervento = orarioInizioIntervento;
	}
	public void setOrarioFineIntervento(Time orarioFineIntervento) {
		this.orarioFineIntervento = orarioFineIntervento;
	}
	public void setAbstract(String aabstract) {
		this.aabstract = aabstract;
	}
}
