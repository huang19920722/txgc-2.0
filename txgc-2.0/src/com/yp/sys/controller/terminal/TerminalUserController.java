package com.yp.sys.controller.terminal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yp.sys.common.BaseController;
import com.yp.sys.entity.terminal.TerminalUser;
import com.yp.sys.pojo.DataGridBean;
import com.yp.sys.pojo.DataGridJson;
import com.yp.sys.pojo.Json;
import com.yp.sys.service.terminal.ITerminalUserService;
/**
 * 
 * 文件名称： 终端使用者：Controller层
 * 内容摘要： 
 * 创建人： nihui
 * 创建日期： 2016-11-16
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
@RequestMapping("/terminalUser")
public class TerminalUserController extends BaseController {

	@Autowired
	private ITerminalUserService terminalUserService;
	
	@RequestMapping(value = "/terminalUserList")
	public String toTer() {
		return "sys/terminal/terminalUserList";

	}
	/**
	 * <p>页面分页</p>
	 * @param dg
	 * @param terminalUser
	 * @return DataGridJson
	 */
	@RequestMapping(value="/datagrid")
	@ResponseBody
	public DataGridJson datagrid(DataGridBean dg,TerminalUser terminalUser){
			return terminalUserService.datagrid(dg, terminalUser);
	}
	/**
	 * <p>新增终端使用者</p>
	 * @param terminalUser
	 * @return Json
	 */
	@RequestMapping(value="/addTerminalUser")
	@ResponseBody
	public Json addTerminalUser(TerminalUser terminalUser){
		Json json=new Json();
		json.setSuccess(terminalUserService.addOrUpdate(terminalUser));
		return json;
	}
	/**
	 * <p>修改终端使用者</p>
	 * @param terminalUser
	 * @return Json
	 */
	@RequestMapping(value="/updateTerminalUser")
	@ResponseBody
	public Json updateTerminalUser(TerminalUser terminalUser){
		Json json=new Json();
		json.setSuccess(terminalUserService.addOrUpdate(terminalUser));
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
		json.setSuccess(terminalUserService.deleteTerminalUserById(ids));
		return json;
	}
	/**
	 * 查询终端用户名字
	 * @return
	 */
	@RequestMapping(value="/findTerminalUserName")
	@ResponseBody
	public List<TerminalUser> getAllTerminalUserName(Long orgId){
		return this.terminalUserService.listAllName(orgId);	
	}
	
}
