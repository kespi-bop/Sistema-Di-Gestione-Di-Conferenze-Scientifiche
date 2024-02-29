package Controller;



import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import DAO.ConferenzaDAO;
import DAO.Organizzare_S_DAO;
import DAO.OrganizzatoreDAO;
import DAO.ProgrammaDAO;
import DAO.SedeDAO;
import DAO.SessioneDAO;
import DAO.SponsorDAO;
import DAO.UtenteDAO;
import GUI.*;
import ImplementazioniPostgresDAO.ConferenzaImplementazionePostgresDAO;
import ImplementazioniPostgresDAO.Organizzare_S_ImplementazionePostgresDAO;
import ImplementazioniPostgresDAO.OrganizzatoreImplementazionePostgresDAO;
import ImplementazioniPostgresDAO.ProgrammaImplementazionePostgresDAO;
import ImplementazioniPostgresDAO.SedeImplementazionePostgresDAO;
import ImplementazioniPostgresDAO.SessioneImplementazionePostgresDAO;
import ImplementazioniPostgresDAO.SponsorImplementazionePostgresDAO;
import ImplementazioniPostgresDAO.UtenteImplementazionePostgresDAO;
import Model.*;

public class Controller {
	
	Utente utente;
	Date dataProgramma = null;
	
	//COSTRUTTORE
	public Controller() {}
	
	//METODI VISUALIZZAZIONE DEI FRAME
	public void visualizzaFrameRiepilogoKS(JFrame frame)
	{
		RiepilogoKeynoteSpeaker elencoPercentualiKS = new RiepilogoKeynoteSpeaker(this, frame);
		elencoPercentualiKS.frame.setVisible(true);
		frame.setEnabled(false);		//non può essere toccata la finestra Home finchè non vengono eseguite azioni sul nuovo frame
	}
	
	public void visualizzaFrameConferenze(JFrame frame)
	{
		VisualizzaConferenza elencoConferenze = new VisualizzaConferenza(this, frame);
		elencoConferenze.frame.setVisible(true);
		frame.setEnabled(false);		
	}
	
	public void tornaAllaHome(JFrame frame, JFrame frameHome)
	{
		frameHome.setEnabled(true);
		frameHome.setVisible(true);
		frame.dispose();
	}
	
	public void visualizzaFrameProgrammi(JFrame frameVisualizzaConferenza, String CodProgramma)
	{
		VisualizzaProgrammi elencoProgrammi = new VisualizzaProgrammi(this, frameVisualizzaConferenza, CodProgramma);
		elencoProgrammi.frame.setVisible(true);
		frameVisualizzaConferenza.setEnabled(false);
	}
	
	public void visualizzaFrameDescrizione(JFrame frameVisualizzaProgrammi, String titoloSessione, String descrizione)
	{
		VisualizzaDescrizione descrizioneSessione = new VisualizzaDescrizione(this, frameVisualizzaProgrammi, titoloSessione, descrizione);
		descrizioneSessione.frame.setVisible(true);
		frameVisualizzaProgrammi.setEnabled(false);
		
	}
	
	public void vediCreazioneConferenza(JFrame frameHome)
	{
		CreazioneConferenza creazioneConferenza = new CreazioneConferenza(this, frameHome);
		creazioneConferenza.frame.setVisible(true);
		frameHome.setEnabled(false);	//non può essere toccata la finestra HomeOrganizzatore
	}
	
	public void vediCreazioneProgramma(JFrame frameCreazioneConferenza, JFrame frameHome, Conferenza conferenzaCreata, ArrayList<Organizzatore_Locale> listaOrganizzatoriLocali,
									   ArrayList<Organizzatore_Scientifico> listaOrganizzatoriScientifici, ArrayList<Pubblicità> listaPubblicità)
	{
		AggiuntaProgrammi creazioneProgramma = new AggiuntaProgrammi(this, frameCreazioneConferenza, frameHome, conferenzaCreata, 
																	listaOrganizzatoriLocali, listaOrganizzatoriScientifici, listaPubblicità);
		creazioneProgramma.frame.setVisible(true);
		frameCreazioneConferenza.setEnabled(false);
	}
	
	public void vediModificaConferenza(JFrame frameHome, ArrayList<Conferenza> listaConferenze)
	{
		ModificaConferenza modificaConferenza = new ModificaConferenza(this, frameHome, listaConferenze);
		modificaConferenza.frame.setVisible(true);
		frameHome.setEnabled(false);
	}
	
	public void vediAzioniDiModifica(JFrame frameModificaConferenza, JFrame frameHome, Conferenza updateConferenza)
	{
		AzioneDiModifica scegliModifica = new AzioneDiModifica(this, frameModificaConferenza, frameHome, updateConferenza);
		scegliModifica.frame.setVisible(true);
		frameModificaConferenza.setEnabled(false);
	}
	
	public void vediCreazioneProgrammaEdit(JFrame frameAzioniDiModifica, Conferenza updateConferenza, Date dataInizio, Date dataFine)
	{
		AggiungiProgrammiInModifica aggiungiProgrammi = new AggiungiProgrammiInModifica(this, frameAzioniDiModifica, updateConferenza, dataInizio, dataFine);
		aggiungiProgrammi.frame.setVisible(true);
		frameAzioniDiModifica.setEnabled(false);
	}
		
	public void vediCancellaConferenza(JFrame frameHome, ArrayList<Conferenza> listaConferenze)
	{
		CancellaConferenza elencoConferenzeCancellabili = new CancellaConferenza(this, frameHome, listaConferenze);
		elencoConferenzeCancellabili.frame.setVisible(true);
		frameHome.setEnabled(false);
	}
	
	
	
	//METODI RICHIESTA QUERY AL DATABASE
	public ArrayList<String> ottieniSedi()
	{
		SedeDAO listaSedi = new SedeImplementazionePostgresDAO();
		return listaSedi.getSedeDB();
	}

	public void ottieniConferenzeConProgrammi(ArrayList<Integer> listaCodici, ArrayList<String> listaTitoli, 
											  ArrayList<String> listaDate, ArrayList<String> listaSedi, String dataInizio, String dataFine, String sede)
	{
		ConferenzaDAO listaConferenze = new ConferenzaImplementazionePostgresDAO();
		listaConferenze.getConferenzeAndProgrammiDB(listaCodici, listaTitoli, listaDate, listaSedi, dataInizio, dataFine, sede);	
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
	
	public void commitModificaConferenza(String titolo, String descrizione, Conferenza updateConferenza)
	{
		ConferenzaDAO c = new ConferenzaImplementazionePostgresDAO();
		c.commitUpdateDB(titolo, descrizione, updateConferenza);
	}
	
	public void commitCancellaConferenza(Conferenza conferenza)
	{
		ConferenzaDAO c = new ConferenzaImplementazionePostgresDAO();
		c.removeConferenzaDB(conferenza);
	}

	public ArrayList<String> ottieniLocazioni(Sede sede) {
		SedeDAO s = new SedeImplementazionePostgresDAO();
		return s.getLocazioniDB(sede);
	}

	public ArrayList<String> ottieniAllPossibiliChair(Conferenza updateConferenza) {
		Organizzare_S_DAO s = new Organizzare_S_ImplementazionePostgresDAO();
		return s.getAllPossibleChair(updateConferenza.getCodConferenza());
	}

	public void commitAggiungiProgramma(String dataProgramma, Conferenza updateConferenza, ArrayList<Intervallo> listaIntervalli, ArrayList<Sessione> listaSessioni,
			ArrayList<Evento_Sociale> listaEventi) {
		ConferenzaDAO p = new ConferenzaImplementazionePostgresDAO();
		p.commitAddProgramma(dataProgramma, updateConferenza, listaIntervalli, listaSessioni, listaEventi);
	}

	public void commitCancellaProgramma(String codProgramma) {
		ConferenzaDAO c = new ConferenzaImplementazionePostgresDAO();
		c.commitDeleteProgramDB(codProgramma);
	}

	public ArrayList<String> ottieniAnniConferenze() {
		ConferenzaDAO c = new ConferenzaImplementazionePostgresDAO();
		return c.getAnniConferenze();
	}
	
	public ArrayList<String> ottieniAllKS() {
		SessioneDAO s = new SessioneImplementazionePostgresDAO();
		return s.getKeynoteDB();
	}

	public String ottieniConferenzaConflitto(Date dataInizio, Date dataFine, String nomeSede) {
		ConferenzaDAO c= new ConferenzaImplementazionePostgresDAO();
		return c.getConflictConferenzaDB(dataInizio, dataFine, nomeSede);
	}

	public void ottieniProgrammi(Conferenza conferenza) {
		ConferenzaDAO c = new ConferenzaImplementazionePostgresDAO();
		c.getProgrammiDB(conferenza);		
	}
	
	//METODI PER IL LOGIN
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

	//metodi per gestione generica 
	public void RipulisciTabella(TableModel model) {
		DefaultTableModel dtm = (DefaultTableModel) model;
		dtm.setRowCount(0);
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
		InizializzaAttributiSessioneNuova(sessioneNuova, Titolo, timeInizio, timeFine, descrizione);
	
		return sessioneNuova;
	}

	private void InizializzaAttributiSessioneNuova(Sessione sessioneNuova, String titolo, Date timeInizio, Date timeFine, String descrizione) {
		sessioneNuova.setTitolo(titolo);
		sessioneNuova.setOrarioInizio(timeInizio);
		sessioneNuova.setOrarioFine(timeFine);	
		sessioneNuova.setDescrizioneSessione(descrizione);	
	}

	public void TornaAllaPaginaPrecedente(JFrame frame, JFrame framePrecedente) {
		frame.dispose();
		frame.setVisible(false);
		framePrecedente.setVisible(true);		
		framePrecedente.setEnabled(true);
	}
	
}

