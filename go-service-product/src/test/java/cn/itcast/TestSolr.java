package cn.itcast;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试类
 * @author lx
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class TestSolr {
	@Autowired
	private SolrServer solrServer;
	
	@Test
	public void testSolrSpring() throws Exception {
		
		SolrInputDocument doc = new SolrInputDocument();
		
		doc.setField("id", 4);
		doc.setField("name", "范冰冰88888");
		//保存
		solrServer.add(doc);
		
		solrServer.commit();
	}
	
	@Test
	public void testSolr() throws Exception {
		String baseURL = "http://192.168.200.128:8080/solr";
		//客户端
		SolrServer solrServer = new HttpSolrServer(baseURL);
		
		SolrInputDocument doc = new SolrInputDocument();
		
		doc.setField("id", 3);
		doc.setField("name", "范冰冰");
		//保存
		solrServer.add(doc);
		
		solrServer.commit();
		
	}
	
}
