package Client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.Scanner;
import javax.swing.JButton;

//import dto.Vehicle_warehouseDTO;
//import enumeration.EnumService;
//import ViewL.View_detail_vehicle;
//import ViewL.View_vehicle_repaired;
import Server.repository.ModelVehicle;
//import repositoryL.Model_Vehicle;
//import view.ViewDetailVehicle;
//import view.ViewVehicleRepaired;

public class Controller_vehicle_repaired {
	
	private Server.repository.ModelVehicle mv;
	private Client.view.ViewVehicleRepaired vvr;
	private Client.view.ViewDetailVehicle vdv;
	private ActionListener ac,ac1,ac2;
	private Controller_detail_vehicle cvd;
	Socket socket;
    BufferedReader in;
    PrintStream out;
	
	public Controller_vehicle_repaired(Socket socket, Client.view.ViewVehicleRepaired vvr){
		this.socket=socket;
		this.vvr=vvr;

	}
	
	public void control(){
		
		ac=new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				try {
					out = new PrintStream(socket.getOutputStream());
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				if((JButton)e.getSource()==vvr.getSearch()){
					vvr.getTextdate().setText("entrez la date de fin d'operation");
					
					ac1=new ActionListener(){
						
						public void actionPerformed(ActionEvent e){
							
							if((JButton)e.getSource()==vvr.getOk()){
								String date_end=vvr.getTextdate().getText();
								
								try {
									
									out.println(Client.enumeration.EnumService.SEARCHVEHICLE.name());
									out.flush();
									out.println(date_end);
									out.flush();
									in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
									int nombre=Integer.parseInt(in.readLine());
									
									String numMat="";
									for(int i=0; i<nombre; i++){
										numMat+=in.readLine()+"\n";
									}
									vvr.getJnumMat().setText(numMat);
									
									
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								
								//String res1=mv.selectAll(date_end);
								//vvr.getJnumMat().setText(res1);
								
								ac2=new ActionListener(){
									
									public void actionPerformed(ActionEvent e){
										
										if((JButton)e.getSource()==vvr.getOk1()){
											vdv=new Client.view.ViewDetailVehicle();
											cvd=new Controller_detail_vehicle(socket,vdv);
											cvd.control();
										}
									}
								};vvr.getOk1().addActionListener(ac2);
							}
						}
					};vvr.getOk().addActionListener(ac1);	
				}
			}
		};vvr.getSearch().addActionListener(ac);

	}
	

}
	


