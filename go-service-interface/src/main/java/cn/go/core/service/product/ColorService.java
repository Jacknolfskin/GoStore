package cn.go.core.service.product;

import java.util.List;

import cn.zhx.core.bean.product.Color;

public interface ColorService {
	
	//查询  父ID不为0
	public List<Color> selectColorListByQuery();

}
