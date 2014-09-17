package de.akad.jav02.model;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Vector;

/**
 * 
 * Artikelverwaltung Modell
 * 
 * @author dfalkner, egrinschuk
 *
 */
public class Artikelverwaltung extends Observable {
	
	private ArrayList<Artikel> stamm = new ArrayList<Artikel>();
	private Datenbank db = null;
	private Statement stm = null;
	private ResultSet rst = null;
	
	/**
	 * 
	 * Die Artikelverwaltung Klasse mit Werten aus der Datenbank füllen.
	 * 
	 */
	public void initDBData() {
		this.loadArtikelFromDB();
	}
	
	/**
	 * 
	 * Datenstramm zurückgeben
	 * 
	 * @return ArrayList<Artikel>
	 */
	public ArrayList<Artikel> getDatenstamm() {
		return stamm;
	}

	/**
	 * 
	 * Artikel zur Datenbank hinzufuegen
	 * 
	 * @param name String Name
	 * @param ek double Einkaufspreis
	 * @param vk double Verkaufspreis
	 */
	public void addArtikel(String name, double ek, double vk) {
			
		String query = "INSERT INTO artikel ("
					+ " name,"
					+ " ek,"
					+ " vk"
					+ " ) VALUES ("
					+ "?, ?, ?)";

		try {

			// Prepared Statement Parameter setzen
		    PreparedStatement st = this.db.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		    st.setString(1, name);
		    st.setDouble(2, ek);
		    st.setDouble(3, vk);
	
		    // Prepared Statement ausfuehren und Datenbank ID auslesen.
		    st.executeUpdate();
		    ResultSet rs = st.getGeneratedKeys();
		    
		    int key = 0;
		    if ( rs.next() ) {
		       key = rs.getInt(1);
		    }
		    
		    st.close();
			
		    Artikel a = new Artikel();
			a.setId(key);
			a.setName(name);
			a.setEk(ek);
			a.setVk(vk);
			
			this.loadArtikelFromDB();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 
	 * Artikel löschen mit Datenbank ID
	 * 
	 * @param id Datenbank ID
	 */
	public void deleteArtikel(int id) {
		
		String query = "DELETE FROM artikel WHERE id = ?";

		try {

			PreparedStatement st = this.db.getConnection().prepareStatement(query);
		    st.setInt(1, id);

		    st.executeUpdate();
		    st.close();
			

		} catch (SQLException e) {
		  e.printStackTrace();
		}
		
		this.loadArtikelFromDB();
		
	}
	
	/**
	 * 
	 * Artikel löschen mit Artikel Objekt
	 * 
	 * @param a Artikel
	 */
	public void deleteArtikel(Artikel a) {
		
		this.deleteArtikel(a.getId());

	}
	

	/**
	 * 
	 * Daten von der Datenbank mit Sortierung laden
	 * 
	 * @param spalte String Datenbank Spalte
	 * @throws Exception 
	 */
	public void sortTable(String spalte) throws Exception {
		
		// Prüfen ob das gewuenschte Sortierfeld in der Datenbanktabelle vorhanden ist
		if (this.getSpalten().contains(spalte)) {
		
			setChanged();
		
			String sql = "select * from artikel ORDER BY " + spalte;
			this.loadArtikelFromDBReal(sql);
			
			notifyObservers(this.stamm);
			
		} else {
			throw new Exception ("Illegales Sortierfeld");
		}
		
		

	}
	
	/**
	 * 
	 * MetaData der Artikeltabelle zurückgeben.
	 * 
	 * @return Vector<String>
	 */
	public Vector<String> getSpalten() {
		
		return this.getSpNamen("artikel");
	}
	
	/**
	 * 
	 * Methode zum Auslesen der MetaDaten einer Datenbank Tabelle
	 * 
	 * @param tab Datenbank Tabelle
	 * @return Vector<String> Namen der Tabellenspalten
	 */
	private Vector<String> getSpNamen(String tab) {
	    
		
		ResultSet rs   = null;
	    
		Vector <String> daten = new Vector<String>();
		
		try {
			DatabaseMetaData meta = this.db.getConnection().getMetaData ();
			rs = meta.getColumns(null, null, tab, null);
	        
			while (rs.next ()) { 
	        	daten.add(rs.getString ("COLUMN_NAME"));
	        } 
			
		} catch(SQLException ex) {
	      	System.out.println("SQL Exception:" + ex.getMessage());
		}
		
		return daten;
		
	}	

	/**
	 * 
	 * Alle Artikel Daten aus der Datenbankholen
	 * 
	 */
	private void loadArtikelFromDB() {
		
		setChanged();

		this.loadArtikelFromDBReal("select * from artikel");		
		
		notifyObservers(this.stamm);
		
	}
	
	/**
	 * 
	 * Alle Artikel Daten aus der Datenbankholen
	 * 
	 * Abfrage der Datenbank anhand eines SQL Querys
	 * 
	 * @param sql String SQL Query 
	 */
	private void loadArtikelFromDBReal(String sql) {
		
		stamm.clear();
		
		db = Datenbank.getInstance();		

		try {
			stm = db.getConnection().createStatement();
			rst = stm.executeQuery(sql);
			
			while(rst.next()) {
				
				Artikel a = new Artikel();
				a.setId(rst.getInt("id"));
				a.setName(rst.getString("Name"));
				a.setEk(rst.getDouble("ek"));
				a.setVk(rst.getDouble("vk"));
				
				stamm.add(a);
			}
			
			rst.close();
			stm.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
