package cn.sujunhua.dao;

import java.util.List;

import cn.sujunhua.pojo.Template;

public interface TemplateDao {
   //查列表
   public List<Template> list();
   //添加
   public Integer add(Template template);
   //删除
   public Integer delete(Integer template_id);
   //根据id查
   public Template findByid(Integer template_id);
}
