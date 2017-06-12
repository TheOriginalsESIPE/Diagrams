package Client.serialization;

import Server.dto.BreakdownDTO;
import Server.dto.IndicatorDTO;
import Server.dto.VehicleDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
}
