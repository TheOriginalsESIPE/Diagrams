package dto;



public class OperationDTO{
	
	private int id_operation; //auto-increment
	private String name, date_begin, date_end, time, degre_emergency, login_repairer;
	private int id_breakdown;
	private String numMat;
	
	public OperationDTO(){
	}
	
	public OperationDTO(int io,String nm,String db,String de,String t,String dem,String lr,int ib, String numMat){
		this.id_operation=io;
		this.name=nm;
		this.date_begin=db;
		this.date_end=de;
		this.time=t;
		this.degre_emergency=dem;
		this.login_repairer=lr;
		this.id_breakdown=ib;
		this.numMat = numMat;
	}

	public int getId_operation() {
		return id_operation;
	}

	public void setId_operation(int id_operation) {
		this.id_operation = id_operation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate_begin() {
		return date_begin;
	}

	public void setDate_begin(String date_begin) {
		this.date_begin = date_begin;
	}

	public String getDate_end() {
		return date_end;
	}

	public void setDate_end(String date_end) {
		this.date_end = date_end;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDegre_emergency() {
		return degre_emergency;
	}

	public void setDegre_emergency(String degre_emergency) {
		this.degre_emergency = degre_emergency;
	}

	public String getLogin_repairer() {
		return login_repairer;
	}

	public void setLogin_repairer(String login_repairer) {
		this.login_repairer = login_repairer;
	}

	

	public int getId_breakdown() {
		return id_breakdown;
	}

	public void setId_breakdown(int id_breakdown) {
		this.id_breakdown = id_breakdown;
	}
	

	/**
	 * @return the numMat
	 */
	public String getNumMat() {
		return numMat;
	}

	/**
	 * @param numMat2 the numMat to set
	 */
	public void setNumMat(String numMat2) {
		this.numMat = numMat2;
	}

	@Override
	public String toString() {
		return "[{\"id_operation\":\"" + id_operation + "\",\"date_begin\":\"" + date_begin
				+ "\",\"date_end\":\"" + date_end + "\",\"time\":\"" + time + "\",\"degre_emergency\":\"" + degre_emergency
				+ "\",\"login_repairer\":\"" + login_repairer + "\",\"id_breakdown\":\"" + id_breakdown + "\",\"numMat\":\"" + numMat + "\"}]";
	}
	
	
}
