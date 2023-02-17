package ImplementazioniPostgresDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.UtenteDAO;
import Database.ConnessioneDatabase;
import Model.Utente;

public class UtenteImplementazionePostgresDAO implements UtenteDAO{
	
	Utente utenteRicordato = new Utente();
	private Connection connection;
	
	public UtenteImplementazionePostgresDAO() {
		try {
			connection = ConnessioneDatabase.getInstance().connection;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Utente ottieniUtenteRicordatoDB()
	{
		PreparedStatement leggiUtenteRicordato;
		try {
			leggiUtenteRicordato = connection.prepareStatement(
					"SELECT * FROM UtenteRicordato LIMIT 1");
		ResultSet rs = leggiUtenteRicordato.executeQuery();
		while (rs.next()) {	
			utenteRicordato.setEmail(rs.getString("emailU"));
			utenteRicordato.setPassword(rs.getString("Password"));
		}
		rs.close();
		connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return utenteRicordato;
	}
}
