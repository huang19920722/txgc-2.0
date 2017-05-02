package com.yp.sys.service.antenna;

import java.util.List;
import java.util.Map;

import com.yp.sys.entity.params.Params;
import com.yp.sys.pojo.AntennaBean;
import com.yp.sys.pojo.DataGridBean;
import com.yp.sys.pojo.DataGridJson;
import com.yp.sys.service.common.IBaseService;
/**
 * 内容摘要： 天线信息service层
 * 创建人： nihui
 * 创建日期： 2016-11-25
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
public interface IAntennaService extends IBaseService<Params> {


	/**
	 * 
	 * @Title: datagrid
	 * @Description: 获取2g工参管理列表
	 * @param dg
	 * @param params
	 * @return
	 */
	public DataGridJson datagrid2g(DataGridBean dg, AntennaBean antenna);
	
	/**
	 * 
	 * @Title: datagrid
	 * @Description: 获取3g工参管理列表
	 * @param dg
	 * @param params
	 * @return
	 */
	public DataGridJson datagrid3g(DataGridBean dg, AntennaBean antenna);
	
	/**
	 * 
	 * @Title: datagrid
	 * @Description: 获取4g工参管理列表
	 * @param dg
	 * @param params
	 * @return
	 */
	public DataGridJson datagrid4g(DataGridBean dg, AntennaBean antenna);
	/**
	 * <p>通过 id删除一行2gantenna数据</p>
	 * @param ids
	 * @return Boolean
	 */
	public Boolean deleteAntenna2gById(String[] ids);
	/**
	 * <p>通过 id删除一行3gantenna数据</p>
	 * @param ids
	 * @return Boolean
	 */
	public Boolean deleteAntenna3gById(String[] ids);
	/**
	 * <p>通过 id删除一行4gantenna数据</p>
	 * @param ids
	 * @return Boolean
	 */
	public Boolean deleteAntenna4gById(String[] ids);
	
	/**
	 * 添加或修改一行AntennaBean2g
	 * @param antennaBean
	 * @return
	 */
	public Boolean addOrUpdate2g(AntennaBean antennaBean);
	/**
	 * 添加或修改一行AntennaBean3g
	 * @param antennaBean
	 * @return
	 */
	public Boolean addOrUpdate3g(AntennaBean antennaBean);
	/**
	 * 添加或修改一行AntennaBean3g
	 * @param antennaBean
	 * @return
	 */
	public Boolean addOrUpdate4g(AntennaBean antennaBean);
}
