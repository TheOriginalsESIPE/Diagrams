package serialization;

import dto.VehicleDTO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Deserialization {
	/**
	 * @Add this method is added by tearsyu in according to the demand of client and server.
	 * This Method used to parse the string to jsonObject.
	 * @param strJson
	 * @return
	 * @throws ParseException
     */
	public JSONObject parseStringToJson(String strJson) throws ParseException {
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(strJson);
		return  jsonObject;
	}

	/**
	 * @modify Delete the second attribute VehicleDTO, it should be created in the function.
	 * @param jsonobject
	 * @return ObjectVehicle
     */
	public VehicleDTO deserialAVehicle(JSONObject jsonobject){
		VehicleDTO VDTO = new VehicleDTO();
		JSONArray msg = (JSONArray) jsonobject.get("VehicleDTO");
		JSONObject jsonObject2 = (JSONObject) msg.get(0);
		VDTO.setNumMat( (int) jsonObject2.get("numMat"));
		VDTO.setModel((String) jsonObject2.get("model"));
		VDTO.setMark((String) jsonObject2.get("mark"));
		VDTO.setVehicle_type((String) jsonObject2.get("vehicle_type"));
		return VDTO;
		}

	public int deserialAction(JSONObject jsonObject){
		Integer integer = (Integer) jsonObject.get("action");
		return integer.intValue();
	}
		
}

