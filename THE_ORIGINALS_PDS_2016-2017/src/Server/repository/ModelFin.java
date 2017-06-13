package Server.repository;

public class ModelFin {
	public ModelFin(){}
	
	public String reqRenseigne (int idOp,String loginRepair){
		String query = "UPDATE operation SET time_end = CURRENT_TIME , date_end = CURRENT_DATE ,done= '1',"
				+ "login_repairer = '"+loginRepair+"' WHERE id_operation = "+idOp+";";
		return query ;
		
	}
	
	public String supOpSort (int idOp){
		String query = "DELETE FROM operation_sort WHERE id_operation ="+idOp+";";

		return query ;
	}
	
	public String compterOp (String matricul){
		String query = "SELECT COUNT(*) FROM operation_sort WHERE numMat='"+matricul+"';" ;
    return query ;
	}
	
	public String modifStatu (String matricul){
		String query = "UPDATE vehicle_warehouse SET STATUS = 'repare' WHERE numMat ='"+matricul+"';" ;
    return query ;
	}


}
