package repository;

import java.util.ArrayList;

import dto.BreakdownDTO;

public class ZDialogVehicleInfoRepository {
	
	
	public static String getBreakdown(){
		String query = null;
		try{
			query = "SELECT id_breakdown, name, duree FROM breakdown";
		}catch(Exception e){
			e.printStackTrace();
		}
		return query;
	}
	
	public static String getMotif(){
		String query = null;
		try{
			query = "SELECT * FROM motif";
		}catch(Exception e){
			e.printStackTrace();
		}
		return query;
	}
	
	public static String getVehicle(String numMat){
		String query = null;
		try{
			query = "SELECT * FROM vehicle "
					+ "WHERE numMat = '"+ numMat+"'";
		}catch(Exception e){
			e.printStackTrace();
		}
		return query;
	}
	
	public static String setOperation(String numMat, int id_breakdown){
		String query = null;
		try{
			query = "INSERT INTO operation(id_breakdown, numMat) VALUES (id_breakdown, numMat)";
		}catch(Exception e){
			e.printStackTrace();
		}
		return query;
	}

}
