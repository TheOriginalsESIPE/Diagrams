package server;
import repository.ModelAuth;
import view.ViewAuthentification;
import repository.ModelPiece;
import view.View;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import javax.swing.JButton;

public class ControllerAuthentification {
	
	private ViewAuthentification v1;
	private View v;
	private Controller c1;

	private ActionListener ac;
    Socket socket;
    BufferedReader in;
    PrintStream out;
	
	
	public ControllerAuthentification(ViewAuthentification v, Socket socket){
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

			out.println("auth");
			out.flush();
			out.println(compte);
			out.flush();
			out.println(password);
			out.flush();
			String res  = in.readLine();

			if(!res.equals("false")){
				v1.disposeView();
				v1.setVisible(false);
                v = new View();
                c1 = new Controller(v, socket);
                c1.control();
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

