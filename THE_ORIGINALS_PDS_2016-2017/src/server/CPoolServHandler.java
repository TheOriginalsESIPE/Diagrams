package server;

import dto.Piece_detachedDTO;
import enumeration.EnumDTO;
import enumeration.EnumOperation;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import repository.ModelAuth;
import repository.ModelPiece;
import serialization.Deserialization;
import serialization.Serialization;
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

                } else if(cmd.equals("piece")){

                    String jsonString = in.readLine();
                    System.out.println(jsonString);
                    server.msg.append("\n" + jsonString);

                    Deserialization des = new Deserialization();
                    JSONObject jo = des.deserialGeneric(jsonString);
                    int action = des.deserialAction(jo);//get action

                    /**
                     * Now we have action and vehicle, should call the Model
                     * */
                    if(des.deserialObject(jo, EnumDTO.PIECE_DETACHED.getName()) != null){
                        ModelPiece modelPiece = new ModelPiece();

                        //Deserialize the dto object and get a jsonArray
                        JSONArray jsonArray = (JSONArray) des.deserialObject(jo, EnumDTO.PIECE_DETACHED.getName());
                        jo = (JSONObject) jsonArray.get(0);
                        Piece_detachedDTO dto = new Piece_detachedDTO();
                        dto.setModel((String) jo.get("model"));
                        dto.setMark((String) jo.get("mark"));
                        dto.setPrice(Float.parseFloat((String) jo.get("price")));
                        dto.setName((String) jo.get("name"));
                        dto.setRef_piece_detached((String) jo.get("ref_piece_detached"));

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
                            server.msg.append("\nQuery SELECT=" + query);
                            ResultSet rs = hsql.selectQuery(query);
                            while (rs.next()){
                                dto.setModel(rs.getString("model"));
                                dto.setName(rs.getString("name"));
                                dto.setMark(rs.getString("mark"));
                                dto.setPrice(rs.getFloat("price"));
                            }
                            server.msg.append("\n Result search = " + dto.toString());
                            Serialization ser = new Serialization();
                            String res  = ser.serialToStr(ser.serialGeneric(EnumOperation.RESPONSE.getIndex(), dto.getClass().getSimpleName(), dto));
                            out.println(res);
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
