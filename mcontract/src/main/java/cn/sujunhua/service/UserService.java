package cn.sujunhua.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import cn.sujunhua.pojo.User;
import cn.sujunhua.pojo.Usertype;

public interface UserService {
	public User userLogin(String username,String password);
	
	public List<User> selectUser();
	
	public boolean check_email(String email);
	
	public boolean check_id_card(String id_card);
	
	public boolean check_phone(String phone);
	
	public boolean checkUser(String contract_partb);
	
	public Integer addUser(User user);
	
	public User selectUserByid(Integer user_id);
	
	public Integer delectUserByid(Integer user_id);
	
	public Integer updateUserByid(User user);
	
	public boolean check_email_receive(String email_receive);
	
	public User findsendemail(String user_email);
	
	public String updateimg(HttpServletRequest request,Integer user_id, MultipartFile user_image,String dirPath);
	
	//查询员工的年龄
	public Integer finduserageByid(Integer user_id);
	
	//员工找回密码
	public User findUserPwd(String username,Integer user_secret_id,String user_secret_answer);
	
	//用户修改密码
	public String updateUserPwd(Integer user_id,String pwd1,String newpwd);
	
	public User selectUserEmail(String user_email);
	
	//发邮件的时候的提示框
	public List<User> selectEmailByemail(String user_email);
	
	public Integer selectCountBydeptId(Integer deptID);
	
	//导出所有员工信息
	public void poiUser(HttpServletResponse response);
	
	//根据id查出员工类型，并且通过用户类型查出他的类型
	public List<Usertype> selectUsertype(HttpServletRequest request);
}
