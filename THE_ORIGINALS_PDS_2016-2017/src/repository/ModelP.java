package repository;

public class ModelP {
	public ModelP(){}
	
	public String selectMaxline( int maxDegree ){
		String query="SELECT * FROM operation_sort2 WHERE emergency_degree = "+maxDegree+" ;";
		return query ;
	}
	
	
	public String selectMaxDegree(){
        String query = "SELECT MAX(emergency_degree) FROM operation_sort2 ; " ;
       
                
        return query; 
}
	
	public String selectMAX(){
        String query = "SELECT id_operation_sort,id_operation,id_breakdown,numMat ,MAX(emergency_degree) FROM operation_sort2 ; " ;
       
                
        return query; 
}
	
	
	
	public String selectMotif(int refPanne){
        String query = "SELECT name FROM breakdown WHERE id_breakdown= "+refPanne+ ";";
       
                
        return query; 
}
	
	//SELECT * FROM vehicle WHERE numMat = 'Ai092'
	public String selecVehicle(String numMat){
        String query = "SELECT * FROM vehicle WHERE numMat = '"+numMat+"' ;";
       
                
        return query; 
}
	public String deleteOpSort(int idOp){
        String query = "DELETE FROM operation_sort2 WHERE id_operation_sort ="+idOp+";";
       
                
        return query; 
}
	
}
