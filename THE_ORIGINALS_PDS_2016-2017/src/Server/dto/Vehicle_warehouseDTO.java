package Server.dto;

//import dto.VehicleDTO;

public class Vehicle_warehouseDTO {
	
	private String date_entrance;
	private String date_wayout;
	private String numMat;
	private String numPlace;
	private String status;
	private VehicleDTO id_warehouse;
	
	public Vehicle_warehouseDTO(){

	}
	
	public Vehicle_warehouseDTO(String date_entrance,String date_wayout,String numMat, String numPlace, String status, VehicleDTO id_warehouse ){
		this.date_entrance=date_entrance;
		this.date_wayout=date_wayout;
		this.numMat=numMat;
		this.numPlace=numPlace;
		this.status=status;
		this.id_warehouse=id_warehouse;
		
	}
	
	public String getDate_entrance() {
		return date_entrance;
	}
	public void setDate_entrance(String date_entrance) {
		this.date_entrance = date_entrance;
	}
	
	public String getDate_wayout() {
		return date_wayout;
	}
	public void setDate_wayout(String date_wayout) {
		this.date_wayout = date_wayout;
	}
	
	
	public String getNumMat() {
		return numMat;
	}

	public void setNumMat(String numMat) {
		this.numMat = numMat;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public VehicleDTO getId_warehouse() {
		return id_warehouse;
	}

	public void setId_warehouse(VehicleDTO id_warehouse) {
		this.id_warehouse = id_warehouse;
	}

	public String getNumPlace() {
		return numPlace;
	}

	public void setNumPlace(String numPlace) {
		this.numPlace = numPlace;
	}

	@Override
	public String toString() {
		return "Vehicle_warehouseDTO [date_entrance=" + date_entrance + ", date_wayout=" + date_wayout + ", numMat="
				+ numMat + ", numPlace=" + numPlace + ", status=" + status + ", id_warehouse=" + id_warehouse + "]";
	}

	

	
	
}