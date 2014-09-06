package de.akad.jav02;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


public class ArtikelListeGUI extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GridLayout gl = new GridLayout();
	private JButton b_del = new JButton("Lšschen");
    private JTable table;
    private JComboBox spalten;

	public ArtikelListeGUI(Artikelverwaltung art) {
		
		gl.setColumns(2);
		gl.setRows(2);
		
		this.b_del.addActionListener(this);


		setDefaultCloseOperation( JFrame.HIDE_ON_CLOSE);
		setVisible( true );
		setTitle("Artikelliste");
		setBounds(100, 100, 608, 414);	    
		setLayout(gl);	
		
		String[] ueberschrift = {
					"ID", "Name", 	"EK", 	"VK"
		
		};
	
		
		DefaultTableModel model = new DefaultTableModel(null, ueberschrift);
	    
	    for (Artikel a: art.getDatenstamm()) {

	    	Object[] row = {a.getId(), a.getName(), a.getEk(), a.getVk()};
	    	model.addRow(row);

	    }
	    
	    this.table = new JTable(model);
	    
	    

	    spalten = new JComboBox(art.getSpalten());
	 
	    spalten.addActionListener(this);
	    
	    
	    add(new JLabel("Filter"));
	    add(spalten);
	    
	    add(table);
		add(b_del);
		
		
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {

		if(arg0.getSource() == this.b_del){

			System.out.println(this.table.getSelectedRow());
			this.table.removeRowSelectionInterval(this.table.getSelectedRow(), this.table.getSelectedRow());
			
		} else if (arg0.getSource() == this.spalten) {
			System.out.println(this.spalten.getSelectedIndex());

		}
	
	
	}
	
	
}

