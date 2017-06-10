package view;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ViewPieceYoucef extends JFrame {
	
	public static final int W=400;
	public static final int H=400;
	
	JLabel labp ;
	JLabel labqte ;
	
	JButton btnOk;

	JTextField texp ;
	JTextField texqte ;
	
	JFrame frame ;
	
	public ViewPieceYoucef (){
		frame = new JFrame("view_piece");
	    frame.setResizable(false);
        //Font font = new Font("Arial",Font.BOLD,16);

	    
		frame.getContentPane().setLayout(null);
		frame.setSize(W, H);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		
		labp = new JLabel ("veuillez indiquer la piece que vous voullez ");
		labqte =new JLabel("veuillez indiquez la quantité pour la piece choisie");
		
		btnOk = new JButton ("OK");
		
		texp = new JTextField();
		texqte= new JTextField(); 
		
		frame.getContentPane().add(btnOk);
		frame.getContentPane().add(texp);
		frame.getContentPane().add(texqte);
		frame.getContentPane().add(labp);
		frame.getContentPane().add(labqte);
		
		
		labp.setBounds(10,10,300,50);
		labqte.setBounds(10,120,300,50);
		texp.setBounds(10,80,200,30);
		texqte.setBounds(10,180,200,30);
		btnOk.setBounds(200,300,100,50);


}
	public JButton getbtnOk (){
		return btnOk ;
	}
	
	public JTextField gettexp(){
		return texp;
	}

	public JTextField gettexqte(){
		return texqte ;
} 
	
	
	
	
}
