package Model;

import java.sql.Time;

public class Evento_Sociale {
	private int CodEvento; //Chiave primaria
	private String TipoEvento;
	private Time OrarioInizioEvento;
	private Time OrarioFineEvento;
	private int CodProgramma; //Chiave secondaria (cascade)
	//Costruttori
	
	//Metodi gets
	public int getcodEvento() {
		return CodEvento;
	}
	public String getTipoEvento() {
		return TipoEvento;
	}
	public Time getOrarioInizioEvento() {

		return OrarioInizioEvento;
	}
	public Time getOrarioFineEvento() {

		return OrarioFineEvento;
	}
	public int getCodProgramma() {

		return CodProgramma;
	}
	
	//Metodi sets
	public void setCodEvento(int input) {
		CodEvento = input;
	}
	public void setTipoEvento(String input) {
		TipoEvento = input;
	}
	public void setOrarioInizioEvento(Time input) {
		OrarioInizioEvento=input;
	}
	public void setOrarioFineEvento(Time input) {
		OrarioFineEvento=input;
	}
	public void setCodProgramma(int input) {
		CodProgramma=input;
	}
} 

