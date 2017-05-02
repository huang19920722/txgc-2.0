package com.yp.sys.dao.manage.group.impl;


import org.springframework.stereotype.Repository;

import com.yp.sys.dao.common.impl.BaseDaoImpl;
import com.yp.sys.dao.manage.group.IRGroupDao;
import com.yp.sys.entity.manage.RGroup;

@Repository(value="rGroupDao")
public class RGroupDaoImpl extends BaseDaoImpl<RGroup> implements IRGroupDao {

}
