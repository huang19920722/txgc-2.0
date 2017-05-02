package com.yp.sys.controller;

import java.util.ResourceBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.yp.sys.common.BaseController;
import com.yp.sys.entity.sys.SysFiles;
import com.yp.sys.pojo.UploaderJson;
import com.yp.sys.service.sys.ISysFilesService;

/**   
 * 文件名称：附件controller 
 * 内容摘要： 
 * 创建人： huangfei
 * 创建日期： 2015年10月28日
 * 版本号： v1.0.0
 * 公  司：重庆重邮汇侧有限公司
 * 版权所有： (C)2001-2015     
 * 修改记录1 
 * 修改日期：
 * 版本号：
 * 修改人：
 * 修改内容：  
 **/ 
@Controller
@RequestMapping("/file")
public class SysFilesController extends BaseController {
	
	@Autowired
	private ISysFilesService sysFilesService;
	
	/**
	 * 上传图片 
	 * @param image
	 * @return String
	 */
	@RequestMapping(value="/front/uploadImg")
	@ResponseBody
	public UploaderJson uploadImg(MultipartFile image){
		String status = "0";//失败
		String message = "上传失败";
		SysFiles sysFile = this.sysFilesService.uploadImg(image);
		UploaderJson json = new UploaderJson();
		if(sysFile!=null){
			status = "1";//成功
			message = "上传成功";
			json.setName(sysFile.getName());
			ResourceBundle bundle = ResourceBundle.getBundle("upload");
			String uploadUrl = bundle.getString("upload.url");
			json.setUrl(uploadUrl+sysFile.getFilePath());
			json.setFilePath(sysFile.getFilePath());
		}
		json.setMessage(message);
		json.setStatus(status);
		return json;
	}
	
	/**
	 * 上传文件 
	 * @param file
	 * @return String
	 */
	@RequestMapping(value="/front/uploadFile")
	@ResponseBody
	public UploaderJson uploadFile(MultipartFile file){
		String status = "0";//失败
		String message = "上传失败";
		SysFiles sysFile = this.sysFilesService.uploadFile(file);
		UploaderJson json = new UploaderJson();
		if(sysFile!=null){
			status = "1";//成功
			message = "上传成功";
			json.setName(sysFile.getName());
			ResourceBundle bundle = ResourceBundle.getBundle("upload");
			String uploadUrl = bundle.getString("upload.url");
			json.setUrl(uploadUrl+sysFile.getFilePath());
			json.setFilePath(sysFile.getFilePath());
		}
		json.setMessage(message);
		json.setStatus(status);
		return json;
	}
}
