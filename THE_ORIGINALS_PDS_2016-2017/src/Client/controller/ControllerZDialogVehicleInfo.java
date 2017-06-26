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
import Client.dto.VisitingMotifDTO;
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
	private ActionListener 	breckdownActionListener, vehicleFindActionListener, listbreckdownActionListener, buttonError1ActionListener, buttonError2ActionListener, 
							listMotifActionListener, visitingMotifActionListener, saveVehicleActionListener, cancelActionListener, addAgainVehicleActionListener;
	
	private Socket socket = null;
	private BufferedReader in = null;
	private PrintStream out = null;
	
	private LocalTime tempsReparation = LocalTime.of(0, 0, 0);
	private Vector<BreakdownDTO> listNameBreakdown = new Vector<>();
	private Vector<VisitingMotifDTO> listVisitingMotif;
	private ArrayList<String> listBreakdown = new ArrayList<>(), listMotifvisiting = new ArrayList<>();
	
	public ControllerZDialogVehicleInfo(ZDialogVehicleInfo viewZDialogVI, Socket socket){
		this.viewZDialogVI = viewZDialogVI;
		this.socket = socket;
	}
	
	public void myControl(){
		
		visitingMotifActionListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
						in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						out = new PrintStream(socket.getOutputStream());
						
						VisitingMotifDTO motifSearch = new VisitingMotifDTO();
						
						Serialization serial = new Serialization();
						
						out.println(EnumService.MOTIFDOWNLOAD.name());
						out.flush();
						out.println(serial.serialToStr(serial.serialGeneric(EnumOperation.SEARCH.getIndex(), "VisitingMotifDTO", motifSearch)));
						out.flush();
						
						
						String result = in.readLine();
						DeserializationGson deserial = new DeserializationGson();
						listVisitingMotif = deserial.deserialVisitingMotif(result);
						
						for(VisitingMotifDTO vDTO : listVisitingMotif){
							viewZDialogVI.getVisitingMotifText().addItem(vDTO.getName());
						}
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
		};
		viewZDialogVI.getButtonVisitingMotif().addActionListener(visitingMotifActionListener);
		
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
						System.out.println("Info reï¿½us"+vehicleInfo);
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
				
					String string1 = "Info vehicule :";
					String string2 = "Numéro matricule :            " + viewZDialogVI.getRegisterText().getText();
					String string3 = "La marque :                         " + viewZDialogVI.getBrandText().getText();
					String string4 = "Le modele :                          " + viewZDialogVI.getModelText().getText();
					String string5 = "Le type :                                " + viewZDialogVI.getVehicleTypeText().getText();
				    
					viewZDialogVI.getSummaryVehilce1().setText(string1);
					viewZDialogVI.getSummaryVehilce2().setText(string2);
					viewZDialogVI.getSummaryVehilce3().setText(string3);
					viewZDialogVI.getSummaryVehilce4().setText(string4);
					viewZDialogVI.getSummaryVehilce5().setText(string5);
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
						
						buttonError1ActionListener = new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								
								if((e.getSource() == viewZDialogVI.getButtonError1())){
									
									viewZDialogVI.getListVisitingMotif().setText(null);
									viewZDialogVI.getListVisitingMotif().setText("LISTE DE MOTIFS: \n");
								
									}
								}
								
							};
							viewZDialogVI.getButtonError1().addActionListener(buttonError1ActionListener);
							
							buttonError2ActionListener = new ActionListener() {
								
								@Override
								public void actionPerformed(ActionEvent e) {
									
									if((e.getSource() == viewZDialogVI.getButtonError2())){
										
										viewZDialogVI.getListBreakdown().setText(null);
										viewZDialogVI.getListBreakdown().setText("LISTE DE PANNES: \n");
									
										}
									}
									
								};
								viewZDialogVI.getButtonError2().addActionListener(buttonError2ActionListener);
								
								
								addAgainVehicleActionListener = new ActionListener() {
									
									@Override
									public void actionPerformed(ActionEvent e) {
										
										if((e.getSource() == viewZDialogVI.getAddAgainVehicle())){
											
											viewZDialogVI.getRegisterText().setText(null);
											viewZDialogVI.getModelText().setText(null);
											viewZDialogVI.getModelText().setText("modele");
											viewZDialogVI.getBrandText().setText(null);
											viewZDialogVI.getBrandText().setText("marque");
											viewZDialogVI.getVehicleTypeText().setText(null);
											viewZDialogVI.getVehicleTypeText().setText("type vehicule");
											viewZDialogVI.getListVisitingMotif().setText(null);
											viewZDialogVI.getListVisitingMotif().setText("LISTE DE MOTIFS: \n");
											viewZDialogVI.getListBreakdown().setText(null);
											viewZDialogVI.getListBreakdown().setText("LISTE DE PANNES: \n");
										
											}
										}
										
									};
									viewZDialogVI.getAddAgainVehicle().addActionListener(addAgainVehicleActionListener);
				
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
			
			String line1 = null;
			try {
				line1 = inMotif.readLine();
				while(line1 != null){
					listMotifvisiting.add(line1);
					line1 = inMotif.readLine();
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
			listMotifvisiting.remove(0);
			int h = 0, m = 0, s = 0;
			
			for(int i = 0; i < listNameBreakdown.size(); i++){
				for(int j = 0; j < listBreakdown.size(); j++){
					if(listNameBreakdown.get(i).getName().equals(listBreakdown.get(j))){
						LocalTime lt = listNameBreakdown.get(i).getDuree().toLocalTime();
						int id_b = listNameBreakdown.get(i).getId_breakdown();
						
						try {
							in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
							out = new PrintStream(socket.getOutputStream());
							
							/*BreakdownDTO breakdownDTO = new BreakdownDTO();
							VehicleDTO vehicleDTO = new VehicleDTO();
							RepairerDTO repairerDTO = new RepairerDTO();
							vehicleDTO.setNumMat(numMat);
							breakdownDTO.setId_breakdown(id_b);
							repairerDTO.setLogin(RepLogin);*/
							
							out.println(EnumService.SAVEVEHICLE.name());
							out.flush();
							out.println("Un");
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
					}
				}
			}
			
			for(int t = 0; t < listMotifvisiting.size(); t++){
				String motif = listMotifvisiting.get(t);
				
				try{
					in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					out = new PrintStream(socket.getOutputStream());
					
					/*VisitingMotifDTO visitingMotifDTO = new VisitingMotifDTO();
					VehicleDTO vehicleDTO = new VehicleDTO();
					
					visitingMotifDTO.setName(motif);
					vehicleDTO.setNumMat(numMat);*/
					
					out.println(EnumService.SAVEVEHICLE.name());
					out.flush();
					out.println("Deux");
					out.flush();
					out.println(motif);
					out.flush();
					out.println(numMat);
					out.flush();
					
					String res = in.readLine();
					
				}catch(IOException ioe){
					ioe.printStackTrace();
				}
			}
			
			
			
			try {
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintStream(socket.getOutputStream());
				
				int numPark = lPark.get(0);
				String numP = String.valueOf(numPark);
				
				out.println(EnumService.SAVEVEHICLE.name());
				out.flush();
				out.println("Trois");
				out.flush();
				out.println(numMat);
				out.flush();
				out.println(numP);
				out.flush();
				
				String res = in.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String d = h + " Heures " + m + " minutes " + s + " secondes\n";
			
			new InfoSysteme(String.valueOf(lPark.get(0)), d);
		
	}

}

