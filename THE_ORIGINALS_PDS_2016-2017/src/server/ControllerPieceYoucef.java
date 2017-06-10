package server;

import java.awt.event.ActionEvent;
import view.*;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import enumeration.EnumService;

public class ControllerPieceYoucef {
	
	private ViewPieceYoucef vp ;
	private ActionListener actionOK ;
	private Socket socket ;
	private ViewP bigView;
	private int compt ;
	 BufferedReader in;
	    PrintStream out;
	    
	public ControllerPieceYoucef(ViewPieceYoucef  vp,ViewP bigView, Socket socket ){
		this.vp=vp ;
		this.socket=socket ;
		this.bigView=bigView;
		this.controll();
		vp.getbtnOk().addActionListener(actionOK);

	}
	
	public void controll (){
		  compt=0;
          actionOK = new ActionListener() {
			
		public void actionPerformed(ActionEvent e) {
			  ;
			 String nomPiece = vp.gettexp().getText() ;
			 String qte =vp.gettexqte().getText();
			 if (nomPiece=="" || qte==""){
				 JOptionPane.showMessageDialog(null, "ERREUR ! \n "
				 		+ "Le(s) champs est/sont vide(s) , veuillez selectionner le nom de la piéce ainsi que la quantitée");

			 }
		
			 try {
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintStream(socket.getOutputStream());
				
			 out.println(EnumService.IMPORT_PIECE.name());
			 out.flush();
			 out.println(nomPiece);
			 out.flush();
			 out.println(qte);
			 out.flush();
			 //on recupére la reponse du serveur.
			 
			 String rep=in.readLine() ;
			 if(rep.equals("impossible")){
				 JOptionPane.showMessageDialog(null, "Il n'y a pas assez de piece");
				 
			 }else{
				 if(rep.equals("effect")){
					 compt++ ;
					 JOptionPane.showMessageDialog(null, "les piece que vous avez demmander sont disponibe,le systéme vous les a réservé");
					 
					 if (compt==1){
                     bigView.insertText(nomPiece, qte);
                     }else if (compt==2){
                         bigView.insertText2(nomPiece, qte);

                     }else if (compt==3){
                         bigView.insertText3(nomPiece, qte);

                     }else if (compt==4){
                         bigView.insertText4(nomPiece, qte);

                     }else{
    					 JOptionPane.showMessageDialog(null, "vous avez reservé plus que 4 pieces !! \n pour voir le reste cliquez sur voirPiece");
 
                     }
                     
                     
                     
				 }else if(rep.equals("err Qte")){
					 JOptionPane.showMessageDialog(null, "ATTENTION ! la quantité n'est pas un chiffre choisissez un "
					 		+ "nombre \n (champ vides ne sont pas accepté)");

				 }else if (rep.equals("piece not exist")){
					 JOptionPane.showMessageDialog(null, "ERREUR ! \n Le nom de la piéce est INVALIDE");

				 }
				 
				 
			 }
			 
			 }catch(IOException ex){
				 ex.printStackTrace();
			 }
			 
			
			}
		};
	}


}
