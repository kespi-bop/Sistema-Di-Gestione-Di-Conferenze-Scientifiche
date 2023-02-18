package Main;


import Controller.Controller;
import GUI.Home;
import GUI.HomeOrganizzatore;

public class Main {

	public static void main(String[] args) {
		
		Controller controller = new Controller();
		
		String nome = controller.ottieniOrganizzatoreLoggato();
		
		if(nome.isEmpty())
		{
			Home home = new Home();	
			home.frame.setVisible(true);
		}
		
		else
		{
			Home home = new Home();
			HomeOrganizzatore homeOrganizzatore = new HomeOrganizzatore(home.frame, nome);
			homeOrganizzatore.frame.setVisible(true);
		}
		
	}

}
