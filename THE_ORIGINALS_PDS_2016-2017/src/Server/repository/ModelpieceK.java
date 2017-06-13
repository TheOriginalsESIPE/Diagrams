package Server.repository;

public class ModelpieceK {
public ModelpieceK(){}
public String SelectNs(String r){
	String z = "SELECT * from piece_stock where name='"+r+"';";
	return z;
}
public String SelectHdr(String r){
	String z = "Select date_reception from piece_stock where ref_piece_detached = '"+r+"';";
	return z;}
	public String SelectHdrc(String r){
		String z = "Select date_reception from piece_consumption where ref_piece_stock = '"+r+"';";
		return z;	
}
public String SelectPc(){
	String z = "SELECT name,ref_piece_stock from piece_consumption ;";
	return z;
}


}
