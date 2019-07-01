package cn.sujunhua.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.sujunhua.pojo.Email;

public interface EmailDao {
	//发邮件
   public Integer sendemail(Email email);
   
   //查看所有邮箱
   public List<Email> selectAllemailByreceive(@Param("email_receive")String email_receive);
   
   //查看邮件
   public Email selectemailByid(@Param("email_id")Integer email_id);
   
   
   public Integer deleteemailByid(@Param("email_id")Integer email_id);
   
   //查找有多少邮件
   public Integer selectcountemail(@Param("email_receive")String email_receive);
   
   
   //查找最新的一条信息
   public Email selectNewEmail(@Param("email_receive")String email_receive);
   
   //根据id查找邮件的文件名称
   public Email selectEfileByid(@Param("email_id")Integer email_id);
   
}
