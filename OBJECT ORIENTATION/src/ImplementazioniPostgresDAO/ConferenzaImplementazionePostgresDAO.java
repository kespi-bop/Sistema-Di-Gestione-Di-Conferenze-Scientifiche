package ImplementazioniPostgresDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import DAO.ConferenzaDAO;
import Database.ConnessioneDatabase;
import Model.Conferenza;
import Model.Evento_Sociale;
import Model.Intervallo;
import Model.Organizzatore_Locale;
import Model.Organizzatore_Scientifico;
import Model.Programma;
import Model.Pubblicità;
import Model.Sessione;

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


	@Override
	public void commitCreateDB(Conferenza conferenzaCreata, ArrayList<Programma> listaProgrammi, ArrayList<Pubblicità> listaPubblicità) {
		
		PreparedStatement estraiCodice;
		PreparedStatement riempiConferenza;
		PreparedStatement riempiPubblicità;
		PreparedStatement riempiProgramma;
		PreparedStatement riempiIntervallo;
		PreparedStatement riempiEvento;
		PreparedStatement riempiOrganizzare_S;
		PreparedStatement riempiOrganizzare_L;
		PreparedStatement riempiSessione;
		try {
			
			
			//TABELLA CONFERENZA
			//estraggo il codice conferenza più alto e lo incremento così da poterlo usare per la nuova conferenza
			estraiCodice = connection.prepareStatement("SELECT Max(CodConferenza) FROM CONFERENZA;");
			ResultSet rsCodConferenza = estraiCodice.executeQuery();
			
			while(rsCodConferenza.next())
				conferenzaCreata.setCodConferenza(rsCodConferenza.getInt(1) + 1);
			
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			riempiConferenza = connection.prepareStatement("INSERT INTO CONFERENZA(CodConferenza, TitoloConferenza, DataInizio, DataFine, Descrizione, NomeSede)"
					+ "VALUES("+conferenzaCreata.getCodConferenza()+",'"+conferenzaCreata.getTitoloConferenza()+"','"+sf.format(conferenzaCreata.getDataInizio())+"',"
					+ "'"+sf.format(conferenzaCreata.getDataFine())+"','"+conferenzaCreata.getDescrizione()+"','"+conferenzaCreata.ospitaConferenza.getNomeSede()+"');\r\n");
			
			riempiConferenza.executeUpdate();;
			
			//TABELLA PONTE PUBBLICITA'
			for(Pubblicità p: listaPubblicità)
			{
				riempiPubblicità = connection.prepareStatement("INSERT INTO pubblicità(PartitaIva,CodConferenza,Spesa)\r\n"
						+ "SELECT PartitaIva,"+conferenzaCreata.getCodConferenza()+","+p.getSpesa()+" FROM SPONSOR WHERE NomeAzienda = '"+p.getSponsor().getNomeAzienda()+"';");
				riempiPubblicità.executeUpdate();
			}	
			
			
			//TABELLA PROGRAMMA
			//estraggo il codice Programma più alto e lo incremento così da poterlo usare per la nuova conferenza
			estraiCodice = connection.prepareStatement("SELECT Max(CodProgramma) FROM PROGRAMMA;");
			ResultSet rsCodProgramma = estraiCodice.executeQuery();
			
			while(rsCodProgramma.next())
			{
				int a = 0;
				listaProgrammi.get(a).setCodProgramma(rsCodProgramma.getInt(1) + (a + 1));	
				a++;
			}
			for(Programma p : listaProgrammi)
			{
				riempiProgramma = connection.prepareStatement("INSERT INTO PROGRAMMA(DataProgramma,CodConferenza,CodProgramma)\r\n"
						+ "VALUES('"+sf.format(p.getDataProgramma())+"',"+conferenzaCreata.getCodConferenza()+","+p.getCodProgramma()+");");
				riempiProgramma.executeUpdate();
			}
			
			
			//TABELLA INTERVALLO
			//estraggo il codice Intervallo più alto e lo incremento così da poterlo usare per la nuova conferenza
			estraiCodice = connection.prepareStatement("SELECT Max(CodIntervallo) FROM INTERVALLO;");
			ResultSet rsCodIntervallo = estraiCodice.executeQuery();
			Integer codiceIntervalloIncrement = null;
			
			while(rsCodIntervallo.next())
				codiceIntervalloIncrement = rsCodIntervallo.getInt(1);
			
			SimpleDateFormat sfTime = new SimpleDateFormat("HH:mm");
			for(Programma p: listaProgrammi)
			{
				for(Intervallo i: p.intervalliProgrammati) 
				{
					riempiIntervallo = connection.prepareStatement("INSERT INTO INTERVALLO(CodIntervallo,TipoIntervallo,OrarioInizioIntervallo,OrarioFineIntervallo,CodProgramma)\r\n"
							+ "VALUES("+codiceIntervalloIncrement+",'"+i.getTipoIntervallo()+"','"+sfTime.format(i.getOrarioInizio())+"','"+sfTime.format(i.getOrarioFine())+"','"+p.getCodProgramma()+"');");
					riempiIntervallo.executeUpdate();
					codiceIntervalloIncrement++;
				}
			}
			
			
			//TABELLA EVENTO_SOCIALE
			//estraggo il codice Intervallo più alto e lo incremento così da poterlo usare per la nuova conferenza
			estraiCodice = connection.prepareStatement("SELECT Max(CodIntervallo) FROM INTERVALLO;");
			ResultSet rsCodEvento = estraiCodice.executeQuery();
			Integer codiceEventoIncrement = null;
			
			while(rsCodEvento.next())
				codiceEventoIncrement = rsCodEvento.getInt(1);
			
			for(Programma p: listaProgrammi)
			{
				for(Evento_Sociale e: p.eventiProgrammati) 
				{
					riempiEvento = connection.prepareStatement("INSERT INTO INTERVALLO(CodIntervallo,TipoIntervallo,OrarioInizioIntervallo,OrarioFineIntervallo,CodProgramma)\r\n"
							+ "VALUES("+codiceEventoIncrement+",'"+e.getTipoEvento()+"','"+sfTime.format(e.getOrarioInizio())+"','"+sfTime.format(e.getOrarioFine())+"','"+p.getCodProgramma()+"');");
					riempiEvento.executeUpdate();
					codiceIntervalloIncrement++;
				}
			}
			
			
			
			//TABELLA PONTE ORGANIZZARE_S
			for(Organizzatore_Scientifico s: conferenzaCreata.getOrganizzatoriScientifici())
			{
				riempiOrganizzare_S = connection.prepareStatement("INSERT INTO ORGANIZZARE_S(CodConferenza,emailS)\r\n"
						+ "VALUES("+conferenzaCreata.getCodConferenza()+", "+s.getEmail()+")");
				riempiOrganizzare_S.executeUpdate();
			}
			
			
			//TABELLA PONTE ORGANIZZARE_L
			for(Organizzatore_Locale l: conferenzaCreata.getOrganizzatoriLocali())
			{
				riempiOrganizzare_L = connection.prepareStatement("INSERT INTO ORGANIZZARE_L(CodConferenza,emailL)\r\n"
						+ "VALUES("+conferenzaCreata.getCodConferenza()+", '"+l.getEmail()+"');");
				riempiOrganizzare_L.executeUpdate();
			}
			
			
			//TABELLA SESSIONE
			//estraggo il codice Sessione più alto e lo incremento così da poterlo usare per la nuova conferenza
			estraiCodice = connection.prepareStatement("SELECT Max(CodSessione) FROM SESSIONE;");
			ResultSet rsCodSessione = estraiCodice.executeQuery();
			Integer codiceSessioneIncrement = null; rsCodSessione.getInt(1);
			while(rsCodSessione.next())
				codiceSessioneIncrement = rsCodSessione.getInt(1);
			for(Programma p: listaProgrammi)
			{
				for(Sessione s: p.sessioniProgrammate) 
				{
					riempiSessione = connection.prepareStatement("INSERT INTO SESSIONE(CodSessione, OrarioInizioSessione, OrarioFineSessione, TitoloSessione, Chair, KeynoteSpeaker, CodProgramma, NomeLocazione)\r\n"
							+ "VALUES("+codiceSessioneIncrement+",'"+sf.format(s.getOrarioInizio())+"','"+sf.format(s.getOrarioFine())+"','"+s.getTitolo()+"','"+s.getChair().getEmail()+"',\r\n"
							+ "'"+s.getKeynoteSpeaker().getemailP()+"',"+p.getCodProgramma()+",'"+s.getLocazione().getNomeLocazione()+"');");
					riempiSessione.executeUpdate();
					codiceSessioneIncrement++;
				}
			}
			
			
			
		connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
