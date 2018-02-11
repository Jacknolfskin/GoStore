package cn.go.core.service.product;

import cn.zhx.common.page.Pagination;
import cn.zhx.core.bean.product.Product;

public interface ProductService {

	// 返回分页对象 入参： Integer pageNo,String name,Long brandId,Boolean isShow （默认值
	// false）
	public Pagination selectPaginationByQuery(Integer pageNo, String name, Long brandId, Boolean isShow);
	
	//保存商品
	public void insertProduct(Product product);
	
	//上架
	public void isShow(Long[] ids);

}
