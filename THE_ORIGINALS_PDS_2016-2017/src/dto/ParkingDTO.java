package dto;
 

public class ParkingDTO {
	
	private int numPlace;
	private WarehouseDTO id_warehouse;
	
	public ParkingDTO(){
		this.numPlace=numPlace;
		this.id_warehouse=id_warehouse;
	}
	
	public ParkingDTO(int nP,WarehouseDTO iw){
		this.numPlace=nP;
		this.id_warehouse=iw;
	}
	
	public int getNumPlace() {
		return numPlace;
	}
	public void setNumPlace(int numPlace) {
		this.numPlace = numPlace;
	}
	
	
	public WarehouseDTO getId_warehouse() {
		return id_warehouse;
	}

	public void setId_warehouse(WarehouseDTO id_warehouse) {
		this.id_warehouse = id_warehouse;
	}
	
	public String toString(){
		return ("numPlacet: "+this.getNumPlace()+" id_warehouse: "+this.getId_warehouse());
	}

}
