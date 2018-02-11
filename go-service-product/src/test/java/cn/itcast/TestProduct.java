package cn.itcast;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.zhx.core.bean.product.Product;
import cn.zhx.core.bean.product.ProductQuery;
import cn.zhx.core.dao.product.ProductDao;

/**
 * 测试类
 * @author lx
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class TestProduct {

	@Autowired
	private ProductDao productDao;
	
	@Test
	public void testProduct() throws Exception {
		
	/*	Product p = productDao.selectByPrimaryKey(1L);
		System.out.println(p);*/
		//  查询  按照条件查询   支持模糊查询    分页  排序  指定字段查   查询总条件  显瘦
		ProductQuery productQuery = new ProductQuery();
//		productQuery.createCriteria().andNameLike("%" + "显瘦" +"%");
		// select * from bbs_product <where> and name like .sfs  and ..sfs and 
		/*productQuery.createCriteria().andNameEqualTo("2016最新款的淑衣图2015新款韩版宽松显瘦毛毛贴口袋眼睛仿皮绒大衣外套 浅灰色 M")
			.andBrandIdEqualTo(3L);*/
		//排序  id desc
		productQuery.setOrderByClause("id desc");
		
		//分页
		productQuery.setPageNo(2);
		productQuery.setPageSize(3);
		//指定字段  select id  from bbs_......
		productQuery.setFields("id,name");
		
		List<Product> products = productDao.selectByExample(productQuery);
		for (Product product : products) {
			System.out.println(product);
		}
		
		// 查询总条数
		//productDao.countByExample(条件)
		// Product  2个字段
		//  insert into bbs_product (name,ssfsdf) values ('sdfsaf','21212')
		//productDao.insertSelective(record)
		
		//productDao.d
		
	}
	
}
