package com.yp.sys.service.workorder.impl;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yp.sys.common.GlobalContext;
import com.yp.sys.dao.common.IBaseDao;
import com.yp.sys.dao.organization.IOrganizationDao;
import com.yp.sys.dao.terminal.ITerminalInfoDao;
import com.yp.sys.dao.user.IUserDao;
import com.yp.sys.dao.workorder.ICollectDao;
import com.yp.sys.dao.workorder.ICommunityDao;
import com.yp.sys.dao.workorder.ITerminalUseDao;
import com.yp.sys.dao.workorder.IUpdatePramaDao;
import com.yp.sys.dao.workorder.IWorkOrderDao;
import com.yp.sys.dao.workorder.IWorkOrderParameterDao;
import com.yp.sys.entity.User;
import com.yp.sys.entity.collect.CollectInfo;
import com.yp.sys.entity.organization.Organization;
import com.yp.sys.entity.terminal.TerminalInfo;
import com.yp.sys.entity.terminal.TerminalUseInfo;
import com.yp.sys.entity.workorder.CommunityInfo;
import com.yp.sys.entity.workorder.WorkOrderParameter;
import com.yp.sys.entity.workorder.WorkOrderUpdatePrama;

import com.yp.sys.entity.workorder.WorkOrderInfo;
import com.yp.sys.pojo.CreatWorkOrderBean;
import com.yp.sys.pojo.DataGridBean;
import com.yp.sys.pojo.DataGridJson;
import com.yp.sys.pojo.Json;
import com.yp.sys.pojo.TreeNodeBean;
import com.yp.sys.pojo.WorkOrderInfoBean;

import com.yp.sys.service.common.impl.BaseServiceImpl;
import com.yp.sys.service.workorder.IWorkOrderService;
import com.yp.sys.util.DateUtil;
import com.yp.sys.util.IdGenerator;

/**
 * @Description: 工单管理service实现类
 * @author youxingliu
 * @date 2016年11月16日 下午4:09:19
 */
@Service("workOrderService")
public class WorkOrderServiceImpl extends BaseServiceImpl<WorkOrderInfo>
		implements IWorkOrderService {

	@Autowired
	private IBaseDao<Object[]> baseDao;
	@Autowired
	private ITerminalInfoDao terminalInfoDao;
	@Autowired
	private ITerminalUseDao terminalUseDao;
	@Autowired
	private IWorkOrderDao workOrderDao;
	@Autowired
	private ICollectDao collectDao;
	@Autowired
	private ICommunityDao communityDao;
	@Autowired
	private IUserDao userDao;
	@Autowired
	private IOrganizationDao orgDao;
	@Autowired
	private IUpdatePramaDao updatePramaDao;// 工单更新字段到工参的字段dao
	@Autowired
	private IWorkOrderParameterDao workOrderParameterDao;// 工单自定义采集字段dao

	@Override
	public DataGridJson terminalUseInfoGrid(TerminalUseInfo param,
			DataGridBean bean) {
		DataGridJson json = new DataGridJson();
		StringBuilder sb = new StringBuilder("from TerminalUseInfo ");
		String hql = sb.toString();
		List<TerminalUseInfo> list = terminalUseDao.find(hql);
		Long total = terminalUseDao.count("select count(*) " + hql);
		json.setTotal(total);
		json.setRows(list);
		return json;
	}

	@Override
	public DataGridJson workOrderGrid(WorkOrderInfoBean param, DataGridBean pg) {
		DataGridJson json = new DataGridJson();
		StringBuilder sb = new StringBuilder(" from WorkOrderInfo t ");
		List<Object> valueList = new ArrayList<Object>();
		if (param != null) {
			if (StringUtils.isNotBlank(param.getOrderState())) {
				sb.append(" and t.orderState= ?");
				valueList.add(param.getOrderState());
			}
			if (StringUtils.isNotBlank(param.getExplainState())) {
				sb.append(" and t.explainState= ?");
				valueList.add(param.getExplainState());
			}
			if (StringUtils.isNotBlank(param.getNetworkStandard())) {
				sb.append(" and t.networkStandard= ?");
				valueList.add(param.getNetworkStandard());
			}
			if (StringUtils.isNotBlank(param.getIssuedWay())) {
				sb.append(" and t.issuedWay= ?");
				valueList.add(param.getIssuedWay());
			}
			if (StringUtils.isNotBlank(param.getWorkOrderNum())) {
				sb.append(" and t.workOrderNum= ?");
				valueList.add(param.getWorkOrderNum());
			}
			if (StringUtils.isNotBlank(param.getTerminalCode())) {
				sb.append(" and t.terminal.code= ?");
				valueList.add(param.getTerminalCode());
			}
			if (StringUtils.isNotBlank(param.getTerminalTerminalUserName())) {
				sb.append(" and t.terminal.terminalUser.name= ?");
				valueList.add(param.getTerminalTerminalUserName());
			}
			if (StringUtils.isNotBlank(param.getUserNickName())) {
				sb.append(" and t.user.nickName= ?");
				valueList.add(param.getUserNickName());
			}

		}
		//数据权限
		if(GlobalContext.getCurrentUser() != null && StringUtils.isNotBlank(GlobalContext.getCurrentUser().getOrgIds())){
			sb.append(" and t.org.id in ("+GlobalContext.getCurrentUser().getOrgIds()+") ");
		}
		
		if (pg != null) {
			if (StringUtils.isNotBlank(pg.getSort())) {
				sb.append(" order by t." + pg.getSort() + " " + pg.getOrder());
			}

		}
		String hql = sb.toString().replaceFirst("and", "where");
		List<WorkOrderInfo> list = workOrderDao.find(hql, pg.getPage(),
				pg.getRows(), valueList);
		Long total = workOrderDao.count("select count(*) " + hql, valueList);
		json.setTotal(total);
		json.setRows(list);
		return json;
	}

	@Override
	public DataGridJson collectGrid(CollectInfo param, DataGridBean bean) {
		DataGridJson json = new DataGridJson();
		StringBuilder sb = new StringBuilder("from CollectInfo ");
		String hql = sb.toString();
		List<CollectInfo> list = collectDao.find(hql);
		Long total = collectDao.count("select count(*) " + hql);
		json.setTotal(total);
		json.setRows(list);
		return json;
	}

	@Override
	public DataGridJson communityGrid(CommunityInfo param, DataGridBean bean) {
		DataGridJson json = new DataGridJson();
		StringBuilder sb = new StringBuilder(
				"from CommunityInfo t where t.networkStandard='"
						+ param.getNetworkStandard() + "' ");
		String hql = sb.toString();
		if (StringUtils.isNotBlank(param.getCname())) {
			hql += " and t.cname='" + param.getCname() + "' ";
		}
		if (StringUtils.isNotBlank(param.getCi())) {
			hql += " and t.ci='" + param.getCi() + "' ";
		}
		if (StringUtils.isNotBlank(param.getLac())) {
			hql += " and  t.lac='" + param.getLac() + "' ";
		}
		List<CommunityInfo> list = communityDao.find(hql);
		Long total = communityDao.count("select count(*) " + hql);
		json.setTotal(total);
		json.setRows(list);
		return json;
	}

	@Override
	public Json createOrder(CreatWorkOrderBean paramBean) {
		Json j = new Json();
		WorkOrderInfo workOrderInfo = new WorkOrderInfo();
		TerminalInfo terminalInfo = terminalInfoDao.get(TerminalInfo.class,
				paramBean.getTerminalId());
		if (terminalInfo != null) {
			workOrderInfo.setTerminal(terminalInfo);
		}
		workOrderInfo.setUser(userDao.get(User.class, GlobalContext
				.getCurrentUser().getId()));
		//
		IdGenerator idGenerator = new IdGenerator();
		String idStr = idGenerator.getLongValue() + "";
		idStr.subSequence(idStr.length() - 5, idStr.length());
		String dateStr = DateUtil.format(new Date(), "yyyyMMdd")
				+ idStr.subSequence(idStr.length() - 5, idStr.length()) + "0";
		// 生成工单编号 规则：年月日8位，取ID生成器里面的后5位，加上一个下发标识0或1，0位系统下发，1为自定义订单
		workOrderInfo.setWorkOrderNum(dateStr);

		workOrderInfo.setOrderState("0");// 任务已下达
		workOrderInfo.setTimeLimit(paramBean.getTimeLimit());
		workOrderInfo.setIssuedWay("0");// 系统下发方式
		workOrderInfo.setIssuedRemark(paramBean.getRemark());
		workOrderInfo.setOrg(orgDao.get(Organization.class,
				paramBean.getOrgId()));
		workOrderInfo.setWorkerParameterId(paramBean.getWparameterId());
		workOrderInfo.setCreateTime(new Timestamp(new Date().getTime()));
		workOrderInfo.setNetworkStandard(paramBean.getNetworkType());
		workOrderInfo.setIssuedTime(workOrderInfo.getCreateTime());
		workOrderDao.save(workOrderInfo);

		if (paramBean != null
				&& StringUtils.isNotBlank(paramBean.getParaObjsText())) {

			JSONArray array = JSONArray.fromObject(paramBean.getParaObjsText());
			if (array != null && array.size() > 0) {
				List<WorkOrderParameter> list = new ArrayList<WorkOrderParameter>();
				for (Object obj : array) {
					Map<String, String> map = (Map<String, String>) obj;
					if (map != null && StringUtils.isNotBlank(map.get("id"))
							&& StringUtils.isNotBlank(map.get("text"))) {
						WorkOrderParameter prama = new WorkOrderParameter();
						prama.setCode(map.get("id"));
						prama.setName(map.get("text"));
						prama.setWorkOrderId(workOrderInfo.getId());
						list.add(prama);
					}
				}
				// TODO 需要调试的模块
				workOrderParameterDao.saveList(list);
			}
		}

		j.setSuccess(true);
		j.setMsg("创建成功");
		j.setObj(workOrderInfo.getWorkOrderNum());
		return j;
	}

	@Override
	public List<TreeNodeBean> userTree() {
		List<Organization> orgList = orgDao
				.find("from Organization where recordStatus='Y'");
		List<User> userList = userDao.find("from User where recordStatus='Y'");
		List<TreeNodeBean> treeList = new ArrayList<TreeNodeBean>();
		if (null != orgList && orgList.size() > 0) {
			for (Organization org : orgList) {
				if (org.getParentOrgan() == null) {
					TreeNodeBean tree = new TreeNodeBean();
					tree.setText(org.getOrgName());
					tree.setChildren(buildTree(org, orgList, userList));
					treeList.add(tree);
				}
			}
		}
		return treeList;
	}

	private List<TreeNodeBean> buildTree(Organization org,
			List<Organization> orgList, List<User> userList) {
		List<TreeNodeBean> treeList = new ArrayList<TreeNodeBean>();
		for (User user : userList) {
			if (user.getOrganization().equals(org)) {
				TreeNodeBean tree = new TreeNodeBean();
				tree.setId(user.getId() + "");
				tree.setText(user.getNickName());
				treeList.add(tree);
			}
		}
		for (Organization o : orgList) {
			if (org.equals(o.getParentOrgan())) {
				TreeNodeBean tree = new TreeNodeBean();
				tree.setText(o.getOrgName());
				tree.setChildren(buildTree(o, orgList, userList));
				treeList.add(tree);
			}
		}
		return treeList;
	}

	@Override
	public List<Object> defineColumns(String appNode) {
		List<Object> list = new ArrayList<Object>();
		Long userId = GlobalContext.getCurrentUser().getId();
		String showColumns = "";
		List<Object[]> objList = baseDao
				.findBySql("select define_content from t_cyhc_define_columns where user_Id='"
						+ userId + "' and app_node='" + appNode + "'");
		List<Object[]> objList1 = baseDao
				.findBySql("SELECT para_name,para_comment FROM t_cyhc_define_column_source WHERE para_table IN(SELECT description FROM sys_menu WHERE id= '"
						+ appNode + "')");
		if (null != objList1 && objList1.size() > 0) {
			for (Object[] obj : objList1) {
				if (StringUtils.isNotBlank(obj[1] + "")) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("id", obj[0]);
					map.put("text", obj[1]);
					if (null != objList && objList.size() > 0) {// 已有自定义列
						showColumns = objList.get(0) + ",";
						if (",".equals(showColumns)) {// 自定义列为空
							map.put("checked", true);
						} else {
							if (showColumns.contains(obj[0] + ",")) {
								map.put("checked", true);
							}
						}
					} else {
						map.put("checked", true);
					}
					list.add(map);
				}
			}
		}
		return list;
	}

	@Override
	public String saveColumns(String appNode, String content) {
		/*
		 * JSONArray array= JSONArray.fromObject(content); if(array!=null
		 * &&array.size()>0){ for(Object obj:array){ Map<String,String>
		 * map=(Map<String,String>)obj;
		 * 
		 * System.out.println(map.get("id")); } }
		 */
		Long userId = GlobalContext.getCurrentUser().getId();
		List<Object[]> objList = baseDao
				.findBySql("select define_content from t_cyhc_define_columns where user_Id='"
						+ userId + "' and app_node='" + appNode + "'");
		if (null != objList && objList.size() > 0) {
			baseDao.executeSql("update t_cyhc_define_columns set define_content='"
					+ content
					+ "' where user_Id='"
					+ userId
					+ "' and app_node='" + appNode + "'");
		} else {
			baseDao.executeSql("insert into t_cyhc_define_columns(id,user_Id,app_node,define_content) values('"
					+ System.currentTimeMillis()
					+ "','"
					+ userId
					+ "','"
					+ appNode + "','" + content + "')");
		}
		return null;
	}

	@Override
	public List<Object> findWorkOrderDefCols(String appNode, Long workOrderId) {
		String sql = "select t.code,t.name from t_cyhc_work_order_parameter t where t.work_order_id="
				+ workOrderId;
		List<Object[]> objList = baseDao.findBySql(sql);
		List<Object> list = new ArrayList<Object>();
		if (objList != null && objList.size() > 0) {
			for (Object[] obj : objList) {
				if (StringUtils.isNotBlank(obj[1] + "")
						&& StringUtils.isNotBlank(obj[0] + "")) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("id", obj[0]);
					map.put("text", obj[1]);
					// map.put("checked", false);
					list.add(map);
				}
			}
		}

		return list;
	}

	@Override
	public Json saveUpdateDefCols(String content, Long workOrderId,
			String explainRemark) {
		Json j = new Json();
		j.setSuccess(false);
		j.setMsg("同步失败");

		String buildSql = "";
		List<String> listPara = new ArrayList<String>();

		List<WorkOrderUpdatePrama> list = new ArrayList<WorkOrderUpdatePrama>();

		// 查询工单信息
		WorkOrderInfo workOrderInfo = workOrderDao.get(WorkOrderInfo.class,
				workOrderId);
		workOrderInfo.setExplainState("1");// 1: 通过，2：不通过',
		workOrderInfo.setExplainUser(userDao.get(User.class, GlobalContext
				.getCurrentUser().getId()));
		workOrderInfo.setExplainTime(new Timestamp(new Date().getTime()));
		workOrderInfo.setExplainRemark(explainRemark);
		workOrderInfo.setOrderState("4");

		if (StringUtils.isNotBlank(content)) {
			JSONArray array = JSONArray.fromObject(content);
			if (array != null && array.size() > 0) {
				for (Object obj : array) {
					Map<String, String> map = (Map<String, String>) obj;
					if (map != null && StringUtils.isNotBlank(map.get("id"))
							&& StringUtils.isNotBlank(map.get("text"))) {
						WorkOrderUpdatePrama orderUpPrama = new WorkOrderUpdatePrama();
						orderUpPrama.setPramaCode(map.get("id"));
						orderUpPrama.setPramaName(map.get("text"));
						orderUpPrama.setWorkOrderId(workOrderId);
						buildSql += "," + map.get("id");
						listPara.add(map.get("id"));
						list.add(orderUpPrama);

					}
				}

				if (StringUtils.isNotBlank(buildSql) && list != null
						&& list.size() > 0) {
					// 查询响应的信息
					buildSql = buildSql.replaceFirst(",", "");
					// 查询采集数据
					String collectSql = "select "
							+ buildSql
							+ " from t_cyhc_collect_info  where  work_order_id="
							+ workOrderId;

					List<Object[]> listCollect = baseDao.findBySql(collectSql);

					// 更新数据到标准工参信息表
					String bilidStr = "";
					if (listPara != null && listPara.size() > 0
							&& listCollect != null && listCollect.size() > 0
							//&& listCollect.get(0) != null  //放开此句则不允许空值同步，屏蔽则允许空值同步
							//&& listCollect.get(0).length == listPara.size()
							) {
						if(listPara.size() == 1){
							bilidStr=" "+listPara.toArray()[0]+"="+listCollect.toArray()[0];
						}else{
							for (int i = 0; i < listPara.size(); i++) {
								bilidStr += "," + listPara.get(i) + "="
										+ listCollect.get(0)[i];
							}
						}
						
						

						bilidStr = bilidStr.replaceFirst(",", " ");
					}

					String paraSql = "";
					if ("2g".equals(workOrderInfo.getNetworkStandard())) {
						paraSql += " update t_cyhc_worker_parameter_2g set "
								+ bilidStr;
						paraSql += ",synchronization_time='"
								+ new Timestamp(new Date().getTime());
						paraSql += "',translate_state='0' ";
						paraSql += " where id="
								+ workOrderInfo.getWorkerParameterId();
					} else if ("3g".equals(workOrderInfo.getNetworkStandard())) {
						paraSql += " update t_cyhc_worker_parameter_3g set "
								+ bilidStr;
						paraSql += ",synchronization_time='"
								+ new Timestamp(new Date().getTime());
						paraSql += "',translate_state='0' ";
						paraSql += " where id="
								+ workOrderInfo.getWorkerParameterId();
					} else if ("4g".equals(workOrderInfo.getNetworkStandard())) {
						paraSql += " update t_cyhc_worker_parameter_4g set "
								+ bilidStr;
						paraSql += ",synchronization_time='"
								+ new Timestamp(new Date().getTime());
						paraSql += "',translate_state='0' ";
						paraSql += " where id="
								+ workOrderInfo.getWorkerParameterId();
					}
					if (!"".equals(paraSql)) {

						baseDao.executeSql(paraSql);
					}
				}
			} else {
				j.setMsg("请先勾选需要同步的数据字段信息");
			}
		}

		workOrderDao.save(workOrderInfo);
		j.setMsg("审核成功");
		j.setSuccess(true);

		return j;
	}

	@Override
	public List<Object> queryColletInfoWords() {
		List<Object[]> objList = baseDao
				.findBySql(" select t.para_name,t.para_comment from t_cyhc_define_column_source t where t.para_table='t_cyhc_collect_info' ");
		List<Object> list = new ArrayList<Object>();
		if (objList != null && objList.size() > 0) {
			for (Object[] obj : objList) {
				if (StringUtils.isNotBlank(obj[1] + "")
						&& StringUtils.isNotBlank(obj[0] + "")) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("id", obj[0]);
					map.put("text", obj[1]);
					// map.put("checked", false);
					list.add(map);
				}
			}
		}
		return list;
	}


	
	@Override
	public Map<String, Object> queryWorkOrderDtail(Long workOrderId) {
		String sql = "select t.code,t.name from t_cyhc_work_order_parameter t where t.work_order_id="
				+ workOrderId;
		List<Object[]> objList = baseDao.findBySql(sql);
		String codeStr="";
		String commentStr="";
		
		Map<String,String> map=new HashMap<String,String>();
		if (objList != null && objList.size() > 0) {
			for (Object[] obj:objList) {
				if (StringUtils.isNotBlank(obj[1] + "")
						&& StringUtils.isNotBlank(obj[0] + "")) {
					codeStr+=obj[0]+",";
					commentStr+=obj[1]+",";
				}
			}
		}
		//查询对应值得字段
		if(StringUtils.isNotBlank(codeStr) && StringUtils.isNotBlank(commentStr)){
			codeStr=codeStr.substring(0, codeStr.length()-1);
			commentStr=commentStr.substring(0, commentStr.length()-1);
			String sqlVal="select "+codeStr+" from t_cyhc_collect_info  where work_order_id="+workOrderId;
			List<Object[]> listValue=baseDao.findBySql(sqlVal);
			
			if(listValue != null && listValue.size()>0){
				for(Object[] objVal :listValue){
					if(objVal!= null && objVal.length>0){
						String[] code=codeStr.split(",");
						String[] comment=commentStr.split(",");
						if(code.length == comment.length){
							for(int u=0;u<code.length;u++){
								map.put(comment[u], objVal[u]+"");
							}
						}
						break;
					}
				}
			}
		}
		Map<String,Object> resultMap=new HashMap<String,Object>();
		//map.put("tre", "1232");
		resultMap.put("collectPara", commentStr);
		if(map.isEmpty()){
			resultMap.put("collectMapExist", false);
		}else{
			resultMap.put("collectMapExist", true);
		}
		resultMap.put("collectMap", map);
		WorkOrderInfo workInfo=workOrderDao.get(WorkOrderInfo.class, workOrderId);
		resultMap.put("workOrderObj", workInfo);
		return resultMap;
	}
	
		@Override
		public Json deleteWorkOrder(Long workOrderId) {
			// TODO Auto-generated method stub
			Json j=new Json();
			j.setSuccess(false);
			j.setMsg("删除失败");
			WorkOrderInfo info=workOrderDao.get(WorkOrderInfo.class, workOrderId);
			if(info!=null){
				if(StringUtils.isNotBlank(info.getOrderState())){
					if(Integer.valueOf(info.getOrderState())==0 || Integer.valueOf(info.getOrderState())==5){
						
						workOrderDao.delete(info);
						j.setSuccess(true);
						j.setMsg("删除成功");
					}else{
						
						j.setMsg("工单已下发,删除失败");
					}
					
				}else{
					
					workOrderDao.delete(info);
					j.setSuccess(true);
					j.setMsg("删除成功");
				}
				
			}else{
				
				j.setMsg("工单已不存在,删除失败");
			}
			
			return j;
		}
	

}
