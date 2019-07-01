package cn.sujunhua.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sujunhua.common.utils.CountNumer;
import cn.sujunhua.pojo.Count;
import cn.sujunhua.pojo.User;
import cn.sujunhua.service.CountService;

@Controller
@Scope("request")
public class CountController {
	@Autowired
	private CountService countService;
	
	@RequestMapping("/count/byday.do")
	@ResponseBody
	public List<CountNumer> countByday(Integer day){
		try {
			return countService.selectCountNumByDay(day);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping("/count/bymonth.do")
	@ResponseBody
	public List<CountNumer> countByMonth(Integer month){
		try {
			return countService.selectCountNumByMonth(month);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping("/count/list.do")
	public String selectAllCount(HttpServletRequest request){
		List<Count> list = countService.selectCountUser();
		request.setAttribute("list", list);
		return "chartlogin";
	}
	
	//保留7天或30天的记录
	@RequestMapping("/count/deleteCountByDay.do")
	@ResponseBody
	public String deleteCountByDay(HttpServletRequest request,Integer day) {
		User user = (User) request.getSession().getAttribute("COOKIE_USER");
		if (user.getUser_type()!=3) {
			return "noJurisdiction";
		}
		try {
			return countService.deleteCountByDay(day);
		} catch (Exception e) {
			e.printStackTrace();
			return "no";
		}
	}
	
	//清除所有登陆记录
	@RequestMapping("/count/deleteAllCount.do")
	@ResponseBody
	public String deleteAllCount(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("COOKIE_USER");
		if (user.getUser_type()!=3) {
			return "noJurisdiction";
		}
		try {
			return countService.deleteAllCount();
		} catch (Exception e) {
			e.printStackTrace();
			return "no";
		}
	}
}
