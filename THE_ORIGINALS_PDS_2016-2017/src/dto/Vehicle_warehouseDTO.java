package dto;


public class Vehicle_warehouseDTO{
	
	private String date_entrance;
	private String date_wayout;
	private VehicleDTO numMat;
	private VehicleDTO model;
	
	public Vehicle_warehouseDTO(){
		this.date_entrance=date_entrance;
		this.date_wayout=date_wayout;
		this.numMat=numMat;
		this.model=model;
	}
	
	public Vehicle_warehouseDTO(String da,String dw,VehicleDTO nM,VehicleDTO mo ){
		this.date_entrance=da;
		this.date_wayout=dw;
		//this.numMat=nM;
		//this.model=mo;
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
	

	
	public VehicleDTO getNumMat() {
		return numMat;
	}

	public void setNumMat(VehicleDTO numMat) {
		this.numMat = numMat;
	}

	
	public VehicleDTO getModel() {
		return model;
	}

	public void setModel(VehicleDTO model) {
		this.model = model;
	}
	
	public String toString(){
		return ("numMat: "+this.getNumMat()+" model: "+this.getModel()+" date_entrance: "+this.getDate_entrance()+" date_wayout: "+this.getDate_wayout());
	}
}