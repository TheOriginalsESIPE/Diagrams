package Server.server;

import enumeration.EnumService;

import repository.ModelAuth;
import repository.ModelInfoVehicle;
import repository.ModelNewOp;
import repository.ModelP;
import repository.ModelPieceY;
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

public class CPoolServHandlerYoucef extends Thread {
    private Socket client;
    private Server server;
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
    public CPoolServHandlerYoucef(Socket client, Server server){
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
                    loginRepair= login ;
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
                    ModelPieceY mp = new ModelPieceY();

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
                	  
                  }else {
                  
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
                    	  id_piece=rs2.getString(1);
                    	  server.msg.append("\nid piece :" + id_piece);
                      //recupération de lid 
                    	  //
                      
                    	  String inser =mp.insertPieceConso(id_piece, nomPiece, qte, idOperation);
                    	  int rep2 = hsql.updateQuery(inser);
                    	  server.msg.append("\n"+rep2+" lignes ont été ajouter a la table des piece consomées");
                    	  if (rep2 != 0){
                    		  String ef="effect";
                    		  out.println(ef);
                              out.flush();
                    	  }//pour lui dire que cest effectué 
                      
                      }
                  
                
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
            		lineBD= lineBD + "Operation N° "+nbS+" Voiture :  "+res.getString(1)+
            				"            Panne :  "+res.getString(2)+" id_operation : "+res.getInt(3)+"\n";
            		
            		
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
            	String reqMaxRang =mdlp.selectMaxRang();
                server.msg.append("\n"+"recupération du degré max ");
                server.msg.append("\n"+reqMaxRang);


            	
            	ResultSet rsMax=hsql.selectQuery(reqMaxRang);
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
                
                //int idPanne=rsMAX.getInt(3);
                
                
                String numMat =rsMAX.getString(3);
                matricul=numMat;
                server.msg.append("\n matricul vehicle : "+matricul);

                int rang =rsMAX.getInt(5);
                
                //on recupére l'idPanne
                String reqIdPanne=mdlp.selectIdPanne(idOperation);
                ResultSet resPanne =hsql.selectQuery(reqIdPanne);
                if (resPanne.next()){
                	 idpanne = resPanne.getInt(1);
                }
                
                //on recupére le motif 
                
                String reqMotif =mdlp.selectMotif(idpanne);
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
            		int numPlace=resInfo3.getInt(2);
            		String numPlaceS=Integer.toString(numPlace);
            		out.println(numPlaceS);
            		out.flush();
            		
            		Date d =resInfo3.getDate(3);
            		String date=d.toString();
            		out.println(date);
            		out.flush();
            		
            	}
            	
            	
            	
            	
            }else if (cmd.equals(EnumService.ADD_OP.name())){
            	server.msg.append("\n"+EnumService.ADD_OP.name());
            	
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
            	
            	
            	
            	
            	
            	
            	
            }
            }
        } catch (IOException e) {
                e.printStackTrace();
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
}
