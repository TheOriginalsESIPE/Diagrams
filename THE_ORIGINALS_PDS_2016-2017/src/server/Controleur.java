package View;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import javax.swing.JButton;

import Serialisation.Serialization;
import dto.Piece_stockDTO;
import enumeration.EnumOperation;

public class Controleur {
private View v ;
    ActionListener ac ; 
    ActionListener ac1 ; 
    ActionListener ac2 ; 
	Socket s ; 
	BufferedReader in ; 
	PrintStream out ; 
	
	public  Controleur (View v, Socket s) {
		this.v=v ;
		this.s=s ;
	}
	
	public void Control() {
	ac = e1 -> {
        if((JButton) e1.getSource()== v.getNiveau()){
               String nomp = v.getNP().getText(); 
               try {
                 
                   in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                   out = new PrintStream(s.getOutputStream());
                   Piece_stockDTO R = new Piece_stockDTO();
                   R.setname(nomp);
                   Serialization serial = new Serialization();                 
                   out.println("niveaudestock");
                   out.flush();
                   out.println(serial.serialToStr(serial.serialGeneric(EnumOperation.SEARCH.getIndex(), "Piece", R)));
                   out.flush();
                  String rep = in.readLine();
                  v.getNL().setText(rep);
                  
               } catch (IOException error){
                   error.printStackTrace();
               }
        }};v.getNiveau().addActionListener(ac);
        
        ac1 = e1 -> {
            if((JButton) e1.getSource()== v.getHisto()){
                   String refp = v.getHP().getText(); 
                   try {
                     
                       in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                       out = new PrintStream(s.getOutputStream());
                       Piece_stockDTO R = new Piece_stockDTO();
                       R.setname(refp);
                       Serialization serial = new Serialization();                 
                       out.println("Historique");
                       out.flush();
                       out.println(serial.serialToStr(serial.serialGeneric(EnumOperation.SEARCH.getIndex(), "Piece", R)));
                       out.flush();
                       String rep = in.readLine();
                       v.getHL().setText(rep);
                      
                   } catch (IOException error){
                       error.printStackTrace();
                   }
            }};v.getHisto().addActionListener(ac1);
        
  
        
                ac2 = e1 -> {
                    if((JButton) e1.getSource()== v.gettoutelespieces()){
                           try {
                               in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                               out = new PrintStream(s.getOutputStream());
                               Piece_stockDTO R = new Piece_stockDTO();
                               Serialization serial = new Serialization();                 
                               out.println("toutelespieces");
                               out.flush();
                               out.println(serial.serialToStr(serial.serialGeneric(EnumOperation.SEARCH.getIndex(), "Piece", R)));
                               out.flush();
                               String rep =in.readLine();
                               v.getAtoutelespieces().setText(rep);
                               
                           } catch (IOException error){
                               error.printStackTrace();
                           }
                    }};v.gettoutelespieces().addActionListener(ac2);
        
        
        
        
}
}
