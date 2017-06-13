package Client.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class InfoSysteme extends JFrame implements ActionListener{
	  private String numPlaceParcking, duree;
	  private JButton ButtonOk = new JButton ("OK");
	  private JLabel message = new JLabel();
	  private JLabel message1 = new JLabel();
	  private JLabel message2 = new JLabel();
	  private JFrame frame = new JFrame ("Systeme info");
	  
	  public InfoSysteme(String numPlaceParcking, String duree){
		  
		   String  str = "Les information sur la reparation du vehicle: ";
		   String str1 = "Il est placé au numéro: " + numPlaceParcking + " du parcking";
		   String str2 = "Durée de reparation : " + duree;
		   
		  frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		  frame.setVisible(true);
		  frame.setSize(400,400);
		  frame.setLocationRelativeTo(null);
		  frame.setBackground(Color.BLUE);
		  frame.setLayout(null);
		  
		  ButtonOk.addActionListener(this);
		  ButtonOk.setBounds(100, 300, 200, 40);
		  
		  message.setText(str);
		  message.setBounds(30, 30, 550, 40);
		  message1.setText(str1);
		  message1.setBounds(30, 100, 500, 40);
		  message2.setText(str2);
		  message2.setBounds(30, 180, 500, 40);
		  
		  frame.add(message);		 frame.add(message1);		 frame.add(message2);
		  frame.add(ButtonOk);
	  }
	
	   
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		frame.dispose();
	}
	
	public static void main(String[] args) {
		new InfoSysteme("48", "7 heures 30 minutes 25secondes");
	}
}