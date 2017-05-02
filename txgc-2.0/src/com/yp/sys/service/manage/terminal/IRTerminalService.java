package com.yp.sys.service.manage.terminal;

import com.yp.sys.entity.manage.RTerminal;
import com.yp.sys.pojo.DataGridBean;
import com.yp.sys.pojo.DataGridJson;
import com.yp.sys.service.common.IBaseService;
/**
* @ClassName: IRTerminalService 
* @Description: 终端服务层接口 
* @author huangmx
* @date 2017年5月2日 下午3:22:26 
* @version V1.0
 */
public interface IRTerminalService extends IBaseService<RTerminal> {
	/**
	* @Title: terminalDataGrid 
	* @Description: 查询终端列表信息 
	* @param @param dg
	* @param @param terminal
	* @param @return     
	* @return DataGridJson     
	* @throws
	 */
	DataGridJson terminalDataGrid(DataGridBean dg,RTerminal terminal);
}
