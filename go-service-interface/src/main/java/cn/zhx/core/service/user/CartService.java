package cn.zhx.core.service.user;

import cn.zhx.core.bean.BuyerCart;
import cn.zhx.core.bean.order.Order;
import cn.zhx.core.bean.product.Sku;

public interface CartService {

	// 通过SKUID 查询 sku对象 颜色对象 商品对象
	public Sku selectSkuById(Long skuId);

	// 保存购物车到Redis中
	public void insertBuyerCartToRedis(BuyerCart buyerCart, String username);

	// 取出Redis中购物车
	public BuyerCart selectBuyerCartFromRedis(String username);
	
	// 取出 Redis中购物车指定商品
	public BuyerCart selectBuyerCartFromRedisBySkuIds(String[] skuIds,String username);
	
	//保存订单
	public void insertOrder(Order order,String username);

}
