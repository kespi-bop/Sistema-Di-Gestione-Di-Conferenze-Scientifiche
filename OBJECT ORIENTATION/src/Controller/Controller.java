package Controller;



import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;

import DAO.ConferenzaDAO;
import DAO.OrganizzatoreDAO;
import DAO.ProgrammaDAO;
import DAO.SedeDAO;
import DAO.SessioneDAO;
import DAO.SponsorDAO;
import DAO.UtenteDAO;
import GUI.*;
import ImplementazioniPostgresDAO.ConferenzaImplementazionePostgresDAO;
import ImplementazioniPostgresDAO.OrganizzatoreImplementazionePostgresDAO;
import ImplementazioniPostgresDAO.ProgrammaImplementazionePostgresDAO;
import ImplementazioniPostgresDAO.SedeImplementazionePostgresDAO;
import ImplementazioniPostgresDAO.SessioneImplementazionePostgresDAO;
import ImplementazioniPostgresDAO.SponsorImplementazionePostgresDAO;
import ImplementazioniPostgresDAO.UtenteImplementazionePostgresDAO;
import Model.*;

public class Controller {
	
	Utente utente;
	
	//costruttore
	public Controller() {}
	
	//metodi chiamata dei Frame
	public void visualizzaFrameRiepilogoKS(Controller controller, JFrame frame)
	{
		RiepilogoKeynoteSpeaker elencoPercentualiKS = new RiepilogoKeynoteSpeaker(controller, frame);
		elencoPercentualiKS.frame.setVisible(true);
		frame.setEnabled(false);		//non può essere toccata la finestra Home finchè non vengono eseguite azioni sul nuovo frame
	}
	
	public void visualizzaFrameConferenze(Controller controller, JFrame frame)
	{
		VisualizzaConferenza elencoConferenze = new VisualizzaConferenza(controller, frame);
		elencoConferenze.frame.setVisible(true);
		frame.setEnabled(false);		
	}
	
	public void tornaAllaHome(Controller controller, JFrame frame, JFrame frameHome)
	{
		frameHome.setEnabled(true);
		frameHome.setVisible(true);
		frame.dispose();
	}
	
	public void visualizzaFrameProgrammi(Controller controller, JFrame frameVisualizzaConferenza, String CodProgramma)
	{
		VisualizzaProgrammi elencoProgrammi = new VisualizzaProgrammi(controller, frameVisualizzaConferenza, CodProgramma);
		elencoProgrammi.frame.setVisible(true);
		frameVisualizzaConferenza.setEnabled(false);
	}
	
	public void visualizzaFrameDescrizione(Controller controller, JFrame frameVisualizzaProgrammi, String titoloSessione, String descrizione)
	{
		VisualizzaDescrizione descrizioneSessione = new VisualizzaDescrizione(controller, frameVisualizzaProgrammi, titoloSessione, descrizione);
		descrizioneSessione.frame.setVisible(true);
		frameVisualizzaProgrammi.setEnabled(false);
		
	}
	
	public void vediCreazioneConferenza(Controller controller, JFrame frameHome)
	{
		CreazioneConferenza creazioneConferenza = new CreazioneConferenza(controller, frameHome);
		creazioneConferenza.frame.setVisible(true);
		frameHome.setEnabled(false);	//non può essere toccata la finestra HomeOrganizzatore
	}
	
	public void vediCreazioneProgramma(Controller controller, JFrame frameCreazioneConferenza, JFrame frameHome, 
									   Conferenza conferenzaCreata, ArrayList<Organizzatore_Locale> listaOrganizzatoriLocali,
									   ArrayList<Organizzatore_Scientifico> listaOrganizzatoriScientifici, ArrayList<Pubblicità> listaPubblicità)
	{
		AggiuntaProgrammi creazioneProgramma = new AggiuntaProgrammi(controller, frameCreazioneConferenza, frameHome, conferenzaCreata, 
																	listaOrganizzatoriLocali, listaOrganizzatoriScientifici, listaPubblicità);
		creazioneProgramma.frame.setVisible(true);
		frameCreazioneConferenza.setEnabled(false);
	}
	
	public void vediModificaConferenza(Controller controller, JFrame frameHome)
	{
		ModificaConferenza modificaConferenza = new ModificaConferenza(controller, frameHome);
		modificaConferenza.frame.setVisible(true);
		frameHome.setEnabled(false);
	}
	
	public void vediAzioniDiModifica(Controller controller,JFrame frameModificaConferenza, JFrame frameHome, Object programma)
	{
		AzioneDiModifica scegliModifica = new AzioneDiModifica(controller, frameModificaConferenza, frameHome, programma);
		scegliModifica.frame.setVisible(true);
		frameModificaConferenza.setEnabled(false);
	}
	
	public void vediCreazioneProgrammaEdit(Controller controller, JFrame frameAzioniDiModifica)
	{
		AggiungiProgrammiEdit aggiungiProgrammi = new AggiungiProgrammiEdit(controller, frameAzioniDiModifica);
		aggiungiProgrammi.frame.setVisible(true);
		frameAzioniDiModifica.setEnabled(false);
	}
	
	public void vediCancellaProgramma(Controller controller, JFrame frameAzioniDiModifica)
	{
		CancellaProgramma listaCancellabili = new CancellaProgramma(controller, frameAzioniDiModifica);
		listaCancellabili.frame.setVisible(true);
		frameAzioniDiModifica.setEnabled(false);
	}
	
	public void vediCancellaConferenza(Controller controller, JFrame frameHome, ArrayList<Conferenza> listaConferenze)
	{
		CancellaConferenza elencoConferenzeCancellabili = new CancellaConferenza(controller, frameHome, listaConferenze);
		elencoConferenzeCancellabili.frame.setVisible(true);
		frameHome.setEnabled(false);
	}
	
	public ArrayList<String> ottieniSedi()
	{
		SedeDAO listaSedi = new SedeImplementazionePostgresDAO();
		return listaSedi.getSedeDB();
	}

	public void ottieniConferenzeConProgrammi(ArrayList<Integer> listaCodici, ArrayList<String> listaTitoli, 
											  ArrayList<String> listaDate, ArrayList<String> listaSedi, String data, String sede)
	{
		ConferenzaDAO listaConferenze = new ConferenzaImplementazionePostgresDAO();
		listaConferenze.getConferenzeAndProgrammiDB(listaCodici, listaTitoli, listaDate, listaSedi, data, sede);	
	}
	
	public ArrayList<Seduta> ottieniSedute(String codProgramma)
	{
		ProgrammaDAO listaSedute = new ProgrammaImplementazionePostgresDAO();
		return listaSedute.getSeduteDB(codProgramma);
	}
	
	public String ottieniDescrizione(String codSessione)
	{
		SessioneDAO descrizione = new SessioneImplementazionePostgresDAO();
		return descrizione.getDescrizioneDB(codSessione);
	}
	
	public ArrayList<Integer> ottieniRiepilogoKS(ArrayList<Ente> istituzioni, String  mese, String anno)
	{		
		ConferenzaDAO c = new ConferenzaImplementazionePostgresDAO();		
		return c.getRiepilogoKSDB(istituzioni, mese, anno);
	}
	
	public ArrayList<String> ottieniAllSponsor()
	{
		SponsorDAO listaSponsor = new SponsorImplementazionePostgresDAO();
		return listaSponsor.getListaSponsorDB();
	}
	
	public ArrayList<String> ottieniAllOrganizzatoriL()
	{
		OrganizzatoreDAO listaOrganizzatoriL = new OrganizzatoreImplementazionePostgresDAO();
		return listaOrganizzatoriL.getOrganizzatoriLDB();
	}
	
	public ArrayList<String> ottieniAllOrganizzatoriS()
	{
		OrganizzatoreDAO listaOrganizzatoriS = new OrganizzatoreImplementazionePostgresDAO();
		return listaOrganizzatoriS.getOrganizzatoriSDB();
	}
	
	public ArrayList<Conferenza> ottieniConferenze()
	{	
		ConferenzaDAO listaConferenze = new ConferenzaImplementazionePostgresDAO();
		return listaConferenze.getConferenzeDB();	
	}
	
	public void commitCreazioneConferenza(Conferenza conferenzaNuova, ArrayList<Programma> listaProgrammi, ArrayList<Pubblicità> listaPubblicità)
	{
		ConferenzaDAO conferenzaCreata= new ConferenzaImplementazionePostgresDAO();
		conferenzaCreata.commitCreateDB(conferenzaNuova, listaProgrammi, listaPubblicità);
	}
	
	public void commitModificaConferenza()
	{
		
	}
	
	public void commitCancellaConferenza(Conferenza conferenza)
	{
		ConferenzaDAO c = new ConferenzaImplementazionePostgresDAO();
		c.removeConferenzaDB(conferenza);
	}
	
	//metodi di login
	public String isCorrectCredenziali(String email, String password)
	{
		UtenteDAO u = new UtenteImplementazionePostgresDAO();
		return u.getAccessDB(email, password);
	}
	
	public String ottieniOrganizzatoreLoggato()
	{
		UtenteDAO u = new UtenteImplementazionePostgresDAO();
		return u.ottieniUtenteRicordatoDB();
	}
	
	public void storeOrganizzatoreRicordato(String email)
	{
		UtenteDAO u = new UtenteImplementazionePostgresDAO();
		u.ricordaPasswordDB(email);
	}
	
	public void deleteOrganizzatoreRicordato()
	{
		UtenteDAO u = new UtenteImplementazionePostgresDAO();
		u.eliminaPasswordDB();
	}

	public ArrayList<String> ottieniLocazioni(Sede sede) {
		SedeDAO s = new SedeImplementazionePostgresDAO();
		return s.getLocazioniDB(sede);
	}

	public Sessione creaSessionedaFrame(String Titolo, Date timeInizio, Date timeFine, String nomeLocazione, String ks,
			String chair, String descrizione) 
	{
		Locazione locazioneSessione = new Locazione();
		locazioneSessione.setNomeLocazione(nomeLocazione);
		Partecipante ksSessione = new Partecipante();
		ksSessione.setemailP(ks);
		Organizzatore_Scientifico chairSessione = new Organizzatore_Scientifico();
		chairSessione.setEmail(chair);
		Sessione sessioneNuova = new Sessione(locazioneSessione, ksSessione, chairSessione);
		sessioneNuova.setTitolo(Titolo);
		sessioneNuova.setOrarioInizio(timeInizio);
		sessioneNuova.setOrarioFine(timeFine);	
		sessioneNuova.setDescrizioneSessione(descrizione);		
		
		return sessioneNuova;
	}

	public ArrayList<String> ottieniAllKS() {
		SessioneDAO s = new SessioneImplementazionePostgresDAO();
		return s.getKeynoteDB();
	}

	public String ottieniConferenzaConflitto(Date dataInizio, Date dataFine, String nomeSede) {
		ConferenzaDAO c= new ConferenzaImplementazionePostgresDAO();
		return c.getConflictConferenzaDB(dataInizio, dataFine, nomeSede);
	}
}

