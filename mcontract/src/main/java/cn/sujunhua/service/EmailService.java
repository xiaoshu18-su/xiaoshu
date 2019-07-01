package cn.sujunhua.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import cn.sujunhua.pojo.Email;

public interface EmailService {
	//发邮件
   public String sendemail(String email_send,String email_receive,String email_theme,
			String email_content,MultipartFile email_file,HttpServletRequest request);
   
    //查看邮箱
   public List<Email> selectAllemailByreceive(String email_receive);
   
   public Email selectemailByid(Integer email_id);
   
   public String deleteemailByid(Integer email_id,HttpServletRequest request);
   
   public Integer selectcountemail(String email_receive);
   
   public Email selectNewEmail(@Param("email_receive")String email_receive);
}
