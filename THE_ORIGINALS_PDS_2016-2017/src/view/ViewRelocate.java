package view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ViewRelocate extends JFrame{
	
	public static final int WIDTH=550;
	public static final int HEIGHT=300;
	private JFrame fenetre;
	private JButton OK;
	private JTextField txtnumMat;
	private JTextArea info;
	private JTextArea info2;
	private JTextField id;
	private JLabel labelnumMat;
	private JLabel labelid;
	
	public ViewRelocate(){
		
		fenetre=new JFrame("RELOCATION");
		GridLayout gl=new GridLayout(4,4);
		fenetre.getContentPane().setLayout(gl);
		fenetre.setSize(WIDTH, HEIGHT);
		fenetre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		fenetre.setVisible(true);
		
		OK=new JButton("OK");
		txtnumMat=new JTextField();
		info=new JTextArea();
		info2=new JTextArea();
		id=new JTextField();
		
		
		labelnumMat=new JLabel("NumMat : ");
		labelid=new JLabel("id_warehouse :");
		
		fenetre.getContentPane().add(labelnumMat);
		fenetre.getContentPane().add(txtnumMat);
		fenetre.getContentPane().add(labelid);
		fenetre.getContentPane().add(id);
		fenetre.getContentPane().add(OK);
		fenetre.getContentPane().add(info);
		fenetre.getContentPane().add(info2);
	}

	public JTextArea getInfo2() {
		return info2;
	}

	public void setInfo2(JTextArea info2) {
		this.info2 = info2;
	}

	public JTextArea getInfo() {
		return info;
	}

	public void setInfo(JTextArea info) {
		this.info = info;
	}

	public JButton getOK() {
		return OK;
	}

	public void setOK(JButton oK) {
		OK = oK;
	}

	public JTextField getTxtnumMat() {
		return txtnumMat;
	}

	public void setTxtnumMat(JTextField txtnumMat) {
		this.txtnumMat = txtnumMat;
	}

	public JLabel getLabelnumMat() {
		return labelnumMat;
	}

	public void setLabelnumMat(JLabel labelnumMat) {
		this.labelnumMat = labelnumMat;
	}

	public JFrame getFenetre() {
		return fenetre;
	}

	public void setFenetre(JFrame fenetre) {
		this.fenetre = fenetre;
	}

	public JTextField getId() {
		return id;
	}

	public void setId(JTextField id) {
		this.id = id;
	}

	public JLabel getLabelid() {
		return labelid;
	}

	public void setLabelid(JLabel labelid) {
		this.labelid = labelid;
	}
}
