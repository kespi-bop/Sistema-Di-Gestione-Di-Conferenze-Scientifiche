package Model;

import java.sql.Time;

public class Intervallo {
	private int CodIntervallo= 0;
	private String TipoIntervallo;
	private Time OrarioInizioIntervallo;
	private Time OrarioFineIntervallo;
	private int CodProgramma;
	
	//Costruttori
	
	//Metodi gets
	public int getCodIntervallo() {
		return CodIntervallo;
	}
	public String getTipoIntervallo() {
		return TipoIntervallo;
	}
	public Time getOrarioInizioIntervallo() {
		return OrarioInizioIntervallo;
	}
	public Time getOrarioFineIntervallo() {
		return OrarioFineIntervallo;
	}
	public int getCodProgramma() {
		return CodProgramma;
	}
	
	//Metodi sets
	public void setCodIntervallo(int input) {
		CodIntervallo = input;
	}
	public void setTipoIntervallo(String input) {
		TipoIntervallo = input;
	}
	public void setOrarioInizioIntervallo(Time input) {
		OrarioInizioIntervallo = input;
	}
	public void setOrarioFineIntervallo(Time input) {
		OrarioFineIntervallo = input;
	}
	public void CodProgramma (int input) {
		CodProgramma = input;
	}

}
