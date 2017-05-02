package com.yp.sys.service.resource.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yp.sys.common.GeneralMethod;
import com.yp.sys.common.GlobalConstant;
import com.yp.sys.dao.resource.IResourceTypeDao;
import com.yp.sys.entity.resource.ResourceType;
import com.yp.sys.pojo.DataGridBean;
import com.yp.sys.pojo.DataGridJson;
import com.yp.sys.pojo.Json;
import com.yp.sys.service.common.impl.BaseServiceImpl;
import com.yp.sys.service.resource.IResourceTypeService;
import com.yp.sys.util.IdGenerator;
import com.yp.sys.util.StringUtil;
/**
 * 
 * 文件名称： 资源类型 Service 接口的实现类
 * 内容摘要： 
 * 创建人： yinyong
 * 创建日期： 2015-6-18
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
@Service("resourceTypeService")
public class ResourceTypeServiceImpl extends BaseServiceImpl<ResourceType> implements IResourceTypeService {
	@Autowired
	private IResourceTypeDao resourceTypeDao;
	@Override
	public DataGridJson datagrid(DataGridBean dg, ResourceType resourceType) {
		DataGridJson json = new DataGridJson();
		List<Object> values = new ArrayList<Object>();
		StringBuffer hql =new StringBuffer();
		hql.append("FROM ResourceType AS sourcetype");
		hql.append(" WHERE recordStatus='").append(GlobalConstant.FLAG_Y).append("' ");
		//添加条件1
		if(StringUtil.isNotBlank(resourceType.getNumber())){
			hql.append(" AND number LIKE ?");
			values.add("%"+resourceType.getNumber()+"%");
		}
		//添加条件2
		if(StringUtil.isNotBlank(resourceType.getName())){
			hql.append(" AND name LIKE ?");
			values.add("%"+resourceType.getName()+"%");
		}
		String totalHql = " SELECT COUNT(*) " + hql;
		//设置总记录数
		json.setTotal(resourceTypeDao.count(totalHql, values));
		//设置排序（默认排序）
		if (dg.getSort()!=null) {
			hql.append(" ORDER BY " + dg.getSort() + " " + dg.getOrder());
		}else{
			hql.append(" ORDER BY sortNum DESC ");
		}
		//通過hql查詢 并將結果放入json
		json.setRows(resourceTypeDao.find(hql.toString(), dg.getPage(), dg.getRows(), values));

		return json;
	}

	@Override
	public List<ResourceType> searchAllResourceType() {
		String hql="FROM ResourceType WHERE recordStatus='"+GlobalConstant.FLAG_Y+"'";
		return resourceTypeDao.find(hql);
	}

	@Override
	public Boolean addOrUpdate(ResourceType resourceType) {
		boolean flag=false;
		if(resourceType.getId()==null){//添加
			resourceType.setSortNum(IdGenerator.getLongValue());
			//设置通用信息
			GeneralMethod.setRecordInfo(resourceType, true);
			//保存该条数据
			resourceTypeDao.save(resourceType);
			flag=true;
		}else{//修改
			ResourceType newResourceType=resourceTypeDao.get(ResourceType.class,resourceType.getId());
			if(newResourceType==null){	
				return false;
			}
			newResourceType.setNumber(resourceType.getNumber());
			newResourceType.setName(resourceType.getName());
			GeneralMethod.setRecordInfo(newResourceType, false);
			flag=true;
		}
		return flag;
	}

	@Override
	public Boolean deleteResourceTypeById(String[] ids) {
		if(ids==null||ids.length<1){
			return false;
		}
		ResourceType newresourceType=null;
		for(String id : ids){
			Long idLong=Long.parseLong(id);
			newresourceType=resourceTypeDao.get(ResourceType.class, idLong);
			if(newresourceType==null){
				return false;
			}
			newresourceType.setRecordStatus(GlobalConstant.FLAG_N);
			//设置通用信息
			GeneralMethod.setRecordInfo(newresourceType, false);

		}
		return true;
	}

	@Override
	public ResourceType searchResourceTypeById(ResourceType resourceType) {
		if(resourceType==null||resourceType.getId()==null){
			return null;
		}
		return resourceTypeDao.get(ResourceType.class, resourceType.getId());


	}

	@Override
	public Boolean getResourceTypeByNumber(String number) {
		String hql="FROM ResourceType WHERE recordStatus='"+GlobalConstant.FLAG_Y
				+"' AND number='"+number+"'";
		List<ResourceType> resourceList=resourceTypeDao.find(hql);
		if(resourceList.size()>0){
			//找到了就返回false
			return false;
		}else{
			//没有找到就返回true 
			return true;
		}
	}

	@SuppressWarnings("null")
	@Override
	public Json moveResourceType(ResourceType resourceType, String manual) {
		boolean flag=false;
		Json json = new Json();
		StringBuffer hql=new StringBuffer();
		if(resourceType==null&&resourceType.getId()==null&&StringUtil.isBlank(manual)){
			json.setSuccess(flag);
			return json;
		}
		ResourceType nowresourceType=resourceTypeDao.get(ResourceType.class, resourceType.getId());
		if(nowresourceType==null){
			json.setSuccess(flag);
			return json;
		}
		hql.append(" FROM ResourceType As resourceType ");
		hql.append(" WHERE recordStatus=? ");
		/*条件查询判断========================================*/
		try{
			if(manual.equals("up")){
				hql.append(" and resourceType.sortNum > ? ORDER BY sortNum asc ) ");
			}else if(manual.equals("down")){
				hql.append(" and resourceType.sortNum < ? ORDER BY sortNum desc ) ");
			}
			List<ResourceType> newResourceTypes=resourceTypeDao.find(hql.toString(), GlobalConstant.FLAG_Y,nowresourceType.getSortNum());
			if(newResourceTypes!=null){
				ResourceType lastResourceType=newResourceTypes.get(0);
				Long sort=lastResourceType.getSortNum();
				lastResourceType.setSortNum(nowresourceType.getSortNum());
				nowresourceType.setSortNum(sort);
				/*设置通用信息*/
				GeneralMethod.setRecordInfo(nowresourceType, false);
				GeneralMethod.setRecordInfo(lastResourceType, false);
				flag=true;
			}


			/*条件查询判断========================================end*/ 
			/*当前培训类型和上一条培训类型交换sort============================*/
		}catch(Exception e){
			e.printStackTrace();
		}
		return json;
	}

}
