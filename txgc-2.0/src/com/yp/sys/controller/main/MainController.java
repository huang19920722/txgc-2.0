package com.yp.sys.controller.main;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yp.sys.common.BaseController;
import com.yp.sys.common.GlobalConstant;
import com.yp.sys.common.GlobalContext;
import com.yp.sys.entity.Menu;
import com.yp.sys.entity.Role;
import com.yp.sys.entity.organization.Organization;
import com.yp.sys.entity.sys.SysFiles;
import com.yp.sys.pojo.Json;
import com.yp.sys.pojo.TreeNodeBean;
import com.yp.sys.pojo.UploaderJson;
import com.yp.sys.pojo.UserBean;
import com.yp.sys.service.menu.IMenuService;
import com.yp.sys.service.organization.IOrganizationService;
import com.yp.sys.service.role.IRoleService;
import com.yp.sys.service.sys.ISysFilesService;
import com.yp.sys.service.user.IUserService;
import com.yp.sys.util.ExceptionUtil;
import com.yp.sys.util.IpUtil;

/**
 * 文件名称： 主页controller 内容摘要： 成功登录后的主页布局 创建人： huangfei 创建日期： 2015年4月29日 版本号：
 * v1.0.0 公 司：重庆重邮汇侧公司 版权所有： (C)2001-2015 修改记录1 修改日期： 版本号： 修改人： 修改内容：
 */
@Controller
@RequestMapping("/main")
public class MainController extends BaseController {
	private static final Logger logger = Logger.getLogger(MainController.class);
	@Autowired
	private IMenuService menuService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IOrganizationService orgService;
	@Autowired
	private ISysFilesService sysFilesService;
	@Autowired
	private IRoleService roleService;

	@RequestMapping(value = "/north")
	public String north() {
		return "sys/layout/north";
	}

	@RequestMapping(value = "/west")
	public String west() {
		return "sys/layout/west";
	}

	@RequestMapping(value = "/center")
	public String center() {
		return "sys/layout/center";
	}

	@RequestMapping(value = "/south")
	public String south() {
		return "sys/layout/south";
	}

	/**
	 * 跳转到home页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/home")
	public String home() {
		return "sys/layout/home";
	}

	/**
	 * 跳转到用户信息页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/managerInfo")
	public String userInfo(Model model) {
		return "sys/user/userInfo";
	}

	/**
	 * Description:跳转到登录页面 <br>
	 * 
	 * @return
	 */
	@RequestMapping(value = "/toLogin")
	public String toLogin() {
		return "redirect:/toLogin";
	}

	/**
	 * 获得用户信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getUserInfo")
	@ResponseBody
	public Json getUserInfo(HttpSession session) {
		Json j = new Json();
		UserBean su = GlobalContext.getCurrentUser();
		if (su != null) {
			// UserBean u = managerService.getUserInfo(su);
			j.setObj(su);
			j.setSuccess(true);
		} else {
			j.setMsg("您没有登录或登录超时，请重新登录后重试！");
		}
		return j;
	}

	/**
	 * 编辑用户信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/editUserInfo")
	@ResponseBody
	public Json editUserInfo(@RequestBody UserBean user) {
		Json j = new Json();
		// String s=managerService.checkEmailAndMobile(user);
		// if("1".equals(s)){
		// j.setSuccess(false);
		// j.setMsg("手机号已被注册，请更换后再试");
		// return j;
		// }
		// if("2".equals(s)){
		// j.setSuccess(false);
		// j.setMsg("邮箱已被注册，请更换后再试");
		// return j;
		// }
		// UserBean u = managerService.edit(user);
		// j.setSuccess(true);
		return j;
	}

	/**
	 * 用户注销
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/logout")
	@ResponseBody
	public Json logout(HttpSession session, UserBean user) {
		user.setLoginAcct(GlobalContext.getCurrentUser().getLoginAcct());
		user.setId(GlobalContext.getCurrentUser().getId());
		Json j = new Json();
		if (session != null) {
			session.invalidate();
		}
		j.setSuccess(true);
		return j;
	}

	/**
	 * 用户注册
	 * 
	 * @param user
	 *            用户的信息
	 * @return json
	 */
	@RequestMapping(value = "/reg")
	@ResponseBody
	public Json reg(UserBean user) {
		// @RequestMapping(value="/
		Json j = new Json();
		try {
			// UserBean u = managerService.reg(user);
			j.setSuccess(true);
			j.setMsg("注册成功！");
		} catch (Exception e) {
			j.setMsg("用户名已存在！");
			logger.error(ExceptionUtil.getExceptionMessage(e));
		}
		return j;
	}

	/**
	 * 用户登录
	 * 
	 * @param user
	 *            用户的信息
	 * @return json
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/login")
	@ResponseBody
	public Json login(UserBean user, HttpServletResponse response,
			HttpServletRequest request) throws UnsupportedEncodingException {
		// response.setContentType("text/html;charset=utf-8");
		// request.setCharacterEncoding("utf-8");
		Json j = new Json();
		if (null != user.getLoginAcct() && !"".equals(user.getLoginAcct())
				&& null != user.getPassword() && !"".equals(user.getPassword())) {
			UserBean u = userService.login(user);
			if (u != null) {
				j.setSuccess(true);
				j.setMsg("登录成功!");
				u.setIp(IpUtil.getIpAddr(request));
				/* 获取用户拥有的菜单权限 */
				List<Menu> menus = this.menuService.findMenuMapByUserId(u
						.getId());
				u.setMenus(menus);

				// 保存用户的所有角色
				u.setRoles(roleService.queryUserRolesByUserId(u.getId()));
				Boolean flag = true;

				if (u.getRoles() != null && u.getRoles().size() > 0) {
					for (Role role : u.getRoles()) {
						if (role.getId() == 0L || "超管角色".equals(role.getText())) {
							flag = false;
						}
					}
				}

				// 查询当前用户能够查询哪些机构数据;超级管理员不查询，可以查看所有数据
				if (flag) {
				//	List<Organization> listOrg = orgService
				//			.queryUserMangerOrgByUserId(u.getId());
					
					
					List<Organization> listOrg = orgService.queryOrgByUserId(u.getId());
					if (listOrg != null && listOrg.size() > 0) {
						u.setOrgs(listOrg);
						String str = "";
						for (Organization org : listOrg) {

							str += "," + org.getId();
						}
						str = str.replaceFirst(",", "");
						u.setOrgIds(str);
					}
				}

				request.getSession().setAttribute(GlobalConstant.CURRENT_USER,
						u);
				j.setObj(u);
			} else {
				j.setMsg("用户名或密码错误!");
			}
		} else {
			j.setMsg("请输入用户名和密码!");
		}
		return j;
	}

	/**
	 * 获取菜单树
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/tree")
	@ResponseBody
	public List<TreeNodeBean> tree(String id, HttpSession session) {
		List<TreeNodeBean> list = menuService.tree(id, GlobalContext
				.getCurrentUser().getId(), GlobalConstant.TIMESTAPE);
		return list;
	}

	/**
	 * 获取菜单树
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getUserMenuTree")
	@ResponseBody
	public List<TreeNodeBean> getUserMenuTree(String id, HttpSession session) {
		List<TreeNodeBean> list = menuService.buildTreeByUserId(GlobalContext
				.getCurrentUser().getId());
		return list;
	}

	/**
	 * 修改用户密码
	 * 
	 * @param user
	 * @return Json
	 */
	@RequestMapping(value = "/updatePwd")
	@ResponseBody
	public Json updatePwd(@RequestBody UserBean user) {
		user = userService.updateUserPassword(user);
		Json j = new Json();
		if (user != null) {
			if (user.getFlag() == "1") {
				GlobalContext.getSession().removeAttribute(
						GlobalConstant.CURRENT_USER);
				j.setSuccess(true);
			} else {
				j.setSuccess(false);
			}
		} else {
			j.setSuccess(false);
		}
		return j;
	}

	/**
	 * 上传图片
	 * 
	 * @param image
	 * @return UploaderJson
	 */
	@RequestMapping(value = "/uploadImg")
	@ResponseBody
	public UploaderJson uploadImg(MultipartFile image) {
		String status = "0";// 失败
		String message = "上传失败";
		SysFiles sysFile = this.sysFilesService.uploadImg(image);
		UploaderJson json = new UploaderJson();
		if (sysFile != null) {
			status = "1";// 成功
			message = "上传成功";
			json.setName(sysFile.getName());
			ResourceBundle bundle = ResourceBundle.getBundle("upload");
			String uploadUrl = bundle.getString("upload.url");
			json.setUrl(uploadUrl + sysFile.getFilePath());
			json.setFilePath(sysFile.getFilePath());
		}
		json.setMessage(message);
		json.setStatus(status);
		return json;
	}

	/**
	 * 上传文件
	 * 
	 * @param file
	 * @return String
	 */
	@RequestMapping(value = "/uploadFile")
	@ResponseBody
	public UploaderJson uploadFile(MultipartFile file) {
		String status = "0";// 失败
		String message = "上传失败";
		SysFiles sysFile = this.sysFilesService.uploadFile(file);
		UploaderJson json = new UploaderJson();
		if (sysFile != null) {
			status = "1";// 成功
			message = "上传成功";
			json.setName(sysFile.getName());
			ResourceBundle bundle = ResourceBundle.getBundle("upload");
			String uploadUrl = bundle.getString("upload.url");
			json.setUrl(uploadUrl + sysFile.getFilePath());
			json.setFilePath(sysFile.getFilePath());
		}
		json.setMessage(message);
		json.setStatus(status);
		return json;
	}

	/**
	 * 上传字幕
	 * 
	 * @param file
	 * @return String
	 */
	@RequestMapping(value = "/uploadCaption")
	@ResponseBody
	public UploaderJson uploadCaption(MultipartFile file, Long id) {
		String status = "0";// 失败
		String message = "上传失败";
		SysFiles sysFile = this.sysFilesService.uploadCaption(file, id);
		UploaderJson json = new UploaderJson();
		if (sysFile != null) {
			status = "1";// 成功
			message = "上传成功";
			json.setName(sysFile.getName());
			ResourceBundle bundle = ResourceBundle.getBundle("upload");
			String uploadUrl = bundle.getString("upload.url");
			json.setUrl(uploadUrl + sysFile.getFilePath());
			json.setFilePath(sysFile.getFilePath());
		}
		json.setMessage(message);
		json.setStatus(status);
		return json;
	}

	/**
	 * 上传视频
	 * 
	 * @param video
	 *            视频文件
	 * @param id
	 *            章节id
	 * @return UploaderJson
	 */
	@RequestMapping(value = "/uploadVideo")
	@ResponseBody
	public UploaderJson uploadVideo(MultipartFile video, Long id) {
		String status = "0";// 失败
		String message = "上传失败";
		SysFiles sysFile = this.sysFilesService.uploadVideo(video, id);
		UploaderJson json = new UploaderJson();
		if (sysFile != null) {
			status = "1";// 成功
			message = "上传成功";
			json.setName(sysFile.getName());
			json.setFilePath(sysFile.getFilePath());
			json.setTime(sysFile.getTime());
		}
		json.setMessage(message);
		json.setStatus(status);

		return json;
	}
}
