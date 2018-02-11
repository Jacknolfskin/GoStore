package cn.zhx.common.fdfs;

import org.apache.commons.io.FilenameUtils;

import org.springframework.core.io.ClassPathResource;

/**
 * 上传FASTDFS图片
 * @author lx
 *
 */
public class FastDFSUtils {

	
	public static String uploadPic(byte[] pic,String name,long size){
		String path = null;
		//ClassPath下的文件 Spring   fdsasdaf.jpg
		ClassPathResource resource = new ClassPathResource("fdfs_client.conf");
		/*try {
			ClientGlobal.init(resource.getClassLoader().getResource("fdfs_client.conf").getPath());
			//客户端
			TrackerClient trackerClient = new TrackerClient();
			TrackerServer trackerServer = trackerClient.getConnection();
			
			StorageServer storageServer = null;
			//连接小弟
			StorageClient1 storageClient1 = new StorageClient1(trackerServer,storageServer);
			//扩展名
			String ext = FilenameUtils.getExtension(name);
			NameValuePair[] meta_list = new NameValuePair[3];
			meta_list[0] = new NameValuePair("filename", name);
			meta_list[1] = new NameValuePair("fileext", ext);
			meta_list[2] = new NameValuePair("filesize", String.valueOf(size));
			//上传
			path = storageClient1.upload_file1(pic, ext, meta_list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return path;
	}
}
