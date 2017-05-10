package server;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import javax.swing.JButton;

import dto.Piece_detachedDTO;
import enumeration.EnumOperation;
import repository.ModelPiece;
import serialization.Serialization;
import view.ViewInsert;

public class ControllerInsert{
	
	private ViewInsert v;
	private ActionListener ac;
	private ActionListener ac1;
	private BufferedReader in;
	private PrintStream out;
	private Socket socket;
	
	public ControllerInsert(ViewInsert v, Socket socket){
		this.v=v;
        this.socket = socket;
	}
		public void control(){
		ac = e -> {
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
                   System.out.println(pieceSearch.toString());
                   Serialization serial = new Serialization();
                   out.println("piece");
                   out.flush();
                   out.println(serial.serialToStr(serial.serialGeneric(EnumOperation.INSERT.getIndex(), "Piece_detachedDTO",pieceSearch)));
                   out.flush();
                   //Response
                   String res = in.readLine();
                   v.getBtnOK().setText(res+"ligne modifiee");
                   v.getTxt1().setText("");
                   v.getTxt2().setText("");
                   v.getTxt3().setText("");
                   v.getTxt4().setText("");
                   v.getTxt5().setText("");
               } catch (IOException e1) {
                   // TODO Auto-generated catch block
                   e1.printStackTrace();
               }


            }

        };v.getBtnOK().addActionListener(ac);
		 
		 ac1 = e -> {
             if((JButton)e.getSource()== v.getBtnAgain()){
                 v.getBtnOK().setText("OK");
             }

         };v.getBtnAgain().addActionListener(ac1);

}
}
