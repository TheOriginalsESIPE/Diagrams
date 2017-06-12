package Client.controller;
import java.sql.SQLException;
import java.sql.Time;
import java.util.LinkedList;

import Server.repository.*;
import Server.dto.*;

public class ControllerAnais{
		private ModelAnais m;
	
		
		public ControllerAnais(ModelAnais m){
			this.m=m;
		
			
			
			
		}
		public LinkedList<OperationAnais> selectAll(){
			LinkedList<OperationAnais> operation_list = null;
				try {
						operation_list = m.select();
							
						
				} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
				}
				return operation_list;
		}
		
		public String selectDate(int id_operation) {
			return m.selectDate(id_operation);
		}
		
		public int selectEmergencyLevel(int id_breakdown) {
			return m.selectEmergencyLevel(id_breakdown);
		}
		
		public void insertOperationSort(OperationSortAnais operation_sort, int rg) {
			m.insertOperationSort(operation_sort, rg);
		}
		
		public int[] selectCriteres() {
			return m.selectCriteres();
		}
		
		public int selectDone(int id_operation) {
			return m.selectDone(id_operation);
		}
		
		public Time selectDuree(OperationAnais operation) {
			return m.selectDuree(operation);
		}
		
		public boolean deleteOperationSortTable() {
			return m.deleteOperationSortTable();
		}
		
	public void insert(){
			
			
				try {
				
					m.insert(1, "henri", "AAA");
					m.insert(2, "henri", "AAA");
					m.insert(1, "henri", "BBB");
					m.insert(3, "henri", "AAA");
					
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
				}
		

}
