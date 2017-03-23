import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Serialisation {

	@SuppressWarnings("unchecked")
	public Object srialisation(int o , VehicleDTO VDTO){
		JSONObject root = new JSONObject();
		root.put("action", o);
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
