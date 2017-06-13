package Client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import enumeration.EnumServiceYoucef;
import view.ViewFin;


public class ControllerFin {
	
	 ViewFin vf ;
	 Socket socket ;
	 BufferedReader in ;
	 PrintStream out ;
	 public ControllerFin(ViewFin vf , Socket socket){
		 this.vf=vf ;
		 this.socket=socket  ;
	 }
	 
	 public void Control (){
		 
		 vf.getbtnFerm().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==vf.getbtnFerm()){
					if(vf.gettexp().getText().equals("0")){
						 JOptionPane.showMessageDialog(null, "actuellement ce véhicule est bien réparé ");

					}
					
					vf.getframe().setVisible(false);
					vf.getframe().dispose();
				}
			}
		});
		
		 try {
			 in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintStream(socket.getOutputStream());
				out.println(EnumServiceYoucef.FINIR.name());
				out.flush();
				
				//on recupére le nombre d'op restante 
				
				String nombreOp = in.readLine();
			    vf.gettexp().setText(nombreOp);
			 
			 
			 
		 }catch(IOException ex){
			 ex.printStackTrace();
		 }
		 
		 
		 
	 }
	 
	

}
