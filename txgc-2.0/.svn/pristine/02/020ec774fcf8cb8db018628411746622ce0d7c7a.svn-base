package com.yp.sys.controller.params;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yp.sys.common.BaseController;
import com.yp.sys.pojo.DataGridBean;
import com.yp.sys.pojo.DataGridJson;
import com.yp.sys.pojo.Json;
import com.yp.sys.pojo.ParamsBean;
import com.yp.sys.pojo.ParamsBean3;
import com.yp.sys.pojo.ParamsBean4;
import com.yp.sys.pojo.ThresoldInfoBean;
import com.yp.sys.service.params.IParamsService;
import com.yp.sys.util.DateUtil;
import com.yp.sys.util.StringUtil;

/**
 * 
 * 版权所有：2016-重庆重邮汇测通讯技术有限公司
 * 项目名称：txgc   
 *
 * 类描述：工参管理控制层
 * 类名称：com.yp.sys.controller.params.ParamsController     
 * 创建人：zhongyang
 * 创建时间：2016-11-16 下午3:11:18   
 * 修改人：
 * 修改时间：2016-11-16 下午3:11:18   
 * 修改备注：   
 * @version   V1.0
 */
@Controller
@RequestMapping("/params")
public class ParamsController extends BaseController {

	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(ParamsController.class);

	private IParamsService paramsService;
	@Autowired
	public void setParamsService(IParamsService paramsService) {
		this.paramsService = paramsService;
	}
	/**
	 * 跳转到2G工参页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/params2g")
	public String params2g(Model model) {
		model.addAttribute("wxcssb",paramsService.executeUtil());
		return "sys/params/params2g";
	}
	
	/**
	 * 跳转到3G工参页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/params3g")
	public String params3g(Model model) {
		model.addAttribute("wxcssb",paramsService.executeUtil());
		return "sys/params/params3g";
	}
	
	/**
	 * 跳转到4G工参页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/params4g")
	public String params4g(Model model) {
		model.addAttribute("wxcssb",paramsService.executeUtil());
		return "sys/params/params4g";
	}
	
	/**
	 * 跳转到阀值管理
	 * 
	 * @return
	 */
	@RequestMapping(value = "/threshold")
	public String threshold(Model model) {
		//model.addAttribute("wxcssb",paramsService.executeUtil());
		return "sys/params/threshold";
	}
	
	/**
	 * 
	 * @Title: datagrid2g
	 * @Description: 2g工参datagrid
	 * @param dg
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/datagrid2g")
	@ResponseBody
	public DataGridJson datagrid2g(DataGridBean dg, ParamsBean params) {
		/*
		if(StringUtil.isBlank(params.getStartTime())){
			params.setStartTime("1900-01-01 00:00:00");
		}
		if(StringUtil.isBlank(params.getEndTime())){
			Date date = new Date();
			String endTime = DateUtil.format(date, "yyyy-MM-dd HH:mm:ss");
			params.setEndTime(endTime);
		}
		*/
		DataGridJson dgj = paramsService.datagrid2g(dg, params);
		return dgj;
	}
	
	/**
	 * 
	 * @Title: datagrid3g
	 * @Description: 3g工参datagrid
	 * @param dg
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/datagrid3g")
	@ResponseBody
	public DataGridJson datagrid3g(DataGridBean dg, ParamsBean3 params) {
		/*if(StringUtil.isBlank(params.getStartTime())){
			params.setStartTime("1900-01-01 00:00:00");
		}
		if(StringUtil.isBlank(params.getEndTime())){
			Date date = new Date();
			String endTime = DateUtil.format(date, "yyyy-MM-dd HH:mm:ss");
			params.setEndTime(endTime);
		}*/
		DataGridJson dgj = paramsService.datagrid3g(dg, params);
		return dgj;
	}
	
	/**
	 * 
	 * @Title: datagrid42g
	 * @Description: 4g工参datagrid
	 * @param dg
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/datagrid4g")
	@ResponseBody
	public DataGridJson datagrid4g(DataGridBean dg, ParamsBean4 params) {
		/*if(StringUtil.isBlank(params.getStartTime())){
			params.setStartTime("1900-01-01 00:00:00");
		}
		if(StringUtil.isBlank(params.getEndTime())){
			Date date = new Date();
			String endTime = DateUtil.format(date, "yyyy-MM-dd HH:mm:ss");
			params.setEndTime(endTime);
		}*/
		DataGridJson dgj = paramsService.datagrid4g(dg, params);
		return dgj;
	}
	
	/**
	 * 添加阀值
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/vilageAdd",method={RequestMethod.POST})
	@ResponseBody
	public Json vilageAdd(@RequestBody ThresoldInfoBean info) {
		Json j = new Json();
		info=paramsService.addVilage(info);	
		j.setSuccess(true);	 
		return j; 
	}
	
	/**
	 * 添加阀值
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/vilageAdd3",method={RequestMethod.POST})
	@ResponseBody
	public Json vilageAdd3(@RequestBody ThresoldInfoBean info) {
		Json j = new Json();
		info=paramsService.addVilage3(info);	
		j.setSuccess(true);	 
		return j; 
	}
	
	/**
	 * 添加阀值
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/vilageAdd4",method={RequestMethod.POST})
	@ResponseBody
	public Json vilageAdd4(@RequestBody ThresoldInfoBean info) {
		Json j = new Json();
		info=paramsService.addVilage4(info);	
		j.setSuccess(true);	 
		return j; 
	}
	
	/**
	 * 
	 * @Title: datagridThr
	 * @Description: 阀值管理datagrid
	 * @param dg
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/datagridThr")
	@ResponseBody
	public DataGridJson datagridThr(DataGridBean dg, ThresoldInfoBean infoBen) {
		DataGridJson dgj = paramsService.datagridThr(dg, infoBen);
		return dgj;
	}
}
