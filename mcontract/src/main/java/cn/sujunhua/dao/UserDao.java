package cn.sujunhua.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.sujunhua.pojo.User;

public interface UserDao {
   public User userLogin(@Param("username")String username,@Param("password")String password);
   
   public List<User> selectUser();
   
   public List<User> poiselectUser();
   
   public List<User> check_email_idcard_phone();
   
   public Integer addUser(User user);
   
   public User selectUserByid(Integer user_id);
   
   //根据id查出员工的类型
   public User selectUsertypeByid(Integer user_id);
   
   public Integer delectUserByid(Integer user_id);
   
   public Integer updateUserByid(User user);
   
   public User check_email_receive(@Param("user_email")String email_receive);
   
   public User checkUser(@Param("user_id")String contract_partb);
   
   public User findsendemail(@Param("user_email")String user_email);
   
   public Integer updateimg(User user);
   
   //查询员工的年龄
   public Integer finduserageByid(Integer user_id);
   
   //找回密码方法
   public User findUserPwd(@Param("username")String username,
		   @Param("user_secret_id")Integer user_secret_id,
		   @Param("user_secret_answer")String user_secret_answer);
   
   //用户修改密码
   public Integer updateUserPwd(@Param("user_id")Integer user_id,
		   @Param("pwd1")String pwd1,@Param("newpwd")String newpwd);
   
   public User selectUserEmail(@Param("user_email")String user_email);
   
   //发邮件的时候的提示框
   public List<User> selectEmailByemail(@Param("user_email")String user_email);
   
   public Integer updateUserDeptId(@Param("deptid1")Integer deptid1,@Param("deptid2")Integer deptid2);
   
   public Integer selectCountBydeptId(@Param("deptID")Integer deptID);
   
   //根据id查询该员工姓名
   public User selectUserNameByid(Integer user_id);
   
   //根据id查出邮箱
   public User findEmailByid(Integer user_id);
   
}
