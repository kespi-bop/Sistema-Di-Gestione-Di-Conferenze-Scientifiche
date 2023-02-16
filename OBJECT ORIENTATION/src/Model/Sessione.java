package Model;

import java.sql.Time;
import java.util.ArrayList;

public class Sessione {
	private int codSessione;
	private Time orarioInizioSessione;
	private Time orarioFineSessione;
	private String titoloSessione;
	private String descrizioneSessione;
	public Programma sessioneInProgramma;//Chiave esterna
	public Locazione luogoLocazione;//Chiave esterna
	public Partecipante KSpresente=null;//Chiave esterna 
	public Organizzatore_Scientifico chairSessione=null;//Chiave esterna
	//Costruttori
	public ArrayList<Intervento> interventiSessione= new ArrayList<Intervento>();
	public Sessione() {
	}
	public Sessione(Programma ref$Programma) {
		sessioneInProgramma=ref$Programma;
		ref$Programma.sessioniProgrammate.add(this);
	}
	public Sessione(Intervento ref$Intervento) {
		interventiSessione.add(ref$Intervento);
		ref$Intervento.interventoInSessione=this;
	}
	
	public Sessione (Locazione ref$Locazione) {
		luogoLocazione=ref$Locazione;
		ref$Locazione.sessioniAccolte.add(this);
	}
	
	public Sessione(Partecipante ref$KS$Partecipante) {
		KSpresente=ref$KS$Partecipante;
		ref$KS$Partecipante.partecipantiKS.add(this);
	}
	public Sessione(Organizzatore_Scientifico ref$Organizzatore_Scientifico) {
		chairSessione.sessioniChair.add(this);
	}
	//Metodi gets
	public int getCodSessione() {
		return codSessione;
	}
	public Time getOrarioInizioSessione(){
		return orarioInizioSessione;
	}
	public Time getOrarioFineSessione(){
		return orarioFineSessione;
	}
	public String getTitoloSessione(){
		return titoloSessione;
	}
	public String getDescrizioneSessione(){
		return descrizioneSessione;
	}
	
	//Metodi sets
	public void setCodSessione(int codSessione) {
		this.codSessione=codSessione;
	}
	public void setOrarioInizioSessione(Time orarioInizioSessione) {
	this.orarioInizioSessione=orarioInizioSessione;
	}
	public void setOrarioFineSessione(Time orarioFineSessione) {
		this.orarioFineSessione=orarioFineSessione;
	}
	public void setTitoloSessione(String titoloSessione) {
		this.titoloSessione=titoloSessione;
	}
	public void setDescrizioneSessione(String descrizioneSessione) {
		this.descrizioneSessione=descrizioneSessione;
	}

}
