package cn.sujunhua.common.utils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.sujunhua.pojo.User;
/**
 * 核心拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler)
			throws Exception {
		// 获取请求的URL
		String url = request.getRequestURI();
		// URL:除了登录请求外，其他的URL都进行拦截控制
		if (url.indexOf("/user/login.do") >= 0) {
			return true;
		}
		if (url.indexOf("/user/findpwd.do") >= 0) {
			return true;
		}
		if (url.indexOf("/user/findSecrets.do") >= 0) {
			return true;
		}
		if (url.indexOf("/index/kaptcha.do") >= 0) {
			return true;
		}
		// 获取Session
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("COOKIE_USER");
		// 判断Session中是否有用户数据，如果有，则返回true,继续向下执行
		if (user == null) {
			// 不符合条件的给出提示信息，并转发到登录页面
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			return false;
		}
		else {
			return true;
		}
	}
	@Override
	public void postHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}
	@Override
	public void afterCompletion(HttpServletRequest request, 
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
}
