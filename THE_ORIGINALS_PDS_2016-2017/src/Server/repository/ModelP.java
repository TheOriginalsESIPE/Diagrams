package Server.repository;

public class ModelP {
	public ModelP(){}
	
	public String selectMaxline( int minDegree ){
		String query="SELECT * FROM operation_sort WHERE rang = "+minDegree+" ;";
		return query ;
	}
	
	
	public String selectMaxRang(){
        String query = "SELECT MIN(rang) FROM `operation_sort` " ;
       
                
        return query; 
}
	
	public String selectMAX(){
        String query = "SELECT id_operation_sort,id_operation,numMat ,MIN(rang) FROM operation_sort ; " ;
       
                
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
	public String selectIdPanne(int idop){
        String query = "SELECT id_breakdown FROM operation WHERE id_operation = "+idop+" ;";
       
                
        return query; 
}
	
}
