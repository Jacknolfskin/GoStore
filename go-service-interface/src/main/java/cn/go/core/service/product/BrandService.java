package cn.go.core.service.product;

import java.util.List;

import cn.zhx.common.page.Pagination;
import cn.zhx.core.bean.product.Brand;

public interface BrandService {
	
	//查询品牌结果集   名称 是否可见
	public List<Brand> selectBrandListByQuery(String name,Integer isDisplay);
	
	
	//返回分页对象
	public Pagination selectPaginationByQuery(Integer pageNo,String name,Integer isDisplay);
	
	
	//通过ID查询一个品牌
	public Brand selectBrandById(Long id);
	
	//修改
	public void updateBrandById(Brand brand);
	
	//删除批量
	public void deletes(Long[] ids);

}
