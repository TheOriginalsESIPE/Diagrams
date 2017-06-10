package sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HandlerSQL {
	Connection connection =null;
    MyConnectionPool myConn = null;

    Statement st = null;
    ResultSet rs = null;

    public HandlerSQL(){
        myConn = new MyConnectionPool();
        connection = myConn.getConnectionFromPool();
    }

    public int updateQuery(String query){
        int nbData = 0;
        try {
            st = connection.createStatement();
            nbData = st.executeUpdate(query);
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nbData;
    }

    public ResultSet selectQuery(String query){
        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }
}


