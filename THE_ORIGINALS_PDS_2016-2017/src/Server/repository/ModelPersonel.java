package Server.repository;

public class ModelPersonel {
	public ModelPersonel(){
		
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
	public String SelectOPV(String z){
		String query = "SELECT * from operation where numMat='"+z+"';";
		return query;
	}
	public String SelectBD(String z){
		String query = "SELECT * from breakdown where id_breakdown='"+z+"';";
		return query;
	}
	
}
