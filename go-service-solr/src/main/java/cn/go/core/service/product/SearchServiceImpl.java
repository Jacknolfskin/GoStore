package cn.go.core.service.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zhx.common.page.Pagination;
import cn.zhx.core.bean.product.Brand;
import cn.zhx.core.bean.product.Product;
import cn.zhx.core.bean.product.ProductQuery;
import cn.zhx.core.bean.product.Sku;
import cn.zhx.core.bean.product.SkuQuery;
import cn.zhx.core.dao.product.ProductDao;
import cn.zhx.core.dao.product.SkuDao;
import redis.clients.jedis.Jedis;

/**
 * 全文检索
 * @author lx
 *
 */
@Service("serachService")
public class SearchServiceImpl implements SerachService {

	@Autowired
	private SolrServer solrServer;
	
	//查询商品信息从Solr
	@Override
	public Pagination selectPaginationFromSolr(Integer pageNo,String keyword,
			String price,Long brandId){
		ProductQuery productQuery = new ProductQuery();
		
		//当前页
		productQuery.setPageNo(pageNo);
		//每页数
		productQuery.setPageSize(8);
		
		SolrQuery solrQuery = new SolrQuery();
		//关键词  商品名称
		solrQuery.set("q", "name_ik:" + keyword);
		//
		StringBuilder params = new StringBuilder();
		params.append("keyword=").append(keyword);
		
		//过滤条件
		if(null != brandId){
			solrQuery.addFilterQuery("brandId:" + brandId);
			params.append("&brandId=").append(brandId);
		}
		//价格   0-99    1600
		if(null != price){
			String[] split = price.split("-");
			if(split.length == 2){
				solrQuery.addFilterQuery("price:[" + split[0] + " TO " + split[1] + "]");
			}else{
				solrQuery.addFilterQuery("price:[" + split[0] + " TO *]");
			}
			params.append("&price=").append(price);
		}
		
		//排序
		solrQuery.addSort("price", ORDER.asc);
		//高亮
		//1：设置   打开高亮的开关
		solrQuery.setHighlight(true);
		//2:字段
		solrQuery.addHighlightField("name_ik");
		//3：设置 样式     <span style='color:red'>2016</span>
		solrQuery.setHighlightSimplePre("<span style='color:red'>");
		solrQuery.setHighlightSimplePost("</span>");
		
		
		
		//分页  limit 开始行，每页数
		solrQuery.setStart(productQuery.getStartRow());
		solrQuery.setRows(productQuery.getPageSize());
		
		QueryResponse response = null;
		try {
			response = solrServer.query(solrQuery);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
		//Map : K String  == 442 : V  Map
		// Map : K String == name_ik  : V list
		//List : String 0 , 1, ... list.get(0)2016最新款的女靴长靴粗跟高跟尖头高筒靴欧美风性感瘦腿弹力靴长筒靴骑士靴 黑   XXL  list.get(1)
		
		
		List<Product> products = new ArrayList<>();
		//结果集
		SolrDocumentList docs = response.getResults();
		//总条数
		long numFound = docs.getNumFound();
		for (SolrDocument doc : docs) {
			
			Product product = new Product();
			//商品ＩＤ
			String id = (String) doc.get("id");
			product.setId(Long.parseLong(id));
			//商品名称
			Map<String, List<String>> map = highlighting.get(id);
			List<String> list = map.get("name_ik");
			//String name = (String) doc.get("name_ik");
			product.setName(list.get(0));
			//图片
			String url = (String) doc.get("url");
			product.setImgUrl(url);
			//价格
			product.setPrice((Float) doc.get("price"));
			//品牌ID
			product.setBrandId(Long.parseLong(String.valueOf((Integer) doc.get("brandId"))));
			//
			products.add(product);
		}
		/*Pagination pagination = new Pagination(
				productQuery.getPageNo(),
				productQuery.getPageSize(),
				(int)numFound,
				products
				);*/
		//页面展示
		String url = "/search";
		Pagination pagination = new Pagination();
		//pagination.pageView(url, params.toString());
		
		return pagination;
	}
	
	@Autowired
	private Jedis jedis;
	//查询Redis中的品牌结果集
	public List<Brand> selectBrandListFromRedis(){
		List<Brand> brands = new ArrayList<>();
		Map<String, String> hgetAll = jedis.hgetAll("brand");
		Set<Entry<String, String>> entrySet = hgetAll.entrySet();
		for (Entry<String, String> entry : entrySet) {
			Brand brand = new Brand();
			brand.setId(Long.parseLong(entry.getKey()));
			brand.setName(entry.getValue());
			brands.add(brand);
		}
		return brands;
	}
	@Autowired
	private ProductDao productDao;
	@Autowired
	private SkuDao skuDao;
	//保存商品信息到SOLR服务器中
	public void insertProductToSolr(Long id){
		//TODO 保存商品信息到SOlr服务器
		SolrInputDocument doc = new SolrInputDocument();
		//ID
		doc.setField("id", id);
		//名称
		Product p = productDao.selectByPrimaryKey(id);
		doc.setField("name_ik", p.getName());
		//图片URL
		doc.setField("url", p.getImgUrls()[0]);
		//品牌ID
		doc.setField("brandId", p.getBrandId());
		//价格  select price from bbs_product where product_id order by price asc limit 0,1
		SkuQuery skuQuery = new SkuQuery();
		skuQuery.createCriteria().andProductIdEqualTo(id);
		skuQuery.setOrderByClause("price asc");
		skuQuery.setPageNo(1);
		skuQuery.setPageSize(1);
		List<Sku> skus = skuDao.selectByExample(skuQuery);
		doc.setField("price", skus.get(0).getPrice());
		//时间  本次 先不写
		try {
			solrServer.add(doc);
			solrServer.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
