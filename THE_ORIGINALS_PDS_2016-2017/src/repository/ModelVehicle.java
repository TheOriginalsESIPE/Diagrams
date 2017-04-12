package repository;


import java.sql.*;
import java.util.Date;
import sql.MyConnectionPool;
import java.text.SimpleDateFormat;

	public class ModelVehicle {
	
		java.sql.Connection connect1 = null;
 		
		    private String numMat;
		    private String model;
		    private String mark;
		    private String vehicle_type;
		    private int numPlace;
		    private String date_entrance;
		    private String date_wayout;
		    

		    public ModelVehicle(){
		    	
		    	MyConnectionPool myConnectionPool = new MyConnectionPool();
		 		connect1 = myConnectionPool.getConnectionFromPool();
				
		    }
		    
			

			
	 public int insert(String numMat, String date_entrance, int numPlace) throws ClassNotFoundException , SQLException {
				 
				 try {
				
				 Statement stmt = connect1.createStatement( );
				 String sql = "insert into vehicule_depot(numMat, date_entrance, numPlace) VALUES ('"+numMat+"','"+date_entrance+"','"+numPlace+"')";
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
         * @param numMat
         * @return
         */
    public String select(String numMat){
        return "SELECT vehicule.numMat, modele, marque, vehicule_type, numPlace, " +
                "date_entree, date_sortie FROM vehicule, vehicule_depot WHERE" +
                " vehicule.numMat = vehicule_depot.numMat and vehicule_depot.numMat ='"+ numMat +"'";
    }

//	public String select(String numMat) throws ClassNotFoundException , SQLException {
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
			 
			 public int delete(String numMat) throws ClassNotFoundException , SQLException {
				 
				 
				 try {
				 
				 Statement stmt = connect1.createStatement( );
				 String sql ="delete from vehicule_depot where numMat='"+numMat+"'";
				 int n= stmt.executeUpdate(sql);
				 stmt.close();
				 return n;
				 }
				 finally { //if (connect1 != null)
				 //connect1.close(); 
				 }
				 
			 }
			
			
			 public int update(String numMat) throws ClassNotFoundException , SQLException {
				 
				 
				 try {
				 
				 Statement stmt = connect1.createStatement( );
				 SimpleDateFormat formater = null;
				 Date aujourdhui = new Date();
				 formater = new SimpleDateFormat("yy-MM-dd");
				 String date_sort= formater.format(aujourdhui);
				 String sql ="update vehicule_depot set date_wayout ='"+date_sort+"' where numMat='"+numMat+"'";
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




