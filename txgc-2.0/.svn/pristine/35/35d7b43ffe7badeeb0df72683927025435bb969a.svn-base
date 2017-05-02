package com.yp.sys.controller.role;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.yp.sys.common.BaseController;
import com.yp.sys.common.GlobalContext;
import com.yp.sys.entity.Menu;
import com.yp.sys.entity.Role;
import com.yp.sys.entity.organization.Organization;
import com.yp.sys.pojo.Json;
import com.yp.sys.pojo.MenuBean;
import com.yp.sys.pojo.RoleBean;
import com.yp.sys.pojo.TreeNodeBean;
import com.yp.sys.pojo.ZtreeBean;
import com.yp.sys.service.menu.IMenuService;
import com.yp.sys.service.organization.IOrganizationService;
import com.yp.sys.service.role.IRoleService;

/**
 * 
 * 
 * 文件名称: 角色控制器
 * 内容摘要: //简要描述本文件的内容，包括主要模块、函数及其功能的说明
 * 创 建 人:mominglong
 * 创建日期: 2013-2-3
 * 公    司: 重庆重邮汇侧有限公司
 * 版权所有: 版权所有(C)2001-2004
 * 
 * 修改记录1: // 修改历史记录，包括修改日期、修改者及修改内容
 *   修改日期：
 *   版 本 号：
 *   修 改 人：
 *   修改内容：
 * 修改记录2：…
 *
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(RoleController.class);

	private IRoleService roleService;
	@Autowired
	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}
	private IMenuService menuService;
	@Autowired
	public void setMenuService(IMenuService menuService) {
		this.menuService = menuService;
	}
	@Autowired
	private IOrganizationService orgService;
	/**
	 * 跳转到角色管理页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/roleList")
	public String role() {
		return "sys/role/role";
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

	/**
	 * 角色treegrid
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/treegrid")
	@ResponseBody
	public List<RoleBean> treegrid(String id) {
		return roleService.treegrid(id);
	}

	/**
	 * 添加角色
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public Json add(@RequestBody RoleBean role) {
		Json roleJson = new Json();
		role = roleService.addRole(role);
		roleJson.setSuccess(true);
		return roleJson;
	}

	/**
	 * 删除角色
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping(value = "/del")
	@ResponseBody
	public Json del( String[] ids) {
		Json roleJson = new Json();
		roleService.delRole(ids);
		roleJson.setSuccess(true);
		roleJson.setMsg("删除成功!");
		return roleJson;
	}

	/**
	 * 编辑角色
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping(value = "/edit")
	@ResponseBody
	public Json edit(@RequestBody RoleBean role) {
		Json roleJson = new Json();
		try {
			role = roleService.editRole(role);
			roleJson.setSuccess(true);
		} catch (Exception e) {
		}
		return roleJson;
	}
	/**
	 * 从PID=0开始加载菜单资源
	 * 获取指定节点的全部子菜单（包括当前菜单节点）
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getMenusByPid")
	@ResponseBody
	public void getMenusByPid(HttpServletRequest request,MenuBean mBean,String roleId,HttpServletResponse response)  {
		String pid = mBean.getParentId()+"";
		if(pid==null || pid.trim().equals("")){
			pid = "0";
		}
		logger.error("==========roleId="+roleId);
		List<ZtreeBean> menus = menuService.loadMenus("", pid, "");
		// 加载全部的菜单
		if(StringUtils.isNotBlank(roleId)){
			// 加载指定角色的权限
			Role role = this.roleService.getById(Long.valueOf(roleId));
			List<Menu> lists =role.getMenus();
			
			// 拿角色拥有的菜单和全部的菜单做比对，进行勾选
			if(null!=lists && lists.size()>0){
				
				for (int i = 0; i < lists.size(); i++) {
					Menu p = lists.get(i);
//					//System.out.println(p.getPid());
					eeee(p, menus);
				}
			}
		}
		writeMenus(menus,response);
	}
	/**
	 * 角色权限和资源菜单进行对比，使checkbox选中
	 * @param p
	 * @param menus
	 */
	private void eeee(Menu p,List<ZtreeBean> menus){
		for (int j = 0; j < menus.size(); j++) {
			ZtreeBean menu = menus.get(j);
				if (menu.getId().equals(p.getId()+"")) {
					menu.setChecked(true);
					return;
				}else{
					if(menu.getChildren()!=null && menu.getChildren().size()>0){
						eeee(p, menu.getChildren());
					}
				}
		}
	}
	//输出菜单到页面
	private String writeMenus(List<ZtreeBean> root,HttpServletResponse response) {
		List<ZtreeBean> root2 = new ArrayList<ZtreeBean>();
		for(int i =0;i<root.size();i++){
			ZtreeBean mi=root.get(i);
			if(mi.getUrl()!=null && !"".equals(mi.getUrl())){
				 mi.setUrl(mi.getUrl());
			}
			 root2.add(mi); 
		}
		JSONArray json = JSONArray.fromObject(root2);
//		//System.out.println(json.toString());
		String jsonStr = json.toString();
		try {
			response.getWriter().write(jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonStr;
	}
	@RequestMapping(value = "/showTree4check/{roleId}")
	@ResponseBody
	public List<TreeNodeBean> showTree4check(@PathVariable String roleId,HttpSession session){
		List<TreeNodeBean> list = menuService.findTree4Permissions(GlobalContext.getCurrentUser().getId(), roleId);
		return list;
	}
	@RequestMapping(value = "/showTree/{roleId}")
	@ResponseBody
	public List<TreeNodeBean> showTreek(@PathVariable String roleId,HttpSession session){
		List<TreeNodeBean> list = menuService.buildTreeByRole(roleId);
		return list;
	}
	@RequestMapping(value = "/treeAll")
	@ResponseBody
	public List<TreeNodeBean> treeAll(String id, HttpSession session) {
		List<TreeNodeBean> list = menuService.treeAll(id);
		return list;
	}
	/**
	 * 从id=0开始加载机构顶级菜单
	 * 获取指定节点的全部子菜单（包括当前菜单节点）
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getOrgsbyRoleId")
	@ResponseBody
	
	public void getOrgsbyId(HttpServletRequest request,Organization org,Long roleId,HttpServletResponse response)  {
		Long id=org.getId();
		if(org.getId()==null){
			id=1L;
		}
		//查询所有的机构
		List<ZtreeBean> list=this.orgService.creatZtreeData(roleId, id);
		writeMenus(list,response);	
	}
}
