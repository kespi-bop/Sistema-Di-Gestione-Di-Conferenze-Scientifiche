package ImplementazioniPostgresDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.OrganizzatoreDAO;
import Database.ConnessioneDatabase;

public class OrganizzatoreImplementazionePostgresDAO implements OrganizzatoreDAO{
	
	private Connection connection;
	
	public OrganizzatoreImplementazionePostgresDAO() {
		try {
			connection = ConnessioneDatabase.getInstance().connection;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	
	@Override
	public ArrayList<String> getOrganizzatoriLDB(){
		
		ArrayList<String> listaOrganizzatoriL = new ArrayList<String>();
		PreparedStatement leggiOrganizzatoriL;
		try {
			leggiOrganizzatoriL = connection.prepareStatement("SELECT emailL FROM Organizzatore_Locale;");
			ResultSet rs = leggiOrganizzatoriL.executeQuery();
			while (rs.next()) {	
				listaOrganizzatoriL.add(rs.getString("emailL"));
			}
			rs.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaOrganizzatoriL;
	}

	@Override
	public ArrayList<String> getOrganizzatoriSDB() {
		
		ArrayList<String> listaOrganizzatoriS = new ArrayList<String>();
		PreparedStatement leggiOrganizzatoriS;
		try {
			leggiOrganizzatoriS = connection.prepareStatement("SELECT emailS FROM Organizzatore_Scientifico;");
			ResultSet rs = leggiOrganizzatoriS.executeQuery();
			while (rs.next()) {	
				listaOrganizzatoriS.add(rs.getString("emailS"));
			}
			rs.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaOrganizzatoriS;
	}

}
