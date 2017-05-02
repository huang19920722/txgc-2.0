package com.yp.sys.service.params.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yp.sys.common.GlobalConstant;
import com.yp.sys.dao.params.IParams3Dao;
import com.yp.sys.dao.params.IParams4Dao;
import com.yp.sys.dao.params.IParamsDao;
import com.yp.sys.dao.thresh.IThreshDao;
import com.yp.sys.entity.params.Params;
import com.yp.sys.entity.params.Params3;
import com.yp.sys.entity.params.Params4;
import com.yp.sys.entity.workorder.TCyhcThresholdInfo;
import com.yp.sys.pojo.DataGridBean;
import com.yp.sys.pojo.DataGridJson;
import com.yp.sys.pojo.ParamsBean;
import com.yp.sys.pojo.ParamsBean3;
import com.yp.sys.pojo.ParamsBean4;
import com.yp.sys.pojo.ThresoldInfoBean;
import com.yp.sys.service.common.impl.BaseServiceImpl;
import com.yp.sys.service.params.IParamsService;
import com.yp.sys.util.StringUtil;
import com.yp.sys.util.TimeStampUtil;
import com.yp.sys.util.YpBeanUtils;

/**
 * 
 * 版权所有：2016-重庆重邮汇测通讯技术有限公司
 * 项目名称：txgc   
 *
 * 类描述：工参管理Service实现类
 * 类名称：com.yp.sys.service.params.impl.ParamsServiceImpl     
 * 创建人：zhongyang
 * 创建时间：2016-11-16 下午3:24:08   
 * 修改人：
 * 修改时间：2016-11-16 下午3:24:08   
 * 修改备注：   
 * @version   V1.0
 */
@Service("paramsService")
@Transactional
public class ParamsServiceImpl extends BaseServiceImpl<Params> implements IParamsService {

	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(ParamsServiceImpl.class);

	TimeStampUtil timeStampUtil=new TimeStampUtil();
	private IParamsDao paramsDao;
	
	public IParamsDao getParamsDao() {
		return paramsDao;
	}
	@Autowired
	public void setParamsDao(IParamsDao paramsDao) {
		this.paramsDao = paramsDao;
	}
	
	private IParams3Dao params3Dao;
	@Autowired
	public void setParams3Dao(IParams3Dao params3Dao) {
		this.params3Dao = params3Dao;
	}

	private IParams4Dao params4Dao;
	@Autowired
	public void setParams4Dao(IParams4Dao params4Dao) {
		this.params4Dao = params4Dao;
	}

	private IThreshDao threshDao;
	
	public IThreshDao getThreshDao() {
		return threshDao;
	}
	@Autowired
	public void setThreshDao(IThreshDao threshDao) {
		this.threshDao = threshDao;
	}
	
	@Autowired
	private SessionFactory sessionFactory;
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	/**
	 * 
	 * @Title: datagrid
	 * @Description: 获取2g工参管理列表
	 * @param dg
	 * @param params
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public DataGridJson datagrid2g(DataGridBean dg, ParamsBean params) {
		DataGridJson j = new DataGridJson();
		String hql = " from Params t where recordStatus=?  ";
		List<Object> values = new ArrayList<Object>();
		values.add(GlobalConstant.FLAG_Y);
		if (params != null) {// 添加查询条件
			if (StringUtil.isNotBlank(params.getStartTime()) && StringUtil.isNotBlank(params.getEndTime())) {
				Timestamp startTime = timeStampUtil.stringToTimeStame(params.getStartTime());
				Timestamp endTime = timeStampUtil.stringToTimeStame(params.getEndTime());
				hql += " and t.lastUpdateTime BETWEEN ";
				hql += " ? ";
				hql += " AND ?";
				values.add(startTime);
				values.add(endTime);
			}
			if(StringUtil.isNotBlank(params.getWirelessEquipmentManufacturer())){
				hql += " and t.wirelessEquipmentManufacturer = ? ";
				values.add(params.getWirelessEquipmentManufacturer());
			}
			if(StringUtil.isNotBlank(params.getCommunityName())){
				hql += " and t.communityName = ? ";
				values.add(params.getCommunityName());
			}
		}
		String totalHql = " select count(*) " + hql;
		j.setTotal(paramsDao.count(totalHql, values));// 设置总记录数
		if (dg.getSort() != null) {// 设置排序
			hql += " order by " + dg.getSort() + " " + dg.getOrder();
		}
		List<Params> paramsList = paramsDao.find(hql, dg.getPage(), dg.getRows(), values);// 查询分页
		List<ParamsBean> beanList = new ArrayList<ParamsBean>();
		if (paramsList != null && paramsList.size() > 0) {// 转换模型
			for (Params par : paramsList) {
				ParamsBean paramsBean = new ParamsBean();
				YpBeanUtils.copyPropertiesIgnoreNull(par, paramsBean);
				paramsBean.setStationName(par.getStationName());
				beanList.add(paramsBean);
			}
		}
		j.setRows(beanList);// 设置返回的行
		return j;
	}
	
	/**
	 * 
	 * @Title: datagrid
	 * @Description: 获取3g工参管理列表
	 * @param dg
	 * @param params
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public DataGridJson datagrid3g(DataGridBean dg, ParamsBean3 params) {
		DataGridJson j = new DataGridJson();
		String hql = " from Params3 t  where recordStatus=? ";
		List<Object> values = new ArrayList<Object>();
		values.add(GlobalConstant.FLAG_Y);
		if (params != null) {// 添加查询条件
			if (StringUtil.isNotBlank(params.getStartTime()) && StringUtil.isNotBlank(params.getEndTime())) {
				Timestamp startTime = timeStampUtil.stringToTimeStame(params.getStartTime());
				Timestamp endTime = timeStampUtil.stringToTimeStame(params.getEndTime());
				hql += " and t.lastUpdateTime BETWEEN ";
				hql += " ? ";
				hql += " AND ?";
				values.add(startTime);
				values.add(endTime);
			}
			if(StringUtil.isNotBlank(params.getWirelessEquipmentManufacturer())){
				hql += " and t.wirelessEquipmentManufacturer = ? ";
				values.add(params.getWirelessEquipmentManufacturer());
			}
			if(StringUtil.isNotBlank(params.getCommunityName())){
				hql += " and t.communityName = ? ";
				values.add(params.getCommunityName());
			}
		}
		String totalHql = " select count(*) " + hql;
		j.setTotal(params3Dao.count(totalHql, values));// 设置总记录数
		if (dg.getSort() != null) {// 设置排序
			hql += " order by " + dg.getSort() + " " + dg.getOrder();
		}
		List<Params3> params3List = params3Dao.find(hql, dg.getPage(), dg.getRows(), values);// 查询分页
		List<ParamsBean3> beanList = new ArrayList<ParamsBean3>();
		if (params3List != null && params3List.size() > 0) {// 转换模型
			for (Params3 par : params3List) {
				ParamsBean3 paramsBean = new ParamsBean3();
				YpBeanUtils.copyPropertiesIgnoreNull(par, paramsBean);
				paramsBean.setLastUpdateTime(par.getLastUpdateTime());
				beanList.add(paramsBean);
			}
		}
		j.setRows(beanList);// 设置返回的行
		return j;
	}
	
	/**
	 * 
	 * @Title: datagrid
	 * @Description: 获取4g工参管理列表
	 * @param dg
	 * @param params
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public DataGridJson datagrid4g(DataGridBean dg, ParamsBean4 params) {
		DataGridJson j = new DataGridJson();
		String hql = " from Params4 t  where recordStatus= ? ";
		List<Object> values = new ArrayList<Object>();
		values.add(GlobalConstant.FLAG_Y);
		if (params != null) {// 添加查询条件
			if (StringUtil.isNotBlank(params.getStartTime()) && StringUtil.isNotBlank(params.getEndTime())) {
				Timestamp startTime = timeStampUtil.stringToTimeStame(params.getStartTime());
				Timestamp endTime = timeStampUtil.stringToTimeStame(params.getEndTime());
				hql += " and t.lastUpdateTime BETWEEN ";
				hql += " ? ";
				hql += " AND ?";
				values.add(startTime);
				values.add(endTime);
			}
			if(StringUtil.isNotBlank(params.getWirelessEquipmentManufacturer())){
				hql += " and t.wirelessEquipmentManufacturer = ? ";
				values.add(params.getWirelessEquipmentManufacturer());
			}
			if(StringUtil.isNotBlank(params.getCommunityName())){
				hql += " and t.communityName = ? ";
				values.add(params.getCommunityName());
			}
		}
		String totalHql = " select count(*) " + hql;
		j.setTotal(params4Dao.count(totalHql, values));// 设置总记录数
		if (dg.getSort() != null) {// 设置排序
			hql += " order by " + dg.getSort() + " " + dg.getOrder();
		}
		List<Params4> paramsList = params4Dao.find(hql, dg.getPage(), dg.getRows(), values);// 查询分页
		List<ParamsBean4> beanList = new ArrayList<ParamsBean4>();
		if (paramsList != null && paramsList.size() > 0) {// 转换模型
			for (Params4 par : paramsList) {
				ParamsBean4 paramsBean = new ParamsBean4();
				YpBeanUtils.copyPropertiesIgnoreNull(par, paramsBean);
				beanList.add(paramsBean);
			}
		}
		j.setRows(beanList);// 设置返回的行
		return j;
	}
	
	/**
	 * @Title: executeUtil
	 * @Description: 获取码表中的无限厂商设备
	 * @return
	 */
	@Override
	public Map<String, String> executeUtil() {
		String sql="select t.gb_code,t.gb_name from t_cyhc_gb t where t.gb_type = 'wxsbcs' order by t.gb_code asc";
		List<Object[]> list=this.getCurrentSession().createSQLQuery(sql).list();
		Map<String, String>  map=new HashMap<String, String>();
		if(list!=null && list.size()>0){
			for(Object[] objects:list){
				map.put(objects[0]+"", objects[1]+"");
			}
		}
		return map;
	}
	
	/**
	 * @Title: vilageAdd
	 * @Description: 添加阀值
	 * @param params
	 * @return
	 */
	public ThresoldInfoBean addVilage(ThresoldInfoBean info){
		TCyhcThresholdInfo infoEntity = new TCyhcThresholdInfo();
		YpBeanUtils.copyPropertiesIgnoreNull(info, infoEntity);
		threshDao.saveNew(infoEntity);
		info.setId(infoEntity.getId());
		//获取阀值ID，更新到工参表中。
		String sql = "";
		//info.getXqgc()多项，数组，循环
		String[] str = info.getXqgc().split(",");
		for (int i = 0 ; i < str.length ; i++ ) {
			sql = "update t_cyhc_worker_parameter_2g t set t.threshold_id = '"+infoEntity.getId()+"' where t.community_scrambler = '"+str[i]+"'";
			paramsDao.executeSql(sql);
		} 
		return info;
	}
	
	/**
	 * @Title: vilageAdd
	 * @Description: 添加阀值
	 * @param params
	 * @return
	 */
	public ThresoldInfoBean addVilage3(ThresoldInfoBean info){
		TCyhcThresholdInfo infoEntity = new TCyhcThresholdInfo();
		YpBeanUtils.copyPropertiesIgnoreNull(info, infoEntity);
		threshDao.saveNew(infoEntity);
		info.setId(infoEntity.getId());
		//获取阀值ID，更新到工参表中。
		String sql = "";
		//info.getXqgc()多项，数组，循环
		String[] str = info.getXqgc().split(",");
		for (int i = 0 ; i < str.length ; i++ ) {
			sql = "update t_cyhc_worker_parameter_3g t set t.threshold_id = '"+infoEntity.getId()+"' where t.community_scrambler = '"+str[i]+"'";
			paramsDao.executeSql(sql);
		} 
		return info;
	}
	
	/**
	 * @Title: vilageAdd
	 * @Description: 添加阀值
	 * @param params
	 * @return
	 */
	public ThresoldInfoBean addVilage4(ThresoldInfoBean info){
		TCyhcThresholdInfo infoEntity = new TCyhcThresholdInfo();
		YpBeanUtils.copyPropertiesIgnoreNull(info, infoEntity);
		threshDao.saveNew(infoEntity);
		info.setId(infoEntity.getId());
		//获取阀值ID，更新到工参表中。
		String sql = "";
		//info.getXqgc()多项，数组，循环
		String[] str = info.getXqgc().split(",");
		for (int i = 0 ; i < str.length ; i++ ) {
			sql = "update t_cyhc_worker_parameter_4g t set t.threshold_id = '"+infoEntity.getId()+"' where t.id = '"+str[i]+"'";
			paramsDao.executeSql(sql);
		} 
		return info;
	}
	
	
	/**
	 * 
	 * @Title: datagridThr
	 * @Description: 获取阀值管理列表
	 * @param dg
	 * @param params
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public DataGridJson datagridThr(DataGridBean dg, ThresoldInfoBean infoBen) {
		DataGridJson j = new DataGridJson();
		String hql = " from TCyhcThresholdInfo t  ";
		List<Object> values = new ArrayList<Object>();
		if (infoBen != null) {// 添加查询条件
		}
		hql=hql.replaceFirst("and", " where ");
		String totalHql = " select count(*) " + hql;
		j.setTotal(paramsDao.count(totalHql, values));// 设置总记录数
		if (dg.getSort() != null) {// 设置排序
			hql += " order by " + dg.getSort() + " " + dg.getOrder();
		}
		List<TCyhcThresholdInfo> infoList = threshDao.find(hql, dg.getPage(), dg.getRows(), values);// 查询分页
		List<ThresoldInfoBean> beanList = new ArrayList<ThresoldInfoBean>();
		if (infoList != null && infoList.size() > 0) {// 转换模型
			for (TCyhcThresholdInfo info : infoList) {
				ThresoldInfoBean bean = new ThresoldInfoBean();
				YpBeanUtils.copyPropertiesIgnoreNull(info, bean);
				beanList.add(bean);
			}
		}
		j.setRows(beanList);// 设置返回的行
		return j;
	}
	
	/**
	 * @Title: get2gParams
	 * @Description: 查询2G天线工程数据
	 * @return
	 */
	@Override
	public List<ParamsBean> get2gParams() {
		return null;
	}
	/**
	 * @Title: add2gParams
	 * @Description: 增加2G工参数据
	 * @param params
	 * @return
	 */
	@Override
	public ParamsBean add2gParams(ParamsBean params) {
		return null;
	}
	/**
	 * 
	 * @Title: edit2gParams
	 * @Description: 修改2G工参数据
	 * @param params
	 * @return
	 */
	@Override
	public ParamsBean edit2gParams(ParamsBean params) {
		return null;
	}
	/**
	 * @Title: delParams
	 * @Description: 删除2G工参数据
	 * @param params
	 */
	@Override
	public void del2gParams(ParamsBean params) {
		
	}
	
	@Override
	public ParamsBean query2gParamById(Long id) {
		// TODO Auto-generated method stub
		Params params= paramsDao.get(Params.class, id);
		
		if(params!=null){
			 ParamsBean paramsBean=new ParamsBean();
			 YpBeanUtils.copyPropertiesIgnoreNull(params, paramsBean);
			 return paramsBean;
		}else{
			return null;
		}
		 
	}
	@Override
	public ParamsBean3 query3gParamById(Long id) {
		Params3 params= params3Dao.get(Params3.class, id);
		
		if(params!=null){
			ParamsBean3 paramsBean=new ParamsBean3();
			 YpBeanUtils.copyPropertiesIgnoreNull(params, paramsBean);
			 return paramsBean;
		}else{
			return null;
		}
	}
	@Override
	public ParamsBean4 query4gParamById(Long id) {
		Params4 params= params4Dao.get(Params4.class, id);
		
		if(params!=null){
			ParamsBean4 paramsBean=new ParamsBean4();
			 YpBeanUtils.copyPropertiesIgnoreNull(params, paramsBean);
			 return paramsBean;
		}else{
			return null;
		}
	}
}
