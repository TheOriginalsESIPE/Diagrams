package Client;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Properties;

import javax.swing.SwingUtilities;

import View.Controleur;
import View.View;


public class Client {
	private Properties properties;
    private InputStream in;
    private Socket client;
    private static String localhost;
    private static int port;

    public Client() throws IOException {
            properties = new Properties();
            in = getClass().getClassLoader().getResourceAsStream("properties/configServer.properties");
            properties.load(in);
            localhost = properties.getProperty("serverIP");
            port = Integer.parseInt(properties.getProperty("port"));

    }

    public Socket getClient(){return client;}
   
    public boolean connectToServer() throws IOException {
        client = new Socket(localhost, port);
        closeStream();
        return  client.isConnected();
    }

    public void closeStream(){
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void closeSocket(){
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            View v = new View();
            System.out.println("Client view ");
            //Start the client and connect to the server.
            try {
                Client client1 = new Client();
                if(client1.connectToServer()){
                    System.out.println("The client connect to the server.");
                    
                    Controleur c = new Controleur( v, client1.getClient());
                    c.Control();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }



        });
    }

}

