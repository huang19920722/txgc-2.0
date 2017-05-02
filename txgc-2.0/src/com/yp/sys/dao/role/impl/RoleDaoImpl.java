package com.yp.sys.dao.role.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.yp.sys.dao.common.impl.BaseDaoImpl;
import com.yp.sys.dao.role.IRoleDao;
import com.yp.sys.entity.Role;

/**
 * 
 * 
 * 文件名称: 角色 内容摘要: //简要描述本文件的内容，包括主要模块、函数及其功能的说明 创 建 人:mominglong 创建日期: 2013-2-3
 * 公 司: 重庆重邮汇侧有限公司 版权所有: 版权所有(C)2001-2004
 * 
 * 修改记录1: // 修改历史记录，包括修改日期、修改者及修改内容 修改日期： 版 本 号： 修 改 人： 修改内容： 修改记录2：…
 * 
 * @param <T>
 */
@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role> implements IRoleDao {
	@Override
	public List<Role> queryUserRolesByUserId(Long userId) {
		//String sql = " select r.id,r.text from sys_user_to_role t LEFT JOIN sys_role r on t.SYROLE_ID=r.ID  where t.SYUSER_ID='"
		//		+ userId + "' ";
		//return this.findBySql(sql);
		return null;
	}
}
