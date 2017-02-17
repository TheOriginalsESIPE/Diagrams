	package sql;

	/**
	* MyConnectionPool
	* 
	* Created on 2017 'PDS'.
	* 
	* @author TheOriginal team <a href = "mailto:theoriginal_esipe@gmail.com">theoriginal_esipe@gmail.com</a>.
	* 
	* @version 1.4
	*/


	import java.io.IOException;
	import java.io.InputStream;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;
	import java.util.Properties;
	import java.util.Vector;

	public class MyConnectionPool {
		
		/**
		 * Vector 'Thread-Safe' does not need synchronization.
		 * Creation of connection list
		 */
		
		Vector<Connection> connectionList = new Vector<Connection>();
		
		/**
		 * This constructor
		 */

		
		public MyConnectionPool(){
			initializeConnexionPool();
		}
		
		/**
		 * 
		 * Initialization of Connection Pool method
		 * 
		 * This method allows to create the expected maximum connection
		 */
		
		private void initializeConnexionPool(){
			while(!checkIfConnectionPoolIsFull()){
				System.out.println("Connection Pool is not full. Proceeding with add new connection");
				/*
				Adding a new connection instance 
				until the pool is full
				 */
				
				connectionList.add(createNewConnectionForPool());
			}
			System.out.println("Connection pool is full");
		}
		
		/**
		 * method to check if the connection pool is full
		 * 
		 * @return a boolean if the connection pool is full or not
		 */

		private synchronized boolean checkIfConnectionPoolIsFull(){
			final int MAX_POOL_SIZE = 21;

			if(connectionList.size() < MAX_POOL_SIZE){
				return false;
			}

			return true;
		}
		
		/**
		 * 
		 *Connection creation method
		 * 
		 * @return a connection.
		 */

		private Connection createNewConnectionForPool(){
			Connection connection = null;
			Properties properties = new Properties();
			InputStream input = null;

			String driverName;
			String dburl;
			String userName;
			String userPasswd;
			String fileName = "C:/Users/dembe/workspace/PDS_2016-2017/src/sqlconfigDB.properties";

			input = getClass().getClassLoader().getResourceAsStream(fileName);
			if(input == null){
				System.out.println("Sorry, unable to find "+fileName);
			}
			//loading a properties file
			try{
				properties.load(input);
			}catch(IOException e){
				e.printStackTrace();
			}

			driverName = properties.getProperty("driverName");
			dburl = properties.getProperty("dburl");
			userName = properties.getProperty("userName");
			userPasswd = properties.getProperty("userPasswd");

			try{
				Class.forName(driverName);
				connection = DriverManager.getConnection(dburl, userName, userPasswd);
				System.out.println("Connection: "+connection);
			}
			catch(SQLException sqle){
				System.err.println("Une erreur SQL "+sqle);
				return null;
			}
			catch(ClassNotFoundException cnfe){
				System.err.println("Class introuvable "+cnfe);
				return null;
			}
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			return connection;
		}
		
		/**
		 * Get connection method. It gives a connection if someone requests to connect to the Database.
		 * 
		 * @return a connection;
		 */

		public synchronized Connection getConnectionFromPool(){
			Connection connection = null;
			while(connectionList.size() >= 0){
				if(connectionList.size() > 0){
					connection = connectionList.get(0);
					connectionList.remove(0);
				}
				if(connectionList.size() == 0){
					connectionList.add(createNewConnectionForPool());
					connection = connectionList.get(0);
					connectionList.remove(0);
				}
			}

			return connection;
		}
		
		/**
		 * Method to return a connection pool
		 * 
		 * @param connection
		 */

		public synchronized void returnConnectionPool(Connection connection){
			//Adding the connection from the client back to the connection pool
			connectionList.add(connection);	
		}
		
		/**
		 * Method return list size
		 * 
		 * @return the size of connection list.
		 */
		
		public synchronized int sizeConnectionPool(){
			return connectionList.size();
		}
	}

