package server;

import dto.Piece_detachedDTO;
import dto.VehicleDTO;
import enumeration.EnumDTO;
import enumeration.EnumOperation;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import repository.ModelAuth;
import repository.ModelPiece;
import serialization.Deserialization;
import sql.HandlerSQL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.ResultSet;
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
            HandlerSQL hsql = new HandlerSQL();
            while(true) {
                //CASE: asking the authentication, here we don't implement the encryption service.
                if (in.readLine().equals("auth")) {
                    String login = in.readLine();
                    String pwd = in.readLine();
                    server.msg.append("\n auth = " + login + " " + pwd);
                    ModelAuth ma = new ModelAuth();
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
                    if(des.deserialObject(jo, EnumDTO.PIECE_DETACHED.getName()) != null){
                        ModelPiece modelPiece = new ModelPiece();
                        Piece_detachedDTO dto = (Piece_detachedDTO) des.deserialObject(jo, EnumDTO.PIECE_DETACHED.getName());
                        if (action == EnumOperation.DELETE.getIndex()){
                            int nb = hsql.updateQuery(modelPiece.delete(dto.getRef_piece_detached()));
                            server.msg.append("\n Query DELETE= " + modelPiece.delete(dto.getRef_piece_detached()) );
                            out.println(nb);
                        } else if (action == EnumOperation.INSERT.getIndex()) {
                            String query = modelPiece.insert(dto.getRef_piece_detached(), dto.getName(), dto.getMark(), dto
                            .getModel(), dto.getPrice());
                            int nb = hsql.updateQuery(query);
                            server.msg.append("\n Query INSERT= " + query);
                            out.println(nb);
                        } else if (action == EnumOperation.UPDATE.getIndex()) {
                            String query = modelPiece.update(dto.getRef_piece_detached(),dto.getPrice());
                            int nb = hsql.updateQuery(query);
                            server.msg.append("\nQuery UPDATE=" + query);
                            out.println(nb);
                        } else if (action == EnumOperation.SEARCH.getIndex()) {
                            String query = modelPiece.select(dto.getRef_piece_detached());
                            ResultSet rs = hsql.selectQuery(query);
                            while (rs.next()){
                                dto.setModel(rs.getString("model"));
                                dto.setName(rs.getString("name"));
                                dto.setMark(rs.getString("mark"));
                                dto.setPrice(rs.getFloat("price"));
                            }
                            server.msg.append("\n Result search = " + dto.toString());
                            out.println(dto.toString());
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
