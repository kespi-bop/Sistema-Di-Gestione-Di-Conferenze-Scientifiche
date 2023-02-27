package Model;

import java.util.ArrayList;

public class Sponsor {
	private String partitaIva;
	private String nomeAzienda;
	
	
	public ArrayList<Pubblicità> sponsorAzienda = new ArrayList<Pubblicità>();
	
	//Costruttori
	public Sponsor(){
	}
	
	//Metodi gets
	public String getPartitaIva() {
		return partitaIva;
	}
	public String getNomeAzienda() {
		return nomeAzienda;
	}
	
	//Metodi sets
	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}
	public void setNomeAzienda(String nomeAzienda) {
		this.nomeAzienda = nomeAzienda;
	}
}
