package Model;

import java.sql.Time;

public class Intervento {
	private int CodIntervento = 0;
	private Time OrarioInizioIntervento;
	private Time OrarioFineIntervento;
	private String Abstract;
	private String CodPartecipante;//formato email
	private  int CodSessione;
	
	//Costruttori
	
	
	//Metodi gets
	public int getCodIntervento()
	{
		return CodIntervento;
	}
	public Time getOrarioInizioIntervento()
	{
		return OrarioInizioIntervento;
	}
	public Time OrarioFineIntervento()
	{
		return OrarioFineIntervento;
	}
	public String getAbstract()
	{
		return Abstract;
	}
	public String getCodPartecipante()
	{
		return CodPartecipante;
	}
	public int getCodSessione()
	{
		return CodSessione;
	}
	
	//Metodi sets
	
	public void setCodIntervento(int input) {
		CodIntervento=input;
	}
	public void setOrarioInizioIntervento(Time input) {
		OrarioInizioIntervento=input;
	}
	public void setOrarioFineIntervento(Time input) {
		OrarioFineIntervento=input;
	}
	public void setAbstract(String input) {
		Abstract=input;
	}
	public void setCodPartecipante(String input) {
		CodPartecipante=input;
	}
	public void setCodSessione(int input) {
		CodSessione=input;
	}
}
