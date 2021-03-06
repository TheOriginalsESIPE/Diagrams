package Client.controller;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import javax.swing.JButton;




public class Controller_vehicle_repaired {
	private Client.view.ViewVehicleRepaired vvr;
	private Client.view.ViewDetailVehicle vdv;
	private ActionListener ac,ac1,ac2,ac3,ac4;
	private Controller_detail_vehicle cvd;
	private Client.view.ViewShowTable vst;
	private ControllerShowTable cst;
	private Socket socket;
    private BufferedReader in;
    private PrintStream out;
    private File f;
	
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
					vvr.getTextdate().setText("entrez la date d'aujourd'hui");
					
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
		
ac3=new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				
				if((JButton)e.getSource()==vvr.getSauvegarde()){
					f=new File("C:/Users/LM/Desktop/gitrep1/Master/THE_ORIGINALS_PDS_2016-2017/src/Client/properties/Sauvegardes");
					
					try {
						Desktop.getDesktop().open(f);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		};vvr.getSauvegarde().addActionListener(ac3);

		ac4=new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				
				if((JButton)e.getSource()==vvr.getTable()){
					vst=new Client.view.ViewShowTable();
					cst=new ControllerShowTable(socket,vst);
					cst.control();
				}
			}
		};vvr.getTable().addActionListener(ac4);

	}
	

}


