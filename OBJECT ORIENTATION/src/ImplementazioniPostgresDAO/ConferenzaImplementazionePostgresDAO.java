package ImplementazioniPostgresDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import DAO.ConferenzaDAO;
import Database.ConnessioneDatabase;
import Model.Conferenza;
import Model.Ente;
import Model.Evento_Sociale;
import Model.Intervallo;
import Model.Organizzatore_Locale;
import Model.Organizzatore_Scientifico;
import Model.Programma;
import Model.Pubblicità;
import Model.Sede;
import Model.Sessione;

public class ConferenzaImplementazionePostgresDAO implements ConferenzaDAO{
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sfTime = new SimpleDateFormat("HH:mm");
	Integer codiceIntervalloIncrement = null;
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
				if(sede.isEmpty())
				{
					leggiConferenze = connection.prepareStatement(
							"SELECT CodProgramma, TitoloConferenza, DataProgramma, NomeSede\r\n"
							+ "FROM CONFERENZA NATURAL JOIN PROGRAMMA\r\n");
				}
				else
				{
					leggiConferenze = connection.prepareStatement(
						"SELECT CodProgramma, TitoloConferenza, DataProgramma, NomeSede\r\n"
						+ "FROM CONFERENZA NATURAL JOIN PROGRAMMA\r\n"
						+ "WHERE NomeSede = '"+sede.replace("'","''")+"';");
				}
				
			}
			else if(sede.isEmpty())
			{
				leggiConferenze = connection.prepareStatement(
						"SELECT CodProgramma, TitoloConferenza, DataProgramma, NomeSede\r\n"
						+ "FROM CONFERENZA NATURAL JOIN PROGRAMMA\r\n"
						+ "WHERE DataProgramma = '"+data+"';");
			}
			else
			{
				leggiConferenze = connection.prepareStatement(
						"SELECT CodProgramma, TitoloConferenza, DataProgramma, NomeSede\r\n"
						+ "FROM CONFERENZA NATURAL JOIN PROGRAMMA\r\n"
						+ "WHERE DataProgramma = '"+data+"' AND NomeSede = '"+sede.replace("'","''")+"';");
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
		PreparedStatement riempiOrganizzare_S;
		PreparedStatement riempiOrganizzare_L;
		try {
			
			
			//TABELLA CONFERENZA
			//estraggo il codice conferenza più alto e lo incremento così da poterlo usare per la nuova conferenza
			estraiCodice = connection.prepareStatement("SELECT Max(CodConferenza) FROM CONFERENZA LIMIT 1;");
			ResultSet rsCodConferenza = estraiCodice.executeQuery();
			
			while(rsCodConferenza.next())
				conferenzaCreata.setCodConferenza(rsCodConferenza.getInt(1) + 1);
			
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			riempiConferenza = connection.prepareStatement("INSERT INTO CONFERENZA(CodConferenza, TitoloConferenza, DataInizio, DataFine, Descrizione, NomeSede)"
					+ "VALUES("+conferenzaCreata.getCodConferenza()+",'"+conferenzaCreata.getTitoloConferenza()+"','"+sf.format(conferenzaCreata.getDataInizio())+"',"
					+ "'"+sf.format(conferenzaCreata.getDataFine())+"','"+conferenzaCreata.getDescrizione()+"','"+conferenzaCreata.sedeOspitante.getNomeSede().replace("'", "''")+"');\r\n");
			
			riempiConferenza.executeUpdate();;
			
			//TABELLA PONTE PUBBLICITA'
			for(Pubblicità p: listaPubblicità)
			{
				riempiPubblicità = connection.prepareStatement("INSERT INTO pubblicità(PartitaIva,CodConferenza,Spesa)\r\n"
						+ "SELECT PartitaIva,"+conferenzaCreata.getCodConferenza()+","+p.getSpesa()+" FROM SPONSOR WHERE NomeAzienda = '"+p.getSponsor().getNomeAzienda()+"';");
				riempiPubblicità.executeUpdate();
			}	
			
					
			//TABELLA PONTE ORGANIZZARE_S
			for(Organizzatore_Scientifico s: conferenzaCreata.getOrganizzatoriScientifici())
			{
				riempiOrganizzare_S = connection.prepareStatement("INSERT INTO ORGANIZZARE_S(CodConferenza,emailS)\r\n"
						+ "VALUES("+conferenzaCreata.getCodConferenza()+", '"+s.getEmail()+"')");
				riempiOrganizzare_S.executeUpdate();
			}
			
			
			//TABELLA PONTE ORGANIZZARE_L
			for(Organizzatore_Locale l: conferenzaCreata.getOrganizzatoriLocali())
			{
				riempiOrganizzare_L = connection.prepareStatement("INSERT INTO ORGANIZZARE_L(CodConferenza,emailL)\r\n"
						+ "VALUES("+conferenzaCreata.getCodConferenza()+", '"+l.getEmail()+"');");
				riempiOrganizzare_L.executeUpdate();
			}
			
			RiempiTabellaProgramma(listaProgrammi, conferenzaCreata);
			
			RiempiTabellaIntervallo(listaProgrammi);
			
			RiempiTabellaSessioni(listaProgrammi);
			
			RiempiTabellaEvento_Sociale(listaProgrammi);
			
			
		connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}


	private void RiempiTabellaEvento_Sociale(ArrayList<Programma> listaProgrammi) {
		PreparedStatement estraiCodice;
		PreparedStatement riempiEvento;
		
		try {
			//TABELLA EVENTO_SOCIALE
			//estraggo il codice Intervallo più alto e lo incremento così da poterlo usare per la nuova conferenza
			estraiCodice = connection.prepareStatement("SELECT Max(CodIntervallo) FROM INTERVALLO LIMIT 1;");
			ResultSet rsCodEvento = estraiCodice.executeQuery();
			Integer codiceEventoIncrement = null;
			
			while(rsCodEvento.next())
				codiceEventoIncrement = rsCodEvento.getInt(1);
			
			for(Programma p: listaProgrammi)
			{
				for(Evento_Sociale e: p.eventiProgrammati) 
				{
					riempiEvento = connection.prepareStatement("INSERT INTO EVENTO_SOCIALE(CodEvento,TipoEvento,OrarioInizioEvento,OrarioFineEvento,CodProgramma)\r\n"
							+ "VALUES("+codiceEventoIncrement+",'"+e.getTitolo()+"','"+sfTime.format(e.getOrarioInizio())+"','"+sfTime.format(e.getOrarioFine())+"','"+p.getCodProgramma()+"');");
					riempiEvento.executeUpdate();
					codiceIntervalloIncrement++;
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}


	private void RiempiTabellaSessioni(ArrayList<Programma> listaProgrammi) {
		PreparedStatement estraiCodice;
		PreparedStatement riempiSessione;
		
		try {
			//TABELLA SESSIONE
			//estraggo il codice Sessione più alto e lo incremento così da poterlo usare per la nuova conferenza
			estraiCodice = connection.prepareStatement("SELECT Max(CodSessione) FROM SESSIONE LIMIT 1;");
			ResultSet rsCodSessione = estraiCodice.executeQuery();
			Integer codiceSessioneIncrement = null;
			while(rsCodSessione.next())
				codiceSessioneIncrement = rsCodSessione.getInt(1);
			for(Programma p: listaProgrammi)
			{
				for(Sessione s: p.sessioniProgrammate)
				{	
					if(s.getKeynoteSpeaker().getemailP().isEmpty())
					{
						riempiSessione = connection.prepareStatement("INSERT INTO SESSIONE(CodSessione, OrarioInizioSessione, OrarioFineSessione, TitoloSessione, Chair, CodProgramma, NomeLocazione, DescrizioneSessione)\r\n"
								+ "VALUES("+codiceSessioneIncrement+",'"+sfTime.format(s.getOrarioInizio())+"','"+sfTime.format(s.getOrarioFine())+"','"+s.getTitolo()+"','"+s.getChair().getEmail()+"',\r\n"
								+""+p.getCodProgramma()+",'"+s.getLocazione().getNomeLocazione().replace("'", "''")+"', '"+s.getDescrizioneSessione()+"');");
						riempiSessione.executeUpdate();
						codiceSessioneIncrement++;
					}
					else
					{
						riempiSessione = connection.prepareStatement("INSERT INTO SESSIONE(CodSessione, OrarioInizioSessione, OrarioFineSessione, TitoloSessione, Chair, KeynoteSpeaker, CodProgramma, NomeLocazione, DescrizioneSessione)\r\n"
								+ "VALUES("+codiceSessioneIncrement+",'"+sfTime.format(s.getOrarioInizio())+"','"+sfTime.format(s.getOrarioFine())+"','"+s.getTitolo()+"','"+s.getChair().getEmail()+"',\r\n"
								+ "'"+s.getKeynoteSpeaker().getemailP()+"',"+p.getCodProgramma()+",'"+s.getLocazione().getNomeLocazione().replace("'", "''")+"', '"+s.getDescrizioneSessione()+"');");
						riempiSessione.executeUpdate();
						codiceSessioneIncrement++;
					}
					
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}


	private void RiempiTabellaIntervallo(ArrayList<Programma> listaProgrammi) {
		PreparedStatement estraiCodice;
		PreparedStatement riempiIntervallo;
		
		try {
			//TABELLA INTERVALLO
			//estraggo il codice Intervallo più alto e lo incremento così da poterlo usare per la nuova conferenza
			estraiCodice = connection.prepareStatement("SELECT Max(CodIntervallo) FROM INTERVALLO LIMIT 1;");
			ResultSet rsCodIntervallo = estraiCodice.executeQuery();
			
			
			while(rsCodIntervallo.next())
				codiceIntervalloIncrement = rsCodIntervallo.getInt(1);
			
			
			for(Programma p: listaProgrammi)
			{
				for(Intervallo i: p.intervalliProgrammati) 
				{
					riempiIntervallo = connection.prepareStatement("INSERT INTO INTERVALLO(CodIntervallo,TipoIntervallo,OrarioInizioIntervallo,OrarioFineIntervallo,CodProgramma)\r\n"
							+ "VALUES("+codiceIntervalloIncrement+",'"+i.getTitolo()+"','"+sfTime.format(i.getOrarioInizio())+"','"+sfTime.format(i.getOrarioFine())+"','"+p.getCodProgramma()+"');");
					riempiIntervallo.executeUpdate();
					codiceIntervalloIncrement++;
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}


	private void RiempiTabellaProgramma(ArrayList<Programma> listaProgrammi, Conferenza conferenzaCreata) {
		PreparedStatement estraiCodice;
		PreparedStatement riempiProgramma;
		
		try {
			//TABELLA PROGRAMMA
			//estraggo il codice Programma più alto e lo incremento così da poterlo usare per la nuova conferenza
			estraiCodice = connection.prepareStatement("SELECT Max(CodProgramma) FROM PROGRAMMA LIMIT 1;");
			ResultSet rsCodProgramma = estraiCodice.executeQuery();
			Integer codProgrammaIncrement = 0;
			
			while(rsCodProgramma.next())
					codProgrammaIncrement = rsCodProgramma.getInt(1);
			
			for(Programma p : listaProgrammi)
			{
				++codProgrammaIncrement;
				p.setCodProgramma(codProgrammaIncrement);				
			}
			
			for(Programma p : listaProgrammi)
			{
				riempiProgramma = connection.prepareStatement("INSERT INTO PROGRAMMA(DataProgramma,CodConferenza,CodProgramma)\r\n"
						+ "VALUES('"+sf.format(p.getDataProgramma())+"',"+conferenzaCreata.getCodConferenza()+","+p.getCodProgramma()+");");
				riempiProgramma.executeUpdate();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}


	@Override
	public String getConflictConferenzaDB(Date dataInizio, Date dataFine, String nomeSede) {
		PreparedStatement leggiConferenzaConflitto;
		String sedeTrovata = new String();
		try {	
			leggiConferenzaConflitto = connection.prepareStatement(
						"SELECT TitoloConferenza FROM CONFERENZA WHERE\r\n"
						+ "( ( (('"+dataInizio+"'>= dataInizio) AND ('"+dataInizio+"' <= dataFine)) OR\r\n"
						+ "	(('"+dataFine+"'>= dataInizio) AND ('"+dataFine+"'<= dataFine)) OR\r\n"
						+ "	(('"+dataInizio+"'<dataInizio) AND	('"+dataFine+"'>dataFine))	) AND\r\n"
						+ " NomeSede = '"+nomeSede.replace("'","''")+"');");			
			ResultSet rs = leggiConferenzaConflitto.executeQuery();
			while (rs.next()) {	
				sedeTrovata = rs.getString("TitoloConferenza");
			}
			rs.close();
			connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return sedeTrovata;
	}


	@Override
	public ArrayList<Conferenza> getConferenzeDB() {
		
		PreparedStatement leggiConferenzaConflitto;
		ArrayList<Conferenza> listaConferenze = new ArrayList<Conferenza>();
		try {		
			leggiConferenzaConflitto = connection.prepareStatement(
					"SELECT CodConferenza,TitoloConferenza, DataInizio, DataFine, NomeSede FROM CONFERENZA ORDER BY DataInizio");			
			ResultSet rs = leggiConferenzaConflitto.executeQuery();
			while (rs.next()) {	
				Conferenza conferenza = new Conferenza(new Sede());
				conferenza.setCodConferenza(rs.getInt("CodConferenza"));
				conferenza.setTitoloConferenza(rs.getString("TitoloConferenza"));
				conferenza.setDataInizio(rs.getDate("DataInizio"));
				conferenza.setDataFine(rs.getDate("DataFine"));
				conferenza.sedeOspitante.setNomeSede(rs.getString("NomeSede"));
				listaConferenze.add(conferenza);
			}
			rs.close();
			connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return listaConferenze;
	}


	@Override
	public void removeConferenzaDB(Conferenza conferenza) {
		PreparedStatement cancellaConferenza;
		try {
			cancellaConferenza = connection.prepareStatement(""
					+ "DELETE FROM CONFERENZA WHERE\r\n"
					+ "CodConferenza = "+conferenza.getCodConferenza()+";");
			cancellaConferenza.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	@Override
	public ArrayList<Integer> getRiepilogoKSDB(ArrayList<Ente> istituzioni, String mese, String anno) {
		
		ArrayList<Integer> numeroKS = new ArrayList<Integer>();
		
		PreparedStatement leggiIstituzioni;
		try {	
				
				if(!(mese.isEmpty()))
				{
					leggiIstituzioni = connection.prepareStatement(
							"SELECT istituzione_di_afferenza, count(keynoteSpeaker) FROM PARTECIPANTE NATURAL JOIN(\r\n"
							+ "SELECT DISTINCT KeynoteSpeaker FROM SESSIONE NATURAL JOIN PROGRAMMA\r\n"
							+ "WHERE extract(year from DataProgramma) = "+anno+" AND extract(month from DataProgramma) = "+mese+"\r\n"
							+ "AND (KeyNoteSpeaker is not null)) AS T\r\n"
							+ "WHERE emailp = KeynoteSpeaker\r\n"
							+ "GROUP BY istituzione_di_afferenza;");			
					ResultSet rs = leggiIstituzioni.executeQuery();
					
					while (rs.next()) {	
						Ente e = new Ente();
						e.setNomeIstituzione(rs.getString("istituzione_di_afferenza"));
						istituzioni.add(e);
						numeroKS.add(rs.getInt(2));
					}
					rs.close();
				}
				
				else
				{
					leggiIstituzioni = connection.prepareStatement(
							"SELECT istituzione_di_afferenza, count(keynoteSpeaker) FROM PARTECIPANTE NATURAL JOIN(\r\n"
							+ "SELECT DISTINCT KeynoteSpeaker FROM SESSIONE NATURAL JOIN PROGRAMMA\r\n"
							+ "WHERE extract(year from DataProgramma) = "+anno+"\r\n"
							+ "AND (KeyNoteSpeaker is not null)) AS T\r\n"
							+ "WHERE emailp = KeynoteSpeaker\r\n"
							+ "GROUP BY istituzione_di_afferenza;");			
					ResultSet rs = leggiIstituzioni.executeQuery();
					
					while (rs.next()) {	
						Ente e = new Ente();
						e.setNomeIstituzione(rs.getString("istituzione_di_afferenza"));
						istituzioni.add(e);
						numeroKS.add(rs.getInt(2));
					}
					rs.close();
				}
				
			connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return numeroKS;
		
	}


	@Override
	public void getProgrammiDB(Conferenza conferenza) {
	
		PreparedStatement leggiProgrammi;
		try {	
				
			leggiProgrammi = connection.prepareStatement(
						"SELECT CodProgramma, DataProgramma FROM PROGRAMMA WHERE CodConferenza = "+conferenza.getCodConferenza()+" ORDER BY DataProgramma;");			
				ResultSet rs = leggiProgrammi.executeQuery();
				
				while (rs.next()) {	
					Programma p = new Programma();
					p.setCodProgramma(rs.getInt("CodProgramma"));
					p.setDataProgramma(rs.getDate("DataProgramma"));
					conferenza.programmiConferenza.add(p);				
				}
				rs.close();	
				connection.close();		
			} catch (SQLException e) {
				e.printStackTrace();
			}
					
	}


	@Override
	public Integer commitAddProgramma(String dataProgramma, Conferenza updateConferenza, ArrayList<Intervallo> listaIntervalli,
			ArrayList<Sessione> listaSessioni, ArrayList<Evento_Sociale> listaEventi) {
		
		PreparedStatement estraiCodice;
		PreparedStatement riempiProgramma;
		Integer CodProgrammaCreato = 0;
		
		try {
			//estraggo il codice Programma più alto e lo incremento così da poterlo usare per la nuova conferenza
			estraiCodice = connection.prepareStatement("SELECT Max(CodProgramma) FROM PROGRAMMA LIMIT 1;");
			ResultSet rsCodProgramma = estraiCodice.executeQuery();
			
			while(rsCodProgramma.next())
				CodProgrammaCreato = rsCodProgramma.getInt(1) + 1;
			
			riempiProgramma = connection.prepareStatement("INSERT INTO PROGRAMMA(DataProgramma,CodConferenza,CodProgramma)\r\n"
					+ "VALUES('"+dataProgramma+"',"+updateConferenza.getCodConferenza()+","+CodProgrammaCreato+");");
			riempiProgramma.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		ArrayList<Programma> prog = new ArrayList<Programma>();
		Programma p = new Programma();
		p.setCodProgramma(CodProgrammaCreato);
		try {
			p.setDataProgramma(sf.parse(dataProgramma));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
			p.sessioniProgrammate.addAll(listaSessioni);
		

			p.intervalliProgrammati.addAll(listaIntervalli);
		

			p.eventiProgrammati.addAll(listaEventi);
		
		prog.add(p);
		updateConferenza.programmiConferenza.add(p);
		
		RiempiTabellaIntervallo(prog);
		
		RiempiTabellaSessioni(prog);
		
		RiempiTabellaEvento_Sociale(prog);
		
		return CodProgrammaCreato;		
	}


	@Override
	public void commitDeleteProgramDB(String codProgramma) {
		PreparedStatement cancellaProgramma;
		try {					
				cancellaProgramma = connection.prepareStatement(
					"DELETE FROM PROGRAMMA WHERE CodProgramma = "+codProgramma+";");			
				cancellaProgramma.executeUpdate();
				connection.close();		
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}


	@Override
	public void commitUpdateDB(String titolo, String descrizione, Conferenza updatedConferenza) {
		PreparedStatement aggiornaTitolo = null;
		PreparedStatement aggiornaDescrizione = null;
		try {
				if(!(titolo.isEmpty()))
				{
					aggiornaTitolo = connection.prepareStatement(
							"UPDATE CONFERENZA SET TitoloConferenza = '"+titolo+"' WHERE CodConferenza = "+updatedConferenza.getCodConferenza()+";");	
					aggiornaTitolo.executeUpdate();
				}
				if(!(descrizione.isEmpty()))
				{
					aggiornaDescrizione = connection.prepareStatement(
							"UPDATE CONFERENZA SET Descrizione = '"+descrizione+"' WHERE CodConferenza = "+updatedConferenza.getCodConferenza()+";");		
					aggiornaDescrizione.executeUpdate();
				}				
				
				
				connection.close();		
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	@Override
	public ArrayList<String> getAnniConferenze() {
		PreparedStatement estraiAnni;
		ArrayList<String> listaAnni = new ArrayList<String>();
		
		try {
			//TABELLA PROGRAMMA
			//estraggo il codice Programma più alto e lo incremento così da poterlo usare per la nuova conferenza
			estraiAnni = connection.prepareStatement("SELECT DISTINCT extract(year from DataInizio) as anno FROM CONFERENZA");
			ResultSet rsAnni = estraiAnni.executeQuery();
			
			while(rsAnni.next())
				listaAnni.add(rsAnni.getString("anno"));
	
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return listaAnni;	
	}
}
