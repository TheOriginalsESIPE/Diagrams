package Client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.StringReader;
import java.net.Socket;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.text.InternationalFormatter;

import Client.dto.BreakdownDTO;
import Client.dto.OperationDTO;
import Client.dto.ParkingDTO;
import Client.dto.RepairerDTO;
import Client.dto.VehicleDTO;
import Client.enumeration.EnumOperation;
import Client.enumeration.EnumService;
import Server.repository.ZDialogVehicleInfoRepository;
import Client.serialization.DeserializationGson;
import Client.serialization.Serialization;
import Client.view.InfoSysteme;
import Client.view.ZDialogVehicleInfo;
import Client.view.numMatError;

/*
 * @auteur Bakary DEMBELE
 * 
 */
public class ControllerZDialogVehicleInfo {
	private ZDialogVehicleInfo viewZDialogVI;
	private ZDialogVehicleInfoRepository zDialogVR;
	private ActionListener 	breckdownActionListener, vehicleFindActionListener, listbreckdownActionListener, 
							listMotifActionListener, visitingMotifActionListener, saveVehicleActionListener, cancelActionListener;
	
	private Socket socket = null;
	private BufferedReader in = null;
	private PrintStream out = null;
	
	private LocalTime tempsReparation = LocalTime.of(0, 0, 0);
	private Vector<BreakdownDTO> listNameBreakdown;
	//private Vector<VisitingMotifDTO> listVisitingMotif;
	private ArrayList<String> listBreakdown = new ArrayList<>(), listMotifvisiting = new ArrayList<>();
	
	public ControllerZDialogVehicleInfo(ZDialogVehicleInfo viewZDialogVI, Socket socket){
		this.viewZDialogVI = viewZDialogVI;
		this.socket = socket;
	}
	
	public void myControl(){
		
		/*visitingMotifActionListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
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
						listNameBreakdown = deserial.deserialBreakdownDTO(result);
						
						for(BreakdownDTO bDTO : listNameBreakdown){
							viewZDialogVI.getVisitingMotifText().addItem(bDTO.getName());
						}
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
		};
		viewZDialogVI.getButtonVisitingMotif().addActionListener(visitingMotifActionListener);*/
		
		breckdownActionListener = new ActionListener() {
			
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
						listNameBreakdown = deserial.deserialBreakdownDTO(result);
						
						for(BreakdownDTO bDTO : listNameBreakdown){
							viewZDialogVI.getBreakdownText().addItem(bDTO.getName());
						}
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		};
		viewZDialogVI.getButtonDownloadBreakdown().addActionListener(breckdownActionListener);
			
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
						if(vehicleInfo.size() != 0){
						for(VehicleDTO dto : vehicleInfo){
							viewZDialogVI.getModelText().setText(dto.getModel());
							viewZDialogVI.getBrandText().setText(dto.getMark());
							viewZDialogVI.getVehicleTypeText().setText(dto.getVehicle_type());
						}
						}else{
							new numMatError();
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
				
				
				
				saveVehicleActionListener = new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						if((e.getSource() == viewZDialogVI.getMainButton1())){	
							
						myMainButton();
							}
						}
						
					};
					viewZDialogVI.getMainButton1().addActionListener(saveVehicleActionListener);
					
					
					
					
					
					cancelActionListener = new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							
							if((e.getSource() == viewZDialogVI.getMainButton2())){
								
								viewZDialogVI.setVisible(false);
							
								}
							}
							
						};
						viewZDialogVI.getMainButton2().addActionListener(cancelActionListener);
				
	}
	
	
	
	public void myMainButton(){
		
		//Recover numMat of vehicle
		String numMat = viewZDialogVI.getRegisterText().getText();
		
		
		//Recover the breakdown list
		String breakdownList = viewZDialogVI.getListBreakdown().getText();
		
		//Recover the visiting motif list
		String listMotif = viewZDialogVI.getListVisitingMotif().getText();
		
		String RepLogin = "henri";
		ArrayList<Integer> lPark = new ArrayList<>();
		int cpt = 0;
		
		BufferedReader inbreak = new BufferedReader(new StringReader(breakdownList));
		BufferedReader inMotif = new BufferedReader(new StringReader(listMotif));
		
			String line = null;
			try {
				line = inbreak.readLine();
				while(line != null){
					listBreakdown.add(line);
					line = inbreak.readLine();
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintStream(socket.getOutputStream());
				
				out.println(EnumService.PARKINGDOWLOAD.name());
				out.flush();
				
				String result = in.readLine();
				DeserializationGson deserial = new DeserializationGson();
				Vector<ParkingDTO> listParking = deserial.deserialParkingDTO(result);
				
				for(int i = 0; i < listParking.size(); i++){
					System.out.println();
					lPark.add(listParking.get(i).getNumPlace());
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			listBreakdown.remove(0);
			int temps = 0, t1 = 0, t2 = 0, t3 = 0, h = 0, m = 0, s = 0;
			
			for(int i = 0; i < listNameBreakdown.size(); i++){
				for(int j = 0; j < listBreakdown.size(); j++){
					if(listNameBreakdown.get(i).getName().equals(listBreakdown.get(j))){
						LocalTime lt = listNameBreakdown.get(i).getDuree().toLocalTime();
						int id_b = listNameBreakdown.get(i).getId_breakdown();
						
						try {
							in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
							out = new PrintStream(socket.getOutputStream());
							
							BreakdownDTO breakdownDTO = new BreakdownDTO();
							VehicleDTO vehicleDTO = new VehicleDTO();
							RepairerDTO repairerDTO = new RepairerDTO();
							vehicleDTO.setNumMat(numMat);
							breakdownDTO.setId_breakdown(id_b);
							repairerDTO.setLogin(RepLogin);
							
							out.println(EnumService.SAVEVEHICLE.name());
							out.flush();
							out.println(id_b);
							out.flush();
							out.println(numMat);
							out.flush();
							out.println(RepLogin);
							out.flush();
							
							
							String result = in.readLine();
							
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						h += lt.getHour();
						
						m += lt.getMinute();
						
						s += lt.getSecond();
					
				
						viewZDialogVI.getListVisitingMotif().append(listBreakdown.get(j)+"\n");
						viewZDialogVI.getListVisitingMotif().append(lt+"\n");
						
					
						if(j == listBreakdown.size()-1){
							cpt++;
						}
					}
				}
			}
			
			viewZDialogVI.getListVisitingMotif().append(tempsReparation+"\n");
			String d = h + " Heures " + m + " minutes " + s + " secondes\n";
			
			new InfoSysteme(String.valueOf(lPark.get(cpt)), d);
		
	}

}

