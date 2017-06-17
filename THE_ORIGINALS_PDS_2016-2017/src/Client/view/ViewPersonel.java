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

public class ViewPersonel extends JFrame {
	private Panneau Pan = new Panneau();
	private JLabel Matri =new JLabel("Matricule");
	private JTextField MatriText = new JTextField();
	private JLabel Df =new JLabel("Date Fin");
	private JTextField DfText = new JTextField();
	private JTextArea Area =new JTextArea();
	private JScrollPane q;
	private JTextField DdText = new JTextField();
	private JLabel Dd = new JLabel("Date debute");
	private JButton buttonch;
	private JButton butoncum;
	private JButton VehiculeMt;
public ViewPersonel() throws IOException{this.setTitle("Personel");
	VehiculeMt = new JButton("VehiculeMt");
	buttonch = new JButton("chercher");
	butoncum = new JButton("cumul�e");
	this.setSize(900,800);
	this.setLocationRelativeTo(null);
	
	this.setBackground(Color.black);
	Pan.img=ImageIO.read(new File("./image/logoupec.png"));
	Pan.x=680; Pan.y=700; Pan.h=200;Pan.v=50;
	Matri.setBounds(10,20,100,10);
	MatriText.setBounds(10,40 , 200, 30);
	Df.setBounds(140,360,100,20);
	DfText.setBounds(140,380, 100, 20);
	Area.setBounds(230, 40, 500, 600);
	q= new JScrollPane(Area, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	q.setBounds(380, 40, 500, 600);
	DdText.setBounds(10, 380, 100, 20);
	Dd.setBounds(10,360,100,20);
	Dd.setForeground(Color.ORANGE);
	Df.setForeground(Color.ORANGE);
	Matri.setForeground(Color.ORANGE);
	buttonch.setBounds(10,620 , 100,25 );
	butoncum.setBounds(140, 620, 100, 25);
	VehiculeMt.setBounds(500, 685, 100, 25);
	Pan.setLayout(null);
	
	Pan.add(VehiculeMt);
	Pan.add(buttonch);
	Pan.add(butoncum);
	Pan.add(MatriText);
	Pan.add(Dd);
	Pan.add(DfText);
	Pan.add(q);
	Pan.add(Matri);
	Pan.add(Df);
	Pan.add(DdText);
	this.setContentPane(Pan);
	this.setVisible(true);
	}
public JButton getBtnChercher() {
	return buttonch;
}
public JButton getBtnVehiculeMt() {
	return VehiculeMt;
}
public JButton getBtncumule() {
	return butoncum;
}

public void setBtnChercher(JButton buttonch) {
	this.buttonch = buttonch;
}


public void setBtncumule(JButton butoncum) {
	this.butoncum = butoncum;
}

public void setTxtA(JTextArea Area) {
	this.Area = Area;
}
public JTextArea getTxtA() {
	return Area;
}
public JTextField getTxtDf() {
	return DfText;
}

public JTextField getTxtDd() {
	return DdText;
}

public void setTxtDf(JTextField DfText) {
	this.DfText = DfText;
}
public void setTxtDd(JTextField DdText) {
	this.DdText = DdText;
}

public void setTxtM(JTextField MatriText) {
	this.MatriText = MatriText;
}
public JTextField getTxtM() {
	return MatriText;
}

}
