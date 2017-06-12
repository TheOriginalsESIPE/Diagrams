package repository;


/**
 * Created by tearsyu on 23/03/17.
 * This class was a part of ModelVehicle, I rewrite it in a new class to divide the
 * process of authentication and the operations of vehicle.
 * @author tearsyu
 */
public class ModelAuth {
    private String login, pwd;
    public ModelAuth(String login, String pwd) {
        this.login = login;
        this.pwd = pwd;
    }

    public String authAdmin(){
        String query = "SELECT * " +
                "FROM administrator " +
                "WHERE (administrator.login='" + login + "' and administrator.password='" + pwd + "');";
        return query;
    }

    public  String authDirector(){
        String query = "SELECT * FROM director WHERE director.login = '" + login +
                "' and director.password = '" + pwd + "';";
        return query;
    }

    public String authRepairer(){
        return "SELECT * FROM repairer WHERE repairer.login = '" + login +
                "' and repairer.password = '" + pwd + "';";
    }

    public String authManut(){
        return "SELECT * FROM manutentionaire WHERE manutentionaire.login = '" + login +
                "' and manutentionaire.password = '" + pwd + "';";

    }


}
