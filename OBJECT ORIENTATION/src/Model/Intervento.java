package Model;

import java.sql.Time;

public class Intervento {
	private int codIntervento = 0;
	private Time orarioInizioIntervento;
	private Time orarioFineIntervento;
	private String aabstract;
	private String codPartecipante;//formato email
	private  int codSessione;
	
	//Costruttori
	
	
	//Metodi gets
	public int getCodIntervento()
	{
		return codIntervento;
	}
	public Time getOrarioInizioIntervento()
	{
		return orarioInizioIntervento;
	}
	public Time OrarioFineIntervento()
	{
		return orarioFineIntervento;
	}
	public String getAabstract()
	{
		return aabstract;
	}
	public String getCodPartecipante()
	{
		return codPartecipante;
	}
	public int getCodSessione()
	{
		return codSessione;
	}
	
	//Metodi sets
	
	public void setCodIntervento(int codIntervento) {
		this.codIntervento = codIntervento;
	}
	public void setOrarioInizioIntervento(Time orarioInizioIntervento) {
		this.orarioInizioIntervento = orarioInizioIntervento;
	}
	public void setOrarioFineIntervento(Time orarioFineIntervento) {
		this.orarioFineIntervento = orarioFineIntervento;
	}
	public void setAbstract(String aabstract) {
		this.aabstract = aabstract;
	}
	public void setCodPartecipante(String codPartecipante) {
		this.codPartecipante = codPartecipante;
	}
	public void setCodSessione(int codSessione) {
		this.codSessione = codSessione;
	}
}
