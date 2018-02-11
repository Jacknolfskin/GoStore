package cn.zhx.core.controller;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zhx.common.web.RequestUtils;
import cn.zhx.core.bean.user.Buyer;
import cn.zhx.core.service.user.BuyerService;
import cn.zhx.core.service.user.SessionProvider;

/**
 * 单点登陆系统
 * @author lx
 *
 */
@Controller
public class LoginController {

	//去登陆页面
	@RequestMapping(value = "/login.aspx",method=RequestMethod.GET)
	public String login(){
		return "login";
	}
	
	@Autowired
	private BuyerService buyerService;
	@Autowired
	private SessionProvider sessionProvider;
	//提交登陆表单
	@RequestMapping(value = "/login.aspx",method=RequestMethod.POST)
	public String login(String username,String password,String returnUrl,Model model
			,HttpServletResponse response,HttpServletRequest request){
		
		//1:判断用户名不能为空
		if(null != username){
			//2:判断密码不能为空
			if(null != password){
				//3:用户名必须正确    用此用户名去数据库 查询  null 用户名不正确
				Buyer buyer = buyerService.selectBuyerByusername(username);
				if(null != buyer){
					//4：密码必须正确
					if(encodePassword(password).equals(buyer.getPassword())){
						//5:设置用户到Session  csessionid 到Redis中了
						sessionProvider.setAttributterForUsername(
								RequestUtils.getCSESSIONID(request, response), buyer.getUsername());
						//6:回跳之前访问页面
						if(null != returnUrl){
							return "redirect:" + returnUrl;
						}else{
							return "redirect:http:localhost:8081/";
						}
					}else{
						model.addAttribute("error", "密码必须正确");
					}
					
				}else{
					model.addAttribute("error", "用户名必须正确 ");
				}
				
			}else{
				model.addAttribute("error", "密码不能为空");
			}
			
		}else{
			model.addAttribute("error", "用户名不能为空");
		}
		return "login";
	}
	
	//加密 
	public String encodePassword(String password){
		//加盐
		//password = "sdfwqgfxbewrgbwefh123456vxhsqwfxcvbremeyts";
		String algorithm = "MD5";
		char[] encodeHex = null;
		try {
			//MD5
			MessageDigest instance = MessageDigest.getInstance(algorithm);
			byte[] digest = instance.digest(password.getBytes());
			//十六进制
			encodeHex = Hex.encodeHex(digest);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//十六制
		return new String(encodeHex);
	}
	public static void main(String[] args) {
		LoginController l = new LoginController();
		String p = l.encodePassword("123456");
		System.out.println(p);
	}
	
	//是否登陆
	@RequestMapping(value = "/isLogin.aspx")
	public @ResponseBody
	MappingJacksonValue isLogin(String callback,HttpServletRequest request,HttpServletResponse response) throws IOException{
		Integer result = 0;
		//判断用户是否登陆
		String username = sessionProvider.getAttributterForUsername(RequestUtils.getCSESSIONID(request, response));
		if(null != username){
			result = 1;
		}
		//<script  类  Spring公司提供
		MappingJacksonValue  mjv = new MappingJacksonValue(result);
		//设置jsonpFunction 
		mjv.setJsonpFunction(callback);
		return mjv;
	}
	
}
