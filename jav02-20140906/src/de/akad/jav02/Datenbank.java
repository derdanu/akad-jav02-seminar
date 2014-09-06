package de.akad.jav02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Datenbank {

	private Connection connect = null;

	// Eine (versteckte) Klassenvariable vom Typ der eigene Klasse
	private static Datenbank instance;
	// Verhindere die Erzeugung des Objektes ueber andere Methoden
	private Datenbank() {
		  
		// this will load the MySQL driver, each DB has its own driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	    // setup the connection with the DB.
	    try {
	    	connect = DriverManager
	    				.getConnection("jdbc:mysql://127.0.0.1/jav02?"
	    							+ "user=jav02&password=test");
			System.out.println("Verbunden....");
		} catch (SQLException e) {
			System.out.println("Fehler bei der Datenbankverdindung: " + e.getLocalizedMessage());
		}
		  
	  }
	  // Eine Zugriffsmethode auf Klassenebene, welches dir '''einmal''' ein konkretes 
	  // Objekt erzeugt und dieses zurueckliefert.
	  // Durch 'synchronized' wird sichergestellt dass diese Methode nur von einem Thread 
	  // zu einer Zeit durchlaufen wird. Der naechste Thread erhaelt immer eine komplett 
	  // initialisierte Instanz.
	public static synchronized Datenbank getInstance () {
		if (Datenbank.instance == null) {
			Datenbank.instance = new Datenbank ();
		}
		return Datenbank.instance;
	}
	
	public Connection getConnection() {	
		return connect;
	} 
	
}
