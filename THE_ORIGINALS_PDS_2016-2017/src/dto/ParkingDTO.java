package dto;
<<<<<<< HEAD
 
=======


>>>>>>> 835007e46af7dc4575eb85f9a21231f2be84fdc1

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
	
<<<<<<< HEAD
	
=======

>>>>>>> 835007e46af7dc4575eb85f9a21231f2be84fdc1
	public WarehouseDTO getId_warehouse() {
		return id_warehouse;
	}

	public void setId_warehouse(WarehouseDTO id_warehouse) {
		this.id_warehouse = id_warehouse;
	}
<<<<<<< HEAD
	
	public String toString(){
		return ("numPlacet: "+this.getNumPlace()+" id_warehouse: "+this.getId_warehouse());
	}

=======

	@Override
	public String toString() {
		return "ParkingDTO [numPlace=" + numPlace + ", id_warehouse=" + id_warehouse + "]";
	}
	
	
>>>>>>> 835007e46af7dc4575eb85f9a21231f2be84fdc1
}
