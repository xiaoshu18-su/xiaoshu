package cn.sujunhua.pojo;

import java.io.Serializable;

public class Usertype implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer usertype_id;
	private String usertype_name;

	public Integer getUsertype_id() {
		return usertype_id;
	}

	public String getUsertype_name() {
		return usertype_name;
	}

	public void setUsertype_id(Integer usertype_id) {
		this.usertype_id = usertype_id;
	}

	public void setUsertype_name(String usertype_name) {
		this.usertype_name = usertype_name;
	}

	@Override
	public String toString() {
		return "Usertype [usertype_id=" + usertype_id + ", usertype_name=" + usertype_name + "]";
	}

}
