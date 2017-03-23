package view;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ViewAuthentification extends JFrame{
	
	public static final int WIDTH=500;
	public static final int HEIGHT=300;
	private JFrame myFrame;
	private JButton btnOK;
	private JTextField txt1;
	private JPasswordField txt2;
	private JLabel l1;
	private JLabel l2;
	
	//private Controller c1;
	
	
public ViewAuthentification() {
		
		myFrame = new JFrame("AUTHENTIFICATION");
		GridLayout gl = new GridLayout(5,1);
		myFrame.getContentPane().setLayout(gl);
		myFrame.setSize(WIDTH, HEIGHT);
		myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		myFrame.setVisible(true);
		btnOK = new JButton("OK");
		txt1 = new JTextField();
		txt2 = new JPasswordField();
		l1 = new JLabel("Login :");
		l2 = new JLabel("Password :");
		
		myFrame.getContentPane().add(l1);
		myFrame.getContentPane().add(txt1);
		myFrame.getContentPane().add(l2);
		myFrame.getContentPane().add(txt2);
		
		myFrame.getContentPane().add(btnOK);
		

}


public JButton getBtnOK() {
	return btnOK;
}


public JTextField getTxt1() {
	return txt1;
}


public JPasswordField getTxt2() {
	return txt2;
}








public void setBtnOK(JButton btnOK) {
	this.btnOK = btnOK;
}


public void setTxt1(JTextField txt1) {
	this.txt1 = txt1;
}


public void setTxt2(JPasswordField txt2) {
	this.txt2 = txt2;
}








}
