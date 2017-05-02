package com.yp.sys.service.organization;

import java.util.List;
import java.util.Map;

import com.yp.sys.entity.area.Area;
import com.yp.sys.entity.organization.Organization;
import com.yp.sys.pojo.DataGridBean;
import com.yp.sys.pojo.DataGridJson;
import com.yp.sys.pojo.OrganBean;
import com.yp.sys.pojo.TreeNodeBean;
import com.yp.sys.pojo.ZtreeBean;
import com.yp.sys.service.common.IBaseService;


/**
 * 
 * 文件名称: 机构管理service
 * 内容摘要: 机构管理service
 * 创 建 人:
 * 创建日期: Dec 6, 2013
 * 公    司: 重庆重邮汇侧有限公司
 * 版权所有: 版权所有(C)2001-2004
 * 
 * 修改记录1: 
 *   修改日期：
 *   版 本 号：
 *   修 改 人：
 *   修改内容：
 * 修改记录2：…
 * 
 */
public interface IOrganizationService extends IBaseService<Organization> {
	
	/**
	 * Description: 查询机构详情<br>
	 * @param organ
	 * @return
	 */
	public OrganBean findOrgan(OrganBean organ);
	
	/**
	 * 
	 * Description:添加机构信息 <br>
	 * @param organBean
	 * @return
	 */
	public OrganBean add(OrganBean organBean);
	/**
	 * Description: 编辑机构信息<br>
	 * @param organBean
	 * @return
	 */
	public String edit(OrganBean organBean);
	/**
	 * Description:获得机构树 <br>
	 * @param id
	 * @return
	 */
	public  List<TreeNodeBean> tree(OrganBean organBean);
	/**
	 * Description: 获取机构树，得到机构编码和机构名称<br>
	 * @param organBean
	 * @return
	 */
	public  List<TreeNodeBean> treeCode(OrganBean organBean);
	/**
	 * Description: 获取机构树，得到机构ID和机构名称<br>
	 * @param organBean
	 * @return
	 */
	public List<TreeNodeBean> treeId(Organization organBean);
	/**
	 * Description: 删除机构<br>
	 * @param ids
	 * @return
	 */
	public boolean del(String[] ids);
	/**
	 * Description: 查询机构并组装成树<br>
	 * @param organBean
	 * @return
	 */
	public OrganBean search(OrganBean organBean);
	
	
	/**
	 * Description: 根据机构编码查询机构<br>
	 * @param code
	 * @return
	 */
	public Organization findByCode(String code);
	/**
	 * Description:查询子节点 <br>
	 * @param orgId
	 * @return
	 */
	public List<TreeNodeBean> findChildren(String orgId);
	
	
	
	/**
	 * 获取机构的编码和名称并保存到map中
	 * @return
	 */
	public Map<String,Organization> mapOrgan();
	/**
	 * 
	 * @Description 根据组织id获取所有子组织
	 * @param orgId
	 * @return DataGridJson
	 */
	public DataGridJson findTreeGridByPid(String orgId);
	/**
	 * 
	 * @Description 修改机构信息 
	 * @param organ
	 * @return OrganBean
	 */
	public OrganBean update(OrganBean organ);
	/**
	 * 调整排序
	 * @Description 
	 * @param organBean
	 * @param moveFlag
	 * @return Boolean
	 */
	public Boolean updateSort(OrganBean organBean, String moveFlag);
	
	/**
	 * 条件查询总条数
	 * @param hql
	 * @param Object[] params
	 * @return Long
	 */
	Long countHql(String hql,Object[] params);
	/**
	 * 查询 bean
	 * @param pkId
	 * @return
	 */
	OrganBean beanSearch(Long pkId);
	
	/**
	 * 
	 * @return
	 */
	public List<Organization> getAllList();

	/**
	 * 列表
	 * @param dg
	 * @param organization
	 * @return
	 */
	public DataGridJson datagrid(DataGridBean dg, Organization organization);
	/**
	 * 
	 * @param org
	 * @return
	 */

	public List<TreeNodeBean> treeOrgId(Organization org);
	/**
	 * 
	 * @param org
	 * @return
	 */

	public List<TreeNodeBean> treeAllId(Organization org);
	/**
	 * 通过用户id查询用户操作机构
	 * @param userId
	 * @return
	 */
	public List<Organization> queryUserMangerOrgByUserId(Long userId);
	
	
	/**
	 * 通过用户id查询用户操作机构(目前使用)
	 * @param userId
	 * @return
	 */
	public List<Organization> queryOrgByUserId(Long userId);
	
	/**
	 * 获取区域列表
	 * @return
	 */
	public List<Area> findAreaList();
	
	/**
	 * 根据某一节点，查询其所有包含的机构，也包括本机构自己（无需组装成树形结构）
	 * @param orgid
	 * @return
	 */
	public List<Organization> queryAllChildsById(String orgid);
	
	
	/**
	 * 查询某一角色关联的机构
	 */
	public List<Object[]> findOrgListByRoleId(String roleId);
	
	/**
	 * 构建ztree结构，并选中当前角色已有的机构
	 * @param hadOrgsList
	 * @param orgId
	 * @return List<ZtreeBean>
	 */
	public List<ZtreeBean> creatZtreeData(Long roleId,Long orgId);
	
	/**
	 * 根据当前用户查询当前用户机构权限
	 * @return List<Organization>
	 */
	public List<Organization> findOrgByCurrentUser();
	/**
	 * 查询当前用户所属机构及其下级机构信息
	 * @return
	 */
	public Map<String,Organization> findAllOrgByCurrentUser(String orgId);
}
