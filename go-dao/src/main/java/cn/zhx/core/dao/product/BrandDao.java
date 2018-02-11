package cn.zhx.core.dao.product;

import java.util.List;

import cn.zhx.core.bean.product.Brand;
import cn.zhx.core.bean.product.BrandQuery;

public interface BrandDao {

	//查询品牌结果集   名称 是否可见
	public List<Brand> selectBrandListByQuery(BrandQuery brandQuery);
	
	//查询符合条件总条数
	public Integer selectCount(BrandQuery brandQuery);
	
	//通过ID查询一个品牌
	public Brand selectBrandById(Long id);
	
	//修改
	public void updateBrandById(Brand brand);
	
	//删除批量
	public void deletes(Long[] ids);
}
