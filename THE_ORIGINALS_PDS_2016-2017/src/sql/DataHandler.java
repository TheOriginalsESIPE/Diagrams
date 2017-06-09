package sql;

import java.beans.Statement;
import java.io.IOException;
import java.lang.reflect.GenericArrayType;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataHandler {
	Connection connect;
	java.sql.Statement stmt;
	ResultSet rset;
	
	
	public DataHandler() {
		// TODO Auto-generated constructor stub
		MyConnectionPool myconnectionPool = new MyConnectionPool();
		connect = myconnectionPool.getConnectionFromPool();
	}
	
	
	public ResultSet executeQuery(String query) throws Exception{
		
		try{
		stmt = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		System.out.println("Executing query" + query);
		rset = stmt.executeQuery(query);
		
		return rset;
		
		}catch(SQLException sqle){
			sqle.printStackTrace();
			throw new Exception(sqle.getMessage());
		}
		
	}
}
