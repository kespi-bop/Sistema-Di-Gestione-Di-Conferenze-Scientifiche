package Controller;

import javax.swing.JFrame;

import GUI.AggiungiProgrammiEdit;
import GUI.AggiuntaProgrammi;
import GUI.AzioneDiModifica;
import GUI.CancellaConferenza;
import GUI.CancellaProgramma;
import GUI.CreazioneConferenza;
import GUI.ModificaConferenza;
import GUI.RiepilogoKeynoteSpeaker;
import GUI.VisualizzaConferenza;
import GUI.VisualizzaDescrizione;
import GUI.VisualizzaProgrammi;

public class Controller {
	
	
	
	
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
	
	public void visualizzaFrameProgrammi(Controller controller, JFrame frameVisualizzaConferenza, Object programma)
	{
		VisualizzaProgrammi elencoProgrammi = new VisualizzaProgrammi(controller, frameVisualizzaConferenza, programma);
		elencoProgrammi.frame.setVisible(true);
		frameVisualizzaConferenza.setEnabled(false);
	}
	
	public void visualizzaFrameDescrizione(Controller controller, JFrame frameVisualizzaProgrammi, Object descrizione)
	{
		VisualizzaDescrizione descrizioneSessione = new VisualizzaDescrizione(controller, frameVisualizzaProgrammi, descrizione);
		descrizioneSessione.frame.setVisible(true);
		frameVisualizzaProgrammi.setEnabled(false);
		
	}
	
	public void vediCreazioneConferenza(Controller controller, JFrame frameHome)
	{
		CreazioneConferenza creazioneConferenza = new CreazioneConferenza(controller, frameHome);
		creazioneConferenza.frame.setVisible(true);
		frameHome.setEnabled(false);	//non può essere toccata la finestra HomeOrganizzatore
	}
	
	public void vediCreazioneProgramma(Controller controller, JFrame frameCreazioneConferenza, JFrame frameHome)
	{
		AggiuntaProgrammi creazioneProgramma = new AggiuntaProgrammi(controller, frameCreazioneConferenza, frameHome);
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
	
	public void vediCancellaConferenza(Controller controller, JFrame frameHome)
	{
		CancellaConferenza elencoConferenzeCancellabili = new CancellaConferenza(controller, frameHome);
		elencoConferenzeCancellabili.frame.setVisible(true);
		frameHome.setEnabled(false);
	}

	
	
	
	
	
	
}
