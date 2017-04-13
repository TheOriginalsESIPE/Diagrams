package dto;

public class VehicleDTO {
	
	private String numMat;
	private String model;
	private String mark;
	private String vehicle_type;
	
	public VehicleDTO(){
		this.numMat=numMat;
		this.model=model;
		this.mark=mark;
		this.vehicle_type=vehicle_type;
	}
	
	public VehicleDTO(String nM, String mo, String ma, String vt){
		this.numMat=nM;
		this.model=mo;
		this.mark=ma;
		this.vehicle_type=vt;
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
	
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	
	public String getVehicle_type() {
		return vehicle_type;
	}
	public void setVehicle_type(String vehicle_type) {
		this.vehicle_type = vehicle_type;
	}
	
	@Override
	public String toString() {
		return "[{\"numMat\":\"" + numMat + "\",\"model\":\"" + model + "\",\"mark\":\"" + mark + "\",\"vehicle_type\":\"" + vehicle_type
				+"\"}]";
	}
}