package serialization;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.*;

/**
 *
 * This class is used to deserialize an object json or a string of json.
 * Created by tearsyu on 07/04/17.
 * @author tearsyu
 */
public class Deserialization {

	/**
	 * This function deserialize a string of json
	 * @param str the string of json
	 * @return JSONObject
     */
	public JSONObject deserialGeneric(String str){
		JSONParser parser = new JSONParser();
		JSONObject o = null;
		try {
			o = (JSONObject) parser.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return o;
	}

	/**
	 * deserialAction return the action of jsonObject.
	 * @param jsonObject
	 * @return An action in the enumOperation
     */
	public int deserialAction(JSONObject jsonObject){
		Integer i = (Integer) jsonObject.get("action");
		return i;
	}

	/**
	 * deserialObject return the object of jsonObject.
	 * @param jsonObject
	 * @return an object of dto or list of dto
     */
	public Object deserialObject(JSONObject jsonObject, String dtoName){
		return jsonObject.get(dtoName);
	}
/*
	public List deserialMultiObject(JSONObject jsonObject){
		List reList = new ArrayList<>();
		int size = jsonObject.size();
		for(int i = 0; i < size; ++i){

		}
	}*/
		
}

