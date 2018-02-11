package cn.zhx.core.service.user;

import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.Jedis;

/**
 * Session提供类
 * @author lx
 *
 */
public class SessionProviderImpl implements SessionProvider{

	//活着的时间  单位分钟
	private Integer exp = 30;
	public void setExp(Integer exp) {
		this.exp = exp;
	}

	@Autowired
	private Jedis jedis;
	//jsessionid   value==用户名
	@Override
	public void setAttributterForUsername(String jsessionid, String value) {
		// TODO Auto-generated method stub
		jedis.set(jsessionid + ":USER_NAME", value);
		jedis.expire(jsessionid + ":USER_NAME", 60*exp);
	}

	@Override
	public String getAttributterForUsername(String jsessionid) {
		// TODO Auto-generated method stub
		String value = jedis.get(jsessionid + ":USER_NAME");
		if(null != value){
			jedis.expire(jsessionid + ":USER_NAME", 60*exp);
			return value;
		}
		return null;
	}

}
