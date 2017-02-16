import java.sql.*;

/**
 * Created by tearsyu on 13/02/17.
 */
public class ConnectionJDBC {
    static final String link = "jdbc:mysql://localhost/CSC";
    static final String driver = "com.mysql.jdbc.Driver";
    Connection conn = null;
    Statement statement = null;
    ResultSet rs = null;
    CallableStatement callst = null;

    public void connectJDBC(){
        //Properties prop = new Properties();

        try {
            //prop.load(new FileInputStream("src/config.properties"));
            //String username = prop.getProperty("dbuser");
            //String password = prop.getProperty("dbpassword");
            String username = "Tearsyu";
            String password = "0123398lain";
            System.out.println("Connect to database...");
            Class.forName(driver);
            conn = DriverManager.getConnection(link, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeJDBC(){
        try {
            if (rs != null)
                rs.close();
            if (statement != null)
                statement.close();
            conn.close();
            System.out.println("close JDBC...");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
