package com.yp.sys.dao.workorder.impl;

import org.springframework.stereotype.Repository;

import com.yp.sys.dao.common.impl.BaseDaoImpl;
import com.yp.sys.dao.workorder.ICollectDao;
import com.yp.sys.entity.collect.CollectInfo;

 /**  
 * @Description: 终端采集信息dao
 * @author youxingliu
 * @date 2016年11月17日 上午11:05:51
 */
@Repository("collectDao")
public class CollectDaoImpl extends BaseDaoImpl<CollectInfo> implements
		ICollectDao {

}
