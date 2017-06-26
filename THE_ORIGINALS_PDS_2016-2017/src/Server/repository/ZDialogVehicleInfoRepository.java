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
	
	public static String getMotif(){
		String query = null;
		try{
			query = "SELECT name FROM motif";
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
	
	public static String setMotifHistorique( String nameMotif, String numMat){
		String query = null;
		try{
			query = "INSERT INTO motif_historique(name, numMat, dateM) VALUES ('"
                         +nameMotif+"','"+numMat+"',NOW())";
		}catch(Exception e){
			e.printStackTrace();
		}
		return query;
	}
	
	public static String setParking(String numP, String numMat){
		String query = null;
		try{
			/*query = "INSERT "
					+ "WHEN numPlace = '"+numP+"'AND numMat is null THEN "
							+ " INTO parking(numMat) VALUES ('"+numMat+"')";*/
			query = "update parking set numMat = '"+numMat+"' where numPlace = '"+numP+"' and numMat is null";
		}catch(Exception e){
			e.printStackTrace();
		}
		return query;
	}
	
	public static String getPark(){
		String query = null;
		try{
			query = "SELECT numPlace FROM parking WHERE numMat is null";
		}catch(Exception e){
			e.printStackTrace();
		}
		return query;
	}

}
