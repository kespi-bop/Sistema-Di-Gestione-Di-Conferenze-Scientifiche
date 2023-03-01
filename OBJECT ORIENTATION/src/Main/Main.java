package Main;


import Controller.Controller;
import GUI.HomeGuest;
import GUI.HomeOrganizzatore;

public class Main {

	public static void main(String[] args) {
		
		Controller controller = new Controller();	
		String nome = controller.ottieniOrganizzatoreLoggato();
		HomeGuest home = new HomeGuest();
		
		if(nome.isEmpty())
		{
			home.frame.setVisible(true);
		}		
		else
		{

			HomeOrganizzatore homeOrganizzatore = new HomeOrganizzatore(home.frame, nome);
			homeOrganizzatore.frame.setVisible(true);
		}
		
	}

}
