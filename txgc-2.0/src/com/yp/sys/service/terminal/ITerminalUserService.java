package com.yp.sys.service.terminal;


import java.util.List;

import com.yp.sys.entity.terminal.TerminalUser;
import com.yp.sys.pojo.DataGridBean;
import com.yp.sys.pojo.DataGridJson;
import com.yp.sys.service.common.IBaseService;
/**
 * 内容摘要： 终端使用者service层
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
 
public interface ITerminalUserService extends IBaseService<TerminalUser> {

	/**
	 * datagrid数据列表
	 * @param dataGridBean
	 * @param terminalUser
	 * @return
	 */
	public DataGridJson datagrid(DataGridBean dataGridBean,TerminalUser terminalUser);

	/**
	 * 添加或修改一行TerminalUser
	 * @param terminalUser
	 * @return
	 */
	public Boolean addOrUpdate(TerminalUser terminalUser);
	/**
	 * <p>通过TerminalUser id删除一行TerminalUser数据</p>
	 * @param ids
	 * @return Boolean
	 */
	public Boolean deleteTerminalUserById(String[] ids);
	/**
	 * 查找所有的使用人名字
	 * @return
	 */
	public List<TerminalUser> listAllName(Long orgId);
	
	
}
