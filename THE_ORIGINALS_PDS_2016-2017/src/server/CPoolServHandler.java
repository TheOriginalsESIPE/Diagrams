package server;
import enumeration.EnumManut;
import enumeration.EnumService;
import service.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by tearsyu on 15/03/17.
 * This class define the service of all the connection of server
 * In R2, this class is responsible to get the info of serialization from client,
 * deserialize it then pass it to DB.
 * Finally it returns a feedback to client.
 * @author tearsyu
 */
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

    public void run() {
        server.msg.append("\nRUN...");
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintStream out = new PrintStream(client.getOutputStream());
            Service serv = new Service();
            while (client.isConnected()) {
                //CASE: asking the authentication, here we don't implement the encryption service.
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
               
                	//server.msg.append("\n vehicle information : " + vehicle);
                	
                	String vehicleInfo = serv.vehicleNumMatService(vehicle);
                	System.out.println(vehicleInfo);
                	out.println(vehicleInfo);
                	out.flush();
                }
           
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintStream out = new PrintStream(client.getOutputStream());
            HandlerSQL hsql = new HandlerSQL();
            while(client.isConnected()) {
                //CASE: asking the authentication, here we don't implement the encryption service.
                String cmd = in.readLine();
                System.out.println(cmd);

                if (cmd.equals("auth1")) {
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
                else if(cmd.equals("chef1")){

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
                     while(rs2.next()){
                    	        DTOp.setTime_begin(rs2.getString("time_begin"));
                            	DTOp.setDate_begin(rs2.getString("date_begin"));
                            	DTOp.setDate_end(rs2.getString("date_end"));
                            	DTOp.setTime_end(rs2.getString("time_end"));
                            	DTOp.setId_breakdown((rs2.getString("id_breakdown")));
                            	DTOp.setId_operation(Integer.parseInt(rs2.getString("id_operation")));
                            	DTOp.setnuMat(rs2.getString("numMat"));
                            	DTOp.setLogin_repairer(rs2.getString("login_repairer"));        
                            }
                            server.msg.append("\n Result search = " + DTOp.toString());
                            Serialization ser = new Serialization();
                            String res  = ser.serialToStr(ser.serialGeneric(EnumOperation.RESPONSE.getIndex(), DTOp.getClass().getSimpleName(), DTOp));
                            out.println(res);
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
                            
                         while(rs2.next()){
                           p="date_begin = "+rs2.getString("date_begin")+"  id_breakdown ="+rs2.getString("id_breakdown")+
                        		   "  id_operation ="+rs2.getString("id_operation")+"  login_repairer  ="+rs2.getString("login_repairer");
                           	}}
							server.msg.append("\n Result search = "+p);
                            out.println(p);
                            p="";
                             }
                }
                
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
                            while(rs.next()){
                            	dtoware.setDate_entrance((rs.getString("date_entrance")));
                            	dtoware.setDate_wayout((rs.getString("date_wayout")));
                            	dtoware.setNumMat((rs.getString("numMat")));
                            }
                            server.msg.append("\n Result search = " + dto.toString());
                            Serialization ser = new Serialization();
                            String res  = ser.serialToStr(ser.serialGeneric(EnumOperation.RESPONSE.getIndex(), dtoware.getClass().getSimpleName(), dtoware));
                            out.println(res);
                            
                            }
                	 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
