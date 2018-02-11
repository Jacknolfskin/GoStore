package cn.go.core.service;

import java.util.List;

import cn.zhx.core.bean.product.Product;
import cn.zhx.core.bean.product.Sku;

public interface CmsService {
	
	
	
	//查询商品 通过Id
	public Product selectProductById(Long id);
	
	//查询SKU结果集 要求：库存必须大于0   通过商品ID
	public List<Sku> selectSkuListByProductIdWithStock(Long id);

}
