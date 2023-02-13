package Model;

import java.util.Date;

public class Programma {
	private int CodProgramma;
	private Date DataProgramma;
	private int CodConferenza;
	
	//Costruttori
	
	//Metodi gets
	public int getCodProgramma() {
		return CodProgramma;
	}
	public Date DataProgramma() {
		return DataProgramma;
	}
	public int getCodConferenza() {
		return CodConferenza;
	}
	
	//Metodi sets
	public void setCodProgramma(int input) {
		CodProgramma = input;
	}
	public void setDataProgramma(Date input) {
		DataProgramma = input;
	}
	public void setCodConferenza(int input) {
		CodConferenza = input;
	}
}
