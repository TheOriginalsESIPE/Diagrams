package dto;


<<<<<<< HEAD
public class Vehicle_warehouseDTO{
=======

public class Vehicle_warehouseDTO {
>>>>>>> 835007e46af7dc4575eb85f9a21231f2be84fdc1
	
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
<<<<<<< HEAD
		//this.numMat=nM;
		//this.model=mo;
=======
		this.numMat=nM;
		this.model=mo;
>>>>>>> 835007e46af7dc4575eb85f9a21231f2be84fdc1
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
	
<<<<<<< HEAD

=======
>>>>>>> 835007e46af7dc4575eb85f9a21231f2be84fdc1
	
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
<<<<<<< HEAD
	
	public String toString(){
		return ("numMat: "+this.getNumMat()+" model: "+this.getModel()+" date_entrance: "+this.getDate_entrance()+" date_wayout: "+this.getDate_wayout());
	}
=======

	@Override
	public String toString() {
		return "Vehicle_warehouseDTO [date_entrance=" + date_entrance + ", date_wayout=" + date_wayout + ", numMat="
				+ numMat + ", model=" + model + "]";
	}
	
	
>>>>>>> 835007e46af7dc4575eb85f9a21231f2be84fdc1
}