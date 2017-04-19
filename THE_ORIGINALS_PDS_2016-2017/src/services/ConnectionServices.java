package services;

import java.util.ArrayList;
import java.util.UUID;

import dto.UserDTO;
import repository.UserRepository;

public class ConnectionServices {
	
	public static ArrayList<Token> listToken = new ArrayList<Token>();
	
	public static Token connect(String login, String password) throws Exception {
		
		UserDTO userdto = UserRepository.userConnect(login, password);
		
		if(userdto == null) {
			throw new Exception("Connection failed, Verify your login and password");
		}
		
		Token token = new Token(UUID.randomUUID().toString(), userdto.getUserKey(), userdto.getType());
		
		listToken.add(token);
		
		return token;
	}
	
	public static boolean isConnected(String token){
		
		return listToken.contains(new Token(token));
		
	}
}
