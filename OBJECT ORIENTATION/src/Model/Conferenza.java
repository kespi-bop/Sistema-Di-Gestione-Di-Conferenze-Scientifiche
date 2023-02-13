package Model;

import java.util.Date;
import java.util.ArrayList;

public class Conferenza {
	private String TitoloConferenza;
	private Date DataInizio;
	private Date DataFine;
	private String Descrizione;
	private int CodConferenza; //Il primo codice conferenza deve essere 1
	private String NomeSede; //chiave esterna
	
	//Costruttori
	
	//Metodi gets
	public String getTitoloConferenza() {
		return TitoloConferenza;
	}
	public String getDescrizione() {
		return Descrizione;
	}
	public Date getDataInizio() {
		return DataInizio;
	}
	public Date getDataFine() {
		return DataFine;
	}
	public String getNomeSede(String input) {
		return NomeSede;
	}
	
	//Metodi sets
	public void setTitoloCoferenza(String input) {
		TitoloConferenza =input;
	}
	public void setCodConferenza (int input) {
		CodConferenza=input;
	}
	public void setDataInizio (Date input) {
		DataInizio=input;
	}
	public void setDataFine (Date input) {
		DataFine=input;
	}
	public void setDescrizione (String input) {
		Descrizione=input;
	}
	public void setNomeSede(String input) {
		NomeSede=input;
	}
}
