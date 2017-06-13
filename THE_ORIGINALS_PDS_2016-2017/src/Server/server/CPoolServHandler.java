package Server.server;
import Server.dto.Vehicle_warehouseDTOL;
import Server.enumeration.EnumService;
import Server.dto.Vehicle_warehouseDTO;
import Server.service.Service;
//import dto.Vehicle_warehouseDTO;
import Server.repository.ModelpieceK;
import Server.dto.Piece_stockDTO;
import Server.enumeration.EnumOperation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import Server.sql.*;
import Server.serialization.Deserialization;

/**
 * Created by tearsyu on 15/03/17.
 * This class define the Server.service of all the connection of Server.server
 * In R2, this class is responsible to get the info of serialization from Client.client,
 * deserialize it then pass it to DB.
 * Finally it returns a feedback to Client.client.
 * @author tearsyu
 */
public class CPoolServHandler extends Thread {
    private Socket client;
    private Server server;
    private HandlerSQL hsql = new HandlerSQL()  ;

    /**
     * The constructor.
     * @param client
     * @param server
     */
    public CPoolServHandler(Socket client, Server server){
        this.client = client;
        this.server = server;
    }

    public void run() {
        server.msg.append("\nRUN...");
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintStream out = new PrintStream(client.getOutputStream());
            Service serv = new Service();
            while (client.isConnected()) {
                //CASE: asking the authentication, here we don't implement the encryption Server.service.
                String cmd = in.readLine();
                System.out.println(cmd);

                if (cmd.equals(EnumService.AUTH.name())) {
                    String login = in.readLine();
                    String pwd = in.readLine();
                    String res = serv.authService(login, pwd);

                    server.msg.append("\n auth = " + login + " " + pwd);
                    server.msg.append("\n response " + res);

                    out.println(res);
                    out.flush();

                } else if (cmd.equals(EnumService.PIECE.name())) {

                    String jsonString = in.readLine();
                    System.out.println(jsonString);
                    server.msg.append("\n" + jsonString);
                    String strRe = serv.pieceService(jsonString);
                    server.msg.append("\n Result search = " + strRe);
                    out.println(strRe);

                } else if (cmd.equals(EnumService.INDICATOR.name())){
                    String strMap = in.readLine();
                    server.msg.append("\n indicator request map :" + strMap);

                    String strRe = serv.indicatorService(strMap);
                    System.out.println(strRe);
                    out.println(strRe);
                    out.flush();
                }else if (cmd.equals(EnumService.BREAKDOWNLOAD.name())){
                	String jsonString = in.readLine();
                	
                	 System.out.println(jsonString);
                     server.msg.append("\n" + jsonString);
                	String strListName = serv.breakdownServices(jsonString);
                	System.out.println(strListName);
                	out.println(strListName);
                	out.flush();
                }else if(cmd.equals(EnumService.VEHICLEDOWNLOAD.name())){
                	String vehicle = in.readLine();
               
                	//Server.server.msg.append("\n vehicle information : " + vehicle);
                	
                	String vehicleInfo = serv.vehicleNumMatService(vehicle);
                	System.out.println(vehicleInfo);
                	out.println(vehicleInfo);
                	out.flush();
                }else if(cmd.equals(EnumService.SAVEVEHICLE.name())){
                    String infoB = in.readLine();
                     	//System.out.println(infoB);
                    String infoV = in.readLine();
                     	//System.out.println(infoV);
                    String infoR = in.readLine();
                         //System.out.println(infoR);
                    String res = serv.saveHehicle(infoB, infoV, infoR);
                    out.println(res);
                    out.flush();
                }else if(cmd.equals(EnumService.PARKINGDOWLOAD.name())){
                    
                    String res = serv.getParking();
                    out.println(res);
                    out.flush();
                }
                
                else if(cmd.equals(EnumService.SEARCHVEHICLE.name())){
                	//System.out.println("debug");
                	String date_end=in.readLine();//partie rajoutée
                	System.out.println(date_end);
                	Vector<Vehicle_warehouseDTOL> d=serv.VehiclenumMatServiceAll(date_end);
                	System.out.println("get vector " + d.size());
                	out.println(d.size());
                	for(int i=0; i<d.size(); i++){
                		out.println(d.get(i).getNumMat());
                		out.flush();
                	}
                	
                }
                
                else if(cmd.equals(EnumService.SEARCHInfoVEHICLE.name())){
                	server.msg.append("service serch vehicle.\n");
                	String numMat=in.readLine();
                	String d=serv.Vehiclenumat(numMat);
                	out.println(d);
                	out.flush();
               }
                else if(cmd.equals(EnumService.VEHICLERELOCATE.name())){
                	server.msg.append("service serch vehicle.\n");
                	String numMat=in.readLine();
                	int id_warehouse = Integer.parseInt(in.readLine());
                	String d=serv.relocate(numMat,id_warehouse);
                	out.println(d);
                	out.flush();
                } 	                if(cmd.equals("niveaudestock")){

                    String jsonString = in.readLine();
                    System.out.println(jsonString);
                    server.msg.append("\n" + jsonString);

                    Deserialization des = new Deserialization();
                    JSONObject jo = des.deserialGeneric(jsonString);
                    int action = des.deserialAction(jo);//get action

                    if(des.deserialObject(jo, "Piece") != null){
                        ModelpieceK modelPiece = new ModelpieceK();

                        
                        JSONArray jsonArray = (JSONArray) des.deserialObject(jo, "Piece");
                        jo = (JSONObject) jsonArray.get(0);
                        Piece_stockDTO dto = new Piece_stockDTO();
                        dto.setname((String) jo.get("name"));
                        if   (action == EnumOperation.SEARCH.getIndex()) {
                            String query = modelPiece.SelectNs(dto.getname());
                            server.msg.append("\nQuery SELECT=" + query);
                            ResultSet rs = hsql.selectQuery(query);
                           int i=0;
                            while (rs.next()){
                                i++;
                                System.out.println(i);
                                server.msg.append("\n Result search = " + i);
                            }
                            String z = Integer.toString(i);
                            out.println(z);
                        }
                    }}
                    else if(cmd.equals("Historique")){
                    	
                        String jsonString1 = in.readLine();
	                    System.out.println(jsonString1);
	                    server.msg.append("\n" + jsonString1);

	                    Deserialization des = new Deserialization();
	                    JSONObject jo = des.deserialGeneric(jsonString1);
	                    int action = des.deserialAction(jo);//get action

	                    if(des.deserialObject(jo, "Piece") != null){
	                        ModelpieceK modelPiece = new ModelpieceK();

	                        
	                        JSONArray jsonArray = (JSONArray) des.deserialObject(jo, "Piece");
	                        jo = (JSONObject) jsonArray.get(0);
	                        Piece_stockDTO dto = new Piece_stockDTO();
	                        dto.setname((String) jo.get("name"));
	                        if   (action == EnumOperation.SEARCH.getIndex()) {
	                            String query = modelPiece.SelectHdr(dto.getname());
	                            String query2 = modelPiece.SelectHdrc(dto.getname());
	                            server.msg.append("\nQuery SELECT=" + query);
	                            server.msg.append("\nQuery SELECT=" + query2);
	                            ResultSet rs = hsql.selectQuery(query);
	                            ResultSet rs2 = hsql.selectQuery(query2);
	                            String z1 = null;
	                            String z = null;
	                            while (rs.next()){
	                            	z ="entre:"+rs.getString("date_reception");
	                            	server.msg.append("\n Result search = " + z);}
	                            while (rs2.next()){
	                            	 z1 ="sortie:"+rs2.getString("date_reception");
	                            	 server.msg.append("\n Result search = " + z1);
	                            }
	                            out.println(z+" "+z1);
	                        }
                    	
                    	
                    } }
                    else if(cmd.equals("toutelespieces")){
                    	 String jsonString1 = in.readLine();
		                    System.out.println(jsonString1);
		                    server.msg.append("\n" + jsonString1);

		                    Deserialization des = new Deserialization();
		                    JSONObject jo = des.deserialGeneric(jsonString1);
		                    int action = des.deserialAction(jo);//get action

		                    if(des.deserialObject(jo, "Piece") != null){
		                        ModelpieceK modelPiece = new ModelpieceK();

		                        
		                        JSONArray jsonArray = (JSONArray) des.deserialObject(jo,"Piece");
		                        jo = (JSONObject) jsonArray.get(0);
		                        Piece_stockDTO dto = new Piece_stockDTO();
		                        dto.setname((String) jo.get("ref_piece_stock"));
		                        if   (action == EnumOperation.SEARCH.getIndex()) {
		                            String query = modelPiece.SelectPc();
		                            server.msg.append("\nQuery SELECT=" + query);
		                            ResultSet rs = hsql.selectQuery(query);
		                            String r="";
		                            String z ="";
		                            while (rs.next()){
		                            	 r =rs.getString("name")+" "+rs.getString("ref_piece_stock");
		                            	
		                            	z=z+r+"\n";
			                            
			                            server.msg.append("\n Result search = " +z);
		                            }			                            
		                            server.msg.append("\n Result search = " + dto.toString());
		                            out.println(z);
		                        }
		                    }
		               }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
