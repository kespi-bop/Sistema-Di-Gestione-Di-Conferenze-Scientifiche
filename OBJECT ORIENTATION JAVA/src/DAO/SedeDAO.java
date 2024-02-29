package DAO;

import java.util.ArrayList;

import Model.Sede;

public interface SedeDAO {
	//ottieni tutte le sedi della conferenza da aggiungere alla ComboBox
	ArrayList<String> getSedeDB();
	
	ArrayList<String> getLocazioniDB(Sede sede);
}
