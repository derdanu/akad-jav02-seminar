package de.akad.jav02;
import java.util.ArrayList;



public class Artikelverwaltung {

	private ArrayList<Artikel> stamm = new ArrayList<Artikel>();

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
