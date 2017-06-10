package server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import javax.swing.SwingUtilities;

import enumeration.EnumService;
import view.ViewListOp;

public class ControllerViewListOp {
	ViewListOp vlOp ;
	private Socket socket ;
	
	
	 BufferedReader in;
	    PrintStream out;
	    //ActionListener actionferm;
	
	public ControllerViewListOp(ViewListOp vlOp,Socket socket){
		this.vlOp=vlOp;
		this.socket=socket;
		//vlOp.getbtnFerm().addActionListener(actionferm);
       
		

	}
	public void control(){
		vlOp.getbtnFerm().addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource()==vlOp.getbtnFerm()){
				SwingUtilities.invokeLater(new Runnable() {
		            public void run() {
                        vlOp.getframe().setVisible(false);
                        vlOp.getframe().dispose();
		            }
				

			});
				
					
			}
			
		}
	});//btnListOp
		
		
		
		try{
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintStream(socket.getOutputStream());
		out.println(EnumService.AFFICHE_PSORT.name());
		out.flush();
		//on recupére les ligne du serveur
		String line ; 
		int nbrIteration ;
		
		if((line=in.readLine()).equals("DEBUT DENVOI")){
		System.out.println("reçu : "+line);
		
		
		
		nbrIteration = Integer.parseInt(in.readLine()); //recupéré nbriteration
		System.out.println("lecture "+nbrIteration+ " fois");
		for(int i=0; i<nbrIteration;i++){
			
		line=in.readLine();
		System.out.println("reçu : "+line);
		vlOp.gettexp().append(line+"\n"); 
		
		}
		
		
		
			
		
		
		
}
		
		
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
}
