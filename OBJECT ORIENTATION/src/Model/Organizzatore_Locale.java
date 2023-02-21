package Model;

import java.util.ArrayList;

public class Organizzatore_Locale extends Utente{
	private String titolo;
	private String nome;
	private String cognome;
	public Ente appartenenzaEnteOL;//Chiave esterna
	public ArrayList<Conferenza> organizzatoreLConferenze = new ArrayList<Conferenza>();
	//Costruttori
	public Organizzatore_Locale() {
		
	}

	//Metodi gets
	public String getTitolo() {
		return titolo;
	}
	public String getNome() {
		return nome;
	}
	public String getCognome() {
		return cognome;
	}
	
	//Metodi sets
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
}
