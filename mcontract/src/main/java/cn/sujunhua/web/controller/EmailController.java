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

import cn.sujunhua.pojo.Email;
import cn.sujunhua.pojo.User;
import cn.sujunhua.service.EmailService;
import cn.sujunhua.service.UserService;

@Controller
@Scope("request")
public class EmailController {
	@Autowired
	private EmailService emailservice;
	@Autowired
	private UserService userservice;
   
	@RequestMapping("/email/to_compose.do")
	public String tomail_compose(HttpServletRequest request) {
		request.setAttribute("compose", "compose");
		return "mail_compose";
	}
	
	@RequestMapping("/email/replyemail.do")
	public String replyemail(HttpServletRequest request) {
		String email = request.getParameter("email");
		request.setAttribute("replyemail", email);
		return "mail_compose";
	}
	
	//发送邮件
	@RequestMapping(value="/email/compose.do",method = RequestMethod.POST)
	@ResponseBody
	public String email_compose(@RequestParam("email_receive")String email_receive,
			@RequestParam("email_theme")String email_theme,
			@RequestParam("email_content")String email_content,
			@RequestParam("email_file")MultipartFile email_file,
			HttpServletRequest request)throws IOException{
		    //获得发送者的邮箱号码
		    HttpSession session=request.getSession();
			//String userStr = CookieUtils.getCookieValue(request, "USER", true);
		    User user = (User) session.getAttribute("COOKIE_USER");
			//String sendemail=JSON.parseObject(userStr, User.class).getUser_email();
		    String sendemail=user.getUser_email();
			try {
				return emailservice.sendemail(sendemail, email_receive, email_theme, email_content,email_file,request);
			}catch(Exception e) {
				e.printStackTrace();
				return "no";
			}
	}
	
	@RequestMapping("/email/emailbox.do")
	public String selectAllemailByreceive(HttpServletRequest request,HttpServletResponse response) {
		//获得当前用户的邮箱号码
		HttpSession session=request.getSession();
		/*
		 * String userStr = CookieUtils.getCookieValue(request, "USER", true); 
		 * String sendemail=JSON.parseObject(userStr, User.class).getUser_email();
		 */
		User user = (User) session.getAttribute("COOKIE_USER");
		String sendemail=user.getUser_email();
		List<Email> emails=emailservice.selectAllemailByreceive(sendemail);
		request.setAttribute("emails",emails );
		return "emailbox";
	}
	
	@RequestMapping("/email/findemailByid.do")
	public String selectemailByid(HttpServletRequest request,HttpServletResponse response) {
		Integer email_id = Integer.valueOf(request.getParameter("email_id"));
		String user_email = request.getParameter("user_email");
		Email email=emailservice.selectemailByid(email_id);
		User user=userservice.findsendemail(user_email);
		request.setAttribute("email", email);
		request.setAttribute("user", user);
		return "email_detail";
	}
	
	@RequestMapping("/email/deleteemail.do")
	@ResponseBody
	public String deleteemailByid(Integer email_id,HttpServletRequest request) {
		try {
			return emailservice.deleteemailByid(email_id,request);
		} catch (Exception e) {
			e.printStackTrace();
			return "no";
		}
	}

    /**
     * 用户输入邮箱时给用户的提示框
     */
	@RequestMapping("/email/selectEmailByemail.do")
	@ResponseBody
	public List<User> selectEmailByemail(String user_email) {
		try {
			List<User> list = userservice.selectEmailByemail(user_email);
			if(!list.isEmpty()) {
				return list;
			}else
				return null;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
