package service;

import dto.BreakdownDTO;
import dto.IndicatorDTO;
import dto.Piece_detachedDTO;
import dto.VehicleDTO;
import enumeration.EnumDTO;
import enumeration.EnumOperation;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import repository.ModelAuth;
import repository.ModelIndicatorActivity;
import repository.ModelPiece;
import repository.ZDialogVehicleInfoRepository;
import serialization.Deserialization;
import serialization.Serialization;
import serialization.SerializationGson;
import sql.HandlerSQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;


/**
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
        
        Object nom = des.deserialObject(jo, EnumDTO.PIECE_DETACHED.getName());
    	System.out.println("le nom de l'objet"+nom);

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
            res = s.serialGeneric(arrIndicator);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
    
    
    public String breakdownServices(String strJson){
    	Deserialization deserial = new Deserialization();
    	Vector<BreakdownDTO> vectorList = new Vector<BreakdownDTO>();
    	JSONObject listBreakdown = deserial.deserialGeneric(strJson); 
    	String listNameBreakdown = null;
    	
    	try{
    		ZDialogVehicleInfoRepository zdvr = new ZDialogVehicleInfoRepository();
    		//hsql = new HandlerSQL();
    		
    		ResultSet resultSet = hsql.selectQuery(zdvr.getBreakdown());
    		
    		while(resultSet.next()){
    			BreakdownDTO breakdownDTO = new BreakdownDTO();
    			breakdownDTO.setName(resultSet.getString("name"));
    			vectorList.add(breakdownDTO);
    		} 
    		
    		SerializationGson serial = new SerializationGson();
    		listNameBreakdown = serial.serialGenericBreakdown(vectorList);
    		
    	}catch(SQLException sqle){
    		sqle.printStackTrace();
    	}
    	return listNameBreakdown;
    }
    
    
    
    public String vehicleNumMatService(String strJson){
    	
    	Deserialization deserial = new Deserialization();
    	JSONObject jsonObject = deserial.deserialGeneric(strJson);
    	int action = deserial.deserialAction(jsonObject);
    	
    	Vector<VehicleDTO> vectorList = new Vector<VehicleDTO>();
    	String vehicleInfo = null;
    	 VehicleDTO vehicledto = new VehicleDTO();
    	System.out.println(vehicledto.getNumMat());
    	Object nom = deserial.deserialObject(jsonObject, EnumDTO.VEHICLE.getName());
    	System.out.println("le nom de l'objet"+nom);
    	
    	if(nom != null){
    		ZDialogVehicleInfoRepository zdvr = new ZDialogVehicleInfoRepository();
    		
    		JSONArray jsonArray = (JSONArray) deserial.deserialObject(jsonObject, EnumDTO.VEHICLE.getName());
    		jsonObject = (JSONObject) jsonArray.get(0);
           // VehicleDTO vehicledto = new VehicleDTO();
            
            vehicledto.setNumMat((String) jsonObject.get("numMat"));
            System.out.println((String) jsonObject.get("numMat"));
            vehicledto.setModel((String) jsonObject.get("model"));
            vehicledto.setMark((String) jsonObject.get("mark"));
            vehicledto.setVehicle_type((String) jsonObject.get("vehicle_type"));
            
            if(action == EnumOperation.SEARCH.getIndex()){
            	System.out.println(vehicledto.getNumMat());
            	ResultSet resultSet = hsql.selectQuery(zdvr.getVehicle(vehicledto.getNumMat()));
        		try{
        		while(resultSet.next()){
        			vehicledto.setNumMat(resultSet.getString("numMat"));
        			vehicledto.setModel(resultSet.getString("model"));
        			vehicledto.setMark(resultSet.getString("mark"));
        			vehicledto.setVehicle_type(resultSet.getString("vehicle_type"));
        			vectorList.add(vehicledto);
        		}
            }catch(SQLException sqle){
        		sqle.printStackTrace();
        	}
    		
    		SerializationGson serial = new SerializationGson();
    		vehicleInfo = serial.serialGenericVehicle(vectorList);
    		
    	}
           }else System.out.println("je suis pas dans le If");
    	return vehicleInfo;
    }


}
