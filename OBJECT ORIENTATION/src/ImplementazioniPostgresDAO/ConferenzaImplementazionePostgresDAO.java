package ImplementazioniPostgresDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import DAO.ConferenzaDAO;
import Database.ConnessioneDatabase;

public class ConferenzaImplementazionePostgresDAO implements ConferenzaDAO{
	
	private Connection connection;
	
	public ConferenzaImplementazionePostgresDAO() {
		try {
			connection = ConnessioneDatabase.getInstance().connection;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	
	
	@Override
	public void getConferenzeAndProgrammiDB(ArrayList<Integer> listaCodici, ArrayList<String> listaTitoli, ArrayList<String> listaDate, 
			 								ArrayList<String> listaSedi, String data, String sede)
	{				
		PreparedStatement leggiConferenze;
		try {
			if(data.isEmpty())
			{
				leggiConferenze = connection.prepareStatement(
						"SELECT CodProgramma, TitoloConferenza, DataProgramma, NomeSede\r\n"
						+ "FROM CONFERENZA NATURAL JOIN PROGRAMMA\r\n"
						+ "WHERE NomeSede = '"+sede+"';");
			}
			else
			{
				leggiConferenze = connection.prepareStatement(
						"SELECT CodProgramma, TitoloConferenza, DataProgramma, NomeSede\r\n"
						+ "FROM CONFERENZA NATURAL JOIN PROGRAMMA\r\n"
						+ "WHERE DataProgramma = '"+data+"' AND NomeSede = '"+sede+"';");
			}
		ResultSet rs = leggiConferenze.executeQuery();
		while (rs.next()) {	
			listaCodici.add(rs.getInt("CodProgramma"));
			listaTitoli.add(rs.getString("TitoloConferenza"));
			listaDate.add(rs.getString("DataProgramma"));
			listaSedi.add(rs.getString("NomeSede"));
		}
		rs.close();
		connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
