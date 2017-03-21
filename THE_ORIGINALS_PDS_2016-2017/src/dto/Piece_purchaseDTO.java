package dto;

<<<<<<< HEAD

=======
>>>>>>> 835007e46af7dc4575eb85f9a21231f2be84fdc1
public class Piece_purchaseDTO{
	
	private String ref_piece_detached, date_purchase;
	private int price,number,id_achat;
	
	public Piece_purchaseDTO(){
		this.ref_piece_detached=ref_piece_detached;
		this.date_purchase=date_purchase;
		this.price=price;
		this.number=number;
		this.id_achat=id_achat;
	}
	
	public Piece_purchaseDTO(String rpd,String dp,int p,int nb,int ia){
		this.ref_piece_detached=rpd;
		this.date_purchase=dp;
		this.price=p;
		this.number=nb;
		this.id_achat=ia;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	

	public int getId_achat() {
		return id_achat;
	}

	public void setId_achat(int id_achat) {
		this.id_achat = id_achat;
	}


	public String getDate_purchase() {
		return date_purchase;
	}

	public void setDate_purchase(String date_purchase) {
		this.date_purchase = date_purchase;
	}

	
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	
	public String getRef_piece_detached() {
		return ref_piece_detached;
	}

	public void setRef_piece_detached(String ref_piece_detached) {
		this.ref_piece_detached = ref_piece_detached;
	}

	@Override
	public String toString() {
		return "Piece_purchaseDTO [ref_piece_detached=" + ref_piece_detached + ", date_purchase=" + date_purchase
				+ ", price=" + price + ", number=" + number + ", id_achat=" + id_achat + "]";
	}
	
	
}
