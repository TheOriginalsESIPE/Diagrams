package server;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Serialisation.Deserialization;

import dto.Piece_stockDTO;
import enumeration.EnumDTO;
import enumeration.EnumOperation;
import sql.HandlerSQL;

public class CPoolServHandlerKarim implements Runnable {
	   private Socket client;
	    private Server2 server;

	    public CPoolServHandlerKarim(Socket client, Server2 server){
	        this.client = client;
	        this.server = server;
	    }

	    public void run(){
	        server.msg.append("\nRUN...");
	        try{
	            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
	            PrintStream out = new PrintStream(client.getOutputStream());
	            HandlerSQL hsql = new HandlerSQL();
	            while(client.isConnected()) {
	                String cmd = in.readLine();
	                System.out.println(cmd);

	                if(cmd.equals("niveaudestock")){

	                    String jsonString = in.readLine();
	                    System.out.println(jsonString);
	                    server.msg.append("\n" + jsonString);

	                    Deserialization des = new Deserialization();
	                    JSONObject jo = des.deserialGeneric(jsonString);
	                    int action = des.deserialAction(jo);//get action

	                    if(des.deserialObject(jo, "Piece") != null){
	                        Modelpiece modelPiece = new Modelpiece();

	                        //Deserialize the dto object and get a jsonArray
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
		                        Modelpiece modelPiece = new Modelpiece();

		                        //Deserialize the dto object and get a jsonArray
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
		                            	z =rs.getString("date_reception");
		                            	server.msg.append("\n Result search = " + z);}
		                            while (rs2.next()){
		                            	 z1 =rs2.getString("date_reception");
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
			                        Modelpiece modelPiece = new Modelpiece();

			                        //Deserialize the dto object and get a jsonArray
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
	                e.printStackTrace();
	        }
	    }
	}

