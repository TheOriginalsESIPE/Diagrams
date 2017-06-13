package Server.repository;

public class ModelInfoVehicle {
	public ModelInfoVehicle(){
	}

	public String reqInfoWarehouse (String numMat){
		String query ="SELECT * FROM vehicle_warehouse WHERE numMat = '"+numMat+"' ;";
		return query ; 
	}
	
	
	public String reqInfoV (String numMat){
		String query ="SELECT * FROM vehicle WHERE numMat = '"+numMat+"' ;";
		return query ; 
	}
	
	public String reqInfoPanne (String name){
		String query ="SELECT duree FROM breakdown WHERE name = '"+name+"' ;";
		return query ; 
	}
}
