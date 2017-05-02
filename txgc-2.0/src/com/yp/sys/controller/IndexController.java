package com.yp.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.yp.sys.common.BaseController;
import com.yp.sys.common.GlobalContext;
import com.yp.sys.pojo.UserBean;

/**
 * 
 * 文件名称：IndexController  地址跳转
 * 内容摘要： 
 * 创建人：dumingqian
 * 创建日期： 2015-6-1
 * 版本号： v1.0.0
 * 公  司：重庆重邮汇侧有限公司
 * 版权所有： (C)2001-2015     
 * 修改记录1 
 * 修改日期：
 * 版本号：
 * 修改人：
 * 修改内容：  
 *
 */
@Controller
public class IndexController extends BaseController {
	
	/*****************************前台********************************/	
	
	/*************************后台***************************/
	@RequestMapping("/manage/index")
	public String index() {
		UserBean currUser = GlobalContext.getCurrentUser();
		if(currUser==null){
			return "redirect:/toLogin";
		}
		return "sys/index";
	}
	@RequestMapping("/toLogin")
	public String toLogin() {
		UserBean currUser = GlobalContext.getCurrentUser();
		if(currUser!=null){
			return "redirect:/manage/index";
		}
		return "sys/login";
	}	
}
