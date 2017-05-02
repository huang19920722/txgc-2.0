package com.yp.sys.controller.rtu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.HttpRequestHandlerServlet;
import org.springframework.web.servlet.ModelAndView;

import com.yp.sys.common.BaseController;
import com.yp.sys.entity.rtu.RtuBaseInfo;
import com.yp.sys.entity.rtu.RtuConfigInfo;
import com.yp.sys.entity.rtu.RtuSlaveInfo;
import com.yp.sys.entity.rtu.SlaveInfo;
import com.yp.sys.pojo.DataGridBean;
import com.yp.sys.pojo.DataGridJson;
import com.yp.sys.pojo.Json;
import com.yp.sys.pojo.RtuBaseInfoBean;
import com.yp.sys.pojo.RtuCollectInfoBean;
import com.yp.sys.pojo.RtuConfigInfoBean;
import com.yp.sys.pojo.RtuInfoBean;
import com.yp.sys.pojo.RtuPropertyBean;
import com.yp.sys.pojo.RtuSlaveInfoBean;
import com.yp.sys.pojo.RtuTranslateProertyBean;
import com.yp.sys.pojo.SlaveInfoBean;
import com.yp.sys.service.rtu.IRtuService;
	
@Controller
@RequestMapping("/rtuController")
public class RtuController extends BaseController {
	@Autowired
	private IRtuService rtuService;
	
	@RequestMapping(value="toRtu")
	public String toRtu(){
		return "sys/rtu/rtuInfo";
	}
	/**
	 * 测试代码
	 * @param dg
	 * @param rtuInfoBean
	 * @return DataGridJson
	 */
	@RequestMapping(value = "rtuInfoDataGrid")
	@ResponseBody
	public DataGridJson rtuInfoDataGrid(DataGridBean dg,
			RtuInfoBean rtuInfoBean) {
		return rtuService.rtuInfoDataGrid(dg, rtuInfoBean);
	}
	
	/**
	 * 跳转到RTU基础信息界面
	 * @return String
	 */
	@RequestMapping(value="toBaseRtu")
	public String toBaseRtu(){
		return "sys/rtu/rtuBaseInfo";
	}
	/**
	 * 跳转到RTU码表配置信息界面
	 * @return String
	 */
	@RequestMapping(value="addRtuProperties")
	public String addRtuProperties(){
		return "sys/rtu/addRtuProperties";
	}
	/**
	 * 跳转到采集信息界面
	 * @return String
	 */
	@RequestMapping(value="toRtuCollectInfo")
	@ResponseBody
	public ModelAndView toRtuCollectInfo(HttpServletRequest request){
		ModelAndView model=new ModelAndView();
		Map<String,Object> map=new HashMap<String,Object>();
		String configId=request.getParameter("configId");
		if(configId!=null && StringUtils.isNotBlank(configId)){
			map.put("urlConfigId", Long.valueOf(configId));
		}
		model.addAllObjects(map);
		model.setViewName("sys/rtu/rtuCollectInfo");
		return model;
	}
	/**
	 * 查询rtu信息
	 * @param dg
	 * @param rtuInfoBean
	 * @return DataGridJson
	 */
	@RequestMapping(value = "rtuBaseDataGrid")
	@ResponseBody
	public DataGridJson rtuBaseDataGrid(DataGridBean dg,
			RtuBaseInfoBean rtuInfoBean) {
		return rtuService.rtuBaseDataGrid(dg, rtuInfoBean);
	}
	
	/**
	 * 查询采集面数据记录项
	 */
	@RequestMapping(value = "rtuCollectInfoDataGrid")
	@ResponseBody
	public DataGridJson rtuCollectInfoDataGrid(DataGridBean dg,
			RtuCollectInfoBean rtuInfoBean) {
		return rtuService.rtuCollectInfoDataGrid(dg, rtuInfoBean);
	}
	

	/**
	 * 查询配置信息
	 * @param dg
	 * @param rtuInfoBean
	 * @return DataGridJson
	 */
	@RequestMapping(value = "rtuConfigDataGrid")
	@ResponseBody
	public DataGridJson rtuConfigDataGrid(DataGridBean dg,
			RtuConfigInfoBean rtuInfoBean) {
		return rtuService.rtuConfigDataGrid(dg, rtuInfoBean);
	}
	
	/**
	 * 查询采集信息的详细信息
	 * @param collectId
	 * @return Json
	 */
	@RequestMapping(value = "queryCollectInfoByCollectId")
	@ResponseBody
	public Json queryCollectInfoByCollectId(Long collectId) {
		Json j=new Json();
		j.setObj(rtuService.changeJsonToList(collectId));
		j.setSuccess(true);
		return j;
	}
	@RequestMapping(value = "deleteSlaveInfo")
	@ResponseBody
	public Json deleteSlaveInfo(Long slaveId) {
		Json j=new Json();
		rtuService.deleteSlaveInfo(slaveId);
		j.setSuccess(true);
		j.setObj(slaveId);
		return j;
	}
	
	/**
	 * 根据pid组装RTU模型Ztree数据
	 * @param pid
	 * @return Json
	 */
	@RequestMapping(value = "buildRtuModelZtree")
	@ResponseBody
	public Json buildRtuModelZtree(Long pid) {
		Json j=new Json();
		//JSONArray json = JSONArray.fromObject(rtuService.createRtuModelZtree(pid));
		j.setObj(rtuService.buildRtuModelZtree(pid));
		j.setSuccess(true);
		return j;
	}
	@RequestMapping(value = "createRtuModelZtree")
	@ResponseBody
	public Json createRtuModelZtree(Long pid) {
		Json j=new Json();
		//JSONArray json = JSONArray.fromObject(rtuService.createRtuModelZtree(pid));
		j.setObj(rtuService.createRtuModelZtree(pid));
		j.setSuccess(true);
		return j;
	}
	/**
	 * 根据pid组装RTU模型Ztree数据并填充已保存的数据
	 * @param pid
	 * @return Json
	 */
	@RequestMapping(value = "editRtuModelZtree")
	@ResponseBody
	public Json editRtuModelZtree(Long pid,String slaveId) {
		Json j=new Json();
		//JSONArray json = JSONArray.fromObject(rtuService.createRtuModelZtree(pid));
		j.setObj(rtuService.editRtuModelZtree(pid,slaveId));
		j.setSuccess(true);
		return j;
	}
	/**
	 * 查询寄存器属性
	 * @param pid
	 * @return Json
	 */
	@RequestMapping(value = "querySlaveBaseProperty")
	@ResponseBody
	public Json querySlaveBaseProperty(Long pid) {
		Json j=new Json();
		j.setObj(rtuService.querySlaveBaseProperty(pid));
		return j;
	}

	/**
	 * 查询寄存器属性并赋值
	 * @param pid
	 * @return Json
	 */
	@RequestMapping(value = "editSlaveBaseProperty")
	@ResponseBody
	public Json editSlaveBaseProperty(Long pid,Long slaveId) {
		Json j=new Json();
		j.setObj(rtuService.editSlaveBaseProperty(pid,slaveId));
		return j;
	}
	/**
	 * 查询寄存器属性
	 * @param pid
	 * @return Json
	 */
	@RequestMapping(value = "querySlaveBasePropertyById")
	@ResponseBody
	public Json querySlaveBasePropertyById(Long pid) {
		Json j=new Json();
		j.setObj(rtuService.querySlaveBasePropertyById(pid));
		return j;
	}
	/**
	 * 查询寄存器属性并赋值
	 * @param pid
	 * @return Json
	 */
	@RequestMapping(value = "editSlaveBasePropertyById")
	@ResponseBody
	public Json editSlaveBasePropertyById(Long pid,Long slaveId) {
		Json j=new Json();
		j.setObj(rtuService.editSlaveBasePropertyById(pid,slaveId));
		return j;
	}
	/**
	 * 根据配置信息查询采集器信息并进行复制操作显示到配置信息页面
	 * @param configId
	 * @return
	 */
	@RequestMapping(value = "findSlaveTrans")
	@ResponseBody
	public Json findSlaveInfo(Long configId){
		Json j=new Json();
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			List<SlaveInfoBean> slaveList=rtuService.findSlaveInfo(configId);
			List<RtuTranslateProertyBean> transList=rtuService.findTransInfo(configId);
			map.put("slaveList", slaveList);
			map.put("transList", transList);
			j.setObj(map);
			j.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg("查询失败");
			j.setSuccess(false);
			j.setObj(null);
		}
		return j;
	}
	/**
	 * 根据配置信息查询采集器信息和传输配置信息
	 * @param configId
	 * @return
	 */
	@RequestMapping(value = "lookSlaveTrans")
	@ResponseBody
	public Json lookSlaveTrans(Long configId){
		Json j=new Json();
		Map<String,Object> map= new HashMap<String, Object>();
		try {
			List<SlaveInfoBean> slaveList=rtuService.lookSlaveInfo(configId);
			List<RtuTranslateProertyBean> transList=rtuService.findTransInfo(configId);
			map.put("slaveList", slaveList);
			map.put("transList", transList);
			j.setObj(map);
			j.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg("查询失败");
			j.setSuccess(false);
			j.setObj(null);
		}
		return j;
	}
	/**
	 * 查看寄存器某已属性的关联值
	 * @param propertyId
	 * @return Json
	 */
	@RequestMapping(value = "queryRegPropertyList")
	@ResponseBody
	public Json queryRegPropertyList(Long propertyId) {
		Json j=new Json();
		j.setObj(rtuService.queryRegPropertyList(propertyId));
		return j;
	}
	
	/**
	 * 保存寄存器信息
	 * @param content
	 * @return Json
	 */
	@RequestMapping(value = "ajaxSaveSlave")
	@ResponseBody
	public Json ajaxSaveSlave(String content){
		Json j=new Json();
		j.setObj(rtuService.ajaxSaveSlave(content));
		j.setSuccess(true);
		return j;
		
	}
	/**
	 * 添加配置属性
	 * @param rtuPropertyBean
	 * @return
	 */
	@RequestMapping(value = "addPro")
	@ResponseBody
	public Json addPro(@RequestBody RtuPropertyBean rtuPropertyBean){
		Json j=new Json();
		String msg="";
		try {
			msg=rtuService.addPro(rtuPropertyBean);
			j.setSuccess(true);
			j.setMsg(msg);
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			j.setMsg("99");
		}
		j.setObj(rtuPropertyBean);
		return j;
	}
	/**
	 * 添加配置属性组
	 * @param rtuPropertyBean
	 * @return
	 */
	@RequestMapping(value = "addProGroup")
	@ResponseBody
	public Json addProGroup(@RequestBody RtuPropertyBean rtuPropertyBean){
		Json j=new Json();
		String msg="";
		try {
			msg=rtuService.addProGroup(rtuPropertyBean);
			j.setSuccess(true);
			j.setMsg(msg);
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			j.setMsg("99");
		}
		j.setObj(rtuPropertyBean);
		return j;
	}
	
	/**
	 * 根据配置的configId查询采集器
	 */
	@RequestMapping(value = "slaveInfoDataGrid")
	@ResponseBody
	public Json slaveInfoDataGrid(SlaveInfoBean slaveInfoBean){
		Json j=new Json();
		j.setObj( rtuService.slaveInfoDataGrid(slaveInfoBean));
		return j;
	}
	
	/**
	 * 保存配置信息
	 * @param pubDelay
	 * @param heartDelay
	 * @param translateContent ==>json字符串
	 * @param slaveIds
	 * @param rtuId
	 * @return Json
	 */
	@RequestMapping(value = "addRtuConfig")
	@ResponseBody
	public Json addRtuConfig(String rtuId,String pubDelay,String heartDelay,String translateContent,String slaveIds){
		Json j=new Json();
		RtuConfigInfo rtuConfigInfo=rtuService.addRtuConfig(rtuId, pubDelay, heartDelay, translateContent, slaveIds);
		
		if(StringUtils.isNotBlank(translateContent)){
			rtuService.saveListTranProperty(translateContent,rtuConfigInfo);
		}
		if (StringUtils.isNotBlank(slaveIds)) {
			rtuService.updateListSlaveinfo(slaveIds, rtuConfigInfo);
		}
		rtuService.makeUpJson(rtuConfigInfo.getCid());
		//rtuConfigInfo.set
	
		//rtuService.makeUpJson(1489557520048L);

		//j.setObj(rtuConfigInfo);
		j.setSuccess(true);
		return j;
	}
	
	/**
	 * 更新或者保存slave初始配置信息
	 * @param rtuSlaveInfo
	 * @return String
	 */
	@RequestMapping(value = "saveRtuSlaveBaseInfo")
	@ResponseBody
	public Json saveRtuSlaveBaseInfo(RtuSlaveInfoBean rtuSlaveInfoBean){
		Json j=new Json();
		j.setMsg(rtuService.saveRtuSlaveBaseInfo(rtuSlaveInfoBean));
		return j;
	}

	/**
	 * 查询采集器列表
	 * @param dg
	 * @param rtuSlaveInfo
	 * @return DataGridJson
	 */
	@RequestMapping(value = "rtuSlaveBaseInfoDataGrid")
	@ResponseBody
	public DataGridJson rtuSlaveBaseInfoDataGrid(DataGridBean dg,
			RtuSlaveInfoBean rtuSlaveInfo) {
		return rtuService.rtuSlaveBaseInfoDataGrid(dg, rtuSlaveInfo);
	}
	
	/**
	 * 保存RTU基础信息
	 * @param rtuBase
	 * @return Json
	 */
	@RequestMapping(value="/addOrUpdateRTUBase")
	@ResponseBody
	public Json addOrUpdateRTUBase(RtuBaseInfo rtuBaseInfo) {
		Json json = new Json();
		json.setSuccess(rtuService.addOrUpdateRTUBase(rtuBaseInfo));
		return json;
	}
	
	/**
	 * 删除RTU基础信息
	 * @param id
	 * @return Json
	 */
	@RequestMapping(value="/deleteRTUBase/{id}")
	@ResponseBody
	public Json deleteRTUBase(@PathVariable("id")long id) {
		Json json = new Json();
		json.setSuccess(rtuService.deleteRTUBaseById(id));
		return json;
	}
	@RequestMapping(value="/deleteRtuPro/{id}")
	@ResponseBody
	public Json deleteRtuPro(@PathVariable("id")long id) {
		Json json = new Json();
		String msg="";
		try {
			msg=rtuService.deleteRtuPro(id);
			json.setSuccess(true);
		} catch (Exception e) {
			msg="3";
			json.setSuccess(false);
		}
		json.setObj(id);
		json.setMsg(msg);
		return json;
	}
	
	/**
	 *删除slaveBase信息
	 * @param id
	 * @return Json
	 */
	@RequestMapping(value="/deleteSlaveBase")
	@ResponseBody
	public Json deleteSlaveBase(Long id) {
		Json json = new Json();
		json.setMsg(rtuService.deleteSlaveBase(id));
		return json;
	}
	/**
	 * 去人rtu配置信息
	 * @param rtuId
	 * @return Json
	 */
	@RequestMapping(value="/updateRtuState")
	@ResponseBody
	public Json updateRtuState(Long rtuId) {
		Json json = new Json();
		json.setMsg(rtuService.updateRtuState(rtuId));
		return json;
	}
	/**
	 * 删除未下发的配置信息
	 * @param configId
	 * @return
	 */
	@RequestMapping(value="/deleteConfigFun")
	@ResponseBody
	public Json deleteConfigFun(Long configId) {
		Json json = new Json();
		String msg="";
		try {
			msg=rtuService.deleteConfigFun(configId);
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			msg="2";
			json.setSuccess(false);
		}
		json.setMsg(msg);
		return json;
	}
}
