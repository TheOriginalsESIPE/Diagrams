package Client.view;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ViewPanneOp extends JFrame{
	
	
	public static final int W=400;
	public static final int H=400;
	
	JLabel labp ;

	JLabel labp2 ;
	
	
	JButton btnAjout;

	JTextField texp ;
	
	JFrame frame ;
	
	public ViewPanneOp (){
		frame = new JFrame("view_new_panne");
	    frame.setResizable(false);
        Font font = new Font("Arial",Font.BOLD,16);

	    
		frame.getContentPane().setLayout(null);
		frame.setSize(W, H);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		
		labp = new JLabel ("Veuillez indiquer le nom de la panne  ");
		labp2 = new JLabel ("que vous voullez ajouter  ");

		labp.setFont(font);
		labp2.setFont(font);

		
		btnAjout = new JButton ("Ajouter");
		btnAjout.setFont(font);
		
		texp = new JTextField();
		
		frame.getContentPane().add(btnAjout);
		frame.getContentPane().add(texp);
		frame.getContentPane().add(labp);
		frame.getContentPane().add(labp2);

		
		
		labp.setBounds(10,10,300,50);
		labp2.setBounds(10,70,300,50);

		texp.setBounds(10,130,200,50);
		btnAjout.setBounds(200,300,100,50);


}
	public JButton getbtnAjout (){
		return btnAjout ;
	}
	
	public JTextField gettexp(){
		return texp;
	}

	
	
	
	
}



