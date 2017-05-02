package com.yp.sys.controller.manager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yp.sys.common.BaseController;
/**
 * 
* @ClassName: GroupController 
* @Description: 分组管理 
* @author huangmx
* @date 2017年5月2日 上午11:10:33 
* @version V1.0
 */
@Controller
@RequestMapping(value="/groupController")
public class GroupController extends BaseController {
	
	@RequestMapping(value="/toGroup",method=RequestMethod.GET)
	private String toGroup(){
		return "manager/group/index";
	}
}
