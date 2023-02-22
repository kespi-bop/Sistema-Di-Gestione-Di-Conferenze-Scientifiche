package Model;

import java.util.ArrayList;

public class Ente {
	private String nomeIstituzione;
	public ArrayList<Partecipante> partecipantiIscritti = new ArrayList<Partecipante>();
	
	public ArrayList<Organizzatore_Locale> listaOrganizzatoriLocaleEnte = new ArrayList<Organizzatore_Locale>();
	
	public ArrayList<Organizzatore_Scientifico> listaOrganizzatoriScientificoEnte = new ArrayList<Organizzatore_Scientifico>();
	
	public ArrayList<Conferenza> listaConferenzeAmministrate = new ArrayList<Conferenza>();
	
	public ArrayList<Conferenza> conferenzeAmministrate = new ArrayList<Conferenza>();
	//Costruttori
	public Ente() {
		
	}

	//Trasposizione Partecipante-Ente (0..*,1)
	public Ente(Partecipante ref$Partecipante) {
		partecipantiIscritti.add(ref$Partecipante);
		ref$Partecipante.entePartecipante=this;
	}
	//Trasposizione Organizzatore_Locale-Ente (1..*,1)
	public Ente(Organizzatore_Locale ref$Organizzatore_Locale) {
		listaOrganizzatoriLocaleEnte.add(ref$Organizzatore_Locale);
		ref$Organizzatore_Locale.appartenenzaEnteOL=this;
	}
	//Trasposizione Organizzatore_Scientifico-Ente (1..*,1)
	public Ente(Organizzatore_Scientifico ref$Organizzatore_Scientifico) {
		listaOrganizzatoriScientificoEnte.add(ref$Organizzatore_Scientifico);
		ref$Organizzatore_Scientifico.appartenenzaEnteOS=this;
	}
	//Metodi gets
	public String getNomeIstituazione() {
		return nomeIstituzione;
	}
	//Metodi sets
	public void setNomeIstituzione(String nomeIstituzione) {
		this.nomeIstituzione = nomeIstituzione;
	}
}
