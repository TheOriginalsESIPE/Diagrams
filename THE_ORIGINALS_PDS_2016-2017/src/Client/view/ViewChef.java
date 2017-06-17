package Client.view;
import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ViewChef extends JFrame {
	private Panneau Pan = new Panneau();
	private JLabel p1 ;
	private JTextField PersonelN ;
	private JLabel p2 ;
	private JTextField PersonelP ;
	private JTextArea Area ;
	private JScrollPane q;
	private JLabel NmbreOp ;
	private JLabel NbreOP ;
	private JButton button ;
	public ViewChef() throws IOException{
		p1 = new JLabel("Nom");
		PersonelN = new JTextField();
		PersonelP = new JTextField();
		p2 =new JLabel("Prenom");
		NbreOP = new JLabel("Nombre d'operration :");
		Area =new JTextArea();
		button = new JButton("chercher");
		Pan = new Panneau();
		NmbreOp = new JLabel();
		this.setTitle("chef-depot");
		this.setSize(900,800);
		this.setLocationRelativeTo(null);
		this.setBackground(Color.black);
		Pan.img=ImageIO.read(new File("./image/logoupec.png"));
		Pan.x=680; Pan.y=700; Pan.h=200;Pan.v=50;
		p1.setBounds(10,20,40,10);
		PersonelN.setBounds(10,40 , 200, 30);
		p2.setBounds(10,80,60,10);
		PersonelP.setBounds(10,100 , 200, 30);
		Area.setBounds(230, 40, 500, 600);
		q= new JScrollPane(Area, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		q.setBounds(380, 40, 500, 600);
		NmbreOp.setBounds(220, 570, 70, 30);
		NbreOP.setBounds(60, 576,200,20);
		NbreOP.setForeground(Color.ORANGE);
		p2.setForeground(Color.ORANGE);
		p1.setForeground(Color.ORANGE);
		button.setBounds(40,620 , 100,50 );
		Pan.setLayout(null);
		Pan.add(button);
		Pan.add(PersonelN);
		Pan.add(NbreOP);
		Pan.add(PersonelP);
		Pan.add(q);
		Pan.add(p1);
		Pan.add(p2);
		Pan.add(NmbreOp);
		this.getContentPane().add(Pan); 
		this.setVisible(true);
		}


	public JButton getBtnChercher() {
		return button;
	}
	public void setBtnChercher(JButton buttonch) {
		this.button = buttonch;
	}
	public void setTxtA(JTextArea Area) {
		this.Area = Area;
	}
	public JTextArea getTxtA() {
		return Area;
	}
	public void setPersonelN(JTextField s){
		this.PersonelN=s;
	}
	public JTextField getPersonelN(){
		return PersonelN;
	}
	public void setPersonelP(JTextField s){
		this.PersonelP=s;
	}
	public JTextField getPersonelP(){
		return PersonelP;
}
	public JLabel getNmbreOP(){
		return NmbreOp;
	}
}
