package Server.dto;

import Server.dto.BreakdownDTO;
import Server.dto.VehicleDTO;

public class OperationAnais {
	
	private int id_operation; //auto-increment
	private String name, date_begin, date_end, time, login_repairer, numMat;
	private int id_breakdown;
	private int level;
	private int done;
	
	public OperationAnais(int io,String nm,String db,String de,String t,int level,String lr, String numMat, int ib, int done){
		this.id_operation=io;
		this.name=nm;
		this.date_begin=db;
		this.date_end=de;
		this.time=t;
		this.level=level;
		this.login_repairer=lr;
		this.numMat = numMat;
		this.id_breakdown=ib;
		this.done = done;
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

	public int getDegre_emergency() {
		return level;
	}

	public void setDegre_emergency(int degre_emergency) {
		this.level = degre_emergency;
	}

	public String getLogin_repairer() {
		return login_repairer;
	}

	public void setLogin_repairer(String login_repairer) {
		this.login_repairer = login_repairer;
	}

	public String getNumMat() {
		return numMat;
	}
	

	public int getId_breakdown() {
		return id_breakdown;
	}

	public void setId_breakdown(int id_breakdown) {
		this.id_breakdown = id_breakdown;
	}
	
	public int getDone() {
		return done;
	}
	
	public void setDone(int value) {
		done = value;
	}


	@Override
	public String toString() {
		return "[{\"id_operation\":\"" + id_operation + "\",\"name\":\"" + name + "\",\"date_begin\":\"" + date_begin
				+ "\",\"date_end\":\"" + date_end + "\",\"time\":\"" + time + "\",\"degre_emergency\":\"" + level
				+ "\",\"login_repairer\":\"" + login_repairer + "\",\"id_breakdown\":\"" + id_breakdown + "\"}]";
	}
}

