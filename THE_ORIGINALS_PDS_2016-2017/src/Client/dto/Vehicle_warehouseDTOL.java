package Client.dto;

public class Vehicle_warehouseDTOL {
	
	private String date_entrance;
	private String date_wayout;
	private VehicleDTOL numMat;
	private String numPlace;
	private String status;
	private VehicleDTOL id_warehouse;
	private String dateBeginOperation;
	private String dateEndOperation;

	public Vehicle_warehouseDTOL(){

	}

	public Vehicle_warehouseDTOL(String date_entrance, String date_wayout, VehicleDTOL numMat, String numPlace, String status, VehicleDTOL id_warehouse,String dateBeginOperation,String dateEndOperation ){
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


	public VehicleDTOL getNumMat() {
		return numMat;
	}

	public void setNumMat(VehicleDTOL numMat) {
		this.numMat = numMat;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public VehicleDTOL getId_warehouse() {
		return id_warehouse;
	}

	public void setId_warehouse(VehicleDTOL id_warehouse) {
		this.id_warehouse = id_warehouse;
	}

	public String getNumPlace() {
		return numPlace;
	}

	public void setNumPlace(String numPlace) {
		this.numPlace = numPlace;
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

	@Override
	public String toString() {
		return "Vehicle_warehouseDTO [date_entrance=" + date_entrance + ", date_wayout=" + date_wayout + ", numMat="
				+ numMat + ", numPlace=" + numPlace + ", status=" + status + ", id_warehouse=" + id_warehouse + ",dateBeginOperation=" + dateBeginOperation + ",dateEndOperation=" + dateEndOperation +"]";
	}

	

	
	
}