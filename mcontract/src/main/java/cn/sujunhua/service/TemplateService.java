package cn.sujunhua.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import cn.sujunhua.pojo.Template;

public interface TemplateService {
   //查列表
   public List<Template> list();
   //添加
   public String add(MultipartFile template_file,HttpServletRequest request);
   //删除
   public String delete(Integer template_id,HttpServletRequest request);
   //根据id查
   public Template findByid(Integer template_id);
}
