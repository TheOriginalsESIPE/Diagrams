package Client.dto;

import java.sql.Time;
import java.time.LocalTime;

public class BreakdownDTO{
	
	private int id_breakdown; //auto increment
	private String name;
	private String description;
	private Time duree;
	private int emergency_degree;
	
	public BreakdownDTO(){

	}
	
	public BreakdownDTO(int ib,String nm,VehicleDTO nM, Time duree, String description, int emergency_degree){
		this.id_breakdown=ib;
		this.name=nm;
		this.description=description;
		this.duree=duree;
		this.emergency_degree=emergency_degree;
	}
	
	public BreakdownDTO(int ib){
		this.id_breakdown=ib;
	}

	/**
	 * @return the id_breakdown
	 */
	public int getId_breakdown() {
		return id_breakdown;
	}

	/**
	 * @param id_breakdown the id_breakdown to set
	 */
	public void setId_breakdown(int id_breakdown) {
		this.id_breakdown = id_breakdown;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the duree
	 */
	public Time getDuree() {
		return duree;
	}

	/**
	 * @param time the duree to set
	 */
	public void setDuree(Time duree) {
		this.duree = duree;
	}

	/**
	 * @return the emergency_degree
	 */
	public int getEmergency_degree() {
		return emergency_degree;
	}

	/**
	 * @param emergency_degree the emergency_degree to set
	 */
	public void setEmergency_degree(int emergency_degree) {
		this.emergency_degree = emergency_degree;
	}

	@Override
	public String toString() {
		return "[{\"id_breakdown\":\"" + id_breakdown + "\",\"name\":\"" + name + "\",\"description\":\"" + description + "\","
				+ "\"duree\":\"" + duree +"\",\"emergency_degree\":\"" + emergency_degree +"\"}]";
	}
	
	
}