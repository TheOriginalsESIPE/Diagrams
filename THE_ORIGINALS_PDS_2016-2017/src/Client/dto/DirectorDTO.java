package Client.dto;

public class DirectorDTO{
	
	private String login;
	private String password;
	private String firstname;
	private String lastname;
	private String adress;
	private int numTel;
	
	public DirectorDTO(){
	}
	
	public DirectorDTO(String lg,String psw,String fn,String ln,String ad,int nT){
		this.login=lg;
		this.password=psw;
		this.firstname=fn;
		this.lastname=ln;
		this.adress=ad;
		this.numTel=nT;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	
	public int getNumTel() {
		return numTel;
	}
	public void setNumTel(int numTel) {
		this.numTel = numTel;
	}

	@Override
	public String toString() {
		return "[{\"login\":\"" + login + "\",\"password\":\"" + password + "\",\"firstname\":\"" + firstname + "\"," +
				"\"lastname\":\"" + lastname + "\",\"adress\":\""+ adress + "\",\"numTel\":\"" + numTel + "\"}]";
	}
	
	
}