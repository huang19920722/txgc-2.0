package com.yp.sys.dao.rtu.impl;

import org.springframework.stereotype.Repository;

import com.yp.sys.dao.common.impl.BaseDaoImpl;
import com.yp.sys.dao.rtu.IRtuCollectInfoDao;
import com.yp.sys.entity.rtu.RtuCollectInfo;
@Repository("rtuCollectInfoDao")
public class RtuCollectInfoDaoImpl extends BaseDaoImpl<RtuCollectInfo>
		implements IRtuCollectInfoDao {
}
