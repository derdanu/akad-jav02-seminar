package de.akad.jav02.model;

/**
 * 
 * Artikel Klasse
 * 
 * @author dfalkner, egrinschuk
 *
 */
public class Artikel {

	// ID
	private int id;
	// Name
	private String name;
	// Einkaufspreis
	private double ek;
	// Verkaufspreis
	private double vk;
	
	/**
	 * 
	 * Getter fuer ID
	 * 
	 * @return int
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * 
	 * Setter fuer ID
	 * 
	 * @param id Int
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * 
	 * Getter fuer Name
	 * 
	 * @return String;
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * Setter fuer Name
	 * 
	 * @param name String
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 
	 * Getter fuer Einkaufspreis
	 * 
	 * @return double
	 */
	public double getEk() {
		return ek;
	}
	
	/**
	 * 
	 * Setter fuer Einkaufspreis
	 * 
	 * @param ek double
	 */
	public void setEk(double ek) {
		this.ek = ek;
	}
	
	/**
	 * 
	 * Getter fuer Verkaufspreis
	 * 
	 * @return double
	 */
	public double getVk() {
		return vk;
	}
	
	/**
	 * 
	 * Setter fuer Verkaufspreis
	 * 
	 * @param vk double
	 */
	public void setVk(double vk) {
		this.vk = vk;
	}
	
	
	/**
	 * 
	 * To String Methode
	 * 
	 */
	public String toString() {
		return this.name;
	}
	
}
