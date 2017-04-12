package repository;


import java.sql.*;
import java.util.Date;
import sql.MyConnectionPool;
import java.text.SimpleDateFormat;

	public class ModelPiece {
	
		java.sql.Connection connect1 = null;
 		
		    private String ref_piece_detached;
		    private String name;
		    private String mark;
		    private String model;
		    private float price;
		   
		    

		    public ModelPiece(){
		    	
		    	MyConnectionPool myConnectionPool = new MyConnectionPool();
		 		connect1 = myConnectionPool.getConnectionFromPool();
				
		    }
		    
			

			
	 public int insert(String ref_piece_detached, String name, String mark, String model, float answer3bis) throws ClassNotFoundException , SQLException {
				 
				 try {
				
				 Statement stmt = connect1.createStatement( );
				 String sql = "insert into piece_detached(ref_piece_detached, name, mark, model, price) VALUES ('"+ref_piece_detached+"','"+name+"','"+mark+"','"+model+"','"+answer3bis+"')";
				 int n= stmt.executeUpdate(sql);
				 stmt.close();
				 return n;
				 }
				 finally { //if (connect1 != null)
				 //connect1.close(); 
				 }
				 
	}




		//It must return an object DTO

        /**
         * @modify by yuxin, this function return a Result Set consisted by 2 DTO
         * @param ref_piece_detached
         * @return
         */
    public String select(String ref_piece_detached){
        return "SELECT piece_detached.ref_piece_detached, model, mark, price, name FROM piece_detached";
          
    }

//	public String select(String ref_piece_detached) throws ClassNotFoundException , SQLException {
//
//        try {
//
//            Statement stmt = connect1.createStatement( );
//            ResultSet rs = stmt.executeQuery("select vehicule.numMat, vehicule_depot.numMat, model, mark, vehicle_type, numPlace, date_entrance, date_wayout from vehicule, vehicule_depot where vehicule.numMat = vehicule_depot.numMat and vehicule_depot.numMat ='"+numMat+"'");
//            String result="";
//            while (rs.next()) {
//					 String model = rs.getString("model");
//					 String mark = rs.getString("mark");
//					 String vehicle_type = rs.getString("vehicle_type");
//					 int numPlace = rs.getInt("numPlace");
//					 String numMatr = rs.getString("numMat");
//					 String date_entrance = rs.getString("date_entrance");
//					 String date_wayout = rs.getString("date_wayout");
//					 result =" numero immatriculation : "+numMat+"\r\n" + " type de vehicule : "+vehicle_type+"\r\n"+" model : "+model+"\r\n"+ " mark : "+mark+"\r\n"+ " numero place : "+numPlace+"\r\n"+ " date entree : "+date_entrance+"\r\n"+" date sortie : "+date_wayout;
//
//            }
//            rs.close();
//            stmt.close();
//            return result;
//        } finally { //if (connect1 != null)
//				 //connect1.close();
//        }
//	}
			 
			 public int delete(String ref_piece_detached) throws ClassNotFoundException , SQLException {
				 
				 
				 try {
				 
				 Statement stmt = connect1.createStatement( );
				 String sql ="delete from piece_detached where ref_piece_detached='"+ref_piece_detached+"'";
				 int n= stmt.executeUpdate(sql);
				 stmt.close();
				 return n;
				 }
				 finally { //if (connect1 != null)
				 //connect1.close(); 
				 }
				 
			 }
			
			
			 public int update(String ref_piece_detached, String answer1) throws ClassNotFoundException , SQLException {
				 
				 
				 try {
				 
				 Statement stmt = connect1.createStatement( );
				 String sql ="update piece_detached set price ='"+answer1+"' where ref_piece_detached='"+ref_piece_detached+"'";
				 int n= stmt.executeUpdate(sql);
				 stmt.close();
				 return n;
				 }
				 finally { //if (connect != null)
				 //connect.close(); 
				 }
				 
			 }

public boolean log(String name, String pwd) throws ClassNotFoundException , SQLException {
	boolean istrue=false;
				 
				 
				 try {
				 
				 Statement stmt = connect1.createStatement( );
				 ResultSet rs = stmt.executeQuery("select login, password from reparateur where login='"+name+"' and password='"+pwd+"'");
				 String result="";
				 
				while (rs.next()) {
					String n = rs.getString("login");
					String m = rs.getString("password");
					 if(n=="" || m==""){
						 istrue = false;
							 	 
						 }
					 else istrue = true;
					}
			
				 rs.close();
				 stmt.close();
				 return istrue;
				 }
				 finally { //if (connect1 != null)
					 //connect1.close(); 
				 }
				
				 
	}
			 
	

	}




