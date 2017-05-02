package com.yp.sys.service.collect;

import java.util.List;

import com.yp.sys.entity.Image;
import com.yp.sys.entity.collect.CollectInfo;
import com.yp.sys.pojo.CollectInfoBean;
import com.yp.sys.pojo.DataGridBean;
import com.yp.sys.pojo.DataGridJson;
import com.yp.sys.pojo.Json;
import com.yp.sys.service.common.IBaseService;
/**
 * 工参采集信息service接口层
 * @author huangmingxing
 *
 */
public interface ICollectInfoService extends IBaseService<CollectInfo> {
	/**
	 * 工参采集信息datagrid
	 * @param collectInfoBean
	 * @return
	 */
	public DataGridJson collectInfoDataGrid(DataGridBean dg,CollectInfoBean collectInfoBean);
	/**
	 * 查询采集信息
	 * @param collectInfoBean,dg
	 * @return
	 */
	public List<CollectInfo> queryCollectInfo(DataGridBean dg,CollectInfoBean collectInfoBean);
	/**
	 * 更新工单状态
	 * @param state
	 * @return
	 */
	public String updateWorkOrderState(CollectInfoBean collectInfoBean);
	/**
	 * 更新订单采集字段的所有字段信息进入标准工参信息表
	 * @return
	 */
	public String updateColInfoToPara(CollectInfoBean collectInfoBean);
	
	 /**
	  * 查询待审核总数
	  * @return
	  */
	 public Json queryAuditCount();

	 /**
	  * 通过工单ID查询采集信息
	  * @param workOrderId
	  * @return
	  */
	 public CollectInfo queryCollectInfoByWorkOrderId(Long workOrderId);
	

	/**
	 * 根据picType查询图片
	 * @return
	 */
	 public List<Image> findImageByType(int picType,Long workOrderId);
	 
	 /**
	  * 根据工单ID查询图片
	  * @param workParamId
	  * @return List<Image>
	  */
	List<Image> findImagesByWorkOrderId(Long workOrderId);
	
	/**
	 * 根据工单ID查询图片的类型有哪几种
	 * @param workParamId
	 * @return List<Integer>
	 */
	List<Integer> findImagesTypeByWorkOrderId(Long workOrderId);
	
	 /**
	  * 根据工参ID查询图片
	  * @param workParamId
	  * @return List<Image>
	  */
	List<Image> findImagesByWorkParamIdT(String workParamId);
	
	/**
	 * 根据工参ID查询图片的类型有哪几种
	 * @param workParamId
	 * @return List<Integer>
	 */
	List<Integer> findImagesTypeByWorkParaIdT(String workParamId);
	

}
