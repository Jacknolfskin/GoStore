package cn.itcast;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import redis.clients.jedis.Jedis;

/**
 * 测试类
 * @author lx
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class TestJedis {

	@Autowired
	private Jedis jedis;
	@Test
	public void testSpringAndRedis() throws Exception {
		jedis.set("qqq", "www");
		
		jedis.close();
	}
	
	@Test
	public void testRedis() throws Exception {
		Jedis jedis = new Jedis("192.168.200.128",6379);
		
		jedis.set("ooo", "aaa");
		
		jedis.close();
		
	}
	
}
