package Client.dto;

import Server.dto.VehicleDTO;

public class Vehicle_warehouseDTOL {
	
	private String date_entrance;
	private String date_wayout;
	private String numMat;
	private String numPlace;
	private String status;
	private VehicleDTO id_warehouse;
	private String dateBeginOperation;
	private String dateEndOperation;
	
	public Vehicle_warehouseDTOL(){

	}
	
	public Vehicle_warehouseDTOL(String date_entrance,String date_wayout,String numMat, String numPlace, String status, VehicleDTO id_warehouse,String dateBeginOperation,String dateEndOperation ){
		this.date_entrance=date_entrance;
		this.date_wayout=date_wayout;
		this.numMat=numMat;
		this.numPlace=numPlace;
		this.status=status;
		this.id_warehouse=id_warehouse;
		this.dateBeginOperation=dateBeginOperation;
		this.dateEndOperation=dateEndOperation;
		
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

	public String getDateBeginOperation() {
		return dateBeginOperation;
	}

	public void setDateBeginOperation(String dateBeginOperation) {
		this.dateBeginOperation = dateBeginOperation;
	}

	public String getDateEndOperation() {
		return dateEndOperation;
	}

	public void setDateEndOperation(String dateEndOperation) {
		this.dateEndOperation = dateEndOperation;
	}

	public void setNumPlace(String numPlace) {
		this.numPlace = numPlace;
	}

	@Override
	public String toString() {
		return "Vehicle_warehouseDTO [date_entrance=" + date_entrance + ", date_wayout=" + date_wayout + ", numMat="
				+ numMat + ", numPlace=" + numPlace + ", status=" + status + ", id_warehouse=" + id_warehouse + ",dateBeginOperation=" + dateBeginOperation + ",dateEndOperation=" + dateEndOperation +"]";
	}

	

	
	
}