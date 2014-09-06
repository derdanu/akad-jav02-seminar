package de.akad.jav02;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


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
				this.addArtikel(rst.getString("Name"), 
								rst.getDouble("ek"),
								rst.getDouble("vk")
								);
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
		Artikel a = new Artikel();
		a.setName(name);
		a.setEk(ek);
		a.setVk(vk);
		
		stamm.add(a);
	}
	
	public void deleteArtikel(Artikel a) {
		stamm.remove(a);
	}
	
	@SuppressWarnings("unused")
	private int getNextID() {
		return 0;
	}
	
	
}
