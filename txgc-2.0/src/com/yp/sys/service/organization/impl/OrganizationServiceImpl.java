package com.yp.sys.service.organization.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yp.sys.common.GeneralMethod;
import com.yp.sys.common.GlobalConstant;
import com.yp.sys.common.GlobalContext;
import com.yp.sys.dao.common.IBaseDao;
import com.yp.sys.dao.organization.IOrganizationDao;
import com.yp.sys.entity.area.Area;
import com.yp.sys.entity.organization.Organization;
import com.yp.sys.pojo.DataGridBean;
import com.yp.sys.pojo.DataGridJson;
import com.yp.sys.pojo.OrganBean;
import com.yp.sys.pojo.TreeNodeBean;
import com.yp.sys.pojo.ZtreeBean;
import com.yp.sys.service.common.impl.BaseServiceImpl;
import com.yp.sys.service.organization.IOrganizationService;
import com.yp.sys.util.YpBeanUtils;
import com.yp.sys.util.IdGenerator;
import com.yp.sys.util.StringUtil;

/**
 * 
 * 文件名称: 机构管理的service实现类
 * 内容摘要: 机构管理的service实现类
 * 创 建 人:zhph
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
@Service("organService")
public class OrganizationServiceImpl extends BaseServiceImpl<Organization> implements
		IOrganizationService {
	private IOrganizationDao organDao;
	@Autowired
	private IBaseDao baseDao;
	@Autowired
	public void setOrganDao(IOrganizationDao organDao) {
		this.organDao = organDao;
	}
	@Override
	public OrganBean add(OrganBean organBean) {
		Organization o=new Organization();
		BeanUtils.copyProperties(organBean, o);
		Organization p=organDao.get(Organization.class, organBean.getParentId());
		o.setParentOrgan(p);
		o.setRecordStatus(GlobalConstant.FLAG_Y);
		o.setSortNum(IdGenerator.getLongValue());
		//设置通用信息
		GeneralMethod.setRecordInfo(o, true);
		organDao.save(o);
		organBean.setId(o.getId());
		return organBean;
	}

	@Override
	public String edit(OrganBean organBean) {
		Organization organ = this.organDao.get(Organization.class, organBean.getId());
		if(null!=organ){
			Organization org= this.findByCode(organBean.getOrgCode());
			if(null!=org && !org.getId().equals(organ.getId())){
				return "1";
			}else{
				try {
					organ.setOrgName(organBean.getOrgName());
					organ.setOrgDesc(organBean.getOrgDesc());
					organ.setIsLeaf("0");
					organ.setNodeLevel(1l);
					Organization temp = new Organization();
					temp.setId(organBean.getParentId());
					organ.setParentOrgan(temp);
					this.organDao.update(organ);
					return "4";
				} catch (Exception e) {
					return "5";
				}
			}
		}else{
			return "6";
		}
	}

	@Override
	public List<TreeNodeBean> tree(OrganBean organBean) {
		String hql = "from Organization d where d.parentOrgan.id=0 ";
		List<Organization> organList = this.organDao.find(hql);
		List<TreeNodeBean> tree = new ArrayList<TreeNodeBean>();
		for (Organization organ:organList)
		{     
			TreeNodeBean node = this.buildTree(organ,true);
			if(node!= null)
			{
				tree.add(node);
			}
		}
		return tree;
	}
	@Override
	public boolean del(String[] ids) {
		boolean flag=true;
		for(String id:ids){
			Organization organ = this.organDao.get(Organization.class, Long.parseLong(id));
			if(null != organ){
				if(organ.getId().longValue()==1l){
					flag=false;
				}else{
					try {
						if(organ.getChildsCount()>0){
							delChildsWithPid(organ.getId());
						}
						organ.setRecordStatus(GlobalConstant.FLAG_N);
						GeneralMethod.setRecordInfo(organ, false);
					} catch (Exception e) {
						e.printStackTrace();
						flag=false;
					}
				}
			}
		}
		return flag;
	}
	/**
	 * 
	 * @Description 根据pid迭代删除所有子节点信息
	 * @param pid void
	 */
	private void delChildsWithPid(Long pid){
		String hql="from Organization where recordStatus=? and parentOrgan.id=?";
		List<Organization> parents=organDao.find(hql,GlobalConstant.FLAG_Y,pid);
		for (Organization organization : parents) {
			if(organization.getChildsCount()>0){
				delChildsWithPid(organization.getId());
			}	
			organization.setRecordStatus(GlobalConstant.FLAG_N);
			//设置通用信息
			GeneralMethod.setRecordInfo(organization, false);
		}
	}
	
	/**
	 * Description: 添加机构前先查询是否存在该名称的机构<br>
	 * @param name
	 * @return
	 */
//	private Organization findByName(String name){
//		String hql="from Organization org where org.orgName = ? ";
//		List<Object> param = new ArrayList<Object>();
//		param.add(name);
//		return this.organDao.get(hql, param);
//	}
	public Organization findByCode(String code){
		String hql="from Organization org where org.orgCode = ? ";
		List<Object> param = new ArrayList<Object>();
		param.add(code);
		return this.organDao.get(hql, param);
	}

	
	/**
	 * Description: 将该机构的子机构组装成树<br>
	 * @param organ
	 * @param recursive
	 * @param name
	 * @return
	 */
	private TreeNodeBean buildTree(Organization organ,boolean recursive,String name){
		
		boolean isExist = false;
		if(organ.getOrgName().contains(name)){//如果包含
			isExist = true;
		}
		TreeNodeBean node = new TreeNodeBean();
		node.setId(organ.getId()+"");   	 
		node.setText(organ.getOrgName());
		String hql=" from Organization t where t.parentOrgan.id=?";
		List<Object> param = new ArrayList<Object>();
		param.add(organ.getId());
		List<Organization> childList = this.organDao.find(hql, param);
		if(childList!=null&&childList.size()>0){
			// node.setState("closed");//关闭节点 	
			if(recursive){	
				List<TreeNodeBean> children = new ArrayList<TreeNodeBean>();   			 
				for (Organization org : childList){
					TreeNodeBean t = buildTree(org,true,name);
					if(t!=null){
						isExist = true;
						children.add(t);
					}		 
					node.setState("open"); 
				}
				node.setChildren(children);
			}   		 
		}    	 
		if(isExist){
			return node;
		}else{
			return null;
		}
	}
	/**
	 * 生成机构树，包含顶级机构
	 * Description: <br>
	 * @param organ
	 * @param recursive
	 * @return
	 */
	private TreeNodeBean buildTree(Organization organ,boolean recursive){
		TreeNodeBean node = new TreeNodeBean();
		node.setId(organ.getId()+"");   	 
		node.setText(organ.getOrgName());
		String hql=" from Organization t where t.parentOrgan.id=?";
		List<Object> param = new ArrayList<Object>();
		param.add(organ.getId());
		List<Organization> childList = this.organDao.find(hql, param);
		if(childList!=null&&childList.size()>0){
			// node.setState("closed");//关闭节点 	
			if(recursive){	
				List<TreeNodeBean> children = new ArrayList<TreeNodeBean>();   			 
				for (Organization org : childList){
//   				TreeNodeBean t = buildTree(org,true);
//   				if(t!=null){
//   					children.add(t);
//   				}
					TreeNodeBean t = new TreeNodeBean();
					t.setId(org.getId()+"");
					t.setText(org.getOrgName());
					t.setIconCls("tree-folder");
					t.setState("close");
					children.add(t);
				}
				node.setState("open"); 
				node.setChildren(children);
			}   		 
		}    	 
		return node;
	}
	private TreeNodeBean buildTree2(Organization organ,boolean recursive){
		TreeNodeBean node = new TreeNodeBean();
		node.setText(organ.getOrgName());
		String hql=" from Organization t where t.parentOrgan.orgCode=?";
		List<Object> param = new ArrayList<Object>();
		List<Organization> childList = this.organDao.find(hql, param);
		if(childList!=null&&childList.size()>0){
			// node.setState("closed");//关闭节点 	
			if(recursive){	
				List<TreeNodeBean> children = new ArrayList<TreeNodeBean>();   			 
				for (Organization org : childList){
					TreeNodeBean t = buildTree2(org,true);
					if(t!=null){
						children.add(t);
					}
				}
				node.setState("close"); 
				node.setChildren(children);
			}   		 
		}    	 
		return node;
	}
	private TreeNodeBean buildTree3(Organization organ,boolean recursive){
		TreeNodeBean node = new TreeNodeBean();
   	 	node.setId(organ.getId()+"");   	 
   	 	node.setText(organ.getOrgName());
   	 	String hql=" from Organization t where t.parentOrgan.id=? and recordStatus=?";
   	 	List<Organization> childList = this.organDao.find(hql,organ.getId(),GlobalConstant.FLAG_Y);
   	 	if(childList!=null&&childList.size()>0){
   		// node.setState("closed");//关闭节点 	
   		 if(recursive){	
   			List<TreeNodeBean> children = new ArrayList<TreeNodeBean>();   			 
   			for (Organization org : childList){
   				TreeNodeBean t = buildTree3(org,true);
   				if(t!=null){
   					children.add(t);
   				}
			}
   			 node.setState("close"); 
   			 node.setChildren(children);
   		 }   		 
   	 }    	 
   		 return node;
    }

	@Override
	public OrganBean search(OrganBean organBean) {
		String hql = "from Organization d where d.parentOrgan.id=0";
		Organization o=organDao.get(Organization.class, organBean.getId());
	    BeanUtils.copyProperties(o, organBean);
	    if(o.getParentOrgan()!=null){
	    	organBean.setParentId(o.getParentOrgan().getId());
	    	organBean.setParentName(o.getParentOrgan().getOrgName());
	    }
		return organBean;
	}

	
	

	@Override
	public OrganBean findOrgan(OrganBean organ) {
		if(null!=organ.getId()&& !"".equals(organ.getId())){
			Organization org= this.organDao.get(Organization.class, organ.getId());
			if(null!=org){
				OrganBean temp = new OrganBean();
				temp.setId(org.getId());
				temp.setOrgName(org.getOrgName());
				temp.setIsLeaf(org.getIsLeaf());
				temp.setNodeLevel(org.getNodeLevel());
				temp.setParentId(null!=org.getParentOrgan()?org.getParentOrgan().getId():0);
				temp.setParentName(null!=org.getParentOrgan()?org.getParentOrgan().getOrgName():"");
				return temp;
			}
		}
		return null;
	}

	@Override
	public List<TreeNodeBean> findChildren(String orgId) {
   	 	String hql=" from Organization t where t.parentOrgan.id=?";
   	 	List<Object> param = new ArrayList<Object>();
   	 	param.add(Long.valueOf(orgId));
   	 	List<Organization> childList = this.organDao.find(hql, param);
   	 	List<TreeNodeBean> children = new ArrayList<TreeNodeBean>(); 
   	 	if(childList!=null&&childList.size()>0){
   			for (Organization org : childList){
   				TreeNodeBean t = new TreeNodeBean();
   				t.setId(org.getId()+"");
   				t.setText(org.getOrgName());
   				children.add(t);
			}
   		 }   		 
   		 return children;
    }

	@Override
	public List<TreeNodeBean> treeCode(OrganBean organBean) {
		String hql = "from Organization d where d.orgCode='10000' ";
		List<Organization> organList = this.organDao.find(hql);
		List<TreeNodeBean> tree = new ArrayList<TreeNodeBean>();
		for (Organization organ:organList)
		{     
			TreeNodeBean node = this.buildTree2(organ,true);
			if(node!= null)
			{
				tree.add(node);
			}
		}
		return tree;
	}
	public List<TreeNodeBean> treeId(Organization organBean) {
		String hql = "from Organization d where d.recordStatus=? ";
		List<Organization> organList=null;
		if(organBean!=null&&organBean.getId()!=null){
			hql=hql+" and d.id=?";
			organList = this.organDao.find(hql,GlobalConstant.FLAG_Y,organBean.getId());
		}else{
			hql=hql+" and d.parentOrgan=null";
			organList = this.organDao.find(hql,GlobalConstant.FLAG_Y);
		}
		List<TreeNodeBean> tree = new ArrayList<TreeNodeBean>();
		for (Organization organ:organList){
			TreeNodeBean node = this.buildTree3(organ,true);
			if(node!= null){
				tree.add(node);
			}
		}
		return tree;
	}

	

	@Override
	public Map<String, Organization> mapOrgan() {
		Map<String,Organization> result=new HashMap<String, Organization>();
		String hql ="  from Organization t ";
		List<Organization> orgList = this.organDao.find(hql);
		return result;
	}
	
	private List<OrganBean> findChildrenBean(String orgId){
		String hql ="from Organization where  recordStatus =? ";
		List<Organization> orgs=null;
		if(StringUtils.isNotBlank(orgId)){
			hql+=" and parentOrgan.id =? order by sortNum ";
		}
		orgs=organDao.find(hql, GlobalConstant.FLAG_Y,Long.valueOf(orgId));
		List<OrganBean> obs=new ArrayList<OrganBean>();
		if(null!=orgs && orgs.size()>0){
			for (Organization org : orgs) {
				OrganBean ob=new OrganBean();
				BeanUtils.copyProperties(org, ob);
				if(org.getChildsCount()>0){
					ob.setState("closed");
				}else{
					ob.setState("open");
				}
				if(org.getParentOrgan()!=null){
					ob.set_parentId(org.getParentOrgan().getId());
				}
				obs.add(ob);
			}
		}
		return obs;
	}
	@Override
	public DataGridJson findTreeGridByPid(String orgId) {
		
		String hql ="from Organization where  recordStatus =? ";// nodeLevel<1000100010001 and
		List<Organization> orgs=null;
		//sql中null判断方式不一样
		if(orgId==null){
			hql = hql + " and parentOrgan is null order by sortNum ";
			orgs=organDao.find(hql, GlobalConstant.FLAG_Y);
		}else{
			hql = hql + " and parentOrgan.id =? order by sortNum ";
			orgs=organDao.find(hql, GlobalConstant.FLAG_Y,Long.valueOf(orgId));
		}
		List<OrganBean> obs=new ArrayList<OrganBean>(0);
		for (Organization org : orgs) {
			OrganBean ob=new OrganBean();
			BeanUtils.copyProperties(org, ob);
			if(org.getChildsCount()>0){
				ob.setState("closed");
			}else{
				ob.setState("open");
			}
			if(org.getParentOrgan()!=null){
				ob.set_parentId(org.getParentOrgan().getId());
			}
			obs.add(ob);
		}
		
		DataGridJson dgj=new DataGridJson();
		if(orgs==null){
			dgj.setTotal(0l);
		}else{
			dgj.setTotal((long)orgs.size());
		}
		
		dgj.setRows(obs);
		return dgj;
	}

	@Override
	public OrganBean update(OrganBean organ) {
		Organization o=organDao.get(Organization.class, organ.getId());
		//设置通用信息
		GeneralMethod.setRecordInfo(o, false);
		YpBeanUtils.copyPropertiesIgnoreNull(organ, o);
		return organ;
	}

	@Override
	public Boolean updateSort(OrganBean organBean, String moveFlag) {
		boolean flag=false;
		if(organBean==null&&organBean.getId()==null&&StringUtil.isNotBlank(moveFlag)){
			return flag;
		}
		StringBuffer hql=new StringBuffer();
		Organization nowOrg=organDao.get(Organization.class,organBean.getId());
		if(nowOrg==null){
			return flag;
		}
		hql.append(" FROM Organization As i ");
		hql.append(" WHERE recordStatus=? ");
		try{
			//生成查询目标组织的 hql
			
			
			//当前节点同父节点的组织
			List<Organization> orgList=null;
			if(nowOrg.getParentOrgan()!=null){
				hql.append(" and parentOrgan.id =? ");
			}else{
				hql.append(" and parentOrgan = null ");
			}
			if(moveFlag.equals("1")){
				hql.append(" and i.sortNum <? order by sortNum desc)");
			}else if(moveFlag.equals("0")){
				hql.append(" and i.sortNum >? order by sortNum asc )");
			}else{
				 return flag;
			 
			}
			//根据节点有无父节点调用不同的查询
			if(nowOrg.getParentOrgan()!=null){
				orgList=organDao.find(hql.toString(),GlobalConstant.FLAG_Y,nowOrg.getParentOrgan().getId(),nowOrg.getSortNum());
			}else{
				orgList=organDao.find(hql.toString(),GlobalConstant.FLAG_Y,nowOrg.getSortNum());
			}
			//查询出需要调换顺序的目标组织
		 	
		 	if(orgList==null||orgList.size()==0){
		 		return flag;
		 	}
		 	Organization targetOrg=orgList.get(0);
			Long temp=targetOrg.getSortNum();
			targetOrg.setSortNum(nowOrg.getSortNum());
			nowOrg.setSortNum(temp);
			 /*设置通用信息*/
			GeneralMethod.setRecordInfo(nowOrg, false);
			GeneralMethod.setRecordInfo(targetOrg, false);
			flag=true;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	@Override
	public Long countHql(String hql, Object[] params) {
		// TODO Auto-generated method stub
		return organDao.count(hql, params);
	}
	@Override
	public OrganBean beanSearch(Long pkId) {
		OrganBean organBean = new OrganBean();
		Organization o = organDao.get(Organization.class, pkId);
	    BeanUtils.copyProperties(o, organBean);
	    if(o.getParentOrgan()!=null){
	    	organBean.setParentId(o.getParentOrgan().getId());
	    	organBean.setParentName(o.getParentOrgan().getOrgName());
	    }
		return organBean;
	}
	
	public List<Organization> getAllList(){
		StringBuffer hql =new StringBuffer();
		hql.append(" from Organization where  100010000<nodeLevel and nodeLevel<1000100010001");
		hql.append("  and  recordStatus=?  order by orgCode ");
		List<Organization> list=organDao.find(hql.toString(), GlobalConstant.FLAG_Y);
		return list;
	}
	
	public DataGridJson datagrid(DataGridBean dg, Organization organization){
		DataGridJson json=new DataGridJson();
		 List<Object> values = new ArrayList<Object>();
		 StringBuffer hql =new StringBuffer();
		 hql.append(" FROM Organization  ");
		 hql.append(" WHERE nodeLevel>=1000100010001 and  recordStatus='").append(GlobalConstant.FLAG_Y).append("' ");
		 /*条件查询判断========================================*/
	 	 if(null!=organization){
			 if(StringUtil.isNotBlank(organization.getOrgName())){
				 hql.append(" and orgName like ? ");
				 values.add("%"+organization.getOrgName()+"%");
			 }
	 	 }
	 	 /*条件查询判断========================================end*/
		 String totalHql = " select count(*) " + hql;
		 /* 设置总记录数*/
		 json.setTotal(organDao.count(totalHql, values));
		 
		 List<Organization> link = organDao.find(hql.toString(),dg.getPage(), dg.getRows(), values);// 查询分页
		 json.setRows(link);// 设置返回的行
		return json;
	}
	
	public List<TreeNodeBean> treeOrgId(Organization organBean) {
		String hql = "from Organization d where d.recordStatus=? ";
		List<Organization> organList=null;
		if(organBean!=null&&organBean.getId()!=null){
			hql=hql+" and d.id=?";
			organList = this.organDao.find(hql,GlobalConstant.FLAG_Y,organBean.getId());
		}else{
			hql=hql+" and d.parentOrgan=null";
			organList = this.organDao.find(hql,GlobalConstant.FLAG_Y);
		}
		List<TreeNodeBean> tree = new ArrayList<TreeNodeBean>();
		for (Organization organ:organList){
			TreeNodeBean node = this.buildTree4(organ,true);
			if(node!= null){
				tree.add(node);
			}
		}
		return tree;
	}
	private TreeNodeBean buildTree4(Organization organ,boolean recursive){
		TreeNodeBean node = new TreeNodeBean();
   	 	node.setId(organ.getId()+"");   	 
   	 	node.setText(organ.getOrgName());
   	 	String hql=" from Organization t where t.parentOrgan.id=? and t.nodeLevel<1000100010001 and recordStatus=?";
   	 	List<Organization> childList = this.organDao.find(hql,organ.getId(),GlobalConstant.FLAG_Y);
   	 	if(childList!=null&&childList.size()>0){
   		// node.setState("closed");//关闭节点 	
   		 if(recursive){	
   			List<TreeNodeBean> children = new ArrayList<TreeNodeBean>();   			 
   			for (Organization org : childList){
   				TreeNodeBean t = buildTree4(org,true);
   				if(t!=null){
   					children.add(t);
   				}
			}
   			 node.setState("close"); 
   			 node.setChildren(children);
   		 }   		 
   	 }    	 
   		 return node;
    }
	/***********************************************************************/
	@Override
	public List<TreeNodeBean> treeAllId(Organization org) {
		String hql = "from Organization d where  d.recordStatus=? ";
		List<Organization> organList=null;
		
		hql=hql+" and d.parentOrgan=null";
		organList = this.organDao.find(hql,GlobalConstant.FLAG_Y);
		List<TreeNodeBean> tree = new ArrayList<TreeNodeBean>();
		for (Organization organ:organList){
			TreeNodeBean node = this.buildTree5(organ,true);
			if(node!= null){
				tree.add(node);
			}
		}
		return tree;
	}
	/**
	 * 
	 * @param organ
	 * @param recursive
	 * @return
	 */
	private TreeNodeBean buildTree5(Organization organ,boolean recursive){
		TreeNodeBean node = new TreeNodeBean();
   	 	node.setId(organ.getId()+"");   	 
   	 	node.setText(organ.getOrgName());
   	 	String hql=" from Organization t where t.parentOrgan.id=? and recordStatus=?";
   	 	List<Organization> childList = this.organDao.find(hql,organ.getId(),GlobalConstant.FLAG_Y);
   	 	if(childList!=null&&childList.size()>0){
   		// node.setState("closed");//关闭节点 	
	   		 if(recursive){	
	   			List<TreeNodeBean> children = new ArrayList<TreeNodeBean>();   			 
	   			for (Organization org : childList){
	   				TreeNodeBean t = buildTree5(org,true);
	   				if(t!=null){
	   					children.add(t);
	   				}
				}
	   			 node.setState("close"); 
	   			 node.setChildren(children);
	   		 }   		 
	   	 }
   		 return node;
    }
	
	@Override
	public List<Organization> queryUserMangerOrgByUserId(Long userId) {
		//并非是List<Organization>类型的list
			List<Organization> list=organDao.queryUserMangerOrgByUserId(userId);
			//List<Organization> listTem=new ArrayList<Organization>();
			List<Organization> listEnd=new ArrayList<Organization>();
			if(list!= null && list.size()>0){
				for(Object objct:list){
					Object[] obj=(Object[])objct;
					Organization orgT=new Organization();
					orgT.setId(Long.valueOf(obj[0]+""));
					orgT.setCode(obj[1]+"");
					orgT.setOrgName(obj[2]+"");
					orgT.setAreaCode(obj[3]+"");
					orgT.setAreaName(obj[4]+"");
				//	listTem.add(orgT);
					listEnd.add(orgT);
					//orgT.setParentOrgan(parentOrgan);			
				}
			}
			
			
		/*	
			//List<Organization>  allOrgList=new ArrayList<Organization>();
			if(listTem != null && listTem.size()>0){
				for(Organization org:listTem){
					List<Organization> childList=queryAllChildsById(String.valueOf(org.getId()));
					if(childList!=null && childList.size() > 0){
						for(Organization childOrg:childList){
							listEnd.add(childOrg);
						}
					}
				}
			}
		*/	
				return listEnd;
	
			
	}
	@Override
	public List<Organization> queryOrgByUserId(Long userId){
		List<Organization> list=new ArrayList<Organization>();
		String sql=" select org_id from sys_user_to_org where user_id="+userId;
		List<Object> listObj=baseDao.findBySql(sql);
		if(listObj!=null && listObj.size()>0){
			for(Object obj:listObj){
				list.add(organDao.get(Organization.class, Long.valueOf(obj.toString())));
			}
		}
		
		return list;
	}
	
	
	@Override
	public List<Area> findAreaList() {
		
		List<Area> list=(List<Area>)baseDao.find(" from Area t where t.level=3 ");
		return list!=null&& list.size()>0?list:null;
	}
	
	@Override
	public List<Organization> queryAllChildsById(String orgid) {
		String hql=" from Organization t where t.parentOrgan.id=? ";
		List<Organization> childList = this.organDao.find(hql,Long.valueOf(orgid));
		List<Organization> listTem=new ArrayList<Organization>(); 
		//childList.add(orgNow);
		if(childList != null && childList.size() > 0){
			for(Organization org:childList){
				listTem.add(org);
				List<Organization> listOrg=buildListOrgs(org);
				if(listOrg !=null && listOrg.size()>0){
					for(Organization orgTem:listOrg){
						listTem.add(orgTem);
					}
				}
			}
		}
		
		return listTem;
	}
	
	private List<Organization> buildListOrgs(Organization org){
		String hql=" from Organization t where t.parentOrgan.id=? ";
		List<Organization> childList = this.organDao.find(hql,org.getId());
		
		List<Organization> listTem=new ArrayList<Organization>(); 
		if(childList != null && childList.size() > 0){
			for(Organization orgChild:childList){
				listTem.add(orgChild);
				List<Organization> listOrg=buildListOrgs(orgChild);
				if(listOrg !=null && listOrg.size()>0){
					for(Organization orgTem:listOrg){
						listTem.add(orgTem);
					}
				}
			}
		}
		return listTem;
	}
	
	@Override
	public List<Object[]> findOrgListByRoleId(String roleId) {
		String sql="select t.org_id from sys_role_to_org t where t.role_id= "+roleId;
		return	baseDao.findBySql(sql);
	}
	
	@Override
	public List<ZtreeBean> creatZtreeData(Long roleId,Long orgId){
		//roleId为用户ID
		//List<ZtreeBean> treeList=new ArrayList<ZtreeBean>();
		List<Organization> allowCheckedOrgList=new ArrayList<Organization>(); ;
		if(GlobalContext.getCurrentUser() != null){
			if(GlobalContext.getCurrentUser().getId()==0L){
				
			}else{
				allowCheckedOrgList=GlobalContext.getCurrentUser().getOrgs();
			}
		}else{
			return null;
		}
		
		//查询当前角色已经拥有的菜单
		List<Object[]> hadOrgsList=new ArrayList<Object[]>();
		if(!"".equals(roleId)){
			String sql="select t.org_id from sys_user_to_org t where t.user_id="+roleId;
			hadOrgsList=baseDao.findBySql(sql);
		}
		String hql=" from Organization t where t.id=? ";
		List<Organization> childList = this.organDao.find(hql,orgId);
		List<ZtreeBean> listZtreeBean=new ArrayList<ZtreeBean>(); 
		if(childList != null && childList.size() > 0){
			
			for(Organization orgChild:childList){
				ZtreeBean ztreeBean=new ZtreeBean();
				ztreeBean.setId(orgChild.getId()+"");
				ztreeBean.setName(orgChild.getOrgName());
				//ztreeBean.setUrl(orgChild.getNodeLevel()+"");
				//判断哪些选中
				if(hadOrgsList!=null && hadOrgsList.size()>0){
					for(Object obj:hadOrgsList){
						if(obj!=null){
							Long temOrgId=Long.valueOf(obj+"");
							if(temOrgId.longValue()==orgChild.getId().longValue()){
								ztreeBean.setChecked(true);
								break;
							}
						}
					}
				}
				//判断哪些可用，哪些不可用
				if(allowCheckedOrgList!=null && allowCheckedOrgList.size()>0){
					ztreeBean.setChkDisabled(true);
					for(Organization allowOrg:allowCheckedOrgList){
						if(allowOrg!=null){
							if(allowOrg.getId().longValue()==orgChild.getId().longValue()){
								ztreeBean.setChkDisabled(false);
								break;
							}
						}
					}
				}
				
				
				//listZtreeBean.add(orgChild);
				List<ZtreeBean> listZtreeTem=creatZtreeDataChild(hadOrgsList,orgChild.getId(),allowCheckedOrgList);
				if(listZtreeTem !=null && listZtreeTem.size()>0){
					ztreeBean.setChildren(listZtreeTem);
				}
				listZtreeBean.add(ztreeBean);
				
			}
			
		}
		return listZtreeBean;
	}

	
	public List<ZtreeBean> creatZtreeDataChild(List<Object[]> hadOrgsList,Long orgId,List<Organization> allowCheckedOrgList){
		List<ZtreeBean> treeList=new ArrayList<ZtreeBean>();
		
		//查询当前角色已经拥有的菜单  hadOrgsList
		String hql=" from Organization t where t.parentOrgan.id=? ";
		List<Organization> childList = this.organDao.find(hql,orgId);
		List<ZtreeBean> listZtreeBean=new ArrayList<ZtreeBean>(); 
		if(childList != null && childList.size() > 0){
			
			for(Organization orgChild:childList){
				ZtreeBean ztreeBean=new ZtreeBean();
				ztreeBean.setId(orgChild.getId()+"");
				ztreeBean.setName(orgChild.getOrgName());
				//ztreeBean.setUrl(orgChild.getNodeLevel()+"");
				
				//判断哪些选中
				if(hadOrgsList!=null && hadOrgsList.size()>0){
					for(Object obj:hadOrgsList){
						if(obj!=null){
							Long temOrgId=Long.valueOf(obj+"");
							if(temOrgId.longValue()==orgChild.getId().longValue()){
								ztreeBean.setChecked(true);
								break;
							}
						}
					}
				}
				//判断哪些可用，哪些不可用
				if(allowCheckedOrgList!=null && allowCheckedOrgList.size()>0){
					ztreeBean.setChkDisabled(true);
					for(Organization allowOrg:allowCheckedOrgList){
						if(allowOrg!=null){
							if(allowOrg.getId().longValue()==orgChild.getId().longValue()){
								ztreeBean.setChkDisabled(false);
								break;
							}
						}
					}
				}
				List<ZtreeBean> listZtreeTem=creatZtreeDataChild(hadOrgsList,orgChild.getId(),allowCheckedOrgList);
				if(listZtreeTem !=null && listZtreeTem.size()>0){
					ztreeBean.setChildren(listZtreeTem);
				}
				listZtreeBean.add(ztreeBean);
				
			}
			
		}
		return listZtreeBean;
	}
	
	@Override
	public List<Organization> findOrgByCurrentUser() {
		
		List<Organization> list=GlobalContext.getCurrentUser().getOrgs();
		if(list == null || list.size() <= 0){
			String hql=" from Organization ";
			list=this.organDao.find(hql);
		}
		return list;
	}
	@Override
	public Map<String,Organization> findAllOrgByCurrentUser(String orgId) {
		Map<String,Organization> result = new HashMap<String,Organization>();
		result=findOrgs(orgId,result);
		return result;
	}
	private Map<String,Organization> findOrgs(String orgId,Map<String,Organization> map){
		String hql=" from Organization t where  t.parentOrgan.id=? and t.recordStatus=?";
		List<Object> params=new ArrayList<Object>();
		params.add(Long.valueOf(orgId));
		params.add(GlobalConstant.FLAG_Y);
		List<Organization> orgList = organDao.find(hql, params);
		if(null!=orgList && orgList.size()>0){
			for(Organization org:orgList){
				boolean flag=true;
				for(String key:map.keySet()){
					if(key.equals(org.getId()+"")){
						flag=false;
						break;
					}
				}
				if(flag){
					map.put(org.getId()+"", org);
					findOrgs(org.getId()+"",map);
				}
			}
		}
		return map;
	}
	
}
