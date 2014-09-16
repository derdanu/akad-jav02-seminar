package de.akad.jav02.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * Datenbank Klasse zum Zugriff auf die MySQL Datenbank
 * 
 * Implementiert über das Singleton Pattern, um Mehrfachverbindungen
 * zu der Datenbank zu vermeiden.
 *
 */
public class Datenbank {

	private static final String DBURL = "jdbc:mysql://127.0.0.1/jav02?user=jav02&password=test";
	
	private Connection connect = null;

	private static Datenbank instance;
	private Datenbank() {
		  
		// MySQL Treiber laden
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	    // Verbindungsparameter setzen und zur Datenbank verbinden
	    try {
	    	connect = DriverManager.getConnection(DBURL);
		} catch (SQLException e) {
			System.out.println("Fehler bei der Datenbankverbindung: " + e.getLocalizedMessage());
		}
		  
	  }

	public static synchronized Datenbank getInstance () {
		if (Datenbank.instance == null) {
			Datenbank.instance = new Datenbank ();
		}
		return Datenbank.instance;
	}
	
	/**
	 * 
	 * Mit dieser Methode kann die Datenbank Connection angefordert 
	 * werden.
	 * 
	 * @return Connection 
	 */
	public Connection getConnection() {	
		return connect;
	} 
	
}
