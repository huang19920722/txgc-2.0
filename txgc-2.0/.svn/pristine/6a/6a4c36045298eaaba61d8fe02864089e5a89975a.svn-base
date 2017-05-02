package com.yp.sys.service.common;



/**   
 * 文件名称：基础service接口 
 * 内容摘要： 所有service接口都继承该接口
 * 创建人： huangfei
 * 创建日期： 2015年4月29日
 * 版本号： v1.0.0
 * 公  司：重庆重邮汇侧有限公司
 * 版权所有： (C)2001-2015     
 * 修改记录1 
 * 修改日期：
 * 版本号：
 * 修改人：
 * 修改内容：  
 **/ 
public interface IBaseService<T> {
	
	/**根据主键查找实体 
	 * @param id
	 * @return T
	 */
	public T getById(Long id);
	/**
	 * 保存实体 
	 * @param obj void
	 */
	public T save(T obj);
	/**
	 * 根据主键删除实体 
	 * @param ids
	 * @return boolean
	 */
	public void deleteById(String... ids);
	/** 
	 * 更新实体
	 * @param obj void
	 */
	public void update(T obj);
}
