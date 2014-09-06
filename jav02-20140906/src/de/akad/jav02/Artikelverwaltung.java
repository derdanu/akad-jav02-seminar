package de.akad.jav02;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;


public class Artikelverwaltung {
	
	private ArrayList<Artikel> stamm = new ArrayList<Artikel>();
	private Datenbank db = null;
	private Statement stm = null;
	private ResultSet rst = null;
	
	public Artikelverwaltung()  {
		
		db = Datenbank.getInstance();
		

		try {
			stm = db.getConnection().createStatement();
			rst = stm.executeQuery("select * from artikel");
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
	
	}	
	
	public ArrayList<Artikel> getDatenstamm() {
		return stamm;
	}

	public void addArtikel(String name, double ek, double vk) {
		
		
		String query = "INSERT INTO artikel ("
				    + " name,"
				    + " ek,"
				    + " vk"
				    + " ) VALUES ("
				    + "?, ?, ?)";

		 try {
		    // set all the preparedstatement parameters
		    PreparedStatement st = this.db.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		    st.setString(1, name);
		    st.setDouble(2, ek);
		    st.setDouble(3, vk);
	
	
		    // execute the preparedstatement insert
		    st.executeUpdate();
		    ResultSet rs = st.getGeneratedKeys();
		    int key = 0;
		    if ( rs.next() ) {
		        // Retrieve the auto generated key(s).
		       key = rs.getInt(1);
		    }
		    
			System.out.println("Id des neuen Datensatzes: " + key);
	
		    st.close();
			
			Artikel a = new Artikel();
			a.setId(key);
			a.setName(name);
			a.setEk(ek);
			a.setVk(vk);
			
			stamm.add(a);
		  } 
		  catch (SQLException e)
		  {
			e.printStackTrace();
		  }

		
	}
	
	public void deleteArtikel(Artikel a) {
		
		
		String query = "DELETE FROM artikel WHERE id = ?";

		 try {

			PreparedStatement st = this.db.getConnection().prepareStatement(query);
		    st.setInt(1, a.getId());

		    st.executeUpdate();
		    st.close();
			
		    stamm.remove(a);

		  } 
		  catch (SQLException e)
		  {
			e.printStackTrace();
		  }
		
	}
	
	public Vector<String> getSpalten() {
		return this.getSpNamen("artikel");
	}
	
	 private Vector<String> getSpNamen(String tab) {
	    	
		 ResultSet rs   = null;
	    
		 Vector <String> daten = new Vector<String>();
		
		 try {
				DatabaseMetaData meta = this.db.getConnection().getMetaData ();
				rs = meta.getColumns(null, null, tab, null);
	        	while (rs.next ()) { 
	        		daten.add(rs.getString ("COLUMN_NAME"));
	        	} 
			}catch(SQLException ex) {
	        	System.out.println("SQL Exception:" + ex.getMessage());
	        	System.exit(1);
	        }
		return daten;
		
	}	
	 
	 
	@SuppressWarnings("unused")
	private int getNextID() {
		return 0;
	}
	
	
}
