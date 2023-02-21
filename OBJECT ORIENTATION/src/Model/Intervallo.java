package Model;

import java.sql.Time;

public class Intervallo extends Seduta{
	private int codIntervallo= 0;
	private String tipoIntervallo;
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

	
	
	//Metodi sets
	public void setCodIntervallo(int codIntervallo) {
		this.codIntervallo = codIntervallo;
	}
	
}
