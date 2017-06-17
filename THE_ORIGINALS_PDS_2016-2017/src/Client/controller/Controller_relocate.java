package Client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;

import Client.enumeration.EnumService;
import Server.repository.ModelVehicle;
//import view.ViewRelocate;

public class Controller_relocate {
	
	private Client.view.ViewRelocate vr; 
	//private Server.repository.ModelVehicle mv;
	private ActionListener ac,ac1;
	BufferedReader in,in1;
    PrintStream out;
    BufferedWriter out1;
    File f;
	private Socket socket;
	public Controller_relocate(Socket socket, Client.view.ViewRelocate vr){
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
					f=new File("C:/Users/LM/Desktop/gitrep1/Master/THE_ORIGINALS_PDS_2016-2017/src/Client/properties/Sauvegardes");
					out1=new BufferedWriter(new FileWriter(f));
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
						//in1=new BufferedReader(new FileReader(f));
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
						
						out1.write(res[0]+" vehicule a été relocalise "+DateAujourdhui(answer2)+"et il a été mis en circulation à la meme date.");
						out1.flush();
						out1.write("\r\n"+"le numero d'immatriculation correspondant est: "+answer1+" avec un id_warehouse de: "+answer2+" et son adresse: "+res[1]+"\r\n");
						out1.flush();
						
					} catch(IOException er){}
					
					for(String ele : res){System.out.println(ele);}
					vr.getInfo().setText(res[0]+" vehicule relocalise "+DateAujourdhui(answer2)+"\r\n"+res[0]+" date modifiee"+"\r\n"+res[0]+" vehicule mis en circulation");
					vr.getInfo2().setText("numero d'immatriculation: "+answer1+"\r\n"+"id_warehouse: "+answer2+"\r\n"+"adresse:"+res[1]);
				}
			}

		};vr.getOK().addActionListener(ac);
	}
}