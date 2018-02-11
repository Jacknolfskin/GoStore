package cn.zhx.core.controller;

import cn.zhx.core.bean.product.Brand;
import cn.go.core.service.product.BrandService;
import cn.zhx.common.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 品牌
 * 
 * @author lx
 *
 */
@Controller
public class BrandController {

	@Autowired
	private BrandService brandService;

	// 去品牌列表页面
	@RequestMapping(value = "/brand/list.do")
	public String list(Integer pageNo, String name, Integer isDisplay, Model model) {

		// List<Brand> brands = brandService.selectBrandListByQuery(name,
		// isDisplay);//Shift+alt + L\
		Pagination pagination = brandService.selectPaginationByQuery(pageNo, name, isDisplay);
		model.addAttribute("pagination", pagination);
		model.addAttribute("name", name);
		if (null != isDisplay) {
			model.addAttribute("isDisplay", isDisplay);
		} else {
			model.addAttribute("isDisplay", 1);
		}
		return "brand/list";
	}

	@RequestMapping(value = "/brand/toEdit.do")
	public String toEdit(Long id, Model model) {
		//
		Brand brand = brandService.selectBrandById(id);
		model.addAttribute("brand", brand);
		return "brand/edit";
	}

	// 提交
	@RequestMapping(value = "/brand/edit.do")
	public String edit(Brand brand, Model model) {
		brandService.updateBrandById(brand);

		return "redirect:/brand/list.do";
	}

	// 删除
	@RequestMapping(value = "/brand/deletes.do")
	public String deletes(Long[] ids, Model model) {

		brandService.deletes(ids);
		
		return "forward:/brand/list.do";
	}

}
