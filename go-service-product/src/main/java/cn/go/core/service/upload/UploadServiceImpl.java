package cn.go.core.service.upload;

import org.springframework.stereotype.Service;

import cn.zhx.common.fdfs.FastDFSUtils;

/**
 * 上传图片
 * @author lx
 *
 */
@Service("uploadService")
public class UploadServiceImpl implements UploadService {

	
	//上传
	public String uploadPic(byte[] pic,String name,long size){
		return FastDFSUtils.uploadPic(pic, name, size);
	}
}
