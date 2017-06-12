package Client.client;

import Client.controller.ControllerAuthentification;
import Client.view.ViewAuthentification;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.Properties;

/**
 *  Created by tearsyu on 15/03/17.
 *  This is Client.client class, it will auto connect to the Server.server when we launch the app.
 *  @author tearsyu
 */
public class Client{
    private Properties properties;
    private InputStream in;
    private Socket client;
    private static String localhost;
    private static int port;

    public Client() throws IOException {
            properties = new Properties();
            in = getClass().getClassLoader().getResourceAsStream("Server/properties/configServer.properties");
            properties.load(in);
            localhost = properties.getProperty("serverIP");
            port = Integer.parseInt(properties.getProperty("port"));

    }
//sdfsjfsdjhsdjh
    public Socket getClient(){return client;}
    /**
     * Connect to Server in according to config file and close the file input stream.
     * @throws IOException
     * */
    public boolean connectToServer() throws IOException {
        client = new Socket(localhost, port);
        closeStream();
        return  client.isConnected();
    }


    /**
     * Close the input stream.
     */
    public void closeStream(){
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Close the socket.
     */
    public void closeSocket(){
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            ViewAuthentification v = new ViewAuthentification();
            System.out.println("Client Client.view ");
            //Start the Client.client and connect to the Server.server.
            try {
                Client client1 = new Client();
                if(client1.connectToServer()){
                    System.out.println("The Client.client connect to the Server.server.");
                    /*This controller doesn't pass the Server.server, it connects directly to the Server.server!!
                    * I need an other type of serialization to send object!
                    * */
                    ControllerAuthentification c = new ControllerAuthentification(v, client1.getClient());
                    c.control();

                    //while(Client.client.getClient().isConnected()){

                    //}
                } else {
                    v.errorDialog(1);
                    System.out.println("Can't not to connect to Server.server.");
                }
            } catch (IOException e) {
                v.errorDialog(1);
                e.printStackTrace();
            }



        });
    }

}
