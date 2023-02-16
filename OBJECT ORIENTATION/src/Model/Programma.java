package Model;

import java.util.ArrayList;
import java.util.Date;

public class Programma {
	private int codProgramma;
	private Date dataProgramma;
	private int codConferenza;
	
	//Costruttori
	public Programma() {
	}
	public ArrayList<Sessione> sessioniProgrammate = new ArrayList<Sessione>();
	
	public ArrayList<Intervallo> intervalliProgrammati = new ArrayList<Intervallo>();
	
	public ArrayList<Evento_Sociale> eventiProgrammati = new ArrayList<Evento_Sociale>();
	//Trasposizione sessione-programma (1..*,1)
	public Programma(Sessione ref$Sessione) {
		sessioniProgrammate.add(ref$Sessione);
		ref$Sessione.sessioneInProgramma=this;
	}
	
	//Trasposizione intervallo-programma (1..*,1)
	public Programma(Intervallo ref$Intervallo) {
		intervalliProgrammati.add(ref$Intervallo);
		ref$Intervallo.intervalloInProgramma=this;
		
	}
	//Trasposizione evento sociale-programma (1..*,1)
	public Programma(Evento_Sociale ref$EventoSociale) {
		eventiProgrammati.add(ref$EventoSociale);
		ref$EventoSociale.eventoSocialeInProgramma=this;
	}
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
