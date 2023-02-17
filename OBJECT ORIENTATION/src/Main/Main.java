package Main;


import Controller.Controller;
import GUI.Home;
import GUI.HomeOrganizzatore;
import Model.Utente;

public class Main {

	public static void main(String[] args) {
		
		Controller controller = new Controller();
		
		Utente verificaUtente = controller.ottieniOrganizzatoreLoggato();
		
		if(verificaUtente.getEmail() == null)
		{
			Home home = new Home();	
			home.frame.setVisible(true);
		}
		
		else
		{
			HomeOrganizzatore homeOrganizzatore = new HomeOrganizzatore();
			homeOrganizzatore.frame.setVisible(true);
		}
		
	}

}
