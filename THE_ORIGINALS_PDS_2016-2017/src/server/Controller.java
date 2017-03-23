package server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.text.View;

import repository.ModelVehicle;
import view.ViewInsert;
import view.View;

public class Controller{
	
	private ViewInsert v1;
	
	private ControllerInsert c1;
	private View v;
	private ModelVehicle mv;
	private ActionListener ac;
	private ActionListener ac1;
	private ActionListener ac2;
	private ActionListener ac3;
	private ActionListener ac4;
	
	private ActionListener ac5;
	private ActionListener ac6;
	private int cpt=0;
	
	public Controller(ModelVehicle mv, View v){
		this.mv=mv;
		this.v=v;
		
		
		
	}
	public void control(){
		ac = new ActionListener(){
	public void actionPerformed(ActionEvent e) {
		
		//SELECTION
	if((JButton)e.getSource()== v.getBtnSelect()){
		v.getTxtF().setText(null);
		v.getTxtU().setText(null);
		v.getTxtD().setText(null);
		v.getTxtA().setText("Entrez le numero d'immatriculation du vehicule a selectionner");
			
			ac2 = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
				 if((JButton)e.getSource()== v.getBtnOK()){
						String answer = v.getTxtF().getText();
						
						try {
						String res = mv.select(answer);
						v.getTxtA().setText(res);
						v.getTxtU().setVisible(false);
						v.getTxtD().setVisible(false);
					
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				}}
			
			
				 
			
		

	
};v.getBtnOK().addActionListener(ac2);
	}}
		};v.getBtnSelect().addActionListener(ac); 


		//INSERTION
ac3 = new ActionListener(){
	public void actionPerformed(ActionEvent e) {
		if((JButton)e.getSource()== v.getBtnInsert()){
			v.getTxtU().setText(null);
			v.getTxtD().setText(null);
			v1 = new VueInsert();
			c1 = new ControllerInsert(mv, v1);
			c1.control();
		}}};v.getBtnInsert().addActionListener(ac3);
		
		//MODIFICATION
		ac1 = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
		if((JButton)e.getSource()== v.getBtnUpdate()){
			
				//v.getTxtA().setText("Entrez le numero d'immatriculation du vehicule a modifier");
				
				v.getTxtU().setText(null);
				v.getTxt1().setText(null);
				v.getTxtD().setText(null);
				ac5 = new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					String answer="";
				 if((JButton)e.getSource()== v.getBtnOK1()){
						answer = v.getTxt1().getText();
						
						int result=0;
						try {
							
							result = mv.update(answer);
							v.getTxtU().setVisible(true);
							v.getTxtA().setText("");
							v.getTxtU().setText(result+"ligne modifiee");
							
							v.getTxtD().setVisible(false);
							//v.getTxtA().setText(result+"ligne modifiee");
							
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				}
				}
				};v.getBtnOK1().addActionListener(ac5);
				
			
		}}};v.getBtnUpdate().addActionListener(ac1);
		
		//SUPPRESSION
		ac4 = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
		if((JButton)e.getSource()== v.getBtnDelete()){
			
				//v.getTxtA().setText("Entrez le numero d'immatricullation du vehicule a supprimer");
				
				v.getTxtU().setText(null);
				v.getTxt2().setText(null);
				v.getTxtD().setText(null);
				ac6 = new ActionListener(){
				public void actionPerformed(ActionEvent e) {
				 if((JButton)e.getSource()== v.getBtnOK2()){
						String answer = v.getTxt2().getText();
						
						int result=0;
						try {
							result = mv.delete(answer);
							v.getTxtD().setVisible(true);
							v.getTxtA().setText("");
							v.getTxtD().setText(result+"ligne supprimee");
							v.getTxtU().setVisible(false);
							
							//v.getTxtA().setText(result+"ligne supprimee");
							
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						
				 }
				}
				};v.getBtnOK2().addActionListener(ac6);
			
		
		}}};v.getBtnDelete().addActionListener(ac4);
		
		
		
	
	}
	
	
	
		}

	
