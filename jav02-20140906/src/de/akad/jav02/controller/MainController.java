package de.akad.jav02.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import de.akad.jav02.model.Artikelverwaltung;
import de.akad.jav02.view.MVCView;

public class MainController implements ActionListener {

	Artikelverwaltung av;
	MVCView view;
	
	public MainController() {

		this.view = new MVCView(this);

		this.av = new Artikelverwaltung();
		this.av.addObserver(this.view);
		this.av.initDBData();
		
		this.view.setSpinnerList(this.av.getDatenstamm());
		this.view.setCBVector(this.av.getSpalten());
		
		this.view.setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getActionCommand().equals("add")){
			 
			 Double ek = Double.parseDouble(view.getEK().replace(",", "."));
			 Double vk = Double.parseDouble(view.getVK().replace(",", "."));
		 
			 this.av.addArtikel(view.getName(), ek, vk);
			 
		 } else if (arg0.getActionCommand().equals("can")){
			 
			 view.setEK(0);
			 view.setVK(0);
			 view.setName("");
			 
		 } else if (arg0.getActionCommand().equals("del")) {
			 
			 this.av.deleteArtikel(view.getSpinnerArtikel());
			 
		 }	else if (arg0.getActionCommand().equals("del_liste")) {

			 ArrayList<Integer> dbIds = new ArrayList<Integer>();

			 // Bei mehrfach Auswahl alle ausgewaehlten Datenbank IDs holen 
			 	
			 for (int i: this.view.getTableSelectedRows()) {
				 dbIds.add(this.view.TableGetDBValueAt(i));
			 }
				
			 // Alle Artikel anhand der Datenbank ID entfernen
			 for (int i: dbIds) {
				this.av.deleteArtikel(i);
			 }
		
		 } else if (arg0.getActionCommand().equals("sort")) {
			 
			 this.av.sortTable(this.view.getCBSelectedItem());
			 
		 }
		
	}

}
