package com.yp.sys.service.cache.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yp.sys.dao.cache.IRedisUserDao;
import com.yp.sys.entity.RedisUser;
import com.yp.sys.service.cache.IRedisUserService;
import com.yp.sys.service.common.impl.BaseServiceImpl;
@Service("redisUserService")
public class RedisUserServiceImpl extends BaseServiceImpl<RedisUser> implements IRedisUserService {  
      
    private IRedisUserDao redisUserDao;  
    @Autowired
	public void setRedisUserDao(IRedisUserDao redisUserDao) {
		this.redisUserDao = redisUserDao;
	}

	
}	
