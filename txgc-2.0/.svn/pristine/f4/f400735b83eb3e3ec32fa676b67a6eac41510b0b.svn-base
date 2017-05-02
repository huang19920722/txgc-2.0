package com.yp.sys.dao.organization.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yp.sys.dao.common.impl.BaseDaoImpl;
import com.yp.sys.dao.organization.IOrganizationDao;
import com.yp.sys.entity.organization.Organization;

/**
 * @author Administrator 机构管理dao实现类
 * @param <T>
 */
@Repository("organDao")
public class OrganizationDaoImpl extends BaseDaoImpl<Organization> implements
		IOrganizationDao {
	@Override
	public List<Organization> queryUserMangerOrgByUserId(Long userId) {
		String sql = " select o.ID,o.ORG_CODE,o.ORG_NAME,o.area_code,o.area_name,o.PARENT_ID from sys_role_to_org u LEFT JOIN organization o on u.org_id=o.ID where u.role_id in (select t.SYROLE_ID from sys_user_to_role t where t.SYUSER_ID='"
				+ userId + "' ) and o.recordStatus='Y'  GROUP BY o.id ";
		return this.findBySql(sql);
	}
}
