package Server.sql;

import java.sql.*;


/**
 * Created by tearsyu on 04/04/17.
 */
public class HandlerSQL {
    Connection connection =null;
    MyConnectionPool myConn = null;

    Statement st = null;
    ResultSet rs = null;

    public HandlerSQL(){
        myConn = new MyConnectionPool();
        connection = myConn.getConnectionFromPool();
    }

    /**
     * Executes the given SQL statement, which may be an <code>INSERT</code>,
     * <code>UPDATE</code>, or <code>DELETE</code> statement or an
     * SQL statement that returns nothing, such as an SQL DDL statement.
     *<p>
     * <strong>Note:</strong>This method cannot be called on a
     * <code>PreparedStatement</code> or <code>CallableStatement</code>.
     * @param query an SQL Data Manipulation Language (DML) statement, such as <code>INSERT</code>, <code>UPDATE</code> or
     * <code>DELETE</code>; or an SQL statement that returns nothing,
     * such as a DDL statement.
     *
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
     *         or (2) 0 for SQL statements that return nothing
     *
     * @exception SQLException if a database access error occurs,
     * this method is called on a closed <code>Statement</code>, the given
     * SQL statement produces a <code>ResultSet</code> object, the method is called on a
     * <code>PreparedStatement</code> or <code>CallableStatement</code>
     */
    public int updateQuery(String query){
        int nbData = 0;
        try {
            st = connection.createStatement();
            nbData = st.executeUpdate(query);
            st.close();
            myConn.returnConnectionPool(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nbData;
    }

    public ResultSet selectQuery(String query){
        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            myConn.returnConnectionPool(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }
}
