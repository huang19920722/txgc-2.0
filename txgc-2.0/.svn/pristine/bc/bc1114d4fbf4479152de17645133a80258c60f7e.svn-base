package com.yp.sys.controller.user;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.yp.sys.common.BaseController;
import com.yp.sys.entity.Log;
import com.yp.sys.pojo.DataGridBean;
import com.yp.sys.pojo.DataGridJson;
import com.yp.sys.pojo.Json;
import com.yp.sys.service.user.ILogService;

@Controller
@RequestMapping("/log")
public class LogController extends BaseController {
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(LogController.class);
	private ILogService logService;
	
	public ILogService getLogService() {
		return logService;
	}
	@Autowired
	public void setLogService(ILogService logService) {
		this.logService = logService;
	}
	
	@RequestMapping(value = "/logList")
	public String west() {
		return "sys/log/log";
	}
	/**
	 * 用户表格
	 * 
	 * @param dg
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/datagrid")
	@ResponseBody
	public DataGridJson datagrid(DataGridBean dg, Log log) {
		return logService.datagrid(dg, log);
	}
	/**
	 * 删除日志
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/del")
	@ResponseBody
	public Json del(String ids) {
		Json logJson = new Json();
		logService.delLog(ids);
		logJson.setSuccess(true);
		return logJson;
	}
}
