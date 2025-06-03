package bean;

public class Teacher implements java.io.Serializable{

	private String id;
	private String password;
	private String login;
	private String school;

	public String getId() {
		return id;
	}
	public String getPassword() {
		return password;
	}
	public String getLogin() {
		return login;
	}
	public String getSchool() { // ← 追加
		return school;
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

	public void setSchool(String school) { // ← 追加
		this.school=school;
	}

}