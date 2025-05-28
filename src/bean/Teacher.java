package bean;

public class Teacher implements java.io.Serializable{

	private String id;
	private String password;
	private String login;

	public String getId() {
		return id;
	}
	public String getPassword() {
		return password;
	}
	public String getLogin() {
		return login;
	}

	public void setId(String id) {
		this.id=id;
	}
	public void setLogin(String login) {
		this.login=login;
	}
	public void setPassword(String password) {
		this.password=password;
	}
}