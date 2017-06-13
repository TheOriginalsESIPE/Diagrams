package Client.view;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ViewInfoVehicle extends JFrame {
	
	public static final int W=400;
	public static final int H=550;
	
	JLabel labdate ;
	JLabel labmodel ;
	JLabel labmark ;
	JLabel labtype ;
	JLabel labdure;
	JLabel labnump ;
	
	JButton btnFerm;

	JTextField texdate ;
	JTextField texmodel ;
	JTextField texmark;
	JTextField textype;
	JTextField texdure ;
	JTextField texnump ;

	
	JFrame frame ;
	
	public ViewInfoVehicle (){
		frame = new JFrame("viewInfoVehicle");
	    frame.setResizable(false);
	    frame.getContentPane().setBackground(Color.lightGray);
        Font font = new Font("Arial",Font.BOLD,16);

	    
		frame.getContentPane().setLayout(null);
		frame.setSize(W, H);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		
		labdate = new JLabel ("Date d'entré :");
		labdate.setFont(font);
		labnump =new JLabel("N° de place :");
		labnump.setFont(font);
		labmodel =new JLabel("Model :");
		labmodel.setFont(font);
		labmark =new JLabel("Mark :");
		labmark.setFont(font);
		labtype =new JLabel("Type :");
		labtype.setFont(font);
		labdure =new JLabel("Durée estimé :");
		labdure.setFont(font);


		btnFerm = new JButton ("Fermer");
		btnFerm.setFont(font);
		
		texdure = new JTextField();
		texdure.setEditable(false);
		
		texmark= new JTextField(); 
		texmark.setEditable(false);
		
		textype = new JTextField();
		textype.setEditable(false);
		
		texmodel= new JTextField();
		texmodel.setEditable(false);
		
		texnump = new JTextField();
		texnump.setEditable(false);
		
		texdate = new JTextField();
		texdate.setEditable(false);
		
		frame.getContentPane().add(texmark);
		frame.getContentPane().add(texmodel);
		frame.getContentPane().add(textype);
		frame.getContentPane().add(texdate);
		frame.getContentPane().add(texnump);
		frame.getContentPane().add(texdure);
		
		frame.getContentPane().add(labdure);
		frame.getContentPane().add(labnump);
		frame.getContentPane().add(labdate);
		frame.getContentPane().add(labtype);
		frame.getContentPane().add(labmodel);
		frame.getContentPane().add(labmark);
		
		frame.getContentPane().add(btnFerm);


		
		
		labmodel.setBounds(10,10,150,50);
		labdure.setBounds(10,70,150,50);
	    labmark.setBounds(10,130,150,50);
		labtype.setBounds(10,190,150,50);
		labdate.setBounds(10,250,150,50);
		labnump.setBounds(10,310,150,50);

		
		texmodel.setBounds(160,10,200,50);
	    texdure.setBounds(160,70,200,50);
		texmark.setBounds(160,130,200,50);
		textype.setBounds(160,190,200,50);
		texdate.setBounds(160,250,200,50);
		texnump.setBounds(160,310,200,50);

        btnFerm.setBounds(150, 420, 100, 50);



}
	public JButton getbtnFerm (){
		return btnFerm ;
	}
	
	public JTextField gettexdure(){
		return texdure;
	}

	public JTextField gettexmark(){
		return texmark ;
} 
	public JTextField gettextype(){
		return textype ;
} 
	public JTextField gettexnump(){
		return texnump ;
} 
	public JTextField gettexdate(){
		return texdate ;
} 
	public JTextField gettexmodel(){
		return texmodel ;
} 
	public JFrame getframe(){
		return frame ;
} 
	
	
	
	
	
}
