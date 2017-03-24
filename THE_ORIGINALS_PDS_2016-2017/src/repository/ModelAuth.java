package repository;

import sql.MyConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by tearsyu on 23/03/17.
 * This class was a part of ModelVehicle, I rewrite it in a new class to divide the
 * process of authentication and the operations of vehicle.
 * @author Anais Hemici
 * @author tearsyu
 */
public class ModelAuth {
    Connection connect = null;
    MyConnectionPool myConnectionPool;
    public ModelAuth(){
        myConnectionPool = new MyConnectionPool();
        connect = myConnectionPool.getConnectionFromPool();
    }

    /**
     * @param name user name
     * @param pwd  password
     * @return true if it's a legal user.
     * @return  false if it's a illegal user.
     */
    public boolean islegalAuth(String name, String pwd){
        boolean isTrue = false;
        try {

            Statement stmt = connect.createStatement( );
            ResultSet rs = stmt.executeQuery("select login, password from reparateur" +
                    " where login='"+name+"' and password='"+pwd+"'");

            while (rs.next()) {
                String n = rs.getString("login");
                String m = rs.getString("password");
                if(n=="" || m==""){
                    isTrue = false;
                }
                else isTrue = true;
            }

            rs.close();
            stmt.close();
            myConnectionPool.returnConnectionPool(connect);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isTrue;
    }
}
