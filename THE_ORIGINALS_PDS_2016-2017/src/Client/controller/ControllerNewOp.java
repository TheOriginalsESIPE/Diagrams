package Client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import Client.enumeration.EnumService;
import Client.view.ViewPanneOp;



public class ControllerNewOp{
	 ViewPanneOp vpo;
	 Socket socket ;
	 BufferedReader in ;
	 PrintStream out ;
	 public ControllerNewOp (ViewPanneOp vpo , Socket socket){
		 this.vpo=vpo ;
		 this.socket=socket  ;
	 }
	 
	 public void Control (){
		 
		vpo.getbtnAjout().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				try{
					  out = new PrintStream(socket.getOutputStream());
					  in= new BufferedReader(new InputStreamReader(socket.getInputStream()));
					  
					  out.println(EnumService.ADD_OP.name());
					  out.flush();
					  String nameBreakd =vpo.gettexp().getText();
					  out.println(nameBreakd);
					  out.flush();
					  
					 String retour=in.readLine();
					 if(!retour.equals("panne exist")){
						 JOptionPane.showMessageDialog(null, "Cette panne n'esxiste pas \n     verifiez l'orthographe");

						 
					 }else{
						 String retourP=in.readLine();
						 if(retourP.equals("ajout�")){
					     JOptionPane.showMessageDialog(null, "vous ajout� une panne a cette voiture \n"
					     		+ "    la panne est : "+nameBreakd);

							 
						 }
						 
						 
					 }
					  
					
					
				}catch(IOException ex){
					ex.printStackTrace();
				}
				
				
				
				
				
			}
		});
	 }
	 

}
