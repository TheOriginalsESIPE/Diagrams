package ViewAuthentification;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import javax.swing.JButton;

import EnumOperation.EnumOperation;
import Serailisation.Deserialization;
import Serailisation.Serialization;
import dto.RepairerDTO;
import dto.VehicleDTO;

public class ControllerChef {
	private Socket socket;
    private	BufferedReader in;
	private PrintStream  out;
	private ViewChef v;
	private ActionListener ac2;
	public ControllerChef (ViewChef v, Socket socket){
		this.v=v;
		this.socket = socket;
        System.out.println("controller principal");
	}
	public void control(){       
		ac2 = e1 -> {
                     if((JButton) e1.getSource()== v.getBtnChercher()){
                            String nom = v.getPersonelN().getText();
                            String prenom = v.getPersonelP().getText();
                            try {
                                System.out.println("select");
                                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                                out = new PrintStream(socket.getOutputStream());
                                RepairerDTO ReparateurDto = new RepairerDTO();
                                ReparateurDto.setFirstname(prenom);
                                ReparateurDto.setLastname(nom);
                                Serialization serial = new Serialization();                 
                               
                                out.println("chef");
                                out.flush();
                                out.println(serial.serialToStr(serial.serialGeneric(EnumOperation.SEARCH.getIndex(), "Repairer", ReparateurDto)));
                                out.flush();
                                System.out.println(serial.serialToStr(serial.serialGeneric(EnumOperation.SEARCH.getIndex(), "Repairer", ReparateurDto)));
                                String z="";
                                String res = in.readLine();
                                while(res != null){
           	                	 z=z+res+"\n";
           	                	 if(in.ready()){
           	                	 res=in.readLine();
           	                 }else{break;}
           	                	 }
           	                   v.getTxtA().setText(z); 


                            } catch (IOException error){
                                error.printStackTrace();
                            }
                    }};v.getBtnChercher().addActionListener(ac2);
        }}
