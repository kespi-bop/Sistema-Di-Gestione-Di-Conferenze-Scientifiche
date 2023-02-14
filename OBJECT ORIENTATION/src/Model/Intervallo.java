package Model;

import java.sql.Time;

public class Intervallo {
	private int codIntervallo= 0;
	private String tipoIntervallo;
	private Time orarioInizioIntervallo;
	private Time orarioFineIntervallo;
	private int codProgramma;
	
	//Costruttori
	
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
	public int getCodProgramma() {
		return codProgramma;
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
	public void CodProgramma (int codProgramma) {
		this.codProgramma = codProgramma;
	}

}
