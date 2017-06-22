package Server.repository;

public class ModelPieceOperation {
	public ModelPieceOperation (){}

	public String testExist(String piece){
        String query = "SELECT * FROM piece_stock where name= '"+piece+"';";
       
                
        return query; 
}
	
	
	public String testtPiece(String piece){
        String query = "SELECT piece_stock.counter from piece_stock ,piece_detached where piece_stock.ref_piece_detached="
        		+ "piece_detached.ref_piece_detached and piece_detached.name='"+piece+"';" ;
       
                
        return query; 
}
	
	public String importPiece (String piece, int qte){
		
        String query = "UPDATE piece_stock SET  counter=counter-" +qte+" where name ='"+piece+"';";
        return query;
}
	
	public String insertPieceConso (String id_piece,String nomPiece ,int qte, int id_op ){
		
		String query = "INSERT INTO piece_consumption VALUES ('"+id_piece+"','"+nomPiece+"',"+qte+","+id_op+",CURRENT_DATE);";
        		
        return query;
}
	public String recupereridPiece (String namePiece){
        String query = "select piece_stock.ref_piece_detached from piece_stock, piece_detached where"
        		+ " piece_stock.ref_piece_detached = piece_detached.ref_piece_detached and piece_detached.name ='"+namePiece+"' ;" ;
		return query;

}
	public String recupererIdOp (){
		String query ="";
		return query;
	}
	public boolean estUnEntier(String chaine) {
		try {
			Integer.parseInt(chaine);
		} catch (NumberFormatException e){
			return false;
		}
 
		return true;
	
	}
	
	
	
	
	
}