package cn.sujunhua.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.sujunhua.common.utils.LoginResult;
import cn.sujunhua.pojo.Department;
import cn.sujunhua.pojo.Secret;
import cn.sujunhua.pojo.User;
import cn.sujunhua.pojo.Usertype;
import cn.sujunhua.service.DepartmentService;
import cn.sujunhua.service.SecretService;
import cn.sujunhua.service.UserService;
import cn.sujunhua.service.UsertypeService;

@Controller
@Scope("request")
public class UserController {
	// tomcat管理的文件夹    路径读取设为/images
	private final String TOMCATFILE = "/images/";
	@Autowired
	private UserService userService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private SecretService secretService;
	@Autowired
	private UsertypeService usertypeService;
	
	@RequestMapping("/user/login.do")
	@ResponseBody
	public LoginResult userlogin(String username,String password,String valideCode,
			HttpServletRequest request) {
		LoginResult loginResult = new LoginResult();
		HttpSession session = request.getSession();
		String vrifyCode = (String) session.getAttribute("vrifyCode");
		//验证码不正确，设置为no
		if (!valideCode.equals(vrifyCode)) {
			loginResult.setState("no");
			loginResult.setMsString("验证码不正确，请检查");
			return loginResult;
		}
		else {
			try {
			User userLogin = userService.userLogin(username,password);
			if(userLogin!=null) {
				/*
				 * String userStr = JSON.toJSONString(userLogin); CookieUtils.setCookie(request,
				 * response, "USER", userStr, 60*60*24, true);
				 */
				//session24小时有效吧
				session.setMaxInactiveInterval(60*60*24);
				String USER_IMG=userLogin.getUser_image();
				session.setAttribute("COOKIE_USER", userLogin);
				session.setAttribute("USER_IMG", USER_IMG);
				//表示登录成功
				loginResult.setState("yes");
			}else {
				loginResult.setState("no");
				loginResult.setMsString("账号密码错误或账号被停用");
			}
			}catch(Exception e) {
				e.printStackTrace();
			}
			return loginResult;
		}
 	}
	
	@RequestMapping("/user/logout.do")
	@ResponseBody
	public String userlogout(HttpServletRequest request) {
		/* CookieUtils.deleteCookie(request, response, "USER"); */
		HttpSession session = request.getSession();
		session.invalidate();
		return "ok";
	}
	
	@RequestMapping("/user/logout2.do")
	public String userlogout2(HttpServletRequest request) {
		/* CookieUtils.deleteCookie(request, response, "USER"); */
		HttpSession session = request.getSession();
		session.invalidate();
		return "login";
	}
	
	@RequestMapping("/user/tologin.do")
	public String tologin(HttpServletRequest request){
		return "redirect:/login.jsp";
	}
	
	//查看用户列表
	@RequestMapping("/user/selectuser.do")
	public String selectUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("COOKIE_USER");
		request.setAttribute("user_type", user.getUser_type());
		List<User> list=userService.selectUser();
		request.setAttribute("list", list);
		return "userlist";
	}
	
	@RequestMapping("/user/toadduser.do")
	public String toadduser(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("COOKIE_USER");
		if (user.getUser_type()==1) {
			return "noJurisdiction";
		}
		List<Usertype> usertypes = userService.selectUsertype(request);
		List<Department> departlist=departmentService.selectDepartment();
		request.setAttribute("usertypes", usertypes);
		request.setAttribute("departlist", departlist);
		return "adduser";
	}
	
	//验证邮箱
	@RequestMapping("/user/checkEmail.do")
	@ResponseBody
	public boolean checkEmail(String user_email) {
		return userService.check_email(user_email);
	}
	
	//验证身份证号
	@RequestMapping("/user/checkId_card.do")
	@ResponseBody
	public boolean checkID_card(String user_id_card) {
		return userService.check_id_card(user_id_card);
	}
	
	//验证手机号
	@RequestMapping("/user/checkPhone.do")
	@ResponseBody
	public boolean checkPhone(String user_phone) {
		return userService.check_phone(user_phone);
	}
	
	
	//验证邮箱是否存在
	@RequestMapping("/user/check_email_receive.do")
	@ResponseBody
	public boolean check_email_receive(String email_receive) {
		return userService.check_email_receive(email_receive);
	}
	
	@RequestMapping(value="/user/adduser.do",method=RequestMethod.POST)
	@ResponseBody
	public String addUser(HttpServletRequest request,User user) {
		User COOKIE_USER = (User) request.getSession().getAttribute("COOKIE_USER");
		if (COOKIE_USER.getUser_type()==1) {
			return "noJurisdiction";
		}
		try {
		Integer flag = userService.addUser(user);
		if(flag>0) {
			return "yes";
		}
		else
		return "no";
		}catch(Exception e) {
			return "no";
		}
	}
	
	@RequestMapping(value="/user/touser_detailed.do")
	public String touser_detailed(HttpServletRequest request,Integer id) {
		HttpSession session = request.getSession();
		User COOKIE_USER = (User) session.getAttribute("COOKIE_USER");
		User user = userService.selectUserByid(id);
		Integer age = userService.finduserageByid(id);
		request.setAttribute("user", user);
		request.setAttribute("user_age", age);
		if (COOKIE_USER.getUser_id()==id) {
			return "user_detailed";
		}else {
			if (COOKIE_USER.getUser_type()>user.getUser_type()) {
				return "user_detailed";
			}
			else {
				return "noJurisdiction";
			}
		}
	}
	
	@RequestMapping("/user/deleteuserbyid.do")
	@ResponseBody
	public String deleteuserByid(HttpServletRequest request,Integer id) {
		try {
			HttpSession session = request.getSession();
			User COOKIE_USER = (User) session.getAttribute("COOKIE_USER");
			User user = userService.selectUserByid(id);
			if (COOKIE_USER.getUser_id()==id) {
				return "noJurisdiction";
			}else {
				if (COOKIE_USER.getUser_type()>user.getUser_type()) {
					Integer i = userService.delectUserByid(id);
					if(i>0) {
						return "yes";
					}
					else {
						return "no";
					}
				}
				else {
					return "noJurisdiction";
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			return "no";
		}
	}
	
	//ajax判断用户权限
	@RequestMapping("/user/judgeUser.do")
	@ResponseBody
    public String judgeUser(HttpServletRequest request,Integer id) {
		HttpSession session = request.getSession();
		User cookie_user = (User) session.getAttribute("COOKIE_USER");
		User user = userService.selectUserByid(id);
		if (id==cookie_user.getUser_id()) {
			return "yes";
		}
		else {
			if (user.getUser_type()>=cookie_user.getUser_type()) {
				return "noJurisdiction";
			}
			else return "yes";
		}
    }
	
	@RequestMapping("/user/toupdateuser.do")
	public String toupdateuser(HttpServletRequest request,Integer id) {
		HttpSession session = request.getSession();
		User COOKIE_USER = (User) session.getAttribute("COOKIE_USER");
		User user = userService.selectUserByid(id);
		List<Department> departlist=departmentService.selectDepartment();
		request.setAttribute("user", user);
		request.setAttribute("departlist", departlist);
		if (COOKIE_USER.getUser_id()==id) {
			return "userupdate";
		}else {
			if (COOKIE_USER.getUser_type()>user.getUser_type()) {
				return "userupdate";
			}
			else {
				return "noJurisdiction";
			}
		}
	}
	
	@RequestMapping("/user/toupubu.do")
	public String toupdateuserbyuser(HttpServletRequest request) {
		User user_session = (User) request.getSession().getAttribute("COOKIE_USER");
		User user = userService.selectUserByid(user_session.getUser_id());
		List<Secret> list = secretService.selectSecret();
		request.setAttribute("secrets", list);
		request.setAttribute("user", user);
		return "userupdatebyuser";
	}
	
	@RequestMapping(value="/user/updateuserbyid.do",method=RequestMethod.POST)
	@ResponseBody
	public String updateuserbyid(User user) {
		try {
		    Integer flag = userService.updateUserByid(user);
		if(flag>0) {
			return "yes";
		}
		else {
		    return "no";
		}
		}catch (Exception e) {
			e.printStackTrace();
			return "no";
		}
	}
	
	@RequestMapping(value="/user/updateimg.do",method = RequestMethod.POST)
	@ResponseBody
	public String updateimg(@RequestParam("user_image")MultipartFile user_image,
			HttpServletRequest request) throws IOException{
		 HttpSession session=request.getSession();
		 User user = (User) session.getAttribute("COOKIE_USER");
		 Integer user_id = user.getUser_id();
		 String dirPath=request.getServletContext().getRealPath(TOMCATFILE);
		 /*String dirPath=TOMCATFILE;*/
		 try {
			 return userService.updateimg(request,user_id, user_image,dirPath);
		 }catch(Exception e) {
			e.printStackTrace();
			return "no";
		 }
	}
	
	@RequestMapping("/user/update_to_index.do")
	public String update_to_index() {
		return "index";
	}
	
	//需要在拦截器放行
	@RequestMapping("/user/findSecrets.do")
	@ResponseBody
	public List<Secret> findSecrets(){
		return secretService.selectSecret();
	}
	
	//需要在拦截器放行
	//找回密码
	@RequestMapping("/user/findpwd.do")
	@ResponseBody
	public User findUserpwd(@RequestParam("username1")String username,
			@RequestParam("user_secret_id")Integer user_secret_id,
			@RequestParam("user_secret_answer")String user_secret_answer) {
		User findUserPwd = userService.findUserPwd(username,user_secret_id,user_secret_answer);
		return findUserPwd;
	}
	
    //用户修改密码
	@RequestMapping("/user/updatePwd.do")
    @ResponseBody
	public String updateUserPwd(HttpServletRequest request,
			@RequestParam("password1")String password1,@RequestParam("password3")String password3) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("COOKIE_USER");
		try {
			return userService.updateUserPwd(user.getUser_id(), password1, password3);
		}catch(Exception e) {
			return "no";
		}
	}
	
	//检查员工是否存在
	@RequestMapping("/user/checkUser.do")
    @ResponseBody
	public boolean checkUser(String contract_partb) {
		return userService.checkUser(contract_partb);
	}
	
	//导出所有员工信息
	@RequestMapping("/user/poiUser.do")
	public void poiUser(HttpServletRequest request,HttpServletResponse response) {
		User COOKIE_USER = (User) request.getSession().getAttribute("COOKIE_USER");
		if (COOKIE_USER.getUser_type()<=1) {
			return ;
		}
		try {
			userService.poiUser(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//修改用户账号状态方法
	@RequestMapping("/user/updatestatus.do")
	@ResponseBody
	public String updateUserStatus(Integer user_id,Integer user_status,
			HttpServletRequest request) {
		User COOKIE_USER = (User) request.getSession().getAttribute("COOKIE_USER");
		if (COOKIE_USER.getUser_type()!=3) {
			return "noJurisdiction";
		}
		User user = new User();
		user.setUser_id(user_id);
		user.setUser_status(user_status);
		Integer row = userService.updateUserByid(user);
		if (row>0) {
			return "yes";
		}
		else return "no";
	}
	
	//设为管理员方法
	@RequestMapping("/user/setadmin.do")
	@ResponseBody
	public String setadmin(Integer user_id,HttpServletRequest request) {
		User COOKIE_USER = (User) request.getSession().getAttribute("COOKIE_USER");
		if (COOKIE_USER.getUser_type()!=3) {
			return "noJurisdiction";
		}
		User user = new User();
		user.setUser_id(user_id);
		Usertype usertype = usertypeService.selectUserByName("管理员");
		user.setUser_type(usertype.getUsertype_id());
		Integer row = userService.updateUserByid(user);
		if (row>0) {
			return "yes";
		}
		else return "no";
	}
	
	//设为普通用户方法
	@RequestMapping("/user/setuser.do")
	@ResponseBody
	public String setuser(Integer user_id,HttpServletRequest request) {
		User COOKIE_USER = (User) request.getSession().getAttribute("COOKIE_USER");
		if (COOKIE_USER.getUser_type()!=3) {
			return "noJurisdiction";
		}
		User user = new User();
		user.setUser_id(user_id);
		Usertype usertype = usertypeService.selectUserByName("普通用户");
		user.setUser_type(usertype.getUsertype_id());
		Integer row = userService.updateUserByid(user);
		if (row>0) {
			return "yes";
		}
		else return "no";
	}
}