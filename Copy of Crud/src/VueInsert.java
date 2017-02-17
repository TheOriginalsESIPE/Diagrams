import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class VueInsert extends JFrame{

		
		public static final int WIDTH=500;
		public static final int HEIGHT=300;
		private JFrame myFrame;
		private JButton btnOK;
		private JTextField txt1;
		private JTextField txt2;
		private JTextField txt3;
		private JLabel l1;
		private JLabel l2;
		private JLabel l3;
		private JButton btnAgain;
		private ControllerInsert c1;
		
		
	public VueInsert() {
			
			myFrame = new JFrame("INSERTION VEHICULE");
			GridLayout gl = new GridLayout(4,4);
			myFrame.getContentPane().setLayout(gl);
			myFrame.setSize(WIDTH, HEIGHT);
			myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			myFrame.setVisible(true);
			btnOK = new JButton("OK");
			btnAgain = new JButton("Again");
			txt1 = new JTextField();
			txt2 = new JTextField();
			txt3 = new JTextField();
			l1 = new JLabel("NumMat : ");
			l2 = new JLabel("NumPlace : ");
			l3 = new JLabel("date entree : ");
			myFrame.getContentPane().add(l1);
			myFrame.getContentPane().add(txt1);
			myFrame.getContentPane().add(l2);
			myFrame.getContentPane().add(txt2);
			myFrame.getContentPane().add(l3);
			myFrame.getContentPane().add(txt3);
			
			
			myFrame.getContentPane().add(btnAgain);
			myFrame.getContentPane().add(btnOK);
			

	}


	public JButton getBtnOK() {
		return btnOK;
	}
	public JButton getBtnAgain() {
		return btnAgain;
	}

	public JTextField getTxt1() {
		return txt1;
	}


	public JTextField getTxt2() {
		return txt2;
	}


	public JTextField getTxt3() {
		return txt3;
	}


	
	public void setBtnAgain(JButton btnAgain) {
		this.btnAgain = btnAgain;
	}

	public void setBtnOK(JButton btnOK) {
		this.btnOK = btnOK;
	}


	public void setTxt1(JTextField txt1) {
		this.txt1 = txt1;
	}


	public void setTxt2(JTextField txt2) {
		this.txt2 = txt2;
	}


	public void setTxt3(JTextField txt3) {
		this.txt3 = txt3;
	}




	}

