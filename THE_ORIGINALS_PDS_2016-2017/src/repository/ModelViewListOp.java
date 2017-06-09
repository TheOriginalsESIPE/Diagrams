package repository;

public class ModelViewListOp {
	 public ModelViewListOp() {}
		
	 public String getListFromBd(){
		 return "SELECT numMat , name, id_operation FROM operation_sort2,breakdown WHERE operation_sort2.id_breakdown=breakdown.id_breakdown;";

	 }

	}


