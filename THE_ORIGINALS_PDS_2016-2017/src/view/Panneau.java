package ViewAuthentification;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class Panneau extends JPanel {
	public Image img;
	public int x,y,h,v;
	public Panneau(Image s){
		this.img=s;
	}
	public Panneau(){
		
	}
	public void paintComponent(Graphics g){
        this.setBackground(Color.black);
		g.drawImage(img, x, y, h,v,this);
	}

}
