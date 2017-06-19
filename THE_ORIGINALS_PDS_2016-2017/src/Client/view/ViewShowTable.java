package Client.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.*;

public class ViewShowTable extends JFrame{
	
	private JFrame fenetre;
	private Table t;
	private JTable table;
	public JScrollPane scrollp;
	
	public ViewShowTable(){
		
		/**fenetre=new JFrame("table");
		this.setSize(500, 500);
		FlowLayout fl=new FlowLayout();
		fenetre.getContentPane().setLayout(fl);
		fenetre.setSize(WIDTH, HEIGHT);
		fenetre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		fenetre.setVisible(true);**/
		
		super("table");
        setLayout(null);
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
		
		t= new Table();
        table = new JTable(t);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);

        scrollp = new JScrollPane(table);
        scrollp.setBounds(20, 20, 400, 400);
        this.getContentPane().add(scrollp);
	}

	public JFrame getFenetre() {
		return fenetre;
	}

	public void setFenetre(JFrame fenetre) {
		this.fenetre = fenetre;
	}

	public Table getT() {
		return t;
	}

	public void setT(Table t) {
		this.t = t;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	
	
	public static void main(String[] args){
		Socket s;
		try {
			s = new Socket("127.0.0.1",20012);
			ViewShowTable vst=new ViewShowTable();
			//Client.controller.Controller_vehicle_repaired c=new Client.controller.Controller_vehicle_repaired(s,vvr);
			//c.control();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
