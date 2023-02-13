package Model;

public class Sponsor {
	private String PartitaIva;
	private String NomeAzienda;
	
	//Costruttori
	
	//Metodi gets
	public String getPartitaIva() {
		return PartitaIva;
	}
	public String getNomeAzienda() {
		return NomeAzienda;
	}
	
	//Metodi sets
	public void setPartitaIva(String input) {
		PartitaIva=input;
	}
	public void setNomeAzienda(String input) {
		NomeAzienda=input;
	}
}
