package de.akad.jav02.view;

import java.awt.GridLayout;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.table.DefaultTableModel;

import de.akad.jav02.controller.MainController;
import de.akad.jav02.model.Artikel;


/**
 * 
 * View Klasse
 * 
 * @author dfalkner, egrinschuk
 *
 */
public class MVCView extends JFrame implements Observer, MVCViewInterface{

	private static final long serialVersionUID = 1L;
		
	private NumberFormat format = NumberFormat.getInstance();
	private GridLayout gl = new GridLayout();
	private GridLayout gl2 = new GridLayout();
	private JTextField tf_name = new JTextField();
	private JFormattedTextField tf_ek = new JFormattedTextField(format);
	private JFormattedTextField tf_vk = new JFormattedTextField(format);
	private JButton b_add = new JButton("Hinzufuegen");
	private JButton b_can = new JButton("Abbrechen");
	private JButton b_del = new JButton("Entfernen");
	private JButton b2_del = new JButton("Entfernen");
	private JSpinner spinner = new JSpinner();
	private JTabbedPane tabbedPane = new JTabbedPane();
	private SpinnerListModel sp_model = new SpinnerListModel();
	private JTable table;
	private JComboBox cb_sort;
	private DefaultTableModel model;
    
	
	public MVCView(MainController control) {

		// View Settings
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setTitle("AKAD JAV02");
		setBounds(100, 100, 608, 414);
		
		
		format.setGroupingUsed(false);
		

		this.b_add.addActionListener(control);
		this.b_add.setActionCommand("add");
		this.b_can.addActionListener(control);
		this.b_can.setActionCommand("can");
		this.b_del.addActionListener(control);
		this.b_del.setActionCommand("del");
		
		JPanel panel1 = new JPanel();

		gl.setColumns(2);
		gl.setRows(7);
		
		panel1.setLayout(gl);	
		
		panel1.add(new JLabel("Name"));
		panel1.add(tf_name);
		
		panel1.add(new JLabel("Einkaufspreis"));
		panel1.add(tf_ek);
		
		panel1.add(new JLabel("Verkaufspreis"));
		panel1.add(tf_vk);
		
		panel1.add(new JLabel(""));
		panel1.add(new JLabel(""));
		
		panel1.add(b_add);
		panel1.add(b_can);
			
		spinner.setModel(sp_model);
		panel1.add(b_del);
		panel1.add(spinner);
		
			
		this.tabbedPane.addTab("Artikel", panel1);
		
		// Ab hier der zweite Tab
		
		this.b2_del.addActionListener(control);
		this.b2_del.setActionCommand("del_liste");
		
		JPanel panel2 = new JPanel();
						
		gl2.setColumns(2);
		gl2.setRows(2);
					    
		panel2.setLayout(gl2);	
		
		String[] ueberschrift = {"ID", "Name", 	"EK", 	"VK"};
				
		model = new DefaultTableModel(null, ueberschrift);
		
		this.table = new JTable(model);
			
		this.cb_sort = new JComboBox();
		 
		this.cb_sort.addActionListener(control);
		this.cb_sort.setActionCommand("sort");
		
		panel2.add(new JLabel("Sortieren nach Spalte: "));
		panel2.add(this.cb_sort);
		
		panel2.add(new JScrollPane(table));
		panel2.add(b2_del);	
		
		
		this.tabbedPane.addTab("Artikelliste", panel2);
		
		
		add(tabbedPane);
		
		pack();
		
			
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable arg0, Object arg1) {
		// Alle Einträge löschen
		this.model.getDataVector().removeAllElements();
	    
		// Artikel einlesen
		for (Artikel a:  (ArrayList<Artikel>) arg1) {
	
	    	Object[] row = {a.getId(), a.getName(), a.getEk(), a.getVk()};
	    	this.model.addRow(row);
	    }
	    
	    // Dem Modell mitteilen, dass sich die Daten geaendert haben
	    this.model.fireTableDataChanged();
	 
	}

	@Override
	public String getEK() {
		return this.tf_ek.getText();
	}

	@Override
	public String getVK() {
		return this.tf_vk.getText();
	}

	@Override
	public String getName() {
		return this.tf_name.getText();
	}

	@Override
	public void setEK(double ek) {
		this.tf_ek.setText(String.valueOf(ek));
	}

	@Override
	public void setVK(double vk) {
		this.tf_vk.setText(String.valueOf(vk));
	}

	@Override
	public void setName(String name) {
		this.tf_name.setText(name);
	}

	@Override
	public void setSpinnerList(List<?> sl) {
		this.sp_model.setList(sl);
	}

	@Override
	public Artikel getSpinnerArtikel() {
		return (Artikel) this.spinner.getValue();
	}

	@Override
	public int[] getTableSelectedRows() {
		return this.table.getSelectedRows();
	}

	@Override
	public int TableGetDBValueAt(int x) {
		return (Integer) this.table.getValueAt(x, 0);		
	}

	@Override
	public String getCBSelectedItem() {
		return (String) this.cb_sort.getSelectedItem();
	}

	@Override
	public void setCBVector(Vector<String> v) {
		this.cb_sort.removeAllItems();
		for (String s: v) {
			this.cb_sort.addItem(s);
		}
	}


	
	
}
