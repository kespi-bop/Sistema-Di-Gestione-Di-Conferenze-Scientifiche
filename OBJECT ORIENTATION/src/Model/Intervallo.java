package Model;

import java.sql.Time;

public class Intervallo extends Seduta{
	private int codIntervallo= 0;
	private String tipoIntervallo;
	private Time orarioInizioIntervallo;
	private Time orarioFineIntervallo;
	public Programma intervalloInProgramma; //Chiave esterna
	//Costruttori
	public Intervallo(){
	}
	
	public Intervallo(Programma p) {
		intervalloInProgramma=p;
		p.intervalliProgrammati.add(this);
	}
	//Metodi gets
	public int getCodIntervallo() {
		return codIntervallo;
	}
	public String getTipoIntervallo() {
		return tipoIntervallo;
	}
	public Time getOrarioInizioIntervallo() {
		return orarioInizioIntervallo;
	}
	public Time getOrarioFineIntervallo() {
		return orarioFineIntervallo;
	}
	
	
	//Metodi sets
	public void setCodIntervallo(int codIntervallo) {
		this.codIntervallo = codIntervallo;
	}
	public void setTipoIntervallo(String tipoIntervallo) {
		this.tipoIntervallo = tipoIntervallo;
	}
	public void setOrarioInizioIntervallo(Time orarioInizioIntervallo) {
		this.orarioInizioIntervallo = orarioInizioIntervallo;
	}
	public void setOrarioFineIntervallo(Time orarioFineIntervallo) {
		this.orarioFineIntervallo = orarioFineIntervallo;
	}

}
