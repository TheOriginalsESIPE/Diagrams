package Client.view;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Erreur extends JFrame{
	Panneau Pan;
	JLabel Text;
	public Erreur() throws IOException{
		Text = new JLabel();
		Text.setForeground(Color.WHITE);
		this.setTitle("erreur");
		this.setSize(600,100);
		Pan = new Panneau();
		Pan.img=ImageIO.read(new File("./image/lerroricon.PNG"));
		Pan.x=15; Pan.y=15; Pan.h=35;Pan.v=35;
		Text.setBounds(65, 20, 1000,25);
		Pan.add(Text);   
		Pan.setLayout(null);
		this.setContentPane(Pan);
		this.setLocationRelativeTo(null);
		
		this.setBackground(Color.black);
		this.setVisible(true);
	}
	public JLabel getJlabel(){
		return this.Text;
	}
	

}
