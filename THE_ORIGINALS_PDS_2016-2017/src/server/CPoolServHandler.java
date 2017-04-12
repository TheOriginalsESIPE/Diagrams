package server;

import dto.VehicleDTO;
import enumeration.EnumOperation;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import repository.ModelAuth;
import repository.ModelVehicle;
import serialization.Deserialization;
import sql.HandlerSQL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.SQLException;

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

    public void run(){
        server.msg.append("\nRUN...");
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintStream out = new PrintStream(client.getOutputStream());
            while(true) {
                //CASE: asking the authentication, here we don't implement the encryption service.
                if (in.readLine().equals("auth")) {
                    String login = in.readLine();
                    String pwd = in.readLine();
                    server.msg.append("\n auth = " + login + " " + pwd);
                    ModelAuth ma = new ModelAuth();
                    HandlerSQL hsql = new HandlerSQL();
                    boolean bool = hsql.selectQuery(ma.authQuery(login, pwd)).next();
                    String res = bool == true ? "true" : "false";
                    server.msg.append("\n query = " + ma.authQuery(login, pwd));
                    server.msg.append("\n response " + res);
                    out.println(res);
                } else {
                    //wait for generic json
                    String jsonString = in.readLine();
                    Deserialization des = new Deserialization();
                    JSONObject jo = des.deserialGeneric(jsonString);
                    int action = des.deserialAction(jo);
                    /**
                     * Now we have action and vehicle, should call the ModelVehicle
                     * */
                    ModelVehicle modelVehicle = new ModelVehicle();
                    if (action == EnumOperation.DELETE.getIndex()) {
                        //modelVehicle.delete(vehicleDTO.getNumMat());
                    } else if (action == EnumOperation.INSERT.getIndex()) {
                        //Didn't find the method....
                    } else if (action == EnumOperation.UPDATE.getIndex()) {
                        //modelVehicle.update(vehicleDTO.getNumMat());
                    } else if (action == EnumOperation.SEARCH.getIndex()) {
                        //modelVehicle.select(vehicleDTO.getNumMat());
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
