package Server.repository;

import java.util.ArrayList;

import Server.dto.BreakdownDTO;

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
	
	/*public static String getMotif(){
		String query = null;
		try{
			query = "SELECT * FROM motif";
		}catch(Exception e){
			e.printStackTrace();
		}
		return query;
	}*/
	
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
	
	public static String setOperation( String id_breakdown, String numMat, String login_repairer){
		//System.out.println(id_breakdown+""+numMat+""+login_repairer);
		String query = null;
		try{
			query = "INSERT INTO operation(id_breakdown, numMat, login_repairer) VALUES ('"
                         +id_breakdown+"','"+numMat+"','"+login_repairer+"')";
		}catch(Exception e){
			e.printStackTrace();
		}
		return query;
	}
	
	public static String getPark(){
		String query = null;
		try{
			query = "SELECT numPlace FROM parking";
		}catch(Exception e){
			e.printStackTrace();
		}
		return query;
	}

}
