package com.yp.sys.service.antenna.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yp.sys.common.GlobalConstant;
import com.yp.sys.dao.params.IParams3Dao;
import com.yp.sys.dao.params.IParams4Dao;
import com.yp.sys.dao.params.IParamsDao;
import com.yp.sys.dao.workorder.ICommunityDao;
import com.yp.sys.entity.params.Params;
import com.yp.sys.entity.params.Params3;
import com.yp.sys.entity.params.Params4;
import com.yp.sys.entity.workorder.CommunityInfo;
import com.yp.sys.pojo.AntennaBean;
import com.yp.sys.pojo.DataGridBean;
import com.yp.sys.pojo.DataGridJson;
import com.yp.sys.service.antenna.IAntennaService;
import com.yp.sys.service.common.impl.BaseServiceImpl;
import com.yp.sys.util.StringUtil;
import com.yp.sys.util.YpBeanUtils;
@Service("antennaService")
public class AntennaServiceImpl extends BaseServiceImpl<Params> implements IAntennaService {

	@Autowired
	private IParamsDao paramsDao;
	@Autowired
	private IParams3Dao params3Dao;
	@Autowired
	private IParams4Dao params4Dao;
	@Autowired
	private ICommunityDao communityDao;
	@Autowired
	private SessionFactory sessionFactory;
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	/**
	 * 
	 * @Title: datagrid
	 * @Description: 获取2g天线信息列表
	 * @param dg
	 * @param antenna
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public DataGridJson datagrid2g(DataGridBean dg, AntennaBean antenna) {
		DataGridJson j = new DataGridJson();
		StringBuffer hql = new StringBuffer();
		hql.append( " from Params t where recordStatus=? ");
		List<Object> values = new ArrayList<Object>();
		values.add(GlobalConstant.FLAG_Y);
		// 添加条件1
		if (StringUtil.isNotBlank(antenna.getAntennaType())) {
				hql.append(" AND antennaType LIKE ?");
				values.add("%" + antenna.getAntennaType() + "%");
		}
		// 添加条件2
		if (StringUtil.isNotBlank(antenna.getCi())) {
				hql.append(" AND ci LIKE ?");
				values.add("%" + antenna.getCi() + "%");
		}
		// 添加条件3
		if (StringUtil.isNotBlank(antenna.getLac())) {
				hql.append(" AND lac LIKE ?");
				values.add("%" + antenna.getLac() + "%");
		}
		String totalHql = " select count(*) " + hql.toString();
		j.setTotal(paramsDao.count(totalHql, values));// 设置总记录数
		if (dg.getSort() != null) {// 设置排序
			hql .append(" order by " + dg.getSort() + " " + dg.getOrder());
		}
		List<Params> paramsList = paramsDao.find(hql.toString(), dg.getPage(), dg.getRows(), values);// 查询分页
		List<AntennaBean> beanList = new ArrayList<AntennaBean>();
		if (paramsList != null && paramsList.size() > 0) {// 转换模型
			for (Params par : paramsList) {
				AntennaBean antennaBean = new AntennaBean();
				antennaBean.setCi(par.getCi());
				antennaBean.setLac(par.getLac());
				antennaBean.setStationName(par.getStationName());
				antennaBean.setId(par.getId());
				antennaBean.setAntennaLength(par.getAntennaLength());
				antennaBean.setWirelessEquipmentManufacturer(par.getWirelessEquipmentManufacturer());
				antennaBean.setAntennaType(par.getAntennaType());
				beanList.add(antennaBean);
			}
		}
		j.setRows(beanList);// 设置返回的行
		return j;
	}

	/**
	 * 
	 * @Title: datagrid
	 * @Description: 获取3g天线信息列表
	 * @param dg
	 * @param antenna
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public DataGridJson datagrid3g(DataGridBean dg, AntennaBean antenna) {
		DataGridJson j = new DataGridJson();
		StringBuffer hql = new StringBuffer();
		hql.append( " from Params3 t where recordStatus=? ");
		List<Object> values = new ArrayList<Object>();
		values.add(GlobalConstant.FLAG_Y);
		// 添加条件1
		if (StringUtil.isNotBlank(antenna.getAntennaType())) {
				hql.append(" AND antennaType LIKE ?");
				values.add("%" + antenna.getAntennaType() + "%");
			}
		// 添加条件2
		if (StringUtil.isNotBlank(antenna.getCi())) {
				hql.append(" AND ci LIKE ?");
				values.add("%" + antenna.getCi() + "%");
			}
		// 添加条件3
		if (StringUtil.isNotBlank(antenna.getLac())) {
				hql.append(" AND lac LIKE ?");
				values.add("%" + antenna.getLac() + "%");
		}
		String totalHql = " select count(*) " + hql.toString();
		j.setTotal(params3Dao.count(totalHql, values));// 设置总记录数
		if (dg.getSort() != null) {// 设置排序
			hql .append(" order by " + dg.getSort() + " " + dg.getOrder());
		}
		List<Params3> params3List = params3Dao.find(hql.toString(), dg.getPage(), dg.getRows(), values);// 查询分页
		List<AntennaBean> beanList = new ArrayList<AntennaBean>();
		if (params3List != null && params3List.size() > 0) {// 转换模型
			for (Params3 par : params3List) {
				AntennaBean antennaBean = new AntennaBean();
				antennaBean.setCi(par.getCi());
				antennaBean.setLac(par.getLac());
				antennaBean.setStationName(par.getStationName());
				antennaBean.setId(par.getId());
				antennaBean.setAntennaLength(par.getAntennaLength());
				antennaBean.setWirelessEquipmentManufacturer(par.getWirelessEquipmentManufacturer());
				antennaBean.setAntennaType(par.getAntennaType());
				beanList.add(antennaBean);
			}
		}
		j.setRows(beanList);// 设置返回的行
		return j;
	}

	/**
	 * 
	 * @Title: datagrid
	 * @Description: 获取4g天线信息列表
	 * @param dg
	 * @param antenna
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public DataGridJson datagrid4g(DataGridBean dg, AntennaBean antenna) {
		DataGridJson j = new DataGridJson();
		StringBuffer hql = new StringBuffer();
		hql.append( " from Params4 where recordStatus=? ");
		List<Object> values = new ArrayList<Object>();
		values.add(GlobalConstant.FLAG_Y);
		// 添加条件1
		if (StringUtil.isNotBlank(antenna.getAntennaType())) {
				hql.append(" AND antennaType LIKE ?");
				values.add("%" + antenna.getAntennaType() + "%");
			}
		// 添加条件2
		if (StringUtil.isNotBlank(antenna.getCi())) {
				hql.append(" AND ci LIKE ?");
				values.add("%" + antenna.getCi() + "%");
			}	
		// 添加条件3
		if (StringUtil.isNotBlank(antenna.getLac())) {
				hql.append(" AND lac LIKE ?");
				values.add("%" + antenna.getLac() + "%");
			}
		String totalHql = " select count(*) " + hql.toString();
		j.setTotal(params4Dao.count(totalHql, values));// 设置总记录数
		if (dg.getSort() != null) {// 设置排序
			hql .append(" order by " + dg.getSort() + " " + dg.getOrder());
		}
		List<Params4> paramsList = params4Dao.find(hql.toString(), dg.getPage(), dg.getRows(), values);// 查询分页
		List<AntennaBean> beanList = new ArrayList<AntennaBean>();
		if (paramsList != null && paramsList.size() > 0) {// 转换模型
			for (Params4 par : paramsList) {
				AntennaBean antennaBean = new AntennaBean();
				antennaBean.setCi(par.getCi());
				antennaBean.setLac(par.getLac());
				antennaBean.setStationName(par.getStationName());
				antennaBean.setId(par.getId());
				antennaBean.setAntennaLength(par.getAntennaLength());
				antennaBean.setWirelessEquipmentManufacturer(par.getWirelessEquipmentManufacturer());
				antennaBean.setAntennaType(par.getAntennaType());
				beanList.add(antennaBean);
			}
		}
		j.setRows(beanList);// 设置返回的行
		return j;
	}

	/**
	 * 
	 * @Title: delete
	 * @Description: 删除2g天线信息
	 * @param ids
	 * @return
	 */
	@Override
	public Boolean deleteAntenna2gById(String[] ids) {
		if(ids==null||ids.length<1){
			return false;
		}
		Params newParams=null;
		StringBuffer hql=new StringBuffer();
		List<Object> values=new ArrayList<Object>();
		CommunityInfo newCommunityInfo=null;
		for(String id : ids){
			Long idLong=Long.parseLong(id);
			newParams=paramsDao.get(Params.class, idLong);
			if(newParams==null){
				return false;
			}
			hql.append("from CommunityInfo where ci=?");
			values.add(newParams.getCi());
			newCommunityInfo=this.communityDao.get(hql.toString(), values);
			newCommunityInfo.setRecordStatus(GlobalConstant.FLAG_N);
			if("1".equals(newParams.getIsManually())){
			newParams.setRecordStatus(GlobalConstant.FLAG_N);
			return true;
			}else{
				return false;
			}
		}
		return true;
	}
	/**
	 * 
	 * @Title: delete
	 * @Description: 删除3g天线信息
	 * @param ids
	 * @return
	 */
	@Override
	public Boolean deleteAntenna3gById(String[] ids) {
		if(ids==null||ids.length<1){
			return false;
		}
		Params3 newParams3=new Params3();
		StringBuffer hql=new StringBuffer();
		List<Object> values=new ArrayList<Object>();
		CommunityInfo newCommunityInfo=null;
		for(String id : ids){
			Long idLong=Long.parseLong(id);
			newParams3=params3Dao.get(Params3.class, idLong);
			if(newParams3==null){
				return false;
			}
			hql.append("from CommunityInfo where ci=?");
			values.add(newParams3.getCi());
			newCommunityInfo=this.communityDao.get(hql.toString(), values);
			newCommunityInfo.setRecordStatus(GlobalConstant.FLAG_N);
				if("1".equals(newParams3.getIsManually())){
					newParams3.setRecordStatus(GlobalConstant.FLAG_N);
					return true;
				}else{
					return false;
				}
		}
		return true;
	}

	/**
	 * 
	 * @Title: delete
	 * @Description: 删除4g天线信息
	 * @param ids
	 * @return
	 */
	@Override
	public Boolean deleteAntenna4gById(String[] ids) {
		if(ids==null||ids.length<1){
			return false;
		}
		Params4 newParams4=null;
		StringBuffer hql=new StringBuffer();
		List<Object> values=new ArrayList<Object>();
		CommunityInfo newCommunityInfo=null;
		for(String id : ids){
			Long idLong=Long.parseLong(id);
			newParams4=params4Dao.get(Params4.class, idLong);
			if(newParams4==null){
				return false;
			}
			hql.append("from CommunityInfo where ci=? ");
			values.add(newParams4.getCi());
			newCommunityInfo=this.communityDao.get(hql.toString(), values);
			newCommunityInfo.setRecordStatus(GlobalConstant.FLAG_N);
			if("1".equals(newParams4.getIsManually())){
			newParams4.setRecordStatus(GlobalConstant.FLAG_N);
			return true;
			}else{
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * @Title: addOrUpdate2g
	 * @Description: 修改或增加2g天线信息
	 * @param antennaBean
	 * @return
	 */
	@Override
	public Boolean addOrUpdate2g(AntennaBean antennaBean) {
		boolean flag=false;
		if(antennaBean.getId()==null){//添加
			//保存该条数据
			Params params=new Params();
			CommunityInfo communityInfo=new CommunityInfo();
			communityInfo.setCi(antennaBean.getCi());
			communityInfo.setNetworkStandard("2g");
			communityInfo.setLac(antennaBean.getLac()); 
			//communityInfo.setCname(antennaBean.get);
			//communityInfo.set
			communityDao.save(communityInfo);
			YpBeanUtils.copyPropertiesIgnoreNull(antennaBean, params);
			params.setRecordStatus(GlobalConstant.FLAG_Y);
			params.setIsManually("1");
			paramsDao.save(params);
			flag=true;
		}else{//修改
			Params newParams=paramsDao.get(Params.class,antennaBean.getId());
			if(newParams==null){	
				return false;
			}
			StringBuffer hql=new StringBuffer();
			List<Object> values=new ArrayList<Object>();
			CommunityInfo newCommunityInfo=null;
			hql.append("from CommunityInfo where ci=? ");
			values.add(newParams.getCi());
			newCommunityInfo=this.communityDao.get(hql.toString(), values);
			newCommunityInfo.setCi(antennaBean.getCi());
			newCommunityInfo.setLac(antennaBean.getLac()); 
			if("1".equals(newParams.getIsManually())){
			YpBeanUtils.copyPropertiesIgnoreNull(antennaBean, newParams);
				flag=true;
			}else{
				flag=false;
			}
		}
		return flag;
	}
	/**
	 * 
	 * @Title: addOrUpdate3g
	 * @Description: 修改或增加3g天线信息
	 * @param antennaBean
	 * @return
	 */
	@Override
	public Boolean addOrUpdate3g(AntennaBean antennaBean) {
		boolean flag=false;
		System.out.println(antennaBean.getId());
		if(antennaBean.getId()==null){//添加
			//保存该条数据
			Params3 params3=new Params3();
			CommunityInfo communityInfo=new CommunityInfo();
			communityInfo.setCi(antennaBean.getCi());
			communityInfo.setNetworkStandard("3g");
			communityInfo.setLac(antennaBean.getLac()); 
			communityDao.save(communityInfo);
			YpBeanUtils.copyPropertiesIgnoreNull(antennaBean, params3);
			params3.setRecordStatus(GlobalConstant.FLAG_Y);
			params3.setIsManually("1");
			params3Dao.save(params3);
			flag=true;
		}else{//修改
			Params3 newParams3=params3Dao.get(Params3.class,antennaBean.getId());
			if(newParams3==null){	
				return false;
			}
			StringBuffer hql=new StringBuffer();
			List<Object> values=new ArrayList<Object>();
			CommunityInfo newCommunityInfo=null;
			hql.append("from CommunityInfo where ci=? ");
			values.add(newParams3.getCi());
			newCommunityInfo=this.communityDao.get(hql.toString(), values);
			newCommunityInfo.setCi(antennaBean.getCi());
			newCommunityInfo.setLac(antennaBean.getLac()); 
			if("1".equals(newParams3.getIsManually())){
				YpBeanUtils.copyPropertiesIgnoreNull(antennaBean, newParams3);
				flag=true;
			}else{
				 flag=false;
			}
		}
		return flag;
	}
	/**
	 * 
	 * @Title: addOrUpdate4g
	 * @Description: 修改或增加4g天线信息
	 * @param antennaBean
	 * @return
	 */
	@Override
	public Boolean addOrUpdate4g(AntennaBean antennaBean) {
		boolean flag=false;
		if(antennaBean.getId()==null){//添加
			//保存该条数据
			Params4 params4=new Params4();
			CommunityInfo communityInfo=new CommunityInfo();
			communityInfo.setCi(antennaBean.getCi());
			communityInfo.setNetworkStandard("4g");
			communityInfo.setLac(antennaBean.getLac());
			communityDao.save(communityInfo);
			YpBeanUtils.copyPropertiesIgnoreNull(antennaBean, params4);
			params4.setRecordStatus(GlobalConstant.FLAG_Y);
			params4.setIsManually("1");
			params4Dao.save(params4);
			flag=true;
		}else{//修改
			Params4 newParams4=params4Dao.get(Params4.class,antennaBean.getId());
			if(newParams4==null){	
				return false;
			}
			StringBuffer hql=new StringBuffer();
			List<Object> values=new ArrayList<Object>();
			CommunityInfo newCommunityInfo=null;
			hql.append("from CommunityInfo where ci=? ");
			values.add(newParams4.getCi());
			newCommunityInfo=this.communityDao.get(hql.toString(), values);
			newCommunityInfo.setCi(antennaBean.getCi());
			newCommunityInfo.setLac(antennaBean.getLac());
			if("1".equals(newParams4.getIsManually())){
				YpBeanUtils.copyPropertiesIgnoreNull(antennaBean, newParams4);
				flag=true;
			}else{
				flag=false;
			}
		}
		return flag;
	}

	
}
