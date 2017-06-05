package services;

import java.util.Date;

public class Token {
	private String token;
	private Date dateCreation;
	private int userKey;
	private String userType;
	
	public Token() {
		
	}
	
	

	/**
	 * @param token
	 */
	public Token(String token) {
		super();
		this.token = token;
	}



	/**
	 * @param token
	 * @param dateCreation
	 * @param userKey
	 * @param userType
	 */
	public Token(String token, int userKey, String userType) {
		super();
		this.token = token;
		this.dateCreation = new Date();
		this.userKey = userKey;
		this.userType = userType;
	}



	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the dateCreation
	 */
	public Date getDateCreation() {
		return dateCreation;
	}

	/**
	 * @param dateCreation the dateCreation to set
	 */
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
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
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}

	/**
	 * @param userType the userType to set
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Token [token=" + token + ", dateCreation=" + dateCreation + ", userKey=" + userKey + ", userType="
				+ userType + "]";
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj){
			return true;
			}
		if (obj == null){
			return false;
			}
		if (getClass() != obj.getClass()){
			return false;
			}
		
		Token other = (Token) obj;
		return token.equals(other.getToken());
	}
	
	
	
	
}
