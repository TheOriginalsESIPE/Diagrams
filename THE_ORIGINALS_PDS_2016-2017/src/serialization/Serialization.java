package serialization;

import dto.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by tearsyu on 07/04/17.
 * This class is used for serialize a generic object.
 * The main function is just a test code, you can ignore it.
 * @author tearsyu
 */
public class Serialization {

	/**
	 * This function is not generic, should be deleted.
	 * @deprecated
	 * @param action
	 * @param dto
     * @return JSONObject
     */
    public JSONObject serialisationDTO(int action , Object dto ) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        JSONObject root = new JSONObject();
         root.put("action", action);
        JSONObject V1 = new JSONObject();
        Field [] fields = dto.getClass().getDeclaredFields();
        for (Field field : fields){
            V1.put(field.getName(), dto.getClass().getDeclaredMethod("get"+field.getName(), null).invoke(dto, null));
        }
        JSONArray listDTO = new JSONArray();
        listDTO.add(V1);
         root.put("DTO", listDTO);
        return  root;
    }


	/**
	 * This function used to serial an action with an object dto.
	 * @param map
     * @return JSONObject
     */
	public JSONObject serialMap(Map map){
		JSONObject root = new JSONObject();
        root.putAll(map);
		return root;
	}


    public JSONObject serialGeneric(int action, String dtoName, Object o){
        JSONObject root = new JSONObject();
        root.put("action", action);
        JSONObject j = new JSONObject();
        JSONArray jlist = new JSONArray();

        jlist.add(j);
        root.put(dtoName, o);
        return root;
    }


    /**
	 * This function used to serialize an action with a list of dto to JSONObject.
	 * @param action
	 * @param dtoList
	 * @param
     * @return JSONObject
     */
	public JSONObject serialGeneric(int action, String dtoName, List dtoList){
		JSONObject root = new JSONObject();
        Deserialization d = new Deserialization();
		root.put("action", action);

		JSONArray array = new JSONArray();
		for (int i = 0; i < dtoList.size(); ++i){
            String str = dtoList.get(i).toString();
            JSONObject ele = new JSONObject();
            ele.put(dtoName, dtoList.get(i));
            array.add(ele);
		}
		root.put("list", array);
		return root;
	}

	/**
	 * Between server and client, the socket transfer the data in Stream,
	 * so json object need to be serial in string.
	 * @param jsonObject
	 * @return String a string of json
     */
	public String serialToStr(JSONObject jsonObject){
		String str = jsonObject.toJSONString();
		return str;
	}


    public static void main(String[] args){
        RepairerDTO r = null;
        List<RepairerDTO> list = new ArrayList<>();
        for(int i = 0; i < 4; ++i){
            r = new RepairerDTO();
            r.setLogin("mary"+ i);
            r.setAdress("44 route helsinki");
            r.setLastname("Ouvik" + i);
            list.add(r);
        }
        Serialization s = new Serialization();
        Deserialization d = new Deserialization();
        JSONObject j = s.serialGeneric(2, "RepaireDTO", list);
        System.out.println(s.serialToStr(j));

        JSONObject jlist = d.deserialGeneric(s.serialToStr(j));
        JSONArray dlist = (JSONArray) jlist.get("list");




    }

}
