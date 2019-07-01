package cn.sujunhua.common.utils;

import cn.sujunhua.pojo.Department;

public class CountAndDeptUtil {
	private Integer count;
	private Department department;

	public CountAndDeptUtil(Integer count, Department department) {
		super();
		this.count = count;
		this.department = department;
	}

	public CountAndDeptUtil() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getCount() {
		return count;
	}

	public Department getDepartment() {
		return department;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}
