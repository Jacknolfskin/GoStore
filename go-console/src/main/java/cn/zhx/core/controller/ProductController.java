package cn.zhx.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.zhx.common.page.Pagination;
import cn.zhx.core.bean.product.Brand;
import cn.zhx.core.bean.product.Color;
import cn.zhx.core.bean.product.Product;
import cn.go.core.service.product.BrandService;
import cn.go.core.service.product.ColorService;
import cn.go.core.service.product.ProductService;

/**
 * 商品管理 列表查询 去添加 添加提交 上架
 * 
 * @author lx
 *
 */
@Controller
public class ProductController {

	@Autowired
	private ProductService productService;
	@Autowired
	private BrandService brandService;

	// 查询
	@RequestMapping(value = "/product/list.do")
	public String list(Integer pageNo, String name, Long brandId, Boolean isShow, Model model) {
		// 查询品牌结果集
		List<Brand> brands = brandService.selectBrandListByQuery(null, 1);
		model.addAttribute("brands", brands);
		//
		Pagination pagination = productService.selectPaginationByQuery(pageNo, name, brandId, isShow);
		model.addAttribute("pagination", pagination);
		model.addAttribute("name", name);
		model.addAttribute("brandId", brandId);
		if (null != isShow) {
			model.addAttribute("isShow", isShow);
		} else {
			model.addAttribute("isShow", false);
		}
		return "product/list";
	}

	@Autowired
	private ColorService colorService;
	// 去商品添加页面
	@RequestMapping(value = "/product/toAdd.do")
	public String toAdd(Model model) {
		// 查询品牌结果集
		List<Brand> brands = brandService.selectBrandListByQuery(null, 1);
		model.addAttribute("brands", brands);
		//颜色
		List<Color> colors = colorService.selectColorListByQuery();
		model.addAttribute("colors", colors);
		return "product/add";
	}
	
	//保存
	@RequestMapping(value = "/product/add.do")
	public String add(Product product,Model model) {
		
		productService.insertProduct(product);
		
		return "redirect:/product/list.do";
	}
	
	//上架
	@RequestMapping(value = "/product/isShow.do")
	public String isShow(Long[] ids,Model model) {
		productService.isShow(ids);
		return "forward:/product/list.do";
	}
}
