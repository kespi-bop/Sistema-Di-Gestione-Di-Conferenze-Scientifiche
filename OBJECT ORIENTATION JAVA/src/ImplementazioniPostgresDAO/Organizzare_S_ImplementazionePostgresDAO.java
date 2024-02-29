package ImplementazioniPostgresDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.Organizzare_S_DAO;
import Database.ConnessioneDatabase;

public class Organizzare_S_ImplementazionePostgresDAO implements Organizzare_S_DAO{
	
	private Connection connection;
		
		public Organizzare_S_ImplementazionePostgresDAO() {
			try {
				connection = ConnessioneDatabase.getInstance().connection;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	

	@Override
	public ArrayList<String> getAllPossibleChair(int codConferenza) {
		ArrayList<String> listaPossibiliChair = new ArrayList<String>();
		PreparedStatement leggiPossibiliChair;
		try {
			leggiPossibiliChair = connection.prepareStatement(
					"SELECT emailS FROM ORGANIZZARE_S WHERE CodConferenza = "+codConferenza+";");
			ResultSet rs = leggiPossibiliChair.executeQuery();
			while (rs.next()) {	
				listaPossibiliChair.add(rs.getString("emailS"));
			}
			rs.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return listaPossibiliChair;
	}	
	
}


