package Client.dto;

public class Piece_consumptionDTO{
	
	private Piece_stockDTO ref_piece_stock;
	private Piece_stockDTO name;
	private int number; 

	
	public Piece_consumptionDTO(){
	}
	
	public Piece_consumptionDTO(Piece_stockDTO rps,Piece_stockDTO n,int nb){
		this.ref_piece_stock=rps;
		this.number=nb;
		this.name=n ; 

	}

	public Piece_stockDTO getRef_piecce_stock() {
		return ref_piece_stock;
	}

	public void setRef_piecce_stock(Piece_stockDTO ref_piecce_stock) {
		this.ref_piece_stock = ref_piecce_stock;
	}

	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	public Piece_stockDTO getname() {
		return name;
	}

	public void setname(Piece_stockDTO name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "[{\"ref_piece_stock\":\"" + ref_piece_stock + "\",\"name\":\"" + name + "\",\"number\":\"" + number + "\",\"id_operation\":\""
				 + "\"}]";
	}
	
	
}
