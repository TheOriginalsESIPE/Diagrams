package Serveur;

import java.io.BufferedReader;



import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.text.JTextComponent;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import EnumOperation.EnumDTO;
import EnumOperation.EnumOperation;
import Serailisation.Deserialization;
import Serailisation.Serialization;
import ViewAuthentification.ModelPersonel;
import ViewAuthentification.Modelchef;
import dto.BreakdownDTO;
import dto.OperationDTO;
import dto.RepairerDTO;
import dto.VehicleDTO;
import dto.Vehicle_warehouseDTO;
import repository.ModelAuth;
import sql.HandlerSQL;


public class CPoolServHandler extends Thread {
    private Socket client;
    private Server server;

    /**
     * The constructor.
     * @param client
     * @param server
     */
    public CPoolServHandler(Socket client, Server server){
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
                //CASE: asking the authentication, here we don't implement the encryption service.
                String cmd = in.readLine();
                System.out.println(cmd);

                if (cmd.equals("auth")) {
                    String login = in.readLine();
                    String pwd = in.readLine();
                    server.msg.append("\n auth = " + login + " " + pwd);
                    ModelAuth ma = new ModelAuth();
                    boolean bool = hsql.selectQuery(ma.authQuery(login, pwd)).next();
                    String res = bool == true ? "true" : "false";
                    server.msg.append("\n query = " + ma.authQuery(login, pwd));
                    server.msg.append("\n response " + res);
                    out.println(res);
                    out.flush();

                } 
////////////////////////////////////////////////////////////////////                
                else if(cmd.equals("chef")){

                    String jsonString = in.readLine();
                    System.out.println(jsonString);
                    server.msg.append("\n" + jsonString);

                    Deserialization des = new Deserialization();
                    JSONObject jo = des.deserialGeneric(jsonString);
                    int action = des.deserialAction(jo);//get action

                    /**
                     * Now we have action and vehicle, should call the Model
                     * */
                    Modelchef modechef= new Modelchef();
                    RepairerDTO dto = new RepairerDTO();
                    OperationDTO DTOp = new OperationDTO();
                    if(des.deserialObject(jo, EnumDTO.REPAIRER.getName()) != null){
                        //Deserialize the dto object and get a jsonArray
                        JSONArray jsonArray = (JSONArray) des.deserialObject(jo, EnumDTO.REPAIRER.getName());
                        jo = (JSONObject) jsonArray.get(0);
                        dto.setFirstname(((String) jo.get("firstname")));
                        dto.setLastname((String) jo.get("lastname"));
                     if (action == EnumOperation.SEARCH.getIndex()) {
							String query = modechef.selectlogin(dto.getLastname(),dto.getFirstname());
                            server.msg.append("\nQuery select login=" + query);
                            ResultSet rs = hsql.selectQuery(query);
                          	while (rs.next()){
                            		dto.setLogin((rs.getString("login")));
                            		 server.msg.append("\nQuery select op=" + rs.getString("login"));
                            					  }
                            String query2 = modechef.selectOp(dto.getLogin());
                            server.msg.append("\nQuery select op=" + query2);
                            ResultSet rs2 = hsql.selectQuery(query2);
                            String z="";
                            String v="";
                            while(rs2.next()){
                            		v="reparateur :"+rs2.getString("login_repairer")+"\n"+" Numero Matricule :"+rs2.getString("numMat")+"\n"+" date_begin :"+rs2.getString("date_begin")
                            		+"  date_end :"+rs2.getString("date_end")+"  time_end"+rs2.getString("time_end")+"\n"+"id_operation :"
                            		+rs2.getString("id_operation")+"  id_breakdown"+rs2.getString("id_breakdown");
                            	server.msg.append("\nQuery select login=" + v);
                            	z=z+v+"\n";
                                   }
                           out.println(z);
                     }}}
///////////////////////////////////////////////////////////////////////////////////////////////////  
                else if(cmd.equals("personelMatricule")){
                     String jsonString = in.readLine();
                     System.out.println(jsonString);
                     server.msg.append("\n" + jsonString);

                     Deserialization des = new Deserialization();
                     JSONObject jo = des.deserialGeneric(jsonString);
                     int action = des.deserialAction(jo);//get action

                     
                     ModelPersonel modelPersonel = new ModelPersonel();
                     VehicleDTO dto = new VehicleDTO();
                     OperationDTO DTOp = new OperationDTO();
                     if(des.deserialObject(jo, EnumDTO.VEHICLE.getName()) != null){   
                         JSONArray jsonArray = (JSONArray) des.deserialObject(jo, EnumDTO.VEHICLE.getName());
                         jo = (JSONObject) jsonArray.get(0);
                         dto.setNumMat(((String) jo.get("numMat")));
                         String p = null;
                         if (action == EnumOperation.SEARCH.getIndex()) {
                           	String List = modelPersonel.SelectOp(dto.getNumMat());
                           	server.msg.append("\n REquet " + List);
                           	ResultSet rs2 = hsql.selectQuery(List);
                            String z="";
                            String v="";
                            while(rs2.next()){
                            	v="Numero Matricule :"+rs2.getString("numMat")+" satus :"+rs2.getString("status");
                            	server.msg.append("\nQuery select login=" + v);
                            	z=z+v+"\n";
                                   }
                            if (z.equals("")){out.println(" le vehicule n'est pas en maintenance");}
                           out.println(z);
                         }
							
                             }
                }
 ///////////////////////////////////////////////////////////////////////////////////               
                else if ((cmd.equals("cumule"))){
                
                	 String jsonString = in.readLine();
                     System.out.println(jsonString);
                     server.msg.append("\n" + jsonString);

                     Deserialization des = new Deserialization();
                     JSONObject jo = des.deserialGeneric(jsonString);
                     int action = des.deserialAction(jo);//get action

                     /**
                      * Now we have action and vehicle, should call the Model
                      * */
                     ModelPersonel modelPersonel = new ModelPersonel();
                     VehicleDTO dto = new VehicleDTO();
                     OperationDTO DTOp = new OperationDTO();
                     Vehicle_warehouseDTO dtoware = new Vehicle_warehouseDTO();
                     if(des.deserialObject(jo, EnumDTO.Operation.getName()) != null){   
                         		JSONArray jsonArray = (JSONArray) des.deserialObject(jo, EnumDTO.Operation.getName());
                         		jo = (JSONObject) jsonArray.get(0);
                         		DTOp.setDate_begin(((String) jo.get("date_begin")));
                         		DTOp.setDate_end(((String) jo.get("date_end")));
                         		if (action == EnumOperation.SEARCH.getIndex()) {
                         				String query = modelPersonel.SelectCum(DTOp.getDate_begin(), DTOp.getDate_end());
                         				server.msg.append("\nQuery select login=" + query);
                         				ResultSet rs = hsql.selectQuery(query);
                         			    String z="";
                                        String v="";
                                        while(rs.next()){
                            v="Numero Matricule : "+rs.getString("numMat")+" date_entrance"+rs.getString("date_entrance")+" date_wayout"+rs.getString("date_wayout");
                             server.msg.append("\nQuery select login=" + v);
                                      z=z+v+"\n";
                                               }
                                       out.println(z);
                                        } 

                     }
                            
                            }
                	 
                  
/////////////////////////////////////////////////////////////////////
                else if ((cmd.equals("VehiculeMatn"))){ 
                	ModelPersonel modelPersonel = new ModelPersonel();
                   String query = modelPersonel.SeclectVDp();
                    server.msg.append("\nQuery select login=" + query);
                    ResultSet rs = hsql.selectQuery(query);
                    String z="";
                    String v="";
                    while(rs.next()){
                    	v="Numero Matricule :"+rs.getString("numMat")+" satus :"+rs.getString("status");
                    	server.msg.append("\nQuery select login=" + v);
                    	z=z+v+"\n";
                           }
                   out.println(z);
                    }
                }

                }catch(Exception x){
                	x.printStackTrace();
                }
            }
}