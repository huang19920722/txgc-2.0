package com.yp.sys.service.params;

import java.util.List;
import java.util.Map;

import com.yp.sys.entity.params.Params;
import com.yp.sys.pojo.DataGridBean;
import com.yp.sys.pojo.DataGridJson;
import com.yp.sys.pojo.ParamsBean;
import com.yp.sys.pojo.ParamsBean3;
import com.yp.sys.pojo.ParamsBean4;
import com.yp.sys.pojo.ThresoldInfoBean;
import com.yp.sys.service.common.IBaseService;


/**
 * 
 * 版权所有：2016-重庆重邮汇测通讯技术有限公司
 * 项目名称：txgc   
 *
 * 类描述：工参管理Service接口
 * 类名称：com.yp.sys.service.params.IParamsService     
 * 创建人：zhongyang
 * 创建时间：2016-11-16 下午3:12:31   
 * 修改人：
 * 修改时间：2016-11-16 下午3:12:31   
 * 修改备注：   
 * @version   V1.0
 */
public interface IParamsService extends IBaseService<Params> {
	
	/**
	 * 
	 * @Title: datagrid
	 * @Description: 获取2g工参管理列表
	 * @param dg
	 * @param params
	 * @return
	 */
	public DataGridJson datagrid2g(DataGridBean dg, ParamsBean params);
	
	/**
	 * 
	 * @Title: datagrid
	 * @Description: 获取3g工参管理列表
	 * @param dg
	 * @param params
	 * @return
	 */
	public DataGridJson datagrid3g(DataGridBean dg, ParamsBean3 params);
	
	/**
	 * 
	 * @Title: datagrid
	 * @Description: 获取4g工参管理列表
	 * @param dg
	 * @param params
	 * @return
	 */
	public DataGridJson datagrid4g(DataGridBean dg, ParamsBean4 params);
	
	/**
	 * @Title: executeUtil
	 * @Description: 获取码表中的无限厂商设备
	 * @return
	 */
	public Map<String, String> executeUtil();
	
	/**
	 * @Title: vilageAdd
	 * @Description: 添加阀值
	 * @param params
	 * @return
	 */
	public ThresoldInfoBean addVilage(ThresoldInfoBean info);
	
	/**
	 * @Title: vilageAdd
	 * @Description: 添加阀值
	 * @param params
	 * @return
	 */
	public ThresoldInfoBean addVilage3(ThresoldInfoBean info);
	
	/**
	 * @Title: vilageAdd
	 * @Description: 添加阀值
	 * @param params
	 * @return
	 */
	public ThresoldInfoBean addVilage4(ThresoldInfoBean info);
	
	/**
	 * 
	 * @Title: datagridThr
	 * @Description: 获取阀值管理datagrid
	 * @param dg
	 * @param params
	 * @return
	 */
	public DataGridJson datagridThr(DataGridBean dg, ThresoldInfoBean infoBen);
	
	
	
	/**
	 * @Title: get2gParams
	 * @Description: 查询2G天线工程数据
	 * @return
	 */
	public List<ParamsBean> get2gParams();
	
	/**
	 * @Title: add2gParams
	 * @Description: 增加2G工参数据
	 * @param params
	 * @return
	 */
	public ParamsBean add2gParams(ParamsBean params);

	/**
	 * 
	 * @Title: edit2gParams
	 * @Description: 修改2G工参数据
	 * @param params
	 * @return
	 */
	public ParamsBean edit2gParams(ParamsBean params);
	
	/**
	 * @Title: delParams
	 * @Description: 删除2G工参数据
	 * @param params
	 */
	public void del2gParams(ParamsBean params);
	
	/**
	 * 通过id查询2g标准工参信息
	 * @param id
	 * @return
	 */
	public ParamsBean query2gParamById(Long id);
	/**
	 * 通过id查询3g标准工参信息
	 * @param id
	 * @return
	 */
	public ParamsBean3 query3gParamById(Long id);
	/**
	 * 通过id查询4g标准工参信息
	 * @param id
	 * @return
	 */
	public ParamsBean4 query4gParamById(Long id);
	
}
