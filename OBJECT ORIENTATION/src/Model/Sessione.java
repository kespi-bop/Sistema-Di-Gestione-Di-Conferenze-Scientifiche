package Model;

public class Sessione extends Seduta{
	private String descrizioneSessione;
	public Programma sessioneInProgramma;//Chiave esterna
	public Partecipante KSpresente = null;//Chiave esterna
	public Locazione luogoLocazione;//Chiave esterna
	public Organizzatore_Scientifico chairSessione=null;//Chiave esterna
	
	//Costruttori
	public Sessione() {
	}
	
	public Sessione(Programma ref$Programma) {
		sessioneInProgramma=ref$Programma;
		ref$Programma.sessioniProgrammate.add(this);
	}

	public Sessione (Locazione ref$Locazione) {
		luogoLocazione=ref$Locazione;
		ref$Locazione.sessioniAccolte.add(this);
	}
	
	public Sessione(Partecipante ref$KS$Partecipante) {
		KSpresente = ref$KS$Partecipante;
		ref$KS$Partecipante.partecipantiKS.add(this);
	}
	public Sessione(Organizzatore_Scientifico ref$Organizzatore_Scientifico) {
		chairSessione = ref$Organizzatore_Scientifico;
		ref$Organizzatore_Scientifico.sessioniChair.add(this);
	}
	
	public Sessione(Locazione ref$Locazione, Partecipante ref$KS$Partecipante, Organizzatore_Scientifico ref$Organizzatore_Scientifico)
	{
		this.luogoLocazione = ref$Locazione;
		ref$Locazione.sessioniAccolte.add(this);
		
		this.KSpresente = ref$KS$Partecipante;
		ref$KS$Partecipante.partecipantiKS.add(this);
		
		this.chairSessione = ref$Organizzatore_Scientifico;
		ref$Organizzatore_Scientifico.sessioniChair.add(this);
	}
	
	//Metodi gets
	public String getDescrizioneSessione(){
		return descrizioneSessione;
	}
	
	public Organizzatore_Scientifico getChair() {
		return chairSessione;
	}
	
	public Partecipante getKeynoteSpeaker() {
		return KSpresente;
	}
	
	public Locazione getLocazione() {
		return luogoLocazione;
	}
	
	//Metodi sets
	public void setDescrizioneSessione(String descrizioneSessione) {
		this.descrizioneSessione=descrizioneSessione;
	}

}
