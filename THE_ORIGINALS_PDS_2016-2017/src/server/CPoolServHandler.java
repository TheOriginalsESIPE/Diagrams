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
                
                else if(cmd.equals(EnumService.SEARCHVEHICLE.name())){
                	//System.out.println("debug");
                	String date_end=in.readLine();//partie rajoutée
                	System.out.println(date_end);
                	Vector<Vehicle_warehouseDTO> d=serv.VehiclenumMatServiceAll(date_end);
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
                }//////////////////////////////////////////YcfBnkt
		    package server;

import enumeration.EnumService;

import repository.ModelAuth;
import repository.ModelInfoVehicle;
import repository.ModelP;
import repository.ModelPiece;
import repository.ModelViewListOp;
import sql.HandlerSQL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Vector;

import dto.Vehicle_warehouseDTO;

public class CPoolServHandler extends Thread {
    private Socket client;
    private Server server;
    int idOperation =1;
    String matricul ;
    String panne ;

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

                if (cmd.equals(EnumService.AUTH.name())) {
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
                    
                  
             
                } else if(cmd.equals(EnumService.IMPORT_PIECE.name())){
                	server.msg.append("import piece\n");
                    ModelPiece mp = new ModelPiece();

                   String nomPiece = in.readLine();//recupération du nom de la piéce
                   String qteS =in.readLine(); //recupération de la quantité
                   
                   if(mp.estUnEntier(qteS)){///
                	   
                   
                   
                   
                   server.msg.append(nomPiece + " | "+ qteS);
                   int qte=Integer.parseInt(qteS);
                  String exist =mp.testExist(nomPiece) ;
                  String select= mp.testtPiece(nomPiece);//on select le nombre qte afin de le tester
                  
                  ResultSet rsExist=hsql.selectQuery(exist);
                  if (!rsExist.next()){
                	  out.println("piece not exist");
                	  out.flush();
                	  
                  }
                  
                  ResultSet rs= hsql.selectQuery(select);
                  
                  server.msg.append("conection avec la BD faite");
                  if (rs.next()){
                  if (rs.getInt("counter") - qte< 0){
                	  server.msg.append("\nImpossible return") ;
                	  out.println("impossible");
                	  out.flush();
                	  ///jusquici tout vas bien
                	  
                  }else{//on modifi la table piéce en stock
                	  
                      String modif=mp.importPiece(nomPiece, qte);
                      
                      server.msg.append("\n"+modif);
                      int rep =hsql.updateQuery(modif);
                      
                      server.msg.append("\n"+rep+"  lignes modifié");
                      //recupéréle id de la piéce 
                      String rec =mp.recupereridPiece(nomPiece);
                      ResultSet rs2 =hsql.selectQuery(rec);
                      String id_piece="";
                      if(rs2.next()){
                    	  id_piece=rs2.getString("ref_piece_stock");
                    	  server.msg.append("\nid piece :" + id_piece);
                      //recupération de lid op
                      
                    	  String inser =mp.insertPieceConso(id_piece, qte, idOperation);
                    	  int rep2 = hsql.updateQuery(inser);
                    	  server.msg.append("\n"+rep2+" lignes ont été ajouter a la table des piece consomées");
                    	  if (rep2 != 0){
                    		  String ef="effect";
                    		  out.println(ef);
                              out.flush();
                    	  }//pour lui dire que cest effectué 
                      

                  
                
                      }}
                   server.msg.append("\n" + nomPiece +"///////"+ qte);

                } }else{
                	String ef="err Qte";//le serveur détécte que la qte n'est pas un entier
          		    out.println(ef);
                    out.flush();
                	
               
                }
            }else if (cmd.equals(EnumService.AFFICHE_PSORT.name())){
            	server.msg.append("\n"+EnumService.AFFICHE_PSORT.name());
            	//we call ModelViewListOp
            	ModelViewListOp m= new ModelViewListOp();
            	//on construit notre requette sql
            	String req =m.getListFromBd();
            	server.msg.append("\n"+req);
            	ResultSet res=hsql.selectQuery(req);
            	
            	server.msg.append("\n recupération de la base");
            	//on recupére ligne par ligne 
            	
            	String lineBD = "";
            	int nb=0;
            	String nbS="";
            	
            	out.println("DEBUT DENVOI");
            	out.flush();
            	while(res.next()){
            		nb++;
            		nbS=Integer.toString(nb);
            		lineBD= lineBD + "Operation N° "+nbS+" Voiture :  "+res.getString("numMat")+
            				"            Panne :  "+res.getString("name")+" id_operation : "+res.getInt("id_operation")+"\n";
            		
            		
            		}
            	out.println(nbS);
            	out.flush();
            	out.print(lineBD);
            
        		out.flush();
        		
        		System.out.println("ce que jai envoyé :"+ lineBD);
        		server.msg.append("\n"+ lineBD ); 


            	//on envoi au client le resultat
            	
            	
            	
            	
            }else if (cmd.equals(EnumService.GET_OPERATION_SORT.name())){
            	//on appel le modelP
            	ModelP mdlp = new ModelP();
            	String reqMaxDegree =mdlp.selectMaxDegree();
                server.msg.append("\n"+"recupération du degré max ");
                server.msg.append("\n"+reqMaxDegree);


            	
            	ResultSet rsMax=hsql.selectQuery(reqMaxDegree);
            	if (rsMax.next()){
                int max =rsMax.getInt(1);
           
            	String reqOpSort =mdlp.selectMaxline(max);//ici on recupérée l'operation priorisée 
                server.msg.append("\n"+reqOpSort);

            	ResultSet rsMAX=hsql.selectQuery(reqOpSort);
                if(rsMAX.next()){
                    server.msg.append("\n operation trouvé");

                int idOp =rsMAX.getInt(1);
                int idOp_Op =rsMAX.getInt(2);
                
                idOperation=idOp_Op;
                server.msg.append("\n identifiant de loperation : "+idOperation);
                
                int idPanne=rsMAX.getInt(3);
                
                
                String numMat =rsMAX.getString(4);
                matricul=numMat;
                server.msg.append("\n matricul vehicle : "+matricul);

                int degree=rsMAX.getInt(5);
                
                //on recupére le motif 
                String reqMotif =mdlp.selectMotif(idPanne);
                server.msg.append("\n"+reqMotif);

                ResultSet rsMotif=hsql.selectQuery(reqMotif);
                if(rsMotif.next()){
                	String namePanne =rsMotif.getString("name");
                	panne=namePanne ;
                	out.println(namePanne);
                	out.flush();
                    server.msg.append("\n"+"motif envoyé");

                }
                //on recupére le nom du véhicule
                String reqVehicle =mdlp.selecVehicle(numMat);
                server.msg.append("\n"+reqVehicle);
                ResultSet rss=hsql.selectQuery(reqVehicle);
                if (rss.next()){
                server.msg.append("\n"+"info envoyé");

                String mdl= rss.getString(2);
                out.println(mdl);
                out.flush();
                out.println(numMat);
                out.flush();
                server.msg.append("\n"+"info envoyé");
                }
                
        
              
                
      
                }
            	}
            	
            	
            }else if (cmd.equals(EnumService.SHOW_INFO.name())){
            	server.msg.append("\n"+cmd);
            	
            	ModelInfoVehicle modelInfo = new ModelInfoVehicle();
            	String req1=modelInfo.reqInfoPanne(panne);
            	String req2=modelInfo.reqInfoV(matricul);
            	String req3=modelInfo.reqInfoWarehouse(matricul);
            	server.msg.append("\n"+req1);
            	ResultSet resInfo=hsql.selectQuery(req1);
            	if (resInfo.next()){
            		
            		Time duree = resInfo.getTime(1);
            		String d =duree.toString();
            		out.println(d);
            		out.flush();
            		 }
            	
            	server.msg.append("\n"+req2);
            	ResultSet resInfo2=hsql.selectQuery(req2);
            	if (resInfo2.next()){
            		String model =resInfo2.getString(2);
            		String mark  =resInfo2.getString(3);
            		String type  =resInfo2.getString(4);
            		
            		out.println(model);
            		out.flush();
            		
            		out.println(mark);
            		out.flush();
            		
            		out.println(type);
            		out.flush  ();
                   }
            	
            	server.msg.append("\n"+req3);
            	ResultSet resInfo3 =hsql.selectQuery(req3);
            	if(resInfo3.next()){
            		int numPlace=resInfo3.getInt(3);
            		String numPlaceS=Integer.toString(numPlace);
            		out.println(numPlaceS);
            		out.flush();
            		
            		Date d =resInfo3.getDate(4);
            		String date=d.toString();
            		out.println(date);
            		out.flush();
            		
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
//////////////////////////////////////////////////////////////////
		    
		    
		    
                
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
