package cn.go.core.service.product;

import java.util.Date;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.zhx.common.page.Pagination;
import cn.zhx.core.bean.product.Product;
import cn.zhx.core.bean.product.ProductQuery;
import cn.zhx.core.bean.product.ProductQuery.Criteria;
import cn.zhx.core.bean.product.Sku;
import cn.zhx.core.dao.product.ProductDao;
import cn.zhx.core.dao.product.SkuDao;
import redis.clients.jedis.Jedis;

/**
 * 商品管理
 * @author lx
 *
 */
@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductDao productDao;
	
	//返回分页对象  入参： Integer pageNo,String name,Long brandId,Boolean isShow （默认值 false）
	public Pagination selectPaginationByQuery(Integer pageNo,String name,Long brandId,Boolean isShow){
		//创建商品条件对象
		ProductQuery productQuery = new ProductQuery();
		//当前页
		productQuery.setPageNo(Pagination.cpn(pageNo));
		//每页数
		productQuery.setPageSize(3);
		
		//排序
		productQuery.setOrderByClause("id desc");
		//唯一的条件对象
		Criteria createCriteria = productQuery.createCriteria();
		
		StringBuilder params = new StringBuilder();
		//判断条件
		if(null != name){
			createCriteria.andNameLike("%" + name + "%");
			params.append("name=").append(name);
		}
		if(null != brandId){
			createCriteria.andBrandIdEqualTo(brandId);
			params.append("&brandId=").append(brandId);
		}
		//上下架
		if(null != isShow){
			createCriteria.andIsShowEqualTo(isShow);
			params.append("&isShow=").append(isShow);
		}else{
			createCriteria.andIsShowEqualTo(false);
			params.append("&isShow=").append(false);
		}
		//构建分页对象
		Pagination pagination = new Pagination(
				productQuery.getPageNo(),
				productQuery.getPageSize(),
				productDao.countByExample(productQuery),
				productDao.selectByExample(productQuery)
				);
		
		//分页在页面上展示
		String url = "/product/list.do";
		pagination.pageView(url, params.toString());
		return pagination;
	}
	@Autowired
	private SkuDao skuDao;
	@Autowired
	private Jedis jedis;
	//保存商品
	public void insertProduct(Product product){
		//商品   
		Long id = jedis.incr("pno");
		product.setId(id);
		//默认下架
		product.setIsShow(false);
		//默认不删除
		product.setIsDel(true);
		//时间 
		product.setCreateTime(new Date());
		//保存商品   
		productDao.insertSelective(product);
		
		//库存  多个
		for(String color : product.getColors().split(",")){
			for(String size : product.getSizes().split(",")){
				Sku sku = new Sku();
				//商品ＩＤ
				sku.setProductId(product.getId());
				//颜色
				sku.setColorId(Long.parseLong(color));
				
				//市场价
				sku.setMarketPrice(0f);
				//售价
				sku.setPrice(0f);
				//运费
				sku.setDeliveFee(10f);
				//购买限制
				sku.setUpperLimit(188);
				//尺码 
				sku.setSize(size);
				//时间
				sku.setCreateTime(new Date());
				
				sku.setStock(0);
				//
				skuDao.insertSelective(sku);
			}
		}
	}
	@Autowired
	private JmsTemplate jmsTemplate;
	//上架
	public void isShow(Long[] ids){
		//5
		//上下架状态   update bbs_product set is_show = 1 where id = #{id}
		Product product = new Product();
		product.setIsShow(true);
		
		for (final Long id : ids) {
			product.setId(id);
			productDao.updateByPrimaryKeySelective(product);
			//发送商品ID到ActiveMQ 即可
			jmsTemplate.send(new MessageCreator(){

				@Override
				public Message createMessage(Session session) throws JMSException {
					// TODO Auto-generated method stub
					return session.createTextMessage(String.valueOf(id));
				}
				
			});
			
			//TODO 静态化
		}
	}
}
