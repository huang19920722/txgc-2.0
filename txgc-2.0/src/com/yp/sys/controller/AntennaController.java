package com.yp.sys.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yp.sys.common.BaseController;
import com.yp.sys.pojo.AntennaBean;
import com.yp.sys.pojo.DataGridBean;
import com.yp.sys.pojo.DataGridJson;
import com.yp.sys.pojo.Json;
import com.yp.sys.service.antenna.IAntennaService;
import com.yp.sys.service.params.IParamsService;
@Controller
@RequestMapping("/antenna")
public class AntennaController extends BaseController {

	@Autowired
	private IAntennaService antennaService;
	@Autowired
	private IParamsService paramsService;
	@RequestMapping("/antennaOperate")
	public String antennaOperate(String choiceForOperate,
								String lacforOperate
							,Model model)  {
		model.addAttribute("choiceForOperate",choiceForOperate);
		model.addAttribute("lac",lacforOperate);
		return "sys/antenna/antennaList";

	}
	@RequestMapping("/antennaList")
	public String toList(){
		return "sys/antenna/antennaList";
	}
	/**
	 * 列表展示无线设备厂家信息
	 * 
	 * @return
	 */
	@RequestMapping("/wirelessEquipmentManufacturer")
	@ResponseBody
	public List<Map<String,String>> params2g() {
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
        Map<String,String> m =paramsService.executeUtil();
        Iterator<Entry<String, String>> it=m.entrySet().iterator();  
        String key=new String();
        String value=new String();
        while(it.hasNext()){   
        Map<String,String> map=new HashMap<String,String>();
            Map.Entry entry = (Map.Entry)it.next();          
            key=entry.getKey().toString();          
            value=entry.getValue().toString();                   
             map.put("text",value);  
             map.put("id",key);
          list.add(map);   
        }
		return list;
	}
	/**
	 * 分页查询2g
	 * @param dg
	 * @param antennaBean
	 * @return
	 */
	@RequestMapping("/datagrid2g")
	@ResponseBody
	public DataGridJson datagrid2g(DataGridBean dg,AntennaBean antennaBean){
		return antennaService.datagrid2g(dg, antennaBean);
	}
	/**
	 * 分页查询3g
	 * @param dg
	 * @param antennaBean
	 * @return
	 */
	@RequestMapping("/datagrid3g")
	@ResponseBody
	public DataGridJson datagrid3g(DataGridBean dg,AntennaBean antennaBean){
		return antennaService.datagrid3g(dg, antennaBean);
	}
	/**
	 * 分页查询4g
	 * @param dg
	 * @param antennaBean
	 * @return
	 */
	@RequestMapping("/datagrid4g")
	@ResponseBody
	public DataGridJson datagrid4g(DataGridBean dg,AntennaBean antennaBean){
		return antennaService.datagrid4g(dg, antennaBean);
	}
	
	/**
	 * <p>通过id删除 2g天线信息所选项</p>
	 * @param ids
	 * @return Json
	 */
	@RequestMapping(value="/delete2gById")
	@ResponseBody
	public Json delete2gById( @RequestParam("ids[]")String ids[]){
		Json json=new Json();
		json.setSuccess(antennaService.deleteAntenna2gById(ids));
		return json;
	}
	/**
	 * <p>通过id删除 3g天线信息所选项</p>
	 * @param ids
	 * @return Json
	 */
	@RequestMapping(value="/delete3gById")
	@ResponseBody
	public Json delete3gById( @RequestParam("ids[]") String ids[]){
		Json json=new Json();
		json.setSuccess(antennaService.deleteAntenna3gById(ids));
		return json;
	}
	/**
	 * <p>通过id删除4g天线信息所选项</p>
	 * @param ids
	 * @return Json
	 */
	@RequestMapping(value="/delete4gById")
	@ResponseBody
	public Json delete4gById( @RequestParam("ids[]") String ids[]){
		Json json=new Json();
		json.setSuccess(antennaService.deleteAntenna4gById(ids));
		return json;
	}
	
	/**
	 * <p>修改2g天线信息</p>
	 * @param antennaBean
	 * @return Json
	 */
	@RequestMapping(value="/update2gAntenna")
	@ResponseBody
	public Json updateAntenna2g(AntennaBean antennaBean){
		Json json=new Json();
		json.setSuccess(antennaService.addOrUpdate2g(antennaBean));
		return json;
	} 
	/**
	 * <p>新增2g天线信息</p>
	 * @param antennaBean
	 * @return Json
	 */
	@RequestMapping(value="/add2gAntenna")
	@ResponseBody
	public Json addAntenna2g(AntennaBean antennaBean){
		Json json=new Json();
		json.setSuccess(antennaService.addOrUpdate2g(antennaBean));
		return json;
	}
	/**
	 * <p>修改3g天线信息</p>
	 * @param antennaBean
	 * @return Json
	 */
	@RequestMapping(value="/update3gAntenna")
	@ResponseBody
	public Json updateAntenna3g(AntennaBean antennaBean){
		Json json=new Json();
		json.setSuccess(antennaService.addOrUpdate3g(antennaBean));
		return json;
	} 
	/**
	 * <p>新增3g天线信息</p>
	 * @param antennaBean
	 * @return Json
	 */
	@RequestMapping(value="/add3gAntenna")
	@ResponseBody
	public Json addAntenna3g(AntennaBean antennaBean){
		Json json=new Json();
		json.setSuccess(antennaService.addOrUpdate3g(antennaBean));
		return json;
	}
	/**
	 * <p>修4g天线信息</p>
	 * @param antennaBean
	 * @return Json
	 */
	@RequestMapping(value="/update4gAntenna")
	@ResponseBody
	public Json updateAntenna4g(AntennaBean antennaBean){
		Json json=new Json();
		json.setSuccess(antennaService.addOrUpdate4g(antennaBean));
		return json;
	} 
	/**
	 * <p>新增4g天线信息</p>
	 * @param antennaBean
	 * @return Json
	 */
	@RequestMapping(value="/add4gAntenna")
	@ResponseBody
	public Json addAntenna4g(AntennaBean antennaBean){
		Json json=new Json();
		json.setSuccess(antennaService.addOrUpdate4g(antennaBean));
		return json;
	}
}
