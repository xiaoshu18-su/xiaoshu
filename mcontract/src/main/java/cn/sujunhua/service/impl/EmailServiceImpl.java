package cn.sujunhua.service.impl;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.sujunhua.dao.EmailDao;
import cn.sujunhua.pojo.Email;
import cn.sujunhua.service.EmailService;

@Service
@Scope("prototype")
public class EmailServiceImpl implements EmailService {
	// tomcat管理的文件夹    路径读取设为/images
	private final String TOMCATFILE = "/images/";

	@Autowired
	private EmailDao emailDao;

	// 发送邮件
	public String sendemail(String email_send, String email_receive, String email_theme, 
			String email_content,MultipartFile email_file,HttpServletRequest request) {
		// 文件的原始名称
		String originalFilename = "";
		// 新的文件名
		String newfileName = "";
		// 判断所上传文件是否存在
		if (!email_file.isEmpty() && email_file.getSize() > 0) {
			// 获取上传文件的原始名称
			originalFilename = email_file.getOriginalFilename();
			// 获取后缀名  commons.io提供的工具类FilenameUtils
			String extension = FilenameUtils.getExtension(originalFilename);
			// UUID创建随机String 用于作为新的文件名
			String string = UUID.randomUUID().toString();

			// 新的文件名
			newfileName = string.replaceAll("-", "") + "." + extension;
			// 设置上传文件的保存地址目录
			String dirPath = request.getServletContext().getRealPath(TOMCATFILE);
			File filePath = new File(dirPath);
			// 如果目录不存在就创建目录
			if (!filePath.exists())
				filePath.mkdirs();
			try {
				email_file.transferTo(new File(dirPath + newfileName));
			} catch (Exception e) {
				e.printStackTrace();
				return "no";
			}
		}
		String dbPath = newfileName;
		// 文件的名称就是上传的原名称
		String email_namefile = originalFilename;
		Date email_datetime = new Date();
		Email email = new Email(null, email_send, email_receive, email_theme,
				email_content, email_namefile, TOMCATFILE+dbPath,email_datetime);
		Integer i = emailDao.sendemail(email);
			if (i > 0) {
				return "yes";
			} else
				return "no";
	}

	@Override
	public List<Email> selectAllemailByreceive(String email_receive) {
		// TODO Auto-generated method stub
		return emailDao.selectAllemailByreceive(email_receive);
	}

	@Override
	public Email selectemailByid(Integer email_id) {
		// TODO Auto-generated method stub
		return emailDao.selectemailByid(email_id);
	}

	@Override
	public String deleteemailByid(Integer email_id,HttpServletRequest request) {
			if (email_id != null) {
				Email email = emailDao.selectEfileByid(email_id);
				String emailFile = email.getEmail_file();
				if (emailFile != null) {
					if (!"".equals(emailFile)) {
						File file = new File(request.getServletContext().getRealPath(TOMCATFILE), emailFile);
						file.delete();
					}
					Integer i = emailDao.deleteemailByid(email_id);
					if (i > 0) {
						return "yes";
					} else {
						return "no";
					}
				}
				else {
					Integer i = emailDao.deleteemailByid(email_id);
					if (i > 0) {
						return "yes";
					} else {
						return "no";
					}
				}
				
			}
			else return "no";
	}

	@Override
	public Integer selectcountemail(String email_receive) {
		return emailDao.selectcountemail(email_receive);
	}

	@Override
	public Email selectNewEmail(String email_receive) {
		return emailDao.selectNewEmail(email_receive);
	}

}
