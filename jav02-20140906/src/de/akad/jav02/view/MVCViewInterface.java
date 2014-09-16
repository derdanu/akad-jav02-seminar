package de.akad.jav02.view;

import java.util.List;
import java.util.Vector;

import de.akad.jav02.model.Artikel;

public interface MVCViewInterface {

	/**
	 * 
	 * Einkaufspreis ermitteln
	 * 
	 * @return String
	 */
	public String getEK();
	
	/**
	 * 
	 * Verkaufspreis ermitteln
	 * 
	 * @return String
	 */
	public String getVK();
	
	/**
	 * 
	 * Namen des neuen Artikels
	 * 
	 * @return String
	 */
	public String getName();
	
	public void setEK(double ek);
	public void setVK(double vk);
	public void setName(String name);

	public void setSpinnerList(List<?> sl);
	public Artikel getSpinnerArtikel();
	
	public int[] getTableSelectedRows();
	public int TableGetDBValueAt(int x);
	
	public String getCBSelectedItem();
	public void setCBVector(Vector<String> v);

		
}
