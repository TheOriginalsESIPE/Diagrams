package ViewAuthentification;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ViewAuthentificationPersonel extends JFrame{
	private static JTextField txtA2 = new JTextField();
	private static JTextField txtA1 = new JTextField();
	private Panneau Pan =new Panneau();
	private JButton bouton = new JButton("identifier");
	private static JLabel Prenom = new JLabel();
	private static JLabel Nom = new JLabel();

		public ViewAuthentificationPersonel() throws IOException{
			this.setTitle("identification");
			this.setSize(400,400);
			this.setLocationRelativeTo(null);// la fenètre ce positionner au centre  
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//pour fermer la fenetre avec croix rouge
			this.setBackground(Color.black);
			bouton.setBounds(140, 180, 100, 30);
			txtA1.setBounds(90, 130, 200, 20);
			txtA2.setBounds(90, 80, 200, 20);
			Nom.setText("Password :");
			Nom.setForeground(Color.ORANGE);
			Nom.setBounds(90, 90, 100, 50);
			Pan.img=ImageIO.read(new File("logoupec.png"));
			Pan.x=200; Pan.y=300; Pan.h=200;Pan.v=50;
			Prenom.setText("login :");
			Prenom.setForeground(Color.orange);
			Prenom.setBounds(90, 40, 100, 50);
			Pan.add(Nom);
			Pan.add(Prenom);
			Pan.setLayout(null);
			Pan.add(bouton);
			Pan.add(txtA1);
			Pan.add(txtA2);
			this.setContentPane(Pan);
			this.setVisible(true);
			//this.setResizable(b) pour empecher le redementionement de la fenetre
			
		
		}

		public JButton getBtnOK() {
		return bouton;
	}

		public JTextField getTxt1() {
		return txtA2;
	}

		public JTextField getTxt2() {
		return  txtA1;
	}

		public void setBtnOK(JButton btnOK) {
		this.bouton = btnOK;
	}

		public void setTxt1(JTextField txt1) {
		this.txtA1 = txtA1;
	}

		public void setTxt2(JPasswordField txt2) {
		this.txtA2 = txtA2;
	}
		public static void main(String[] args) throws IOException {
			ViewAuthentificationPersonel v = new ViewAuthentificationPersonel();
		}
}
