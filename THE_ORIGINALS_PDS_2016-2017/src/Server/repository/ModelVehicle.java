package Server.repository;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ModelVehicle {
	
	public ModelVehicle(){}
	
	public String select (String numMat){
		
		String sql="select vehicle.numMat, vehicle_warehouse.numMat, model, mark, vehicle_type, numPlace, date_entrance, date_wayout, status from vehicle, vehicle_warehouse where vehicle.numMat = vehicle_warehouse.numMat and vehicle_warehouse.numMat ='"+numMat+"'";
		return sql;
	}
	
	public String select1 (String numMat){
		String sql="select numMat,adress,vehicle_warehouse.id_warehouse from vehicle_warehouse,warehouse where vehicle_warehouse.id_warehouse=warehouse.id_warehouse and numMat='"+numMat+"'";
		return sql;
	}
	
	public String selectAll(String date_end){
		String sql="select vehicle_warehouse.numMat from operation,vehicle_warehouse where operation.numMat=vehicle_warehouse.numMat and operation.date_end='"+date_end+"'and vehicle_warehouse.status<>'en circulation'";
		return sql;
	}
	
	public String update(String numMat,int id){
		
		SimpleDateFormat formater=null;
		Date aujourdhui = new Date();
		formater = new SimpleDateFormat("yy-MM-dd");
		String date_end=formater.format(aujourdhui);
		
		String sql="update vehicle_warehouse set date_wayout ='"+date_end+"',status='en circulation',id_warehouse='"+id+"' where numMat='"+numMat+"'";
		return sql;
	}
	
	public String selectTable(){
		String sql="select vehicle_warehouse.numMat,numPlace,date_entrance,date_wayout,status,operation.date_begin,operation.date_end from vehicle_warehouse,operation where vehicle_warehouse.numMat=operation.numMat";
		return sql;
	}

}
