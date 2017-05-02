package com.yp.sys.dao.common;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**   
 * 文件名称：基础dao接口 
 * 内容摘要： 所有dao接口继承该dao接口，实现基础功能
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
public interface IBaseDao<T> {

	/**
	 * 保存一个对象
	 * 
	 * @param o
	 *            对象
	 */
	public void save(T o);
	
	/**
	 * 保存多个对象
	 * @param o
	 */
	public void saveList(List<T> list);

	/**
	 * 保存一个新对象
	 * @Title: saveNew
	 *
	 * @param o
	 */
	public void saveNew(T o);
	
	/**
	 * 更新一个对象
	 * 
	 * @param o
	 *            对象
	 */
	public void update(T o);
	/**
	 * 批量更新
	 * @param list
	 */
	public void updateList(List<T> list);

	/**
	 * 刷新缓存 <br>
	 * 
	 * @param o
	 */
	public void refresh(T o);
	/**
	 * 立即提交更改内容 <br>
	 * 
	 * @param o
	 */
	public void commit(T o);

	/**
	 * 保存或更新对象
	 * 
	 * @param o
	 *            对象
	 */
	public void saveOrUpdate(T o);

	/**
	 * 合并一个对象
	 * 
	 * @param o
	 *            对象
	 */
	public void merge(T o);

	/**
	 * 删除一个对象
	 * 
	 * @param o
	 *            对象
	 */
	public void delete(T o);

	/**
	 * 查找对象集合
	 * 
	 * @param hql
	 * @param param
	 * @return List<T>
	 */
	public List<T> find(String hql, Object... param);

	/**
	 * 查找对象集合
	 * 
	 * @param hql
	 * @param param
	 * @return List<T>
	 */
	public List<T> find(String hql, List<Object> param);

	/**
	 * 查找对象集合,带分页
	 * 
	 * @param hql
	 * @param page
	 *            当前页
	 * @param rows
	 *            每页显示记录数
	 * @param param
	 * @return 分页后的List<T>
	 */
	public List<T> find(String hql, int page, int rows, List<Object> param);

	/**
	 * 查找对象集合,带分页
	 * 
	 * @param hql
	 * @param page
	 *            当前页
	 * @param rows
	 *            每页显示记录数
	 * @param param
	 * @return 分页后的List<T>
	 */
	public List<T> find(String hql, int page, int rows, Object... param);

	public List<T> findBySql(String sql, int page, int rows,List<Object> param);

	/**
	 * 获得一个对象
	 * 
	 * @param c
	 *            对象类型
	 * @param id
	 * @return Object
	 */
	public T get(Class<T> c, Serializable id);

	/**
	 * 获得一个对象
	 * 
	 * @param hql
	 * @param param
	 * @return Object
	 */
	public T get(String hql, Object... param);

	/**
	 * 获得一个对象
	 * 
	 * @param hql
	 * @param param
	 * @return Object
	 */
	public T get(String hql, List<Object> param);

	/**
	 * 获得一个对象
	 * 
	 * @param c
	 *            对象类型
	 * @param id
	 * @return Object
	 */
	public T load(Class<T> c, Serializable id);

	/**
	 * select count(*) from 类
	 * 
	 * @param hql
	 * @param param
	 * @return Long
	 */
	public Long count(String hql, Object... param);

	/**
	 * select count(*) from 类
	 * 
	 * @param hql
	 * @param param
	 * @return Long
	 */
	public Long count(String hql, List<Object> param);

	public Long countBySql(String sql, Object... param);
	public Long countBySql(String sql, List<Object> param);

	/**
	 * 执行HQL语句
	 * 
	 * @param hql
	 * @return 相应数目
	 */
	public Integer executeHql(String hql);

	public List<T> findBySql(String sql);
	

	public List<T> findBySql(String sql, Class<T> clazz);

	/**
	 * 执行SQL语句
	 * 
	 * @param sql
	 */
	public void executeSql(String sql);

	/**
	 * 根据sql查询 返回集合中为每个字段名为key和值为value的map
	 * 
	 * @param sql
	 * @return
	 */
	public List<Map<String, Object>> searchMapBySql(String sql);
	/**
	 * 从session中移除对象 
	 * @param object void
	 */
	public void removeObjFromSession(Object object);
	/**
	 * 返回object
	 * @param sql
	 * @param page
	 * @param rows
	 * @param param
	 * @return
	 */
	List<T> findByObjSql(String sql, int page, int rows,
			List<Object> param);
	/**
	 * 获取jdbc操作的初始化信息
	 * @return
	 */
	public Map<String,Object> getSessionInfo();
}
