package Server;

import Controller.ControllerAuthentification;
import Model.ModelVehicle;
import Views.ViewAuthentification;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Properties;

/**
 * Created by tearsyu on 15/03/17.
 *  This is client class, it will auto connect to the server when we launch the app.
 */
public class Client {
    private Properties properties;
    private InputStream in;
    private Socket client;
    private String localhost;
    private int port;

    public Client(){
        properties = new Properties();
        in = getClass().getClassLoader().getResourceAsStream("Resources/configServer.properties");
        try {
            properties.load(in);
            localhost = properties.getProperty("serverIP");
            port = Integer.parseInt(properties.getProperty("port"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void connectToServer() throws IOException {
        client = new Socket(localhost, port);
    }

    public void closeStream{
        in.close();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ModelVehicle m = new ModelVehicle();
                ViewAuthentification v = new ViewAuthentification();

                //Start the client and connect to the server.
                Client client = new Client();
                try {
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
