package Server.server;
import Server.dto.Vehicle_warehouseDTOL;
import Server.enumeration.EnumService;
import Server.dto.Vehicle_warehouseDTO;
import Server.service.Service;
//import dto.Vehicle_warehouseDTO;
import Server.repository.ModelpieceK;
import Server.dto.OperationDTOAli;
import Server.dto.Piece_stockDTO;
import Server.dto.RepairerDTO;
import Server.dto.VehicleDTOAli;
import Server.enumeration.EnumOperation;

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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Server.enumeration.*;
import Server.sql.*;

import Server.repository.ModelInfoVehicle;
import Server.repository.ModelP;
import Server.repository.ModelPersonel;
import Server.repository.ModelPieceOperation;
import Server.repository.ModelViewListOp;
import Server.repository.Modelchef;
import Server.serialization.Deserialization;
import Server.repository.*;

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
    private HandlerSQL hsql = new HandlerSQL() ;
    //variable pour le controllerP
    
    int idOperation =1;
    String matricul ;
    String panne ;
    int idpanne  ;
    String loginRepair ;

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
                    loginRepair= login ;


                    server.msg.append("\n auth = " + login + " " + pwd);
                    server.msg.append("\n response " + res);

                    out.println(res);
                    out.flush();

                }else if (cmd.equals(EnumService.AFFICHE_PSORT.name())){

                	server.msg.append("\n"+EnumServiceYoucef.AFFICHE_PSORT.name());
                	//we call ModelViewListOp
                	ModelViewListOp m= new ModelViewListOp();
                	//on construit notre requette sql
                	String req =m.getListFromBd();
                	server.msg.append("\n"+req);
                	ResultSet res=hsql.selectQuery(req);
                	
                	server.msg.append("\n recupï¿½ration de la base");
                	//on recupï¿½re ligne par ligne 
                	
                	String lineBD = "";
                	int nb=0;
                	String nbS="";
                	
                	out.println("DEBUT DENVOI");
                	out.flush();
                	while(res.next()){
                		nb++;
                		nbS=Integer.toString(nb);
                		lineBD= lineBD + "Operation N° "+nbS+" Voiture :  "+res.getString(1)+
                				"            Panne :  "+res.getString(2)+" id_operation : "+res.getInt(3)+"\n";
                		
                		
                		}
                	out.println(nbS);
                	out.flush();
                	out.print(lineBD);
                
            		out.flush();
            		
            		System.out.println("ce que jai envoyé  :"+ lineBD);
            		server.msg.append("\n"+ lineBD ); 


                	//on envoi au client le resultat
                	
                	
                	
                	
                
                	
                	
                	
                }else if(cmd.equals(EnumServiceYoucef.IMPORT_PIECE.name())){
                	

                	server.msg.append("import piece\n");
                    ModelPieceOperation mp = new ModelPieceOperation();

                   String nomPiece = in.readLine();//recupï¿½ration du nom de la piï¿½ce
                   String qteS =in.readLine(); //recupï¿½ration de la quantitï¿½
                   
                   if(mp.estUnEntier(qteS)){///
                	   
                   
                   
                   
                   server.msg.append(nomPiece + " | "+ qteS);
                   int qte=Integer.parseInt(qteS);
                  String exist =mp.testExist(nomPiece) ;
                  String select= mp.testtPiece(nomPiece);//on select le nombre qte afin de le tester
                  
                  ResultSet rsExist=hsql.selectQuery(exist);
                  if (!rsExist.next()){
                	  out.println("piece not exist");
                	  out.flush();
                	  
                  }else {
                  
                  ResultSet rs= hsql.selectQuery(select);
                  
                  server.msg.append("conection avec la BD faite");
                  if (rs.next()){
                  if (rs.getInt("counter") - qte< 0){
                	  server.msg.append("\nImpossible return") ;
                	  out.println("impossible");
                	  out.flush();
                	  ///jusquici tout vas bien
                	  
                  }else{//on modifi la table piï¿½ce en stock
                	  
                      String modif=mp.importPiece(nomPiece, qte);
                      
                      server.msg.append("\n"+modif);
                      int rep =hsql.updateQuery(modif);
                      
                      server.msg.append("\n"+rep+"  lignes modifiï¿½");
                      //recupï¿½rï¿½le id de la piï¿½ce 
                      String rec =mp.recupereridPiece(nomPiece);
                      ResultSet rs2 =hsql.selectQuery(rec);
                      String id_piece="";
                      if(rs2.next()){
                    	  id_piece=rs2.getString(1);
                    	  server.msg.append("\nid piece :" + id_piece);
                      //recupï¿½ration de lid 
                    	  //
                      
                    	  String inser =mp.insertPieceConso(id_piece, nomPiece, qte, idOperation);
                    	  int rep2 = hsql.updateQuery(inser);
                    	  server.msg.append("\n"+rep2+" lignes ont ï¿½tï¿½ ajouter a la table des piece consomï¿½es");
                    	  if (rep2 != 0){
                    		  String ef="effect";
                    		  out.println(ef);
                              out.flush();
                    	  }//pour lui dire que cest effectuï¿½ 
                      
                      }
                  
                
                      }}
                   server.msg.append("\n" + nomPiece +"///////"+ qte);

                } }else{
                	String ef="err Qte";//le serveur dï¿½tï¿½cte que la qte n'est pas un entier
          		    out.println(ef);
                    out.flush();
                	
               
                }
            
                	
                	
                }else if (cmd.equals(EnumServiceYoucef.GET_OPERATION_SORT.name())){

                	//on appel le modelP
                	ModelP mdlp = new ModelP();
                	String reqMaxRang =mdlp.selectMaxRang();
                    server.msg.append("\n"+"recupï¿½ration du degrï¿½ max ");
                    server.msg.append("\n"+reqMaxRang);


                	
                	ResultSet rsMax=hsql.selectQuery(reqMaxRang);
                	if (rsMax.next()){
                    int max =rsMax.getInt(1);
               
                	String reqOpSort =mdlp.selectMaxline(max);//ici on recupï¿½rï¿½e l'operation priorisï¿½e 
                    server.msg.append("\n"+reqOpSort);
     
                	ResultSet rsMAX=hsql.selectQuery(reqOpSort);
                    if(rsMAX.next()){
                        server.msg.append("\n operation trouvï¿½");

                    int idOp =rsMAX.getInt(1);
                    int idOp_Op =rsMAX.getInt(2);
                    
                    idOperation=idOp_Op;
                    server.msg.append("\n identifiant de loperation : "+idOperation);
                    
                    //on renseigne les champs time_begin et date begin
                    String renseign =mdlp.setTime(idOp_Op);
                    int ligneModif =hsql.updateQuery(renseign);
                    
                    server.msg.append("\n les champs time et date on ï¿½tï¿½ renseignï¿½  "+ligneModif);
                    
                    
                    //int idPanne=rsMAX.getInt(3);
                    
                    
                    String numMat =rsMAX.getString(3);
                    matricul=numMat;
                    server.msg.append("\n matricul vehicle : "+matricul);

                    int rang =rsMAX.getInt(5);
                    
                    //on recupï¿½re l'idPanne
                    String reqIdPanne=mdlp.selectIdPanne(idOperation);
                    ResultSet resPanne =hsql.selectQuery(reqIdPanne);
                    if (resPanne.next()){
                    	 idpanne = resPanne.getInt(1);
                    }
                    
                    //on recupï¿½re le motif 
                    
                    String reqMotif =mdlp.selectMotif(idpanne);
                    server.msg.append("\n"+reqMotif);

                    ResultSet rsMotif=hsql.selectQuery(reqMotif);
                    if(rsMotif.next()){
                    	String namePanne =rsMotif.getString("name");
                    	panne=namePanne ;
                    	out.println(namePanne);
                    	out.flush();
                        server.msg.append("\n"+"motif envoyï¿½");

                    }
                    //on recupï¿½re le nom du vï¿½hicule
                    String reqVehicle =mdlp.selecVehicle(numMat);
                    server.msg.append("\n"+reqVehicle);
                    ResultSet rss=hsql.selectQuery(reqVehicle);
                    if (rss.next()){
                    server.msg.append("\n"+"info envoyï¿½");

                    String mdl= rss.getString(2);
                    out.println(mdl);
                    out.flush();
                    out.println(numMat);
                    out.flush();
                    server.msg.append("\n"+"info envoyï¿½");
                    }
                    
            
                  
                    
          
                    }
                	}
                	
                	
                
                	
                }else if (cmd.equals(EnumServiceYoucef.SHOW_INFO.name())){
                	

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
                		int numPlace=resInfo3.getInt(2);
                		String numPlaceS=Integer.toString(numPlace);
                		out.println(numPlaceS);
                		out.flush();
                		
                		Date d =resInfo3.getDate(3);
                		String date=d.toString();
                		out.println(date);
                		out.flush();
                		
                	}
                	
                	
                	
                	
                
                }else if (cmd.equals(EnumServiceYoucef.ADD_OP.name())){

                	server.msg.append("\n"+EnumServiceYoucef.ADD_OP.name());
                	
                	ModelNewOp mOp= new ModelNewOp();
                	String nomDEpanne = in.readLine();
                	
                	String reqTest=mOp.testPanne(nomDEpanne);
                	server.msg.append("\n"+reqTest);
                	
                	ResultSet rsOp=hsql.selectQuery(reqTest);
                	if(rsOp.next()){
                    	server.msg.append("\n"+"panne existante");

                		out.println("panne exist");
                		out.flush();
                		int idBreakdown = rsOp.getInt(1);
                		String reqInsert=mOp.ReqInserOp(idBreakdown, loginRepair, matricul);
                    	server.msg.append("\n"+reqInsert);

                		int nb=hsql.updateQuery(reqInsert);
                    	if(nb!=0){
                        	server.msg.append("\n"+nb+"  lignes ajouté");
                        	out.println("ajouté");
                        	out.flush();

                    	}

                		
                		
                	}else{
                		out.println("panne not exist");
                		out.flush();
                		
                	
                	
                	}
                	
                
                }else if(cmd.equals(EnumServiceYoucef.FINIR.name())){ 

                	ModelFin mf = new ModelFin();
                	String reqRenseign = mf.reqRenseigne(idOperation, loginRepair);
                	String reqSup =mf.supOpSort(idOperation);
                	String reqCount=mf.compterOp(matricul);
                	String reqChangeStatus=mf.modifStatu(matricul);
                	
                	//on renseigne les information au niveau de la table operation
                	int nbR = hsql.updateQuery(reqRenseign);
                	if(nbR!=0){
                		server.msg.append("\n" + reqRenseign);
                		
                		//on suprime loperation_sort
                		int nbS=hsql.updateQuery(reqSup);
                		
                		if (nbS!=0){
                    		server.msg.append("\n" + reqSup);
                    	//on compte le nombre d'operation restante pour ce vehicule
                    	
                    		ResultSet result=hsql.selectQuery(reqCount);
                    		
                    		if(result.next()){
                        		server.msg.append("\n" + reqCount);
                        		int nombreDop =result.getInt(1);
                        		String nombreDopS=Integer.toString(nombreDop);
                        		out.println(nombreDopS);
                        		
                        		//on teste si il est null afin de changer le statu du véhicule en "réparé"
                        		
                        		if(nombreDop==0){
                        			server.msg.append("\n  il n'ya plus d'operation pour ce vehcul");
                        			int nbChange =hsql.updateQuery(reqChangeStatus);
                        			if(nbChange!=0){
                        				server.msg.append("\n"+reqChangeStatus);
                        				
                        				
                        			}
                        		}

                    		}

                		}
                		
                		
                	}
                	
                	
                	
                	
                	
                
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
                }else if (cmd.equals(EnumService.MOTIFDOWNLOAD.name())){
                	String jsonString = in.readLine();
                	
                	//System.out.println(jsonString);
                    //server.msg.append("\n" + jsonString);
               	String strListMotif = serv.visitingMotifService(jsonString);
               	System.out.println(strListMotif);
               	out.println(strListMotif);
               	out.flush();
               }else if(cmd.equals(EnumService.VEHICLEDOWNLOAD.name())){
                	String vehicle = in.readLine();
               
                	//Server.server.msg.append("\n vehicle information : " + vehicle);
                	
                	String vehicleInfo = serv.vehicleNumMatService(vehicle);
                	System.out.println(vehicleInfo);
                	out.println(vehicleInfo);
                	out.flush();
                }else if(cmd.equals(EnumService.SAVEVEHICLE.name())){
                	String etape = in.readLine();
                	if(etape.equals("Un")){
                    String infoB = in.readLine();
                     	//System.out.println(infoB);
                    String infoV = in.readLine();
                     	//System.out.println(infoV);
                    String infoR = in.readLine();
                         //System.out.println(infoR);
                    String res = serv.saveVehicle(infoB, infoV, infoR);
                    out.println(res);
                    out.flush();
                    }
                	if(etape.equals("Deux")){
                		String infoMotif = in.readLine();
                     
                		String infoV = in.readLine();
                		
                		String res = serv.saveVehicle2(infoMotif, infoV);
                        out.println(res);
                        out.flush();
                	}
                	if(etape.equals("Trois")){
                		String numMat = in.readLine();
                		
                		String numP = in.readLine();
                		
                		String res = serv.saveVehicle3(numP, numMat);
                        out.println(res);
                        out.flush();
                	}
                }else if(cmd.equals(EnumService.PARKINGDOWLOAD.name())){
                    
                    String res = serv.getParking();
                    out.println(res);
                    out.flush();
                } else if(cmd.equals(EnumService.SEARCHVEHICLE.name())){
                	//System.out.println("debug");
                	String date_end=in.readLine();//partie rajoutÃ©e
                	System.out.println(date_end);
                	Vector<Vehicle_warehouseDTOL> d=serv.VehiclenumMatServiceAll(date_end);
                	System.out.println("get vector " + d.size());
                	out.println(d.size());
                	for(int i=0; i<d.size(); i++){
                		out.println(d.get(i).getNumMat());
                		out.flush();
                	}
                	
                }else if(cmd.equals(EnumService.TABLE.name())){
                	System.out.println("table case");
                	String d=serv.Table();
                	out.println(d);
                	out.flush();
                	
                }
                
                else if(cmd.equals(EnumService.SEARCHInfoVEHICLE.name())){
                	server.msg.append("service serch vehicle.\n");
                	String numMat=in.readLine();
                	String d=serv.Vehiclenumat(numMat);
                	out.println(d);
                	out.flush();
               }  else if(cmd.equals(EnumService.VEHICLERELOCATE.name())){
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
                    }
                }else if(cmd.equals("Historique")){
                    	
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
	                            	z ="entrï¿½:"+rs.getString("date_reception");
	                            	server.msg.append("\n Result search = " + z);}
	                            while (rs2.next()){
	                            	 z1 ="sortie:"+rs2.getString("date_reception");
	                            	 server.msg.append("\n Result search = " + z1);
	                            }
	                            out.println(z+" "+z1);
	                        }
                    } 
	            }
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
		               } else if(cmd.equals("chef")){
                        String jsonString = in.readLine();
                        System.out.println(jsonString);
                        server.msg.append("\n" + jsonString);

                        Deserialization des = new Deserialization();
                        JSONObject jo = des.deserialGeneric(jsonString);
                        int action = des.deserialAction(jo);//get action

                        /**
                         * Now we have action and vehicle, should call the Model
                         * */
                        Modelchef modechef = new Modelchef();
                        RepairerDTO dto = new RepairerDTO();
                        OperationDTOAli DTOp = new OperationDTOAli();
                        if(des.deserialObject(jo, EnumDTO.REPAIRER.getName()) != null){
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
                                int i=0;
                                while(rs2.next()){
                                		v="reparateur :"+rs2.getString("login_repairer")+"\n"+" Numero Matricule :"+rs2.getString("numMat")+"\n"+" date_begin :"+rs2.getString("date_begin")
                                		+"  date_end :"+rs2.getString("date_end")+"  time_end"+rs2.getString("time_end")+"\n"+"id_operation :"
                                		+rs2.getString("id_operation")+"  id_breakdown"+rs2.getString("id_breakdown");
                                	server.msg.append("\nQuery select login=" + v);
                                	z=z+v+"\n";
                                     i++;  }
                               out.println(i+"\n"+z);
                               
                         }
                    }
                } else if(cmd.equals("personelMatricule")){
                    String jsonString = in.readLine();
                    System.out.println(jsonString);
                    server.msg.append("\n" + jsonString);

                    Deserialization des = new Deserialization();
                    JSONObject jo = des.deserialGeneric(jsonString);
                    int action = des.deserialAction(jo);//get action

                    
                    ModelPersonel modelPersonel = new ModelPersonel();
                    VehicleDTOAli dto = new VehicleDTOAli();
                    OperationDTOAli DTOp = new OperationDTOAli();
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
                          out.println(z);
                        }		
                   }
               }
                
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
