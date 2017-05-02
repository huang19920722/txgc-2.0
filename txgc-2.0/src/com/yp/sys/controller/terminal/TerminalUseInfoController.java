package com.yp.sys.controller.terminal;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yp.sys.common.BaseController;
import com.yp.sys.entity.terminal.TerminalUseInfo;
import com.yp.sys.pojo.DataGridBean;
import com.yp.sys.pojo.DataGridJson;
import com.yp.sys.pojo.Json;
import com.yp.sys.service.terminal.ITerminalUseInfoService;
/**
 * 
 * 文件名称： 终端使用信息：Controller层
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
@RequestMapping("terminalUseInfo")
public class TerminalUseInfoController extends BaseController {

	@Autowired
	private ITerminalUseInfoService terminalUserInfoService;
	
	@RequestMapping(value = "/terminalUseInfoList")
	public String toTer() {
		return "sys/terminal/terminalUseInfoList";
	}
	/**
	 * <p>页面分页</p>
	 * @param dg
	 * @param terminalUseInfo
	 * @return DataGridJson
	 */
	@RequestMapping(value="/datagrid")
	@ResponseBody
	public DataGridJson datagrid(DataGridBean dg,TerminalUseInfo terminalUseInfo,Date backTimeBegin,Date backTimeEnd){
		Map<String,Date> timeParams=new HashMap<String, Date>();
		if(backTimeBegin!=null){
			timeParams.put("backTimeBegin",backTimeBegin);
		}
		if(backTimeEnd!=null){
			timeParams.put("backTimeEnd", backTimeEnd);
		}	
		return terminalUserInfoService.datagrid(dg, terminalUseInfo,timeParams);
	}
	/**
	 * <p>指派终端使用者</p>
	 * @param terminalUseInfo
	 * @return Json
	 */
	@RequestMapping(value="/assignTerminalUseInfo")
	@ResponseBody
	public Json assignTerminalUseInfo(TerminalUseInfo terminalUseInfo){
		Json json=new Json();
		json.setSuccess(terminalUserInfoService.assign(terminalUseInfo));
		return json;
	}
	
	/**
	 * <p>停用终端设备</p>
	 * @param terminalUseInfo
	 * @return Json
	 */
	@RequestMapping(value="/stopUseTerminalById")
	@ResponseBody
	public Json stopUseTerminalById(Long trminalId){
		Json json=new Json();
		json.setSuccess(terminalUserInfoService.stopUseTerminalById(trminalId));
		return json;
	}
	/**
	 * <p>归还终端设备</p>
	 * @param terminalUseInfo
	 * @return Json
	 */
	@RequestMapping(value="/comeBackTerminalById")
	@ResponseBody
	public Json comeBackTerminalById(Long trminalId){
		Json json=new Json();
		json.setSuccess(terminalUserInfoService.comeBackTerminalById(trminalId));
		return json;
	}
	
	
	
	/**
	 * <p>查看详情</p>
	 * @param terminalUseInfo
	 * @return Json
	 */
	@RequestMapping(value="/getTerminalUseInfo")
	@ResponseBody
	public Json other(Long id){
		TerminalUseInfo termnialUseInfo=new TerminalUseInfo();
		termnialUseInfo=this.terminalUserInfoService.getTerminalUseInfo(id);
		Json json=new Json();
		json.setSuccess(true);
		json.setObj(termnialUseInfo);
		return json;
	}
}
