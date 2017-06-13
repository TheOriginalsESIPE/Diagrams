package Client.dto;

public class Piece_stockDTO {
	private String login_administrator, date_reception;
	private int counter;
	private String ref_piece_stock;
	private String name;
	
	
	public Piece_stockDTO(){
	}
	
	public Piece_stockDTO(String la,String dr,int c,String rps,String n){
		this.login_administrator=la;
		this.counter=c;
		this.date_reception=dr;
		this.ref_piece_stock=rps;
		this.name = n ;
	}

	
	public String getLogin_administrator() {
		return login_administrator;
	}

	public void setLogin_administrator(String login_administrator) {
		this.login_administrator = login_administrator;
	}

	
	public String getDate_reception() {
		return date_reception;
	}

	public void setDate_reception(String date_reception) {
		this.date_reception = date_reception;
	}

	
	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	
	public String getRef_piece_stock() {
		return ref_piece_stock;
	}

	public void setRef_piece_stock(String ref_piece_stock) {
		this.ref_piece_stock = ref_piece_stock;
	}
	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "[{\"login_administrator\":\"" + login_administrator + "\",\"name\":\"" + name + "\", \"date_reception\":\"" + date_reception
				+ "\",\"counter\":\"" + counter + "\",\"ref_piece_stock\":\"" + ref_piece_stock + "\"}]";
	}
	
	
}
