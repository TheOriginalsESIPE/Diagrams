package serialization;

import dto.VehicleDTO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Serialization {


	@SuppressWarnings("unchecked")
	public Object serialisAVehicle(int action, VehicleDTO VDTO){
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
	
}
