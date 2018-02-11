package cn.itcast;

import java.io.StringWriter;
import java.io.Writer;

import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.zhx.core.bean.TestTb;

/**
 * 测试类
 * @author lx
 *
 */
public class TestJson {

	
	@Test
	public void testJson() throws Exception {
		TestTb testTb = new TestTb();
		testTb.setName("范冰冰3");
		
		ObjectMapper  om = new ObjectMapper();
		om.setSerializationInclusion(Include.NON_NULL);
		//
		Writer w = new StringWriter();
		om.writeValue(w, testTb);
		
		System.out.println(w.toString());
		
		//转回对象
		TestTb r = om.readValue(w.toString(), TestTb.class);
		System.out.println(r.toString());
		
		
		
	}
	
}
