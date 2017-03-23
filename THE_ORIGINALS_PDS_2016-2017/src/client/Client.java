package client;

import repository.ModelVehicle;
import server.ControllerAuthentification;
import view.ViewAuthentification;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Properties;

/**
 *  Created by tearsyu on 15/03/17.
 *  This is client class, it will auto connect to the server when we launch the app.
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
            in = getClass().getClassLoader().getResourceAsStream("properties/configServer.properties");
            properties.load(in);
            localhost = properties.getProperty("serverIP");
            port = Integer.parseInt(properties.getProperty("port"));

    }

    /**
     * Connect to Server in according to config file and close the file input stream.
     * @throws IOException
     * */
    public void connectToServer() throws IOException {
        client = new Socket(localhost, port);
        closeStream();
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
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ModelVehicle m = new ModelVehicle();
                ViewAuthentification v = new ViewAuthentification();

                //Start the client and connect to the server.
                try {
                    Client client = new Client();
                    client.connectToServer();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                ControllerAuthentification c = new ControllerAuthentification(m, v);
                c.control();
            }
        });
    }

}
