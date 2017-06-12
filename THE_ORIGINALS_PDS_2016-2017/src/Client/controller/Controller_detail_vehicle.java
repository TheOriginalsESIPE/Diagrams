package Client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import javax.swing.JButton;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Client.dto.VehicleDTO;
import Client.enumeration.EnumService;
import Client.view.ViewDetailVehicle;
import Client.view.ViewRelocate;
import Server.repository.ModelVehicle;

/**import dto.VehicleDTO;
import dto.Vehicle_warehouseDTO;
import enumeration.EnumDTO;
import enumeration.EnumOperation;
import enumeration.EnumService;
//import ViewL.View_detail_vehicle;
//import ViewL.View_relocate;
import repository.ModelVehicle;
import serialization.Deserialization;
import serialization.Serialization;
//import repositoryL.Model_Vehicle;
import view.ViewDetailVehicle;
import view.ViewRelocate;**/

public class Controller_detail_vehicle {
	
	private ViewDetailVehicle vdv;
	private ViewRelocate vr;
	private Controller_relocate cr;
	private ModelVehicle mv;
	private ActionListener ac,ac1;
	Socket socket;
    BufferedReader in;
    PrintStream out;
	
	public Controller_detail_vehicle(Socket socket, ViewDetailVehicle vdv){
		this.socket=socket;
		this.vdv=vdv;
	}
	
	public void control(){
		
		ac1=new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				
				try{
					out = new PrintStream(socket.getOutputStream());
				} catch(IOException e1){}
				
				if((JButton)e.getSource()==vdv.getOk()){
					String numMat=vdv.getJnumMat().getText();
					
					try{
						 in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						
						out.println(EnumService.SEARCHInfoVEHICLE.name());
						out.flush();
						out.println(numMat);
						out.flush();
						
						String reponse=in.readLine();
						vdv.getInfovehicle().setText(reponse);
						
						Client.serialization.Deserialization d=new Client.serialization.Deserialization();
						JSONObject j=d.deserialGeneric(reponse);
						VehicleDTO v=new VehicleDTO();
						JSONArray jsonArray = (JSONArray) d.deserialObject(j, v.getClass().getSimpleName());
						j = (JSONObject) jsonArray.get(0);
						
						v.setNumMat((String) j.get("numMat"));
			            v.setModel((String) j.get("model"));
			            v.setMark((String) j.get("mark"));
			            v.setVehicle_type((String) j.get("vehicle_type"));
			            v.setStatus((String) j.get("status"));
			            v.setNumPlace(Integer.parseInt((String)j.get("numPlace")));
			            
						vdv.getInfovehicle().setText("numMat: "+v.getNumMat()+"\r\n"+"model: "+v.getModel()+"\r\n"+"mark: "+v.getMark()+"\r\n"+"vehicle_type: "+v.getVehicle_type()+"\r\n"+"status: "+v.getStatus()+"\r\n"+"numPlace: "+v.getNumPlace());
						
					} catch(IOException e2){}
					
					/*String result=mv.select(numMat);
					vdv.getInfovehicle().setText(result);*/
					ac=new ActionListener(){
						
						public void actionPerformed(ActionEvent e){
							
							if((JButton)e.getSource()==vdv.getRelocaliser()){
								vr=new ViewRelocate();
								cr=new Controller_relocate(socket, vr);
								cr.control();
							}
						}
					};vdv.getRelocaliser().addActionListener(ac);
					
				}
			}
		};vdv.getOk().addActionListener(ac1);
	}
}


