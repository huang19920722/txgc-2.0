package com.yp.sys.controller.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yp.sys.common.BaseController;
import com.yp.sys.entity.manage.RTerminal;
import com.yp.sys.pojo.DataGridBean;
import com.yp.sys.pojo.DataGridJson;
import com.yp.sys.service.manage.terminal.IRTerminalService;

/**
 * 
* @ClassName: TerminalController 
* @Description: 终端控制层
* @author huangmx
* @date 2017年5月2日 下午4:05:02 
* @version V1.0
 */

@Controller
@RequestMapping(value="/terminalController")
public class TerminalController extends BaseController {
	@Autowired
	private IRTerminalService rTerminalService;
	
	/**
	* @Title: terminalDataGrid 
	* @Description: 终端列表数据查询
	* @param @param dg
	* @param @param terminal
	* @param @return     
	* @return DataGridJson     
	* @throws
	 */
	@RequestMapping(value="/terminalDataGrid")
	private DataGridJson terminalDataGrid(DataGridBean dg,RTerminal terminal){
		return rTerminalService.terminalDataGrid(dg, terminal);
	}
	
}
