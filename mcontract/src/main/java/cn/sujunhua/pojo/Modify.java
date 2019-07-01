package cn.sujunhua.pojo;

import java.io.Serializable;

public class Modify implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer modify_id;
	private String modify_name;

	public Integer getModify_id() {
		return modify_id;
	}

	public String getModify_name() {
		return modify_name;
	}

	public void setModify_id(Integer modify_id) {
		this.modify_id = modify_id;
	}

	public void setModify_name(String modify_name) {
		this.modify_name = modify_name;
	}

	@Override
	public String toString() {
		return "Modify [modify_id=" + modify_id + ", modify_name=" + modify_name + "]";
	}

}
