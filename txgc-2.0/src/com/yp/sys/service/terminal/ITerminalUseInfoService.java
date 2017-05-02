package com.yp.sys.service.terminal;

import java.util.Date;
import java.util.Map;

import com.yp.sys.entity.terminal.TerminalUseInfo;
import com.yp.sys.pojo.DataGridBean;
import com.yp.sys.pojo.DataGridJson;
import com.yp.sys.service.common.IBaseService;
/**
 * 内容摘要： 终端使用情况service层
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
public interface ITerminalUseInfoService extends IBaseService<TerminalUseInfo> {


	/**
	 * datagrid数据列表
	 * @param dataGridBean
	 * @param terminalUser
	 * @return
	 */
	public DataGridJson datagrid(DataGridBean dataGridBean,TerminalUseInfo terminalUseInfo,Map<String, Date> timeParams);

	/**
	 * 指派TerminalUseInfo
	 * @param terminalUseInfo
	 * @return
	 */
	public Boolean assign(TerminalUseInfo terminalUseInfo);
	/**
	 * 查询终端使用信息
	 * @param id
	 * @return TerminalUseInfo
	 */
	public TerminalUseInfo getTerminalUseInfo(Long id);
	/**
	 * 停用终端设备
	 * @param trminalId
	 * @return Boolean
	 */
	Boolean stopUseTerminalById( Long trminalId);
	/**
	 * 归还终端设备
	 * @param trminalId
	 * @return Boolean
	 */
	Boolean comeBackTerminalById( Long trminalId);
}
