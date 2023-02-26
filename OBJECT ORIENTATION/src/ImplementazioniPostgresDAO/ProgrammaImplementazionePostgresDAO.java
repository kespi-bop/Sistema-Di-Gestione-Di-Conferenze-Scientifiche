package ImplementazioniPostgresDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.ProgrammaDAO;
import Database.ConnessioneDatabase;
import Model.Conferenza;
import Model.Evento_Sociale;
import Model.Intervallo;
import Model.Seduta;
import Model.Sessione;

public class ProgrammaImplementazionePostgresDAO implements ProgrammaDAO{

	private Connection connection;
	
	public ProgrammaImplementazionePostgresDAO() {
		try {
			connection = ConnessioneDatabase.getInstance().connection;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	
	@Override
	public ArrayList<Seduta> getSeduteDB(String CodProgramma)
	{
		ArrayList<Seduta> listaSedute = new ArrayList<Seduta>();
		PreparedStatement leggiSedute;
		
		try {
				leggiSedute = connection.prepareStatement(
						"(SELECT CodSessione, TitoloSessione, OrarioInizioSessione, OrarioFineSessione\r\n"
						+ "FROM SESSIONE NATURAL JOIN PROGRAMMA\r\n"
						+ "WHERE CodProgramma = "+CodProgramma+")\r\n"
						+ "UNION\r\n"
						+ "(SELECT null, TipoIntervallo::text , OrarioInizioIntervallo, OrarioFineIntervallo\r\n"
						+ "FROM INTERVALLO NATURAL JOIN PROGRAMMA\r\n"
						+ "WHERE CodProgramma = "+CodProgramma+")\r\n"
						+ "UNION\r\n"
						+ "(SELECT null, TipoEvento::text , OrarioInizioEvento, OrarioFineEvento\r\n"
						+ "FROM EVENTO_SOCIALE NATURAL JOIN PROGRAMMA\r\n"
						+ "WHERE CodProgramma = "+CodProgramma+")\r\n"
						+ "ORDER BY OrarioInizioSessione;");
				
				ResultSet rs = leggiSedute.executeQuery();
				while (rs.next()) 
				{	
					if(rs.getString("CodSessione") != null)
					{
						Sessione s = new Sessione();
						s.setCodSeduta(rs.getInt("CodSessione"));
						s.setTitolo(rs.getString("TitoloSessione"));
						s.setOrarioInizio(rs.getTime("OrarioInizioSessione"));
						s.setOrarioFine(rs.getTime("OrarioFineSessione"));
						listaSedute.add(s);
					}
					else
					{
						Seduta s = new Seduta();
						s.setTitolo(rs.getString("TitoloSessione"));
						s.setOrarioInizio(rs.getTime("OrarioInizioSessione"));
						s.setOrarioFine(rs.getTime("OrarioFineSessione"));
						listaSedute.add(s);	
					}
				}
				rs.close();
				connection.close();
			}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaSedute;
	}

}
