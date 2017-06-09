package services;

public class AuthentificationLayer {
	public AuthentificationLayer(Token token) throws Exception {
		// TODO Auto-generated constructor stub
		if(ConnectionServices.isConnected(token.getToken())){
			throw new Exception("Not autorised, session timeout");
		}
	}
}
