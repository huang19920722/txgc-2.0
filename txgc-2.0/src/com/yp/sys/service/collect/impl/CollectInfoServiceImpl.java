package com.yp.sys.service.collect.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yp.sys.common.GlobalContext;
import com.yp.sys.dao.common.IBaseDao;
import com.yp.sys.dao.image.IImageDAO;
import com.yp.sys.dao.params.IParams3Dao;
import com.yp.sys.dao.params.IParams4Dao;
import com.yp.sys.dao.params.IParamsDao;
import com.yp.sys.dao.user.IUserDao;
import com.yp.sys.dao.workorder.ICollectDao;
import com.yp.sys.dao.workorder.IWorkOrderDao;
import com.yp.sys.entity.Image;
import com.yp.sys.entity.User;
import com.yp.sys.entity.collect.CollectInfo;
import com.yp.sys.entity.params.Params;
import com.yp.sys.entity.params.Params3;
import com.yp.sys.entity.params.Params4;
import com.yp.sys.entity.workorder.WorkOrderInfo;
import com.yp.sys.pojo.CollectInfoBean;
import com.yp.sys.pojo.DataGridBean;
import com.yp.sys.pojo.DataGridJson;
import com.yp.sys.pojo.Json;
import com.yp.sys.service.collect.ICollectInfoService;
import com.yp.sys.service.common.impl.BaseServiceImpl;
import com.yp.sys.util.DateUtil;

/**
 * 工参信息的service实现层
 * 
 * @author huangmingxing
 * 
 */
@Service("collectInfoService")
public class CollectInfoServiceImpl extends BaseServiceImpl<CollectInfo>
		implements ICollectInfoService {
	@Autowired
	private ICollectDao collectInfoDao;//采集信息
	@Autowired
	private IBaseDao<Object[]> baseDao;
	@Autowired
	private IParamsDao params2Dao;
	@Autowired
	private IParams4Dao params4Dao;
	@Autowired
	private IParams3Dao params3Dao;
	
	@Autowired
	private IWorkOrderDao workOrderDao;//订单Dao
	@Autowired
	private IUserDao userDao;//用户Dao
	@Autowired
	private IImageDAO imageDao;//图片Dao
	@Override
	public DataGridJson collectInfoDataGrid(DataGridBean dg,
			CollectInfoBean collectInfoBean) {

		String hql = " from CollectInfo t ";
		DataGridJson j = new DataGridJson();
		List<Object> values = new ArrayList<Object>();
		
		if (collectInfoBean != null) {
			
			if(!StringUtils.isEmpty(collectInfoBean.getReportTimeStart())){
				hql+=" and t.reportTime >= ? ";
				values.add(DateUtil.format(collectInfoBean.getReportTimeStart(),DateUtil.Y_M_D_HMS));
			}
			
			if(!StringUtils.isEmpty(collectInfoBean.getReportTimeEnd())){
				hql+=" and t.reportTime <= ? ";
				values.add(DateUtil.format(collectInfoBean.getReportTimeEnd(),DateUtil.Y_M_D_HMS));
			}
			
			if(!StringUtils.isEmpty(collectInfoBean.getCommunityName())){
				hql+=" and t.communityName = ? ";
				values.add(collectInfoBean.getCommunityName());
			}
			if(!StringUtils.isEmpty(collectInfoBean.getAntennaManufacturer())){
				hql+=" and t.antennaManufacturer = ? ";
				values.add(collectInfoBean.getAntennaManufacturer());
			}
		}
		//数据权限
		if(GlobalContext.getCurrentUser() != null && StringUtils.isNotBlank(GlobalContext.getCurrentUser().getOrgIds())){
			hql+=" and t.workOrder.org.id in ("+GlobalContext.getCurrentUser().getOrgIds()+") ";
		}
		
		hql = hql.replaceFirst("and", " where ");
		String totalHql = " select count(*) " + hql;

		j.setTotal(collectInfoDao.count(totalHql, values));// 设置总记录数
		if (dg.getSort() != null) {// 设置排序
			hql += " order by " + dg.getSort() + " " + dg.getOrder();
		}

		List<CollectInfo> collectInfoList = collectInfoDao.find(hql,
				dg.getPage(), dg.getRows(), values);// 查询分页
		
		//数据组装
		if(collectInfoList!=null && collectInfoList.size()>0){
			for(int i=0;i<collectInfoList.size();i++){
				if(collectInfoList.get(i) != null && collectInfoList.get(i).getWorkParameterId() != null 
						&& StringUtils.isNotBlank(collectInfoList.get(i).getNetworkStandard())){
					if(collectInfoList.get(i).getNetworkStandard().equals("2g")){
						Params params2=params2Dao.get(Params.class, collectInfoList.get(i).getWorkParameterId());
						if(params2 != null ){
							if(StringUtils.isNotBlank(params2.getStationName())){
								collectInfoList.get(i).setStationName(params2.getStationName());
							}
							if(StringUtils.isNotBlank(params2.getCommunityName())){
								collectInfoList.get(i).setCommunityName(params2.getCommunityName());
							}
							if(StringUtils.isNotBlank(params2.getCommunityScrambler())){
								collectInfoList.get(i).setCommunityScrambler(params2.getCommunityScrambler());
							}
						}
					}else if(collectInfoList.get(i).getNetworkStandard().equals("3g")){
						Params3 params3=params3Dao.get(Params3.class, collectInfoList.get(i).getWorkParameterId());
						if(params3 != null ){
							if(StringUtils.isNotBlank(params3.getStationName())){
								collectInfoList.get(i).setStationName(params3.getStationName());
							}
							if(StringUtils.isNotBlank(params3.getCommunityName())){
								collectInfoList.get(i).setCommunityName(params3.getCommunityName());
							}
							if(StringUtils.isNotBlank(params3.getCommunityScrambler())){
								collectInfoList.get(i).setCommunityScrambler(params3.getCommunityScrambler());
							}
						}
					}else if(collectInfoList.get(i).getNetworkStandard().equals("4g")){
						Params4 params4=params4Dao.get(Params4.class, collectInfoList.get(i).getWorkParameterId());
						if(params4 != null ){
							if(StringUtils.isNotBlank(params4.getStationName())){
								collectInfoList.get(i).setStationName(params4.getStationName());
							}
							if(StringUtils.isNotBlank(params4.getCommunityName())){
								collectInfoList.get(i).setCommunityName(params4.getCommunityName());
							}
							if(StringUtils.isNotBlank(params4.getCommunityScrambler())){
								collectInfoList.get(i).setCommunityScrambler(params4.getCommunityScrambler());
							}
						}
					}

				}
			}
		}

		j.setRows(collectInfoList);
		
		return j;
	}
	
	@Override
	public List<CollectInfo> queryCollectInfo(DataGridBean dg,CollectInfoBean collectInfoBean) {
		List<CollectInfo> list =new ArrayList<CollectInfo>();
		if(collectInfoBean != null){
			String hql=" from CollectInfo t ";
			//订单状态 wsh：未审核
			if("wsh".equals(collectInfoBean.getExplainState())){ 
				hql+=" and t.workOrder.orderState ='3' ";
			}
			
			//数据权限
			if(GlobalContext.getCurrentUser() != null && !StringUtils.isEmpty(GlobalContext.getCurrentUser().getOrgIds())){
				hql+=" and t.workOrder.org.id in ("+GlobalContext.getCurrentUser().getOrgIds()+") ";
			}
			
			hql=hql.replaceFirst("and", " where ");
			
			list=collectInfoDao.find(hql,dg.getPage(), dg.getRows(),new ArrayList());// 查询分页
		}
		
		return list;
	}
	@Override
	public CollectInfo queryCollectInfoByWorkOrderId(Long workOrderId) {
		String hql=" from CollectInfo t  where  t.workOrder.id="+workOrderId;
		List<CollectInfo> list=collectInfoDao.find(hql);
		if(list!=null && list.size()>0 && list.get(0)!=null){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public String updateColInfoToPara(CollectInfoBean collectInfoBean) {
		String msg="false";
		
		
		
		return msg;
	}

	@Override
	public String updateWorkOrderState(CollectInfoBean collectInfoBean) {
		WorkOrderInfo workOrderInfo=workOrderDao.get(WorkOrderInfo.class, collectInfoBean.getWorkOrderId());
		String msg="false";
		if(workOrderInfo != null){
			User user=userDao.get(User.class, GlobalContext.getCurrentUser().getId());
			workOrderInfo.setExplainRemark(collectInfoBean.getExplainRemark());
			workOrderInfo.setExplainState("1");
			workOrderInfo.setExplainTime(new Timestamp(System.currentTimeMillis()));
			workOrderInfo.setUser(user);
			workOrderDao.update(workOrderInfo);
			msg="true";
		}
		return msg;
	}
	
	@Override
	public Json queryAuditCount() {
		Json j=new Json();
		String hql=" from CollectInfo t ";
		hql+=" and t.workOrder.orderState ='3' ";
		
		//数据权限
		if(GlobalContext.getCurrentUser() != null && !StringUtils.isEmpty(GlobalContext.getCurrentUser().getOrgIds())){
			hql+=" and t.workOrder.org.id in ("+GlobalContext.getCurrentUser().getOrgIds()+") ";
		}
		
		hql = hql.replaceFirst("and", " where ");
		
		String totalHql = " select count(*) " + hql;
	
		j.setObj(collectInfoDao.count(totalHql));
		j.setSuccess(true);
		return j;
	}

	@Override
	public List<Image> findImageByType(int picType,Long workOrderId) {
		StringBuffer hql=new StringBuffer();
		hql.append(" from Image t ");
		hql.append(" where t.picType=? and t.workOrderId=?");
		List<Object> params=new ArrayList<Object>();
		params.add(picType);
		params.add(workOrderId);
		List<Image> images=this.imageDao.find(hql.toString(), params);
		return images;
	}

	
	@Override
	public List<Image> findImagesByWorkOrderId(Long workOrderId) {
		StringBuffer hql=new StringBuffer();
		hql.append(" from Image t ");
		hql.append(" where t.workOrderId=? ");
		List<Object> params=new ArrayList<Object>();
		params.add(workOrderId);
		List<Image> images=this.imageDao.find(hql.toString(), params);
		return images;
	}
	
	@Override
	public List<Integer> findImagesTypeByWorkOrderId(Long workOrderId) {
		List<Integer> listEnd=new ArrayList<Integer>();
		String sql="select t.pic_type from t_cyhc_image_info t where t.workOrderId='"+workOrderId+"' group by t.pic_type";
		List<Object[]> list=baseDao.findBySql(sql);
		if(list!=null){
			for(Object obj:list){
				if(obj!=null ){
					listEnd.add(Integer.valueOf(obj.toString()));
				}
			}
		}
		return listEnd;
	}

	
	@Override
	public List<Image> findImagesByWorkParamIdT(String workParamId) {
		// TODO Auto-generated method stub 后期改造，此方法将被删除
		StringBuffer hql=new StringBuffer();
		hql.append(" from Image t ");
		hql.append(" where t.url like '"+workParamId+"%' ");
		List<Image> images=this.imageDao.find(hql.toString());
		return images;
	}
	
	@Override
	public List<Integer> findImagesTypeByWorkParaIdT(String workParamId) {
		// TODO Auto-generated method stub 后期改造，此方法将被删除
		List<Integer> listEnd=new ArrayList<Integer>();
		String sql="select t.pic_type from t_cyhc_image_info t where  t.url like '"+workParamId+"%' group by t.pic_type";
		List<Object[]> list=baseDao.findBySql(sql);
		if(list!=null){
			for(Object obj:list){
				if(obj!=null ){
					listEnd.add(Integer.valueOf(obj.toString()));
				}
			}
		}
		return listEnd;
	}

}
