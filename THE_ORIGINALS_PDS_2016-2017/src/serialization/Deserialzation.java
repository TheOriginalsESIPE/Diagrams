package serialization;

import dto.VehicleDTO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Deserialzation {
	
	public VehicleDTO deserialAVehicle(JSONObject jsonobject, VehicleDTO VDTO){
		JSONArray msg = (JSONArray) jsonobject.get("VehicleDTO");
			 JSONObject jsonObject2 = (JSONObject) msg.get(0);
			 VDTO.setNumMat( (int) jsonObject2.get("numMat"));
			 VDTO.setModel((String) jsonObject2.get("model"));
			 VDTO.setMark((String) jsonObject2.get("mark"));
			 VDTO.setVehicle_type((String) jsonObject2.get("vehicle_type"));
			 return VDTO;
		}
		
	}

