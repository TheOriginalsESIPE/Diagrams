package ViewAuthentification;

public class Modelchef {

	
	public Modelchef(){}
	
	
	
	 public String selectlogin(String Nom,String Prenom){
         return "SELECT login FROM repairer" +
					" WHERE firstName= '" + Prenom + "' and lastName= '"+Nom+"' ;";
     }
	 public String selectOp(String q){
		 return "SELECT * FROM operation " +
					" WHERE login_repairer= '" + q + "' ;"; 
		 
	 } 




}
