package Model;

import java.sql.Time;

public class Evento_Sociale extends Seduta{
	private int codEvento; //Chiave primaria
	private String tipoEvento;
	private Time orarioInizioEvento;
	private Time orarioFineEvento;
	public Programma eventoSocialeInProgramma;//Chiave esterna
	//Costruttori
	public Evento_Sociale()
	{
		
	}
	public Evento_Sociale(Programma ref$Programma) {
		eventoSocialeInProgramma=ref$Programma;
		ref$Programma.eventiProgrammati.add(this);
	}
	//Metodi gets
	public int getcodEvento() {
		return codEvento;
	}
	public String getTipoEvento() {
		return tipoEvento;
	}
	public Time getOrarioInizioEvento() {

		return orarioInizioEvento;
	}
	public Time getOrarioFineEvento() {

		return orarioFineEvento;
	}

	
	//Metodi sets
	public void setCodEvento(int codEvento) {
		this.codEvento = codEvento;
	}
	
	public void setOrarioInizioEvento(Time orarioInizioEvento) {
		this.orarioInizioEvento = orarioInizioEvento;
	}
	public void setOrarioFineEvento(Time orarioFineEvento) {
		this.orarioFineEvento = orarioFineEvento;
	}

} 

