package Server;

import javax.swing.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by tearsyu on 15/03/17.
 * This class is aim to create a singleton server which play the role of Controller in out project.
 */
public class Server extends JFrame {
    public static final int PORT = 20012;
    public static final int MAX_CONNECTION = 10;
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
                System.out.println("Waiting for client connect...");
                server.msg.append("\nWaiting for client connect...");

                client = serverSocket.accept();
                System.out.println("Connexion successfully! Client is " + client.getInetAddress().getHostAddress());
                server.msg.append("\nConnexion successfully! \nClient is " + client.getInetAddress().getHostAddress());
                handler = new CPoolServHandler(client, server);
                executorService.execute(handler);
                server.msg.append("Thread active ID "+String.valueOf(handler.getId())+" state is " + handler.getState());

            }
        }catch (Exception e){}
    }
}
