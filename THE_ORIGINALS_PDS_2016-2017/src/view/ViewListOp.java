package view;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class ViewListOp extends JFrame{
	
	
	public static final int W=400;
	public static final int H=700;
	
	JLabel labp ;

	
	
	JButton btnFerm;

	JTextArea texp ;
	
	JFrame frame ;
	JScrollPane scrolp;
	
	public ViewListOp (){
		frame = new JFrame("view_new_panne");
	    frame.setResizable(false);
        Font font = new Font("Arial",Font.BOLD,16);

	    
		frame.getContentPane().setLayout(null);
		frame.setSize(W, H);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//frame.getContentPane().setBackground(Color.WHITE);
		
		labp = new JLabel ("Voici la liste de toutes les opération existantes");
        
		labp.setFont(font);

		
		btnFerm = new JButton ("Fermer");
		btnFerm.setFont(font);
		
		texp = new JTextArea();
		texp.setEditable(false);
		texp.setBackground(Color.lightGray);
		scrolp=new JScrollPane(texp);
		
		frame.getContentPane().add(btnFerm);
		frame.getContentPane().add(scrolp);
		frame.getContentPane().add(labp);
        scrolp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		
		
		labp.setBounds(10,10,399,50);

		scrolp.setBounds(20,70,350,500);
		btnFerm.setBounds(150,600,100,50);
		frame.setVisible(true);



}
  public void filaman (){
		
	setVisible(false);	
    dispose();
	//this.setVisible(false);
	
	}
	public JButton getbtnFerm (){
		return btnFerm ;
	}
	
	public JTextArea gettexp(){
		return texp;
	}
 public JFrame getframe(){
	 return frame;
 }
	
	
	
	
}



