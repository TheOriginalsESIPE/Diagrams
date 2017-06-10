package ViewAuthentification;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import javax.swing.JButton;

public class ControllerAuthentificationPersonel {
	private ViewAuthentificationPersonel v1;
	private Socket socket;
    private BufferedReader in;
    private PrintStream out;
	private ViewPersonel v;
	private ControllerPersonel c1;
	private ActionListener ac;
	
public ControllerAuthentificationPersonel(ViewAuthentificationPersonel v, Socket socket){
	this.v1=v;
	this.socket=socket;
}
public void control(){
	ac = e -> {
	try {
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintStream(socket.getOutputStream());
		if((JButton)e.getSource()== v1.getBtnOK()){
			String compte = v1.getTxt1().getText();
            String password = v1.getTxt2().getText();
			out.println("auth");
			out.flush();
			out.println(compte);
			out.flush();
			out.println(password);
			out.flush();
			String res  = in.readLine();

			if(!res.equals("false")){
				v1.setVisible(false);
                v = new ViewPersonel();
                c1 = new ControllerPersonel(v, socket);
                c1.control();
            }
        }
    } catch (IOException e1) {
        e1.printStackTrace();
    }
    };
    v1.getBtnOK().addActionListener(ac);

    }
}

