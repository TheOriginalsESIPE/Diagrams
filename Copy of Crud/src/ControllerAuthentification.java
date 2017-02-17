import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ControllerAuthentification {
	
	private ViewAuthentification v1;
	private View v;
	private Controller c1;
	
	private ModelVehicle mv;
	private ActionListener ac;
	
	
	public ControllerAuthentification(ModelVehicle mv, ViewAuthentification v){
		this.mv=mv;
		this.v1=v;
		
		
		
	}

public void control(){
	ac = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
		
					 if((JButton)e.getSource()== v1.getBtnOK()){
						    String answer1 = v1.getTxt1().getText();
							String answer2 = v1.getTxt2().getText();
							try {
							
							boolean res = mv.log(answer1, answer2);
							//System.out.println(res);
							if(res==true){
							v1.dispose();
							v1.setVisible(false);
							v = new View();
							c1 = new Controller(mv, v);
							
							c1.control();
							
							}
							else{}
							
						
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
					}
				
				
					 
				
			}
			};v1.getBtnOK().addActionListener(ac);

}}
