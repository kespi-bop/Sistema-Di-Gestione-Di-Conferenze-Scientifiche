package Model;

public class Sede {
	private String NomeSede;
	private String NomeVia;
	private int NumeroCivico;
	private String Città;
	
	//Costruttori
	
	//Metodi gets
	
	public String getNomeSede() {
		return NomeSede;
	}
	public String getNomeVia() {
		return NomeVia;
	}
	public int getNumeroCivico() {
		return NumeroCivico;
	}
	public String getCittà() {
		return Città;
	}
	
	//Metodi sets
	public void setNomeSede(String input) {
		NomeSede=input;
	}
	public void setNomeVia(String input) {
		NomeVia=input;
	}
	public void setNumeroCivico(int input) {
		NumeroCivico=input;
	}
	public void setCittà(String input) {
		Città=input;
	}
}
