package server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.SQLException;
import javax.swing.JButton;

import dto.Piece_detachedDTO;
import enumeration.EnumOperation;
import repository.ModelPiece;
import serialization.Deserialization;
import serialization.Serialization;
import view.ViewInsert;

public class ControllerInsert{
	
	private ViewInsert v; 
	private ModelPiece mv;
	private ActionListener ac;
	private ActionListener ac1;
	private BufferedReader in;
	private PrintStream out;
	private Socket socket;
	
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
					
					in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    out = new PrintStream(socket.getOutputStream());
                    Piece_detachedDTO pieceSearch = new Piece_detachedDTO();
                    pieceSearch.setRef_piece_detached(answer1);
                    pieceSearch.setName(answer2);
                    pieceSearch.setPrice(answer3bis);
                    pieceSearch.setModel(answer4);
                    pieceSearch.setMark(answer5);
                    Serialization serial = new Serialization();
                    out.print(serial.serialToStr(serial.serialGeneric(EnumOperation.INSERT.getIndex(), "Piece_detachedDTO",pieceSearch)));
                    
                  //int result = mv.insert(answer1, answer2, answer4, answer5, answer3bis);
                    //Response
                    String res = in.readLine();
                    v.getBtnOK().setText(res+"ligne modifiee");
					v.getTxt1().setText("");
					v.getTxt2().setText("");
					v.getTxt3().setText("");
					v.getTxt4().setText("");
					v.getTxt5().setText("");
					

                    Deserialization deserial = new Deserialization();
                    /**
                     * @Attention Here I consider it returns just an object, but in fact it returns
                     * often a list of object. So the view may should be changed at the end of project.
                     * Like for (Object o : listVehicle)
                     *          vehicleSearch = (VehicleDTO)o
                     *          v.getTxtA().append("\n" + vehicleSearch.toString())
                     */
                    pieceSearch = (Piece_detachedDTO) deserial.deserialObject(deserial.deserialGeneric(res), pieceSearch.getClass().getName());
                 
					
			
	
				} catch (IOException e1) {
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
