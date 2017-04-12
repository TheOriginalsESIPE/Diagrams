package server;
import repository.ModelAuth;
import sql.HandlerSQL;
import view.ViewAuthentification;
import repository.ModelPiece;
import view.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Handler;
import javax.swing.JButton;

public class ControllerAuthentification {
	
	private ViewAuthentification v1;
	private View v;
	private Controller c1;
	
	private ModelAuth ma;
	private ActionListener ac;
    Socket socket;
    BufferedReader in;
    PrintStream out;
	
	
	public ControllerAuthentification(ModelAuth ma, ViewAuthentification v, Socket socket){
		this.ma=ma;
		this.v1=v;
        this.socket = socket;
        System.out.println("controller of auth");
	}

public void control(){
	ac = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintStream(socket.getOutputStream());

            if((JButton)e.getSource()== v1.getBtnOK()){
                String answer1 = v1.getTxt1().getText();
				String answer2 = v1.getTxt2().getText();

                out.println("auth");
                out.flush();
                out.println(answer1);
                out.flush();
                out.println(answer2);
                out.flush();
                String res  = in.readLine();
                if(!res.equals("false")){
                    v1.dispose();
                    v1.setVisible(false);
                    v = new View();
                    ModelPiece mv = new ModelPiece();
                    c1 = new Controller(mv, v, socket);
                    c1.control();
                } else{
                    v1.errorDialog(2);
                }
            }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    };
    v1.getBtnOK().addActionListener(ac);

}}

