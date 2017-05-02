package com.yp.sys.service.terminal.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yp.sys.common.GeneralMethod;
import com.yp.sys.common.GlobalConstant;
import com.yp.sys.common.GlobalContext;
import com.yp.sys.dao.organization.IOrganizationDao;
import com.yp.sys.dao.terminal.ITerminalUserDao;
import com.yp.sys.entity.organization.Organization;
import com.yp.sys.entity.terminal.TerminalUser;
import com.yp.sys.pojo.DataGridBean;
import com.yp.sys.pojo.DataGridJson;
import com.yp.sys.service.common.impl.BaseServiceImpl;
import com.yp.sys.service.terminal.ITerminalUserService;
import com.yp.sys.util.StringUtil;
import com.yp.sys.util.YpBeanUtils;
/**
 * 内容摘要： 终端使用者service层实现层
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
@Service("terminalUserService")
public class TerminalUserServiceImpl extends BaseServiceImpl<TerminalUser>
		implements ITerminalUserService {

	@Autowired
	private ITerminalUserDao terminalUserDao;

	@Autowired
	private IOrganizationDao organizationDao;
	/**
	 * 分页查询termnialUse
	 * params：dataGridBean；
	 * params:terminalUse;
	 */
	@Override
	public DataGridJson datagrid(DataGridBean dataGridBean,
			TerminalUser terminalUser) {
		DataGridJson dataGridJson = new DataGridJson();
		StringBuffer hql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		hql.append(" from TerminalUser t  where recordStatus=?");
		params.add(GlobalConstant.FLAG_Y);
		// 添加条件1
		if (StringUtil.isNotBlank(terminalUser.getName())) {
			hql.append(" AND name LIKE ?");
			params.add("%" + terminalUser.getName() + "%");
		}
		// 添加条件2
		if (StringUtil.isNotBlank(terminalUser.getIDCard())) {
			hql.append(" AND IDCard LIKE ?");
			params.add(terminalUser.getIDCard() + "%");
		}
		// 添加条件3
		if (StringUtil.isNotBlank(terminalUser.getOrgIdForSearch())) {
			long orgId = Long.parseLong(terminalUser.getOrgIdForSearch()); 
			hql.append(" AND org.id = ? " );
			params.add(orgId);
		}else if(StringUtils.isNotBlank(GlobalContext.getCurrentUser().getOrgIds())){
			hql.append(" and t.org.id in (" +GlobalContext.getCurrentUser().getOrgIds()+ ")");
		}
		String totalHql = "select count(*) " + hql.toString();
		dataGridJson.setTotal(terminalUserDao.count(totalHql, params));
		// 设置排序（默认排序）
		if (dataGridBean.getSort() != null) {
			hql.append(" order by " + dataGridBean.getSort() + " "
					+ dataGridBean.getOrder());
		} else {
			hql.append(" order by id asc ");
		}
		List<TerminalUser> list = terminalUserDao.find(hql.toString(),
				dataGridBean.getPage(), dataGridBean.getRows(), params);
		dataGridJson.setRows(list);
		return dataGridJson;
	}

	/**
	 * 增加或修改terminalUse
	 * params:terminalUse
	 * 
	 */
	@Override
	public Boolean addOrUpdate(TerminalUser terminalUser) {
		boolean flag=false;
		if(terminalUser.getId()==null){//添加
			//设置通用信息
			GeneralMethod.setRecordInfo(terminalUser, true);
			Organization org1=organizationDao.get(Organization.class, terminalUser.getOrgId());
			terminalUser.setOrg(org1);
			//保存该条数据
			terminalUserDao.save(terminalUser);
			flag=true;
		}else{//修改
			TerminalUser newTerminalUser=terminalUserDao.get(TerminalUser.class,terminalUser.getId());
			if(newTerminalUser==null){	
				return false;
			}
			YpBeanUtils.copyPropertiesIgnoreNull(terminalUser,newTerminalUser);
			Organization org1=organizationDao.get(Organization.class, terminalUser.getOrgId());
			newTerminalUser.setOrg(org1);
			GeneralMethod.setRecordInfo(newTerminalUser, false);
			flag=true;
		}
		return flag;
	}

	/**
	 * 根据id删除terminalUser
	 * params:ids;
	 */
	@Override
	public Boolean deleteTerminalUserById(String[] ids) {
		if(ids==null||ids.length<1){
			return false;
		}
		TerminalUser newTerminalUser=null;
		for(String id : ids){
			Long idLong=Long.parseLong(id);
			newTerminalUser=terminalUserDao.get(TerminalUser.class, idLong);
			if(newTerminalUser==null){
				return false;
			}
			newTerminalUser.setRecordStatus(GlobalConstant.FLAG_N);
			//设置通用信息
			GeneralMethod.setRecordInfo(newTerminalUser, false);

		}
		return true;
	}

	/**
	 * 展示所有使用者名字
	 */
	@Override
	public List<TerminalUser> listAllName(Long orgId) {
		List<TerminalUser> terminalUsers =new ArrayList<TerminalUser>();
		StringBuffer hql=new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		if(orgId == null){
			hql.append(" from TerminalUser where recordStatus=?");
			params.add(GlobalConstant.FLAG_Y);
		}else{
			hql.append(" from TerminalUser where recordStatus=? and org.id=? ");
			params.add(GlobalConstant.FLAG_Y);
			params.add(orgId);
		}
		
		
		terminalUsers=this.terminalUserDao.find(hql.toString(), params);
		return terminalUsers;
	}

	
}
