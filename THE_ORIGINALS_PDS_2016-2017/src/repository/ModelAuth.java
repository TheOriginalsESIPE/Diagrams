package repository;


/**
 * Created by tearsyu on 23/03/17.
 * This class was a part of ModelVehicle, I rewrite it in a new class to divide the
 * process of authentication and the operations of vehicle.
 * @author tearsyu
 */
public class ModelAuth {

    public ModelAuth() {
    }
    /**
     * @param login user name
     * @param pwd  password
     * @return a string of query.
     */

    public String authQuery(String login, String pwd){
        String query = "SELECT * " +
                "FROM administrator, director, repairer " +
                "WHERE (repairer.login='" + login + "' and repairer.password='" + pwd + "')" +
                " OR (administrator.login='" + login + "' and administrator.password='" + pwd + "')" +
                " OR (director.login='" +login + "' and director.password='" + pwd + "');";
        return query;
    }


}
