package Client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import javax.swing.JButton;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import Client.dto.VehicleDTOL;
import Client.enumeration.EnumService;
import Client.view.ViewDetailVehicle;
import Client.view.ViewRelocate;


public class Controller_detail_vehicle {
	
	private ViewDetailVehicle vdv;
	private ViewRelocate vr;
	private Controller_relocate cr;
	private ActionListener ac,ac1;
	private Socket socket;
    private BufferedReader in;
    private PrintStream out;
	
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
						VehicleDTOL v=new VehicleDTOL();
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


