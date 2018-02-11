package cn.go.core.service.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.zhx.core.bean.product.Sku;
import cn.zhx.core.bean.product.SkuQuery;
import cn.zhx.core.dao.product.ColorDao;
import cn.zhx.core.dao.product.SkuDao;

/**
 * 库存管理
 * 去库存列表
 * 修改
 * 保存
 * @author lx
 *
 */
@Service("skuService")
@Transactional
public class SkuServiceImpl implements SkuService{

	@Autowired
	private SkuDao skuDao;
	@Autowired
	private ColorDao colorDao;
	
	//查询SKu结果集
	public List<Sku> selectSkuListByProductId(Long productId){
		SkuQuery skuQuery = new SkuQuery();
		skuQuery.createCriteria().andProductIdEqualTo(productId);
		// 15条  3    1,2,3
		List<Sku> skus = skuDao.selectByExample(skuQuery);
		for (Sku sku : skus) {
			//向Sql语句    3条   select * from bbs_color where id = 1
			sku.setColor(colorDao.selectByPrimaryKey(sku.getColorId()));
		}
		return skus;
	}
	//保存
	public void updateSkuById(Sku sku){
		skuDao.updateByPrimaryKeySelective(sku);
	}
}
