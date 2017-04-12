package server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;

import repository.ModelPiece;
import view.ViewInsert;

public class ControllerInsert{
	
	private ViewInsert v; 
	private ModelPiece mv;
	private ActionListener ac;
	private ActionListener ac1;
	
	public ControllerInsert(ModelPiece mv, ViewInsert v){
		this.mv=mv;
		this.v=v;
	}
		public void control(){
		ac = new ActionListener(){
		 public void actionPerformed(ActionEvent e) {
		 
			 if((JButton)e.getSource()== v.getBtnOK()){
				String answer1 = v.getTxt1().getText();
				String answer2 = v.getTxt3().getText();
				String answer4 = v.getTxt1().getText();
				String answer5 = v.getTxt3().getText();
				String answer3 = v.getTxt2().getText();
				float answer3bis = Float.parseFloat(answer3);
				try {
					int result = mv.insert(answer1, answer2, answer4, answer5, answer3bis);
					v.getBtnOK().setText(result+"ligne modifiee");
					v.getTxt1().setText("");
					v.getTxt2().setText("");
					v.getTxt3().setText("");
					v.getTxt4().setText("");
					v.getTxt5().setText("");
					
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
	
			 }
	
		 }
		 };v.getBtnOK().addActionListener(ac);
		 
		 ac1 = new ActionListener(){
			 public void actionPerformed(ActionEvent e) {
			 
				 if((JButton)e.getSource()== v.getBtnAgain()){
					 v.getBtnOK().setText("OK");
				 }
		
			 }
			 };v.getBtnAgain().addActionListener(ac1);

}
}
