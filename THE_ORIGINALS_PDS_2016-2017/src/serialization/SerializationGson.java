package serialization;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dto.IndicatorDTO;

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

}
