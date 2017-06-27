package Server.dto;

public class VisitingMotifDTO {
	private int id_visitinMotif;
	private String name;
	
	public VisitingMotifDTO(){}
	
	public VisitingMotifDTO(int id_visitinMotif, String name){
		this.id_visitinMotif = id_visitinMotif;
		this.name = name;
	}

	
	/**
	 * @return the id_visitinMotif
	 */
	public int getId_visitinMotif() {
		return id_visitinMotif;
	}

	/**
	 * @param id_visitinMotif the id_visitinMotif to set
	 */
	public void setId_visitinMotif(int id_visitinMotif) {
		this.id_visitinMotif = id_visitinMotif;
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
	
	@Override
	public String toString() {
		return "[{\"id_breakdown\":\"" + id_visitinMotif + "\",\"name\":\"" + name + "\"}]";
	}
	
}
