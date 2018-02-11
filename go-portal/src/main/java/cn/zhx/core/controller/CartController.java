package cn.zhx.core.controller;

import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.zhx.common.web.Constants;
import cn.zhx.common.web.RequestUtils;
import cn.zhx.core.bean.BuyerCart;
import cn.zhx.core.bean.BuyerItem;
import cn.zhx.core.bean.order.Order;
import cn.zhx.core.bean.product.Sku;
import cn.zhx.core.service.user.CartService;
import cn.zhx.core.service.user.SessionProvider;

/**
 * 加入购物车
 * 去购物车结算
 * 结算
 * 提交订单
 * @author lx
 *
 */
@Controller
public class CartController {

	@Autowired
	private CartService cartService;
	@Autowired
	private SessionProvider sessionProvider;
	
	//加入购物车
	@RequestMapping(value = "/shopping/buyerCart")
	public String buyerCart(Long skuId,Integer amount,HttpServletRequest request
			,HttpServletResponse response) throws Exception{
		ObjectMapper  om = new ObjectMapper();
		om.setSerializationInclusion(Include.NON_NULL);
//		非登陆
		BuyerCart buyerCart = null;
//		1：获取Cookie中的购物车
		Cookie[] cookies = request.getCookies();
		if(null != cookies && cookies.length > 0){
			for (Cookie cookie : cookies) {
				//
				if(Constants.BUYER_CART.equals(cookie.getName())){
					//购物车 对象   与json字符串  互转
					buyerCart = om.readValue(cookie.getValue(), BuyerCart.class);
					break;
				}
			}
		}
//		2：没有，创建购物车对象
		if(null == buyerCart){
			buyerCart = new BuyerCart();
		}
//		3：将当前款商品追加到购物车
		if(null != skuId && null != amount){
			Sku sku = new Sku();
			sku.setId(skuId);
			BuyerItem buyerItem = new BuyerItem();
			buyerItem.setSku(sku);
			//设置数量
			buyerItem.setAmount(amount);
			//添加购物项到购物车
			buyerCart.addItem(buyerItem);
		}
/*		//排序  倒排
		List<BuyerItem> items = buyerCart.getItems();
		Collections.sort(items, new Comparator<BuyerItem>() {
			
			@Override
			public int compare(BuyerItem o1, BuyerItem o2) {
				// TODO Auto-generated method stub
				//  -1 1 0 
				return -1;
			}
		});*/
		
		
		//判断用户是否已经登陆
		String username = sessionProvider.getAttributterForUsername(RequestUtils.getCSESSIONID(request, response));
		if(null != username){
			//登陆时
//			4：将购物车追加到Redis中   
			cartService.insertBuyerCartToRedis(buyerCart, username);
//			5：清空Cookie  0 马上
			Cookie cookie = new Cookie(Constants.BUYER_CART,null);
			cookie.setPath("/");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}else{
//		4：保存购物车到Cookie中
			Writer w = new StringWriter();
			om.writeValue(w, buyerCart);
			Cookie cookie = new Cookie(Constants.BUYER_CART,w.toString());
			cookie.setPath("/");
			// -1  0   >0  秒
			cookie.setMaxAge(60*60*24);
//		5：Cookie写回浏览器
			response.addCookie(cookie);
		}
		//
//		重定向
		return "redirect:/shopping/toCart";
	}
	//去购物车结算
	@RequestMapping(value = "/shopping/toCart")
	public String toCart(Model model,HttpServletRequest request
			,HttpServletResponse response)throws Exception{
		
		ObjectMapper  om = new ObjectMapper();
		om.setSerializationInclusion(Include.NON_NULL);
//		非登陆
		BuyerCart buyerCart = null;
//		1：获取Cookie中的购物车
		Cookie[] cookies = request.getCookies();
		if(null != cookies && cookies.length > 0){
			for (Cookie cookie : cookies) {
				//
				if(Constants.BUYER_CART.equals(cookie.getName())){
					//购物车 对象   与json字符串  互转
					buyerCart = om.readValue(cookie.getValue(), BuyerCart.class);
					break;
				}
			}
		}

		//判断是否登陆
		String username = sessionProvider.getAttributterForUsername(RequestUtils.getCSESSIONID(request, response));
		if(null != username){
		 //2：购物车保存到Redis中
			if(null != buyerCart){
				cartService.insertBuyerCartToRedis(buyerCart, username);
//				5：清空Cookie  0 马上
				Cookie cookie = new Cookie(Constants.BUYER_CART,null);
				cookie.setPath("/");
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
		 //3：取出Redis中购物车
			buyerCart = cartService.selectBuyerCartFromRedis(username);
		}
		
//		4：没有 创建购物车 （不是null 购物车是空车子）
		if(null == buyerCart){
			buyerCart = new BuyerCart();
		}
//		5：回显 
		List<BuyerItem> items = buyerCart.getItems();
		if(items.size() > 0){
//		把购物车装满  Service 数据库查询数据
			for (BuyerItem buyerItem : items) {
				buyerItem.setSku(cartService.selectSkuById(buyerItem.getSku().getId()));
			}
			
		}
		model.addAttribute("buyerCart", buyerCart);
//		6：跳转购物页面
		return "cart";
	}
	
	//去结算
	@RequestMapping(value = "/buyer/trueBuy")
	public String trueBuy(String[] skuIds,Model model,HttpServletRequest request
			,HttpServletResponse response){
//		1、	购物车必须有商品   没有商品：1）原购物车页面刷新 （购物车页面就有 没有商品的提示）
		//取出用户名  再取购物车
		String username = sessionProvider.getAttributterForUsername(RequestUtils.getCSESSIONID(request, response));
		 //3：取出Redis中购物车
		BuyerCart buyerCart = cartService.selectBuyerCartFromRedisBySkuIds(skuIds, username);
		List<BuyerItem> items = buyerCart.getItems();
		if(items.size() > 0){
			//购物车中有商品
			Boolean flag = true;
//		2、	购物车中商品必须有库存 购买大于库存数量时，视认为无货   提示：购物车原页面不动。。有货改为无货（可加红提醒）
			for (BuyerItem item : items) {
				//装满
				item.setSku(cartService.selectSkuById(item.getSku().getId()));
				//校验库存  
				if(item.getAmount() > item.getSku().getStock()){
					//无货
					item.setIsHave(false);
					flag = false;
				}
			}
			//无货
			if(!flag){
				//有某个商品  无货  提 示：购物车原页面不动。。有货改为无货（可加红提醒）
				model.addAttribute("buyerCart", buyerCart);
				return "cart";
			}
		}else{
			//购物车中没有商品
//			1）原购物车页面刷新 （购物车页面就有 没有商品的提示
			return "redirect:/shopping/toCart";
		}
//		3、	正常进入下一个页面  
		return "order";
	}
	
	//提交订单
	@RequestMapping(value = "/buyer/submitOrder")
	public String submitOrder(Order order,HttpServletRequest request
			,HttpServletResponse response){
		//redis  fbb2016 : 1
		String username = sessionProvider.getAttributterForUsername(RequestUtils.getCSESSIONID(request, response));
		//保存订单
		cartService.insertOrder(order, username);
		
		return "success";
	}
	
}
