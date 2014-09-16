package de.akad.jav02.view;

import java.util.List;
import java.util.Vector;
import de.akad.jav02.model.Artikel;

/**
 * 
 * Interface fuer den View um die vom Controller 
 * benoetigten Methoden implementieren zu muessen
 * 
 * @author dfalkner, egrinschuk
 *
 */
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
	
	/**
	 * 
	 * Einkaufspreis setzen
	 * 
	 * @param ek double
	 */
	public void setEK(double ek);
	
	/**
	 * 
	 * Verkaufspreis setzen
	 * 
	 * @param vk double
	 */
	public void setVK(double vk);
	
	/**
	 * 
	 * Namen setzen
	 * 
	 * @param name String
	 */
	public void setName(String name);

	/**
	 * JSpinner Liste setzen
	 * 
	 * @param sl List<?>
	 */
	public void setSpinnerList(List<?> sl);
	
	/**
	 * 
	 * Ausgewaehlten Artikel im JSpinner zurueckgeben
	 * 
	 * @return Artikel
	 */
	public Artikel getSpinnerArtikel();
	
	/**
	 * 
	 * Ausgewaehlt Zeilen im JTable zurueckgeben
	 * 
	 * @return int[] 
	 */
	public int[] getTableSelectedRows();
	
	/**
	 * 
	 * Datenbank ID einer Zeile im JTable ermitteln
	 * 
	 * @param x int Zeile innerhalb des JTables
	 * 
	 * @return int DatenbankID
	 */
	public int TableGetDBValueAt(int x);
	
	/**
	 * 
	 * Ausgewaeltes Checkbox Feld zurueckgeben
	 * 
	 * @return String;
	 */
	public String getCBSelectedItem();
	
	/**
	 * 
	 * Checkbox Eintraege als Vector uebergeben
	 * 
	 * @param v Vector<String>
	 */
	public void setCBVector(Vector<String> v);

		
}
