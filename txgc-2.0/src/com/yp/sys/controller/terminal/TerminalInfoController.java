package com.yp.sys.controller.terminal;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yp.sys.common.BaseController;
import com.yp.sys.entity.organization.Organization;
import com.yp.sys.entity.terminal.TerminalInfo;
import com.yp.sys.pojo.DataGridBean;
import com.yp.sys.pojo.DataGridJson;
import com.yp.sys.pojo.Json;
import com.yp.sys.pojo.ZtreeBean;
import com.yp.sys.service.organization.IOrganizationService;
import com.yp.sys.service.terminal.ITerminalInfoService;
/**
 * 
 * 文件名称： 终端信息：Controller层
 * 内容摘要： 
 * 创建人： nihui
 * 创建日期： 2016-11-18
 * 版本号： v1.0.0
 * 公  司：重邮汇测
 * 版权所有： (C)2001-2015     
 * 修改记录
 * 修改日期：
 * 版本号：
 * 修改人：
 * 修改内容：  
 *
 */
@Controller
@RequestMapping("/terminalInfo")
public class TerminalInfoController extends BaseController {

	@Autowired
	private ITerminalInfoService terminalInfoService;
	
	
	@Autowired
	private IOrganizationService orgService;
	
	@RequestMapping(value = "/terminalInfoList")
	public String toTer() {
		return "sys/terminal/terminalInfoList";
	}
		/**
		 * <p>页面分页</p>
		 * @param dg
		 * @param terminalInfo
		 * @return DataGridJson
		 */
	@RequestMapping(value="/datagrid")
	@ResponseBody
	public DataGridJson datagrid(DataGridBean dg,TerminalInfo terminalInfo,Date castTimeBegin,Date castTimeEnd){
		Map<String,Date> timeParams=new HashMap<String, Date>();
		if(castTimeBegin!=null){
			timeParams.put("castTimeBegin",castTimeBegin);
		}
		if(castTimeEnd!=null){
			timeParams.put("castTimeEnd", castTimeEnd);
		}
		return terminalInfoService.datagrid(dg, terminalInfo,timeParams);
	}
	/**
	 * <p>新增终端使用者</p>
	 * @param terminalInfo
	 * @return Json
	 */
	@RequestMapping(value="/addTerminalInfo")
	@ResponseBody
	public Json addTerminalInfo(TerminalInfo terminalInfo){
		Json json=new Json();
		json.setSuccess(terminalInfoService.addOrUpdate(terminalInfo));
		return json;
	}
	/**
	 * <p>修改终端使用者</p>
	 * @param terminalInfo
	 * @return Json
	 */
	@RequestMapping(value="/updateTerminalInfo")
	@ResponseBody
	public Json updateTerminalInfo(TerminalInfo terminalInfo){
		Json json=new Json();
		json.setSuccess(terminalInfoService.addOrUpdate(terminalInfo));
		return json;
	}
	/**
	 * <p>通过id删除 所选项</p>
	 * @param ids
	 * @return Json
	 */
	@RequestMapping(value="/deleteById")
	@ResponseBody
	public Json deleteById(@RequestParam("ids[]") String ids[]){
		Json json=new Json();
		json.setSuccess(terminalInfoService.deleteTerminalInfoById(ids));
		return json;
	}
	/**
	 * <p>terminalCode唯一性验证</p>
	 * @param ids
	 * @return Json
	 */
	@RequestMapping(value="/validateCode")
	@ResponseBody
	public Json validateCode(String code){
		Json json=new Json();
		json.setSuccess(terminalInfoService.findTerminalByCode(code));
		return json;
	}
	
	
	/**
	 * 从id=0开始加载机构顶级菜单
	 * 获取指定节点的全部子菜单（包括当前菜单节点）
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getOrgsbyRoleId")
	@ResponseBody
	
	public void getOrgsbyId(HttpServletRequest request,Organization org,Long roleId,HttpServletResponse response)  {
		Long id=org.getId();
		if(org.getId()==null){
			id=1L;
		}
		//查询所有的机构
		List<ZtreeBean> list=this.orgService.creatZtreeData(roleId, id);
		writeMenus(list,response);	
	}
	
	//输出菜单到页面
		private String writeMenus(List<ZtreeBean> root,HttpServletResponse response) {
			List<ZtreeBean> root2 = new ArrayList<ZtreeBean>();
			for(int i =0;i<root.size();i++){
				ZtreeBean mi=root.get(i);
				if(mi.getUrl()!=null && !"".equals(mi.getUrl())){
					 mi.setUrl(mi.getUrl());
				}
				 root2.add(mi); 
			}
			JSONArray json = JSONArray.fromObject(root2);
//			//System.out.println(json.toString());
			String jsonStr = json.toString();
			try {
				response.getWriter().write(jsonStr);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return jsonStr;
		}
		/**
		 * 根据当前用户查询当前用户权限机构
		 */
		@RequestMapping(value = "/findOrgByCurrentUser")
		@ResponseBody
		public List<Organization> findOrgByCurrentUser(){
			return orgService.findOrgByCurrentUser();
		}

	
}
