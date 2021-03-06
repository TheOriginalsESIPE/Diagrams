package Client.view;

import java.awt.*;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.*;


public class ViewVehicleRepaired extends JFrame{
	
	public static final int WIDTH=350;
	public static final int HEIGHT=300;
	
	private JFrame fenetre1;
	private JButton ok, search,ok1,sauvegarde,table;
	private JTextField textdate;
	private JTextArea JnumMat;
	
	public ViewVehicleRepaired(){
		
		fenetre1=new JFrame("recherche de vehicules");
		this.setSize(500, 500);
		FlowLayout fl=new FlowLayout();
		System.out.println("DEBUG");
		fenetre1.getContentPane().setLayout(fl);
		fenetre1.setSize(WIDTH, HEIGHT);
		fenetre1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		fenetre1.setVisible(true);
		
		ok=new JButton("OK");
		ok1=new JButton("research");
		search=new JButton("search");
		sauvegarde=new JButton("backup");
		table=new JButton("table");
		textdate=new JTextField("");
		JnumMat=new JTextArea("");
		
		ok.setPreferredSize(new Dimension(100, 30));
		ok1.setPreferredSize(new Dimension(100, 30));
		sauvegarde.setPreferredSize(new Dimension(100, 30));
		table.setPreferredSize(new Dimension(100, 30));
		textdate.setPreferredSize(new Dimension(180, 30));
		JnumMat.setPreferredSize(new Dimension(250, 400));
		
		JPanel panel1 = new JPanel();
		
		panel1.setLayout(new FlowLayout());
		panel1.setPreferredSize(new Dimension(300, 250));
		fenetre1.getContentPane().add(panel1);
		panel1.add(search);
		panel1.add(textdate);
		panel1.add(ok);
		panel1.add(ok1);
		panel1.add(sauvegarde);
		panel1.add(table);
		panel1.add(JnumMat);
	}

	public JButton getSearch() {
		return search;
	}

	public void setSearch(JButton search) {
		this.search = search;
	}

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
	
	public JButton getSauvegarde() {
		return sauvegarde;
	}

	public void setSauvegarde(JButton sauvegarde) {
		this.sauvegarde = sauvegarde;
	}
	
	
	public JButton getTable() {
		return table;
	}

	public void setTable(JButton table) {
		this.table = table;
	}

	public static void main(String[]args){
		Socket s;
		try {
			s = new Socket("127.0.0.1",20012);
			ViewVehicleRepaired vvr=new ViewVehicleRepaired();
			Client.controller.Controller_vehicle_repaired c=new Client.controller.Controller_vehicle_repaired(s,vvr);
			c.control();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
}