package Server.dto;



public class Piece_detachedDTO{
	
	private String name, mark, model;
	private float price;
	private String ref_piece_detached;
	
	public Piece_detachedDTO(){

	}
	
	public Piece_detachedDTO(String nm,String ma,String mo,float p,String rpd){
		this.name=nm;
		this.mark=ma;
		this.model=mo;
		this.price=p;
		this.ref_piece_detached=rpd;
	}

	public String getRef_piece_detached() {
		return ref_piece_detached;
	}

	public void setRef_piece_detached(String ref_piece_detached) {
		this.ref_piece_detached = ref_piece_detached;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	
	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "[{\"name\":\"" + name + "\",\"mark\":\"" + mark + "\",\"model\":\"" + model + "\",\"price\":\"" + price
				+ "\",\"ref_piece_detached\":\"" + ref_piece_detached + "\"}]";
	}
	
	
}