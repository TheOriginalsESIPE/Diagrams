package dto;



public class BreakdownDTO{
	
	private int id_breakdown=0; //auto increment
	private String name;
	private VehicleDTO numMat;
	
	public BreakdownDTO(){
		this.id_breakdown=id_breakdown;
		this.name=name;
		this.numMat=numMat;}
	
	public BreakdownDTO(int ib,String nm,VehicleDTO nM){
		this.id_breakdown=ib;
		this.name=nm;
		this.numMat=nM;
	}
	
	public int getId_breakdown() {
		return id_breakdown;
	}
	public void setId_breakdown(int id_breakdown) {
		this.id_breakdown = id_breakdown;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

	public VehicleDTO getNumMat() {
		return numMat;
	}

	public void setNumMat(VehicleDTO numMat) {
		this.numMat = numMat;
	}
}