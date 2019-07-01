package cn.sujunhua.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.sujunhua.dao.CountDao;
import cn.sujunhua.dao.UserDao;
import cn.sujunhua.dao.UsertypeDao;
import cn.sujunhua.pojo.Count;
import cn.sujunhua.pojo.User;
import cn.sujunhua.pojo.Usertype;
import cn.sujunhua.service.UserService;

@Service
@Scope("prototype")
public class UserServiceImpl implements UserService {
	// tomcat管理的文件夹    路径读取设为/images
	private final String TOMCATFILE = "/images/";
	
	//系统默认图片
	private final String DEFAULT_IMG="default.jpg";
    
	@Autowired
	private UserDao userdao;
	
	@Autowired
	private CountDao countDao;
	
	@Autowired
	private UsertypeDao usertypeDao;
	
	@Override
	public User userLogin(String username,String password) {
		// TODO Auto-generated method stub
		//User userLogin = ;
		//System.out.println(userLogin);
		User login = userdao.userLogin(username,password);
		if (login != null) {
			Integer user_id = login.getUser_id();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			String stringdate = format.format(date);
			Count count = new Count(null,user_id,stringdate,null);
			countDao.addCountRecord(count);
		}
		return login;
	}

	/**
	 * 查出
	 */
	@Override
	public List<User> selectUser() {
		return userdao.selectUser();
	}

	/*
	 * public String check_email(String email) { boolean flag=true; List<User>
	 * user_emails=userdao.check_email_idcard_phone(); for (User user : user_emails)
	 * { if(user.getUser_email().equals(email)) { flag=false; break; } } if(flag) {
	 * return "yes"; }else { return "no"; } }
	 */
	
	public boolean check_email(String email) {
		boolean flag=true;
		List<User> user_emails=userdao.check_email_idcard_phone();
		for (User user : user_emails) {
			if(user.getUser_email().equals(email)) {
				flag=false;
				break;
			}
		}
		return flag;
	}

	@Override
	public boolean check_id_card(String id_card) {
		boolean flag=true;
		List<User> user_id_cards=userdao.check_email_idcard_phone();
		for (User user : user_id_cards) {
			if(user.getUser_id_card().equals(id_card)) {
				flag=false;
				break;
			}
		}
	    return flag;
	}

	@Override
	public boolean check_phone(String phone) {
		boolean flag=true;
		List<User> user_phones=userdao.check_email_idcard_phone();
		for (User user : user_phones) {
			if(user.getUser_phone().equals(phone)) {
				flag=false;
				break;
			}
		}
		return flag;
	}

	@Override
	public Integer addUser(User user) {
		return userdao.addUser(user);
	}

	@Override
	public User selectUserByid(Integer user_id) {
		return userdao.selectUserByid(user_id);
	}

	@Override
	public Integer delectUserByid(Integer user_id) {
		return userdao.delectUserByid(user_id);
	}

	@Override
	public Integer updateUserByid(User user) {
		return userdao.updateUserByid(user);
	}

	@Override
	public boolean check_email_receive(String email_receive) {
		User user = userdao.check_email_receive(email_receive);
		if(user==null) {
			return false;
		}
		else return true;
	}

	@Override
	public User findsendemail(String user_email) {
		return userdao.findsendemail(user_email);
	}
    
	//用户修改头像
	@Override
	public String updateimg(HttpServletRequest request,Integer user_id, 
			MultipartFile user_image,String dirPath) {
		HttpSession session=request.getSession();
		// 文件的原始名称
		String originalFilename = "";
		// 新的文件名
		String newfileName = "";
		// 获取上传文件的原始名称
		originalFilename = user_image.getOriginalFilename();
		// 获取后缀名
		String extension = FilenameUtils.getExtension(originalFilename);
		// UUID创建随机String 用于作为新的文件名
		String string = UUID.randomUUID().toString();
		// 新的文件名
		newfileName = string.replaceAll("-", "") + "." + extension;
		// 设置上传文件的保存地址目录
		File filePath = new File(dirPath);
		// 如果目录不存在就创建目录
		if (!filePath.exists())
			filePath.mkdirs();
		String imgpath=(String) session.getAttribute("USER_IMG");
		if(imgpath!=null && !"".equals(imgpath)) {
			//判断是不是默认的图片   如果不是 ，则删除   是就不删除
			if(!DEFAULT_IMG.equals(imgpath)) {
			File file = new File(request.getServletContext().getRealPath(""),imgpath);
			file.delete(); 
			}
		}
		try {
			user_image.transferTo(new File(dirPath + newfileName));
		} catch (Exception e) {
			e.printStackTrace();
			return "no";
		}
		session.setAttribute("USER_IMG", TOMCATFILE+newfileName);
		User user=new User();
		user.setUser_image(TOMCATFILE+newfileName);
		user.setUser_id(user_id);
		Integer i = userdao.updateimg(user);
        if(i>0) {
        	return "yes";
        }
        else 
        	return "no";
		
	}
    
	//查询员工的年龄
	@Override
	public Integer finduserageByid(Integer user_id) {
		return userdao.finduserageByid(user_id);
	}

	@Override
	public User findUserPwd(String username,Integer user_secret_id,String user_secret_answer) {
		return userdao.findUserPwd(username,user_secret_id,user_secret_answer);
	}

	@Override
	public String updateUserPwd(Integer user_id, String pwd1, String newpwd) {
		Integer row = userdao.updateUserPwd(user_id, pwd1, newpwd);
		if(row>0) {
			return "yes";
		}
		else
			return "no";
	}

	@Override
	public User selectUserEmail(String user_email) {
		return userdao.selectUserEmail(user_email);
	}

	@Override
	public List<User> selectEmailByemail(String user_email) {
		return userdao.selectEmailByemail(user_email);
	}

	@Override
	public Integer selectCountBydeptId(Integer deptID) {
		return userdao.selectCountBydeptId(deptID);
	}

	@Override
	public boolean checkUser(String contract_partb) {
		User user = userdao.checkUser(contract_partb);
		if (user==null) {
			return false;
		}else return true;
	}

	@Override
	public void poiUser(HttpServletResponse response) {
		@SuppressWarnings("resource")
		HSSFWorkbook book = new HSSFWorkbook();
		HSSFSheet sheet = book.createSheet();
		HSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue("员工编号");   //设置表头
		sheet.setColumnWidth(0, "xxxx".getBytes().length*256*2);   //设置列宽
		row.createCell(1).setCellValue("员工姓名");
		sheet.setColumnWidth(1, "xxxx".getBytes().length*256*2);
		row.createCell(2).setCellValue("性别");
		sheet.setColumnWidth(2, "xxxx".getBytes().length*256*2);
		row.createCell(3).setCellValue("地址");
		sheet.setColumnWidth(3, "xxxx".getBytes().length*256*2*4);
		row.createCell(4).setCellValue("员工邮箱");
		sheet.setColumnWidth(4, "xxxx".getBytes().length*256*2*4);
		row.createCell(5).setCellValue("身份证号码");
		sheet.setColumnWidth(5, "xxxx".getBytes().length*256*2*4);
		row.createCell(6).setCellValue("手机号码");
		sheet.setColumnWidth(6, "xxxx".getBytes().length*256*2*2);
		row.createCell(7).setCellValue("用户角色");
		sheet.setColumnWidth(7, "xxxx".getBytes().length*256*2*2);
		row.createCell(8).setCellValue("所在部门	");
		sheet.setColumnWidth(8, "xxxx".getBytes().length*256*2);
		row.createCell(9).setCellValue("qq号码");
		sheet.setColumnWidth(9, "xxxx".getBytes().length*256*4);
		row.createCell(10).setCellValue("学历");
		sheet.setColumnWidth(10, "xxxx".getBytes().length*256*2);
		row.createCell(11).setCellValue("政治面貌");
		sheet.setColumnWidth(11, "xxxx".getBytes().length*256*2);
		row.createCell(12).setCellValue("民族");
		sheet.setColumnWidth(12, "xxxx".getBytes().length*256*2);
		row.createCell(13).setCellValue("生日");
		sheet.setColumnWidth(13, "xxxx".getBytes().length*256*2*3);
		row.createCell(14).setCellValue("入职日期");
		sheet.setColumnWidth(14, "xxxx".getBytes().length*256*2*3);
		row.createCell(15).setCellValue("账号状态");
		sheet.setColumnWidth(15, "xxxx".getBytes().length*256*2);
		
		List<User> users = userdao.poiselectUser();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		int i=1;
		for (User user : users) {
			HSSFRow rowi = sheet.createRow(i);
			rowi.createCell(0).setCellValue(user.getUser_id());
			rowi.createCell(1).setCellValue(user.getUser_name());
			rowi.createCell(2).setCellValue(user.getUser_sex());
			rowi.createCell(3).setCellValue(user.getUser_address());
			rowi.createCell(4).setCellValue(user.getUser_email());
			rowi.createCell(5).setCellValue(user.getUser_id_card());
			rowi.createCell(6).setCellValue(user.getUser_phone());
			rowi.createCell(7).setCellValue(user.getUsertype().getUsertype_name());
			rowi.createCell(8).setCellValue(user.getDepartment().getDepartment_name());
			rowi.createCell(9).setCellValue(user.getUser_qq_no());
			rowi.createCell(10).setCellValue(user.getUser_education());
			rowi.createCell(11).setCellValue(user.getUser_political());
			rowi.createCell(12).setCellValue(user.getUser_nation());
			rowi.createCell(13).setCellValue(format.format(user.getUser_birthday()));
			rowi.createCell(14).setCellValue(format.format(user.getUser_entry()));
			if (user.getUser_status()==0) {
				rowi.createCell(15).setCellValue("正常");
			}
			else {
				rowi.createCell(15).setCellValue("停用");
			}
			i++;
		}
		//设置响应头,响应的内容是为附件形式
		try {
			response.addHeader("Content-Disposition",
					"attachment;filename=" + new String("员工信息.xlsx".getBytes(), "ISO-8859-1"));
			book.write(response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Usertype> selectUsertype(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User cookie_user = (User) session.getAttribute("COOKIE_USER");
		Usertype usertype = usertypeDao.selectNameByid(cookie_user.getUser_type());
		if ("管理员".equals(usertype.getUsertype_name())) {
			return usertypeDao.selectUsertype("用户");
		}
		else return usertypeDao.selectUsertype(null);
	}

}
