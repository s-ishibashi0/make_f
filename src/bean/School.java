package bean;

import java.io.Serializable;

public class School implements Serializable {
	private String cd; // TODO:フィールド名の説明
	private String name;// TODO:フィールド名の説明

	public String getCd() {
		return cd;
	}

	public void setCd(String cd) {
		this.cd = cd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
