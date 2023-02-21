package ImplementazioniPostgresDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.SedeDAO;
import Database.ConnessioneDatabase;
import Model.Locazione;
import Model.Sede;

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


	@Override
	public ArrayList<String> getLocazioniDB(Sede sede) {
		ArrayList<String> listaLocazioni = new ArrayList<String>();
		PreparedStatement leggiLocazioni;
		String nomeSede = sede.getNomeSede();
		
		if(nomeSede.contains("'"))
			nomeSede = nomeSede.replace("'","''");

			
			
		try {
			leggiLocazioni = connection.prepareStatement(
					"SELECT nomeLocazione FROM LOCAZIONE \r\n"
				  + "WHERE nomeSede = '"+nomeSede+"';");
		ResultSet rs = leggiLocazioni.executeQuery();
		while (rs.next()) {	
			listaLocazioni.add(rs.getString("nomeLocazione"));
		}
		rs.close();
		connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaLocazioni;
	}
	
}
