package com.yp.sys.service.terminal.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yp.sys.common.GlobalConstant;
import com.yp.sys.common.GlobalContext;
import com.yp.sys.dao.organization.IOrganizationDao;
import com.yp.sys.dao.terminal.ITerminalInfoDao;
import com.yp.sys.dao.terminal.ITerminalUseInfoDao;
import com.yp.sys.dao.terminal.ITerminalUserDao;
import com.yp.sys.entity.organization.Organization;
import com.yp.sys.entity.terminal.TerminalInfo;
import com.yp.sys.entity.terminal.TerminalUseInfo;
import com.yp.sys.entity.terminal.TerminalUser;
import com.yp.sys.pojo.DataGridBean;
import com.yp.sys.pojo.DataGridJson;
import com.yp.sys.pojo.UserBean;
import com.yp.sys.service.common.impl.BaseServiceImpl;
import com.yp.sys.service.terminal.ITerminalUseInfoService;
import com.yp.sys.util.StringUtil;
import com.yp.sys.util.YpBeanUtils;

/**
 * 内容摘要： 终端使用情况service层实现层 创建人： nihui 创建日期： 2016-11-16 版本号： v1.0.0 公 司：重邮汇测
 * 版权所有： (C)2001-2015 修改记录 修改日期： 版本号： 修改人： 修改内容：
 * 
 */
@Service("terminalUseInfoService")
public class TerminalUseInfoServiceImpl extends
		BaseServiceImpl<TerminalUseInfo> implements ITerminalUseInfoService {
	@Autowired
	private ITerminalUseInfoDao terminalUseInfoDao;
	@Autowired
	private IOrganizationDao organizationDao;
	@Autowired
	private ITerminalInfoDao terminalInfoDao;
	@Autowired
	private ITerminalUserDao terminalUserDao;

	/**
	 * 分页查询terminalUseInfo params:dataGridBean; params:terminalUseInfo;
	 * params:timeParams
	 */
	@Override
	public DataGridJson datagrid(DataGridBean dataGridBean,
			TerminalUseInfo terminalUseInfo, Map<String, Date> timeParams) {
		DataGridJson dataGridJson = new DataGridJson();
		StringBuffer hql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		hql.append(" from TerminalUseInfo t where recordStatus=?");
		params.add(GlobalConstant.FLAG_Y);
		// 添加条件1
		if (StringUtil.isNotBlank(terminalUseInfo.getTerminalUserName())) {
			hql.append(" AND terminalUserName LIKE ?");
			params.add("%" + terminalUseInfo.getTerminalUserName() + "%");
		}
		// 添加条件2
		if (StringUtil.isNotBlank(terminalUseInfo.getTerminalCode())) {
			hql.append(" AND terminalCode LIKE ?");
			params.add(terminalUseInfo.getTerminalCode() + "%");
		}
		// 添加条件3
		if (StringUtil.isNotBlank(terminalUseInfo.getTerminalState())
				&& !"null".equals(terminalUseInfo.getTerminalState())) {
			hql.append(" AND terminalState = ? ");
			params.add(terminalUseInfo.getTerminalState());
		}
		// 添加条件4
		if (timeParams.get("backTimeBegin") != null) {
			hql.append(" and backTime>=? ");
			params.add(timeParams.get("backTimeBegin"));
		}
		if (timeParams.get("backTimeEnd") != null) {
			hql.append(" and backTime<=?  ");
			params.add(timeParams.get("backTimeEnd"));
		}
		// 添加条件5
		if (StringUtil.isNotBlank(terminalUseInfo.getUseAdress())) {
			hql.append(" AND useAdress LIKE ?");
			params.add(terminalUseInfo.getUseAdress() + "%");
		}
		// 添加条件6
		if (StringUtil.isNotBlank(terminalUseInfo.getUseAreaName())) {
			hql.append(" AND useAreaName LIKE ?");
			params.add(terminalUseInfo.getUseAreaName() + "%");
		}
		// 添加条件7
		if (StringUtil.isNotBlank(terminalUseInfo.getOrgIdForSearch())) {
			long orgId = Long.parseLong(terminalUseInfo.getOrgIdForSearch());
			hql.append(" AND org.id = ? ");
			params.add(orgId);
		} else if (StringUtils.isNotBlank(GlobalContext.getCurrentUser()
				.getOrgIds())) {
			hql.append(" and t.org.id in ("
					+ GlobalContext.getCurrentUser().getOrgIds() + ") ");
		}
		String totalHql = "select count(*) " + hql.toString();
		dataGridJson.setTotal(terminalUseInfoDao.count(totalHql, params));
		// 设置排序（默认排序）
		if (dataGridBean.getSort() != null) {
			hql.append(" order by " + dataGridBean.getSort() + " "
					+ dataGridBean.getOrder());
		} else {
			hql.append(" order by t.creatTime desc ");
		}
		List<TerminalUseInfo> list = terminalUseInfoDao.find(hql.toString(),
				dataGridBean.getPage(), dataGridBean.getRows(), params);
		dataGridJson.setRows(list);
		return dataGridJson;
	}

	/**
	 * 指派TermninalUseInfo params:terminalUseInfo
	 */
	@Override
	public Boolean assign(TerminalUseInfo terminalUseInfo) {
		boolean flag = false;
		// String useTime1=terminalUseInfo.getUseTime1();借用时间为当前时间开始
		// Timestamp backTime=terminalUseInfo.getBackTime();
		Timestamp ts1 = new Timestamp(System.currentTimeMillis());
		// Timestamp ts2 = new Timestamp(System.currentTimeMillis());
		Timestamp ts3 = new Timestamp(System.currentTimeMillis());
		try {
			if (StringUtil.isNotBlank(terminalUseInfo.getBackTime1())) {
				terminalUseInfo.setBackTime(Timestamp.valueOf(terminalUseInfo
						.getBackTime1()));
			}
			// terminalUseInfo.setBackTime(terminalUseInfo.getBackTime1())
			// ts2= Timestamp.valueOf(backTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		terminalUseInfo.setUseTime(ts1);
		// terminalUseInfo.setBackTime(backTime);
		if (terminalUseInfo.getId() == null) {// 添加
			TerminalInfo terminalInfo = this.terminalInfoDao.get(
					TerminalInfo.class, terminalUseInfo.getTerminalId());
			if (terminalInfo != null) {
				TerminalUser terminalUser = this.terminalUserDao
						.get(TerminalUser.class,
								terminalUseInfo.getTerminalUserId());
				if ("3".equals(terminalUseInfo.getTerminalState())
						|| "4".equals(terminalUseInfo.getTerminalState())) {
					terminalInfo.setTerminalUser(terminalUser);
				} else if ("1".equals(terminalUseInfo.getTerminalState())
						|| "2".equals(terminalUseInfo.getTerminalState())) {
					terminalInfo.setTerminalUser(null);
				}
				TerminalInfo t = this.terminalInfoDao.get(TerminalInfo.class,
						terminalUseInfo.getTerminalId());
				t.setTerminalState(terminalUseInfo.getTerminalState());
				t.setTerminalUser(terminalUser);
				terminalInfoDao.save(t);
			}
			// 设置通用信息
			UserBean currUser = GlobalContext.getCurrentUser();
			terminalUseInfo.setCreateUserId(currUser.getId());
			terminalUseInfo.setRecordStatus(GlobalConstant.FLAG_Y);
			// terminalUseInfo.setBackTime(backTime);
			terminalUseInfo.setCreatTime(ts3);
			Organization org1 = organizationDao.get(Organization.class,
					terminalUseInfo.getOrgId());
			terminalUseInfo.setOrg(org1);
			// 保存该条数据
			terminalUseInfoDao.save(terminalUseInfo);
			flag = true;
		}

		return flag;
	}

	/**
	 * 根据id查询terminalUseInfo params：id
	 */
	@Override
	public TerminalUseInfo getTerminalUseInfo(Long id) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from TerminalUseInfo t ");
		hql.append(" where t.terminalId=? ");
		hql.append(" order by t.creatTime desc ");
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		List<TerminalUseInfo> terminalUseInfos = this.terminalUseInfoDao.find(
				hql.toString(), 1, 1, params);
		TerminalUseInfo terminalUseInfo = terminalUseInfos.get(0);
		return terminalUseInfo;
	}

	@Override
	public Boolean stopUseTerminalById(Long trminalId) {
		// 更改终端设备的信息
		Timestamp stampTime = new Timestamp(System.currentTimeMillis());
		TerminalInfo terminalInfo = this.terminalInfoDao.get(
				TerminalInfo.class, trminalId);
		terminalInfo.setTerminalUser(null);
		// terminalInfo.setOrg(null);
		terminalInfo.setTerminalState("1");
		terminalInfo.setModifyTime(stampTime);
		UserBean currUser = GlobalContext.getCurrentUser();
		terminalInfo.setModifyUserId(currUser.getId());
		terminalInfoDao.save(terminalInfo);

		// 日志信息的写入
		TerminalUseInfo terminalUseInfo = new TerminalUseInfo();
		terminalUseInfo.setTerminalCode(terminalInfo.getCode());
		terminalUseInfo.setTerminalId(terminalInfo.getId());
		terminalUseInfo.setCreatTime(stampTime);
		terminalUseInfo.setCreateUserId(currUser.getId());
		terminalUseInfo.setTerminalState("1");
		terminalUseInfo.setRecordStatus("Y");
		terminalUseInfo.setOrg(terminalInfo.getOrg());
		terminalUseInfo.setOrgId(terminalInfo.getOrg().getId());
		terminalUseInfoDao.save(terminalUseInfo);
		return true;
	}

	@Override
	public Boolean comeBackTerminalById(Long trminalId) {

		Timestamp stampTime = new Timestamp(System.currentTimeMillis());
		TerminalInfo terminalInfo = this.terminalInfoDao.get(
				TerminalInfo.class, trminalId);

		UserBean currUser = GlobalContext.getCurrentUser();
		// 日志信息的写入
		TerminalUseInfo terminalUseInfo = new TerminalUseInfo();
		terminalUseInfo.setTerminalCode(terminalInfo.getCode());
		terminalUseInfo.setTerminalId(terminalInfo.getId());
		terminalUseInfo.setCreatTime(stampTime);
		terminalUseInfo.setCreateUserId(currUser.getId());
		terminalUseInfo.setTerminalState("2");
		terminalUseInfo
				.setTerminalUserId(terminalInfo.getTerminalUser() != null ? terminalInfo
						.getTerminalUser().getId() : null);
		terminalUseInfo
				.setTerminalUserName(terminalInfo.getTerminalUser() != null ? terminalInfo
						.getTerminalUser().getName() : null);
		terminalUseInfo.setOrg(terminalInfo.getOrg() != null ? terminalInfo
				.getOrg() : null);
		terminalUseInfo.setOrgId(terminalInfo.getOrg() != null ? terminalInfo
				.getOrg().getId() : null);
		terminalUseInfo.setBackTime(stampTime);
		terminalUseInfo.setRecordStatus("Y");
		terminalUseInfoDao.save(terminalUseInfo);

		// 更改终端设备的信息
		terminalInfo.setTerminalUser(null);
		// terminalInfo.setOrg(null);
		terminalInfo.setTerminalState("2");
		terminalInfo.setModifyTime(stampTime);

		terminalInfo.setModifyUserId(currUser.getId());
		terminalInfoDao.save(terminalInfo);
		return true;
	}

}
