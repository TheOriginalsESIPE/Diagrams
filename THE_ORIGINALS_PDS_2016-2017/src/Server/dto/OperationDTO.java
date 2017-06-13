package Server.dto;

import Server.dto.BreakdownDTO;
import Server.dto.VehicleDTO;

public class OperationDTO{
	
	private int id_operation; //auto-increment
	private String time_begin, date_begin, date_end, time_end, degre_emergency, login_repairer, done;
	private BreakdownDTO id_breakdown;
	private VehicleDTO numMat;
	
	public OperationDTO(){
	}
	
	public OperationDTO(int io,String nm,String db,String de,String t,String dem,String lr,BreakdownDTO ib, VehicleDTO numMat,String done){
		this.id_operation=io;
		this.time_begin=nm;
		this.date_begin=db;
		this.date_end=de;
		this.time_end=t;
		this.degre_emergency=dem;
		this.login_repairer=lr;
		this.id_breakdown=ib;
		this.numMat = numMat;
		this.done = done;
	}

	
	/**
	 * @return the id_operation
	 */
	public int getId_operation() {
		return id_operation;
	}

	/**
	 * @param id_operation the id_operation to set
	 */
	public void setId_operation(int id_operation) {
		this.id_operation = id_operation;
	}

	/**
	 * @return the time_begin
	 */
	public String getTime_begin() {
		return time_begin;
	}

	/**
	 * @param time_begin the time_begin to set
	 */
	public void setTime_begin(String time_begin) {
		this.time_begin = time_begin;
	}

	/**
	 * @return the date_begin
	 */
	public String getDate_begin() {
		return date_begin;
	}

	/**
	 * @param date_begin the date_begin to set
	 */
	public void setDate_begin(String date_begin) {
		this.date_begin = date_begin;
	}

	/**
	 * @return the date_end
	 */
	public String getDate_end() {
		return date_end;
	}

	/**
	 * @param date_end the date_end to set
	 */
	public void setDate_end(String date_end) {
		this.date_end = date_end;
	}

	/**
	 * @return the time_end
	 */
	public String getTime_end() {
		return time_end;
	}

	/**
	 * @param time_end the time_end to set
	 */
	public void setTime_end(String time_end) {
		this.time_end = time_end;
	}

	/**
	 * @return the degre_emergency
	 */
	public String getDegre_emergency() {
		return degre_emergency;
	}

	/**
	 * @param degre_emergency the degre_emergency to set
	 */
	public void setDegre_emergency(String degre_emergency) {
		this.degre_emergency = degre_emergency;
	}

	/**
	 * @return the login_repairer
	 */
	public String getLogin_repairer() {
		return login_repairer;
	}

	/**
	 * @param login_repairer the login_repairer to set
	 */
	public void setLogin_repairer(String login_repairer) {
		this.login_repairer = login_repairer;
	}

	/**
	 * @return the done
	 */
	public String getDone() {
		return done;
	}

	/**
	 * @param done the done to set
	 */
	public void setDone(String done) {
		this.done = done;
	}

	/**
	 * @return the id_breakdown
	 */
	public BreakdownDTO getId_breakdown() {
		return id_breakdown;
	}

	/**
	 * @param id_breakdown the id_breakdown to set
	 */
	public void setId_breakdown(BreakdownDTO id_breakdown) {
		this.id_breakdown = id_breakdown;
	}

	/**
	 * @return the numMat
	 */
	public VehicleDTO getNumMat() {
		return numMat;
	}

	/**
	 * @param numMat the numMat to set
	 */
	public void setNumMat(VehicleDTO numMat) {
		this.numMat = numMat;
	}

	@Override
	public String toString() {
		return "[{\"id_operation\":\"" + id_operation + "\",\"date_begin\":\"" + date_begin
				+ "\",\"date_end\":\"" + date_end + "\",\"time_end\":\"" + time_end + "\",\"time_begin\":\"" + time_begin + "\",\"degre_emergency\":\"" + degre_emergency
				+ "\",\"login_repairer\":\"" + login_repairer + "\",\"id_breakdown\":\"" + id_breakdown + "\",\"done\":\"" + done + "\",\"numMat\":\"" + numMat + "\"}]";
	}
	
	
}
