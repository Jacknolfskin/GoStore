package cn.zhx.common.web;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 请求工具类
 * @author lx
 *
 */
public class RequestUtils {
	
	
	//获取CSESSIONID
	public static String getCSESSIONID(HttpServletRequest request,HttpServletResponse response){
		//1：从Request中Cookies 数组
		Cookie[] cookies = request.getCookies();
		//2：从Cookie数据中遍历查找  并取CSESSIONID
		if(null != cookies && cookies.length >0){
			for (Cookie cookie : cookies) {
				if("CSESSIONID".equals(cookie.getName())){
					//有   直接返回
					return cookie.getValue();
				}
			}
		}
		//没有  创建一个新的CSESSIONID     
		String csessionid = UUID.randomUUID().toString().replaceAll("-", "");
		//并且放到Cookie中
		Cookie cookie = new Cookie("CSESSIONID",csessionid);
		//cookie 每次都带来 设置路径
		cookie.setPath("/");
		//设置 -1：关闭浏览器就要消失     0:立即消失   >0  秒  
		cookie.setMaxAge(-1);
		//再写回浏览器
		response.addCookie(cookie);
		//返回新的CSESSIONID
		return csessionid;
	}

}
