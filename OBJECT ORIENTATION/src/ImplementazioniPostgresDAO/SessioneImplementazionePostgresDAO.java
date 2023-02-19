package ImplementazioniPostgresDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.SessioneDAO;
import Database.ConnessioneDatabase;

public class SessioneImplementazionePostgresDAO implements SessioneDAO{
	private Connection connection;
	
	public SessioneImplementazionePostgresDAO() {
		try {
			connection = ConnessioneDatabase.getInstance().connection;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	
	@Override
	public String getDescrizioneDB(String codSessione){
		
		String descrizione = new String();
		PreparedStatement leggiSedi;
		try {
			leggiSedi = connection.prepareStatement(
					"SELECT DescrizioneSessione FROM SESSIONE \r\n"
					+ "WHERE CodSessione = '"+codSessione+"';");
			ResultSet rs = leggiSedi.executeQuery();
			while (rs.next()) {	
				descrizione = rs.getString("DescrizioneSessione");
			}
			rs.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return descrizione;
	}

}
