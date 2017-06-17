package Client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.SwingUtilities;

import Client.enumeration.EnumService;
import Client.view.*;

public class ControllerP {
	private ViewP vp ;
	private ViewPiece vpiece ;
	private ViewInfoVehicle vInfoV ;
	private ViewFin vfin ;
	private ViewListOp vlOp;
	private ViewPanneOp vpOp;
	private Socket client ;
	
	BufferedReader in;
    PrintStream out;	
    
	public ControllerP (ViewP vp, Socket client){
		this.client=client;
		this.vp=vp;
	}
 public void control (){
	 
	
	 
	 vp.getbtnFin().addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==vp.getbtnFin()){
					SwingUtilities.invokeLater(new Runnable() {
			            public void run() {
			            	vfin = new ViewFin();
			            }
					

				});
				}
				
			}
		});//btnFin
	 
	 vp.getbtnListOp().addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==vp.getbtnListOp()){
					SwingUtilities.invokeLater(new Runnable() {
			            public void run() {
			            	vlOp = new ViewListOp();
			            	ControllerViewListOp cvOp= new ControllerViewListOp(vlOp, client);
			            	cvOp.control();
			            
			            }
					

				});
					
						
				}
				
			}
		});//btnListOp
	 
	 vp.getbtnAjoutOp().addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==vp.getbtnAjoutOp()){
					SwingUtilities.invokeLater(new Runnable() {
			            public void run() {
			            	 vpOp= new ViewPanneOp ();
			            	 ControllerNewOp ct = new ControllerNewOp(vpOp, client);
			            	 ct.Control();
			            
			            }
					

				});
					
						
				}
				
			}
		});//btnAjoutOp 
	 
	
	 
	 vp.getbtnOp().addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==vp.getbtnOp()){
					SwingUtilities.invokeLater(new Runnable() {
			            public void run() {
			            try{	
			            	
			           	 in= new BufferedReader(new InputStreamReader(client.getInputStream()));
			           	 out= new PrintStream(client.getOutputStream());
			           	 
			           	 out.println(EnumService.GET_OPERATION_SORT.name());
			           	 out.flush();
			           	 System.out.println("service envoy�");
			           	 String motif =in.readLine();
			           	 vp.gettexPanne().setText(motif);//motif affich� 
			           	 
			           	 String mdl ="Model : "+in.readLine();
			           	 String numMat="Matricule : "+in.readLine();
			           	 
			           	 String info=mdl+"  "+numMat;
			           	 vp.gettexRef().setText(info);//info affich�
			           	 vp.showBtn();
			           	 vp.showbtnPiece();
			           	 //on ajoute l'action listener du boutton ajouter
			           	vp.getbtnInfoVehicle().addActionListener(new ActionListener() {
			    			
			    			
			    			public void actionPerformed(ActionEvent e) {
			    				if (e.getSource()==vp.getbtnInfoVehicle()){
			    					SwingUtilities.invokeLater(new Runnable() {
			    			            public void run() {
			    			            	 vInfoV= new ViewInfoVehicle ();	
			    			            	 ControllerInfoVehicle ctinfo =new ControllerInfoVehicle(vInfoV, vp, client);
			    			            	 ctinfo.control();
			    			            	 
			    			            }
			    			            
			    					         

			    				});
			    					
			    						
			    				}
			    				
			    			}
			    		});//btn info vehicle
			           	
			            vp.getbtnAjoutPiece().addActionListener(new ActionListener() {
			        		
			        		
			        		public void actionPerformed(ActionEvent e) {
			        			if (e.getSource()==vp.getbtnAjoutPiece()){
			        				SwingUtilities.invokeLater(new Runnable() {
			        		            public void run() {
			        		            	vpiece = new ViewPiece();
			        		            	ControllerPieceYoucef cp = new ControllerPieceYoucef(vpiece,vp, client);
			        		            }
			        				

			        			});
			        			}
			        			
			        		}
			        	});//btnAjoutPiece
			           	
			           	
			           	 
			           	 }catch(IOException ex){
			           		 ex.printStackTrace();
			           	 }
			            	
			            	 			            }//fin run()
					

				});
					
						
				}
				
			}
		});//fin boutton prendre une operation 
	 
 }
}
