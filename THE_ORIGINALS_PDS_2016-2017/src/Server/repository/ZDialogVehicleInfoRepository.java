package Server.repository;

public class ZDialogVehicleInfoRepository {
	
	
	public static String getBreakdown(){
		String query = null;
		try{
			query = "SELECT name FROM breakdown";
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

}
