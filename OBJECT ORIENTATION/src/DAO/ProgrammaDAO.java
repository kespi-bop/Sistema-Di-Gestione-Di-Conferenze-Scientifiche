package DAO;
import java.util.ArrayList;

import Model.Conferenza;
import Model.Evento_Sociale;
import Model.Intervallo;
import Model.Seduta;
import Model.Sessione;

public interface ProgrammaDAO {
	/* estrae le sessioni, intervalli ed eventi sociali appartenenti a un programma dato 
	   il codice, e li inserisce in un array list di tipo seduta che è estesa dai tre.*/
	ArrayList<Seduta> getSeduteDB(String CodProgramma);

}
