package com.yp.sys.service.resource;

import java.util.List;

import com.yp.sys.entity.resource.ResourceType;
import com.yp.sys.pojo.DataGridBean;
import com.yp.sys.pojo.DataGridJson;
import com.yp.sys.pojo.Json;
import com.yp.sys.service.common.IBaseService;
/**
 * 
 * 文件名称： 资源管理 资源类型： Service层
 * 内容摘要： 
 * 创建人： yinyong
 * 创建日期： 2015-6-5
 * 版本号： v1.0.0
 * 公  司：重庆重邮汇侧有限公司
 * 版权所有： (C)2001-2015     
 * 修改记录1 
 * 修改日期：
 * 版本号：
 * 修改人：
 * 修改内容：  
 *
 */
public interface IResourceTypeService extends IBaseService<ResourceType> {
	/**
	 * datagrid数据列表
	 * @param dg
	 * @param resourceType
	 * @return DataGridJson
	 */
	public DataGridJson datagrid(DataGridBean dg,ResourceType resourceType);
	/**
	 * <p>通过数据库 查找所有ResourceType</p>
	 * @return List<ResourceType>
	 */
	public List<ResourceType> searchAllResourceType();
	/**
	 * <p>添加或者修改一行ResourceType</p>
	 * @param resourceType
	 * @return Boolean
	 */
	public Boolean addOrUpdate(ResourceType resourceType);
	/**
	 * <p>通过ResourceType id删除一行ResourceType数据</p>
	 * @param ids
	 * @return Boolean
	 */
	public Boolean deleteResourceTypeById(String[] ids);
	/**
	 * <p>通过ResourceType id查找一行ResourceType</p>
	 * @param resourceType
	 * @return ResourceType
	 */
	public ResourceType searchResourceTypeById(ResourceType resourceType);
	/**
	 * <p>通过ResourceType number查找ResourceType</p>
	 * @param number
	 * @return Boolean
	 */
	public Boolean getResourceTypeByNumber(String number);
	/**
	 * <p>通过ResourceType 和操作类型  改变排序结果</p>
	 * @param resourceType
	 * @param manual
	 * @return Json
	 */
	public Json moveResourceType(ResourceType resourceType,String manual);
}
