package com.yp.sys.service.sys.impl;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.yp.sys.dao.sys.ISysFilesDao;
import com.yp.sys.entity.sys.SysFiles;
import com.yp.sys.service.common.impl.BaseServiceImpl;
import com.yp.sys.service.sys.ISysFilesService;
import com.yp.sys.util.DateUtil;
import com.yp.sys.util.FileUtil;
import com.yp.sys.util.StringUtil;


/**   
 * 文件名称：文件上传service实现类 
 * 内容摘要： 
 * 创建人： huangfei
 * 创建日期： 2015年7月7日
 * 版本号： v1.0.0
 * 公  司：重庆重邮汇侧有限公司
 * 版权所有： (C)2001-2015     
 * 修改记录1 
 * 修改日期：
 * 版本号：
 * 修改人：
 * 修改内容：  
 **/ 
@Service("sysFilesService")
public class SysFilesServiceImpl extends BaseServiceImpl<SysFiles> implements ISysFilesService {
	
	@Autowired
	private ISysFilesDao sysFilesDao;
	
	@Value("${upload.img.suffix}")
	private String imgSuffix;//允许上传的图片后缀
	
	@Value("${upload.img.mime}")
	private String imgMime;//允许上传的图片mime类型
	
	@Value("${upload.img.upload.size}")
	private String imgUploadSize;//图片上传大小
	
	@Value("${upload.file.suffix}")
	private String fileSuffix;//允许上传文件后缀
	
	@Value("${upload.file.mime}")
	private String fileMime;//允许上传文件mime类型
	
	@Value("${upload.file.upload.size}")
	private String fileUploadSize;//允许上传文件大小
	
	@Value("${upload.rootPath}")
	private String rootPath;//服务器存放文件或图片的根路径
	
	@Value("${upload.video.suffix}")
	private String videoSuffix;//允许上传视频后缀
	
	@Value("${upload.video.mime}")
	private String videoMime;//允许上传视频mime类型
	
	@Value("${upload.video.upload.size}")
	private String videoUploadSize;//允许上传视频大小
	
	@Value("${upload.videoPath}")
	private String videoPath;//流媒体服务器存放视频的路径
	
	/*上传附件的类别*/
	private final String IMAGE_TYPE = "images"; //图片
	private final String FILE_TYPE = "files"; //文件
	private final String VIDEO_TYPE = "video"; //视频

	@Override
	public SysFiles uploadImg(MultipartFile img) {
		return upload(img, IMAGE_TYPE,null);
	}

	@Override
	public SysFiles uploadFile(MultipartFile file) {
		return upload(file, FILE_TYPE,null);
	}
	@Override
	public SysFiles uploadCaption(MultipartFile file,Long id) {
		return upload(file, FILE_TYPE,id);
	}
	
	/**
	 * 上传文件 
	 * @param file 文件
	 * @param type 类型
	 * @return boolean
	 */
	private SysFiles upload(MultipartFile file,String type,Long chapterId){
		if(file!=null&&!file.isEmpty()){
			if(StringUtil.isBlank(imgSuffix)||StringUtil.isBlank(imgMime)||StringUtil.isBlank(imgUploadSize)
					||StringUtil.isBlank(fileSuffix)||StringUtil.isBlank(fileMime)||StringUtil.isBlank(fileUploadSize)
					||StringUtil.isBlank(rootPath)||StringUtil.isBlank(videoSuffix)||StringUtil.isBlank(videoMime)
					||StringUtil.isBlank(videoUploadSize)||StringUtil.isBlank(videoPath)){
				throw new RuntimeException("upload.properties文件未正确配置");
			}
			String allowSuffix = imgSuffix;
			String allowMime = imgMime;
			String allowSize = imgUploadSize;
			if(StringUtil.isEquals(type, FILE_TYPE)){//文件
				allowSuffix = fileSuffix;
				allowMime = fileMime;
				allowSize = fileUploadSize;
			}else if(StringUtil.isEquals(type, VIDEO_TYPE)){
				allowSuffix = videoSuffix;
				allowMime = videoMime;
				allowSize = videoUploadSize;
			}
			String mime = file.getContentType();//MIME类型
			String fileName = file.getOriginalFilename();//文件名
			//int index = fileName.lastIndexOf(".");
			String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
			//fileName = fileName.substring(0, index);//原文件名
			long fileSize = file.getSize();//文件大小
			/*校验文件后缀*/
			List<String> suffixList = Arrays.asList(allowSuffix.split(","));
			if(!suffixList.contains(suffix)){
				return null;
			}
			/*校验MIME类型*/
			List<String> mimeList = Arrays.asList(allowMime.split(","));
			if(!mimeList.contains(mime)){
				return null;
			}
			/*校验上传文件大小*/
			int uploadSize = Integer.parseInt(allowSize);
			if(fileSize>uploadSize*1024){
				return null;
			}
			try {
				/*保存文件到硬盘*/
				String dateString = DateUtil.getCurrDate(DateUtil.YMD);
				String saveDir = type + File.separator + dateString;
				String newDir = rootPath + saveDir;
				String newFileName = StringUtil.getUUID() + suffix;
				if(StringUtil.isEquals(type, VIDEO_TYPE)){//视频
					newDir = videoPath;
					newFileName = String.valueOf(chapterId)+suffix;
				}
				//字幕文件 start
				if(suffix != null && (suffix.equals(".srt")||suffix.equals(".smi")||suffix.equals(".ssa"))){
					newDir = rootPath + "captions";
					newFileName = String.valueOf(chapterId)+suffix;
				}
				//字幕文件 end
				File fileDir = new File(newDir);
				if (!fileDir.exists()) {
					fileDir.mkdirs();
				}
				File newFile = new File(fileDir, newFileName);
				file.transferTo(newFile);
				/*封装数据*/
				SysFiles sysFile = new SysFiles();
				if(StringUtil.isEquals(type, VIDEO_TYPE)){
					/*读取视频时长*/
					Encoder encoder = new Encoder();
					MultimediaInfo m = encoder.getInfo(newFile);
					Long times = m.getDuration();
					sysFile.setTime(times);
				}
				sysFile.setName(newFileName);
				String saveUrl = type + "/" + dateString + "/" + newFileName;
				//字幕文件  start
				if(suffix != null && (suffix.equals(".srt")||suffix.equals(".smi")||suffix.equals(".ssa"))){
					saveUrl = "captions/"+String.valueOf(chapterId)+suffix;
				}
				//字幕文件  end
				sysFile.setFilePath(saveUrl);
				//this.save(sysFile);
				return sysFile;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public SysFiles uploadVideo(MultipartFile video,Long chapterId) {
		return upload(video, VIDEO_TYPE,chapterId);
	}

	@Override
	public void downloadFile(String path, String fileName,
			HttpServletRequest request, HttpServletResponse response) {
		if(StringUtil.isBlank(fileName)){
			return;
		}
		BufferedInputStream bis = null;
	    BufferedOutputStream bos = null;
	    OutputStream fos = null;
	    InputStream fis = null;
	    String fullFileName = request.getSession().getServletContext().getRealPath(path+fileName);
		File file = new File(fullFileName);
		if(!file.exists()){
			return;
		}
		try {
			fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            fos = response.getOutputStream();
            bos = new BufferedOutputStream(fos);
            FileUtil.setFileDownloadHeader(request, response, fileName);
            int byteRead = 0;
            byte[] buffer = new byte[1024];
            while((byteRead=bis.read(buffer,0,1024))!=-1){
                bos.write(buffer,0,byteRead);
            }
            bos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(fis!=null){
				try{
					fis.close();
				}catch(IOException  e){
					e.printStackTrace();
				}
			}
			if(bis!=null){
				try{
					bis.close();
				}catch(IOException  e){
					e.printStackTrace();
				}
			}
			if(fos!=null){
				try{
					fos.close();
				}catch(IOException  e){
					e.printStackTrace();
				}
			}
			if(bos!=null){
				try{
					bos.close();
				}catch(IOException  e){
					e.printStackTrace();
				}
			}
		}
	}

}
