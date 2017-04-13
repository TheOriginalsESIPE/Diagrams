package serialization;

import dto.*;
import enumeration.EnumOperation;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.List;

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
	 * @param VDTO
     * @return JSONObject
     */
	public JSONObject serialisAVehicle(int action, VehicleDTO VDTO){
		JSONObject root = new JSONObject();
		root.put("action", action);
		JSONObject V1 = new JSONObject();
		V1.put("numMat", VDTO.getNumMat());
		V1.put("model", VDTO.getModel());
		V1.put("mark",VDTO.getMark());
		V1.put("vehicle_type",VDTO.getVehicle_type());
		JSONArray listDTO = new JSONArray();
		listDTO.add(V1);
		root.put("VehicleDTO", listDTO);
		return root;
	}

	/**
	 * This function used to serial an action with an object dto.
	 * @param action
	 * @param o
	 * @param dtoName
     * @return JSONObject
     */
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
	 * @param listName
     * @return
     */
	public JSONObject serialGeneric(int action, String listName, List dtoList){
		JSONObject root = new JSONObject();
		root.put("action", action);
		root.put(listName, dtoList);
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

	public static void main(String[] arg){
		Serialization s = new Serialization();
		Deserialization d = new Deserialization();

		VehicleDTO vehicleDTO = new VehicleDTO();
		vehicleDTO.setNumMat("24243JDS");
		vehicleDTO.setMark("BWM");
		vehicleDTO.setModel("MDH334 E2J");
		vehicleDTO.setVehicle_type("voiture");
		VehicleDTO V2 = vehicleDTO;

        Piece_detachedDTO pd = new Piece_detachedDTO("xxx", "xer d", "renault", (float) 125.3, "erwfsdf");
        String str = s.serialToStr(s.serialGeneric(EnumOperation.UPDATE.getIndex(), pd.getClass().getSimpleName(), pd));
        d.deserialGeneric(str);
        JSONObject jo = d.deserialGeneric(str);
        JSONArray ja = (JSONArray) d.deserialObject(jo, "Piece_detachedDTO");
        jo = (JSONObject) ja.get(0);

	}
	
}
