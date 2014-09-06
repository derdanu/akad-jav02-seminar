package de.akad.jav02;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
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


	public ArtikelListeGUI(Artikelverwaltung art) {
		
		gl.setColumns(2);
		gl.setRows(1);
		
		this.b_del.addActionListener(this);


		setDefaultCloseOperation( JFrame.HIDE_ON_CLOSE);
		setVisible( true );
		setTitle("Artikelliste");
		setBounds(100, 100, 608, 414);	    
		setLayout(gl);	
		
		String[] ueberschrift = {
					"Name", 	"EK", 	"VK"
		
		};
	
		
		DefaultTableModel model = new DefaultTableModel(null, ueberschrift);
	    
	    for (Artikel a: art.getDatenstamm()) {

	    	Object[] row = {a.getName(), a.getEk(), a.getVk()};
	    	model.addRow(row);

	    }
	    
	    this.table = new JTable(model);
	    

	    add(table);
		add(b_del);
		
		
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(this.table.getSelectedRow());
		this.table.removeRowSelectionInterval(this.table.getSelectedRow(), this.table.getSelectedRow());
	
	
	}
	
	
}

