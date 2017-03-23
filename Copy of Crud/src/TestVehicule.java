import Controller.ControllerAuthentification;
import Model.ModelVehicle;
import Server.Client;
import Views.ViewAuthentification;

import java.io.IOException;

import javax.swing.SwingUtilities;

public class TestVehicule {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
		public void run(){
			ModelVehicle m =new ModelVehicle();
			ViewAuthentification v = new ViewAuthentification();

            //Start the client and connect to the server.
			Client client = new Client();
            try {
                client.connectToServer();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //Controleur c = new Controleur(m, v);
			ControllerAuthentification c = new ControllerAuthentification(m, v);
			c.control();
		}
		});
}
}
