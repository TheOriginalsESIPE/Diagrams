package Server.service;

import Server.dto.BreakdownDTO;
import Server.dto.IndicatorDTO;
import Server.dto.Piece_detachedDTO;
import Server.dto.VehicleDTOL;
import Server.enumeration.EnumDTO;
import Server.enumeration.EnumOperation;
import Server.enumeration.EnumUserToken;
import Server.serialization.Deserialization;
import Server.serialization.Serialization;
import Server.serialization.SerializationGson;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import Server.repository.ModelAuth;
import Server.repository.ModelIndicatorActivity;
import Server.repository.ModelPiece;
import Server.repository.ModelVehicle;
import Server.repository.ZDialogVehicleInfoRepository;
import Server.sql.HandlerSQL;

import Server.dto.Vehicle_warehouseDTOL;
import Server.dto.WarehouseDTOL;
//import repository.ModelVehicle;
import Server.dto.VehicleDTO;

import Server.dto.ParkingDTO;

import Server.dto.Vehicle_warehouseDTO;
import Server.dto.WarehouseDTO;

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
    private HandlerSQL hsql;
    public Service(){
        hsql = new HandlerSQL();
    }

    /**
     * This method returns the result of login Client.view, if the user is valid,
     * authService() returns his role(admin or director or repairer or manutentionaire)
     * @param login
     * @param pwd
     * @return
     */
    public String authService(String login, String pwd){
        ModelAuth ma = new ModelAuth(login, pwd);
        String res = "false";
        try {
            boolean bool = hsql.selectQuery(ma.authAdmin()).next();
            if (bool){
                res = EnumUserToken.ADMINISTRATOR.name();
            } else if (hsql.selectQuery(ma.authDirector()).next()){
                res = EnumUserToken.DIRECTOR.name();
            } else if (hsql.selectQuery(ma.authRepairer()).next()){
                res = EnumUserToken.REPAIROR.name();
            } else if (hsql.selectQuery(ma.authManut()).next()){
                res = EnumUserToken.MANUT.name();
            }
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

            //Deserialize the Server.dto object and get a jsonArray
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
            System.out.println("With a calculate of n*n complexity, finaly I finish my Server.service.");

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
    

 

    public String saveHehicle(String infoB, String infoV, String infoR){
		ZDialogVehicleInfoRepository zdvr = new ZDialogVehicleInfoRepository();
		//System.out.println(infoB+""+infoV+""+infoR);
       	int query = hsql.updateQuery(zdvr.setOperation(infoB, infoV, infoR));
    
       	return String.valueOf(query);
}

public String getParking(){
	
	Vector<ParkingDTO> vectorList = new Vector<>();
	ZDialogVehicleInfoRepository zdvr = new ZDialogVehicleInfoRepository();
	ResultSet resultSet = hsql.selectQuery(zdvr.getPark());
	try{
	while(resultSet.next()){
		ParkingDTO parkingDTO = new ParkingDTO();
		parkingDTO.setNumPlace(resultSet.getInt("numPlace"));
		vectorList.add(parkingDTO);
	}
}catch(SQLException sqle){
	sqle.printStackTrace();
}

SerializationGson serial = new SerializationGson();
String parking = serial.serialGenericVehicle(vectorList);
System.out.println(parking);

return parking;
}
    
    public Vector<Vehicle_warehouseDTOL> VehiclenumMatServiceAll(String date_end){
    	System.out.println("vehicle num sevice");
    	Vector<Vehicle_warehouseDTOL> list=new Vector<Vehicle_warehouseDTOL>();
    	//String date_end1="";
    	
    	try{
    		Server.repository.ModelVehicle mv=new Server.repository.ModelVehicle();
    		 hsql = new HandlerSQL();
    		 ResultSet rs=hsql.selectQuery(mv.selectAll(date_end));
    		 System.out.println("get rs");
    		 while(rs.next()){
    			 Vehicle_warehouseDTOL vh=new Vehicle_warehouseDTOL();
    			 vh.setNumMat(rs.getString("numMat"));
    			 list.add(vh);
    		 }
    	}
    	catch(SQLException sql){}
    	return list;
    }
    
    public String Vehiclenumat(String numMat){
  
    	System.out.println("le nom de l'objet"+numMat);

        Server.repository.ModelVehicle mv=new Server.repository.ModelVehicle();
        //Vehicle_warehouseDTO vh=new Vehicle_warehouseDTO();
        VehicleDTOL v=new VehicleDTOL();
            
       
    	
    	try{
    		 hsql = new HandlerSQL();
    		 ResultSet rs=hsql.selectQuery(mv.select(numMat));
    		 
    		 while(rs.next()){
    			 v.setNumMat(rs.getString("numMat"));
    			 v.setModel(rs.getString("model"));
    			 v.setMark(rs.getString("mark"));
    			 v.setVehicle_type(rs.getString("vehicle_type"));
    			 v.setNumPlace(rs.getInt("numPlace"));
    			 v.setStatus(rs.getString("status"));
    			
    		}
    	}
    	catch(SQLException sql){}
    	Serialization s=new Serialization();
    	return s.serialToStr(s.serialGeneric(EnumOperation.RESPONSE.getIndex(), v.getClass().getSimpleName(), v));
    }
    
    public String relocate(String numMat,int id_warehouse){
    	
    	 Server.repository.ModelVehicle mv=new Server.repository.ModelVehicle();
    	 Server.dto.WarehouseDTO w=new Server.dto.WarehouseDTO();
    	 String res = "";
    	 try{
    		 hsql = new HandlerSQL();
    		 ResultSet rs=hsql.selectQuery(mv.select1(numMat));
    		 int update=hsql.updateQuery(mv.update(numMat, id_warehouse));
    		 System.out.println(mv.select1(numMat));
    		 res = update + " ";
    		 while(rs.next()){
    			 res += rs.getString("adress") + " ";
    			 res += rs.getString("id_warehouse");
    			 
    		}
    		 
    	}
    	catch(SQLException sql){}
    	 
    	 return res;
    }
    
public String Table() {
		
		ModelVehicle mv=new ModelVehicle();
		List<Vehicle_warehouseDTOL> list=new ArrayList<Vehicle_warehouseDTOL>();
		
		String res="";
		
		try{
			
			ResultSet rs=hsql.selectQuery(mv.selectTable());
			while(rs.next()){
				String nM = rs.getString("numMat");
				int nP = rs.getInt("numPlace");
				String De = rs.getString("date_entrance");
				String Dw = rs.getString("date_wayout");
				String dbo = rs.getString("date_begin");
				String deo = rs.getString("date_end");
				String s = rs.getString("status");
				System.out.println(nP+ " ; " + nM + ";"+ De +" ; "+ Dw +" ; "+ dbo +" ; "+ deo+" ; "+ s +" ; ");
				Vehicle_warehouseDTOL vh=new Vehicle_warehouseDTOL();
				vh.setNumMat(nM);
				vh.setNumPlace(String.valueOf(nP));
				vh.setDate_entrance(De);
				vh.setDate_wayout(Dw);
				vh.setDateBeginOperation(dbo);
				vh.setDateEndOperation(deo);
				vh.setStatus(s);
				list.add(vh);
			}
			System.out.println("call table service." + rs.getRow());
			
			SerializationGson v=new SerializationGson();
			res=v.serialGenericVehicle_warehouseDTOL(list);
		}catch(SQLException e){}
		return res;
	}


}




