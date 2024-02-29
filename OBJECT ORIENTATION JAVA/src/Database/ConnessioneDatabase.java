package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnessioneDatabase {

	// ATTRIBUTI
	private static ConnessioneDatabase instance = null;
	public Connection connection = null;
	private String user = "postgres";
	private String password = "gianfranco";
	private String url = "jdbc:postgresql://localhost:5432/Conferenze_Scientifiche";
	private String driver = "org.postgresql.Driver";

	// COSTRUTTORE
	private ConnessioneDatabase() throws SQLException {
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, user, password);

		} catch (ClassNotFoundException ex) {
			System.out.println("Database Connection Creation Failed : " + ex.getMessage());
			ex.printStackTrace();
		}
		catch (SQLException ex) {
			System.out.println("Database Connection Creation Failed : " + ex.getMessage());
			ex.printStackTrace();
		}

	}


	public static ConnessioneDatabase getInstance() throws SQLException {
		if (instance == null) {
			instance = new ConnessioneDatabase();
		} else if (instance.connection.isClosed()) {
			instance = new ConnessioneDatabase();
		}
		return instance;
	}
	
	
}