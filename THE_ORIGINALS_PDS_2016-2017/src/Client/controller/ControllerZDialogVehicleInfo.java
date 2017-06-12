package Client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.StringReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

import Client.serialization.DeserializationGson;
import Client.serialization.Serialization;
import Server.dto.BreakdownDTO;
import Server.dto.VehicleDTO;
import Client.enumeration.EnumOperation;
import Client.enumeration.EnumService;
import Server.repository.ZDialogVehicleInfoRepository;
import Client.view.ZDialogVehicleInfo;

public class ControllerZDialogVehicleInfo {
	private ZDialogVehicleInfo viewZDialogVI;
	private ZDialogVehicleInfoRepository zDialogVR;
	private ActionListener breckdownActionListener, vehicleFindActionListener, listbreckdownActionListener, listMotifActionListener, visitingMotifActionListener, saveVehicleActionListener;
	
	private Socket socket = null;
	private BufferedReader in = null;
	private PrintStream out = null;
	
	public ControllerZDialogVehicleInfo(ZDialogVehicleInfo viewZDialogVI, Socket socket){
		this.viewZDialogVI = viewZDialogVI;
		this.socket = socket;
	}
	
	public void myControl(){
		
		breckdownActionListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource() == viewZDialogVI.getButtonVisitingMotif()){
					try {
						in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						out = new PrintStream(socket.getOutputStream());
						
						BreakdownDTO breakSearch = new BreakdownDTO();
						
						Serialization serial = new Serialization();
						
						out.println(EnumService.BREAKDOWNLOAD.name());
						out.flush();
						out.println(serial.serialToStr(serial.serialGeneric(EnumOperation.SEARCH.getIndex(), "BreakdownDTO", breakSearch)));
						out.flush();
						
						
						String result = in.readLine();
						DeserializationGson deserial = new DeserializationGson();
						Vector<BreakdownDTO> listNameBreakdown = deserial.deserialBreakdownDTO(result);
						
						for(BreakdownDTO bDTO : listNameBreakdown){
							viewZDialogVI.getVisitingMotifText().addItem(bDTO.getName());
						//	System.out.println(viewZDialogVI.getVisitingMotifText().addItem(bDTO.getName()));
						}
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		};
		viewZDialogVI.getButtonVisitingMotif().addActionListener(breckdownActionListener);
		
		vehicleFindActionListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource() == viewZDialogVI.getButtonFindVehicle()){
					
					String vehicleNumMat = viewZDialogVI.getRegisterText().getText();
					System.out.println("le getText est "+vehicleNumMat);

					try{
						in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						out = new PrintStream(socket.getOutputStream());
					
						VehicleDTO vehicleDTO = new VehicleDTO();
						vehicleDTO.setNumMat(vehicleNumMat);
						Serialization serial = new Serialization();
						out.println(EnumService.VEHICLEDOWNLOAD.name());
						out.flush();
						out.println(serial.serialToStr(serial.serialGeneric(EnumOperation.SEARCH.getIndex(), "VehicleDTO", vehicleDTO)));
						out.flush();
						System.out.println(serial.serialToStr(serial.serialGeneric(EnumOperation.SEARCH.getIndex(), "VehicleDTO", vehicleDTO)));
						System.out.println(vehicleDTO.getNumMat());
						
						String result = in.readLine();
						DeserializationGson deserial = new DeserializationGson();
						
						Vector<VehicleDTO> vehicleInfo = deserial.deserialVehicleDTO(result);
						System.out.println("Info re�us"+vehicleInfo);
						//Iterator<VehicleDTO> it = vehicleInfo.iterator();
						for(VehicleDTO dto : vehicleInfo){
							viewZDialogVI.getModelText().setText(dto.getModel());
							viewZDialogVI.getBrandText().setText(dto.getMark());
							viewZDialogVI.getVehicleTypeText().setText(dto.getVehicle_type());
						}
						
					}catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		};
		viewZDialogVI.getButtonFindVehicle().addActionListener(vehicleFindActionListener);
		
		listbreckdownActionListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				if((e.getSource() == viewZDialogVI.getButtonAddBreakdown())){
					
					String str = (String) viewZDialogVI.getBreakdownText().getSelectedItem();				
					viewZDialogVI.getListBreakdown().append(str+"\n");
					
					}
				}
				
			//}
				
			};
			viewZDialogVI.getButtonAddBreakdown().addActionListener(listbreckdownActionListener);
			
			listMotifActionListener = new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					if((e.getSource() == viewZDialogVI.getButtonAddVisitingMotif())){
						
						String str = (String) viewZDialogVI.getVisitingMotifText().getSelectedItem();
						viewZDialogVI.getListVisitingMotif().append(str+"\n");
					
						}
					}
					
				//}
					
				};
				viewZDialogVI.getButtonAddVisitingMotif().addActionListener(listMotifActionListener);
				
				
				visitingMotifActionListener = new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if(e.getSource() == viewZDialogVI.getButtonDownloadBreakdown()){
							try {
								in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
								out = new PrintStream(socket.getOutputStream());
								
								BreakdownDTO breakSearch = new BreakdownDTO();
								
								Serialization serial = new Serialization();
								
								out.println(EnumService.BREAKDOWNLOAD.name());
								out.flush();
								out.println(serial.serialToStr(serial.serialGeneric(EnumOperation.SEARCH.getIndex(), "BreakdownDTO", breakSearch)));
								out.flush();
								
								
								String result = in.readLine();
								DeserializationGson deserial = new DeserializationGson();
								Vector<BreakdownDTO> listNameBreakdown = deserial.deserialBreakdownDTO(result);
								
								for(BreakdownDTO bDTO : listNameBreakdown){
									viewZDialogVI.getBreakdownText().addItem(bDTO.getName());
								//	System.out.println(viewZDialogVI.getVisitingMotifText().addItem(bDTO.getName()));
								}
								
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						
					}
				};
				viewZDialogVI.getButtonDownloadBreakdown().addActionListener(visitingMotifActionListener);
				
				saveVehicleActionListener = new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						if((e.getSource() == viewZDialogVI.getMainButton1())){
							
							String numMat = viewZDialogVI.getRegisterText().getText();
							
							String listMotif = viewZDialogVI.getListVisitingMotif().getText();
							BufferedReader inn = new BufferedReader(new StringReader(listMotif));
							String str = null;
							try {
								str = inn.readLine();
								while(str != null){
									ArrayList<String> listVisitingMotif = new ArrayList<>();
									listVisitingMotif.add(str);
									str = inn.readLine();
								}
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							String breakdownList = viewZDialogVI.getListBreakdown().getText();
							BufferedReader in = new BufferedReader(new StringReader(breakdownList));
							String line = null;
							try {
								line = in.readLine();
								while(line != null){
									ArrayList<String> listBreakdown = new ArrayList<>();
									listBreakdown.add(line);
									line = in.readLine();
								}
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						
						
							}
						}
						
					};
					viewZDialogVI.getMainButton1().addActionListener(saveVehicleActionListener);
				
	}
	

}
