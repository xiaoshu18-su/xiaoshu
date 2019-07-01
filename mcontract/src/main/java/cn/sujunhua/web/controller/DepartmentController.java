package cn.sujunhua.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sujunhua.common.utils.CountAndDeptUtil;
import cn.sujunhua.pojo.Department;
import cn.sujunhua.pojo.User;
import cn.sujunhua.service.DepartmentService;
import cn.sujunhua.service.UserService;

@Controller
@Scope("request")
public class DepartmentController {
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private UserService UserService;
	
	@RequestMapping("/department/list.do")
	public String selectDeptList(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("COOKIE_USER");
		if (user.getUser_type()==1) {
			return "noJurisdiction";
		}
		List<Department> list = departmentService.selectDepartmentOrderdesc();
		request.setAttribute("list", list);
		return "deptlist";
	}
	
	@RequestMapping("/department/check.do")
	@ResponseBody
	public boolean checkDeptexit(String department_name) {
		return departmentService.checkDeptexit(department_name);
	}
	
	@RequestMapping(value="/department/add.do",method=RequestMethod.POST)
	@ResponseBody
	public String addDepartment(HttpServletRequest request,Department department) {
		User user = (User) request.getSession().getAttribute("COOKIE_USER");
		if (user.getUser_type()==1) {
			return "noJurisdiction";
		}
		try {
			Integer row = departmentService.addDepartment(department);
			if (row>0) {
				return "yes";
			}else return "no";
		} catch (Exception e) {
			e.printStackTrace();
			return "no";
		}
	}
	
	@RequestMapping(value="/department/update.do",method=RequestMethod.POST)
	@ResponseBody
	public String updateDeptByid(HttpServletRequest request,
			Integer update_dept_id,String update_dept_name) {
		User user = (User) request.getSession().getAttribute("COOKIE_USER");
		if (user.getUser_type()==1) {
			return "noJurisdiction";
		}
		Department department = new Department();
		department.setDepartment_id(update_dept_id);
		department.setDepartment_name(update_dept_name);
		try {
			return departmentService.updateDeptByid(department);
		} catch (Exception e) {
			e.printStackTrace();
			return "no";
		}
	}
	
	@RequestMapping("/department/delete.do")
	@ResponseBody
	public String deleteDeptByid(HttpServletRequest request,Integer department_id) {
		User user = (User) request.getSession().getAttribute("COOKIE_USER");
		if (user.getUser_type()==1) {
			return "noJurisdiction";
		}
		try {
			return departmentService.deleteDeptByid(department_id);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	@RequestMapping("/department/find.do")
	@ResponseBody
	public CountAndDeptUtil findDepartmentByid(HttpServletRequest request,Integer department_id) {
		User user = (User) request.getSession().getAttribute("COOKIE_USER");
		if (user.getUser_type()==1) {
			return null;
		}
		Integer row = UserService.selectCountBydeptId(department_id);
		Department department = departmentService.findDepartmentByid(department_id);
		CountAndDeptUtil countAndDeptUtil = new CountAndDeptUtil(row,department);
		return countAndDeptUtil;
	}
}
