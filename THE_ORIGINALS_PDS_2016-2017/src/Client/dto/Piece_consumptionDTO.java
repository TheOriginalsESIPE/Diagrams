package Client.dto;


public class Piece_consumptionDTO{
	
	private Piece_stockDTO ref_piece_stock;
	private int number; 
	private OperationDTO id_operation;
	
	public Piece_consumptionDTO(){
	}
	
	public Piece_consumptionDTO(Piece_stockDTO rps, int nb, OperationDTO io){
		this.ref_piece_stock=rps;
		this.number=nb;
		this.id_operation=io;
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


	public OperationDTO getId_operation() {
		return id_operation;
	}

	public void setId_operation(OperationDTO id_operation) {
		this.id_operation = id_operation;
	}

	@Override
	public String toString() {
		return "[{\"ref_piece_stock\":\"" + ref_piece_stock + "\",\"number\":\"" + number + "\",\"id_operation\":\""
				+ id_operation + "\"}]";
	}
	
	
}
