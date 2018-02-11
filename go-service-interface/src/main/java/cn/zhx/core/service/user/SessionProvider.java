package cn.zhx.core.service.user;

public interface SessionProvider {
	
	//面向接口编译   实现类
	//保存用户名到Redis中   注册：用户名  密码 。。。手机号   到Mysql 同时  保存用户名作为K 用户ID作为V 保存到Redis中
	
	//保存用户的ID到Redis中
	//保存用户名到Redis中 
	public void setAttributterForUsername(String key,String value);
	
	
	//取
	public String getAttributterForUsername(String key);

}
