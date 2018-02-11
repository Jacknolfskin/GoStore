package cn.zhx.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 后台管理中心
 * @author lx
 *
 */
@Controller
@RequestMapping(value = "/control")
public class CenterController {

		
	//入口
	@RequestMapping(value = "/index.do")
	public String index(){
		return "index";
	}
	//头
	@RequestMapping(value = "/top.do")
	public String top(){
		return "top";
	}
	//身体
	@RequestMapping(value = "/main.do")
	public String main(){
		return "main";
	}
	//身体 左
	@RequestMapping(value = "/left.do")
	public String left(){
		return "left";
	}
	//身体 右
	@RequestMapping(value = "/right.do")
	public String right(){
		return "right";
	}
	//商品身体
	@RequestMapping(value = "/frame/product_main.do")
	public String product_main(){
		return "frame/product_main";
	}
	//商品身体 左
	@RequestMapping(value = "/frame/product_left.do")
	public String product_left(){
		return "frame/product_left";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * void ：ajax 异步请求  json字符串
	 * String : 返回视图   数据与视图分开                      少 ： ajax 异步请求  不返回JSON字符时  返回一个页面时
	 * ModelAndView : 返回视图  ANd 返回数据     不用了 
	 */
	
/*	@Autowired
	private TestTbService testTbService;
	
	//测试
	@RequestMapping(value = "/test/index.do")
	public void index(Model model){
		
		TestTb testTb = new TestTb();
		testTb.setName("范冰冰5");
		testTb.setBirthday(new Date());
		
		testTbService.insertTestTb(testTb);
		
	}*/
}
