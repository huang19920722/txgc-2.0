package com.yp.sys.service.rtu;


import java.util.List;
import java.util.Map;

import com.yp.sys.entity.rtu.RtuBaseInfo;
import com.yp.sys.entity.rtu.RtuConfigInfo;
import com.yp.sys.entity.rtu.RtuInfo;
import com.yp.sys.entity.rtu.RtuProperty;
import com.yp.sys.entity.rtu.RtuRefer;
import com.yp.sys.entity.rtu.RtuSlaveInfo;
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
import com.yp.sys.pojo.ZtreeBean;
import com.yp.sys.service.common.IBaseService;

public interface IRtuService extends IBaseService<RtuInfo> {
	/**
	 * 测试代码
	 * @param dg
	 * @param rtuInfoBean
	 * @return DataGridJson
	 */
	 DataGridJson rtuInfoDataGrid(DataGridBean dg,RtuInfoBean rtuInfoBean);
	 /**
	  * 查询rtu信息
	  * @param dg
	  * @param rtuInfoBean
	  * @return DataGridJson
	  */
	 DataGridJson rtuBaseDataGrid(DataGridBean dg,RtuBaseInfoBean rtuInfoBean);
	 
	 /**
	  * 查询采集信息
	  * @param dg
	  * @param rtuInfoBean
	  * @return DataGridJson
	  */
	 DataGridJson rtuCollectInfoDataGrid(DataGridBean dg,RtuCollectInfoBean rtuInfoBean);
	/**
	 * 查询配置信息
	 * @param dg
	 * @param rtuInfoBean
	 * @return DataGridJson
	 */
	 DataGridJson rtuConfigDataGrid(DataGridBean dg,RtuConfigInfoBean rtuInfoBean);
	 /**
	  * 查询展示RTU属性的码表
	  * @return Map<String,String>
	  */
	 Map<String,String> queryRtuPopertyReferMap();
	 /**
	  * 查询某一条采集信息的详情信息
	  * @param collectId
	  * @return List<RtuCollectInfoPojo>
	  */
	 List<RtuCollectInfoPojo> changeJsonToList(Long collectId);
	/**
	 * 通过Pid组装rtu模板Ztree数据项 
	 * @param pid
	 * @return List<ZtreeBean>
	 */
	 List<ZtreeBean> createRtuModelZtree(Long pid);
	/**
	 * 码表配置查询所有节点组成树结构
	 * @param pid
	 * @return
	 */
	List<ZtreeBean> buildRtuModelZtree(Long pid);
	/**
	 * 通过Pid组装rtu模板Ztree数据项 并填充已保存的数据
	 * @param pid
	 * @return List<ZtreeBean>
	 */
	List<ZtreeBean> editRtuModelZtree(Long pid,String slaveId);
	/**
	 * 根据属性上级ID查询下级节点
	 * @param pid
	 * @return List<RtuProperty>
	 */
	List<RtuProperty> querySlaveBaseProperty(Long pid);

	/**
	 * 根据属性上级ID查询下级节点并修改值
	 * @param pid
	 * @return
	 */
	List<RtuProperty> editSlaveBaseProperty(Long pid,Long slaveId);
	/**
	 * 根据属性当前ID查询当前节点
	 * @param pid
	 * @return List<RtuProperty>
	 */
	List<RtuProperty> querySlaveBasePropertyById(Long id);
	/**
	 * 根据属性当前ID查询当前节点并赋值
	 * @param id
	 * @return
	 */
	List<RtuProperty> editSlaveBasePropertyById(Long id,Long slaveId);
	
	/**
	 * 根据属性ID查询属性值
	 * @param propertyId
	 * @return List<RtuRefer>
	 */
	List<RtuRefer> queryRegPropertyList(Long propertyId);
	/**
	 * 根据属性ID查询属性值并判断是否选中
	 * @param propertyId
	 * @param SlaveId
	 * @return
	 */
	List<RtuRefer> editRegPropertyList(Long propertyId,Long SlaveId);
	
	/**
	 * 保存采集器信息
	 * @param content
	 * @return String
	 */
	SlaveInfo ajaxSaveSlave(String content);
	/**
	 * 查询采集器的列表
	 * @param dg
	 * @param slaveInfoBean
	 * @return DataGridJson
	 */
	List<SlaveInfo> slaveInfoDataGrid(SlaveInfoBean slaveInfoBean);
	/**
	 * 新增的配置信息
	 * @param rtuId
	 * @param pubDelay
	 * @param heartDelay
	 * @param translateContent
	 * @param slaveIds
	 * @return RtuConfigInfo
	 */
	RtuConfigInfo addRtuConfig(String rtuId,String pubDelay,String heartDelay,String translateContent,String slaveIds);
	/**
	 * 根据配置信息保存传输配置信息
	 * @param translateContent
	 * @param rtuConfigInfo void
	 */
	 void saveListTranProperty(String translateContent,RtuConfigInfo rtuConfigInfo);
	 /**
	  * 配置和采集器挂关系
	  * @param slaveIds
	  * @param rtuConfigInfo void
	  */
	 void updateListSlaveinfo(String slaveIds,RtuConfigInfo rtuConfigInfo);
	 /**
	  * 组装JSON
	  * @param configId
	  * @param pubDelay
	  * @param heartDelay
	  * @return String
	  */
	 String makeUpJson(Long configId);
	 /**
	  * 查询采集器的信息列表
	  * @param dg
	  * @param rtuSlaveInfo
	  * @return DataGridJson
	  */
	 DataGridJson rtuSlaveBaseInfoDataGrid(DataGridBean dg,RtuSlaveInfoBean rtuSlaveInfo);
	 /**
	  * 保存采集器基础信息
	  * @param rtuSlaveInfo
	  * @return String
	  */
	String saveRtuSlaveBaseInfo(RtuSlaveInfoBean rtuSlaveInfoBean);
	/**
	 * 查询采集器基本信息并进行复制操作
	 * @param configId
	 * @return
	 */
	public List<SlaveInfoBean> findSlaveInfo(Long configId);
	/**
	 * 查询采集器基本信息
	 * @param configId
	 * @return
	 */
	public List<SlaveInfoBean> lookSlaveInfo(Long configId);
	/**
	 * 根据配置ID查询传输配置信息
	 * @param configId
	 * @return
	 */
	public List<RtuTranslateProertyBean> findTransInfo(Long configId);
	/**
	 * 保存RTU基础信息
	 * @param rtuBase
	 * @return boolean
	 */
	boolean addOrUpdateRTUBase(RtuBaseInfo rtuBaseInfo);
	/**
	 * 删除RTU基础信息
	 * @param id
	 * @return boolean
	 */
	boolean deleteRTUBaseById(long id);
	/**
	 * 删除属性配置信息
	 * @param id
	 * @return
	 */
	String deleteRtuPro(Long id);
	/**
	 * 删除采集器信息
	 * @param slaveId
	 * @return
	 */
	public String deleteSlaveInfo(Long slaveId);
	
	/**
	 * 删除基础slave信息
	 * @param id
	 * @return String
	 */
	String deleteSlaveBase(Long id);
	/**
	 * 更新rtu配置状态字段
	 * @param rtuId
	 * @return String
	 */
	String updateRtuState(Long rtuId);
	/**
	 * 删除未下发的配置信息
	 * @param configId
	 * @return
	 */
	public String deleteConfigFun(Long configId);
	
	/**
	 * 保存各项配置属性
	 * @return
	 */
	public String addPro(RtuPropertyBean proBean);
	/**
	 * 添加配置属性组
	 * @param proBean
	 * @return
	 */
	public String addProGroup(RtuPropertyBean proBean);
}
