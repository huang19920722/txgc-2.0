package com.yp.sys.service.user;


import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.yp.sys.entity.Log;
import com.yp.sys.pojo.DataGridBean;
import com.yp.sys.pojo.DataGridJson;
import com.yp.sys.pojo.PublicPojo;
import com.yp.sys.pojo.TimeBean;
import com.yp.sys.service.common.IBaseService;

/**
 * 日志Service
 * 
 * @author  
 * 
 */
public interface ILogService extends IBaseService<Log> {

	public boolean saveLog(Log log);
	
	public boolean delLog(String logIds);
	/**
	 * 
	 * Description: <br>数据列表
	 * @param dg
	 * @param log
	 * @return
	 */
	public DataGridJson datagrid(DataGridBean dg, Log log);

	public List<Log> getALL(String start, String end);

	public List<TimeBean> getTimeNo(String start, String end);

	public List<TimeBean> getPeople(String start,String end);

	public List<TimeBean> getCourse(String start, String end);

	public HSSFWorkbook courseExcel();

	public List<TimeBean> getLstw(String start, String end);

	public List<TimeBean> getXstw(String start, String end);

	public HSSFWorkbook workExcel(Long l);

	public List<Date> getZCQS(String areaId,String end,String start);

	public Object getPeople(String areaId,String end,String start);

	public Object getXL(String areaId,String end,String start);

	public Object getSF(String areaId,String end,String start);

	public List<Date> getDLQS(String areaId,String end,String start);

	public Object getDlqsPeople(String areaId,String end,String start);

	public List<Date> getXkqstime(String areaId, String courseId);


	public Object getXl(String areaId, String courseId);

	public Object getSf(String areaId, String courseId);

	public Object getKsqk(String areaId, String courseId);

	public List<Date> getWckstime(String areaId, String courseId);

	public Object getWcksPeople(String areaId, String courseId);

	public String toLearningEffectCharts(String areaId, Long courseId);

	public String getCountLeaning(String start, String end);

	public List<Date> getLogTime(String start, String end);

	public Object getLogPeople(String start, String end);

	public List<Date> getLogDate(String start, String end);

	public Object getTimeDate(String start, String end, Long no1, Long no2);

	public Object getTotal(String areaId,String end,String start);

	public List<PublicPojo> getZcrs();

	public Object getDW(String areaId, String courseId);

	public String getAllLeaning(String start, String end);

	public List<TimeBean> getCountDW(String start, String end);

	public String LeaningCount(String areaId, String courseId);
	

	public List<TimeBean> beanList(String courseId);


	public List<TimeBean> Pf(String courseId);


	public HSSFWorkbook courseExcelAll(Long id);
}
