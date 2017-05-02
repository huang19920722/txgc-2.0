package com.yp.sys.service.terminal.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yp.sys.common.GeneralMethod;
import com.yp.sys.common.GlobalConstant;
import com.yp.sys.common.GlobalContext;
import com.yp.sys.dao.organization.IOrganizationDao;
import com.yp.sys.dao.terminal.ITerminalInfoDao;
import com.yp.sys.dao.terminal.ITerminalUseInfoDao;
import com.yp.sys.entity.organization.Organization;
import com.yp.sys.entity.terminal.TerminalInfo;
import com.yp.sys.entity.terminal.TerminalUseInfo;
import com.yp.sys.pojo.DataGridBean;
import com.yp.sys.pojo.DataGridJson;
import com.yp.sys.service.common.impl.BaseServiceImpl;
import com.yp.sys.service.terminal.ITerminalInfoService;
import com.yp.sys.util.DateUtil;
import com.yp.sys.util.StringUtil;
import com.yp.sys.util.YpBeanUtils;
/**
 * 内容摘要： 终端信息service层实现层
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
@Service("terminalInfoService")
public class TerminalInfoServiceImpl extends BaseServiceImpl<TerminalInfo> implements
		ITerminalInfoService {

	@Autowired
	private ITerminalInfoDao terminalInfoDao;
	@Autowired
	private IOrganizationDao organizationDao;
	@Autowired
	private ITerminalUseInfoDao terminalUseInfoDao;
	@Override
	/**
	 * 分页查询TerminalInfo
	 * params:datagridBean；
	 * params:terminalInfo;
	 * params:timeParams
	 */
	public DataGridJson datagrid(DataGridBean dataGridBean,
			TerminalInfo terminalInfo,Map<String, Date> timeParams) {
		DataGridJson dataGridJson = new DataGridJson();
		StringBuffer hql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		hql.append(" from TerminalInfo t where recordStatus=?");
		params.add(GlobalConstant.FLAG_Y);
		// 添加查询条件1
		if (StringUtil.isNotBlank(terminalInfo.getCode())) {
			hql.append(" AND code LIKE ?");
			params.add(terminalInfo.getCode() + "%");
		}
		// 添加查询条件2
		if (StringUtil.isNotBlank(terminalInfo.getModel())) {
			hql.append(" AND model LIKE ?");
			params.add(terminalInfo.getModel() + "%");
		}
		// 添加查询条件3
		if(timeParams.get("castTimeBegin")!=null){
			hql.append(" and castTime>=?");
			params.add(timeParams.get("castTimeBegin"));
		}
		if(timeParams.get("castTimeEnd")!=null){
			hql.append(" and castTime<=?");
			params.add(timeParams.get("castTimeEnd"));
		}
		// 添加查询条件4
		if (StringUtil.isNotBlank(terminalInfo.getTerminalState())) {

			hql.append(" AND terminalState = ?");
			params.add(terminalInfo.getTerminalState());
		}	
		System.out.println(GlobalContext.getCurrentUser().getOrgIds());
		// 添加条件5
		if (StringUtil.isNotBlank(terminalInfo.getOrgIdForSearch())) {
			long orgId = Long.parseLong(terminalInfo.getOrgIdForSearch());
			hql.append(" AND org.id = ? " );
			params.add(orgId);
			
		}else if(StringUtils.isNotBlank(GlobalContext.getCurrentUser().getOrgIds())){
			hql.append(" and t.org.id in (" +GlobalContext.getCurrentUser().getOrgIds()+ " )");

		}
		if(terminalInfo.getOrgId()!=null){
			hql.append(" and t.org.id =? ");
			params.add(Long.valueOf(terminalInfo.getOrgId()));
		}else{
			//数据权限
			if(StringUtils.isNotBlank(GlobalContext.getCurrentUser().getOrgIds())){
				hql.append(" and t.org.id in (" +GlobalContext.getCurrentUser().getOrgIds()+ " )");
			}
		}

		String totalHql = "select count(*) " + hql.toString();
		dataGridJson.setTotal(terminalInfoDao.count(totalHql, params));
		// 设置排序（默认排序）
		if (dataGridBean.getSort() != null) {
			hql.append(" order by " + dataGridBean.getSort() + " "
					+ dataGridBean.getOrder());
		} else {
			hql.append(" order by id asc ");
		}
		List<TerminalInfo> list = terminalInfoDao.find(hql.toString(),
				dataGridBean.getPage(), dataGridBean.getRows(), params);
		dataGridJson.setRows(list);
		return dataGridJson;
	}

	/**
	 * 增加或修改terminalInfo
	 * params：terminalInfo
	 */
	@Override
	public Boolean addOrUpdate(TerminalInfo terminalInfo) {
		boolean flag=false;
		String time=terminalInfo.getCastTime1();
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		 try {
			ts = Timestamp.valueOf(time);//将time转型为Timestamp格式
		} catch (Exception e) {
			e.printStackTrace();
		} 
		terminalInfo.setCastTime(ts);
		if(terminalInfo.getId()==null){//添加
			//设置通用信息
			GeneralMethod.setRecordInfo(terminalInfo, true);
			Organization org1=organizationDao.get(Organization.class, terminalInfo.getOrgId());
			terminalInfo.setOrg(org1);
			//保存该条数据
			terminalInfoDao.save(terminalInfo);
			TerminalUseInfo terminalUseInfo=new TerminalUseInfo();
			terminalUseInfo.setTerminalCode(terminalInfo.getCode());
			terminalUseInfo.setUseTime(terminalInfo.getCastTime());
			terminalUseInfo.setOrg(terminalInfo.getOrg());
			terminalUseInfo.setTerminalState(terminalInfo.getTerminalState());
			GeneralMethod.setRecordInfo(terminalUseInfo, true);
			DateUtil util=new DateUtil();
			Timestamp times=new Timestamp(terminalUseInfo.getCreateTime().getTime());
			terminalUseInfo.setCreatTime(times);
			terminalUseInfoDao.save(terminalUseInfo);
			flag=true;
		}else{//修改
			TerminalInfo newTerminalInfo=terminalInfoDao.get(TerminalInfo.class,terminalInfo.getId());
			if(newTerminalInfo==null){	
				return false;
			}
			YpBeanUtils.copyPropertiesIgnoreNull(terminalInfo,newTerminalInfo);
			Organization org1=organizationDao.get(Organization.class, terminalInfo.getOrgId());
			newTerminalInfo.setOrg(org1);
			GeneralMethod.setRecordInfo(newTerminalInfo, false);
			flag=true;
		}
		return flag;
	}

	/**
	 * 删除terminalInfo
	 * params ：ids；
	 */
	@Override
	public Boolean deleteTerminalInfoById(String[] ids) {
		if(ids==null||ids.length<1){
			return false;
		}
		TerminalInfo newTerminalInfo=null;
		for(String id : ids){
			Long idLong=Long.parseLong(id);
			newTerminalInfo=terminalInfoDao.get(TerminalInfo.class, idLong);
			if(newTerminalInfo==null){
				return false;
			}
			newTerminalInfo.setRecordStatus(GlobalConstant.FLAG_N);
			//设置通用信息
			GeneralMethod.setRecordInfo(newTerminalInfo, false);

		}
		return true;
	}

	/**
	 * 根据code查询terminalInfo
	 * params：code
	 */
	@Override
	public Boolean findTerminalByCode(String code) {
		StringBuffer hql=new StringBuffer();
		hql.append("from TerminalInfo t where code=?");
		List<Object> params=new ArrayList<Object>();
		params.add(code);
		TerminalInfo terminalInfo=this.terminalInfoDao.get(hql.toString(), params);
		if(terminalInfo==null){
			return false;
		}else{
			return true;
		}
	}

}
