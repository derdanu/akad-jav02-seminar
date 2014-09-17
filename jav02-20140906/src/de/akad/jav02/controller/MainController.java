package de.akad.jav02.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import de.akad.jav02.model.Artikelverwaltung;
import de.akad.jav02.view.MVCView;

/**
 * 
 * Controller Klasse
 * 
 * @author dfalkner, egrinschuk
 *
 */
public class MainController implements ActionListener {

	Artikelverwaltung av;
	MVCView view;
	
	public MainController() {

		// View Initialiseren
		this.view = new MVCView(this);

		// Model Initalisieren, Observer zuweisen
		this.av = new Artikelverwaltung();
		this.av.addObserver(this.view);
		this.av.initDBData();
		
		// View Default Werte anzeigen
		this.view.setSpinnerList(this.av.getDatenstamm());
		this.view.setCBVector(this.av.getSpalten());
		
		this.view.setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		// Neuen Artikel hinzufuegen
		if(arg0.getActionCommand().equals("add")){
			 
			Double ek = Double.parseDouble(view.getEK().replace(",", "."));
			Double vk = Double.parseDouble(view.getVK().replace(",", "."));
		 
			this.av.addArtikel(view.getName(), ek, vk);
		
		// Abbrechen, GUI Felder leeren
		} else if (arg0.getActionCommand().equals("can")){
			 
			view.setEK(0);
			view.setVK(0);
			view.setName("");

		// Loeschen ueber JSpinner Auswahl
		} else if (arg0.getActionCommand().equals("del")) {
			 
			this.av.deleteArtikel(view.getSpinnerArtikel());
		
		// Loeschen ueber JTable Auswahl (auch Mehrfachauswahl)
		} else if (arg0.getActionCommand().equals("del_liste")) {

			ArrayList<Integer> dbIds = new ArrayList<Integer>();

			// Bei mehrfach Auswahl alle ausgewaehlten Datenbank IDs holen 
			 	
			for (int i: this.view.getTableSelectedRows()) {
				dbIds.add(this.view.TableGetDBValueAt(i));
			}
				
			// Alle Artikel anhand der Datenbank ID entfernen
			for (int i: dbIds) {
				this.av.deleteArtikel(i);
			}
		
		// Sortieren der JTable anhand der Datenbankspalten im Pulldown
		} else if (arg0.getActionCommand().equals("sort")) {
			 
			try {
				this.av.sortTable(this.view.getCBSelectedItem());
			} catch (Exception e) {
				// Sollte eine falsche Datenbankspalte übergegeben werden wird eine Exception geworfen.
				e.printStackTrace();
			}
			 
		}
		
	}

}
