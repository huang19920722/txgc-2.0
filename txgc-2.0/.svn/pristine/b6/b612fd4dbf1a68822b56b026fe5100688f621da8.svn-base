package com.yp.sys.controller.user;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.yp.sys.common.BaseController;
import com.yp.sys.common.GlobalConstant;
import com.yp.sys.common.GlobalContext;
import com.yp.sys.entity.Menu;
import com.yp.sys.entity.User;
import com.yp.sys.entity.organization.Organization;
import com.yp.sys.enums.AcountStatusEnum;
import com.yp.sys.enums.UserTypeEnum;
import com.yp.sys.pojo.DataGridBean;
import com.yp.sys.pojo.DataGridJson;
import com.yp.sys.pojo.Json;
import com.yp.sys.pojo.TreeNodeBean;
import com.yp.sys.pojo.UserBean;
import com.yp.sys.service.menu.IMenuService;
import com.yp.sys.service.organization.IOrganizationService;
import com.yp.sys.service.role.IRoleService;
import com.yp.sys.service.sys.ISysFilesService;
import com.yp.sys.service.user.IUserService;
import com.yp.sys.util.Encrypt;
import com.yp.sys.util.StringUtil;

/**
 * 用户控制器
 * 
 * @author  
 * 
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(UserController.class);
	private IRoleService roleService;
	@Autowired
	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}
	@Autowired
	private IUserService userService;
	@Autowired
	private ISysFilesService sysFilesService;
	@Autowired
	private IMenuService menuService;
	@Autowired
	private IOrganizationService organService;
	/*****************************后台*********************************/

	/**
	 * 跳转到系统探针页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/jspinfo",method={RequestMethod.GET})
	public String jspinfo() {
		return "admin/tz/jspinfo";
	}

	/**
	 * 跳转到用户管理页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/userList",method={RequestMethod.GET})
	public String user() {
		return "sys/user/user";
	}

	/**
	 * 跳转到用户信息页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/userInfo",method={RequestMethod.GET})
	public String userInfo() {
		return "sys/user/userInfo";
	}
	

	
	
	

	/**
	 * 获得用户表格
	 * 
	 * @param dg
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/loginDatagrid",method={RequestMethod.POST})
	@ResponseBody
	public DataGridJson loginDatagrid(DataGridBean dg, UserBean user) {
		return userService.datagrid(dg, user);
	}

	/**
	 * 获得用户列表
	 * 
	 * @param q
	 * @return
	 */
	@RequestMapping(value = "/loginCombobox",method={RequestMethod.POST})
	@ResponseBody
	public List<UserBean> loginCombobox(String q) {
		return userService.combobox(q);
	}

	/**
	 * 用户表格
	 * 
	 * @param dg
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/datagrid")
	@ResponseBody
	public DataGridJson datagrid(DataGridBean dg, UserBean user) {
		String orgIds="";
		String orgId="";
		if(GlobalContext.getCurrentUser()!=null && GlobalContext.getCurrentUser().getOrganization()!=null){
			orgId=GlobalContext.getCurrentUser().getOrganization().getId()+"";
			Map<String,Organization> maps= organService.findAllOrgByCurrentUser(orgId);
			for(String key:maps.keySet()){
				orgIds+=","+key;
			}
		}
		if(StringUtils.isNotBlank(orgIds)){
			orgIds=orgId+orgIds;
			user.setOrgIds(orgIds);
		}
		DataGridJson dgj=userService.datagrid(dg, user);
		return dgj;
	}

	/**
	 * 添加用户
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/add",method={RequestMethod.POST})
	@ResponseBody
	public Json add(@RequestBody UserBean user) {
		Json j = new Json();
		try{
			//判断用户登录帐号唯一
			if(userService.checkLoginAcct(user.getLoginAcct())){
				user=userService.add(user);
				j.setSuccess(true);	 
			}else{
				j.setSuccess(false);
				j.setMsg("该帐号已经存在！");
			}
		 }
		 catch(Exception e){
			 
			 j.setSuccess(false);
		 }
		 return j; 
	}

	/**
	 * 编辑用户
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/edit",method={RequestMethod.POST})
	@ResponseBody
	public Json  edit(@RequestBody UserBean user) {
		Json j = new Json();
		 try{
			 userService.edit(user);
			 j.setSuccess(true);
			 
		 }
		 catch(Exception e){
			 e.printStackTrace();
			 j.setSuccess(false);
			 
		}
		return j;
	}

	/**
	 * 删除用户
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/del",method={RequestMethod.POST})
	@ResponseBody
	public Json del(@RequestBody String[] ids) {
		Json j = new Json();
		userService.del(ids);
		j.setSuccess(true);
		return j;
	}
	/**ajax校验教师账号是否已存在 
	 * @param loginAcct
	 * @return boolean
	 */
	@RequestMapping("/isExist")
	@ResponseBody
	public boolean isExist(String loginAcct){
		boolean exist = true;
		UserBean user =  this.userService.findByLoginAcct(loginAcct);
		if(user==null){
			exist = false;
		}
		return  exist;
	}
	/**********************************前台*****************************************/
	/**
	 * 跳转到注册页面
	 * @return String
	 */
	@RequestMapping(value = "/front/toRegister",method=RequestMethod.GET)
	public String reg(Model model){
		model.addAttribute("student", UserTypeEnum.Student.getName());
		model.addAttribute("teacher", UserTypeEnum.Teacher.getName());
		return "front/user/register";
	}
	/**
	 * 跳转到信息完善或修改密码页面
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/front/userInfo",method={RequestMethod.POST,RequestMethod.GET})
	public String userInfo(Model model,User user,String type,String way){
		UserBean currUser = GlobalContext.getCurrentUser();//从session里面取user
		String url="";
		if(currUser!=null&&currUser.getId()!=null){
			user=userService.getById(currUser.getId());
		}
		model.addAttribute("user",user);
		if(way.equals("userInfo")){
			url="front/user/userInfo";
		}else if(way.equals("userInfoNew")){
			url="front/user/new/userInfo";
		}else if(way.equals("updatePassword")){
			url="front/user/updatePassword";
		}else if(way.equals("updatePasswordNew")){
			url="front/user/new/updatePassword";
		}
		return url;
	}
	
	
	/**
	 * 修改密码
	 * @return Json
	 */
	@RequestMapping(value = "/front/updatePassword",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Json updatePassword(User user,String Oldpassword){
		Json j=new Json();
		String msg="";
		msg=userService.updatePassword(user,Oldpassword);
		if(msg.equals("Ok")){
			j.setSuccess(true);
		}else{
			j.setMsg(msg);
			j.setSuccess(false);
		}
		return j;
	}
	/**
	 * 帐号,登录名,手机号等唯一性
	 * @param user
	 * @return boolean
	 */
	@RequestMapping(value = "/front/check",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public boolean check(User user){
		//帐号,登录名,手机号等唯一性
		return userService.check(user);
	}
	
	@RequestMapping(value = "/front/checkY",method={RequestMethod.GET})
	@ResponseBody
	public Json checkY(User user){
		Json j=new Json();
		try{
			j.setMsg(userService.checkY(user));
			j.setSuccess(true);
		}catch(Exception e){
			e.printStackTrace();
			j.setSuccess(false);
		}
		//帐号,登录名,手机号等唯一性
		return j;
	}
	/**
	 * <p>去登录页面</p>
	 * @author yinyong
	 * @return String
	 */
	@RequestMapping(value="/front/toLogin")
	public String toLogin(){
		UserBean currUser = GlobalContext.getCurrentUser();
		if(currUser!=null){
			return "redirect:/index/Index";
		}
		return "front/user/login";
	}
	/**
	 * <p>登录验证</p>
	 * @author yinyong
	 * @param user
	 * @param validatacode
	 * @param userType
	 * @param model
	 * @return Json
	 */
	@RequestMapping(value="/front/valLogin",method={RequestMethod.POST})
	@ResponseBody
	public Json valLogin(User user,String validatacode,String password2){
		Json json=new Json();
		if(!String.valueOf(GlobalContext.getSessionAttribute(GlobalConstant.VERIFY_CODE)).equalsIgnoreCase(validatacode)){
			json.setSuccess(false);
			json.setMsg("验证码错误!");
			return json;
		}
		String loginAcct = user.getLoginAcct();
		String password="";
		if(null!=user.getPassword()&&!"".equals(user.getPassword())){
			password = user.getPassword();
		}else{
			password=password2;
		}
		if(StringUtil.isBlank(loginAcct)||StringUtil.isBlank(password)){
			json.setSuccess(false);
			json.setMsg("请输入账号或密码!");
			return json;
		}
		UserBean findUser = this.userService.findByLoginAcct(loginAcct);
		if(findUser==null){
			json.setSuccess(false);
			json.setMsg("该用户未注册！");
			return json;
		}
		if(StringUtil.isNotEquals(findUser.getStatusId(), AcountStatusEnum.Enable.getId())){
			json.setSuccess(false);
			json.setMsg("该用户未审核或已停用，请联系管理员！");
			return json;
		}
		if(StringUtil.isNotEquals(findUser.getPassword(), Encrypt.e(password))){
			json.setSuccess(false);
			json.setMsg("密码错误！");
			return json;
		}
		/*登录成功*/
		List<Menu> menus = this.menuService.findMenuMapByUserId(findUser.getId());//获取用户拥有的菜单权限
		findUser.setMenus(menus);
		GlobalContext.setSessionAttribute(GlobalConstant.CURRENT_USER, findUser);
		json.setSuccess(true);
		json.setObj(findUser);
		return json;
	}
	@RequestMapping(value="/front/logout",method={RequestMethod.GET})
	public String logout(HttpSession session, UserBean user){
		user.setLoginAcct(GlobalContext.getCurrentUser().getLoginAcct());
		user.setId(GlobalContext.getCurrentUser().getId());
		session=GlobalContext.getSession();
		session.setAttribute(GlobalConstant.CURRENT_USER, null);
		session.setAttribute("loginUser", null);
		
		return "redirect:/";
	}
	/**
	 * <p>去到用户的个人中心</p>实现控制到控制的跳转
	 * @return String
	 */
	@RequestMapping(value="/front/toUser",method={RequestMethod.GET})
	public String toUser(){
		HttpSession session=GlobalContext.getSession();
		User  user=(User)session.getAttribute("loginUser");
		if (user!=null) {
			if (user.getType().equalsIgnoreCase(UserTypeEnum.Teacher.getId())) {
				return "redirect:/teacher/main";
			}else{
				return "redirect:/student/main";
			}
		}else{
			return "redirect:/index/toIndex";
		}
		
	}
	/**
	 * <p>学生登录成功后去的页面</p>
	 * @author yinyong
	 * @return String
	 */
	@RequestMapping(value="/front/student",method={RequestMethod.GET})
	public String studentLogin(){
		return "front/user/student";
	}
	/**
	 * <p>老师登录成功后去的页面</p>
	 * @author yinyong
	 * @return String
	 */
	@RequestMapping(value="/front/teacher",method={RequestMethod.GET})
	public String teacherLogin(){
		return "teacher/main";
	}
	/**
	 * 角色树
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/tree")
	@ResponseBody
	public List<TreeNodeBean> tree(String orgId) {
		return roleService.tree(orgId);
	}
	@RequestMapping(value = "/findOrgTreeByLoginUser")
	@ResponseBody
	public List<TreeNodeBean> findOrgTreeByLoginUser() {
		//根据登录用户查找组织机构信息
		UserBean user=GlobalContext.getCurrentUser();
		//重新从数据库中再次查找用户的组织机构信息
		user=userService.getUserInfo(user);
		Organization org=user.getOrganization();
		//判断是否是超级管理员
		if(user.getId()==0){
			org=null;
		}
		return this.organService.treeId(org);
	}
}
