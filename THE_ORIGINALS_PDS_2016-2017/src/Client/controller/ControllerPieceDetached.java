package Client.controller;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import javax.swing.JButton;

import Client.serialization.Deserialization;
import Client.serialization.Serialization;
import Client.dto.Piece_detachedDTO;
import Client.enumeration.EnumOperation;
import Client.enumeration.EnumService;
import Client.view.ViewPieceDetached;
import Client.view.ViewInsert;

public class ControllerPieceDetached {
	
	private ViewInsert v1;
	
	private ControllerInsert c1;
	private ViewPieceDetached v;
	private ActionListener ac;
	private ActionListener ac1;
	private ActionListener ac2;
	private ActionListener ac3;
	private ActionListener ac4;
	
	private ActionListener ac5;
	private ActionListener ac6;
    Socket socket;
    BufferedReader in;
    PrintStream out;
	
	public ControllerPieceDetached(ViewPieceDetached v, Socket socket){
		this.v=v;
		this.socket = socket;
        System.out.println("controller principal");
	}
	public void control(){
		ac = e -> {

            //SELECTION
        if((JButton)e.getSource()== v.getBtnSelect()){
            v.getTxtF().setText(null);
            v.getTxtU().setText(null);
            v.getTxtD().setText(null);
            v.getTxtA().setText("Entrez la reference de la piece a selectionner");

                ac2 = e1 -> {

                     if((JButton) e1.getSource()== v.getBtnOK()){
                         //answer is the reference of the piece
                            String answer = v.getTxtF().getText();
                           
                            try {
                                /**
                                 * @Modify by Yuxin. Here we can't use directly model, it must be created a DTO
                                 * and serialized in String or an object and send it to Server.server.
                                 * Then the Server.server invoke the model in using the connection pool
                                 * */
                                System.out.println("select");
                                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                                out = new PrintStream(socket.getOutputStream());
                                Piece_detachedDTO pieceSearch = new Piece_detachedDTO();
                                pieceSearch.setRef_piece_detached(answer);
                                Serialization serial = new Serialization();
                                out.println(EnumService.PIECE.name());
                                out.flush();
                                out.println(serial.serialToStr(serial.serialGeneric(EnumOperation.SEARCH.getIndex(), "Piece_detachedDTO", pieceSearch)));
                                out.flush();
                                System.out.println(serial.serialToStr(serial.serialGeneric(EnumOperation.SEARCH.getIndex(), "Piece_detachedDTO", pieceSearch)));
                                //String res = mv.select(answer);

                                //Response
                                String res = in.readLine();

                                Deserialization deserial = new Deserialization();
                                /**
                                 * @Attention Here I consider it returns just an object, but in fact it returns
                                 * often a list of object. So the Client.view may should be changed at the end of project.
                                 * Like for (Object o : listVehicle)
                                 *          vehicleSearch = (VehicleDTO)o
                                 *          v.getTxtA().append("\n" + vehicleSearch.toString())
                                 */
                                //pieceSearch = deserial.deserialObject(deserial.deserialGeneric(res), pieceSearch.getClass().getName());
                                v.getTxtA().setText(res);
                                v.getTxtU().setVisible(false);
                                v.getTxtD().setVisible(false);

                            } catch (IOException error){
                                error.printStackTrace();
                            }
                    }};v.getBtnOK().addActionListener(ac2);
        }};v.getBtnSelect().addActionListener(ac);


		//INSERTION
        ac3 = e -> {
            if((JButton)e.getSource()== v.getBtnInsert()){
                v.getTxtU().setText(null);
                v.getTxtD().setText(null);
                v1 = new ViewInsert();
                c1 = new ControllerInsert(v1, socket);
                c1.control();
            }};v.getBtnInsert().addActionListener(ac3);
		
		//MODIFICATION
		ac1 = e -> {
            if((JButton)e.getSource()== v.getBtnUpdate()){

            //v.getTxtA().setText("Entrez le numero d'immatriculation du vehicule a modifier");

            v.getTxtU().setText(null);
            v.getTxtU2().setText(null);
            v.getTxt1().setText(null);
            v.getTxtD().setText(null);
            ac5 = e1 -> {
                String answer="";
                String answer1="";
                if((JButton) e1.getSource()== v.getBtnOK1()){
                    answer = v.getTxt1().getText();
                    answer1 = v.getTxt3().getText();
                    float answer1Bis=Float.parseFloat(answer1);
                    try {
                    	
                    	 in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                         out = new PrintStream(socket.getOutputStream());
                         Piece_detachedDTO pieceSearch = new Piece_detachedDTO();
                         pieceSearch.setRef_piece_detached(answer);
                         pieceSearch.setPrice(answer1Bis);
                         Serialization serial = new Serialization();
                         out.println("piece");
                         out.flush();
                         out.println(serial.serialToStr(serial.serialGeneric(EnumOperation.UPDATE.getIndex(), "Piece_detachedDTO",pieceSearch)));
                         out.flush();
                         String res = in.readLine();
                         
                         v.getTxtU().setVisible(true);
                         v.getTxtA().setText("");
                         v.getTxtU().setText(res+"ligne modifiee");
                         v.getTxtD().setVisible(false);
                         //v.getTxtA().setText(result+"ligne modifiee");
                    	
                } catch (IOException error) {
                        // TODO Auto-generated catch block
                        error.printStackTrace();
                    }
                }
            };v.getBtnOK1().addActionListener(ac5);
         }};v.getBtnUpdate().addActionListener(ac1);
		
		//SUPPRESSION
		ac4 = e -> {
            if((JButton)e.getSource()== v.getBtnDelete()){

            //v.getTxtA().setText("Entrez le numero d'immatricullation du vehicule a supprimer");

            v.getTxtU().setText(null);
            v.getTxt2().setText(null);
            v.getTxtD().setText(null);
            ac6 = e1 -> {
                if((JButton) e1.getSource()== v.getBtnOK2()){
                    String answer = v.getTxt2().getText();

                    try {
                    	
                    	 in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                         out = new PrintStream(socket.getOutputStream());
                         Piece_detachedDTO pieceSearch = new Piece_detachedDTO();
                         pieceSearch.setRef_piece_detached(answer);
                         Serialization serial = new Serialization();
                         out.println("piece");
                         out.flush();
                         out.println(serial.serialToStr(serial.serialGeneric(EnumOperation.DELETE.getIndex(), "Piece_detachedDTO",pieceSearch)));
                         out.flush();
                         //Response
                         String res = in.readLine();
                         v.getTxtD().setVisible(true);
                         v.getTxtA().setText("");
                         v.getTxtD().setText(res+"ligne supprimee");
                         v.getTxtU().setVisible(false);

             
                    } catch (IOException error) {
                        // TODO Auto-generated catch block
                        error.printStackTrace();
                    }


                }
            };v.getBtnOK2().addActionListener(ac6);


        }};v.getBtnDelete().addActionListener(ac4);
	
	}
}

	
