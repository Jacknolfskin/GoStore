package cn.go.core.service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.servlet.ServletContext;
import java.io.*;
import java.util.Map;

public class StaticPageServiceImpl implements StaticPageService,ServletContextAware{

	//Spring  xml  jstl
	private Configuration conf;
	public void setFreeMarkerConfigurer(FreeMarkerConfigurer freeMarkerConfigurer) {
		this.conf = freeMarkerConfigurer.getConfiguration();
	}

	//静态化页面的方法
	public void index(Map<String,Object> root,String id){
		//输出目录  
		// C:\workspace\.metadata\.plugins\
		//org.eclipse.wst.server.core\tmp4\wtpwebapps\babasport-service-cms\html\product\443.html
		String path = getPath("/html/product/" + id + ".html");
		File f = new File(path);
		File parentFile = f.getParentFile();
		if(!parentFile.exists()){
			parentFile.mkdirs();
		}
		//conf不用再目录所在位置
		Writer out = null;
		try {
			//读
			Template template = conf.getTemplate("product.html");
			//输出的位置
			//写
			out = new OutputStreamWriter(new FileOutputStream(f), "UTF-8");
			//处理
			template.process(root, out);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(null != out){
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	//获取webapp 下的html文件夹所在位置  /html.....
	public String getPath(String name){
		return servletContext.getRealPath(name);
	}
	private ServletContext servletContext;
	@Override
	public void setServletContext(ServletContext servletContext) {
		// TODO Auto-generated method stub
		this.servletContext = servletContext;
	}
}
