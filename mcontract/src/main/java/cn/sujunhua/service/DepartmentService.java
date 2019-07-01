package cn.sujunhua.service;

import java.util.List;

import cn.sujunhua.pojo.Department;

public interface DepartmentService {
	public List<Department> selectDepartment();

	public Department findDepartmentByid(Integer department_id);
	
	//根据时间后序查找部门
	public List<Department> selectDepartmentOrderdesc();
	
	public boolean checkDeptexit(String department_name);
	
	public Integer addDepartment(Department department);
	
	public String updateDeptByid(Department department);
	
	public String deleteDeptByid(Integer department_id);
}
