package com.yp.sys.controller.organization;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.yp.sys.common.GlobalContext;
import com.yp.sys.entity.area.Area;
import com.yp.sys.entity.organization.Organization;
import com.yp.sys.pojo.DataGridBean;
import com.yp.sys.pojo.DataGridJson;
import com.yp.sys.pojo.Json;
import com.yp.sys.pojo.OrganBean;
import com.yp.sys.pojo.TreeNodeBean;
import com.yp.sys.pojo.UserBean;
import com.yp.sys.service.organization.IOrganizationService;
import com.yp.sys.service.user.IUserService;

/**
 * 
 * 文件名称: 组织机构controller 内容摘要: //简要描述本文件的内容，包括主要模块、函数及其功能的说明 创 建 人: 创建日期: Dec 6,
 * 2013 公 司: 亚德科技股份有限公司 版权所有: 版权所有(C)2001-2004
 * 
 * 修改记录1: // 修改历史记录，包括修改日期、修改者及修改内容 修改日期： 版 本 号： 修 改 人： 修改内容： 修改记录2：…
 * 
 */
@Controller
@RequestMapping("/organ")
public class OrganizationController {
	private IUserService userService;
	@Autowired
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	private IOrganizationService organService;
	@Autowired
	public void setOrganService(IOrganizationService organService) {
		this.organService = organService;
	}

	@RequestMapping(value = "/main")
	public String toOrgan() {
		return "sys/organization/organization";

	}

	/**
	 * Description:查询机构树 <br>
	 * 
	 * @param organBean
	 * @return
	 */
	@RequestMapping(value = "/tree")
	@ResponseBody
	public List<TreeNodeBean> tree(OrganBean organBean) {
		return this.organService.tree(organBean);
	}

	/**
	 * Description:查询机构树 <br>
	 * 
	 * @param organBean
	 * @return
	 */
	@RequestMapping(value = "/treeCode")
	@ResponseBody
	public List<TreeNodeBean> treeCode(OrganBean organBean) {
		return this.organService.treeCode(organBean);
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

	@RequestMapping(value = "/findChildrens")
	@ResponseBody
	public List<TreeNodeBean> findChildrens(String orgId) {
		return this.organService.findChildren(orgId);
	}

	@RequestMapping(value = "/findChildren")
	@ResponseBody
	public void tree(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/text");
		String orgId = request.getParameter("orgId");
		List<TreeNodeBean> lists = this.organService.findChildren(orgId);
		String orgData = "[";
		if (null != lists && lists.size() > 0) {
			for (int i = 0; i < lists.size(); i++) {
				orgData += i == 0 ? "{id:'" + lists.get(i).getId() + "',text:'"
						+ lists.get(i).getText() + "'}" : ",{id:'"
						+ lists.get(i).getId() + "',text:'"
						+ lists.get(i).getText() + "'}";
			}
		}
		orgData += "]";
		StringBuilder sbResult = new StringBuilder();
		sbResult.append("{");
		sbResult.append("orgData:{0}".replace("{0}", orgData));
		sbResult.append("}");
		response.getWriter().print(sbResult.toString());
	}

	/**
	 * Description: 查询机构树<br>
	 * 
	 * @param organBean
	 * @return
	 */
	@RequestMapping(value = "/search")
	@ResponseBody
	public OrganBean search(OrganBean organBean) {
		//System.out.println(1);
		return this.organService.search(organBean);
	}

	/**
	 * Description: 添加或修改机构<br>
	 * 
	 * @param organ
	 * @return
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public Json add(OrganBean organ) {
		String tempParentNodeLevel =  "";
		String hqlWhere = " is null";
		Json j = new Json();
		j.setSuccess(true);
		organ.setIsLeaf("0");
		if(organ.getParentId() != null && 0!=organ.getParentId()){
			organ.setIsLeaf("1");
			hqlWhere = "=" + organ.getParentId();
			OrganBean parseOrganBean = organService.beanSearch(organ.getParentId());
			if(parseOrganBean != null){
				tempParentNodeLevel = parseOrganBean.getNodeLevel() + "";
			}
		}
		Long tempCount = organService.countHql(" select count(*) from Organization where parentOrgan.id "+hqlWhere, new Object[]{});
		tempCount = tempCount + 10001;
		if(tempParentNodeLevel != null && !tempParentNodeLevel.equals("")){
			tempParentNodeLevel = tempParentNodeLevel + (tempCount+"").substring(1);
			tempCount = Long.parseLong(tempParentNodeLevel);
		}
		organ.setNodeLevel(tempCount);
		
		organ=organService.add(organ);
		return j;
	}
	/**
	 * Description: 添加或修改机构<br>
	 * 
	 * @param organ
	 * @return
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public Json update(OrganBean organ) {
		Json j = new Json();
		j.setSuccess(true);
		organ.setIsLeaf("0");
		if(organ.get_parentId() != null){
			organ.setIsLeaf("1");
		}
		organ=organService.update(organ);
		return j;
	}
	/**
	 * 删除
	 */
	@RequestMapping(value="/del")
	public @ResponseBody Boolean del(@RequestBody String[] ids,HttpServletRequest request) {
		return organService.del(ids);
	}
	/**
	 * Description: 查询机构详情<br>
	 * 
	 * @param organ
	 * @return
	 */
	@RequestMapping(value = "/findOrgan")
	@ResponseBody
	public Json findOrgan(OrganBean organ) {
		OrganBean org = this.organService.findOrgan(organ);
		Json j = new Json();
		j.setSuccess(true);
		j.setObj(org);
		return j;
	}
	/**
	 * 
	 * @Description 获取treegrid
	 * @param organ
	 * @return Json
	 */
	@RequestMapping(value = "/treegrid")
	@ResponseBody
	public DataGridJson treegrid(String orgId) {
		DataGridJson dgj=organService.findTreeGridByPid(orgId);
		return dgj;
	}
	/**
	 * 
	 * @Description  调整排序
	 * @param organ
	 * @return Boolean
	 */
	@RequestMapping(value = "/updateSort")
	@ResponseBody
	public Boolean updateSort(OrganBean organBean,String moveFlag) {
		return organService.updateSort(organBean,moveFlag);
	}
	
	/**
	 * 专业添加
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String index(){
		return "sys/organization/professionList";
	}
	
	@RequestMapping(value = "/datagrid")
	@ResponseBody
	public DataGridJson datagrid(DataGridBean dg,Organization organization) {
		return organService.datagrid(dg,organization);
	}
	
	@RequestMapping(value="/addProOrg",method=RequestMethod.GET)
	public String toProOrg(Long id,Model model){
		Organization list;
		if(id!=null){
			list=organService.getById(id);
			model.addAttribute("list", list);
		}
		model.addAttribute("orglist", organService.getAllList());
		return "sys/organization/addProfessionList";
	}
	//保存
	@RequestMapping(value="/saveOrganization")
	@ResponseBody
	public Json add(Organization organization) {
		Json j=new Json();
		organization.setIsLeaf("1");
		//organization.setNodeLevel(9000100010001l);
		organization.setOrgCode("");
		organService.save(organization);
		j.setSuccess(true);
		return j;
	}
	@RequestMapping(value="/updateOrganization")
	@ResponseBody
	public Json update(Organization organization) {
		Json j=new Json();
		if(organization.getId()!=null){
			organization.setNodeLevel(organization.getNodeLevel());
			organService.update(organization);
			j.setSuccess(true);
		}else{
			j.setSuccess(false);
		}
		
		return j;
	}
	//删除
	@RequestMapping(value = "/delOrgan")
	@ResponseBody
	public Json delOrganById(@RequestParam("ids[]") String[] ids) {
		Json j = new Json();
		organService.deleteById(ids);
		j.setSuccess(true);
		return j;
	}
	
	/*专业管理 下拉树*/
	@RequestMapping(value = "/findOrgTreeByP")
	@ResponseBody
	public List<TreeNodeBean> findOrgTreeByP() {
		Organization org=new Organization();
		
		return this.organService.treeOrgId(org);
	}
	/**
	 * 学生信息那棵树
	 * @return
	 */
	@RequestMapping(value = "/findOrgTree")
	@ResponseBody
	public List<TreeNodeBean> findOrgTree() {
		
		Organization org=new Organization();
		
		return this.organService.treeAllId(org);
	}
	/**
	 * 查询区域列表
	 * @return
	 */
	@RequestMapping(value = "/findAreaList")
	@ResponseBody
	public List<Area> findAreaList(){
		return organService.findAreaList();
	}
	

}
