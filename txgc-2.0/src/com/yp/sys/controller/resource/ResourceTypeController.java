package com.yp.sys.controller.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.yp.sys.common.BaseController;
import com.yp.sys.entity.resource.ResourceType;
import com.yp.sys.pojo.DataGridBean;
import com.yp.sys.pojo.DataGridJson;
import com.yp.sys.pojo.Json;
import com.yp.sys.service.resource.IResourceTypeService;

/**
 * 
 * 文件名称： 資源类型：Controller层
 * 内容摘要： 
 * 创建人： yinyong
 * 创建日期： 2015-6-5
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
@RequestMapping("/resourceType")
public class ResourceTypeController extends BaseController {
	@Autowired
	private IResourceTypeService resourceService;
	/**
	 * <p>资源类型列表集合</p>
	 * @param model
	 * @return String
	 */
	@RequestMapping(value="list")
	public String list(Model model){
		model.addAttribute("resource", resourceService.searchAllResourceType());
		return "sys/resource/resourceType/list";
	}
	/**
	 * <p>页面分页</p>
	 * @param dg
	 * @param resourceType
	 * @return DataGridJson
	 */
	@RequestMapping(value="datagrid",method={RequestMethod.POST})
	@ResponseBody
	public DataGridJson datagrid(DataGridBean dg,ResourceType resourceType){
		return resourceService.datagrid(dg, resourceType);
	}
	/**
	 * <p>新增资源类型</p>
	 * @param resourceType
	 * @return Json
	 */
	@RequestMapping(value="addResourceType",method={RequestMethod.POST})
	@ResponseBody
	public Json addResourceType(ResourceType resourceType){
		Json json=new Json();
		
		json.setSuccess(resourceService.addOrUpdate(resourceType));
	
		return json;
	}
	/**
	 * <p>通过资源编号 判断该编号是否重复</p>
	 * @param number
	 * @return Boolean
	 */
	@RequestMapping(value="getResourceTypeByNumber",method={RequestMethod.POST})
	@ResponseBody
	public Boolean getResourceTypeByNumber(String number){
		return resourceService.getResourceTypeByNumber(number);
	}
	/**
	 * <p>修改编辑资源类型</p>
	 * @param resourceType
	 * @return Json
	 */
	@RequestMapping(value="updateResourceType",method={RequestMethod.POST})
	@ResponseBody
	public Json updateResourceType(ResourceType resourceType){
		Json json=new Json();
		json.setSuccess(resourceService.addOrUpdate(resourceType));
		return json;
	}
	/**
	 * <p>通过资源类型id，查找资源类型</p>
	 * @param resourceType
	 * @return Json
	 */
	@RequestMapping(value="findResourceTypeById",method={RequestMethod.POST})
	@ResponseBody
	public Json findResourceTypeById(ResourceType resourceType){
		Json json=new Json();
		json.setObj(resourceService.searchResourceTypeById(resourceType));
		return json;
	}
	/**
	 * <p>通过id删除 所选项</p>
	 * @param ids
	 * @return Json
	 */
	@RequestMapping(value="deleteById",method={RequestMethod.POST})
	@ResponseBody
	public Json deleteById(@RequestParam("ids[]") String ids[]){
		Json json=new Json();
		json.setSuccess(resourceService.deleteResourceTypeById(ids));
		return json;
	}
	@RequestMapping(value="moveResourceType",method={RequestMethod.POST})
	@ResponseBody
	public Json moveResourceType(ResourceType resourceType,String manual) {
		return resourceService.moveResourceType(resourceType, manual);
	}
	
}
