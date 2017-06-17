package Client.controller;


import java.awt.event.ActionListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.stream.Stream;

import javax.swing.JButton;
import javax.swing.JTextArea;

import Client.serialization.*;
import Client.view.Erreur;
import Client.view.ViewPersonel;
import Client.dto.OperationDTOAli;
import Client.dto.VehicleDTOAli;
import Client.enumeration.EnumOperation;


public class ControllerPersonel {
	Socket socket;
	BufferedReader in;
	PrintStream  out;
	ViewPersonel v;
	private ActionListener ac2,ac3,ac4;
	public ControllerPersonel(ViewPersonel v, Socket socket){
		this.v=v;
		this.socket = socket;
        System.out.println("controller principal");
	}
	public void control(){       
		ac2 = e1 -> {
                     if((JButton) e1.getSource()== v.getBtnChercher()){
                         //answer is the reference of the piece
                            String matricule = v.getTxtM().getText();
                            try {
                                System.out.println("select");
                                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                                out = new PrintStream(socket.getOutputStream());
                                VehicleDTOAli VehiculeDto = new VehicleDTOAli();
                                VehiculeDto.setNumMat(matricule);
                                Serialization serial = new Serialization();
  //depant serveur modifiere Serveur                            
                                out.println("personelMatricule");
                                out.flush();
                                out.println(serial.serialToStr(serial.serialGeneric(EnumOperation.SEARCH.getIndex(), "VehiculeDTO", VehiculeDto)));
                                out.flush();
                                System.out.println(serial.serialToStr(serial.serialGeneric(EnumOperation.SEARCH.getIndex(), "VehiculeDTO", VehiculeDto)));
                                
                                String res = in.readLine();
                                
                                JTextArea z =v.getTxtA();
                                if (res.equals("")){
                                	Erreur erreur = new Erreur();
                                	erreur.getJlabel().setText(" le vehicule n'est pas en maintenance");
                                	}
                                else{ z.setText(res);}

                            } catch (IOException error){
                                error.printStackTrace();
                            }
                    }};v.getBtnChercher().addActionListener(ac2);
        
	ac3 = e2 ->{
		         if((JButton) e2.getSource()== v.getBtncumule()){
                  //answer is the reference of the piece
                    String dated = v.getTxtDd().getText();
                    String datef = v.getTxtDf().getText();
                    try{
                   System.out.println("select");
                   in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                   out = new PrintStream(socket.getOutputStream());
                   OperationDTOAli VehiculeDto = new OperationDTOAli();
                   VehiculeDto.setDate_begin(dated);
                   VehiculeDto.setDate_end(datef);
                   Serialization serial = new Serialization();
                   out.println("cumule");
                   out.flush();
                   out.println(serial.serialToStr(serial.serialGeneric(EnumOperation.SEARCH.getIndex(), "Operation", VehiculeDto)));
                   out.flush();
                   String z="";
	                  String res = in.readLine();
	                 while(res != null){
	                	 z=z+res+"\n";
	                	 if(in.ready()){
	                	 res=in.readLine();
	                 }else{break;}
	                	 }
	                   v.getTxtA().setText(z); 
                   } catch(IOException error){
                	   error.printStackTrace();
                   }
		          }};v.getBtncumule().addActionListener(ac3);
    ac4 = e3 ->{
			         if((JButton) e3.getSource()== v.getBtnVehiculeMt()){
	                    try{
	                   System.out.println("VehiculeMatn");
	                   in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	                   out = new PrintStream(socket.getOutputStream());
	                   out.println("VehiculeMatn");
	                   out.flush();
	                  String z="";
	                  String res = in.readLine();
	                 while(res != null){
	                	 z=z+res+"\n";
	                	 if(in.ready()){
	                	 res=in.readLine();
	                 }else{break;}
	                	 }
	                   v.getTxtA().setText(z); 
	                   } catch(IOException error){
	                   }
			          }};v.getBtnVehiculeMt().addActionListener(ac4);
	}}

	


