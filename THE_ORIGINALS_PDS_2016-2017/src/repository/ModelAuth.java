package repository;


/**
 * Created by tearsyu on 23/03/17.
 * This class was a part of ModelVehicle, I rewrite it in a new class to divide the
 * process of authentication and the operations of vehicle.
 * @author Anais Hemici
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
        String query = "SELECT * from reparateur " +
                "where login = '" + login + "' and password='" + pwd + "';";
        return query;
    }

}
