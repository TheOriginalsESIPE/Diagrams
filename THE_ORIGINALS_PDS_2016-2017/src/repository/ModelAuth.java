package repository;

public class ModelAuth {
    public ModelAuth() {
    }
    /**
     * @param login user name
     * @param pwd  password
     * @return a string of query.
     */

    public String authQuery(String login, String pwd){
        String query = "SELECT * from repairer " +
                "where login = '" + login + "' and password='" + pwd + "';";
        return query;
    }
}
