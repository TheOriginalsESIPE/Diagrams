package view;

import java.awt.*;
import javax.swing.*;

public class ViewVehicleRepaired extends JFrame{
	
	public static final int WIDTH=350;
	public static final int HEIGHT=300;
	
	private JFrame fenetre1;
	private JButton ok, search,ok1;
	private JTextField textdate;
	private JTextArea JnumMat;
	//private JScrollPane js;
	
	public ViewVehicleRepaired(){
		
		fenetre1=new JFrame("recherche de véhicules");
		
		FlowLayout fl=new FlowLayout();
		
		fenetre1.getContentPane().setLayout(fl);
		fenetre1.setSize(WIDTH, HEIGHT);
		fenetre1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		fenetre1.setVisible(true);
		
		ok=new JButton("OK");
		ok1=new JButton("research");
		search=new JButton("search");
		textdate=new JTextField("");
		JnumMat=new JTextArea("");
		//js=new JScrollPane(JnumMat);
		
		ok.setPreferredSize(new Dimension(100, 30));
		ok1.setPreferredSize(new Dimension(100, 30));
		textdate.setPreferredSize(new Dimension(180, 30));
		JnumMat.setPreferredSize(new Dimension(250, 400));
		//js.setPreferredSize(new Dimension(200, 400));
		
		JPanel panel1 = new JPanel();
		
		panel1.setLayout(new FlowLayout());
		panel1.setPreferredSize(new Dimension(300, 250));
		fenetre1.getContentPane().add(panel1);
		panel1.add(search);
		panel1.add(textdate);
		panel1.add(ok);
		panel1.add(ok1);
		panel1.add(JnumMat);
		//panel1.add(ok1);
		//panel1.add(js);
	}

	public JButton getSearch() {
		return search;
	}

	public void setSearch(JButton search) {
		this.search = search;
	}

	/*public JScrollPane getJs() {
		return js;
	}

	public void setJs(JScrollPane js) {
		this.js = js;
	}*/

	public JFrame getFenetre1() {
		return fenetre1;
	}

	public void setFenetre1(JFrame fenetre1) {
		this.fenetre1 = fenetre1;
	}

	public JButton getOk() {
		return ok;
	}

	public void setOk(JButton ok) {
		this.ok = ok;
	}

	public JTextField getTextdate() {
		return textdate;
	}

	public void setTextdate(JTextField textdate) {
		this.textdate = textdate;
	}

	public JTextArea getJnumMat() {
		return JnumMat;
	}

	public void setJnumMat(JTextArea jnumMat) {
		JnumMat = jnumMat;
	}

	public JButton getOk1() {
		return ok1;
	}

	public void setOk1(JButton ok1) {
		this.ok1 = ok1;
	}
	public static void main(String[] args){
		
		ViewVehicleRepaired vvr=new ViewVehicleRepaired();
		
	}
}
