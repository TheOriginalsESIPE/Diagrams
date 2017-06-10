package server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;

import enumeration.EnumService;
//import ViewL.View_relocate;
import repository.ModelVehicle;
//import repositoryL.Model_Vehicle;
import view.ViewRelocate;

public class Controller_relocate {
	
	private ViewRelocate vr; 
	private ModelVehicle mv;
	private ActionListener ac,ac1;
	BufferedReader in;
    PrintStream out;
	private Socket socket;
	public Controller_relocate(Socket socket, ViewRelocate vr){
		this.socket =socket;
		this.vr=vr;
	}
	
	 public String DateAujourdhui(String date){
			
		 SimpleDateFormat formater=null;
		 Date aujourdhui = new Date();
		 formater = new SimpleDateFormat("'le' dd MMMM yyyy 'a' hh:mm:ss");
		 String date_end=formater.format(aujourdhui);
		 return date_end;
	 }
	
	public void control(){
		
		ac=new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				
				try{
					out = new PrintStream(socket.getOutputStream());
				} catch(IOException e1){}
				
				if((JButton)e.getSource()==vr.getOK()){
					String answer1=vr.getTxtnumMat().getText();
					String answer2=vr.getId().getText();
					int answer2bis=Integer.parseInt(answer2);
					String[] res = new String[4];
					//String date = null;
					try{
						in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						out.println(EnumService.VEHICLERELOCATE.name());
						out.flush();
						out.println(answer1);
						out.flush();
						out.println(answer2bis);
						out.flush();
						
						String reponse=in.readLine();
						res = reponse.split(" ");
						// reponse = "AAA 2".split(" ")  
						// res = {"AAA", "2"} res[0] = "AAA"
					} catch(IOException er){}
					
					for(String ele : res){System.out.println(ele);}
					vr.getInfo().setText(res[0]+"vehicule relocalise "+DateAujourdhui(answer2)+"\r\n"+res[0]+" date modifiee"+"\r\n"+res[0]+" vehicule mis en circulation");
					vr.getInfo2().setText("numero d'immatriculation: "+answer1+"\r\n"+"id_warehouse: "+res[2]+"\r\n"+"adresse:"+res[1]);
				}
			}

		};vr.getOK().addActionListener(ac);
	}
}
