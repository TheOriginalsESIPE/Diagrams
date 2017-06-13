package Server.server;
import Server.dto.Vehicle_warehouseDTO;
import Server.enumeration.EnumService;
import Server.service.Service;
//import dto.Vehicle_warehouseDTO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Vector;

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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
