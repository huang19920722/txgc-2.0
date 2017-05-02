package com.yp.sys.controller.collect;


import java.util.HashMap;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.yp.sys.common.BaseController;
import com.yp.sys.entity.Image;
import com.yp.sys.entity.collect.CollectInfo;
import com.yp.sys.pojo.CollectInfoBean;
import com.yp.sys.pojo.DataGridBean;
import com.yp.sys.pojo.DataGridJson;
import com.yp.sys.pojo.Json;
import com.yp.sys.pojo.ParamsBean;
import com.yp.sys.service.collect.ICollectInfoService;
import com.yp.sys.service.params.IParamsService;
/**
 * 文件名称：工参采集信息controller 内容摘要： 统一处理controller中的异常，绑定springMvc类型转换器 创建人：
 * huangmingxing 创建日期： 2016年11月20日 版本号： v1.0.0 公 司：重庆重邮汇测 版权所有： (C)2001-2016
 * 修改记录： 修改日期： 版本号： 修改人： 修改内容：
 **/

@Controller
@RequestMapping(value = "/collectInfo")
public class CollectInfoController extends BaseController {

	@Autowired
	private ICollectInfoService collectInfoService;
	@Autowired
	private IParamsService paramsService;

	/**
	 * 跳转到工参采集信息界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/toCollectInfo")
	public String toCollectInfo(Model model) {
		model.addAttribute("wxcssb", paramsService.executeUtil());
		return "sys/collect/collectInfo";
	}

	/**
	 * 查询待审核的数据总数
	 */
	@RequestMapping(value = "queryAuditCount")
	@ResponseBody
	public Json queryAuditCount() {
		return collectInfoService.queryAuditCount();
	}

	/**
	 * 跳转到人工审核界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "toPersonAudit")
	public String toPersonAudit(Model model) {
		CollectInfoBean collectInfoBean = new CollectInfoBean();

		DataGridBean dg = new DataGridBean();
		dg.setOrder("reportTime");
		dg.setSort("desc");
		dg.setRows(1);
		dg.setPage(1);
		collectInfoBean.setExplainState("wsh");

		List<CollectInfo> list = collectInfoService.queryCollectInfo(dg,
				collectInfoBean);
		if (list != null && list.size() > 0) {
			model.addAttribute("collecteInfo", list.get(0));
			if (StringUtils.isNotBlank(list.get(0).getNetworkStandard())) {
				if ("2g".equalsIgnoreCase(list.get(0).getNetworkStandard())) {
					if (list.get(0).getWorkOrder() != null
							&& list.get(0).getWorkOrder()
									.getWorkerParameterId() != null) {
						model.addAttribute(
								"standardInfo",
								paramsService.query2gParamById(list.get(0)
										.getWorkOrder().getWorkerParameterId()));
					}
				} else if ("3g".equalsIgnoreCase(list.get(0)
						.getNetworkStandard())) {
					if (list.get(0).getWorkOrder() != null
							&& list.get(0).getWorkOrder()
									.getWorkerParameterId() != null) {
						model.addAttribute(
								"standardInfo",
								paramsService.query3gParamById(list.get(0)
										.getWorkOrder().getWorkerParameterId()));
					}
				} else if ("4g".equalsIgnoreCase(list.get(0)
						.getNetworkStandard())) {
					if (list.get(0).getWorkOrder() != null
							&& list.get(0).getWorkOrder()
									.getWorkerParameterId() != null) {
						model.addAttribute(
								"standardInfo",
								paramsService.query4gParamById(list.get(0)
										.getWorkOrder().getWorkerParameterId()));
					}
				}
			} else {
				model.addAttribute("standardInfo", new ParamsBean());
			}

		} else {
			model.addAttribute("collecteInfo", new CollectInfo());
		}

		return "sys/collect/auditCollectInfo";
	}

	/**
	 * 构建工参采集信息的datagrid
	 * 
	 * @param dg
	 * @param collectInfoBean
	 * @return
	 */
	@RequestMapping(value = "collectInfoDataGrid")
	@ResponseBody
	public DataGridJson collectInfoDataGrid(DataGridBean dg,
			CollectInfoBean collectInfoBean) {
		return collectInfoService.collectInfoDataGrid(dg, collectInfoBean);
	}


	/**
	 * 工单ID查询采集信息
	 * 
	 * @param workOrderId
	 * @return
	 */
	@RequestMapping(value = "queryCollectInfoByWorkOrderId")
	@ResponseBody
	public Json queryCollectInfoByWorkOrderId(Long workOrderId) {

		Json j = new Json();
		Map<String, Object> map = new HashMap<String, Object>();
		// 查询采集信息
		CollectInfo cObj = collectInfoService
				.queryCollectInfoByWorkOrderId(workOrderId);
		map.put("collectInfo", cObj);
		// 查询标准工参信息
		if (StringUtils.isNotBlank(cObj.getNetworkStandard())) {
			if ("2g".equalsIgnoreCase(cObj.getNetworkStandard())) {
				if (cObj.getWorkOrder() != null
						&& cObj.getWorkOrder().getWorkerParameterId() != null) {
					map.put("standardInfo", paramsService.query2gParamById(cObj
							.getWorkOrder().getWorkerParameterId()));
				}
			} else if ("3g".equalsIgnoreCase(cObj.getNetworkStandard())) {
				if (cObj.getWorkOrder() != null
						&& cObj.getWorkOrder().getWorkerParameterId() != null) {
					map.put("standardInfo", paramsService.query3gParamById(cObj
							.getWorkOrder().getWorkerParameterId()));
				}
			} else if ("4g".equalsIgnoreCase(cObj.getNetworkStandard())) {
				if (cObj.getWorkOrder() != null
						&& cObj.getWorkOrder().getWorkerParameterId() != null) {
					map.put("standardInfo", paramsService.query4gParamById(cObj
							.getWorkOrder().getWorkerParameterId()));
				}
			}
		}

		j.setObj(map);
		j.setMsg("查询成功");
		j.setSuccess(true);
		return j;
	}


	/**
	 * 根据picType查询图片
	 * 
	 */
	@RequestMapping(value="/findImageByType")
	@ResponseBody
	public Json other(HttpServletRequest request)throws Exception{
		boolean flag=true;
		Json json=new Json();
		int picType=Integer.parseInt(request.getParameter("type"));
		Long workOrderId=Long.valueOf(request.getParameter("workOrderId"));
		//Long workParamId=Long.valueOf(request.getParameter("workParamId"));
		List<Image> images=new ArrayList<Image>();
		images=this.collectInfoService.findImageByType(picType,workOrderId);
		if(images==null||images.size()==0){
			flag=false;
			json.setSuccess(flag);
		}
		json.setSuccess(flag);
		json.setObj(images);
		return json;
	}
	
	
	/**
	 * 根据工单Id查询图片
	 * @param workOrderId
	 * @return
	 * @throws Exception Json
	 */
	@RequestMapping(value="/queryCollectInfoImages")
	@ResponseBody
	public Json queryCollectInfoImages(Long workOrderId)throws Exception{
		Json json=new Json();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("imageList", this.collectInfoService.findImagesByWorkOrderId(workOrderId));
		map.put("imageTypeList", this.collectInfoService.findImagesTypeByWorkOrderId(workOrderId));
		json.setObj(map);
		json.setSuccess(true);
		return json;
	}
	
	
	/**
	 * 根据工参信息Id查询图片
	 * @param workParamId
	 * @return
	 * @throws Exception Json
	 */
	@RequestMapping(value="/queryCollectInfoImagesT")
	@ResponseBody
	public Json queryCollectInfoImagesT(String workParamId)throws Exception{
		Json json=new Json();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("imageList", this.collectInfoService.findImagesByWorkParamIdT(workParamId));
		map.put("imageTypeList", this.collectInfoService.findImagesTypeByWorkParaIdT(workParamId));
		json.setObj(map);
		json.setSuccess(true);
		return json;
	}
}
