package com.yp.sys.service.sys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.yp.sys.entity.sys.SysFiles;
import com.yp.sys.service.common.IBaseService;

/**   
 * 文件名称：文件上传service接口 
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
public interface ISysFilesService extends IBaseService<SysFiles> {
	/**
	 * 上传图片 
	 * @param img
	 * @return SysFiles
	 */
	public SysFiles uploadImg(MultipartFile img);
	/**
	 * 上传文件 
	 * @param file
	 * @return SysFiles
	 */
	public SysFiles uploadFile(MultipartFile file);
	/**
	 * 上传字幕
	 * @param file
	 * @param id
	 * @return
	 */
	public SysFiles uploadCaption(MultipartFile file,Long id);
	/**
	 * 上传视频 
	 * @param video
	 * @param chapterId
	 * @return SysFiles
	 */
	public SysFiles uploadVideo(MultipartFile video,Long chapterId);
	/**
	 * 文件下载 
	 * @param path 相对于根目录的路径
	 * @param fileName 文件名 xxx.xx
	 * @param request
	 * @param response
	 */
	public void downloadFile(String path, String fileName,HttpServletRequest request,HttpServletResponse response);
}
