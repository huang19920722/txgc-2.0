package com.yp.sys.service.workorder;

import java.util.List;
import java.util.Map;


import com.yp.sys.entity.collect.CollectInfo;
import com.yp.sys.entity.terminal.TerminalUseInfo;
import com.yp.sys.entity.workorder.CommunityInfo;

import com.yp.sys.entity.workorder.WorkOrderInfo;
import com.yp.sys.pojo.CreatWorkOrderBean;
import com.yp.sys.pojo.DataGridBean;
import com.yp.sys.pojo.DataGridJson;
import com.yp.sys.pojo.Json;
import com.yp.sys.pojo.TreeNodeBean;
import com.yp.sys.pojo.WorkOrderInfoBean;
import com.yp.sys.service.common.IBaseService;

/**
 * @Description: 工单管理service接口
 * @author youxingliu
 * @date 2016年11月16日 下午3:54:02
 */

public interface IWorkOrderService extends IBaseService<WorkOrderInfo>{
	/**
	 * 终端使用信息grid
	 * @param param
	 * @param bean
	 * @return
	 */
	DataGridJson terminalUseInfoGrid(TerminalUseInfo param,DataGridBean bean);
	/**
	 * 工单信息grid
	 * @param param
	 * @param bean
	 * @return
	 */
	DataGridJson workOrderGrid(WorkOrderInfoBean param,DataGridBean bean);
	/**
	 * 终端采集信息grid
	 * @param param
	 * @param bean
	 * @return
	 */
	DataGridJson collectGrid(CollectInfo param,DataGridBean bean);
	/**
	 * 小区信息grid
	 * @param param
	 * @param bean
	 * @return
	 */
	DataGridJson communityGrid(CommunityInfo param,DataGridBean bean);
	/**
	 * 创建工单
	 * @param param
	 * @return
	 */
	public Json createOrder(CreatWorkOrderBean paramBean);
	/**
	 * 用户树
	 * @return
	 */
	List<TreeNodeBean> userTree();
	/**
	 * 用户自定义列
	 * @param tableName
	 * @return
	 */
	List<Object> defineColumns(String appNode);
	
	/**
	 *查询工单自定义列表
	 * @param appNode
	 * @param workOrderId
	 * @return
	 */
	List<Object> findWorkOrderDefCols(String appNode,Long workOrderId);
	/**
	 * 同步数据
	 * @param content
	 * @param workOrderId
	 * @param explainRemark
	 * @return
	 */
	 Json  saveUpdateDefCols(String content,Long workOrderId,String explainRemark);
	
	/**
	 * 保存自定义列
	 * @param appNode
	 * @param content
	 * @return
	 */
	String saveColumns(String appNode,String content);
	/**
	 * 查询采集信息所有字段和注释
	 * @return
	 */
	public List<Object> queryColletInfoWords();

	/**
	 * 查询工单采集数据以及采集字段
	 * @param workOrderId
	 * @return Map<String,String>
	 */
	public Map<String,Object> queryWorkOrderDtail(Long workOrderId);
	/**
	 * 删除工单
	 * @param workOrderId
	 * @return Json
	 */
	public Json deleteWorkOrder(Long workOrderId);
	

}
