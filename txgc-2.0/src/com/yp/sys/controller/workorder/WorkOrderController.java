package com.yp.sys.controller.workorder;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
 




import org.springframework.web.bind.annotation.ResponseBody;

import com.yp.sys.common.GlobalContext;
import com.yp.sys.entity.organization.Organization;
import com.yp.sys.entity.terminal.TerminalUseInfo;
import com.yp.sys.entity.workorder.CommunityInfo;

import com.yp.sys.entity.workorder.WorkOrderInfo;
import com.yp.sys.pojo.CreatWorkOrderBean;
import com.yp.sys.pojo.DataGridBean;
import com.yp.sys.pojo.DataGridJson;
import com.yp.sys.pojo.Json;
import com.yp.sys.pojo.TreeNodeBean;
import com.yp.sys.pojo.UserBean;
import com.yp.sys.pojo.WorkOrderInfoBean;
import com.yp.sys.service.organization.IOrganizationService;
import com.yp.sys.service.user.IUserService;
import com.yp.sys.service.workorder.IWorkOrderService;
/**  
 * @Description: 工单controller
 * @author youxingliu
 * @date 2016年11月16日 下午4:25:33
 */
@Controller
@RequestMapping("/workOrder")
public class WorkOrderController {
	
	@Autowired
	private IUserService userService;
	@Autowired
	IWorkOrderService workOrderService;
	@Autowired
	private IOrganizationService organService;
	
	@RequestMapping("/{page}")
	public String index(@PathVariable String page){
		return "workorder/"+page;
	}
	//工单列表
	public String index(){
		return "workorder/index";
	}
	//终端选择页面
	@RequestMapping("/terminalSelect")
	public String terminalSelect(){
		return "workorder/terminalSelect";
	}
	//终端选择页面
	@RequestMapping("/cellSelect")
	public String cellSelect(){
		return "workorder/cellSelect";
	}
	//小区选择页面
	@RequestMapping("/communitySelect")
	public String communitySelect(){
		return "workorder/communitySelect";
	}
	//工单处理页面
	@RequestMapping("/processIndex")
	public String processIndex(){
		return "workorder/processIndex";
	}
	//终端使用信息列表
	@RequestMapping("/terminalUseGrid")
	@ResponseBody
	public DataGridJson terminalUseGrid(TerminalUseInfo param,DataGridBean bean){
		return workOrderService.terminalUseInfoGrid(param, bean);
	}
	//小区信息列表
	@RequestMapping("/communityGrid")
	@ResponseBody
	public DataGridJson communityGrid(CommunityInfo param,DataGridBean bean){
		return workOrderService.communityGrid(param, bean);
	}
	//工单列表
	@RequestMapping("/workOrderGrid")
	@ResponseBody
	public DataGridJson workOrderGrid(WorkOrderInfoBean param,DataGridBean bean){
		return workOrderService.workOrderGrid(param, bean);
	}
	
	//用户树
	@RequestMapping("/userTree")
	@ResponseBody
	public List<TreeNodeBean> userTree(){
		return workOrderService.userTree();
	}
	//用户自定义列
	@RequestMapping("/defineColumns")
	@ResponseBody
	public List<Object> defineColumns(String appNode){
		return workOrderService.defineColumns(appNode);
	}
	
	//获取采集信息所有字段
	@RequestMapping("/queryColletInfoWords")
	@ResponseBody
	public List<Object> queryColletInfoWords(){
		return workOrderService.queryColletInfoWords();
	}
	
	
	//用户自定义列
	@RequestMapping("/saveColumns")
	@ResponseBody
	public Json saveColumns(String appNode,String content){
		

		Json j = new Json();
		workOrderService.saveColumns(appNode,content);
		j.setSuccess(true);
		return j;
	}
	/**
	 * 查询工单自定义列
	 * @param appNode
	 * @param workOrderId
	 * @return
	 */
	@RequestMapping("/findWorkOrderDefCols")
	@ResponseBody
	public List<Object> findWorkOrderDefCols(String appNode,Long workOrderId){
		return workOrderService.findWorkOrderDefCols(appNode, workOrderId);
	}
	/**
	 * 同步并保存用户自定义列
	 * @param appNode
	 * @param content
	 * @param workOrderId
	 * @return
	 */
	@RequestMapping("/saveUpdateDefCols")
	@ResponseBody
	 public Json  saveUpdateDefCols(String content,Long workOrderId,String explainRemark){
		return workOrderService.saveUpdateDefCols( content, workOrderId,explainRemark); 
	}
	
	/**
	 * 创建工单
	 * @param param
	 * @return
	 */
		@RequestMapping("/createOrder")
		@ResponseBody
		public Json createOrder(CreatWorkOrderBean paramBean){
			Json j = new Json();
			try{
				j=workOrderService.createOrder(paramBean);
			}catch(Exception e){
				e.printStackTrace();
				j.setSuccess(false);
				j.setMsg("创建失败");
			}
			return j;
		}
		/**
		 * 查询工单详情
		 * @param workOrderId
		 * @return json
		 */
		@RequestMapping(value="queryWorkOrderDtail")
		@ResponseBody
		public Json queryWorkOrderDtail(Long workOrderId){
			Json j=new Json();
			j.setObj(workOrderService.queryWorkOrderDtail(workOrderId));
			j.setSuccess(true);
			return j;
		}
		/**
		 * 删除工单
		 * @param workOrderId
		 * @return Json
		 */
		@RequestMapping(value="deleteWorkOrder")
		@ResponseBody
		public Json deleteWorkOrder(Long workOrderId){
			return workOrderService.deleteWorkOrder(workOrderId);
		}
	
		
		@RequestMapping(value = "/findOrgTreeByLoginUser")
		@ResponseBody
		public List<TreeNodeBean> findOrgTreeByLoginUser() {
			//根据登录用户查找组织机构信息
			UserBean user=GlobalContext.getCurrentUser();
			//重新从数据库中再次查找用户的组织机构信息
			user=userService.getUserInfo(user);
			Organization org=user.getOrganization();
			//判断是否是超级管理员
			if(user.getId()==0){
				org=null;
			}
			return organService.treeId(org);
		}
}
