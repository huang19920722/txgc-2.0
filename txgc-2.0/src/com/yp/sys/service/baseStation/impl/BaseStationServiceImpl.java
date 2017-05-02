package com.yp.sys.service.baseStation.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yp.sys.common.GlobalConstant;
import com.yp.sys.dao.params.IParams3Dao;
import com.yp.sys.dao.params.IParams4Dao;
import com.yp.sys.dao.params.IParamsDao;
import com.yp.sys.entity.params.Params;
import com.yp.sys.entity.params.Params3;
import com.yp.sys.entity.params.Params4;
import com.yp.sys.pojo.AntennaBean;
import com.yp.sys.pojo.BaseStationBean;
import com.yp.sys.pojo.DataGridBean;
import com.yp.sys.pojo.DataGridJson;
import com.yp.sys.service.baseStation.IBaseStationService;
import com.yp.sys.service.common.impl.BaseServiceImpl;
import com.yp.sys.util.StringUtil;
import com.yp.sys.util.TimeStampUtil;
@Service("baseStationService")
public class BaseStationServiceImpl extends BaseServiceImpl<Params> implements
		IBaseStationService{


	@Autowired
	private IParamsDao paramsDao;
	@Autowired
	private IParams3Dao params3Dao;
	@Autowired
	private IParams4Dao params4Dao;
	TimeStampUtil timeStampUtil=new TimeStampUtil();
	
	
	/**
	 * 
	 * @Title: datagrid
	 * @Description: 获取2g基站信息列表
	 * @param dg
	 * @param baseStation
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public DataGridJson datagrid2g(DataGridBean dg, BaseStationBean baseStation) {
		DataGridJson j = new DataGridJson();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT *  FROM  t_cyhc_worker_parameter_2g  WHERE  recordStatus=?  GROUP BY  LAC ");
		List<Object> values = new ArrayList<Object>();
		values.add(GlobalConstant.FLAG_Y);
		// 添加条件1
		if(StringUtil.isNotBlank(baseStation.getLac())&&StringUtil.isNotBlank(baseStation.getStationType())){
			sql.insert(0, "select * from (");
			sql.append(") t where t.LAC like ? ");
			values.add("%" + baseStation.getLac() + "%");
			sql.append(" and t.station_Type like ? ");
			values.add("%" + baseStation.getStationType() + "%");
		}else{
			if (StringUtil.isNotBlank(baseStation.getLac())) {
					sql.insert(0, "select * from (");
					sql.append(" ) t where t.LAC like ?");
					values.add("%" + baseStation.getLac() + "%");	
			}
			// 添加条件2
			if (StringUtil.isNotBlank(baseStation.getStationType())) {
					sql.insert(0, "select * from (");
					sql.append(" ) t where t.station_Type like ?");
					values.add("%" + baseStation.getStationType() + "%");
			}
		}
		String totalSql = " select count(*) from " + "("+sql.toString()+")u" ;
		j.setTotal(paramsDao.countBySql(totalSql, values));// 设置总记录数
		if (dg.getSort() != null) {// 设置排序
			sql .append(" order by " + dg.getSort() + " " + dg.getOrder());
		}
		List<Params> paramsList = paramsDao.findBySql(sql.toString(), dg.getPage(), dg.getRows(), values);// 查询分页
		List<BaseStationBean> beanList = new ArrayList<BaseStationBean>();
		if (paramsList != null && paramsList.size() > 0) {// 转换模型
			for (Params par : paramsList) {
				BaseStationBean baseStationBean = new BaseStationBean();
				baseStationBean.setLac(par.getLac());
				baseStationBean.setStationName(par.getStationName());
				baseStationBean.setId(par.getId());
				baseStationBean.setTransmissionMedium(par.getTransmissionMedium());
				baseStationBean.setStationType(par.getStationType());
				baseStationBean.setOpenTime1(par.getOpenTime());
				baseStationBean.setDetailAdress(par.getDetailAdress());
				beanList.add(baseStationBean);
			}
		}
		j.setRows(beanList);// 设置返回的行
		return j;
	}

	/**
	 * 
	 * @Title: datagrid
	 * @Description: 获取3g基站信息列表
	 * @param dg
	 * @param baseStation
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public DataGridJson datagrid3g(DataGridBean dg, BaseStationBean baseStation) {
		DataGridJson j = new DataGridJson();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT *  FROM  t_cyhc_worker_parameter_3g  WHERE  recordStatus=?  GROUP BY  LAC ");
		List<Object> values = new ArrayList<Object>();
		values.add(GlobalConstant.FLAG_Y);
		// 添加条件1
		if(StringUtil.isNotBlank(baseStation.getLac())&&StringUtil.isNotBlank(baseStation.getStationType())){
			sql.insert(0, "select * from (");
			sql.append(") t where t.LAC like ? ");
			values.add("%" + baseStation.getLac() + "%");
			sql.append(" and t.station_Type like ? ");
			values.add("%" + baseStation.getStationType() + "%");
		}else{
			if (StringUtil.isNotBlank(baseStation.getLac())) {
					sql.insert(0, "select * from (");
					sql.append(" ) t where t.LAC like ?");
					values.add("%" + baseStation.getLac() + "%");	
			}
			// 添加条件2
			if (StringUtil.isNotBlank(baseStation.getStationType())) {
					sql.insert(0, "select * from (");
					sql.append(" ) t where t.station_Type like ?");
					values.add("%" + baseStation.getStationType() + "%");
			}
		}
		String totalSql = " select count(*) from " + "("+sql.toString()+")u" ;
		j.setTotal(params3Dao.countBySql(totalSql, values));// 设置总记录数
		if (dg.getSort() != null) {// 设置排序
			sql .append(" order by " + dg.getSort() + " " + dg.getOrder());
		}
		List<Params3> params3List = params3Dao.findBySql(sql.toString(), dg.getPage(), dg.getRows(), values);// 查询分页
		List<BaseStationBean> beanList = new ArrayList<BaseStationBean>();
		if (params3List != null && params3List.size() > 0) {// 转换模型
			for (Params3 par : params3List) {
				BaseStationBean baseStationBean = new BaseStationBean();
				baseStationBean.setLac(par.getLac());
				baseStationBean.setStationName(par.getStationName());
				baseStationBean.setId(par.getId());
				baseStationBean.setTransmissionMedium(par.getTransmissionMedium());
				baseStationBean.setStationType(par.getStationType());
				baseStationBean.setOpenTime1(par.getOpenTime());
				baseStationBean.setDetailAdress(par.getDetailAdress());
				beanList.add(baseStationBean);
			}
		}
		j.setRows(beanList);// 设置返回的行
		return j;
	}

	/**
	 * 
	 * @Title: datagrid
	 * @Description: 获取4g基站信息列表
	 * @param dg
	 * @param baseStation
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public DataGridJson datagrid4g(DataGridBean dg, BaseStationBean baseStation) {
		DataGridJson j = new DataGridJson();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT *  FROM  t_cyhc_worker_parameter_4g  WHERE recordStatus=?  GROUP BY LAC ");
		List<Object> values = new ArrayList<Object>();
		values.add(GlobalConstant.FLAG_Y);
		// 添加条件1
		if (StringUtil.isNotBlank(baseStation.getLac())) {
			sql.insert(0, "select * from (");
			sql.append(" ) t where t.LAC like ?");
			values.add("%" + baseStation.getLac() + "%");
			}
		String totalSql = " select count(*) from " + "("+sql.toString()+")u" ;
		j.setTotal(params4Dao.countBySql(totalSql, values));// 设置总记录数
		if (dg.getSort() != null) {// 设置排序
			sql .append(" order by " + dg.getSort() + " " + dg.getOrder());
		}
		List<Params4> paramsList = params4Dao.findBySql(sql.toString(), dg.getPage(), dg.getRows(), values);// 查询分页
		List<BaseStationBean> beanList = new ArrayList<BaseStationBean>();
		if (paramsList != null && paramsList.size() > 0) {// 转换模型
			for (Params4 par : paramsList) {
				BaseStationBean baseStationBean = new BaseStationBean();
				baseStationBean.setLac(par.getLac());
				baseStationBean.setStationName(par.getStationName());
				baseStationBean.setEnbIp(par.getEnbIp());
				baseStationBean.setOpenTime1(par.getOpenTime());
				beanList.add(baseStationBean);
			}
		}
		j.setRows(beanList);// 设置返回的行
		return j;
	}

	/**
	 * 
	 * @Title: delete
	 * @Description: 删除2g基站信息
	 * @param lacs
	 * @return
	 */
	@Override
	public Boolean deleteBaseStation2gByLacs(String[] lacs) {
		if(lacs==null||lacs.length<1){
			return false;
		}
		Params newParams=null;
		for(String lac : lacs){
			StringBuffer hql=new StringBuffer();
			hql.append(" from Params where lac= ?");
			List<Object> params=new ArrayList<Object>();
			params.add(lac);
			newParams=paramsDao.get(hql.toString(), params);
			if(newParams==null){
				return false;
			}
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
	 * @Description: 删除3g基站信息
	 * @param names
	 * @return
	 */
	@Override
	public Boolean deleteBaseStation3gByLacs(String[] lacs) {
		if(lacs==null||lacs.length<1){
			return false;
		}
		Params3 newParams3=new Params3();
		for(String lac : lacs){
			StringBuffer hql=new StringBuffer();
			hql.append(" from Params3 where lac= ?");
			List<Object> params=new ArrayList<Object>();
			params.add(lac);
			newParams3=params3Dao.get(hql.toString(), params);
			if(newParams3==null){
				return false;
			}else{
				if("1".equals(newParams3.getIsManually())){
					newParams3.setRecordStatus(GlobalConstant.FLAG_N);
					return true;
				}else{
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 
	 * @Title: delete
	 * @Description: 删除4g基站信息
	 * @param names
	 * @return
	 */
	@Override
	public Boolean deleteBaseStation4gByLacs(String[] lacs) {
		if(lacs==null||lacs.length<1){
			return false;
		}
		Params4 newParams4=null;
		for(String lac : lacs){
			StringBuffer hql=new StringBuffer();
			hql.append(" from Params4 where lac= ?");
			List<Object> params=new ArrayList<Object>();
			params.add(lac);
			newParams4=params4Dao.get(hql.toString(), params);
			if(newParams4==null){
				return false;
			}
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
	 * @Description: 修改或增加2g基站信息
	 * @param baseStation
	 * @return
	 */
	@Override
	public Boolean addOrUpdate2g(BaseStationBean baseStation) {
		boolean flag=false;
		if(baseStation.getId()==null){//添加
			//保存该条数据
			Params params=new Params();
			params.setLac(baseStation.getLac());
			params.setStationName(baseStation.getStationName());
			params.setStationType(baseStation.getStationType());
			params.setTransmissionMedium(baseStation.getTransmissionMedium());
			params.setOpenTime(timeStampUtil.stringToTimeStame(baseStation.getOpenTime()));
			params.setDetailAdress(baseStation.getDetailAdress());
			params.setRecordStatus(GlobalConstant.FLAG_Y);
			params.setIsManually("1");
			paramsDao.save(params);
			flag=true;
		}else{//修改
			Params newParams=paramsDao.get(Params.class,baseStation.getId());
			if(newParams==null){	
				return false;
			}
			if("1".equals(newParams.getIsManually())){
				newParams.setLac(baseStation.getLac());
				newParams.setStationName(baseStation.getStationName());
				newParams.setStationType(baseStation.getStationType());
				newParams.setTransmissionMedium(baseStation.getTransmissionMedium());
				newParams.setOpenTime(timeStampUtil.stringToTimeStame(baseStation.getOpenTime()));
				newParams.setDetailAdress(baseStation.getDetailAdress());
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
	 * @Description: 修改或增加3g基站信息
	 * @param baseStation
	 * @return
	 */
	@Override
	public Boolean addOrUpdate3g(BaseStationBean baseStation) {
		boolean flag=false;
		if(baseStation.getId()==null){//添加
			//保存该条数据
			Params3 params3=new Params3();
			params3.setLac(baseStation.getLac());
			params3.setStationName(baseStation.getStationName());
			params3.setStationType(baseStation.getStationType());
			params3.setTransmissionMedium(baseStation.getTransmissionMedium());
			params3.setOpenTime(timeStampUtil.stringToTimeStame(baseStation.getOpenTime()));
			params3.setDetailAdress(baseStation.getDetailAdress());
			params3.setRecordStatus(GlobalConstant.FLAG_Y);
			params3.setIsManually("1");
			params3Dao.save(params3);
			flag=true;
		}else{//修改
			Params3 newParams3=params3Dao.get(Params3.class,baseStation.getId());
			if(newParams3==null){	
				return false;
			}
			if("1".equals(newParams3.getIsManually())){
				newParams3.setLac(baseStation.getLac());
				newParams3.setStationName(baseStation.getStationName());
				newParams3.setStationType(baseStation.getStationType());
				newParams3.setTransmissionMedium(baseStation.getTransmissionMedium());
				newParams3.setOpenTime(timeStampUtil.stringToTimeStame(baseStation.getOpenTime()));
				newParams3.setDetailAdress(baseStation.getDetailAdress());
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
	 * @Description: 修改或增加4g基站信息
	 * @param baseStation
	 * @return
	 */
	@Override
	public Boolean addOrUpdate4g(BaseStationBean baseStation) {
		boolean flag=false;
		if(baseStation.getId()==null){//添加
			//保存该条数据
			Params4 params4=new Params4();
			params4.setLac(baseStation.getLac());
			params4.setStationName(baseStation.getStationName());
			params4.setEnbIp(baseStation.getEnbIp());
			params4.setOpenTime(timeStampUtil.stringToTimeStame(baseStation.getOpenTime()));
			params4.setRecordStatus(GlobalConstant.FLAG_Y);
			params4.setIsManually("1");
			params4Dao.save(params4);
			flag=true;
		}else{//修改
			Params4 newParams4=params4Dao.get(Params4.class,baseStation.getId());
			if(newParams4==null){	
				return false;
			}
			if("1".equals(newParams4.getIsManually())){
				newParams4.setLac(baseStation.getLac());
				newParams4.setStationName(baseStation.getStationName());
				newParams4.setEnbIp(baseStation.getEnbIp());
				newParams4.setOpenTime(timeStampUtil.stringToTimeStame(baseStation.getOpenTime()));
				flag=true;
			}else{
				flag=false;
			}
		}
		return flag;
	}
/**
 * 根据名称查询2gantenna
 */
	@Override
	public List<AntennaBean> find2gAntennaByLac(String lac) {
		List<AntennaBean> antennas=new ArrayList<AntennaBean>();
		StringBuffer hql=new StringBuffer();
		hql.append(" From Params where lac=? ");
		List<Object> list=new ArrayList<Object>();
		list.add(lac);
		List<Params> params=this.paramsDao.find(hql.toString(), list);
		if(params!=null){
			for(Params par:params){
				AntennaBean antenna=new AntennaBean();
				antenna.setId(par.getId());
				antenna.setAntennaType(par.getAntennaType());
				antenna.setAntennaManufacturer(par.getAntennaManufacturer());
				antennas.add(antenna);
			}
		}
		return antennas;
	}
	/**
	 * 根据名称查询3gantenna
	 */
	@Override
	public List<AntennaBean> find3gAntennaByLac(String lac) {
		List<AntennaBean> antennas=new ArrayList<AntennaBean>();
		StringBuffer hql=new StringBuffer();
		hql.append(" From Params3 where lac=? ");
		List<Object> list=new ArrayList<Object>();
		list.add(lac);
		List<Params3> params=this.params3Dao.find(hql.toString(), list);
		if(params!=null){
			for(Params3 par:params){
				AntennaBean antenna=new AntennaBean();
				antenna.setId(par.getId());
				antenna.setAntennaType(par.getAntennaType());
				antenna.setAntennaManufacturer(par.getAntennaManufacturer());
				antennas.add(antenna);
			}
		}
		return antennas;
	}
	/**
	 * 根据名称查询4gantenna
	 */
	@Override
	public List<AntennaBean> find4gAntennaByLac(String lac) {
		List<AntennaBean> antennas=new ArrayList<AntennaBean>();
		StringBuffer hql=new StringBuffer();
		hql.append(" From Params4 where lac=? ");
		List<Object> list=new ArrayList<Object>();
		list.add(lac);
		List<Params4> params=this.params4Dao.find(hql.toString(), list);
		if(params!=null){
			for(Params4 par:params){
				AntennaBean antenna=new AntennaBean();
				antenna.setId(par.getId());
				antenna.setAntennaType(par.getAntennaType());
				antenna.setAntennaManufacturer(par.getAntennaManufacturer());
				antennas.add(antenna);
			}
		}
		return antennas;
	}
}
