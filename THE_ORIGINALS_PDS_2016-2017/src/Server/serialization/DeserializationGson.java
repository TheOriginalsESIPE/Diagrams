package Server.serialization;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Server.dto.BreakdownDTO;
import Server.dto.IndicatorDTO;
import Server.dto.ParkingDTO;
import Server.dto.VehicleDTO;
//import Server.dto.VisitingMotifDTO;
import Server.dto.Vehicle_warehouseDTO;
import Server.dto.Vehicle_warehouseDTOL;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Vector;

/**
 * Created by tearsyu on 10/05/17.
 */
public class DeserializationGson {
    Gson g;
    public DeserializationGson(){
        g = new Gson();
    }
    public List<IndicatorDTO> deIndicatorDTO(String str){
        Type type = new TypeToken<List<IndicatorDTO>>(){}.getType();
        List<IndicatorDTO> arrIndicator = g.fromJson(str, type);
        return arrIndicator;
    }
    
   
    public Vector<BreakdownDTO> deserialBreakdownDTO(String str){
        Type type = new TypeToken<Vector<BreakdownDTO>>(){}.getType();
        Vector<BreakdownDTO> breakdownList = g.fromJson(str, type);
        return breakdownList;
    }
    
    public Vector<VehicleDTO> deserialVehicleDTO(String str){
        Type type = new TypeToken<Vector<VehicleDTO>>(){}.getType();
        Vector<VehicleDTO> vehicleInfo = g.fromJson(str, type);
        return vehicleInfo;
    }
    
    /*public Vector<VisitingMotifDTO> deserialVisitingMotif(String str){
        Type type = new TypeToken<Vector<VisitingMotifDTO>>(){}.getType();
        Vector<VisitingMotifDTO> visitingMotifList = g.fromJson(str, type);
        return visitingMotifList;
    }*/
    
    public Vector<ParkingDTO> deserialParkingDTO(String str){
        Type type = new TypeToken<Vector<ParkingDTO>>(){}.getType();
        Vector<ParkingDTO> listPark = g.fromJson(str, type);
        return listPark;
    }
    
    public Vector<Vehicle_warehouseDTOL> desVehicle_warehouseDTOL(String s){
    	Type t=new TypeToken<Vector<Vehicle_warehouseDTOL>>(){}.getType();
    	Vector<Vehicle_warehouseDTOL> list1=g.fromJson(s,t);
    	return list1;
    }
}
