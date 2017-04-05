package server;

import javax.swing.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by tearsyu on 15/03/17.
 * This is the server, it uses the mechanism of Executors to create a pool connection
 * between the client and the server, when a client connect to the server, the server
 * transfer the client socket to the object of CPoolServHandle.
 * I add a GUI to show the status of all the clients which connect to this server.
 * @author tearsyu
 */
public class Server extends JFrame{
    public static final int PORT = 20012;
    public static final int MAX_CONNECTION = 21;
    public JTextArea msg;

    public Server(){
        super("Server Log");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(400,300);
        msg = new JTextArea("Log:\n");
        msg.setBounds(10,10,10,10);
        this.getContentPane().add(msg);
    }

    public static void main(String[] arg){
        ServerSocket serverSocket;
        ExecutorService executorService = Executors.newFixedThreadPool(MAX_CONNECTION);
        Server server = new Server();

        try{
            serverSocket = new ServerSocket(PORT);
            Socket client;
            CPoolServHandler handler;

            while(true){
                server.msg.append("\nWaiting for client connect...");

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
