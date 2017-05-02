package com.yp.sys.service.role;

import java.util.List;

import com.yp.sys.entity.Role;
import com.yp.sys.pojo.TreeNodeBean;
import com.yp.sys.pojo.RoleBean;
import com.yp.sys.service.common.IBaseService;

/**
 * 角色Service
 * 
 * @author  
 * 
 */
public interface IRoleService extends IBaseService<Role> {

	/**
	 * 
	 * Description: <br>根据id查询出定级菜单或者其子菜单的树
	 * @param id
	 * @return
	 */
	public List<TreeNodeBean> tree(String id);

	public List<RoleBean> treegrid(String id);

	public RoleBean addRole(RoleBean role);

	public void delRole(String[] ids);

	public RoleBean editRole(RoleBean role);
	
	/**
	 * 通过用用户Id查询用户所有角色
	 * @param userId
	 * @return
	 */
	public List<Role> queryUserRolesByUserId(Long userId);

}
