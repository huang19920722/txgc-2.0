package com.yp.sys.service.user.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
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
import com.yp.sys.dao.user.IUserDao;
import com.yp.sys.entity.Role;
import com.yp.sys.entity.User;
import com.yp.sys.pojo.DataGridBean;
import com.yp.sys.pojo.DataGridJson;
import com.yp.sys.pojo.UserBean;
import com.yp.sys.service.common.impl.BaseServiceImpl;
import com.yp.sys.service.user.IUserService;
import com.yp.sys.util.YpBeanUtils;
import com.yp.sys.util.DateUtil;
import com.yp.sys.util.Encrypt;
import com.yp.sys.util.StringUtil;



/**
 * 用户Service
 * 
 * @author  
 * 
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {
	@Autowired
	private IBaseDao<Object[]> baseDao;
	@Autowired
	private IUserDao userDao;

	@CacheEvict(value = "syproUserCache", allEntries = true)
	public UserBean reg(UserBean user) {
		user.setPassword(Encrypt.e(user.getPassword()));
		User u = new User();
		BeanUtils.copyProperties(user, u);
		GeneralMethod.setRecordInfo(u, true);
		userDao.save(u);
		return user;
	}

//	@Cacheable(value = "syproUserCache", key = "'login'+#user.loginAcct+#user.password")
//	@Transactional(readOnly = true)
	public UserBean login(UserBean user) {
		User u = userDao.get("from User u where u.loginAcct=? and u.password=? and u.recordStatus=?", user.getLoginAcct(), Encrypt.e(user.getPassword()),GlobalConstant.FLAG_Y);
		if (u != null) {
			user.setId(u.getId());
			user.setLoginAcct(u.getLoginAcct());
			user.setNickName(u.getNickName());
			user.setOrganization(u.getOrganization());
			return user;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public DataGridJson datagrid(DataGridBean dg, UserBean user) {
		DataGridJson j = new DataGridJson();
		String hql = " from User t  ";
		List<Object> values = new ArrayList<Object>();
		if (user != null) {// 添加查询条件
			if (user.getLoginAcct() != null && !user.getLoginAcct().trim().equals("")) {
				hql += " and t.loginAcct like '%%" + user.getLoginAcct().trim() + "%%' ";
			}
			if (user.getCreatedatetimeStart() != null && !user.getCreatedatetimeStart().equals("")) {
				hql += " and t.createdateTime>=? ";
				values.add(DateUtil.format(user.getCreatedatetimeStart()));
			}
			if (user.getCreatedatetimeEnd() != null && !user.getCreatedatetimeEnd().equals("")) {
				hql += " and t.createdateTime<=? ";
				values.add(DateUtil.format(user.getCreatedatetimeEnd()));
			}
			if (user.getModifydatetimeStart() != null && !user.getModifydatetimeStart().equals("")) {
				hql += " and t.modifyDateTime>=? ";
				values.add(DateUtil.format(user.getModifydatetimeStart()));
			}
			if (user.getModifydatetimeEnd() != null && !user.getModifydatetimeStart().equals("")) {
				hql += " and t.modifyDateTime<=? ";
				values.add(DateUtil.format(user.getModifydatetimeEnd()));
			}
		}
		if(StringUtils.isNotBlank(user.getOrgIds())){
			//hql += " and t.createOrg.id='"+GlobalContext.getCurrentUser().getOrganization().getId()+"' ";
			hql += " and t.organization.id in ("+user.getOrgIds()+")";
		}
		
		hql=hql.replaceFirst("and", " where ");
		String totalHql = " select count(*) " + hql;
		j.setTotal(userDao.count(totalHql, values));// 设置总记录数
		if (dg.getSort() != null) {// 设置排序
			hql += " order by " + dg.getSort() + " " + dg.getOrder();
		}
		List<User> syusers = userDao.find(hql, dg.getPage(), dg.getRows(), values);// 查询分页

		List<UserBean> users = new ArrayList<UserBean>();
		if (syusers != null && syusers.size() > 0) {// 转换模型
			for (User syuser : syusers) {
				UserBean u = new UserBean();
				YpBeanUtils.copyPropertiesIgnoreNull(syuser, u);
				//处理角色避免出现角色拥有用户 ，用户拥有角色的死循环情况
				/*List<Role> roles=u.getRoles();
				if(roles!=null){
					for (Role role : roles) {
						role.setUsers(null);
					}
				}*/
				users.add(u);
			}
		}
		j.setRows(users);// 设置返回的行
		return j;
	}

	@Cacheable(value = "syproUserCache", key = "'combobox'+#q")
	@Transactional(readOnly = true)
	public List<UserBean> combobox(String q) {
		if (q == null) {
			q = "1";
		}
		String hql = " from User t where name like '%%" + q.trim() + "%%' and recordStatus='"+GlobalConstant.FLAG_Y+"'";
		List<User> l = userDao.find(hql,new Object[]{});
		List<UserBean> ul = new ArrayList<UserBean>();
		if (l != null && l.size() > 0) {
			for (User syuser : l) {
				UserBean u = new UserBean();
				BeanUtils.copyProperties(syuser, u);
				ul.add(u);
			}
		}
		return ul;
	}

	@CacheEvict(value = "syproUserCache", allEntries = true)
	public UserBean add(UserBean user) {
		//user.setId(UUID.randomUUID().toString());
		if(user.getPassword()==null){
			user.setPassword(Encrypt.e("123456"));
		}else{
			user.setPassword(Encrypt.e(user.getPassword()));
		}
		User syuser = new User();
		YpBeanUtils.copyPropertiesIgnoreNull(user, syuser);
		//通用信息设置
		GeneralMethod.setRecordInfo(syuser, true);
		
		//获取当前系统登录用户
		if(GlobalContext.getCurrentUser()!=null && GlobalContext.getCurrentUser().getOrganization()!=null){
			syuser.setCreateOrg(GlobalContext.getCurrentUser().getOrganization());
		}

		userDao.save(syuser);
		user.setId(syuser.getId());
//	
//		//角色跟机构关联
//		if(StringUtils.isNotBlank(user.getOrgIds())){
//			String[] orgArray=user.getOrgIds().split(",");
//			if(orgArray!= null && orgArray.length>0){
//				String insertSql="insert into sys_user_to_org (user_id,org_id) values ";
//				for(int i=0;i<orgArray.length;i++){
//					insertSql+= "('"+user.getId()+"','"+orgArray[i]+"'),";
//				}
//				insertSql=insertSql.substring(0, insertSql.length()-1);
//				baseDao.executeSql(insertSql);
//			}
//		}

		return user;
	}
	@CacheEvict(value = { "syproAuthCache", "syproUserCache" }, allEntries = true)
	public UserBean edit(UserBean user) {
		User syuser = userDao.get(User.class, Long.valueOf(user.getId()));
		if (syuser == null) {
			return user;
		}
		
		
		//通用信息设置
		YpBeanUtils.copyPropertiesIgnoreNull(user, syuser);
	/*	//获取当前系统登录用户
		if(GlobalContext.getCurrentUser()!=null && GlobalContext.getCurrentUser().getOrganization()!=null){
			syuser.setCreateOrg(GlobalContext.getCurrentUser().getOrganization());
		}
	*/	
		//删除用户跟机构关联
//		baseDao.executeSql("delete from sys_user_to_org  where user_id="+user.getId());
//		//用户跟机构关联
//		if(StringUtils.isNotBlank(user.getOrgIds())){
//			String[] orgArray=user.getOrgIds().split(",");
//			if(orgArray!= null && orgArray.length>0){
//				String insertSql="insert into sys_user_to_org (user_id,org_id) values ";
//				for(int i=0;i<orgArray.length;i++){
//					insertSql+= "('"+user.getId()+"','"+orgArray[i]+"'),";
//				}
//				insertSql=insertSql.substring(0, insertSql.length()-1);
//				baseDao.executeSql(insertSql);
//			}
//		}
		
		GeneralMethod.setRecordInfo(syuser, false);
		return user;
	}

	@SuppressWarnings("unchecked")
	@CacheEvict(value = "syproUserCache", allEntries = true)
	public void del(String[] ids) {
		for (String id : ids) {
			User syuser = userDao.get(User.class, Long.valueOf(id));
			//设置通用信息
			GeneralMethod.setRecordInfo(syuser, false);
			syuser.setRecordStatus(GlobalConstant.FLAG_N);
		}
	}

//	@SuppressWarnings("unchecked")
//	@Cacheable(value = "syproUserCache", key = "'getUserInfo'+#user.id")
	public UserBean getUserInfo(UserBean user) {
		User syuser = userDao.get(User.class, user.getId());
		BeanUtils.copyProperties(syuser, user);
		return user;
	}

	@CacheEvict(value = "syproUserCache", allEntries = true)
	public UserBean editUserInfo(UserBean user) {
		if(user==null){
			return user;
		}
		if(user.getId()==null){
			user.setFlag("2");
			return user;
		}
		User u=getById(user.getId());
		if(u==null){
			user.setFlag("2");
			return user;
		}
		YpBeanUtils.copyPropertiesIgnoreNull(user, u);
		//设置通用信息
		GeneralMethod.setRecordInfo(u, false);
		user.setFlag("1");
		return user;
	}
	@Override
	public UserBean loginPointV(String userName) {
       User user =   userDao.get("from User u where u.loginAcct=?", userName);
        if(null != user){
        	UserBean ub = new UserBean();
        	ub.setId(user.getId());
        	ub.setLoginAcct(user.getLoginAcct());
			return ub;
        }
        	return null;
       
		
	}

	@Override
	public boolean checkLoginAcct(String loginAcct) {
		String hql="from User where recordStatus=? and loginAcct=?";
		List<User> users=userDao.find(hql,GlobalConstant.FLAG_Y,loginAcct);
		if(users==null||users.size()==0){
			return true;
		}
		return false;
	}

	@Override
	public UserBean updateUserPassword(UserBean user) {
		if(user==null){
			return user;
		}
		if(user.getId()==null){
			user.setFlag("2");
			return user;
		}
		String hql="from User where recordStatus=? and password=?";
		User u=this.userDao.get(hql, GlobalConstant.FLAG_Y,Encrypt.e(user.getOldPassword()));
		if(u==null){
			user.setFlag("2");
			return user;
		}
		u.setPassword(Encrypt.e(user.getPassword()));
		GeneralMethod.setRecordInfo(u, false);
		user.setFlag("1");
		return user;
	}

	@Override
	public boolean check(User user) {
		String hql = "";
		List<User> users=null;
		if(StringUtil.isNotBlank(user.getEmail())){
			//hql="from User where recordStatus='Y' and  email=? ";
			//users =userDao.find(hql,user.getEmail());
			hql="select * from sys_user  where  email='"+user.getEmail()+"'";
			users =userDao.findBySql(hql.toString());
		}else if(StringUtil.isNotBlank(user.getLoginAcct())){
//			hql="from User where recordStatus='Y' and loginAcct=?";
//			users =userDao.find(hql,user.getLoginAcct());
			hql="select * from sys_user  where loginAcct='"+user.getLoginAcct()+"'";
			users =userDao.findBySql(hql.toString());
		}
		if (users == null || users.size() == 0) {
			return true;
		}
		return false;
	}	
	@Override
	public String checkY(User user){
		String hql = "";
		List<User> users=null;
		System.out.print("1111");
		if(StringUtil.isNotBlank(user.getEmail())){
			hql="select * from sys_user  where getEmail='"+user.getEmail()+"'";
			users =userDao.findBySql(hql.toString());
		}else if(StringUtil.isNotBlank(user.getLoginAcct())){
			hql="select * from sys_user  where loginAcct='"+user.getLoginAcct()+"'";
			users =userDao.findBySql(hql.toString());
		}
		if (users == null || users.size() == 0) {
			return "t";
		}
		return "f";
	}
	@Override
	public String updatePassword(User user,String Oldpassword){
		String msg="";
		String hql="from User where id=? and  loginAcct=? And password=?";
		List<Object> values=new ArrayList<Object>();
		values.add(user.getId());
		values.add(user.getLoginAcct());
		values.add(Encrypt.e(Oldpassword));
		List<User> users=userDao.find(hql, values);
		if (users == null || users.size() == 0) {
			msg="N";
		}else{
			User u=getById(user.getId());
			user.setPassword(Encrypt.e(user.getPassword()));
			YpBeanUtils.copyPropertiesIgnoreNull(user, u);
			GeneralMethod.setRecordInfo(u, false);
			msg="Ok";
		}
		return msg;
	}

	@Override
	public UserBean findByLoginAcct(String loginAcct) {
		UserBean userBean = null;
		if(StringUtil.isNotBlank(loginAcct)){
			List<User> users = userDao.find("from User u where  u.loginAcct=?", loginAcct);
			if(users!=null&&users.size()==1){
				userBean = new UserBean();
				User user = users.get(0);
				YpBeanUtils.copyProperties(user, userBean);
			}
		}
		return userBean;
	} 


}
