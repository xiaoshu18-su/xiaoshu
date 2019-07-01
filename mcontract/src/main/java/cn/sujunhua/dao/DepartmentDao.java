package cn.sujunhua.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.sujunhua.pojo.Department;

public interface DepartmentDao {
   public List<Department> selectDepartment();
   
   public Department findDepartmentByid(@Param("department_id")Integer department_id);
   
   //根据时间后序查找部门
   public List<Department> selectDepartmentOrderdesc();
   
   public Department checkDeptexit(@Param("department_name")String department_name);
   
   public Integer addDepartment(Department department);
   
   public Integer updateDeptByid(Department department);
   
   public Integer deleteDeptByid(@Param("department_id")Integer department_id);
   
   public Integer selectDeptCount();
}
