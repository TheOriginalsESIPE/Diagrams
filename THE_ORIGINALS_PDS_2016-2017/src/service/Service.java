package service;

import dto.IndicatorDTO;
import dto.Piece_detachedDTO;
import enumeration.EnumDTO;
import enumeration.EnumOperation;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import repository.ModelAuth;
import repository.ModelIndicatorActivity;
import repository.ModelPiece;
import serialization.Deserialization;
import serialization.Serialization;
import serialization.SerializationGson;
import sql.HandlerSQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * The class service is using for reacting with HandleSQL, like CPoolHandle deal with the command that
 * the client send, this class Service offers a bundle of methods that executing the code according to the command.
 * And it receives the result from sql server, and give a response to CPoolServHandler(For example, if it was a
 * "Select", Service generates an object of DTO or a list of DTO and serialize it to String json
 * and send it as the response to CPoolServHandler. If it was a "UPDATE" or "DELETE", it easier to manipulate,
 * Service just give a number as an indicator to response to CPoolServHandler.) Then CPoolServHandler send this
 * response to Client.
 * Created by tearsyu on 18/05/17.
 */
public class Service {
    HandlerSQL hsql;
    public Service(){
        hsql = new HandlerSQL();
    }



    public String authService(String login, String pwd){
        ModelAuth ma = new ModelAuth();
        String res = "false";
        try {
            boolean bool = hsql.selectQuery(ma.authQuery(login, pwd)).next();
            res = bool == true ? "true" : "false";
            System.out.println(res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(res);
        return  res;
    }


    public String pieceService(String strJson){
        Deserialization des = new Deserialization();
        JSONObject jo = des.deserialGeneric(strJson);
        int action = des.deserialAction(jo);//get action

        if(des.deserialObject(jo, EnumDTO.PIECE_DETACHED.getName()) != null) {
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


            if (action == EnumOperation.DELETE.getIndex()) {
                int nb = hsql.updateQuery(modelPiece.delete(dto.getRef_piece_detached()));
                return String.valueOf(nb);
            } else if (action == EnumOperation.INSERT.getIndex()) {
                String query = modelPiece.insert(dto.getRef_piece_detached(), dto.getName(), dto.getMark(), dto
                        .getModel(), dto.getPrice());
                int nb = hsql.updateQuery(query);
                return String.valueOf(nb);
            } else if (action == EnumOperation.UPDATE.getIndex()) {
                String query = modelPiece.update(dto.getRef_piece_detached(), dto.getPrice());
                int nb = hsql.updateQuery(query);
                return String.valueOf(nb);
            } else if (action == EnumOperation.SEARCH.getIndex()) {
                String query = modelPiece.select(dto.getRef_piece_detached());
                ResultSet rs = hsql.selectQuery(query);
                try {
                    while (rs.next()) {
                        dto.setModel(rs.getString("model"));
                        dto.setName(rs.getString("name"));
                        dto.setMark(rs.getString("mark"));
                        dto.setPrice(rs.getFloat("price"));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Serialization ser = new Serialization();
                String res  = ser.serialToStr(ser.serialGeneric(EnumOperation.RESPONSE.getIndex(), dto.getClass().getSimpleName(), dto));
                return res;
            }

        }
        return "error";
    }

    public String indicatorService(String strJson){
        Deserialization d = new Deserialization();
        HashMap indicatorMap = d.deserialGeneric(strJson);
        System.out.println(indicatorMap.toString());
        List<IndicatorDTO> arrIndicator;
        String res = "";
        try{
            ModelIndicatorActivity modelIA = new ModelIndicatorActivity(indicatorMap);
            hsql = new HandlerSQL();
            ResultSet rs = hsql.selectQuery(modelIA.getOperation());
            System.out.println(modelIA.getOperation());
            int numRes = rs.getFetchSize();
            arrIndicator = new ArrayList<>(numRes);
            while(rs.next()){
                IndicatorDTO idc = new IndicatorDTO();
                idc.setNumMat(rs.getString("numMat"));
                idc.setRepairer(rs.getString("repairer"));
                idc.setvType(rs.getString("vehicle_type"));
                idc.setDateB(rs.getString("date_begin"));
                idc.setDateE(rs.getString("date_end"));
                idc.setBreaktype(rs.getString("name"));
                idc.setId(rs.getInt("id_operation"));
                idc.setPieceConso(0);
                arrIndicator.add(idc);
            }
            System.out.println("finish adding result of part operation. Size is :" + numRes);
            if(arrIndicator.size() == 0){
                return "None";
            }
            rs = hsql.selectQuery(modelIA.pieceConso());
            //There is a n*n complexity!!! it could be better if I use Map<Integer, IndicatorDTO>
            // But I don't want to rewrite my code. Let it be!
            while(rs.next()){
                int id = rs.getInt("id_operation");
                int num = rs.getInt("conso");
                for (IndicatorDTO idc : arrIndicator){
                    if(idc.getId() == id){
                        idc.setPieceConso(num);
                    }
                }
            }
            System.out.println("With a calculate of n*n complexity, finaly I finish my service.");

            SerializationGson s = new SerializationGson();
            res = s.serialIndict(arrIndicator);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }


}
