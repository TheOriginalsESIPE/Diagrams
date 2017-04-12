package serialization;

import dto.VehicleDTO;
import enumeration.EnumOperation;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
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
		root.put(dtoName, o);
		return root;
	}

	/**
	 * If we have multiple list have to serialize, for example:
	 * I want to return a list of vehicle and it's depot date and it's repairer.
	 * @param action
	 * @param dtoList
	 * @param listName
     * @return
     */
	public JSONObject serialGeneric(int action, String[] listName, List ... dtoList){
		JSONObject root = new JSONObject();
		root.put("action", action);
		int i = 0;
		for (List list : dtoList){
			String str = listName[i]+ i;
			root.put(str, list);
		}
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
		List list = new ArrayList<>();
		list.add(vehicleDTO);
		list.add(V2);
		System.out.println(vehicleDTO.getClass().getSimpleName());
		JSONObject j = s.serialGeneric(EnumOperation.DELETE.getIndex(), vehicleDTO.getClass().getSimpleName(),vehicleDTO);
		JSONObject j2 = s.serialGeneric(EnumOperation.SEARCH.getIndex(), "vehicleDTO",list);

		System.out.println(j2.toJSONString()+ "\n Size "+j2.size());


		System.out.println("Action is " + EnumOperation.getNameByIndex(d.deserialAction(j)));
		System.out.println("Object without list is " + d.deserialObject(j, vehicleDTO.getClass().getSimpleName()).toString());
		System.out.println("Object with list is " + d.deserialObject(j2, "vehicleDTO").toString());
		System.out.println(s.serialToStr(j));

	}
	
}
