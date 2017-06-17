package Server.dto;

public class OperationDTOAli {
	private int id_operation; //auto-increment
	private String name, date_begin, date_end,time_end, time_begin, numMat,login_repairer;
	private String id_breakdown;
	
	public OperationDTOAli(){
	}
	
	public OperationDTOAli(int io,String nm,String db,String de,String t,String dem,String lr,String ib){
		this.id_operation=io;
		this.name=nm;
		this.date_begin=db;
		this.date_end=de;
		this.login_repairer=lr;
		this.id_breakdown=ib;
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
	public String getTime_end() {
		return time_end;
	}

	public void setTime_end(String date_end) {
		this.time_end = date_end;
	}
	public String getTime_begin() {
		return time_begin;
	}

	public void setTime_begin(String time) {
		this.time_begin = time;
	}

	public String getnuMat() {
		return numMat;
	}

	public void setnuMat(String degre_emergency) {
		this.numMat = degre_emergency;
	}

	public String getLogin_repairer() {
		return login_repairer;
	}

	public void setLogin_repairer(String login_repairer) {
		this.login_repairer = login_repairer;
	}

	

	public String getId_breakdown() {
		return id_breakdown;
	}

	public void setId_breakdown(String id_breakdown) {
		this.id_breakdown = id_breakdown;
	}

	@Override
	public String toString() {
		return "[{\"id_operation\":\"" + id_operation + "\",\"date_begin\":\"" + date_begin
				+ "\",\"date_end\":\"" + date_end + "\",\"time_end\":\"" + time_end + "\",\"time_begin\":\"" + time_begin  +"\",\"login_repairer\":\"" + login_repairer
				+ "\",\"numMat\":\"" + numMat + "\",\"id_breakdown\":\"" + id_breakdown + "\"}]";
	}
	
	
}
