package Model;

public class Sede {
	private String nomeSede;
	private String nomeVia;
	private int numeroCivico;
	private String città;
	
	//Costruttori
	
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
