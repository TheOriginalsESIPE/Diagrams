package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

	public class Server extends JFrame{
		public static final int PORT = 20012;
	    public static final int MAX_CONNECTION = 21;
	    public JTextArea msg;
	    public JScrollPane scrollp;

	    public Server(){
	        super("Server Log");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setVisible(true);
	        setSize(600,600);
	        msg = new JTextArea("Log:\n");
	        scrollp = new JScrollPane(msg);
	        scrollp.setBounds(20, 20, 400, 400);
	        //msg.setBounds(10, 10, 40, 50);
	        msg.setEditable(false);
	        scrollp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	        this.getContentPane().add(scrollp);
	        this.pack();
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
	                server.msg.append("\n Waiting for client connect...");

	                client = serverSocket.accept();

	                server.msg.append("\n Connexion successfully! \nClient is " + client.getInetAddress().getHostAddress());

	                handler = new CPoolServHandler(client, server);
	                executorService.execute(handler);
	                server.msg.append("\n Thread active ID "+String.valueOf(handler.getId())+" state is " + handler.getState());
	            }
	        }catch (Exception e){
	            e.printStackTrace();
	            server.msg.append(String.valueOf(System.err));
	        }
	    }
	}
	
