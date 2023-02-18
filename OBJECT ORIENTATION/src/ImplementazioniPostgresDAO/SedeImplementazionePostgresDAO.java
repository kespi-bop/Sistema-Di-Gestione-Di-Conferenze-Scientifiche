package ImplementazioniPostgresDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.SedeDAO;
import Database.ConnessioneDatabase;

public class SedeImplementazionePostgresDAO implements SedeDAO{
	
	private Connection connection;
		
		public SedeImplementazionePostgresDAO() {
			try {
				connection = ConnessioneDatabase.getInstance().connection;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		
		
	@Override
	public ArrayList<String> getSedeDB() {
		
		ArrayList<String> listaSedi = new ArrayList<String>();
		PreparedStatement leggiSedi;
		try {
			leggiSedi = connection.prepareStatement(
					"SELECT NomeSede FROM SEDE");
		ResultSet rs = leggiSedi.executeQuery();
		while (rs.next()) {	
			listaSedi.add(rs.getString("NomeSede"));
		}
		rs.close();
		connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaSedi;
	}
	
}
