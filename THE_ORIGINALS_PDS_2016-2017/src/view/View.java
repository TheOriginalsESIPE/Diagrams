package View;

import java.awt.Color;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class View extends JFrame{
	  private JButton a1, a2, a3 ;
	   private JLabel label ;
	   private JLabel label2 ;
	   private JLabel label3 ;

	   JTextField jtf ;
	   JTextField jtf1 ;

	   JTextArea jta ;
	   JTextArea jta1 ;
	  public View(){
		   this.setTitle("depot");
		   this.setBackground(Color.white);
		    this.setSize(800, 500);
		    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    this.setLocationRelativeTo(null);
		    this.setLayout(null);
		    
	    JPanel k = new JPanel() ; 
	    k.setBackground(Color.white);
	    k.setLayout(null);
	    a1 = new JButton("niveau de stock") ; 
	    a1.setBounds(70, 70, 150, 30);
	    a2 = new JButton("historique") ; 
	    a2.setBounds(70, 170, 150, 30);
	    a3 = new JButton("pieces utilisés ");
	    a3.setBounds(70,300,150,30);
	    
	    label = new JLabel(); 
	    label.setBounds(550,70,50,30);
	    label2 = new JLabel("nomPiece");
	    label2.setBounds(250,70,70,30);
	    label3 = new JLabel("refPiece");
	    label3.setBounds(250,170,50,30);
	    jtf = new JTextField() ; 
	    jtf.setBounds(330,70,150,30);
	    jtf1 = new JTextField() ; 
	    jtf1.setBounds(330,170,150,30);

	    jta= new JTextArea(); 
	    jta.setBounds(550,170,150,150);
	  
	    jta1= new JTextArea(); 
	    jta1.setBounds(300,300,150,150);
	    
	    k.add(a1);
	    k.add(a2);
	    k.add(a3);
	    k.add(label);
	    k.add(label2);
	    k.add(label3);
	    k.add(jtf);
	    k.add(jtf1);
	    k.add(jta);
	    k.add(jta1);
	    
	    this.setContentPane(k);
	    this.setVisible(true);
	  }

	  
	  public JButton getNiveau(){
		  return a1;
	  }
	  public JTextField getNP(){
		  return this.jtf;
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
      
	  public JButton gettoutelespieces(){
		  return a3;
	  }
	  public  JTextArea getAtoutelespieces() {
		  return this.jta1; 
	  }
	 
	  
public static void main(String[] args) {
	View  L = new View ();
}
	
}