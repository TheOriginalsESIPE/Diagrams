
//CODE REALISE PAR ANAIS HEMICI ET LAETITIA ZADI

import java.sql.*;
import java.util.Scanner;

import com.mysql.jdbc.Connection;

public class Utilisateur {
	public static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	public static final String DATABASE_URL = "jdbc:mysql://127.0.0.1/anaispds";
	 	private int id;
	    private String email;
	    private String mot_de_passe;
	    private String nom;

	    Utilisateur(){
	        
	    }
	    
	    public Utilisateur(int identifiant, String email, String mot_de_passe, String nom){
	        this.id= identifiant;
	        this.email = email;
	        this.mot_de_passe = mot_de_passe;
	        this.nom = nom;
	    }

		public int getId() {
			return id;
		}

		public String getEmail() {
			return email;
		}

		public String getMot_de_passe() {
			return mot_de_passe;
		}

		public String getNom() {
			return nom;
		}

		public void setId(int id) {
			this.id = id;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public void setMot_de_passe(String mot_de_passe) {
			this.mot_de_passe = mot_de_passe;
		}

		public void setNom(String nom) {
			this.nom = nom;
		}
	  
 public void insert(int id) throws ClassNotFoundException , SQLException {
			 
			 java.sql.Connection connect = null;
			 try {
			 Class.forName(DRIVER_NAME);
			 connect = DriverManager.getConnection(DATABASE_URL,"root","");
			 Statement stmt = connect.createStatement( );
			 System.out.println("*********************************"); 
			 System.out.println("INSERT"); 
			 Scanner sc = new Scanner(System.in);
			 System.out.println("Veuillez saisir un email :");
			 String str = sc.nextLine();
			 Scanner sc1 = new Scanner(System.in);
			 System.out.println("Veuillez saisir un mdp :");
			 String str1 = sc.nextLine();
			 Scanner sc2 = new Scanner(System.in);
			 System.out.println("Veuillez saisir un nom :");
			 String str2 = sc.nextLine();
			 String sql = "insert into Utilisateur(id, email, mot_de_passe, nom) VALUES ('"+id+"','"+str+"','"+str1+"','"+str2+"')";
			 int n= stmt.executeUpdate(sql);
			 System.out.println( n +" lignes ajoutees");
			 System.out.println("*********************************");
			 stmt.close();
			 }
			 finally { if (connect != null)
			 connect.close(); 
			 }
			 
		 }
		 public void select(int id) throws ClassNotFoundException , SQLException {
			 
			 java.sql.Connection connect = null;
			 try {
			 Class.forName(DRIVER_NAME);
			 connect = DriverManager.getConnection(DATABASE_URL,"root","");
			 Statement stmt = connect.createStatement( );
			 System.out.println("*********************************");
			 System.out.println("SELECT");
			 ResultSet rs = stmt.executeQuery("select email, nom, mot_de_passe from Utilisateur where id="+id);
			 while (rs . next()) {
			 String nom = rs.getString("nom");
			 String email = rs.getString("email");
			 String mdp = rs.getString("mot_de_passe");
			 
			 System.out.println("nom : "+nom);
			 System.out.println("email : "+email);
			 System.out.println("mdp : "+mdp);
			 }
			 System.out.println("*********************************");
			 rs.close();
			 stmt.close();
			 }
			 finally { if (connect != null)
			 connect.close(); 
			 }
			 
		 }
		 public void delete(int id) throws ClassNotFoundException , SQLException {
			 
			 java.sql.Connection connect = null;
			 try {
			 Class.forName(DRIVER_NAME);
			 connect = DriverManager.getConnection(DATABASE_URL,"root","");
			 Statement stmt = connect.createStatement( );
			 System.out.println("*********************************");
			 System.out.println("DELETE");
			 int n= stmt.executeUpdate("delete from Utilisateur where id="+id);
			 System.out.println( n +" lignes supprimees");
			 System.out.println("*********************************");
			 stmt.close();
			 }
			 finally { if (connect != null)
			 connect.close(); 
			 }
			 
		 }
		 public void update(int id) throws ClassNotFoundException , SQLException {
			 
			 java.sql.Connection connect = null;
			 try {
			 Class.forName(DRIVER_NAME);
			 connect = DriverManager.getConnection(DATABASE_URL,"root","");
			 Statement stmt = connect.createStatement( );
			 System.out.println("*********************************");
			 System.out.println("UPDATE");
			 Scanner sc = new Scanner(System.in);
			 System.out.println("Veuillez saisir un email :");
			 String str = sc.nextLine();
			 Scanner sc1 = new Scanner(System.in);
			 System.out.println("Veuillez saisir un mdp :");
			 String str1 = sc.nextLine();
			 Scanner sc2 = new Scanner(System.in);
			 System.out.println("Veuillez saisir un nom :");
			 String str2 = sc.nextLine();
			 int n= stmt.executeUpdate("update Utilisateur set email ='"+str+"',mot_de_passe='"+str1+"',nom='"+str2+"' where id="+id+"");
			 System.out.println( n +" lignes modifiees");
			 System.out.println("*********************************");
			 stmt.close();
			 }
			 finally { if (connect != null)
			 connect.close(); 
			 }
			 
		 }
public void selectAll() throws ClassNotFoundException , SQLException {
			 
			 java.sql.Connection connect = null;
			 try {
			 Class.forName(DRIVER_NAME);
			 connect = DriverManager.getConnection(DATABASE_URL,"root","");
			 Statement stmt = connect.createStatement( );
			 System.out.println("*********************************");
			 System.out.println("SELECT ALL");
			 ResultSet rs = stmt.executeQuery("select id, email, nom, mot_de_passe from Utilisateur");
			 while (rs . next()) {
			 String nom = rs.getString("nom");
			 String email = rs.getString("email");
			 String mdp = rs.getString("mot_de_passe");
			 
			 System.out.println("nom : "+nom);
			 System.out.println("email : "+email);
			 System.out.println("mdp : "+mdp);
			 System.out.println("*********************************");
			 }
			 
			 rs.close();
			 stmt.close();
			 }
			 finally { if (connect != null)
			 connect.close(); 
			 }
			 
		 }

}
