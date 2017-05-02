package com.yp.sys.service.terminal;

import java.util.Date;
import java.util.Map;

import com.yp.sys.entity.terminal.TerminalInfo;
import com.yp.sys.pojo.DataGridBean;
import com.yp.sys.pojo.DataGridJson;
import com.yp.sys.service.common.IBaseService;

/**
 * 内容摘要： 终端信息service层
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
public interface ITerminalInfoService extends IBaseService<TerminalInfo> {

	/**
	 * datagrid数据列表
	 * @param dataGridBean
	 * @param terminalInfo
	 * @return
	 */
	public DataGridJson datagrid(DataGridBean dataGridBean,TerminalInfo terminalInfo,Map<String, Date> timeParams);

	/**
	 * 添加或修改一行TerminalInfo
	 * @param terminalInfo
	 * @return
	 */
	public Boolean addOrUpdate(TerminalInfo terminalInfo);
	/**
	 * <p>通过TerminalInfo id删除一行TerminalInfo数据</p>
	 * @param ids
	 * @return Boolean
	 */
	public Boolean deleteTerminalInfoById(String[] ids);
	
	/**
	 * <p>根据code查询terminal</p>
	 * @param code
	 * @return Boolean
	 */
	public Boolean findTerminalByCode(String code);
}
