package server;
import repository.ModelAuth;
import view.ViewAuthentification;
import repository.ModelVehicle;
import view.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class ControllerAuthentification {
	
	private ViewAuthentification v1;
	private View v;
	private Controller c1;
	
	private ModelAuth ma;
    private ModelVehicle mv;
	private ActionListener ac;
	
	
	public ControllerAuthentification(ModelAuth ma, ViewAuthentification v){
		this.ma=ma;
		this.v1=v;
		
		
		
	}

public void control(){
	ac = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			if((JButton)e.getSource()== v1.getBtnOK()){
                String answer1 = v1.getTxt1().getText();
				String answer2 = v1.getTxt2().getText();

                boolean res = ma.islegalAuth(answer1, answer2);
                //System.out.println(res);
                if(res==true){
                    v1.dispose();
                    v1.setVisible(false);
                    v = new View();
                    c1 = new Controller(mv, v);
                    c1.control();
                } else{
                    v1.errorDialog(2);
                }
            }
        }
    };
    v1.getBtnOK().addActionListener(ac);

}}

