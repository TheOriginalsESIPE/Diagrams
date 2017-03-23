package dto;


<<<<<<< HEAD
=======

>>>>>>> 835007e46af7dc4575eb85f9a21231f2be84fdc1
public class Piece_consumptionDTO{
	
	private Piece_stockDTO ref_piece_stock;
	private int number; 
	private OperationDTO id_operation;
	
	public Piece_consumptionDTO(){
		this.ref_piece_stock=ref_piece_stock;
		this.number=number;
		this.id_operation=id_operation;
	}
	
	public Piece_consumptionDTO(Piece_stockDTO rps,int nb, OperationDTO io){
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
		return "Piece_consumptionDTO [ref_piece_stock=" + ref_piece_stock + ", number=" + number + ", id_operation="
				+ id_operation + "]";
	}
	
	
}
