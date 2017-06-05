package dto;

import enumeration.TypeUser;

public class UserDTO {
	protected int userKey;
	private String login;
	private String password;
	private String type;

	/**
	 * @param userKey
	 * @param login
	 * @param password
	 */
	public UserDTO(int userKey, String login, String password) {
		super();
		this.userKey = userKey;
		this.login = login;
		this.password = password;
	}
	
	/**
	 * @param userKey
	 * @param login
	 * @param type
	 */
	public UserDTO(int userKey, String login, TypeUser type) {
		super();
		this.userKey = userKey;
		this.login = login;
		this.type = type.toString();
	}

	/**
	 * @param login
	 * @param password
	 */
	public UserDTO(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}

	/**
	 * @param userKey
	 * @param login
	 */
	public UserDTO(int userKey, String login) {
		super();
		this.userKey = userKey;
		this.login = login;
	}

	/**
	 * @param userKey
	 */
	public UserDTO(int userKey) {
		super();
		this.userKey = userKey;
	}

	/**
	 * @return the userKey
	 */
	public int getUserKey() {
		return userKey;
	}


	/**
	 * @param userKey the userKey to set
	 */
	public void setUserKey(int userKey) {
		this.userKey = userKey;
	}


	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}


	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}


	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}


	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserDTO [userKey=" + userKey + ", login=" + login + ", password=" + password + ", type=" + type + "]";
	}
	
	
	
}
