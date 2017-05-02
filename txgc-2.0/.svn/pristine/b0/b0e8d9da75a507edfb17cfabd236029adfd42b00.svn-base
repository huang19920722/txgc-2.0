package com.yp.sys.service.user;

import java.util.List;

import com.yp.sys.entity.User;
import com.yp.sys.pojo.DataGridBean;
import com.yp.sys.pojo.DataGridJson;
import com.yp.sys.pojo.UserBean;
import com.yp.sys.service.common.IBaseService;

/**
 * 用户Service
 * 
 * @author  
 * 
 */
public interface IUserService extends IBaseService<User> {

	/**
	 * 用户注册
	 * 
	 * @param user
	 *            用户信息
	 * @return User
	 */
	public UserBean reg(UserBean user);

	/**
	 * 用户登录
	 * 
	 * @param user
	 *            用户信息
	 * @return User
	 */
	public UserBean login(UserBean user);

	public DataGridJson datagrid(DataGridBean dg, UserBean user);

	public List<UserBean> combobox(String q);

	public UserBean add(UserBean user);

	public UserBean edit(UserBean user);

	public void del(String[] ids);


	public UserBean getUserInfo(UserBean user);
	
	/**
	 * 修改用户的基本信息
	 * @param user
	 * @return UserBean
	 */
	public UserBean editUserInfo(UserBean user);
	
	
	public UserBean loginPointV(String userName);
	/**
	 * 校验用户登录帐号是否唯一
	 * @Description 
	 * @param loginAcct
	 * @return boolean
	 */
	public boolean checkLoginAcct(String loginAcct);
	/**
	 * 修改用户密码
	 * @param user
	 * @return UserBean
	 */
	public UserBean updateUserPassword(UserBean user);
	/**
	 * 帐号唯一性检测
	 * @param user
	 * @return String
	 */
	public boolean check(User user);
	/**
	 * 修改密码
	 * @param user
	 * @return String
	 */
	public String updatePassword(User user,String Oldpassword);
	/** 
	 * 根据用户信息查找用户
	 * @param loginAcct 登录账号
	 * @return UserBean
	 */
	public UserBean findByLoginAcct(String loginAcct);

	public String checkY(User user);

}
