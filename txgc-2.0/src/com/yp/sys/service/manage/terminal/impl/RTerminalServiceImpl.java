package com.yp.sys.service.manage.terminal.impl;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yp.sys.dao.manage.terminal.IRTerminalDao;
import com.yp.sys.entity.manage.RTerminal;
import com.yp.sys.pojo.DataGridBean;
import com.yp.sys.pojo.DataGridJson;
import com.yp.sys.service.common.impl.BaseServiceImpl;
import com.yp.sys.service.manage.terminal.IRTerminalService;
import com.yp.sys.util.StringUtil;
/**
 * 
* @ClassName: RTerminalServiceImpl 
* @Description: 终端服务成接口实现类 
* @author huangmx
* @date 2017年5月2日 下午3:24:00 
* @version V1.0
 */
@Service(value="rTerminalService")
public class RTerminalServiceImpl extends BaseServiceImpl<RTerminal> implements IRTerminalService {
	@Autowired
	private IRTerminalDao rTerminalDao;
	
	 @Override
	public DataGridJson terminalDataGrid(DataGridBean dg, RTerminal terminal) {
		 DataGridJson j=new DataGridJson();
		 String hql=" from RTerminal t ";
	/*	 if(terminal != null){
			 
		 }
	*/	 
		hql = hql.replaceFirst("and", " where ");
		String totalHql = " select count(*) " + hql;
		j.setTotal(rTerminalDao.count(totalHql));// 设置总记录数
		
		if (dg.getSort() != null) {// 设置排序
			hql += " order by " + dg.getSort() + " " + dg.getOrder();
		}
		j.setRows(rTerminalDao.find(hql,dg.getPage(), dg.getRows(),new ArrayList<Object>()));// 查询分页);
		return j;
	}
}
