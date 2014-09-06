package de.akad.jav02;

public class Artikel {

	private int id;
	private String name;
	private double ek;
	private double vk;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getEk() {
		return ek;
	}
	
	public void setEk(double ek) {
		this.ek = ek;
	}
	
	public double getVk() {
		return vk;
	}
	
	public void setVk(double vk) {
		this.vk = vk;
	}
	
	public String toString() {
		return this.name;
	}
	
}
