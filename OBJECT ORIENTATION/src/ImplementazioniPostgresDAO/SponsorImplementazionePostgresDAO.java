package ImplementazioniPostgresDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.SponsorDAO;
import Database.ConnessioneDatabase;

public class SponsorImplementazionePostgresDAO implements SponsorDAO{

private Connection connection;
	
	public SponsorImplementazionePostgresDAO() {
		try {
			connection = ConnessioneDatabase.getInstance().connection;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	
	@Override
	public ArrayList<String> getListaSponsorDB(){
		
		ArrayList<String> listaSponsor = new ArrayList<String>();
		PreparedStatement leggiSponsor;
		try {
			leggiSponsor = connection.prepareStatement("SELECT NomeAzienda FROM SPONSOR;");
			ResultSet rs = leggiSponsor.executeQuery();
			while (rs.next()) {	
				listaSponsor.add(rs.getString("NomeAzienda"));
			}
			rs.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaSponsor;
	}

}
