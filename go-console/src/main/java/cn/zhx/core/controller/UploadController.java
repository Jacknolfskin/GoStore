package cn.zhx.core.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import cn.zhx.common.web.Constants;
import cn.go.core.service.upload.UploadService;

/**
 * 上传图片
 * @author lx
 *
 */
@Controller
public class UploadController {
	
	@Autowired
	private UploadService uploadService;

	//上传图片 品牌   K pic  : V  图片本身
	@RequestMapping(value = "/upload/uploadPic.do")
	public void uploadPic(@RequestParam(required = false) MultipartFile pic,
			HttpServletResponse response) throws IOException{
		
		System.out.println(pic.getOriginalFilename());
		//Java接口 连接 FastDFS
		String path = uploadService.uploadPic(pic.getBytes(), pic.getOriginalFilename(), pic.getSize());
		
		// path : group1/M00/00/01/wKjIgFWOYc6APpjAAAD-qk29i78248.jpg
		// url : http://192.168.200.128/  + path ;
		String url = Constants.img_url + path;
		//工具
		JSONObject jo = new JSONObject();
		jo.put("url", url);
		jo.put("path", path);
		
		//json 
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(jo.toString());
	}
	
	//上传图片 商品多张
	@RequestMapping(value = "/upload/uploadPics.do")
	public @ResponseBody
	List<String> uploadPics(@RequestParam(required = false) MultipartFile[] pics,
			HttpServletResponse response) throws IOException{
		//多张图片的路径容器
		List<String> paths = new ArrayList<String>();
		
		for (MultipartFile pic : pics) {
			System.out.println(pic.getOriginalFilename());
			//Java接口 连接 FastDFS
			String path = uploadService.uploadPic(pic.getBytes(), pic.getOriginalFilename(), pic.getSize());
			paths.add(Constants.img_url + path);
		}
		return paths;
	}
	
	//接收KindEditor  的图片    场景  不知道名称
	@RequestMapping(value = "/upload/uploadFck.do")
	public void uploadFck(HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		//猜想  不管图片叫什么名称 肯定在Request当中
		//Spring提供  MultipartRequest
		MultipartRequest mr = (MultipartRequest)request;
		//支持多张 但本次只有一张
		Map<String, MultipartFile> fileMap = mr.getFileMap();
		Set<Entry<String, MultipartFile>> entrySet = fileMap.entrySet();
		for (Entry<String, MultipartFile> entry : entrySet) {
			MultipartFile pic = entry.getValue();
			//Java接口 连接 FastDFS
			String path = uploadService.uploadPic(pic.getBytes(), pic.getOriginalFilename(), pic.getSize());
			
			// path : group1/M00/00/01/wKjIgFWOYc6APpjAAAD-qk29i78248.jpg
			// url : http://192.168.200.128/  + path ;
			String url = Constants.img_url + path;
			//工具
			JSONObject jo = new JSONObject();
			jo.put("error", 0);
			jo.put("url", url);
			//json  {"error" : 0 , "url" : "http://....."}
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(jo.toString());
		}
	}
}
