package Server.dto;

public class Vehicle_warehouseDTOAli {
	
	private String date_entrance;
	private String date_wayout;
	private String numMat;
	private String model;
	
	public Vehicle_warehouseDTOAli(){

	}
	
	public Vehicle_warehouseDTOAli(String da,String dw,String nM,String mo ){
		this.date_entrance=da;
		this.date_wayout=dw;
		this.numMat=nM;
		this.model=mo;
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

	
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Override
	public String toString() {
		return "[{\"date_entrance\":\"" + date_entrance + "\",\"date_wayout\":\"" + date_wayout + "\",\"numMat\":\""
				+ numMat + "\",\"model\":\"" + model + "\"}]";
	}
	
	
}
