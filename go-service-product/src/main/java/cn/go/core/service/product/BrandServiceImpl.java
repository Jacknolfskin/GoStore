package cn.go.core.service.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.zhx.common.page.Pagination;
import cn.zhx.core.bean.product.Brand;
import cn.zhx.core.bean.product.BrandQuery;
import cn.zhx.core.dao.product.BrandDao;
import redis.clients.jedis.Jedis;

@Service("brandService")
@Transactional
public class BrandServiceImpl implements BrandService {

	@Autowired
	private BrandDao brandDao;
	@Override
	public List<Brand> selectBrandListByQuery(String name,Integer isDisplay) {
		BrandQuery brandQuery = new BrandQuery();
		if(null != name){
			brandQuery.setName(name);
		}
		if(null != isDisplay){
			brandQuery.setIsDisplay(isDisplay);
		}else{
			//是   1=是   0否
			brandQuery.setIsDisplay(1);
		}
		// TODO Auto-generated method stub
		return brandDao.selectBrandListByQuery(brandQuery);
	}
	//返回分页对象
	public Pagination selectPaginationByQuery(Integer pageNo,String name,Integer isDisplay) {
		BrandQuery brandQuery = new BrandQuery();
		
		//设置当前页   cpn 如果是null 或小于 1  设置pageNo为1
		brandQuery.setPageNo(Pagination.cpn(pageNo));
		//设置每页数
		brandQuery.setPageSize(3);
		//拼接条件
		StringBuilder params = new StringBuilder();
		if(null != name){
			brandQuery.setName(name);
			
			params.append("name=").append(name);
		}
		if(null != isDisplay){
			brandQuery.setIsDisplay(isDisplay);
			
			params.append("&isDisplay=").append(isDisplay);
		}else{
			//是   1=是   0否
			brandQuery.setIsDisplay(1);
			params.append("&isDisplay=").append(1);
		}
		//构建分页对象
		Pagination pagination = new Pagination(
				brandQuery.getPageNo(),
				brandQuery.getPageSize(),
				brandDao.selectCount(brandQuery)
				);
		
		//查询结果集  select * from bbs_brand where....  limit (pageNo-1)*3 ,3
		pagination.setList(brandDao.selectBrandListByQuery(brandQuery));
		//分页在页面上展示       /brand/list.do?name=sfsf&isDisplay=0
		String url = "/brand/list.do";
		pagination.pageView(url, params.toString());
		
		return pagination;
		
	}
	
	//通过ID查询一个品牌
	public Brand selectBrandById(Long id){
		return brandDao.selectBrandById(id);
	}
	@Autowired
	private Jedis jedis;
	//修改
	@Override
	public void updateBrandById(Brand brand) {
		// TODO Auto-generated method stub
		//保存或修改Redis中的品牌
	/*	Map<String,String> hash = new HashMap<>();
		hash.put(String.valueOf(brand.getId()), brand.getName());
		jedis.hmset("brand", hash);*/
		jedis.hset("brand", String.valueOf(brand.getId()), brand.getName());
		brandDao.updateBrandById(brand);
	}
	
	//删除批量
	public void deletes(Long[] ids){
		brandDao.deletes(ids);
	}

}
