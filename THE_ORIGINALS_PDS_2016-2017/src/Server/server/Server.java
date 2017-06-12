package Server.server;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by tearsyu on 15/03/17.
 * This is the Server.server, it uses the mechanism of Executors to create a pool connection
 * between the Client.client and the Server.server, when a Client.client connect to the Server.server, the Server.server
 * transfer the Client.client socket to the object of CPoolServHandle.
 * I add a GUI to show the statu of all the clients which connect to this Server.server.
 * @author tearsyu
 */
public class Server extends JFrame{
    //public static final int PORT = 20012;
    public static int port = 0;
    public static final int MAX_CONNECTION = 21;
    public JTextArea msg;
    public JScrollPane scrollp;

    public Server(){
        super("Server Log");
        Properties properties = new Properties();
        InputStream in = getClass().getClassLoader().getResourceAsStream("Server/properties/configServer.properties");
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        port = Integer.parseInt(properties.getProperty("port"));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(500,500);
        msg = new JTextArea("Log:\n");
        scrollp = new JScrollPane(msg);
        scrollp.setBounds(20, 20, 400, 400);
        msg.setEditable(false);
        scrollp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.getContentPane().add(scrollp);
    }

    public static void main(String[] arg){
        ServerSocket serverSocket;
        ExecutorService executorService = Executors.newFixedThreadPool(MAX_CONNECTION);
        Server server = new Server();

        try{
            serverSocket = new ServerSocket(port);
            Socket client;
            CPoolServHandler handler;

            while(true){
                server.msg.append("\nWaiting for Client.client connect...");

                client = serverSocket.accept();

                server.msg.append("\nConnexion successfully! \nClient is " + client.getInetAddress().getHostAddress());

                handler = new CPoolServHandler(client, server);
                executorService.execute(handler);

                server.msg.append("\nThread active ID "+String.valueOf(handler.getId())+" state is " + handler.getState());
            }
        }catch (Exception e){
            e.printStackTrace();
            server.msg.append(String.valueOf(System.err));
        }
    }
}
