package com.yp.sys.service.rtu.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSON;
import com.yp.sys.common.GeneralMethod;
import com.yp.sys.common.GlobalContext;
import com.yp.sys.dao.common.IBaseDao;
import com.yp.sys.dao.rtu.IRtuBaseDao;
import com.yp.sys.dao.rtu.IRtuCollectInfoDao;
import com.yp.sys.dao.rtu.IRtuConfigInfoDao;
import com.yp.sys.dao.rtu.IRtuDao;
import com.yp.sys.dao.rtu.IRtuPropertyDao;
import com.yp.sys.dao.rtu.IRtuPropertyReferDao;
import com.yp.sys.dao.rtu.IRtuReferDao;
import com.yp.sys.dao.rtu.IRtuSlaveBaseInfoDao;
import com.yp.sys.dao.rtu.IRtuSlaveInfoDao;
import com.yp.sys.dao.rtu.IRtuSlaveProertyDao;
import com.yp.sys.dao.rtu.IRtuTranslateProertyDao;
import com.yp.sys.dao.rtu.ISlaveInfoDao;
import com.yp.sys.dao.user.IUserDao;
import com.yp.sys.entity.User;
import com.yp.sys.entity.rtu.RtuBaseInfo;
import com.yp.sys.entity.rtu.RtuCollectInfo;
import com.yp.sys.entity.rtu.RtuConfigInfo;
import com.yp.sys.entity.rtu.RtuInfo;
import com.yp.sys.entity.rtu.RtuProperty;
import com.yp.sys.entity.rtu.RtuPropertyRefer;
import com.yp.sys.entity.rtu.RtuRefer;
import com.yp.sys.entity.rtu.RtuSlaveInfo;
import com.yp.sys.entity.rtu.RtuSlaveProerty;
import com.yp.sys.entity.rtu.RtuTranslateProerty;
import com.yp.sys.entity.rtu.SlaveInfo;
import com.yp.sys.pojo.DataGridBean;
import com.yp.sys.pojo.DataGridJson;
import com.yp.sys.pojo.RtuBaseInfoBean;
import com.yp.sys.pojo.RtuCollectInfoBean;
import com.yp.sys.pojo.RtuCollectInfoPojo;
import com.yp.sys.pojo.RtuConfigInfoBean;
import com.yp.sys.pojo.RtuInfoBean;
import com.yp.sys.pojo.RtuPropertyBean;
import com.yp.sys.pojo.RtuSlaveInfoBean;
import com.yp.sys.pojo.RtuTranslateProertyBean;
import com.yp.sys.pojo.SlaveInfoBean;
import com.yp.sys.pojo.UserBean;
import com.yp.sys.pojo.ZtreeBean;
import com.yp.sys.service.common.impl.BaseServiceImpl;
import com.yp.sys.service.rtu.IRtuService;
import com.yp.sys.util.DateUtil;
import com.yp.sys.util.ManageCache;
import com.yp.sys.util.YpBeanUtils;

@Service("rtuService")
public class RtuServiceImpl extends BaseServiceImpl<RtuInfo> implements
		IRtuService {
	@Autowired
	private IRtuDao rtuDao;
	@Autowired
	private IRtuBaseDao rtuBaseDao;// RTU基础信息dao
	@Autowired
	private IRtuCollectInfoDao rtuCollectInfoDao;// rtu采集信息dao
	@Autowired
	private IRtuConfigInfoDao rtuConfigInfoDao;// rtu配置信息dao
	@Autowired
	private IBaseDao<Object[]> baseDao;// 基础dao
	@Autowired
	private IRtuPropertyDao rtuPropertyDao;// RTU模板属性Dao
	@Autowired
	private IRtuReferDao rtuReferDao;// RTU模板属性关联表
	@Autowired
	private IRtuSlaveProertyDao rtuSlaveProertyDao;// 采集器属性保存
	@Autowired
	private IRtuSlaveInfoDao rtuSlaveInfoDao;// 采集器属性dao《中间表、临时的关系表》
	@Autowired
	private IRtuTranslateProertyDao rtuTranProertyDao;// 传输属性到
	@Autowired
	private IUserDao userDao;//用户Dao
	@Autowired
	private IRtuSlaveBaseInfoDao rtuSlaveBaseInfoDao;//采集器器基础信息Dao
	@Autowired
	private ISlaveInfoDao slaveInfoDao;
	@Autowired
	private IRtuPropertyReferDao referDao;
	@Override
	public DataGridJson rtuInfoDataGrid(DataGridBean dg, RtuInfoBean rtuInfoBean) {
		String hql = " from RtuInfo t ";
		DataGridJson j = new DataGridJson();
		List<Object> values = new ArrayList<Object>();

		if (rtuInfoBean != null) {
			if (rtuInfoBean.getLongVal() != null) {
				hql += " and t.baselineLength <= ? ";
				values.add(rtuInfoBean.getLongVal());
			}
			if (rtuInfoBean.getShortVal() != null) {
				hql += " and t.baselineLength >= ? ";
				values.add(rtuInfoBean.getShortVal());
			}

		}
		// 数据权限
		/*
		 * if(GlobalContext.getCurrentUser() != null &&
		 * StringUtils.isNotBlank(GlobalContext.getCurrentUser().getOrgIds())){
		 * hql
		 * +=" and t.workOrder.org.id in ("+GlobalContext.getCurrentUser().getOrgIds
		 * ()+") "; }
		 */
		hql = hql.replaceFirst("and", " where ");
		String totalHql = " select count(*) " + hql;

		j.setTotal(rtuDao.count(totalHql, values));// 设置总记录数
		if (dg.getSort() != null) {// 设置排序
			hql += " order by " + dg.getSort() + " " + dg.getOrder();
		}

		List<RtuInfo> rtuInfoList = rtuDao.find(hql, dg.getPage(),
				dg.getRows(), values);// 查询分页

		j.setRows(rtuInfoList);

		return j;
	}

	@Override
	public List<SlaveInfo> slaveInfoDataGrid(SlaveInfoBean slaveInfoBean) {
		DataGridJson j = new DataGridJson();
		String hql = " from SlaveInfo t ";
		List<Object> values = new ArrayList<Object>();
		if (slaveInfoBean != null) {
			if (slaveInfoBean.getConfigId() != null) {
				hql += " and t.configId = ? ";
				values.add(slaveInfoBean.getConfigId());
			}
		}
		hql = hql.replaceFirst("and", " where ");
		List<SlaveInfo> rtuInfoList = rtuSlaveInfoDao.find(hql, values);// 查询分页
		return rtuInfoList;
	}

	@Override
	public DataGridJson rtuBaseDataGrid(DataGridBean dg,
			RtuBaseInfoBean rtuBaseInfoBean) {
		String hql = " from RtuBaseInfo t ";
		DataGridJson j = new DataGridJson();
		List<Object> values = new ArrayList<Object>();

		if (rtuBaseInfoBean != null) {
			if (StringUtils.isNotBlank(rtuBaseInfoBean.getRtuAdress())) {
				hql += " and t.rtuAdress = ? ";
				values.add(rtuBaseInfoBean.getRtuAdress());
			}
			if (StringUtils.isNotBlank(rtuBaseInfoBean.getRtuName())) {
				hql += " and t.rtuName = ? ";
				values.add(rtuBaseInfoBean.getRtuName());
			}

		}
		// 数据权限
		/*
		 * if(GlobalContext.getCurrentUser() != null &&
		 * StringUtils.isNotBlank(GlobalContext.getCurrentUser().getOrgIds())){
		 * hql
		 * +=" and t.workOrder.org.id in ("+GlobalContext.getCurrentUser().getOrgIds
		 * ()+") "; }
		 */
		hql = hql.replaceFirst("and", " where ");
		String totalHql = " select count(*) " + hql;

		j.setTotal(rtuBaseDao.count(totalHql, values));// 设置总记录数
		if (dg.getSort() != null) {// 设置排序
			hql += " order by " + dg.getSort() + " " + dg.getOrder();
		}

		List<RtuBaseInfo> rtuBaseInfoList = rtuBaseDao.find(hql, dg.getPage(),
				dg.getRows(), values);// 查询分页

		j.setRows(rtuBaseInfoList);

		return j;
	}

	@Override
	public DataGridJson rtuCollectInfoDataGrid(DataGridBean dg,
			RtuCollectInfoBean rtuCollectInfoBean) {
		String hql = " from RtuCollectInfo t ";
		DataGridJson j = new DataGridJson();
		List<Object> values = new ArrayList<Object>();

		if (rtuCollectInfoBean != null) {
			if (rtuCollectInfoBean.getRtuId() != null) {
				hql += " and t.rtu.id = ? ";
				values.add(rtuCollectInfoBean.getRtuId());
			}
			if (rtuCollectInfoBean.getRtuConfigId() != null) {
				hql += " and t.config.cid = ? ";
				values.add(rtuCollectInfoBean.getRtuConfigId());
			}
			if (rtuCollectInfoBean.getSid() != null) {
				hql += " and t.sid = ? ";
				values.add(rtuCollectInfoBean.getSid());
			}
			if(StringUtils.isNotBlank(rtuCollectInfoBean.getRtuName())){
				hql += " and t.rtu.rtuName = ? ";
				values.add(rtuCollectInfoBean.getRtuName());
			}
			if(StringUtils.isNotBlank(rtuCollectInfoBean.getRtuAdress())){
				hql += " and t.rtu.rtuAdress = ? ";
				values.add(rtuCollectInfoBean.getRtuAdress());
			}
			if(StringUtils.isNotBlank(rtuCollectInfoBean.getUploadTimeStart())){
				
				hql += " and t.uploadTime >= ? ";
				values.add(Timestamp.valueOf(rtuCollectInfoBean.getUploadTimeStart()));
			}
			if(StringUtils.isNotBlank(rtuCollectInfoBean.getUploadTimeEnd())){
				hql += " and t.uploadTime <= ? ";
				values.add(Timestamp.valueOf(rtuCollectInfoBean.getUploadTimeEnd()));
			}
		}
		// 数据权限
		/*
		 * if(GlobalContext.getCurrentUser() != null &&
		 * StringUtils.isNotBlank(GlobalContext.getCurrentUser().getOrgIds())){
		 * hql
		 * +=" and t.workOrder.org.id in ("+GlobalContext.getCurrentUser().getOrgIds
		 * ()+") "; }
		 */
		hql = hql.replaceFirst("and", " where ");
		String totalHql = " select count(*) " + hql;

		j.setTotal(rtuCollectInfoDao.count(totalHql, values));// 设置总记录数
		if (dg.getSort() != null) {// 设置排序
			hql += " order by " + dg.getSort() + " " + dg.getOrder();
		}else{
			//默认按照时间降序排序
			hql += " order by t.uploadTime desc ";
		}

		List<RtuCollectInfo> rtuBaseInfoList = rtuCollectInfoDao.find(hql,
				dg.getPage(), dg.getRows(), values);// 查询分页

		j.setRows(rtuBaseInfoList);
		return j;
	}

	@Override
	public DataGridJson rtuConfigDataGrid(DataGridBean dg,
			RtuConfigInfoBean rtuConfigBean) {
		String hql = " from RtuConfigInfo t ";
		DataGridJson j = new DataGridJson();
		List<Object> values = new ArrayList<Object>();

		if (rtuConfigBean != null) {
			if (rtuConfigBean.getRtuId() != null) {
				hql += " and t.rtu.id = ? ";
				values.add(rtuConfigBean.getRtuId());
			}
			if (StringUtils.isNotBlank(rtuConfigBean.getCreateTimeStart())) {
				hql += " and t.createTime >= ? ";
				values.add(DateUtil.format(rtuConfigBean.getCreateTimeStart(),
						DateUtil.Y_M_D_HMS));
			}
			if (StringUtils.isNotBlank(rtuConfigBean.getCreateTimeEnd())) {
				hql += " and t.createTime <= ? ";
				values.add(DateUtil.format(rtuConfigBean.getCreateTimeEnd(),
						DateUtil.Y_M_D_HMS));
			}
		}
		// 数据权限
		/*
		 * if(GlobalContext.getCurrentUser() != null &&
		 * StringUtils.isNotBlank(GlobalContext.getCurrentUser().getOrgIds())){
		 * hql
		 * +=" and t.workOrder.org.id in ("+GlobalContext.getCurrentUser().getOrgIds
		 * ()+") "; }
		 */
		hql = hql.replaceFirst("and", " where ");
		String totalHql = " select count(*) " + hql;
		j.setTotal(rtuConfigInfoDao.count(totalHql, values));// 设置总记录数
		if (dg.getSort() != null) {// 设置排序
			hql += " order by " + dg.getSort() + " " + dg.getOrder();
		}else{
			hql+=" order by t.createTime desc ";
		}
		List<RtuConfigInfo> rtuBaseInfoList = rtuConfigInfoDao.find(hql,
				dg.getPage(), dg.getRows(), values);// 查询分页

		j.setRows(rtuBaseInfoList);
		return j;
	}

	/**
	 * 处理JSON数组对象
	 * 
	 * @param jsonText
	 * @return List<RtuCollectInfoPojo>
	 */
	public List<RtuCollectInfoPojo> changeJsonToList(Long collectId) {

		RtuCollectInfo collectInfo = rtuCollectInfoDao.get(
				RtuCollectInfo.class, collectId);
		List<RtuCollectInfoPojo> list = new ArrayList<RtuCollectInfoPojo>();
		if (collectInfo != null
				&& StringUtils.isNotBlank(collectInfo.getContent())) {
			String jsonText = collectInfo.getContent();
			// ManageCache.rtuPopertyReferMap=
			if (StringUtils.isNotBlank(jsonText)) {
				JSONArray array = JSONArray.fromObject(jsonText);
				if (array != null && array.size() > 0) {
					for (Object obj : array) {
						if (obj != null) {
							RtuCollectInfoPojo colectPojo = new RtuCollectInfoPojo();
							Map<String, Object> map = (Map<String, Object>) obj;
							if (map.get("regId") != null) {
								colectPojo
										.setRegId(map.get("regId").toString());
								colectPojo
										.setRegName(ManageCache.rtuPopertyReferMap
												.get(map.get("regId")
														.toString()));
							}
							// colectPojo.setRegName(regName);
							colectPojo.setRegState(StringUtils.isNotBlank(map
									.get("regState").toString()) ? map.get(
									"regState").toString() : "");
							colectPojo.setRegValue(StringUtils.isNotBlank(map
									.get("regValue").toString()) ? map.get(
									"regValue").toString() : "");
							list.add(colectPojo);
						}
					}
				}
			}
		}

		return list;
	}

	// 查询模组属性码表
	@Override
	public Map<String, String> queryRtuPopertyReferMap() {
		String sql = "select u.property_key,u.property_text from t_cyhc_rtu_property_refer u ";
		Map<String, String> map = new HashMap<String, String>();
		List<Object[]> list = baseDao.findBySql(sql);
		if (list != null && list.size() > 0) {
			for (Object[] arr : list) {
				if (arr != null && arr.length > 0) {
					map.put(arr[0].toString(), arr[1].toString());
				}
			}
		}

		return map;
	}

	@Override
	public List<ZtreeBean> buildRtuModelZtree(Long pid) {
		List<ZtreeBean> result=new ArrayList<ZtreeBean>();
		String hql = " from RtuProperty t where t.pid=? order by t.propOrder";
		List<Object> params = new ArrayList<Object>();
		params.add(pid);
		List<RtuProperty> lists = rtuPropertyDao.find(hql,params);
		List<RtuRefer> rrList=queryAllRegPropertyList();
		if(null!=lists && lists.size()>0){
			RtuProperty rp=rtuPropertyDao.get(RtuProperty.class, pid);
			String hql2="from RtuProperty t  order by t.propOrder";
			List<RtuProperty> lists2=rtuPropertyDao.find(hql2);
			for(RtuProperty rtu:lists){
				ZtreeBean node=buildTree(rtu,true,rrList,lists2);
				if ("Y".equals(rtu.getIsRelateRefer())) {
					List<RtuRefer> reList= new ArrayList<RtuRefer>();
					if(null!=rrList && rrList.size()>0){
						for(RtuRefer rr:rrList){
							if(null!=rr.getPropertyId() && rr.getPropertyId().longValue()==rtu.getId().longValue()){
								reList.add(rr);
							}
						}
					}
					rtu.setListRtuRefer(reList);
				}
				node.setPname(rp.getPropDesc());
				node.setBasicInfo(rtu);
				if(null!=node){
					result.add(node);
				}
			}
		}
		return result;
	}
	private ZtreeBean buildTree(RtuProperty rtu,boolean recursive,List<RtuRefer> rrList,List<RtuProperty> lists2){
		ZtreeBean node = new ZtreeBean();
		if(StringUtils.isBlank(rtu.getPropKey())){
			node.setAddFlag("1");
		}else{
			node.setAddFlag("2");
		}
		node.setId(rtu.getId()+"");   	 
		node.setName(rtu.getPropDesc());
		node.setPid(rtu.getPid()+"");
		List<RtuProperty> childList = new ArrayList<RtuProperty>();
//		String hql=" from RtuProperty t where t.pid=? order by t.propOrder";
//		List<Object> param = new ArrayList<Object>();
//		param.add(rtu.getId());
//		List<RtuProperty> childList = this.rtuPropertyDao.find(hql, param);
		if(null!=lists2 && lists2.size()>0){
			for(int m=0;m<lists2.size();m++){
				if(lists2.get(m).getPid().longValue()==rtu.getId().longValue()){
					childList.add(lists2.get(m));
				}
			}
		}
		if(childList!=null&&childList.size()>0){
			// node.setState("closed");//关闭节点 	
			if(recursive){	
				List<ZtreeBean> children = new ArrayList<ZtreeBean>();   			 
				for (RtuProperty temp : childList){
					ZtreeBean cnode= buildTree(temp,true,rrList,lists2);
					cnode.setPname(rtu.getPropDesc());
					if ("Y".equals(temp.getIsRelateRefer())) {
						List<RtuRefer> reList= new ArrayList<RtuRefer>();
						if(null!=rrList && rrList.size()>0){
							for(RtuRefer rr:rrList){
								if(null!=rr.getPropertyId() && rr.getPropertyId().longValue()==temp.getId().longValue()){
									reList.add(rr);
								}
							}
						}
						temp.setListRtuRefer(reList);
					}
					cnode.setBasicInfo(temp);
					if(cnode!=null){
						children.add(cnode);
					}	
				}
				node.setState("open"); 
				node.setChildren(children);
			}   		 
		}    	 
		return node;
	}
	@Override
	public List<ZtreeBean> createRtuModelZtree(Long pid) {
		String hql = " from RtuProperty t where  t.pid="
				+ pid+" order by t.propOrder ";
		List<RtuProperty> list = rtuPropertyDao.find(hql);
		List<ZtreeBean> listEnd = new ArrayList<ZtreeBean>();
		if (list != null && list.size() > 0) {
			for (RtuProperty rtuProperty : list) {
				ZtreeBean zTreeBean = new ZtreeBean();
				zTreeBean.setId(rtuProperty.getId().toString());
				zTreeBean.setName(rtuProperty.getPropDesc());
				zTreeBean.setPid(rtuProperty.getPid().toString());
				if (StringUtils.isNotBlank(rtuProperty.getPropKey())) {
					//zTreeBean.setChildren(loadChildZtree(rtuProperty.getId()));
					List<ZtreeBean> children=loadChildZtree(rtuProperty.getId());
					if(null!=children && children.size()>0){
						zTreeBean.setChildren(children);
					}
				}

				listEnd.add(zTreeBean);
			}
		}

		return listEnd;
	}
	
	private List<ZtreeBean> loadChildZtree(Long pid) {
		String hql = " from RtuProperty t where  t.pid="
				+ pid;
		List<RtuProperty> list = rtuPropertyDao.find(hql);
		List<ZtreeBean> listEnd = new ArrayList<ZtreeBean>();
		if (list != null && list.size() > 0) {
			for (RtuProperty rtuProperty : list) {
				ZtreeBean zTreeBean = new ZtreeBean();
				zTreeBean.setId(rtuProperty.getId().toString());
				zTreeBean.setName(rtuProperty.getPropDesc());
				zTreeBean.setPid(rtuProperty.getPid().toString());
				if (StringUtils.isNotBlank(rtuProperty.getPropKey())) {
					//zTreeBean.setChildren(loadChildZtree(rtuProperty.getId()));
					List<ZtreeBean> children=loadChildZtree(rtuProperty.getId());
					if(null!=children && children.size()>0){
						zTreeBean.setChildren(children);
					}
				}
				listEnd.add(zTreeBean);
			}
		}
		return listEnd;
	}
	@Override
	public List<ZtreeBean> editRtuModelZtree(Long pid,String slaveId) {
		List<ZtreeBean> initList=this.createRtuModelZtree(pid);
		List<Object> params = new ArrayList<Object>(); 
		String hql=" from RtuSlaveProerty t where t.slaveId=?";
		params.add(Long.valueOf(slaveId));
		List<RtuSlaveProerty> slaveList=rtuSlaveProertyDao.find(hql, params);
		if(null!=slaveList && slaveList.size()>0){
			for(RtuSlaveProerty slave:slaveList){
				eeee(slave, initList);
			}
		}
		return initList;
	}
	/**
	 * 给左侧导航栏填充保存的值
	 * @param p
	 * @param lists
	 */
	private void eeee(RtuSlaveProerty p,List<ZtreeBean> lists){
		for (int j = 0; j < lists.size(); j++) {
			ZtreeBean ztb = lists.get(j);
			ztb.setSlaveId(p.getSlaveId()+"");
			if (ztb.getId().equals(p.getTreeId()+"")) {
				ztb.setChecked(true);
				return;
			}else{
				if(ztb.getChildren()!=null && ztb.getChildren().size()>0){
					ztb.setState("open");
					boolean flag=true;
					for(ZtreeBean cztb:ztb.getChildren()){
						if(cztb.getChecked()==false){
							flag=false;
							break;
						}
					}
					if(flag){
						ztb.setChecked(true);
					}
					eeee(p, ztb.getChildren());
				}
			}
		}
	}
	@Override
	public List<RtuProperty> querySlaveBaseProperty(Long pid) {
		String hql = "from RtuProperty t where t.pid=" + pid;
		List<RtuProperty> list = rtuPropertyDao.find(hql);
		if (list != null && list.size() > 0) {
			for (int u = 0; u < list.size(); u++) {
				
				if ("Y".equals(list.get(u).getIsRelateRefer())) {
					list.get(u).setListRtuRefer(
							queryRegPropertyList(list.get(u).getId()));
				}
			}
		}
		return list;
	}

	@Override
	public List<RtuProperty> editSlaveBaseProperty(Long pid,Long slaveId) {
		List<RtuProperty> result = new ArrayList<RtuProperty>();
		String hql = "from RtuProperty t where t.pid=?";
		List<Object> param= new ArrayList<Object>();
		param.add(pid);
		List<RtuProperty> list = rtuPropertyDao.find(hql,param);
		if (list != null && list.size() > 0) {
			for (int u = 0; u < list.size(); u++) {
				if ("Y".equals(list.get(u).getIsRelateRefer())) {
					List<RtuRefer> rrList=queryRegPropertyList(list.get(u).getId());
					List<RtuRefer> rrList2=new ArrayList<RtuRefer>();
					if(null!=rrList && rrList.size()>0){
						String hql3=" from RtuSlaveProerty s where s.treeId=? " +
								" and  s.propertyKey=? and s.slaveId=? ";
						List<Object> param3= new ArrayList<Object>();
						param3.add(pid);
						param3.add(list.get(u).getPropKey());
						param3.add(slaveId);
						List<RtuSlaveProerty> rspList2=rtuSlaveProertyDao.find(hql3,param3);
						for(RtuRefer rr:rrList){
							if(null!=rspList2&& rspList2.size()>0){
								for(RtuSlaveProerty rsp:rspList2){
									if(rr.getReferKey().equals(rsp.getPropertyVal())){
										if(rsp.getCheckType()==1){
											rr.setFlag(1l);
										}else{
											rr.setFlag(2l);
										}
										break;
									}
								}
							}else{
								rr.setFlag(2l);
							}
							rrList2.add(rr);
						}
					}
					list.get(u).setListRtuRefer(rrList2);
				}
			}
			String hql2=" from  RtuSlaveProerty l where (l.treePid=? or l.treeId=?) and l.slaveId=?";
			List<Object> params=new ArrayList<Object>();
			params.add(pid);
			params.add(pid);
			params.add(slaveId);
			List<RtuSlaveProerty> rspList = rtuSlaveProertyDao.find(hql2,params);
			for(RtuProperty rp:list){
				if(null!=rspList && rspList.size()>0){
					for(RtuSlaveProerty rsp: rspList){
						if(rp.getPropKey().equals(rsp.getPropertyKey())){
							rp.setPropDefault(rsp.getPropertyVal());
							break;
						}
					}
				}
				result.add(rp);
			}
		}
		return result;
	}
	@Override
	public List<RtuProperty> querySlaveBasePropertyById(Long id) {
		String hql = "from RtuProperty t where t.id=" + id;
		List<RtuProperty> list = rtuPropertyDao.find(hql);
		if (list != null && list.size() > 0) {
			for (int u = 0; u < list.size(); u++) {
				if ("Y".equals(list.get(u).getIsRelateRefer())) {
					list.get(u).setListRtuRefer(
							queryRegPropertyList(list.get(u).getId()));
				}
			}
		}
		return list;
	}
	@Override
	public List<RtuProperty> editSlaveBasePropertyById(Long id,Long slaveId) {
		List<RtuProperty> result = new ArrayList<RtuProperty>();
		String hql = "from RtuProperty t where t.id=?";
		List<Object> params=new ArrayList<Object>();
		params.add(id);
		List<RtuProperty> list = rtuPropertyDao.find(hql,params);
		if (list != null && list.size() > 0) {
			for (int u = 0; u < list.size(); u++) {
				if ("Y".equals(list.get(u).getIsRelateRefer())) {
					List<RtuRefer> rrList=queryRegPropertyList(list.get(u).getId());
					if(null!=rrList && rrList.size()>0){
						
					}
					list.get(u).setListRtuRefer(rrList);
				}
			}
			String hql2=" from  RtuSlaveProerty l where l.treeId=? and l.slaveId=?";
			List<Object> param=new ArrayList<Object>();
			param.add(id);
			param.add(slaveId);
			List<RtuSlaveProerty> rspList = rtuSlaveProertyDao.find(hql2,param);
			if(null!=rspList && rspList.size()>0){
				for(RtuProperty rp:list){
					for(RtuSlaveProerty rsp: rspList){
						if(rp.getId().longValue()==rsp.getTreeId().longValue()){
							rp.setPropDefault(rsp.getPropertyVal());
							break;
						}
					}
					result.add(rp);
				}
			}
		}
		return result;
	}
	
	@Override
	public List<RtuRefer> queryRegPropertyList(Long propertyId) {
		
		String hql = " from RtuRefer t where t.propertyId=" + propertyId;
		List<RtuRefer> list = rtuReferDao.find(hql);
		return list;
	}
	private List<RtuRefer> queryAllRegPropertyList() {
		String hql = " from RtuRefer t order by propertyId " ;
		List<RtuRefer> list = rtuReferDao.find(hql);
		return list;
	}
	@Override
	public List<RtuRefer> editRegPropertyList(Long propertyId,Long slaveId) {
		String hql = " from RtuRefer t where t.propertyId=" + propertyId;
		List<RtuRefer> list = rtuReferDao.find(hql);
		return list;
	}
	@Override
	public SlaveInfo ajaxSaveSlave(String content) {
		SlaveInfo slaveInfo = new SlaveInfo();
		if (StringUtils.isNotBlank(content)) {
			JSONArray array = JSONArray.fromObject(content);
			List<RtuSlaveProerty> list = new ArrayList<RtuSlaveProerty>();
			if (array != null && array.size() > 0) {
				for (Object obj : array) {
					Map<String, Object> map = (Map<String, Object>) obj;
					if (map != null) {
						//System.out.println(map.get("proKey"));
						RtuSlaveProerty rtuSlaveProerty = new RtuSlaveProerty();

						rtuSlaveProerty.setPropertyKey(map.get("proKey")
								.toString());
						if (StringUtils.isNotBlank(map.get("pid").toString())) {
							rtuSlaveProerty.setTreePid(Long.valueOf(map.get(
									"pid").toString()));
						}
						rtuSlaveProerty.setPropertyName(map.get("proName")
								.toString());
						rtuSlaveProerty.setPropertyType(map.get("proType")
								.toString());
						rtuSlaveProerty.setPropertyVal(map.get("proVal")
								.toString());
						rtuSlaveProerty.setSlaveId(1L);
						rtuSlaveProerty.setTreeName(map.get("treeName")
								.toString());
						if (StringUtils
								.isNotBlank(map.get("treeId").toString())) {
							rtuSlaveProerty.setTreeId(Long.valueOf(map
									.get("treeId") + ""));
						}
						if (map.get("checkType") != null
								&& StringUtils.isNotBlank(map.get("checkType")
										.toString())) {
							if ("true".equals(map.get("checkType").toString())) {
								rtuSlaveProerty.setCheckType(1);// 1为选中
							} else {
								rtuSlaveProerty.setCheckType(2);// 2为不选中
							}
						}
						if (map.get("treeLevel") != null) {
							rtuSlaveProerty.setTreeLevel(Integer.valueOf(map
									.get("treeLevel").toString()));
							if (Integer
									.valueOf(map.get("treeLevel").toString()) == 0) {
								if (map.get("proKey") != null
										&& StringUtils.isNotBlank(map.get(
												"proKey").toString())) {
									if ("slave_index".equals(map.get("proKey")
											.toString())) {
										slaveInfo.setSlaveOrder(Integer
												.valueOf(map.get("proVal")
														.toString()));
									} else if ("slave_sid".equals(map.get(
											"proKey").toString())) {
										slaveInfo.setSlaveId(Integer
												.valueOf(map.get("proVal")
														.toString()));
									} else if ("slave_name".equals(map.get(
											"proKey").toString())) {
										slaveInfo.setSlaveName(map
												.get("proVal").toString());
									}
								}
							}
						}
						list.add(rtuSlaveProerty);
					}
				}
			}
			if (slaveInfo != null) {
				rtuSlaveInfoDao.save(slaveInfo);
				for (int i = 0; i < list.size(); i++) {
					list.get(i).setSlaveId(slaveInfo.getId());
				}
				rtuSlaveProertyDao.saveList(list);
			}

		}
		return slaveInfo;
	}

	private List<RtuTranslateProerty> analysisJson(String content, Long configId) {
		List<RtuTranslateProerty> list = new ArrayList<RtuTranslateProerty>();
		if (StringUtils.isNotBlank(content)) {
			JSONArray array = JSONArray.fromObject(content);

			if (array != null && array.size() > 0) {
				for (Object obj : array) {
					Map<String, Object> map = (Map<String, Object>) obj;
					if (map != null) {
						
						RtuTranslateProerty rtuSlaveProerty = new RtuTranslateProerty();
						
						rtuSlaveProerty.setTreeLevel(Integer.valueOf(map
								.get("treeLevel").toString()));
						
						rtuSlaveProerty.setPropertyKey(map.get("proKey")
								.toString());
						if (null!=map.get("pid") ) {
							rtuSlaveProerty.setTreePid(Long.valueOf(map.get(
									"pid").toString()));
						}
						rtuSlaveProerty.setPropertyName(map.get("proName")
								.toString());
						rtuSlaveProerty.setPropertyType(map.get("proType")
								.toString());
						rtuSlaveProerty.setPropertyVal(map.get("proVal")
								.toString());
						rtuSlaveProerty.setConfigId(configId);
						rtuSlaveProerty.setTreeName(map.get("treeName")
								.toString());
						if (StringUtils
								.isNotBlank(map.get("treeId").toString())) {
							rtuSlaveProerty.setTreeId(Long.valueOf(map
									.get("treeId") + ""));
						}
						if (map.get("checkType") != null
								&& StringUtils.isNotBlank(map.get("checkType")
										.toString())) {
							if ("true".equals(map.get("checkType").toString())) {
								rtuSlaveProerty.setCheckType(1);// 1为选中
							} else {
								rtuSlaveProerty.setCheckType(2);// 2为不选中
							}
						}

						list.add(rtuSlaveProerty);
					}
				}
			}

		}
		return list;
	}
	@Override
	public RtuConfigInfo addRtuConfig(String rtuId, String pubDelay,
			String heartDelay, String translateContent, String slaveIds) {
		RtuConfigInfo rtuConfigInfo = new RtuConfigInfo();
		rtuConfigInfo.setCreateTime(new Timestamp(new Date().getTime()));
		if (GlobalContext.getCurrentUser() != null) {
			User user = userDao.get(User.class, GlobalContext.getCurrentUser()
					.getId());
			if (user != null) {
				rtuConfigInfo.setCreateUser(user);
			}
		}
		if(StringUtils.isNotBlank(heartDelay)){
			rtuConfigInfo.setHeartDelay(Integer.valueOf(heartDelay));
		}
		if(StringUtils.isNotBlank(pubDelay)){
			rtuConfigInfo.setPubDelay(Integer.valueOf(pubDelay));
		}
		rtuConfigInfo.setConfigStatus(1);
		if (StringUtils.isNotBlank(rtuId)) {
			RtuBaseInfo rtuBaseInfo = rtuBaseDao.get(RtuBaseInfo.class,
					Long.valueOf(rtuId));
			if (rtuBaseInfo != null) {
				rtuConfigInfo.setRtu(rtuBaseInfo);
			}
		}
		rtuConfigInfoDao.save(rtuConfigInfo);
		
		RtuBaseInfo rtuBaseInfo= rtuConfigInfo.getRtu();
		if(rtuBaseInfo!=null){
			rtuBaseInfo.setcState(0);
			rtuBaseInfo.setcId(rtuConfigInfo.getCid());
			rtuBaseDao.update(rtuBaseInfo);
		}

		return rtuConfigInfo;
	}

	@Override
	public void saveListTranProperty(String translateContent,
			RtuConfigInfo rtuConfigInfo) {
		List<RtuTranslateProerty> listTranProperty = analysisJson(
				translateContent, rtuConfigInfo.getCid());
		rtuTranProertyDao.saveList(listTranProperty);
	}
	@Override
	public void updateListSlaveinfo(String slaveIds, RtuConfigInfo rtuConfigInfo) {
		
			String[] slaveArray = slaveIds.split(",");
			List<SlaveInfo> listSlaveinfo = new ArrayList<SlaveInfo>();
			for (int i = 0; i < slaveArray.length; i++) {
				SlaveInfo slaveInfo = rtuSlaveInfoDao.get(SlaveInfo.class,
						Long.valueOf(slaveArray[i]));
				if (slaveInfo != null) {
					slaveInfo.setConfigId(rtuConfigInfo.getCid());
					listSlaveinfo.add(slaveInfo);
					//rtuSlaveInfoDao.update(slaveInfo);
				}
			}
			rtuSlaveInfoDao.updateList(listSlaveinfo);
	}
	
	/**
	 * 根据配置ID进行json组装
	 * 
	 * @return String
	 */
	@Override
	public String makeUpJson(Long configId) {
		RtuConfigInfo rtuConfigInfo=rtuConfigInfoDao.get(RtuConfigInfo.class, configId);
		Map<String, Object> mapTopOne = new HashMap<String, Object>();// 第一层
		mapTopOne.put("conf_id", configId);
		mapTopOne.put("conf_pub_delay", rtuConfigInfo.getPubDelay());
		mapTopOne.put("conf_heart_delay",rtuConfigInfo.getHeartDelay());
		List<Map<String, Object>> listMapMiddleTwo = new ArrayList<Map<String, Object>>();// 第二层
		String slaveHql = " from  SlaveInfo t where t.configId=" + configId
				+ " order by t.slaveOrder ";
		List<SlaveInfo> listSlaveInfo = rtuSlaveInfoDao.find(slaveHql);
		if (listSlaveInfo != null && listSlaveInfo.size() > 0) {
			for (SlaveInfo slaveInfo : listSlaveInfo) {
				Map<String, Object> mapMiddleTwo= new HashMap<String, Object>();// 第2层 采集器信息
				String slaveProertyHqlL1 = " from RtuSlaveProerty u where u.slaveId="
						+ slaveInfo.getId() + " and u.treeLevel=0 ";
				List<RtuSlaveProerty> listRtuSlaveProerty = rtuSlaveProertyDao
						.find(slaveProertyHqlL1);
				if (listRtuSlaveProerty != null
						&& listRtuSlaveProerty.size() > 0) {
					for (RtuSlaveProerty rtuSlaveProerty : listRtuSlaveProerty) {
						mapMiddleTwo.put(rtuSlaveProerty.getPropertyKey(),
								changeDataType(rtuSlaveProerty.getPropertyVal()));
					}
				}
				//采集器自己的单个属性已组装完成，开始组装采集器数组信息
				List<Map<String, Object>> listMapMiddleThree = new ArrayList<Map<String, Object>>();
				String slaveProertyHql1= " from RtuSlaveProerty u where u.slaveId="
						+ slaveInfo.getId()
						+ " and u.treeLevel=1   group by u.treeId ";
				List<RtuSlaveProerty> listRtuSlaveProerty1 = rtuSlaveProertyDao
						.find(slaveProertyHql1);
				//属性类别有哪些
				if (listRtuSlaveProerty1 != null
						&& listRtuSlaveProerty1.size() > 0) {
					//遍历所有属性类别
					for (RtuSlaveProerty rtuSlaveProerty1 : listRtuSlaveProerty1) {
						Map<String, Object> mapMiddleThree = new HashMap<String, Object>();
						String slaveProertyHql2 = " from RtuSlaveProerty u where u.slaveId="
								+ slaveInfo.getId()
								+ " and u.treeLevel=1  and  u.propertyType='text' and u.treeId="
								+ rtuSlaveProerty1.getTreeId();
						//查询每一个属性类别下的单个文本信息
						List<RtuSlaveProerty> listRtuSlaveProerty2=rtuSlaveProertyDao.find(slaveProertyHql2);
						if(listRtuSlaveProerty2 != null && listRtuSlaveProerty2.size()>0){
							for(RtuSlaveProerty rtuSlaveProerty2:listRtuSlaveProerty2){
								mapMiddleThree.put(rtuSlaveProerty2.getPropertyKey(), changeDataType(rtuSlaveProerty2.getPropertyVal()));
							}
						}
						//查询单个属性下面有哪几类选中复选框
						String slaveProertyHql3=" from RtuSlaveProerty u where u.slaveId="
								+ slaveInfo.getId()+" and  u.treeId="+rtuSlaveProerty1.getTreeId()
								+ " and u.treeLevel=1  and  u.propertyType='checkbox' group by u.propertyKey";
						List<RtuSlaveProerty> listRtuSlaveProerty3=rtuSlaveProertyDao.find(slaveProertyHql3);
						if(listRtuSlaveProerty3!=null && listRtuSlaveProerty3.size()>0){
							//遍历每个类型的复选框选中的值
							for(RtuSlaveProerty rtuSlaveProerty3:listRtuSlaveProerty3){
								String slaveProertyHql4=" from RtuSlaveProerty u where u.slaveId="
										+ slaveInfo.getId()+" and  u.treeId="+rtuSlaveProerty1.getTreeId()
										+ " and u.treeLevel=1  and  u.propertyType='checkbox' and u.checkType=1 " 
										+ " and u.propertyKey='"+rtuSlaveProerty3.getPropertyKey()+"'";
								List<RtuSlaveProerty> listRtuSlaveProerty4=rtuSlaveProertyDao.find(slaveProertyHql4);
								if(listRtuSlaveProerty4 != null && listRtuSlaveProerty4.size() > 0){
									int[] valArray=new int[listRtuSlaveProerty4.size()];
									for(int i=0;i<listRtuSlaveProerty4.size();i++ ){
										valArray[i]=Integer.valueOf(listRtuSlaveProerty4.get(i).getPropertyVal());
									}
									mapMiddleThree.put(rtuSlaveProerty3.getPropertyKey(), valArray);
								}else{
									List<Object> list=new ArrayList<Object>();
									mapMiddleThree.put(rtuSlaveProerty3.getPropertyKey(),list);
								}
							}
						}
						listMapMiddleThree.add(mapMiddleThree);
					}
				}
				mapMiddleTwo.put("slave_reg_list", listMapMiddleThree);
				listMapMiddleTwo.add(mapMiddleTwo);
			}
		}
		 mapTopOne.put("conf_slave_list",listMapMiddleTwo);
		 //组装传输配置json
		 List<Map<String, Object>> listMapTran = new ArrayList<Map<String, Object>>();
		 //查询下面有好多个类型的属性
		 String tranHql=" from RtuTranslateProerty t where t.configId="+rtuConfigInfo.getCid()+" group by t.treeId";
		 List<RtuTranslateProerty> listTranType=rtuTranProertyDao.find(tranHql);
		 if(listTranType!=null && listTranType.size() > 0){
			 for(RtuTranslateProerty rtuTranslateProerty:listTranType){
				 	Map<String, Object> mapMiddleTran = new HashMap<String, Object>();
				 	//查询每一个属性下面的所有属性值
				 	String findtranHql=" from RtuTranslateProerty t where t.configId="+rtuConfigInfo.getCid()+" and t.treeId="+rtuTranslateProerty.getTreeId();
				 	List<RtuTranslateProerty> listTranList=rtuTranProertyDao.find(findtranHql);
				 	if(listTranList!=null && listTranList.size()>0){
				 		for(RtuTranslateProerty translateProerty:listTranList){
				 			mapMiddleTran.put(translateProerty.getPropertyKey(), changeDataType(translateProerty.getPropertyVal()));
				 		}
				 	}
					 listMapTran.add(mapMiddleTran); 
				 }
			 mapTopOne.put("conf_translation_list",listMapTran);
		 }
			String text= JSON.toJSONString(mapTopOne);
			System.out.println(text);
			rtuConfigInfo.setContent(text);
			rtuConfigInfoDao.save(rtuConfigInfo);
			return text;
	}
	
	//将字符串进行转换成其他数据格式(整型、double型)
	private Object changeDataType(String valObj){
		if( StringUtils.isNotBlank(valObj)){
			String intReg="^-?[0-9]\\d*$";//正整数
			String floatReg="^-?[0-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*$";//正浮点数
			if(valObj.matches(intReg)){
				return Integer.valueOf(valObj);
			}else if(valObj.matches(floatReg)){
				return Float.valueOf(valObj);
			}else{
				return valObj;
			}
		}
		return "";
	}
	
	@Override
	public DataGridJson rtuSlaveBaseInfoDataGrid(DataGridBean dg,
			RtuSlaveInfoBean rtuSlaveInfo) {
		String hql = " from RtuSlaveInfo t ";
		DataGridJson j = new DataGridJson();
		List<Object> values = new ArrayList<Object>();
		if (rtuSlaveInfo != null) {
			if (rtuSlaveInfo.getRtuId() != null) {
				hql += " and t.rtu.id = ?";
				values.add(rtuSlaveInfo.getRtuId());
			}
		}
		hql = hql.replaceFirst("and", " where ");
		String totalHql = " select count(*) " + hql;
		j.setTotal(rtuSlaveBaseInfoDao.count(totalHql, values));// 设置总记录数
		if (dg.getSort() != null) {// 设置排序
			hql += " order by " + dg.getSort() + " " + dg.getOrder();
		}
		List<RtuSlaveInfo> rtuSlaveInfoList = rtuSlaveBaseInfoDao.find(hql, dg.getPage(),
				dg.getRows(), values);// 查询分页
		j.setRows(rtuSlaveInfoList);
		return j;
	}
	
	@Override
	public String saveRtuSlaveBaseInfo(RtuSlaveInfoBean rtuSlaveInfoBean) {
		if(rtuSlaveInfoBean !=  null ){
			if (GlobalContext.getCurrentUser() != null) {
				UserBean user = GlobalContext.getCurrentUser();
				if(rtuSlaveInfoBean.getId() == null){
					RtuSlaveInfo rtuSlaveInfo=new RtuSlaveInfo();
					YpBeanUtils.copyPropertiesIgnoreNull(rtuSlaveInfoBean, rtuSlaveInfo);
					rtuSlaveInfo.setCreatTime(new Timestamp(new Date().getTime()));
					rtuSlaveInfo.setCreatUserId(user.getId());
					if(rtuSlaveInfoBean.getRtuId()!=null){
						rtuSlaveInfo.setRtu(rtuBaseDao.get(RtuBaseInfo.class, rtuSlaveInfoBean.getRtuId()));
					}
					rtuSlaveBaseInfoDao.save(rtuSlaveInfo);
					
					RtuBaseInfo rtuBaseInfo=rtuSlaveInfo.getRtu();
							//rtuBaseDao.get(RtuBaseInfo.class, rtuSlaveInfoBean.getRtuId());
					if(rtuBaseInfo!=null){
						rtuBaseInfo.setDelayTime(rtuSlaveInfoBean.getDelayTime());
						rtuBaseInfo.setReserveColumn(rtuSlaveInfoBean.getReserveColumn());
						rtuBaseInfo.setcState(0);
						rtuBaseDao.save(rtuBaseInfo);
					}
					
					return "保存成功";
				}else{
					RtuSlaveInfo temRtuSlaveInfo=rtuSlaveBaseInfoDao.get(RtuSlaveInfo.class, rtuSlaveInfoBean.getId());
					if(temRtuSlaveInfo!=null){
						YpBeanUtils.copyPropertiesIgnoreNull(rtuSlaveInfoBean, temRtuSlaveInfo);
						if(rtuSlaveInfoBean.getRtuId()!=null){
							temRtuSlaveInfo.setRtu(rtuBaseDao.get(RtuBaseInfo.class, rtuSlaveInfoBean.getRtuId()));
						}
						
						temRtuSlaveInfo.setModifyTime(new Timestamp(new Date().getTime()));;
						temRtuSlaveInfo.setModifyUserId(user.getCreateUserId());
						rtuSlaveBaseInfoDao.update(temRtuSlaveInfo);
						
						RtuBaseInfo rtuBaseInfo=temRtuSlaveInfo.getRtu();
								//rtuBaseDao.get(RtuBaseInfo.class, rtuSlaveInfoBean.getRtuId());
						if(rtuBaseInfo!=null){
							rtuBaseInfo.setDelayTime(rtuSlaveInfoBean.getDelayTime());
							rtuBaseInfo.setReserveColumn(rtuSlaveInfoBean.getReserveColumn());
							rtuBaseInfo.setcState(0);
							rtuBaseDao.save(rtuBaseInfo);
						}
						
						return "保存成功";
					}
					
				}
				
			}
		}
		return "保存失败";
	}
	@Override
	public List<SlaveInfoBean> findSlaveInfo(Long configId) {
		List<SlaveInfoBean> result = new ArrayList<SlaveInfoBean>();
		String hql=" from SlaveInfo s where s.configId=?";
		List<Object> params=new ArrayList<Object>();
		params.add(configId);
		List<SlaveInfo> slList=slaveInfoDao.find(hql, params);
		List<RtuSlaveProerty> newRspList=new ArrayList<RtuSlaveProerty>();
		if(null!=slList && slList.size()>0){
			for(SlaveInfo sl : slList){
				SlaveInfo sInfo=new SlaveInfo();
				BeanUtils.copyProperties(sl, sInfo);
				sInfo.setId(null);
				sInfo.setConfigId(null);
				slaveInfoDao.save(sInfo);
				Long slaveId=sInfo.getId();
				String hql2=" from RtuSlaveProerty t where t.slaveId=?";
				List<Object> params2=new ArrayList<Object>();
				params2.add(sl.getId());
				List<RtuSlaveProerty> rspList=rtuSlaveProertyDao.find(hql2, params2);
				if(null!=rspList && rspList.size()>0){
					for(RtuSlaveProerty rsp:rspList){
						RtuSlaveProerty  newrsp=new RtuSlaveProerty();
						BeanUtils.copyProperties(rsp, newrsp);
						newrsp.setSlaveId(slaveId);
						newRspList.add(newrsp);
					}
				}
				if(null!=newRspList&& newRspList.size()>0){
					Map<String,Object> map=rtuSlaveProertyDao.getSessionInfo();
					//saveListByJdbc(map,newRspList);
					rtuSlaveProertyDao.saveList(newRspList);
				}
				SlaveInfoBean slBean= new  SlaveInfoBean();
				BeanUtils.copyProperties(sInfo, slBean);
				result.add(slBean);
			}
		}
		return result;
	}
	@SuppressWarnings("static-access")
	private void saveListByJdbc(Map<String,Object> map,List<RtuSlaveProerty> newRspList){
		PreparedStatement ps = null;
		Session session=(Session)map.get("newSession");
		Connection conn=(Connection)map.get("conn");
		String sql="insert into t_cyhc_rtu_slave_proerty(id ,tree_level ,tree_id ,tree_pid,tree_name,property_key,property_name,property_val,property_type,slave_id,check_type) " +
				" values(?,?,?,?,?,?,?,?,?,?,?);";
		
		try {
			conn.setAutoCommit(false);
			ps=conn.prepareStatement(sql);
			for(int i=0;i<newRspList.size();i++){
				RtuSlaveProerty rsp =newRspList.get(i);
				Thread.currentThread().sleep(1);//毫秒 
				AtomicLong id = new AtomicLong(System.currentTimeMillis());
				//long now=new Date().getTime();
				//ps.setLong(1, Long.valueOf(Long.toHexString(now),16));
				ps.setLong(1, Long.valueOf(id.incrementAndGet()));
				ps.setInt(2, rsp.getTreeLevel());
				ps.setLong(3,rsp.getTreeId());
				ps.setLong(4, rsp.getTreePid());
				ps.setString(5, rsp.getTreeName());
				ps.setString(6, rsp.getPropertyKey());
				ps.setString(7, rsp.getPropertyName());
				ps.setString(8, rsp.getPropertyVal());
				ps.setString(9, rsp.getPropertyType());
				ps.setLong(10, rsp.getSlaveId());
				ps.setInt(11, rsp.getCheckType());
				ps.addBatch();
				if(i%100==0){
					ps.executeBatch();
					conn.commit();
				}
			}
			ps.executeBatch();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(null!=ps){
					ps.close();
				}
				if(null!=conn){
					conn.close();
				}
				session.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	@Override
	public List<SlaveInfoBean> lookSlaveInfo(Long configId) {
		List<SlaveInfoBean> result = new ArrayList<SlaveInfoBean>();
		String hql=" from SlaveInfo s where s.configId=?";
		List<Object> params=new ArrayList<Object>();
		params.add(configId);
		List<SlaveInfo> slList=slaveInfoDao.find(hql, params);
		if(null!=slList && slList.size()>0){
			for(SlaveInfo sl : slList){
				SlaveInfoBean slBean= new  SlaveInfoBean();
				BeanUtils.copyProperties(sl, slBean);
				result.add(slBean);
			}
		}
		return result;
	}

	@Override
	public List<RtuTranslateProertyBean> findTransInfo(Long configId) {
		List<RtuTranslateProertyBean> result= new ArrayList<RtuTranslateProertyBean>();
		String hql=" from RtuTranslateProerty s where s.configId=?";
		List<Object> params=new ArrayList<Object>();
		params.add(configId);
		List<RtuTranslateProerty> lists=rtuTranProertyDao.find(hql, params);
		if(null!=lists && lists.size()>0){
			for(RtuTranslateProerty rtp :lists){
				RtuTranslateProertyBean rtpBean = new RtuTranslateProertyBean();
				BeanUtils.copyProperties(rtp, rtpBean);
				result.add(rtpBean);
			}
		}
		return result;
	}

	@Override
	public boolean addOrUpdateRTUBase(RtuBaseInfo rtuBaseInfo) {
		boolean flag = false;
		RtuBaseInfo newRtuBaseInfo = rtuBaseDao.get(RtuBaseInfo.class, rtuBaseInfo.getId());
		
		
		if (newRtuBaseInfo == null) {
			// 若无ID，状态为添加
			// 设置创建人和创建时间
			rtuBaseInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
			User user = userDao.get(User.class, GlobalContext.getCurrentUser().getId());
			rtuBaseInfo.setCreateUser(user);
			rtuBaseDao.save(rtuBaseInfo);
			flag = true;
		} else {
			// 状态为修改
			// 设置修改人和修改时间

			YpBeanUtils.copyPropertiesIgnoreNull(rtuBaseInfo, newRtuBaseInfo);
			newRtuBaseInfo.setModifyTime(new Timestamp(System.currentTimeMillis()));
			User user = userDao.get(User.class, GlobalContext.getCurrentUser().getId());
			newRtuBaseInfo.setModifyUser(user);
			GeneralMethod.setRecordInfo(newRtuBaseInfo, false);
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean deleteRTUBaseById(long id) {
		RtuBaseInfo rtuBaseInfo = rtuBaseDao.get(RtuBaseInfo.class, id);
		if (rtuBaseInfo == null) {
			return false;
		}
		rtuBaseDao.delete(rtuBaseInfo);
		return true;
	}
//	@Override
//	public String deleteRtuPro(Long id) {
//		RtuProperty rp=this.rtuPropertyDao.get(RtuProperty.class, id);
//		if(null!=rp){
//			String hql1=" from RtuPropertyRefer t where t.propertyKey='"+rp.getPropDefault()+"' and t.propertyText='"+rp.getPropDesc()+"'";
//			List<RtuPropertyRefer> rprList=this.referDao.find(hql1);
//			if(null!=rprList && rprList.size()>0){
//				for(RtuPropertyRefer rpr:rprList){
//					this.referDao.delete(rpr);
//				}
//			}
//			String hql2=" from RtuRefer s where s.propertyId="+rp.getId();
//			List<RtuRefer> rrList= this.rtuReferDao.find(hql2);
//			if(null!=rrList && rrList.size()>0){
//				for(RtuRefer rr:rrList){
//					this.rtuReferDao.delete(rr);
//				}
//			}
//			this.rtuPropertyDao.delete(rp);
//			return "2";
//		}else{
//			return "1";
//		}
//	}
	@Override
	public String deleteRtuPro(Long id) {
		String sql3="delete from t_cyhc_rtu_property   where   id="+id+" or pid="+id;
		String sql2=" delete from t_cyhc_rtu_property_refer  where property_key in  " +
				" (select prop_default from t_cyhc_rtu_property  where (id="+id+" or pid="+id+")" +
						" and prop_key in ('reg_id','trans_id'))";
		String sql1=" delete from t_cyhc_rtu_refer  where property_id in " +
				"(select id from t_cyhc_rtu_property  where id="+id+" or pid="+id+")";
		this.rtuReferDao.executeSql(sql1);
		this.referDao.executeSql(sql2);
		this.rtuPropertyDao.executeSql(sql3);
		return "2";
	}

	@Override
	public String deleteSlaveInfo(Long slaveId) {
		String sql1=" delete from t_cyhc_rtu_slave_proerty  where slave_id= "+slaveId;
		rtuSlaveProertyDao.executeSql(sql1);
		String sql2=" delete  from t_cyhc_slave_info  where id="+slaveId;
		slaveInfoDao.executeSql(sql2);
		return slaveId+"";
	}
	
		@Override
		public String deleteSlaveBase(Long id) {
			RtuSlaveInfo rtuSlaveInfo=rtuSlaveBaseInfoDao.get(RtuSlaveInfo.class, id);
			if(rtuSlaveInfo!=null){
				rtuSlaveBaseInfoDao.delete(rtuSlaveInfo);
			}else{
				return "删除失败";
			}
			
			return "删除成功";
		}
		
		@Override
		public String updateRtuState(Long rtuId) {
			RtuBaseInfo rtuBaseInfo=rtuBaseDao.get(RtuBaseInfo.class, rtuId);
			rtuBaseInfo.setcState(1);
			return "确认成功";
		}

		@Override
		public String deleteConfigFun(Long configId) {
			String result="";
			RtuConfigInfo configInfo=this.rtuConfigInfoDao.get(RtuConfigInfo.class, configId);
			if(null!=configInfo && configInfo.getConfigStatus()==1){
				String sql1=" delete from t_cyhc_rtu_translate_proerty where config_id="+configId;
				String sql2=" delete from t_cyhc_rtu_slave_proerty  where slave_id in ( " +
					" select id from t_cyhc_slave_info  where config_id="+configId+")";
				String sql3=" delete from t_cyhc_slave_info  where config_id="+configId;
				String sql4=" delete from t_cyhc_rtu_config_info  where cid="+configId;
				this.baseDao.executeSql(sql1);
				this.baseDao.executeSql(sql2);
				this.baseDao.executeSql(sql3);
				this.baseDao.executeSql(sql4);
				result="1";
			}else{
				result="99";
			}
			return result;
		}
		public static void main(String[] args) {
			long now=new Date().getTime();
			System.out.println(Long.valueOf(Long.toHexString(now),16));
		}

		@Override
		public String addPro(RtuPropertyBean proBean) {
			if(proBean.getPropKey().equals("reg_id") || proBean.getPropKey().equals("trans_id")){
				String sql=" select t.property_key,t.property_text from t_cyhc_rtu_property_refer t ";
				List<Object[]> lists=this.baseDao.findBySql(sql);
				Map<String,String> map= new HashMap<String,String>();
				if(null!=lists&& lists.size()>0){
					for(Object[] obj:lists){
						map.put(obj[0].toString(), obj[1].toString());
					}
				}
				boolean flag=true;
				for(String key:map.keySet()){
					if(proBean.getPropDefault().equals(key)){
						flag=false;
						break;
					}
				}
				if(!flag){
					return "1";
				}else{
					RtuPropertyRefer refer=new RtuPropertyRefer();
					refer.setPropertyKey(proBean.getPropDefault());
					refer.setPropertyText(proBean.getPropDesc());
					this.referDao.save(refer);
				}
			}
			RtuProperty rp = new RtuProperty();
			YpBeanUtils.copyPropertiesIgnoreNull(proBean, rp);
			this.rtuPropertyDao.save(rp);
			Long rpId=rp.getId();
			if("Y".equals(proBean.getIsRelateRefer())){
				if(StringUtils.isNotBlank(proBean.getReferDesc())
						&& StringUtils.isNotBlank(proBean.getReferKey())){
					List<RtuRefer> rrlist =new ArrayList<RtuRefer>();
					String [] descarr=proBean.getReferDesc().split(",");
					String [] keyarr=proBean.getReferKey().split(",");
					if(descarr.length==keyarr.length){
						for(int i=0;i<descarr.length;i++){
							RtuRefer rr= new RtuRefer();
							rr.setPropertyId(rpId);
							rr.setReferDesc(descarr[i]);
							rr.setReferKey(keyarr[i]);
							rr.setReferOrder(i+1);
							rrlist.add(rr);
						}
						if(null!=rrlist && rrlist.size()>0){
							this.rtuReferDao.saveList(rrlist);
						}
					}else{
						return "2";
					}
				}
			}
			return "3";
		}
		@Override
		public String addProGroup(RtuPropertyBean proBean) {
			RtuProperty rp = new RtuProperty();
			YpBeanUtils.copyPropertiesIgnoreNull(proBean, rp);
			this.rtuPropertyDao.save(rp);
			return "3";
		}
}
