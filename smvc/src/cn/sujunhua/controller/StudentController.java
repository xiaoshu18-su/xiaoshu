package cn.sujunhua.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class StudentController extends MultiActionController {
	public void add(HttpServletRequest request, HttpServletResponse response) {
       System.out.println("add method");
	}
	public void list(HttpServletRequest request, HttpServletResponse response) {
	   System.out.println("list method");
	}
}
