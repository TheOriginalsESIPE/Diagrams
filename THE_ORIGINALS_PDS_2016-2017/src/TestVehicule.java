import view.Authentification;
import view.ViewAuthentification;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.SwingUtilities;

import repository.ModelVehicle;
import server.ControllerAuthentification;

public class TestVehicule {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			
		public void run(){
			
			ModelVehicle m = new ModelVehicle();
			ViewAuthentification v = new ViewAuthentification();
			
			//Controleur c = new Controleur(m, v);
			//ControllerAuthentification c = new ControllerAuthentification(m, v);
			//c.control();
			
		
		}
		});
}
}
