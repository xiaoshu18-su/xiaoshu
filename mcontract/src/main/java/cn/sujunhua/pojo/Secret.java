package cn.sujunhua.pojo;

import java.io.Serializable;

public class Secret implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer secret_id;
	private String secret_name;

	public Integer getSecret_id() {
		return secret_id;
	}

	public String getSecret_name() {
		return secret_name;
	}

	public void setSecret_id(Integer secret_id) {
		this.secret_id = secret_id;
	}

	public void setSecret_name(String secret_name) {
		this.secret_name = secret_name;
	}

	@Override
	public String toString() {
		return "Secret [secret_id=" + secret_id + ", secret_name=" + secret_name + "]";
	}

}
