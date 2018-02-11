package cn.zhx.core.service.user;

import cn.zhx.core.bean.user.Buyer;

public interface BuyerService {
	
	//查询用户对象 通过用户名
	public Buyer selectBuyerByusername(String username);

}
