package Client.view;

import java.awt.*;
import javax.swing.*;

public class ViewDetailVehicle extends JFrame {
	
	public static final int WIDTH=350;
	public static final int HEIGHT=400;
	
	private JFrame fenetre;
	private JButton relocaliser,ok;
	private JLabel vehicle;
	private JTextField JnumMat;
	private JTextArea infovehicle;
	//private JScrollPane js;
	
	public ViewDetailVehicle(){
		
		fenetre=new JFrame("information of vehicle");
		
		BorderLayout bo=new BorderLayout();
		
		
		fenetre.getContentPane().setLayout(bo);
		fenetre.setSize(WIDTH, HEIGHT);
		fenetre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		fenetre.setVisible(true);
		
		relocaliser=new JButton("relocate");
		ok=new JButton("OK");
		
		infovehicle=new JTextArea("");
		//js=new JScrollPane(infovehicle);
		JnumMat=new JTextField("");
		
		relocaliser.setPreferredSize(new Dimension(150, 50));
		ok.setPreferredSize(new Dimension(150, 50));
		infovehicle.setPreferredSize(new Dimension(100, 120));
		JnumMat.setPreferredSize(new Dimension(100, 50));
		//js.setPreferredSize(new Dimension(200, 120));
		fenetre.getContentPane().add(JnumMat, BorderLayout.NORTH);
		
		JPanel panel=new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setPreferredSize(new Dimension(250, 300));
		fenetre.getContentPane().add(panel);
		panel.add(ok, BorderLayout.NORTH);
		panel.add(infovehicle, BorderLayout.CENTER);
		panel.add(relocaliser, BorderLayout.SOUTH);
		//panel.add(infovehicle);
		//panel.add(js);
		
	}

	public JFrame getFenetre() {
		return fenetre;
	}

	public void setFenetre(JFrame fenetre) {
		this.fenetre = fenetre;
	}

	public JButton getRelocaliser() {
		return relocaliser;
	}

	public void setRelocaliser(JButton relocaliser) {
		this.relocaliser = relocaliser;
	}

	public JLabel getVehicle() {
		return vehicle;
	}

	public void setVehicle(JLabel vehicle) {
		this.vehicle = vehicle;
	}

	public JTextArea getInfovehicle() {
		return infovehicle;
	}

	public void setInfovehicle(JTextArea infovehicle) {
		this.infovehicle = infovehicle;
	}

	public JButton getOk() {
		return ok;
	}

	public void setOk(JButton ok) {
		this.ok = ok;
	}

	public JTextField getJnumMat() {
		return JnumMat;
	}

	public void setJnumMat(JTextField jnumMat) {
		JnumMat = jnumMat;
	}

	/**public JScrollPane getJs() {
		return js;
	}

	public void setJs(JScrollPane js) {
		this.js = js;
	}**/
	
	
}

