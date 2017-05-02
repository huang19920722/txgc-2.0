package com.yp.sys.service.baseStation;

import java.util.List;

import com.yp.sys.entity.params.Params;
import com.yp.sys.pojo.AntennaBean;
import com.yp.sys.pojo.BaseStationBean;
import com.yp.sys.pojo.DataGridBean;
import com.yp.sys.pojo.DataGridJson;
import com.yp.sys.service.common.IBaseService;

public interface IBaseStationService extends IBaseService<Params> {

	/**
	 * 
	 * @Title: datagrid
	 * @Description: 获取2g工参管理列表
	 * @param dg
	 * @param baseStationBean
	 * @return
	 */
	public DataGridJson datagrid2g(DataGridBean dg,BaseStationBean baseStationBean );
	
	/**
	 * 
	 * @Title: datagrid
	 * @Description: 获取3g工参管理列表
	 * @param dg
	 * @param baseStationBean
	 * @return
	 */
	public DataGridJson datagrid3g(DataGridBean dg,BaseStationBean baseStationBean );
	
	/**
	 * 
	 * @Title: datagrid
	 * @Description: 获取4g工参管理列表
	 * @param dg
	 * @param baseStationBean
	 * @return
	 */
	public DataGridJson datagrid4g(DataGridBean dg, BaseStationBean baseStationBean);
	/**
	 * <p>通过 id删除一行2gBaseStation数据</p>
	 * @param ids
	 * @return Boolean
	 */
	public Boolean deleteBaseStation2gByLacs(String[] lacs);
	/**
	 * <p>通过 id删除一行3gBaseStation数据</p>
	 * @param ids
	 * @return Boolean
	 */
	public Boolean deleteBaseStation3gByLacs(String[] lacs);
	/**
	 * <p>通过 id删除一行4gBaseStation数据</p>
	 * @param ids
	 * @return Boolean
	 */
	public Boolean deleteBaseStation4gByLacs(String[] lacs);
	
	/**
	 * 添加或修改一行BaseStationBean2g
	 * @param baseStationBean
	 * @return
	 */
	public Boolean addOrUpdate2g(BaseStationBean baseStationBean);
	/**
	 * 添加或修改一行BaseStationBean3g
	 * @param baseStationBean
	 * @return
	 */
	public Boolean addOrUpdate3g(BaseStationBean baseStationBean);
	/**
	 * 添加或修改一行BaseStationBean3g
	 * @param baseStationBean
	 * @return
	 */
	public Boolean addOrUpdate4g(BaseStationBean baseStationBean);
	/**
	 * 根据2g基站名称查询基站所用天线
	 * @param stationName
	 * @return
	 */
	public List<AntennaBean> find2gAntennaByLac(String lac);
	/**
	 * 根据3g基站名称查询基站所用天线
	 * @param stationName
	 * @return
	 */
	public List<AntennaBean> find3gAntennaByLac(String lac);
	/**
	 * 根据4g基站名称查询基站所用天线
	 * @param stationName
	 * @return
	 */
	public List<AntennaBean> find4gAntennaByLac(String lac);
}
