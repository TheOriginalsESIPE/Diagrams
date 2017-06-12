package Client.controller;
import Client.enumeration.EnumService;
import Client.view.ViewAuthentification;
import Client.view.WelcomeView;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import javax.swing.*;

public class ControllerAuthentification {
	
	private ViewAuthentification viewAuth;
	private WelcomeView v;

	private ActionListener ac;
    private Socket socket;
    private BufferedReader in;
    private PrintStream out;
	
	
	public ControllerAuthentification(ViewAuthentification v, Socket socket){
		this.viewAuth =v;
        this.socket = socket;
        System.out.println("controller of auth");
	}

public void control(){
	ac = e -> {
	try {
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintStream(socket.getOutputStream());

		if((JButton)e.getSource()== viewAuth.getBtnOK()){
			String compte = viewAuth.getTxt1().getText();
            String password = String.valueOf(viewAuth.getTxt2().getPassword());

			out.println(EnumService.AUTH.name());
			out.flush();
			out.println(compte);
			out.flush();
			out.println(password);
			out.flush();
			String res  = in.readLine();

			if(res.equals("false")){
				viewAuth.errorDialog(2);
				viewAuth.getTxt2().setText("");
            } else{
				SwingUtilities.invokeLater(()->{
					v = new WelcomeView(res, socket);
				});
				viewAuth.disposeView();
            }
        }
    } catch (IOException e1) {
        e1.printStackTrace();
    }
    };
    viewAuth.getBtnOK().addActionListener(ac);

    }
}

