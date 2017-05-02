package com.yp.sys.dao.cache.impl;

import org.springframework.stereotype.Repository;

import com.yp.sys.dao.cache.IRedisUserDao;
import com.yp.sys.dao.common.impl.BaseDaoImpl;
import com.yp.sys.entity.RedisUser;
@Repository("redisUserDao")
public class RedisUserDaoImpl extends BaseDaoImpl<RedisUser> implements IRedisUserDao {
	
}
