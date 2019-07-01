package cn.sujunhua.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import cn.sujunhua.dao.DepartmentDao;
import cn.sujunhua.pojo.Department;
import cn.sujunhua.service.DepartmentService;

@Service
@Scope("prototype")
public class DepartmentServiceImpl implements DepartmentService {
    
	@Autowired
	private DepartmentDao departmentDao;
	
	@Override
	public List<Department> selectDepartment() {
		return departmentDao.selectDepartment();
	}

	@Override
	public Department findDepartmentByid(Integer department_id) {
		return departmentDao.findDepartmentByid(department_id);
	}

	@Override
	public List<Department> selectDepartmentOrderdesc() {
		return departmentDao.selectDepartmentOrderdesc();
	}

	@Override
	public boolean checkDeptexit(String department_name) {
		boolean flag=false;
		Department department = departmentDao.checkDeptexit(department_name);
		if (department==null) {
			flag=true;
		}
		return flag;
	}

	@Override
	public Integer addDepartment(Department department) {
		return departmentDao.addDepartment(department);
	}

	@Override
	public String updateDeptByid(Department department) {
			Integer row = departmentDao.updateDeptByid(department);
			if (row>0) {
				return "yes";
			}
			else return "no";
	}

	@Override
	public String deleteDeptByid(Integer department_id) {
			Integer deptByid = departmentDao.deleteDeptByid(department_id);
			if (deptByid>0) {
				return "yes";
			}
			else return "no";
	}
}
