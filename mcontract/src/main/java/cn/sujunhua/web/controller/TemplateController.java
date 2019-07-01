package cn.sujunhua.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.sujunhua.pojo.Template;
import cn.sujunhua.pojo.User;
import cn.sujunhua.service.TemplateService;

@Controller
@Scope("request")
public class TemplateController {
   @Autowired
   private TemplateService templateService;
   
   @RequestMapping("/template/template.do")
   public String template(HttpServletRequest request) {
	   User user = (User) request.getSession().getAttribute("COOKIE_USER");
	   if (user.getUser_type()==1) {
		   return "noJurisdiction";
	   }
	   return "template";
   }
   
   @RequestMapping("/template/list.do")
   @ResponseBody
   public List<Template> querylist(){
	   return templateService.list();
   }
   
	@RequestMapping("/template/add.do")
	@ResponseBody
	public String add(@RequestParam("template_file") MultipartFile template_file, 
			HttpServletRequest request) {
		try {
          return templateService.add(template_file, request);
		} catch (Exception e) {
			e.printStackTrace();
			return "no";
		}
	}
	
	@RequestMapping("/template/delete.do")
	@ResponseBody
	public String delete(Integer template_id,HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("COOKIE_USER");
	    if (user.getUser_type()==1) {
		    return "noJurisdiction";
	    }
	    return templateService.delete(template_id,request);
	}
}
