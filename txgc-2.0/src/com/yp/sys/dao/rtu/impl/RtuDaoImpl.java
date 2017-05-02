package com.yp.sys.dao.rtu.impl;


import org.springframework.stereotype.Repository;

import com.yp.sys.dao.common.impl.BaseDaoImpl;
import com.yp.sys.dao.rtu.IRtuDao;
import com.yp.sys.entity.rtu.RtuInfo;
@Repository("rtuDao")
public class RtuDaoImpl extends BaseDaoImpl<RtuInfo> implements IRtuDao {

}
