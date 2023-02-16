package Model;

import java.util.ArrayList;
import java.util.Date;

public class Conferenza {
	private String titoloConferenza;
	private Date dataInizio;
	private Date dataFine;
	private String descrizione;
	private int codConferenza;
	public Sede ospitaConferenza;//Chiave esterna
	
	public ArrayList<Programma> programmiConferenza = new ArrayList<Programma>();
	//Costruttori
	public Conferenza(Programma ref$Programma) {
		programmiConferenza.add(ref$Programma);
		ref$Programma.programmaConferenza=this;
	}
	
	//Trasposizione delle entità conferenza-sede
	public Conferenza(Sede ref$Sede) {
		ospitaConferenza=ref$Sede;
		ref$Sede.sediConferenza.add(this);
	}
	public Conferenza() {
	}
	//Metodi gets
	public String getTitoloConferenza() {
		return titoloConferenza;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public Date getDataInizio() {
		return dataInizio;
	}
	public Date getDataFine() {
		return dataFine;
	}
	public int getCodConferenza() {
		return codConferenza;
	}
	
	//Metodi sets
	public void setTitoloCoferenza(String titoloConferenza) {
		this.titoloConferenza = titoloConferenza;
	}
	public void setCodConferenza (int codConferenza) {
		this.codConferenza= codConferenza;
	}
	public void setDataInizio (Date dataInizio) {
		this.dataInizio= dataInizio;
	}
	public void setDataFine (Date dataFine) {
		this.dataFine= dataFine;
	}
	public void setDescrizione (String descrizione) {
		this.descrizione= descrizione;
	}
	
}
