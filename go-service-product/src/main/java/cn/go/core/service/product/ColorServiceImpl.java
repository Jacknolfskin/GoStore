package cn.go.core.service.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zhx.core.bean.product.Color;
import cn.zhx.core.bean.product.ColorQuery;
import cn.zhx.core.dao.product.ColorDao;

/**
 * 颜色
 * @author lx
 *
 */
@Service("colorService")
public class ColorServiceImpl  implements ColorService{

	@Autowired
	private ColorDao colorDao;
	//查询  父ID不为0
	public List<Color> selectColorListByQuery(){
		ColorQuery colorQuery = new ColorQuery();
		colorQuery.createCriteria().andParentIdNotEqualTo(0L);
		return colorDao.selectByExample(colorQuery);
	}
}
