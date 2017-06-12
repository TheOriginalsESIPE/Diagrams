package Server.repository;

import java.sql.ResultSet;

import Server.dto.UserDTO;
import Client.enumeration.TypeUser;
import Client.enumeration.UserEnum;
import Server.sql.DataHandler;

public class UserRepository {
	
	
	public static UserDTO userConnect(String login, String password) throws Exception{
		UserDTO userdto = null;
		
		String query = "SELECT * FROM USERS WHERE "
						+ "upper(login) = '" + login.toUpperCase() + "'"
						+ " and password = '" + password.toUpperCase() + "'";
		
		DataHandler datatHandler = new DataHandler();
		ResultSet rset = datatHandler.executeQuery(query);
		
		if(rset.next()){
			System.out.println("UUUU");
			userdto = new UserDTO(rset.getInt(UserEnum.UserKey.toString()),
									rset.getString(UserEnum.Login.toString()),
									TypeUser.valueOf(rset.getString(UserEnum.Type.toString())));
		}
		
		return userdto;
	}
	 
	
	
}
