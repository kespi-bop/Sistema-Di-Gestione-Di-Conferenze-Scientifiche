package ImplementazioniPostgresDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.UtenteDAO;
import Database.ConnessioneDatabase;

public class UtenteImplementazionePostgresDAO implements UtenteDAO{
	
	private Connection connection;
	
	public UtenteImplementazionePostgresDAO() {
		try {
			connection = ConnessioneDatabase.getInstance().connection;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String ottieniUtenteRicordatoDB()
	{
		String nome = new String();
		PreparedStatement leggiUtenteRicordato;
		try {
			leggiUtenteRicordato = connection.prepareStatement(
					"SELECT nome FROM UtenteRicordato LIMIT 1");
		ResultSet rs = leggiUtenteRicordato.executeQuery();
		while (rs.next()) {	
			nome = rs.getString("nome");
		}
		rs.close();
		connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return nome;
	}
	
	@Override
	public void ricordaPasswordDB(String email)
	{	
		try {
			PreparedStatement storeUtenteRicordato = connection.prepareStatement(
					"INSERT INTO UtenteRicordato (nome, emailu, password)\r\n"
					+ "SELECT nome, emailL, password\r\n"
					+ "FROM ORGANIZZATORE_LOCALE WHERE emailL = '"+email+"';\r\n"
					+ "INSERT INTO UtenteRicordato (nome, emailu, password)\r\n"
					+ "SELECT nome, emailS, password\r\n"
					+ "FROM ORGANIZZATORE_SCIENTIFICO WHERE emailS = '"+email+"';");
			storeUtenteRicordato.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public void eliminaPasswordDB()
	{
		try {
			PreparedStatement deleteUtenteRicordato = connection.prepareStatement(
					"DELETE FROM UtenteRicordato;");
			deleteUtenteRicordato.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public String getAccessDB(String email, String password)
	{
		String l= new String();
		String s = new String();
		PreparedStatement verifyAccess;
		try {
			verifyAccess = connection.prepareStatement(
					"SELECT nome FROM Organizzatore_Locale WHERE\r\n"
					+ "emailL = '"+email+"' AND password = '"+password+"';");
		ResultSet rsl = verifyAccess.executeQuery();	
		
			verifyAccess = connection.prepareStatement(
					"SELECT nome FROM Organizzatore_Scientifico WHERE\r\n"
							+ "emailS = '"+email+"' AND password = '"+password+"';");
		ResultSet rss = verifyAccess.executeQuery();
		while (rsl.next()) {	
			l = rsl.getString("nome");
		}
		while (rsl.next()) {	
			s = rsl.getString("nome");
		}
		rsl.close();
		rss.close();
		connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(l.isEmpty())
			return s;
		
		else
			return l;
	}
}
