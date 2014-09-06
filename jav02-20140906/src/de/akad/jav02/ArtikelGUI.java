package de.akad.jav02;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;


public class ArtikelGUI extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 NumberFormat format = NumberFormat.getInstance();

	
	private GridLayout gl = new GridLayout();
	private JTextField tf_n = new JTextField();
	private JFormattedTextField tf_ek = new JFormattedTextField(format);
	private JFormattedTextField tf_vk = new JFormattedTextField(format);
	private JButton b_add = new JButton("Add");
	private JButton b_can = new JButton("Cancel");
	private JButton b_del = new JButton("Del");
	private JSpinner spinner = new JSpinner();
	private JButton b_liste = new JButton("Aritkelliste");
	
	private Artikelverwaltung art = new Artikelverwaltung();
	
	public ArtikelGUI() {
		
		gl.setColumns(2);
		gl.setRows(7);
		
		this.b_add.addActionListener(this);
		this.b_can.addActionListener(this);
		this.b_del.addActionListener(this);
		this.b_liste.addActionListener(this);

		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setVisible( true );
		setTitle("AKAD JAV02 Calculator (C) 2014 Daniel Falkner");
		setBounds(100, 100, 608, 414);	    
		setLayout(gl);	
		  
		add(new JLabel("Name"));
		add(tf_n);
		
		add(new JLabel("Einkaufspreis"));
				
		add(tf_ek);
		
		add(new JLabel("Verkaufspreis"));
		add(tf_vk);
	
		add(new JLabel(""));
		add(new JLabel(""));
		
		add(b_add);
		add(b_can);
		
			
		SpinnerListModel model = new SpinnerListModel(this.art.getDatenstamm());
		spinner.setModel(model);
		add(b_del);
		add(spinner);
		
		add(b_liste);
		add(new JLabel(""));
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		 		
		if(arg0.getSource() == this.b_add){
			 
			 Double ek = Double.parseDouble(this.tf_ek.getText().replace(",", "."));
			 Double vk = Double.parseDouble(this.tf_vk.getText().replace(",", "."));
		 
			 this.art.addArtikel(this.tf_n.getText(), ek, vk);
			 
			 System.out.println("Aktueller Datenstamm");
			 for (Artikel a: this.art.getDatenstamm()) {
				 System.out.println(a.getName() + " " + a.getEk() + " " + a.getVk());
			 }
	
			 
		 } else if (arg0.getSource() == this.b_can){
			 this.tf_n.setText("");
			 this.tf_ek.setText("");
			 this.tf_vk.setText("");
		
		 } else if (arg0.getSource() == this.b_del) {
			 this.art.deleteArtikel((Artikel) this.spinner.getValue());
		 } else if (arg0.getSource() == this.b_liste) {
			 new ArtikelListeGUI(this.art);
			 
		 }
	
		
	}


}
