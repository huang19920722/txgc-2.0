package com.yp.sys.service.role.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.yp.sys.common.GeneralMethod;
import com.yp.sys.common.GlobalConstant;
import com.yp.sys.common.GlobalContext;
import com.yp.sys.dao.common.IBaseDao;
import com.yp.sys.dao.menu.IMenuDao;
import com.yp.sys.dao.role.IRoleDao;
import com.yp.sys.entity.Role;
import com.yp.sys.pojo.RoleBean;
import com.yp.sys.pojo.TreeNodeBean;
import com.yp.sys.service.common.impl.BaseServiceImpl;
import com.yp.sys.service.role.IRoleService;
import com.yp.sys.util.YpBeanUtils;

/**
 * 角色Service实现类
 * 
 * @author  
 * 
 */
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements IRoleService {

	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(RoleServiceImpl.class);

	private IRoleDao roleDao;
	private IMenuDao menuDao;
	
	@Autowired
	private IBaseDao<Object[]> baseDao;




	public IRoleDao getRoleDao() {
		return roleDao;
	}

	@Autowired
	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}

	
	
	

	

	public IMenuDao getMenuDao() {
		return menuDao;
	}
	@Autowired
	public void setMenuDao(IMenuDao menuDao) {
		this.menuDao = menuDao;
	}

	/**
	 * 
	 * Description: <br>根据id查询出定级菜单或者其子菜单的树
	 * @param id
	 * @return
	 */
	//@Cacheable(value = "syproRoleCache", key = "'tree'+#id")
	@Transactional(readOnly = true)
	public List<TreeNodeBean> tree(String id) {
		String hql = "from Role t where t.recordStatus='Y' ";
		List<Role> syroleList=null;
		if (id != null && !id.trim().equals("")) {
			hql = "from Role t  where t.createOrg.id=? and t.recordStatus='Y' order by t.seq";
			syroleList= roleDao.find(hql,Long.valueOf(id));
		}else{
			//获取当前系统登录用户
			if(GlobalContext.getCurrentUser()!=null ){
				hql+=" and t.createUserId="+GlobalContext.getCurrentUser().getId();
			}
			hql+=" order by t.seq ";
			
			syroleList= roleDao.find(hql);
		}
		
		List<TreeNodeBean> tree = new ArrayList<TreeNodeBean>();
		for (Role syrole : syroleList) {
			tree.add(tree(syrole, false));
		}
		return tree;
	}

	/**
	 * 
	 * Description: <br>根据角色和recursive（是否递归查询子节点）
	 * 查询出角色或者其子角色的树
	 * @param id
	 * @return
	 */
	private TreeNodeBean tree(Role syrole, boolean recursive) {
		TreeNodeBean node = new TreeNodeBean();
		node.setId(syrole.getId()+"");
		node.setText(syrole.getText());
		Map<String, Object> attributes = new HashMap<String, Object>();
		node.setAttributes(attributes);
		
		return node;
	}
	/**
	 * 
	 * Description: <br>根据id查询出定级菜单或者其子菜单的树
	 * @param id
	 * @return
	 */
	//@Cacheable(value = "syproRoleCache", key = "'treegrid'+#id")
	@Transactional(readOnly = true)
	public List<RoleBean> treegrid(String id) {
		List<RoleBean> treegrid = new ArrayList<RoleBean>();
		//查询顶级角色
		String hql = "from Role t where t.recordStatus=? ";
		if(GlobalContext.getCurrentUser()!=null ){
//			if(GlobalContext.getCurrentUser().getOrganization()!=null){
//				hql+=" and t.createOrg.id="+ GlobalContext.getCurrentUser().getOrganization().getId();
//			}
			hql+=" and t.createUserId="+GlobalContext.getCurrentUser().getId();
		}
		hql+=" order by seq ";
		List<Role> syroleList = roleDao.find(hql,GlobalConstant.FLAG_Y);
		//循环遍历角色
		for (Role syrole : syroleList) {
			RoleBean roleBean = new RoleBean();
			BeanUtils.copyProperties(syrole, roleBean);
			//处理角色 避免出现 用户角色 角色用户出现死循环情况
			/*List<User> users=roleBean.getUsers();
			if(users!=null){		
				for (User u : users) {
					u.setRoles(null);
				}
			}*/
			treegrid.add(roleBean);
		}
		return treegrid;
	}

	/**
	 * 添加角色
	 * 
	 */
	@CacheEvict(value = "syproRoleCache", allEntries = true)
	public RoleBean addRole(RoleBean role) {
		Role syrole = new Role();
		BeanUtils.copyProperties(role, syrole);
		//设置通用信息
		GeneralMethod.setRecordInfo(syrole, true);
		//获取当前系统登录用户
		if(GlobalContext.getCurrentUser()!=null ){
			if(GlobalContext.getCurrentUser().getOrganization()!=null){
				syrole.setCreateOrg(GlobalContext.getCurrentUser().getOrganization());
			}
			if(GlobalContext.getCurrentUser().getId().longValue()==0l){
				syrole.setSystemRole("1");
			}else{
				syrole.setSystemRole("2");
			}
		}
		
		
		roleDao.save(syrole);
		role.setId(syrole.getId());
	/*	
		//角色跟机构关联
		if(StringUtils.isNotBlank(role.getOrgIds())){
			String[] orgArray=role.getOrgIds().split(",");
			if(orgArray!= null && orgArray.length>0){
				String insertSql="insert into sys_role_to_org (role_id,org_id) values ";
				for(int i=0;i<orgArray.length;i++){
					insertSql+= "('"+role.getId()+"','"+orgArray[i]+"'),";
				}
				insertSql=insertSql.substring(0, insertSql.length()-1);
				baseDao.executeSql(insertSql);
			}
		}
	*/			
		return role;
	}

	/**
	 * 级联删除角色
	 * 
	 */
	@CacheEvict(value = "syproRoleCache", allEntries = true)
	public void delRole(String[] ids) {
		if(ids==null||ids.length==0){
			return;
		}
		for (String s : ids) {
			Role r=roleDao.get(Role.class, Long.valueOf(s));
			if(null!=r){
				if("2".equals(r.getSystemRole())){
					//设置通用属性
					r.setRecordStatus(GlobalConstant.FLAG_N);
					GeneralMethod.setRecordInfo(r, false);
				}
			}
		}
	}
	/**
	 * 
	 * 编辑角色
	 * 
	 */
	@CacheEvict(value = "syproRoleCache", allEntries = true)
	public RoleBean editRole(RoleBean role) {
		Role r=roleDao.get(Role.class, role.getId());
		if(null!=r){
			//系统内置角色不允许修改
			if("2".equals(r.getSystemRole())){
				//设置通用属性
				GeneralMethod.setRecordInfo(r, false);
				YpBeanUtils.copyPropertiesIgnoreNull(role, r);
				/*//获取当前系统登录用户
				if(GlobalContext.getCurrentUser()!=null && GlobalContext.getCurrentUser().getOrganization()!=null){
					r.setCreateOrg(GlobalContext.getCurrentUser().getOrganization());
				}
				*/		
				r.setMenus(role.getMenus());
				roleDao.save(r);
				/*	
				//删除角色跟机构关联
				baseDao.executeSql("delete from sys_role_to_org  where role_id="+role.getId());
				
				//角色跟机构关联
				if(StringUtils.isNotBlank(role.getOrgIds())){
					String[] orgArray=role.getOrgIds().split(",");
					if(orgArray!= null && orgArray.length>0){
						String insertSql="insert into sys_role_to_org (role_id,org_id) values ";
						for(int i=0;i<orgArray.length;i++){
							insertSql+= "('"+role.getId()+"','"+orgArray[i]+"'),";
						}
						insertSql=insertSql.substring(0, insertSql.length()-1);
						baseDao.executeSql(insertSql);
					}
				}
				*/	
			}
		}
		return role;
	}
	
	@Override
	public List<Role> queryUserRolesByUserId(Long userId) {
		
		
		String sql = " select r.id,r.text from sys_user_to_role t LEFT JOIN sys_role r on t.SYROLE_ID=r.ID  where t.SYUSER_ID='"
				+ userId + "' ";
		List<Role> list=roleDao.findBySql(sql);
		List<Role> listRole=new ArrayList<Role>();
		if(list!=null && list.size()>0){
			for(Object objct:list){
				Object[] obj=(Object[])objct;
				Role role=new Role();
				role.setId(Long.valueOf(obj[0]+""));
				role.setText(obj[1]+"");
				listRole.add(role);
			}
		}
		
		return listRole;
		
		//return roleDao.queryUserRolesByUserId(userId);
	}
}
