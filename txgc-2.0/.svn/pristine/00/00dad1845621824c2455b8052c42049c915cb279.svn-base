package com.yp.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yp.sys.common.BaseController;
import com.yp.sys.pojo.BaseStationBean;
import com.yp.sys.pojo.DataGridBean;
import com.yp.sys.pojo.DataGridJson;
import com.yp.sys.pojo.Json;
import com.yp.sys.service.baseStation.IBaseStationService;
@Controller
@RequestMapping("/baseStation")
public class BaseStationController extends BaseController {

	@Autowired
	private IBaseStationService baseStationService;
	@RequestMapping("/baseStationList")
	public String toList() {
		return "sys/baseStation/baseStationList";
	}
	/**
	 * 分页查询2g
	 * @param dg
	 * @param baseStation
	 * @return
	 */
	@RequestMapping("/datagrid2g")
	@ResponseBody
	public DataGridJson datagrid2g(DataGridBean dg,BaseStationBean baseStation){
		return baseStationService.datagrid2g(dg, baseStation);
	}
	/**
	 * 分页查询3g
	 * @param dg
	 * @param baseStation
	 * @return
	 */
	@RequestMapping("/datagrid3g")
	@ResponseBody
	public DataGridJson datagrid3g(DataGridBean dg,BaseStationBean baseStation){
		return baseStationService.datagrid3g(dg, baseStation);
	}
	/**
	 * 分页查询4g
	 * @param dg
	 * @param baseStation
	 * @return
	 */
	@RequestMapping("/datagrid4g")
	@ResponseBody
	public DataGridJson datagrid4g(DataGridBean dg,BaseStationBean baseStation){
		return baseStationService.datagrid4g(dg, baseStation);
	}
	
	/**
	 * <p>通过id删除 2g基站信息所选项</p>
	 * @param lacs
	 * @return Json
	 */
	@RequestMapping(value="/delete2gByLac")
	@ResponseBody
	public Json delete2gById( @RequestParam("lacs[]")String lacs[]){
		Json json=new Json();
		json.setSuccess(baseStationService.deleteBaseStation2gByLacs(lacs));
		return json;
	}
	/**
	 * <p>通过id删除 3g基站信息所选项</p>
	 * @param lacs
	 * @return Json
	 */
	@RequestMapping(value="/delete3gByLac")
	@ResponseBody
	public Json delete3gById( @RequestParam("lacs[]") String lacs[]){
		Json json=new Json();
		json.setSuccess(baseStationService.deleteBaseStation3gByLacs(lacs));
		return json;
	}
	/**
	 * <p>通过id删除4g基站信息所选项</p>
	 * @param lacs
	 * @return Json
	 */
	@RequestMapping(value="/delete4gByLac")
	@ResponseBody
	public Json delete4gById( @RequestParam("lacs[]") String lacs[]){
		Json json=new Json();
		json.setSuccess(baseStationService.deleteBaseStation4gByLacs(lacs));
		return json;
	}
	
	/**
	 * <p>修改2g基站信息</p>
	 * @param BaseStation
	 * @return Json
	 */
	@RequestMapping(value="/update2gBaseStation")
	@ResponseBody
	public Json updateBaseStation2g(BaseStationBean baseStation){
		Json json=new Json();
		json.setSuccess(baseStationService.addOrUpdate2g(baseStation));
		return json;
	} 
	/**
	 * <p>新增2g基站信息</p>
	 * @param BaseStation
	 * @return Json
	 */
	@RequestMapping(value="/add2gBaseStation")
	@ResponseBody
	public Json addBaseStation2g(BaseStationBean baseStation){
		Json json=new Json();
		json.setSuccess(baseStationService.addOrUpdate2g(baseStation));
		return json;
	}
	/**
	 * <p>修改3g基站信息</p>
	 * @param BaseStation
	 * @return Json
	 */
	@RequestMapping(value="/update3gBaseStation")
	@ResponseBody
	public Json updateBaseStation3g(BaseStationBean baseStation){
		Json json=new Json();
		json.setSuccess(baseStationService.addOrUpdate3g(baseStation));
		return json;
	} 
	/**
	 * <p>新增3g基站信息</p>
	 * @param baseStation
	 * @return Json
	 */
	@RequestMapping(value="/add3gBaseStation")
	@ResponseBody
	public Json addBaseStation3g(BaseStationBean baseStation){
		Json json=new Json();
		json.setSuccess(baseStationService.addOrUpdate3g(baseStation));
		return json;
	}
	/**
	 * <p>修4g基站信息</p>
	 * @param baseStation
	 * @return Json
	 */
	@RequestMapping(value="/update4gBaseStation")
	@ResponseBody
	public Json updateBaseStation4g(BaseStationBean baseStation){
		Json json=new Json();
		json.setSuccess(baseStationService.addOrUpdate4g(baseStation));
		return json;
	} 
	/**
	 * <p>新增4g基站信息</p>
	 * @param baseStation
	 * @return Json
	 */
	@RequestMapping(value="/add4gBaseStation")
	@ResponseBody
	public Json addBaseStation4g(BaseStationBean baseStation){
		Json json=new Json();
		json.setSuccess(baseStationService.addOrUpdate4g(baseStation));
		return json;
	}
	/**
	 * <p>2g根据基站名称查询查询基站所有的天线</p>
	 * @param lac
	 * @return
	 */
	@RequestMapping(value="/see2gByLac")
	@ResponseBody
	public Json see2gByName( @RequestParam("lac") String lac){
		Json json=new Json();
		json.setSuccess(true);
		json.setObj(baseStationService.find2gAntennaByLac(lac));
		return json;
		
	}
	/**
	 * <p>3g根据基站名称查询查询基站所有的天线</p>
	 * @param lac
	 * @return
	 */
	@RequestMapping(value="/see3gByLac")
	@ResponseBody
	public Json see3gByName( @RequestParam("lac") String lac){
		Json json=new Json();
		json.setSuccess(true);
		json.setObj(baseStationService.find3gAntennaByLac(lac));
		return json;
		
	}
	/**
	 * <p>4g根据基站名称查询查询基站所有的天线</p>
	 * @param lac
	 * @return
	 */
	@RequestMapping(value="/see4gByLac")
	@ResponseBody
	public Json see4gByName( @RequestParam("lac") String lac){
		Json json=new Json();
		json.setSuccess(true);
		json.setObj(baseStationService.find4gAntennaByLac(lac));
		return json;
		
	}
}

