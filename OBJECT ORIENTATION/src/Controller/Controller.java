package Controller;

import javax.swing.JFrame;

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
	
	public void vediCreazioneConferenza()
	{
		
	}
	
	public void vediCreazioneProgramma()
	{
		
	}
	
	public void vediModificaConferneza()
	{
		
	}
	
	public void vediAzioniDiModifica()
	{
		
	}
	
	public void vediCancellaProgramma()
	{
		
	}
	
	public void vediCancellaConferenza()
	{
		
	}

	
	
	
	
	
	
}
