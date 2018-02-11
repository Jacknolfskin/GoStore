package cn.go.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zhx.core.bean.product.Product;
import cn.zhx.core.bean.product.Sku;
import cn.zhx.core.bean.product.SkuQuery;
import cn.zhx.core.dao.product.ColorDao;
import cn.zhx.core.dao.product.ProductDao;
import cn.zhx.core.dao.product.SkuDao;

/**
 * 商品详情页面数据加载
 * 
 * @author lx
 *
 */
@Service("cmsService")
public class CmsServiceImpl implements CmsService{

	@Autowired
	private ProductDao productDao;
	@Autowired
	private SkuDao skuDao;
	@Autowired
	private ColorDao colorDao;
	//查询商品 通过Id
	public Product selectProductById(Long id){
		return productDao.selectByPrimaryKey(id);
	}
	
	//查询SKU结果集 要求：库存必须大于0   通过商品ID
	public List<Sku> selectSkuListByProductIdWithStock(Long id){
		SkuQuery skuQuery = new SkuQuery();
		skuQuery.createCriteria().andProductIdEqualTo(id).andStockGreaterThan(0);
		List<Sku> skus = skuDao.selectByExample(skuQuery);
		for (Sku sku : skus) {
			sku.setColor(colorDao.selectByPrimaryKey(sku.getColorId()));
		}
		return skus;
	}
	
	
}
