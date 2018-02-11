package cn.zhx.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.zhx.common.web.RequestUtils;
import cn.zhx.core.service.user.SessionProvider;

/**
 * 拦截器
 * 上下文
 * 用户是否登陆。。    Controller之前 之后  页面渲染后拦截   处理器Controller  
 * @author lx
 *
 */
public class CustomInterceptor implements HandlerInterceptor{

	@Autowired
	private SessionProvider sessionProvider;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		//判断用户是否登陆  如果没有 重定向到登陆页面  如果有登陆  放行
		
		String username = sessionProvider.getAttributterForUsername(RequestUtils.getCSESSIONID(request, response));
		if(null == username){
			//重定向到登陆页面
			response.sendRedirect("http://localhost:8082/login.aspx?returnUrl=" 
			+ request.getParameter("returnUrl"));
			
			//不放行
			return false;
		}
		//放行
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
