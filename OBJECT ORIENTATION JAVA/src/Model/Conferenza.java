package Model;

import java.util.ArrayList;
import java.util.Date;

public class Conferenza {
	private String descrizione;
	private String titoloConferenza;
	private int codConferenza;
	public Sede sedeOspitante;//Chiave esterna
	private Date dataInizio;
	private Date dataFine;
	private ArrayList<Organizzatore_Locale> organizzatori_Locali;
	private ArrayList<Organizzatore_Scientifico> organizzatori_Scientifici;
	public ArrayList<Programma> programmiConferenza = new ArrayList<Programma>();
	//Costruttori
	public Conferenza(Sede ref$Sede) {
		sedeOspitante=ref$Sede;
		ref$Sede.sediConferenza.add(this);
	}

	public Conferenza() {
	}

	//Metodi gets
	public String getDescrizione() {
		return descrizione;
	}
	
	public int getCodConferenza() {
		return codConferenza;
	}
	
	public Date getDataInizio() {
		return dataInizio;
	}
	
	public Date getDataFine() {
		return dataFine;
	}
	
	public String getTitoloConferenza() {
		return titoloConferenza;
	}
	
	public ArrayList<Organizzatore_Locale> getOrganizzatoriLocali(){
		return organizzatori_Locali;
	}

	public ArrayList<Organizzatore_Scientifico> getOrganizzatoriScientifici(){
		return organizzatori_Scientifici;
	}

	
	//Metodi sets
	public void setCodConferenza (int codConferenza) {
		this.codConferenza= codConferenza;
	}
	
	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public void setDescrizione (String descrizione) {
		this.descrizione= descrizione;
	}

	public void setOrganizzatoriLocali (ArrayList<Organizzatore_Locale> organizzatori_Locali) {
		this.organizzatori_Locali = organizzatori_Locali;
	}

	public void setOrganizzatoriScientifici (ArrayList<Organizzatore_Scientifico> organizzatori_Scientifici) {
		this.organizzatori_Scientifici = organizzatori_Scientifici;
	}

	public void setTitoloConferenza(String titoloConferenza) {
		this.titoloConferenza = titoloConferenza;
	}
	

}
	

