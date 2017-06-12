package Server.serialization;

import Server.dto.BreakdownDTO;
import Server.dto.IndicatorDTO;
import Server.dto.VehicleDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by tearsyu on 10/05/17.
 */
public class SerializationGson {
	
    public String serialGeneric(Object o){
        Gson gson = new Gson();
        java.lang.reflect.Type obj = new TypeToken<List<IndicatorDTO>>(){}.getType();
        String parseString = gson.toJson(o, obj);
        return parseString;
    }
    
    
    public String serialGenericBreakdown(Object o){
        Gson gson = new Gson();
        java.lang.reflect.Type obj = new TypeToken<List<BreakdownDTO>>(){}.getType();
        String parseString = gson.toJson(o, obj);
        return parseString;
    }
    
    public String serialGenericVehicle(Object o){
        Gson gson = new Gson();
        java.lang.reflect.Type obj = new TypeToken<List<VehicleDTO>>(){}.getType();
        String parseString = gson.toJson(o, obj);
        return parseString;
    }

}
