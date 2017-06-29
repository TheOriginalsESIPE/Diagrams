package Client.view;

import java.awt.Color;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ViewDepotPieces extends JFrame{
	  private JButton a1, a2 ;
	   private JLabel label ;
	   private JLabel label3 ;
	   private JComboBox combo ;
	   JTextField jtf1 ;
	   JScrollPane q;
	   JTextArea jta ;
	   JTextArea jta1 ;
	  public ViewDepotPieces(){
		   this.setTitle("depot");
	
		   this.setSize(900,600);
		   this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	       this.setLocationRelativeTo(null);
	       this.setLayout(null);
		   combo = new JComboBox ();
		   combo.addItem("TOUTE LES PIECES");
		   combo.addItem("PNEU");
		   combo.addItem("FENETRE");
		   combo.addItem("MOTEUR");
		   combo.addItem("PORTE");
		   combo.addItem("RETROVISEUR");
		   combo.addItem("BOITE DE VITESSE");
		   combo.addItem("CLIGNOTANT");
		   combo.addItem("ECROU");
		   combo.addItem("PLAQUETES DE FREIN");
		   combo.addItem("BOUGIE D'ALLUMAGE");
		   combo.addItem("ROULEMENT DE ROUES");
		   combo.addItem("FILTRE A HUILE");
		   combo.addItem("PHARES");
		   combo.addItem("AMORTISSEUR");
		   combo.setPreferredSize(new Dimension(100, 20));
	       JPanel k = new JPanel() ; 
	       k.setBackground(Color.BLACK);
	       k.setLayout(null);
	       combo.setBounds(70,70,150,30);
	       a1 = new JButton("niveau de stock") ; 
	       a1.setBounds(300, 70, 150, 30);
	       a2 = new JButton("historique") ; 
	       a2.setBounds(70, 170, 150, 30);
	       label = new JLabel(); 
	       label.setBounds(550,70,50,30);
	       label.setForeground(Color.white);
	       label3= new JLabel("Date");
	       label3.setBounds(263,170,70,30);
	       label3.setForeground(Color.white);
	       jtf1 = new JTextField() ; 
	       jtf1.setBounds(300,170,150,30);
	       jta= new JTextArea(); 
	       jta.setBounds(550,170,300,300);  
	       q = new JScrollPane(jta,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	       q.setBounds(550,170,300,300);
	     
	       k.add(a1);
	       k.add(a2);
	       k.add(combo);
	       k.add(label);
	     
	       k.add(label3);
	      
	       k.add(jtf1);
	       k.add(q);

	       this.setContentPane(k);
	       this.setVisible(true);
	  }

	  
	  public JButton getNiveau(){
		  return a1;
	  }
	  public JComboBox getcombo(){
		  return combo;
	  }
	
	  public  JLabel getNL() {
		  return this.label; 
	  }
	  public JButton getHisto(){
		  return a2;
	  }
	  public JTextField getHP(){
		  return this.jtf1;
	  }
	  public  JTextArea getHL() {
		  return this.jta; 
	  }
    

	  
	  public  JTextArea getAtoutelespieces() {
		  return this.jta1; 
	  }
	
}



