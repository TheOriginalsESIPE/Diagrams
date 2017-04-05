package serialization;

import dto.VehicleDTO;
import enumeration.EnumOperation;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Serialization {


	@SuppressWarnings("unchecked")
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

	public static void main(String[] arg){
		Serialization s = new Serialization();
		VehicleDTO vehicleDTO = new VehicleDTO();
		vehicleDTO.setNumMat("24243JDS");
		vehicleDTO.setMark("BWM");
		vehicleDTO.setModel("MDH334 E2J");
		vehicleDTO.setVehicle_type("voiture");
		JSONObject j = s.serialisAVehicle(EnumOperation.DELETE.getIndex(), vehicleDTO);
		System.out.println(j.toJSONString());

		Deserialization d = new Deserialization();
		System.out.println("Action is " + EnumOperation.getNameByIndex(d.deserialAction(j)));
		System.out.println(d.deserialAVehicle(j).toString());

	}
	
}
