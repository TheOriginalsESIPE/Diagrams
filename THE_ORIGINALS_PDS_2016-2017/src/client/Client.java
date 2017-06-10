package Client;


import ViewAuthentification.ControllerAuthentificationChef;
import ViewAuthentification.ControllerAuthentificationPersonel;
import ViewAuthentification.ControllerPersonel;
import ViewAuthentification.Identifier;

import ViewAuthentification.ViewAuthentificationPersonel;
import ViewAuthentification.ViewPersonel;
import javax.swing.*;

import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

/**
 *  Created by tearsyu on 15/03/17.
 *  This is client class, it will auto connect to the server when we launch the app.
 *  @author tearsyu
 */
public class Client{
 
    private Socket client;
    private static String localhost ="127.0.0.1";
    private static int port=20012;
    private Identifier v1;
    private static ActionListener ac;
    private static ActionListener ac2;
    public Client() throws IOException {
    }

    public Socket getClient(){return client;}
    public boolean connectToServer() throws IOException {
        client = new Socket(localhost, port);
        return  client.isConnected(); }
    public void closeSocket(){
        try { client.close();
        } catch (IOException e) {
            e.printStackTrace();}
    }
    
public static void main(String [] arg) throws IOException{
	Client client1 = new Client();
	Identifier v1 = new Identifier();
	
		ac = e -> {
		try{
			if((JButton)e.getSource()== v1.getBPersonel()){
				ViewAuthentificationPersonel v = new ViewAuthentificationPersonel();
				
					if(client1.connectToServer()){
							System.out.println("The client connect to the server.");
							ControllerAuthentificationPersonel c = new ControllerAuthentificationPersonel(v, client1.getClient());
							c.control();
												}
					else System.out.println("personel n'arrive pas a connecter");}
			else if ((JButton)e.getSource()== v1.getBChef()){
							ViewAuthentificationPersonel v = new ViewAuthentificationPersonel();
							if(client1.connectToServer()){
									System.out.println("personel connect to the server.");
									ControllerAuthentificationChef c = new ControllerAuthentificationChef(v, client1.getClient());
									c.control();
				}
				else {
					System.out.println("chef n'arrive pas a connecter");
				}
			}
	    
		}catch(Exception e2){
			e2.printStackTrace();
		}};v1.getBPersonel().addActionListener(ac);
		
		
		ac2 = e1 -> {
		try{	if((JButton)e1.getSource()== v1.getBChef()){
				ViewAuthentificationPersonel v = new ViewAuthentificationPersonel();
				if(client1.connectToServer()){
						System.out.println("chef est connect to the server.");
						ControllerAuthentificationChef c = new ControllerAuthentificationChef(v, client1.getClient());
						c.control();}
				else System.out.println("chef n'arrive pas a connecter");
				}}catch(Exception e3){
					e3.printStackTrace();
				}};v1.getBChef().addActionListener(ac2);	
 }

}