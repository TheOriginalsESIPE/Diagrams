package Server.repository;

public class ModelPersonel {
	public ModelPersonel(){
		
	}
	public String SelectId_Br(String matricul){
	        String query = "SELECT id_breakdown from breakdown where numMat = '"+matricul+"';";
	        return query;
	    }
	public String SelectOp(String s){
		String query = "SELECT * from vehicle_warehouse where numMat = '"+s+"';";
        return query;
	}
	public String SelectCum(String Dated,String Datef){
		String query = "SELECT * from vehicle_warehouse " + "where date_entrance = '" + Dated + "' and date_wayout ='" + Datef + "';";
        return query;
	}
	public String SeclectVDp(){
		String query = "SELECT * from vehicle_warehouse ;";
        return query;
	}
}
