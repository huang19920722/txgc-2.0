package com.yp.sys.dao.common.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.service.jdbc.connections.spi.ConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.yp.sys.dao.common.IBaseDao;
import com.yp.sys.util.StringUtil;



/**   
 * 文件名称：基础dao实现类 
 * 内容摘要： 所有dao继承该dao，实现基础功能
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
@Repository("baseDao")
@SuppressWarnings("unchecked")
public class BaseDaoImpl<T> implements IBaseDao<T> {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DruidDataSource dataSource;
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void save(T o) {
		this.getCurrentSession().save(o);
	}
	
	public void saveNew(T o) {
		Session session  = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(o);
		tx.commit();
	}

	public void update(T o) {
		this.getCurrentSession().update(o);
	}

	public void refresh(T o) {
		this.getCurrentSession();
		this.getCurrentSession().evict(o);
	}

	public void saveOrUpdate(T o) {
		this.getCurrentSession().saveOrUpdate(o);
	}

	public void merge(T o) {
		this.getCurrentSession().merge(o);
	}

	public void delete(T o) {
		this.getCurrentSession().delete(o);
	}

	public List<T> find(String hql, List<Object> param) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		List<T> list = q.list();
		return list;
	}

	public List<T> find(String hql, Object... param) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}

		return q.list();
	}

	public List<T> find(String hql, int page, int rows, List<Object> param) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	public List<T> find(String hql, int page, int rows, Object... param) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	@SuppressWarnings("rawtypes")
	public List<T> findBySql(String sql, int page, int rows,
			List<Object> param) {
		Class clazz = getClass();
		Class <T> entityClass = null;
		String name = BaseDaoImpl.class.getName();
		if(StringUtil.isNotEquals(clazz.getName(), name)){
			entityClass = (Class <T>) ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[0];
			
		}
		Query q = this.getCurrentSession().createSQLQuery(sql).addEntity(entityClass);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}
	public List<T> findByObjSql(String sql, int page, int rows,
			List<Object> param) {
		Query q = this.getCurrentSession().createSQLQuery(sql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	public T get(Class<T> c, Serializable id) {
		return (T) this.getCurrentSession().get(c, id);
	}

	@SuppressWarnings("rawtypes")
	public T get(String hql, Object... param) {
		List l = this.find(hql, param);
		if (l != null && l.size() > 0) {
			return (T) l.get(0);
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public T get(String hql, List<Object> param) {
		List l = this.find(hql, param);
		if (l != null && l.size() > 0) {
			return (T) l.get(0);
		}
		return null;
	}

	public T load(Class<T> c, Serializable id) {
		return (T) this.getCurrentSession().load(c, id);
	}

	public Long count(String hql, Object... param) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return (Long) q.uniqueResult();
	}

	public Long countBySql(String sql, Object... param) {
		Long result = new Long(0);
		Query q = this.getCurrentSession().createSQLQuery(sql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		List<?> lists = q.list();
		if (null != lists && lists.size() > 0) {
			result = Long.valueOf(lists.get(0) + "");
		}
		return result;
	}

	public Long count(String hql, List<Object> param) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		return (Long) q.uniqueResult();
	}

	public Integer executeHql(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		return q.executeUpdate();
	}

	public List<T> findBySql(String sql) {
		Query query = this.getCurrentSession().createSQLQuery(sql);
		return query.list();
	}

	public List<T> findBySql(String sql, Class<T> clazz) {
		Query query = this.getCurrentSession().createSQLQuery(sql).addEntity(
				clazz);
		return query.list();
	}

	@Override
	public void executeSql(String sql) {
		this.getCurrentSession().createSQLQuery(sql).executeUpdate();
	}

	public List<Map<String, Object>> searchMapBySql(String sql) {
		SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		List<?> lists = query.list();
		List<Map<String, Object>> results = new ArrayList<Map<String,Object>>();
		if (null != lists && lists.size() > 0) {
			results = new ArrayList<Map<String, Object>>();
			for (int i = 0; i < lists.size(); i++) {
				Map<String, Object> temp = (Map<String, Object>) lists.get(i);
				// System.out.println("ee:"+temp.get("ID")+"ff:"+temp.get("NAME"));
				results.add(temp);
			}
		}
		return results;
	}

	@Override
	public void removeObjFromSession(Object object) {
		this.getCurrentSession().evict(object);
	}

	@Override
	public void commit(T o) {
		this.getCurrentSession().flush();
	}

	@Override
	public Long countBySql(String sql, List<Object> param) {
		Long result = new Long(0);
		Query q = this.getCurrentSession().createSQLQuery(sql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		List<?> lists = q.list();
		if (null != lists && lists.size() > 0) {
			result = Long.valueOf(lists.get(0) + "");
		}
		return result;
	}
	
	@Override
	public void saveList(List<T> list) {
			Session session = null;
			if(null != list && list.size()>0){
				try {  
					session = sessionFactory.openSession(); // 获取Session  
					session.beginTransaction(); // 开启事物  
		
					for (int i = 0; i < list.size(); i++) {  
						session.save(list.get(i));   
						// 批插入的对象立即写入数据库并释放内存  
						if (i % 10 == 0) {  
							session.flush();  
							session.clear();  
						}  
					}  
					session.getTransaction().commit(); // 提交事物  
				} catch (Exception e) {  
					e.printStackTrace(); // 打印错误信息  
					session.getTransaction().rollback(); // 出错将回滚事物  
				}
				finally{
					session.close();
				}
			}
	}
	
	@Override
	public void updateList(List<T> list) {
		Session session = null;
		if(null != list && list.size()>0){
			try {  
				session = sessionFactory.openSession(); // 获取Session  
				session.beginTransaction(); // 开启事物  
	
				for (int i = 0; i < list.size(); i++) {  
					session.update(list.get(i));   
					// 批插入的对象立即写入数据库并释放内存  
					if (i % 10 == 0) {  
						//session.flush();  
						session.clear();  
					}  
				}  
				session.getTransaction().commit(); // 提交事物  
			} catch (Exception e) {  
				e.printStackTrace(); // 打印错误信息  
				session.getTransaction().rollback(); // 出错将回滚事物  
			}
			finally{
				session.close();
			}
		}
		
	}

	@Override
	public Map<String, Object> getSessionInfo() {
		Map<String, Object> map=new HashMap<String,Object>();
		Session session=sessionFactory.openSession();
		map.put("newSession", session);
		Connection conn=null;
		try {
			conn=dataSource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("dataSource", dataSource);
		map.put("conn", conn);
		return map;
	}

}
