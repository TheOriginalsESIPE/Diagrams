package Client.view;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ViewFin extends JFrame{
	
	
	public static final int W=780;
	public static final int H=250;
	
	JLabel labp ;
	JLabel labp2 ;
	JLabel labp3 ;



	JButton btnFerm;

	JTextField texp ;
	
	JFrame frame ;
	
	public ViewFin (){
		frame = new JFrame("view_new_panne");
	    frame.setResizable(false);
        Font font = new Font("Arial",Font.BOLD,16);

	    
		frame.getContentPane().setLayout(null);
		frame.setSize(W, H);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		frame.getContentPane().setBackground(Color.WHITE);
		
		labp = new JLabel  ("----------------------------------------------------------OPERATION REUSSIE---------------------------------------------------------");
		labp2 = new JLabel ("Le nombre actuel d'operations restante concernant ce vehicule est : ");
		labp3 = new JLabel ("operations");



		labp.setFont(font);
		labp2.setFont(font);
		labp3.setFont(font);


		
		btnFerm = new JButton ("Terminer");
		btnFerm.setFont(font);
		
		texp = new JTextField();
		texp.setEditable(false);
		texp.setBackground(Color.lightGray);
		
		frame.getContentPane().add(btnFerm);
		frame.getContentPane().add(texp);
		frame.getContentPane().add(labp);
		frame.getContentPane().add(labp);
		frame.getContentPane().add(labp2);
		frame.getContentPane().add(labp3);



		
		
		labp.setBounds(10,10,780,50);
		labp2.setBounds(10,80,520,50);
		labp3.setBounds(600,80,100,50);
        texp.setBounds(540,80,50,50);
		btnFerm.setBounds(310,160,150,50);


}
	public JButton getbtnFerm (){
		return btnFerm ;
	}
	
	public JTextField gettexp(){
		return texp;
	}

	
	
	
	
}




