package cn.sujunhua.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Department implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer department_id;
	private String department_name;
	private Date department_time;

	// 员工实体 部门对员工 一对多
	private List<User> user;

	public Integer getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(Integer department_id) {
		this.department_id = department_id;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public Date getDepartment_time() {
		return department_time;
	}

	public void setDepartment_time(Date department_time) {
		this.department_time = department_time;
	}

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Department [department_id=" + department_id + ", department_name=" + department_name
				+ ", department_time=" + department_time + ", user=" + user + "]";
	}

}
