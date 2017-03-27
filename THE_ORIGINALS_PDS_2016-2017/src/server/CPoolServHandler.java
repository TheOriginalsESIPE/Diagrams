package server;

import dto.VehicleDTO;
import enumeration.EnumOperation;
import org.json.simple.parser.ParseException;
import repository.ModelVehicle;
import serialization.Deserialization;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.sql.SQLException;

/**
 * Created by tearsyu on 15/03/17.
 * This class define the service of all the connection of server
 * In R2, this class is responsible to get the info of serialization from client,
 * unserialize it then pass it to DB.
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
        server.msg.append("RUN...");
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String jsonString = in.readLine();
            Deserialization des = new Deserialization();

            VehicleDTO vehicleDTO = des.deserialAVehicle(des.parseStringToJson(jsonString));
            int action = des.deserialAction(des.parseStringToJson(jsonString));
            /**
             * Now we have action and vehicle, should call the Model
             * */
            ModelVehicle modelVehicle = new ModelVehicle();
            if (action == EnumOperation.DELETE.getIndex()){
                modelVehicle.delete(vehicleDTO.getNumMat());
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
