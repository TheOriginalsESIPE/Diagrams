

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class View extends JFrame{
	
	public static final int WIDTH=650;
	public static final int HEIGHT=550;
	private JFrame myFrame;
	private JButton btnSelect;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnInsert;
	private JButton btnOK;
	private JButton btnOK1;
	private JButton btnOK2;
	
	
	private static JTextArea txtA;
	private static JTextField txtF;
	private static JLabel txtU;
	private static JLabel txtD;
	private JTextField txt1;
	private JTextField txt2;
	
	
	public View() {
		
		myFrame = new JFrame("VEHICULES");
		
		FlowLayout fl = new FlowLayout();
		
		myFrame.getContentPane().setLayout(fl);
		myFrame.setSize(WIDTH, HEIGHT);
		myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		myFrame.setVisible(true);
		btnSelect = new JButton("SELECT");
		btnUpdate = new JButton("UPDATE");
		btnDelete = new JButton("DELETE");
		btnInsert = new JButton("INSERT");
		
		btnOK = new JButton("OK");
		btnOK1 = new JButton("OK");
		btnOK2 = new JButton("OK");
		txtA = new JTextArea("");
		txtF = new JTextField("");
		txt1 = new JTextField("");
		txt2 = new JTextField("");
		txtU = new JLabel("");
		txtD = new JLabel("");
		
		btnSelect.setPreferredSize(new Dimension(150, 50));
		btnUpdate.setPreferredSize(new Dimension(150, 50));
		btnDelete.setPreferredSize(new Dimension(150, 50));
		btnInsert.setPreferredSize(new Dimension(150, 50));
		
		txtA.setPreferredSize(new Dimension(200, 120));
		txtF.setPreferredSize(new Dimension(180, 30));
		txt1.setPreferredSize(new Dimension(180, 30));
		txt2.setPreferredSize(new Dimension(180, 30));
		txtU.setPreferredSize(new Dimension(180, 30));
		txtD.setPreferredSize(new Dimension(180, 30));
		
			
			

		
		btnOK.setPreferredSize(new Dimension(100, 30));
		btnOK1.setPreferredSize(new Dimension(100, 30));
		btnOK2.setPreferredSize(new Dimension(100, 30));
		txtA.setLineWrap(true);
		txtA.setWrapStyleWord(true);
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		BorderLayout b4 = new BorderLayout();
		panel1.setLayout(new FlowLayout());
		panel2.setLayout(new FlowLayout());
		panel3.setLayout(new FlowLayout());
		panel4.setLayout(new FlowLayout());
		
 
		panel1.setPreferredSize(new Dimension(300, 250));
		panel2.setPreferredSize(new Dimension(300, 250));
		panel3.setPreferredSize(new Dimension(300, 250));
		panel4.setPreferredSize(new Dimension(300, 250));
		panel1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLUE));
		panel2.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLUE));
		panel3.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLUE));
		panel4.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLUE));
		myFrame.getContentPane().add(panel1);
		myFrame.getContentPane().add(panel2);
		myFrame.getContentPane().add(panel3);
		myFrame.getContentPane().add(panel4);
		panel1.add(btnSelect);
		panel1.add(txtA);
		panel1.add(txtF);
		panel1.add(btnOK);
		panel2.add(btnUpdate);
		panel2.add(txtU);
		panel2.add(txt1);
		panel2.add(btnOK1);
		panel3.add(btnDelete);
		panel3.add(txtD);
		panel3.add(txt2);
		panel3.add(btnOK2);
		panel4.add(btnInsert, b4);
		
		
		
		
		
		

}
	
	
	
	

	public JButton getBtnSelect() {
		return btnSelect;
	}
	public JButton getBtnOK() {
		return btnOK;
	}

	public JButton getBtnUpdate() {
		return btnUpdate;
	}


	public JButton getBtnDelete() {
		return btnDelete;
	}
	public JButton getBtnInsert() {
		return btnInsert;
	}


	public static JTextArea getTxtA() {
		return txtA;
	}
	public static JTextField getTxtF() {
		return txtF;
	}
	


	public void setBtnSelect(JButton btnSelect) {
		this.btnSelect = btnSelect;
	}


	public void setBtnUpdate(JButton btnUpdate) {
		this.btnUpdate = btnUpdate;
	}


	public void setBtnDelete(JButton btnDelete) {
		this.btnDelete = btnDelete;
	}


	public static void setTxtA(JTextArea txtA) {
		txtA = txtA;
	}





	public static JLabel getTxtU() {
		return txtU;
	}





	public static JLabel getTxtD() {
		return txtD;
	}





	public static void setTxtU(JLabel txtU) {
		txtU = txtU;
	}





	public static void setTxtD(JLabel txtD) {
		txtD = txtD;
	}





	public JButton getBtnOK1() {
		return btnOK1;
	}





	public JButton getBtnOK2() {
		return btnOK2;
	}





	public void setBtnOK1(JButton btnOK1) {
		this.btnOK1 = btnOK1;
	}





	public void setBtnOK2(JButton btnOK2) {
		this.btnOK2 = btnOK2;
	}





	public JTextField getTxt1() {
		return txt1;
	}





	public JTextField getTxt2() {
		return txt2;
	}





	public void setTxt1(JTextField txt1) {
		this.txt1 = txt1;
	}





	public void setTxt2(JTextField txt2) {
		this.txt2 = txt2;
	}





	









	


	
	
}
