package cn.zhx.core.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.zhx.common.page.Pagination;
import cn.zhx.core.bean.product.Brand;
import cn.zhx.core.bean.product.Color;
import cn.zhx.core.bean.product.Product;
import cn.zhx.core.bean.product.Sku;
import cn.go.core.service.CmsService;
import cn.go.core.service.product.SerachService;

/**
 * 前台
 * 首页
 * 检索页面
 * 商品详情页面
 * @author lx
 *
 */
@Controller
public class ProductController {

	@Autowired
	private SerachService serachService;
	//去首页
	@RequestMapping(value = "/")
	public String index(){
		return "index";
	}

	//搜索
	@RequestMapping(value = "/search")
	public String search(Integer pageNo,String keyword,String price,Long brandId,Model model){
		//品牌结果集 Redis中
		List<Brand> brands = serachService.selectBrandListFromRedis();
		model.addAttribute("brands", brands);
		
		//map  已选条件
		Map<String,String> map = new HashMap<>();
		if(null != brandId){
			// 100 品牌
			for (Brand brand : brands) {
				if(brandId.equals(brand.getId())){
					map.put("品牌", brand.getName());
					break;
				}
			}
		}
		// 0-99 1600以上
		if(null != price){
			String[] split = price.split("-");
			if(split.length == 2){
				map.put("价格", price);
			}else{
				map.put("价格", price + "以上");
			}
		}
		model.addAttribute("map", map);
		
		Pagination pagination = serachService.selectPaginationFromSolr(pageNo, keyword, price,brandId);
		model.addAttribute("pagination", pagination);
		model.addAttribute("keyword", keyword);
		model.addAttribute("brandId", brandId);
		model.addAttribute("price", price);
		return "search";
	}
	@Autowired
	private CmsService cmsService;
	//去商品详情页面
	@RequestMapping(value = "/product/detail")
	public String detail(Long id,Model model){
		//商品
		Product product = cmsService.selectProductById(id);
		
		model.addAttribute("product", product);
		//结果集   9，9，14，14，29，29，29
		List<Sku> skus = cmsService.selectSkuListByProductIdWithStock(id);
	
		//去掉重复的
		Set<Color> colors = new HashSet<Color>();
		for (Sku sku : skus) {
			colors.add(sku.getColor());
		}
		model.addAttribute("skus", skus);
		model.addAttribute("colors", colors);
		return "product";
	}
	
}
