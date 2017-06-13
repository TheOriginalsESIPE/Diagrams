package Server.repository;

public class ModelNewOp {
public ModelNewOp () {}

public String ReqInserOp (int idpanne ,String login,String matricule){
	String query = "INSERT INTO operation VALUES (id_operation,"+idpanne+",null,null,null,null,3,'"+login+"','"+matricule+"');";
	return query ;

}

public String testPanne (String nomDepanne){
	String query = "Select * from BREAKDOWN WHERE name = '"+ nomDepanne+"';" ;
	return query ;

}

public String getIdBreak (String nomDepanne){
	String query = "SELECT id breakdown form breakdown where name = '"+nomDepanne+"';";
	return query ;

}


}
