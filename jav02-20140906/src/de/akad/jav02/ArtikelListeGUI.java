package de.akad.jav02;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class ArtikelListeGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private GridLayout gl = new GridLayout();
	private JButton b_del = new JButton("Entfernen");
    private JTable table;
    private JComboBox spalten;
    private Artikelverwaltung art;
    private DefaultTableModel model;
    
	public ArtikelListeGUI(Artikelverwaltung art) {
		
		this.art = art;
		
		gl.setColumns(2);
		gl.setRows(2);
		
		this.b_del.addActionListener(this);

		setDefaultCloseOperation( JFrame.HIDE_ON_CLOSE);
		setVisible(true);
		setTitle("Artikelliste");
		setBounds(100, 100, 608, 414);	    
		setLayout(gl);	
		
		String[] ueberschrift = {
				"ID", "Name", 	"EK", 	"VK"
		};

	
		model = new DefaultTableModel(null, ueberschrift);
	
		this.updateModel(art);

		this.table = new JTable(model);
		
	    spalten = new JComboBox(art.getSpalten());
	 
	    spalten.addActionListener(this);
	    
	    
	    add(new JLabel("Sortieren nach Spalte: "));
	    add(spalten);
	    
	    add(new JScrollPane(table));
		add(b_del);
		
	
		
	}

	/**
	 * JTable Datenmodell updaten
	 * 
	 * @param art	Artikelverwaltung
	 * 
	 */
	private void updateModel(Artikelverwaltung art) {
		
		// Alle Einträge löschen
		model.getDataVector().removeAllElements();
	    
		// Artikel einlesen
	    for (Artikel a: art.getDatenstamm()) {
	
	    	Object[] row = {a.getId(), a.getName(), a.getEk(), a.getVk()};
	    	model.addRow(row);
	    }
	    
	    // Dem Modell mitteilen, dass sich die Daten geaendert haben
	    model.fireTableDataChanged();
    
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		// Beim druecken des Entfernen Buttons
		if(arg0.getSource() == this.b_del){
			
			// Bei mehrfach Auswahl alle Artikel der Reihe nach entfernen
			for (int i: this.table.getSelectedRows()) {
				int dbid = (Integer) this.table.getValueAt(i, 0);
				this.art.deleteArtikel(dbid);
			}
	
			// Modell wieder neu einlesen
			this.updateModel(art);
			
		} else if (arg0.getSource() == this.spalten) { // Beim auswaehlen des Spalten Pulldowns zum Sortieren
		
			// Spalte zum Sortieren übergeben
			this.art.sortTable((String) this.spalten.getSelectedItem());
			
			// Modell wieder neu einlesen
			this.updateModel(art);
		}
	
	}
	
	
}

