package repository;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ModelVehicle {
	
	public ModelVehicle(){}
	
	public String select (String numMat){
		
		String sql="select vehicle_warehouse.numMat, model, mark, vehicle_type, numPlace, date_entrance, date_wayout, status, id_vehicle_warehouse from vehicle, vehicle_warehouse where vehicle.numMat = vehicle_warehouse.numMat and vehicle_warehouse.numMat ='"+numMat+"'";
		return sql;
	}
	
	public String select1 (String numMat){
		String sql="select numMat,adress,vehicle_warehouse.id_warehouse from vehicle_warehouse,warehouse where vehicle_warehouse.id_warehouse=warehouse.id_warehouse and numMat='"+numMat+"'";
		return sql;
	}
	
	public String selectAll(String date_end){
		String sql="select vehicle_warehouse.numMat from operation,vehicle_warehouse where operation.id_vehicle_warehouse=vehicle_warehouse.id_vehicle_warehouse and operation.date_end='"+date_end+"'and vehicle_warehouse.status<>'en circulation'";
		return sql;
	}
	
	public String update(String numMat){
		
		SimpleDateFormat formater=null;
		Date aujourdhui = new Date();
		formater = new SimpleDateFormat("yy-MM-dd");
		String date_end=formater.format(aujourdhui);
		
		String sql="update vehicle_warehouse set date_wayout ='"+date_end+"' where numMat='"+numMat+"'";
		return sql;
	}
	
	public String update1(String numMat){
		
		String sql="update vehicle_warehouse set status='en circulation' where numMat='"+numMat+"'";
		return sql;
	}
	
	public String update2(String numMat, int id){
		
		String sql="update vehicle_warehouse set id_warehouse='"+id+"' where numMat='"+numMat+"'";
		return sql;
	}
	
	 public String DateAujourdhui(String date){
			
		 SimpleDateFormat formater=null;
		 Date aujourdhui = new Date();
		 formater = new SimpleDateFormat("'le' dd MMMM yyyy 'à' hh:mm:ss");
		 String date_end=formater.format(aujourdhui);
		 return date_end;
	 }

}
