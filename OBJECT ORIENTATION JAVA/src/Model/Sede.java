package Model;

import java.util.ArrayList;

public class Sede {
	private String nomeSede;
	private String nomeVia;
	private int numeroCivico;
	private String città;
	
	//Costruttori
	public ArrayList<Conferenza> sediConferenza = new ArrayList<Conferenza>();
	public ArrayList<Locazione> locazioniSede = new ArrayList<Locazione>();
	//Trasposizione delle entità conferenza-sede (1..*,1)
	public Sede(Conferenza ref$Conferenza) {
		sediConferenza.add(ref$Conferenza);
		ref$Conferenza.sedeOspitante=this;
	}
	
	//Trasposizione delle entità locazione-sede (1..*,1)
	public Sede(Locazione ref$Locazione) {
		locazioniSede.add(ref$Locazione);
		ref$Locazione.sedeProprietaria=this;
	}
	public Sede() {
	}
	
	//Metodi gets	
	public String getNomeSede() {
		return nomeSede;
	}
	public String getNomeVia() {
		return nomeVia;
	}
	public int getNumeroCivico() {
		return numeroCivico;
	}
	public String getCittà() {
		return città;
	}
	
	//Metodi sets
	public void setNomeSede(String nomeSede) {
		this.nomeSede = nomeSede;
	}
	public void setNomeVia(String nomeVia) {
		this.nomeVia = nomeVia;
	}
	public void setNumeroCivico(int numeroCivico) {
		this.numeroCivico = numeroCivico;
	}
	public void setCittà(String città) {
		this.città = città;
	}
}
