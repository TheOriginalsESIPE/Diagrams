package Server.repository;

public class ModelViewListOp {
	 public ModelViewListOp() {}
		
	 public String getListFromBd(){
		 return "SELECT operation_sort.numMat , breakdown.name, operation_sort.id_operation FROM operation_sort,"
		 		+ "breakdown,operation WHERE operation_sort.id_operation=operation.id_operation and"
		 		+ " operation.id_breakdown= breakdown.id_breakdown";

	 }
//SELECT operation_sort.numMat , breakdown.name, operation_sort.id_operation FROM operation_sort,breakdown,operation WHERE operation_sort.id_operation=operation.id_operation and operation.id_breakdown= breakdown.id_breakdown
	}


