package repository;
import server.*;
import sql.MyConnectionPool;

import java.sql.*;
import java.util.Date;
import java.util.LinkedList;


import dto.*;


import java.text.SimpleDateFormat;

		// déclenchement de l'algo après ces méthodes
		public class ModelAnais {
			static java.sql.Connection connect1 = null;

			    public ModelAnais(){
			    	MyConnectionPool myConnectionPool = new MyConnectionPool();
			 		connect1 = myConnectionPool.getConnectionFromPool();
					
			    }

		
		 //insertion d'une operation après l'entree du vehicule
		 public int insert(int s1, String s2, String s3) throws ClassNotFoundException , SQLException {
					int cpt=0;
					 try {
						 
					
					Statement stmt = connect1.createStatement( );
					 String sql = "insert into operation (id_breakdown, id_operation, time_begin, time_end, date_begin, date_end, done, login_repairer, numMat) VALUES ('"+s1+"','"+cpt+"','00:00:00','00:00:00','2017-02-17','2017-02-20','3','"+s2+"','"+s3+"')";
					 int n= stmt.executeUpdate(sql);
					 System.out.println(n+" ligne ajoutee");
					 stmt.close();
					 cpt=cpt+1;
					 return n;
					 
					 }
					 finally { //if (connect1 != null)
					 //connect1.close(); 
					 }
					 
		}
		 
		//selection des operations qui sont en attente de pieces et en attente de reparation.
		//celles en attente de pièces seront placées en dernière dans la liste
		public LinkedList<OperationAnais> select() throws ClassNotFoundException , SQLException {
					LinkedList<OperationAnais> operation_list = new LinkedList<OperationAnais>();
					 try {
					 Statement stmt = connect1.createStatement( );
					 ResultSet rs = stmt.executeQuery("select vehicle_warehouse.date_entrance, vehicle_warehouse.numMat, breakdown.emergency_level, breakdown.name, breakdown.id_breakdown, operation.id_breakdown, operation.numMat, operation.done, operation.id_operation, operation.time_begin, operation.date_begin, operation.date_end, operation.login_repairer from vehicle_warehouse, breakdown,operation where breakdown.id_breakdown=operation.id_breakdown and vehicle_warehouse.numMat= operation.numMat and operation.done<>1");
					 String result="";
					 while (rs.next()) {
						 int id_breakdown = rs.getInt("id_breakdown");
						 String numMat = rs.getString("numMat");
						 String date = rs.getString("date_entrance");
						 String name_breakdown = rs.getString("name");
						 int done = rs.getInt("done");
						 int level = rs.getInt("emergency_level");
						 int id_operation = rs.getInt("id_operation");
						 String time_begin = rs.getString("time_begin");
						 String date_begin = rs.getString("date_begin");
						 String date_end = rs.getString("date_end");
						 String login_repairer = rs.getString("login_repairer");
						 OperationAnais operation = new OperationAnais(id_operation, name_breakdown, date_begin, date_end, time_begin, level, login_repairer, numMat, id_breakdown, done);
						 operation_list.add(operation);
						 
						/* if(done==2){
							 String doneBis = "en attente de piece";
							 result =" numMat : "+numMat+" |" + " breakdown id : "+id_breakdown+" |"+ " breakdown name : "+name_breakdown+" |"+" emergency level : "+level+" |"+" done : "+doneBis+" |"+" id operation : "+id_operation+" |"+" date_entrance : "+date;
							 System.out.println(result);
						 }
						 if(done==3){
							 String doneBis = "en attente de reparation";
							 result =" numMat : "+numMat+" |" + " breakdown id : "+id_breakdown+" |"+ " breakdown name : "+name_breakdown+" |"+" emergency level : "+level+" |"+" done : "+doneBis+" |"+" id operation : "+id_operation+" |"+" date_entrance : "+date;
							 System.out.println(result);
						 }*/
						 
						}
					 rs.close();
					 stmt.close();
					 return operation_list;
					}
					 finally { //if (connect1 != null)
					 //connect1.close(); 
					 }
					
					 
		}
		
		public String selectDate(int id_operation) {
			String date = "";
			try {
				Statement st = connect1.createStatement();
				ResultSet rs = st.executeQuery("select vehicle_warehouse.date_entrance from vehicle_warehouse, operation where operation.id_operation="+id_operation+" and vehicle_warehouse.numMat=operation.numMat");
				if(rs.next()) {
					date = rs.getString("date_entrance");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return date;
			
		}
		
		public int selectEmergencyLevel(int id_breakdown) {
			int emergency_level = 0;
			try {
				Statement st = connect1.createStatement();
				ResultSet rs = st.executeQuery("select breakdown.emergency_level from breakdown, operation where operation.id_breakdown="+id_breakdown+" and breakdown.id_breakdown="+id_breakdown+" and operation.id_breakdown=breakdown.id_breakdown");
				if(rs.next()) {
					emergency_level = rs.getInt("emergency_level");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return emergency_level;
		}
		
		public void insertOperationSort(OperationSortAnais operation_sort, int rg) {
			int id_operation_sort = operation_sort.getId();
			int id_operation = operation_sort.getOperation().getId_operation();
			String numMat = operation_sort.getOperation().getNumMat();
			int note = operation_sort.getNote();
			int rang = rg;
			try {
				Statement st = connect1.createStatement();
				st.executeUpdate("insert into operation_sort values("+id_operation_sort+","+id_operation+",'"+numMat+"',"+note+","+rang+")");
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		

		
		public int[] selectCriteres() {
			int[] criteresValues = new int[4];
			try {
				Statement st = connect1.createStatement();
				ResultSet rs = st.executeQuery("select piece_stock, emergency_level, date_entrance, time from critere");
				if(rs.next()) {
					criteresValues[0] = rs.getInt("piece_stock");
					criteresValues[1] = rs.getInt("emergency_level");
					criteresValues[2] = rs.getInt("date_entrance");
					criteresValues[3] = rs.getInt("time");
	
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			
			return criteresValues;
			
		}
	
		
	
		
		
		public int selectDone(int id_operation) {
			int done = 0;
			try {
				Statement st = connect1.createStatement();
				ResultSet rs = st.executeQuery("select done from operation where id_operation="+id_operation);
				if(rs.next()) {
					done = rs.getInt("done");
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			return done;
		}
		
		public Time selectDuree(OperationAnais operation) {
			Time duree = null;
			try {
				Statement st = connect1.createStatement();
				ResultSet rs = st.executeQuery("select breakdown.duree from breakdown, operation where operation.id_breakdown=breakdown.id_breakdown and operation.id_operation="+operation.getId_operation());
				if(rs.next()) {
					duree = rs.getTime("duree");
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			return duree;
		}
		
		public boolean deleteOperationSortTable() {
			boolean res = false;
			try {
				Statement st = connect1.createStatement();
				st.executeUpdate("delete from operation_sort");
				res = true;
				st.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
			return res;
		}
				 
	

}
