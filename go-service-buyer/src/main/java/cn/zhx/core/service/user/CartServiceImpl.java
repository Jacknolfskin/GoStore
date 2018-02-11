package cn.zhx.core.service.user;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zhx.core.bean.BuyerCart;
import cn.zhx.core.bean.BuyerItem;
import cn.zhx.core.bean.order.Detail;
import cn.zhx.core.bean.order.Order;
import cn.zhx.core.bean.product.Sku;
import cn.zhx.core.dao.order.DetailDao;
import cn.zhx.core.dao.order.OrderDao;
import cn.zhx.core.dao.product.ColorDao;
import cn.zhx.core.dao.product.ProductDao;
import cn.zhx.core.dao.product.SkuDao;
import redis.clients.jedis.Jedis;

@Service("cartService")
public class CartServiceImpl implements CartService {

	@Autowired
	private SkuDao skuDao;
	@Autowired
	private ColorDao colorDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private Jedis jedis;

	// 通过SKUID 查询 sku对象 颜色对象 商品对象
	public Sku selectSkuById(Long skuId) {
		Sku sku = skuDao.selectByPrimaryKey(skuId);
		// 颜色
		sku.setColor(colorDao.selectByPrimaryKey(sku.getColorId()));
		// sku.getProduct.getImgUrls[0]
		sku.setProduct(productDao.selectByPrimaryKey(sku.getProductId()));
		return sku;
	}

	// 保存购物车到Redis中
	public void insertBuyerCartToRedis(BuyerCart buyerCart, String username) {
		// 购物项结果集
		List<BuyerItem> items = buyerCart.getItems();
		if (items.size() > 0) {
			Map<String, String> hash = new HashMap<String, String>();
			for (BuyerItem item : items) {
				// 判断是否有同款
				if (jedis.hexists("buyerCart:" + username, String.valueOf(item.getSku().getId()))) {
					// 同款追加数量
					jedis.hincrBy("buyerCart:" + username, String.valueOf(item.getSku().getId()), item.getAmount());
				} else {
					hash.put(String.valueOf(item.getSku().getId()), String.valueOf(item.getAmount()));
				}
			}
			if (hash.size() > 0) {
				jedis.hmset("buyerCart:" + username, hash);
			}
		}
	}

	// 取出Redis中购物车
	public BuyerCart selectBuyerCartFromRedis(String username) {
		BuyerCart buyerCart = new BuyerCart();
		// 所有商品
		Map<String, String> hgetAll = jedis.hgetAll("buyerCart:" + username);
		// 遍历 商品保存到购物车
		Set<Entry<String, String>> entrySet = hgetAll.entrySet();
		for (Entry<String, String> entry : entrySet) {

			Sku sku = new Sku();
			sku.setId(Long.parseLong(entry.getKey()));
			BuyerItem buyerItem = new BuyerItem();
			buyerItem.setSku(sku);
			// 数量
			buyerItem.setAmount(Integer.parseInt(entry.getValue()));
			// 添加到购物车
			buyerCart.addItem(buyerItem);
		}

		return buyerCart;
	}

	// 取出 Redis中购物车指定商品
	public BuyerCart selectBuyerCartFromRedisBySkuIds(String[] skuIds,String username) {
		BuyerCart buyerCart = new BuyerCart();
		// 所有商品
		Map<String, String> hgetAll = jedis.hgetAll("buyerCart:" + username);
		
		if(null != hgetAll && hgetAll.size() > 0){
			// 遍历 商品保存到购物车
			Set<Entry<String, String>> entrySet = hgetAll.entrySet();
			for (Entry<String, String> entry : entrySet) {
				for (String skuId : skuIds) {
					if(skuId.equals(entry.getKey())){
						Sku sku = new Sku();
						sku.setId(Long.parseLong(entry.getKey()));
						BuyerItem buyerItem = new BuyerItem();
						buyerItem.setSku(sku);
						// 数量
						buyerItem.setAmount(Integer.parseInt(entry.getValue()));
						// 添加到购物车
						buyerCart.addItem(buyerItem);
					}
				}
			}
		}
		return buyerCart;
	}
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private DetailDao detailDao;
	//保存订单
	public void insertOrder(Order order,String username){
		//订单ID
//		ID   订单编号 全国唯一 Redis
		Long id = jedis.incr("oid");
		order.setId(id);
		//用户ID
		String buyerId = jedis.get(username);
		order.setBuyerId(Long.parseLong(buyerId));
		//从Redis取出购物车
		BuyerCart buyerCart = selectBuyerCartFromRedis(username);
		List<BuyerItem> items = buyerCart.getItems();
		for (BuyerItem item : items) {
			item.setSku(selectSkuById(item.getSku().getId()));
		}
//		运费   由购物车提供
		order.setDeliverFee(buyerCart.getFee());
//		订单总金额 由购物车提供
		order.setTotalPrice(buyerCart.getTotalPrice());
//		订单金额  由购物车提供
		order.setOrderPrice(buyerCart.getProductPrice());
		
//		支付状态 :0到付1待付款,2已付款,3待退款,4退款成功,5退款失败   后台程序 
		if(order.getPaymentWay() == 1){
			order.setIsPaiy(0);
		}else{
			order.setIsPaiy(1);
		}
//		订单状态 0:提交订单 1:仓库配货 2:商品出库 3:等待收货 4:完成 5待退货 6已退货
		order.setOrderState(0);
//		生成时间 必须
		order.setCreateDate(new Date());
		
		//保存订单
		orderDao.insertSelective(order);
		
		
		//保存订单详情
		for (BuyerItem item : items) {
			Detail detail = new Detail();
//			订单详情表
//			ID  自增长
//			订单ID  上面表的ID
			detail.setOrderId(id);
//			商品编号 ID 
			detail.setProductId(item.getSku().getProductId());
//			商品名称
			detail.setProductName(item.getSku().getProduct().getName());
//			颜色中文名称
			detail.setColor(item.getSku().getColor().getName());
//			尺码 
			detail.setSize(item.getSku().getSize());
//			价格
			detail.setPrice(item.getSku().getPrice());
//			数量
			detail.setAmount(item.getAmount());

//			购物车中购物项提交数量
			detailDao.insertSelective(detail);
		}
		//清空购物车
		jedis.del("buyerCart:" + username);
		//jedis
	}
}
