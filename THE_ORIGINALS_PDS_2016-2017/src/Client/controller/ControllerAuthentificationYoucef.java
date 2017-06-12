package Client.controller;
import enumeration.EnumService;
import view.ViewAuthentification;
import view.ViewP;

import repository.ModelAuth;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import javax.swing.*;

public class ControllerAuthentificationYoucef {
	
	private ViewAuthentification v1;
	private ControllerP c1;
    private ModelAuth mau ; 
	private ActionListener ac;
    Socket socket;
    BufferedReader in;
    PrintStream out;
	ViewP vp ;
	
	public ControllerAuthentificationYoucef(ViewAuthentification v, Socket socket){
		this.v1=v;
        this.socket = socket;
        System.out.println("controller of auth");
	}

public void control(){
	ac = e -> {
	try {
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintStream(socket.getOutputStream());

		if((JButton)e.getSource()== v1.getBtnOK()){
			String compte = v1.getTxt1().getText();
            String password = String.valueOf(v1.getTxt2().getPassword());

			out.println(EnumService.AUTH.name());
			out.flush();
			out.println(compte);
			out.flush();
			out.println(password);
			out.flush();
			String res  = in.readLine();

			if(!res.equals("false")){
				v1.disposeView();
				SwingUtilities.invokeLater(() -> {
							vp = new ViewP();
							c1 = new ControllerP(vp,socket);
							c1.control();
						});
            } else{
                v1.errorDialog(2);
            }
        }
    } catch (IOException e1) {
        e1.printStackTrace();
    }
    };
    v1.getBtnOK().addActionListener(ac);

    }
}

