package cn.sujunhua.web.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.code.kaptcha.impl.DefaultKaptcha;

import cn.sujunhua.pojo.User;
import cn.sujunhua.service.EmailService;
import cn.sujunhua.service.UserService;

@Controller
@Scope("request")
public class IndexController {
	// tomcat管理的文件夹    路径读取设为/images
	//private final String TOMCATFILE = "/images/";
	@Autowired
	private EmailService emailservice;
	@Autowired
	private UserService userService;
	@Autowired
	private DefaultKaptcha captchaProducer;

	@RequestMapping("/index/toIndex.do")
	public String toIndexjsp(HttpServletRequest request) {
		/*
		 * String userStr = CookieUtils.getCookieValue(request, "USER", true); User user
		 * = JSON.parseObject(userStr, User.class); request.setAttribute("COOKIE_USER",
		 * user);
		 */
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("COOKIE_USER");
		Integer countemail = emailservice.selectcountemail(user.getUser_email());

		// tomcat管理的文件夹 位置：G:/file 读取的路径设置未 /images/
		/*session.setAttribute("IMGPATH", "/images/");*/

		request.setAttribute("countemail", String.valueOf(countemail));
		/*
		 * //管理员 if (user.getUser_type()==2) { return "index"; } //普通用户 else if
		 * (user.getUser_type()==1) { return "index_user"; } //超级管理员 else if
		 * (user.getUser_type()==3) { return "index"; } else return "forward:/404.jsp";
		 */
		return "index";
	}

	/**
	 * 获取验证码
	 * 
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @throws Exception
	 */
	@RequestMapping("/index/kaptcha.do")
	public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws Exception {
		byte[] captchaChallengeAsJpeg = null;
		ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
		try {
			// 生产验证码字符串并保存到session中
			String createText = captchaProducer.createText();
			httpServletRequest.getSession().setAttribute("vrifyCode", createText);
			// 使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
			BufferedImage challenge = captchaProducer.createImage(createText);
			ImageIO.write(challenge, "jpg", jpegOutputStream);
		} catch (IllegalArgumentException e) {
			httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		// 定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
		captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
		httpServletResponse.setHeader("Cache-Control", "no-store");
		httpServletResponse.setHeader("Pragma", "no-cache");
		httpServletResponse.setDateHeader("Expires", 0);
		httpServletResponse.setContentType("image/jpeg");
		ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
		responseOutputStream.write(captchaChallengeAsJpeg);
		responseOutputStream.flush();
		responseOutputStream.close();
	}

	// 附件的下载方法
	@RequestMapping("/index/filedownload.do")
	public ResponseEntity<byte[]> filedownload(HttpServletRequest request, String myfile, String filename)
			throws Exception {
		String Path = request.getServletContext().getRealPath("");
		// 创建该文件对象
		File file = new File(Path + myfile);
		// 设置响应头
		HttpHeaders headers = new HttpHeaders();
		// 对文件名编码，防止中文乱码
		filename = getFilename(request, filename);
		// 通知浏览器以下载的方式打开文件
		headers.setContentDispositionFormData("attachment", filename);
		// 定义以流的形式下载返回文件数据
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		// 使用sping mvc框架的ResponseEntity对象封装返回下载数据
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
	}

	/**
	 * 根据不同的浏览器进行编码设置，返回编码后的文件名
	 * 
	 * @param request
	 * @param filename
	 * @return String
	 * @throws Exception
	 */
	public String getFilename(HttpServletRequest request, String filename) throws Exception {
		// IE不同版本User-Agent中出现的关键词
		String[] IEBrowserKeyWords = { "MSIE", "Trident", "Edge" };
		// 获取请求头代理信息
		String userAgent = request.getHeader("User-Agent");
		for (String keyWord : IEBrowserKeyWords) {
			if (userAgent.contains(keyWord)) {
				return URLEncoder.encode(filename, "UTF-8");
			}
		}
		// chrome等其他浏览器返回iso-8859-1
		return new String(filename.getBytes("UTF-8"), "ISO-8859-1");
	}

	@RequestMapping("/index/toIndex_v1.do")
	public String toindex_v1() {
		return "index_v1";
	}

	@RequestMapping("/index/to_img_update.do")
	public String to_img_update() {
		return "imgupdate";
	}

	@RequestMapping("/index/login.do")
	public String tologin() {
		return "login";
	}

	@RequestMapping("/index/chartcontract.do")
	public String tochart() {
		return "chartcontract";
	}

	@RequestMapping("/index/chartlogin.do")
	public String tochartandlist() {
		return "chartlogin";
	}
	
	//系统维护controller
	@RequestMapping("/index/maintenance.do")
	public String tomaintenance(HttpServletRequest request) {
		User COOKIE_USER = (User) request.getSession().getAttribute("COOKIE_USER");
		if (COOKIE_USER.getUser_type()!=3) {
			return "noJurisdiction";
		}
		List<User> list = userService.selectUser();
		List<User> users = new ArrayList<User>();
		if (list.size()>0) {
			for (User user : list) {
				if (!"超级管理员".equals(user.getUsertype().getUsertype_name())) {
					users.add(user);
				}
			}
		}
		request.setAttribute("users", users);
		return "maintenance";
	}
    
	//权限管理
	@RequestMapping("/index/privilege.do")
	public String privilege(HttpServletRequest request) {
		User COOKIE_USER = (User) request.getSession().getAttribute("COOKIE_USER");
		if (COOKIE_USER.getUser_type()!=3) {
			return "noJurisdiction";
		}
		List<User> list = userService.selectUser();
		List<User> users = new ArrayList<User>();
		if (list.size()>0) {
			for (User user : list) {
				if (!"超级管理员".equals(user.getUsertype().getUsertype_name())) {
					users.add(user);
				}
			}
		}
		request.setAttribute("users", users);
		return "privilegemanagement";
	}
}
