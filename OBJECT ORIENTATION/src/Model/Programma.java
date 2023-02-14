package Model;

import java.util.Date;

public class Programma {
	private int codProgramma;
	private Date dataProgramma;
	private int codConferenza;
	
	//Costruttori
	
	//Metodi gets
	public int getCodProgramma() {
		return codProgramma;
	}
	public Date DataProgramma() {
		return dataProgramma;
	}
	public int getCodConferenza() {
		return codConferenza;
	}
	
	//Metodi sets
	public void setCodProgramma(int codProgramma) {
		this.codProgramma = codProgramma;
	}
	public void setDataProgramma(Date dataProgramma) {
		this.dataProgramma = dataProgramma;
	}
	public void setCodConferenza(int codConferenza) {
		this.codConferenza = codConferenza;
	}
}
