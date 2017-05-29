package serialization;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dto.IndicatorDTO;

import java.lang.reflect.Type;
import java.util.List;

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
}
