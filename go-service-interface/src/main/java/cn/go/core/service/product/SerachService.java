package cn.go.core.service.product;

import java.util.List;

import cn.zhx.common.page.Pagination;
import cn.zhx.core.bean.product.Brand;

public interface SerachService {
	
	
	//查询商品信息从Solr
	public Pagination selectPaginationFromSolr(Integer pageNo,String keyword,String price,Long brandId);
	
	//查询Redis中的品牌结果集
	public List<Brand> selectBrandListFromRedis();
	
	//保存商品信息到SOLR服务器中
	public void insertProductToSolr(Long id);

}
