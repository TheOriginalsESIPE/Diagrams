package dto;


public class Piece_detachedDTO{
	
	private String name, mark, model;
	private Piece_purchaseDTO price;
	private Piece_purchaseDTO ref_piece_detached;
	
	public Piece_detachedDTO(){
		this.name=name;
		this.mark=mark;
		this.model=model;
		this.price=price;
		this.ref_piece_detached=ref_piece_detached;
	}
	
	public Piece_detachedDTO(String nm,String ma,String mo,Piece_purchaseDTO p,Piece_purchaseDTO rpd){
		this.name=nm;
		this.mark=ma;
		this.model=mo;
		this.price=p;
		this.ref_piece_detached=rpd;
	}

	public Piece_purchaseDTO getRef_piece_detached() {
		return ref_piece_detached;
	}

	public void setRef_piece_detached(Piece_purchaseDTO ref_piece_detached) {
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

	
	public Piece_purchaseDTO getPrice() {
		return price;
	}

	public void setPrice(Piece_purchaseDTO price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Piece_detachedDTO [name=" + name + ", mark=" + mark + ", model=" + model + ", price=" + price
				+ ", ref_piece_detached=" + ref_piece_detached + "]";
	}
	
	
}
