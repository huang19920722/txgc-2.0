package com.yp.sys.service.user.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yp.sys.dao.common.IBaseDao;
import com.yp.sys.dao.user.ILogDao;
import com.yp.sys.entity.Log;
import com.yp.sys.pojo.DataGridBean;
import com.yp.sys.pojo.DataGridJson;
import com.yp.sys.pojo.PublicPojo;
import com.yp.sys.pojo.TimeBean;
import com.yp.sys.service.common.impl.BaseServiceImpl;
import com.yp.sys.service.user.ILogService;



/**
 * 用户Service
 * 
 * @author  
 * 
 */
@Service("logService")
public class LogServiceImpl extends BaseServiceImpl<Log> implements ILogService {

	private ILogDao logDao;

	@Autowired
	private IBaseDao baseDao;
	
	public ILogDao getLogDao() {
		return logDao;
	}
	@Autowired
	public void setLogDao(ILogDao logDao) {
		this.logDao = logDao;
	}

	/**
	 * 
	 * 保存日志信息
	 * 
	 * 
	 */
	@Override
	public boolean saveLog(Log log) {
		boolean flag = true;
		try {
			logDao.save(log);
		} catch(Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	/**
	 * 
	 * 删除日志信息
	 * 
	 */
	@Override
	public boolean delLog(String logIds) {
		boolean flag = true;
		String[] logIdA = logIds.split(",");
		try {
			StringBuffer delSql = new StringBuffer();
			if(logIdA.length > 0) {
				delSql.append("delete from sys_log where id in (");
				for(int i = 0; i < logIdA.length; i++) {
					if(i != 0) {
						delSql.append(",");
					}
					delSql.append("'"+logIdA[i]+"'");
				}
				delSql.append(")");
			}
			logDao.executeSql(delSql.toString());
		} catch(Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	/**
	 * 
	 */
	@Transactional(readOnly = true)
	public DataGridJson datagrid(DataGridBean dg, Log log) {
		DataGridJson j = new DataGridJson();
		String hql = " from Log t where 1=1 ";
		List<Object> values = new ArrayList<Object>();
		if (log != null) {// 添加查询条件
			if (!StringUtils.isBlank(log.getNote())) {
				hql += " and t.note like '%" + log.getNote() + "%' ";
			}
			if (!StringUtils.isBlank(log.getUserName())) {
				hql += " and t.userName like '%" + log.getUserName() + "%' ";
			}
		}
		String totalHql = " select count(*) " + hql;
		j.setTotal(logDao.count(totalHql, values));// 设置总记录数
		if (dg.getSort() != null) {// 设置排序
			hql += " order by " + dg.getSort() + " " + dg.getOrder();
		}else{
			hql += " order by useTime desc " ;
		}
		List<Log> logs = logDao.find(hql, dg.getPage(), dg.getRows(), values);// 查询分页
		j.setRows(logs);// 设置返回的行
		return j;
	}
	
	
	/*********************************************统计周报***************************************************/
	public List<Log> getALL(String start, String end){
		List<Log> logList=new ArrayList<Log>();
		String sql="SELECT  date_format(t.USE_TIME,'%Y-%m-%d') as a, count( date_format(t.USE_TIME,'%Y%m%d'))as b  from sys_log t WHERE date_format('"+start+"','%Y%m%d')<=date_format(t.USE_TIME,'%Y%m%d') and date_format(t.USE_TIME,'%Y%m%d')<=date_format('"+end+"','%Y%m%d') " +
				"and t.NOTE ='登录系统成功' group by date_format(t.USE_TIME,'%Y%m%d')";
		List<Object[]> list=(List<Object[]>)baseDao.findBySql(sql);
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				Log log=new Log();
				log.setUseTime(list.get(i)[0].toString());
				log.setNote(list.get(i)[1].toString());
				logList.add(log);
			}
		}		
		return logList;
	}
	public List<Date> getLogTime(String start, String end){
		String sql="SELECT  date_format(t.USE_TIME,'%Y-%m-%d') as a  from sys_log t WHERE date_format('"+start+"','%Y%m%d')<=date_format(t.USE_TIME,'%Y%m%d') and date_format(t.USE_TIME,'%Y%m%d')<=date_format('"+end+"','%Y%m%d') " +
				"and t.NOTE ='登录系统成功' group by date_format(t.USE_TIME,'%Y%m%d')";
		List<Date> list=baseDao.findBySql(sql);
		return list;
	}
	public Object getLogPeople(String start, String end){
		String sql="SELECT  count( date_format(t.USE_TIME,'%Y%m%d'))as b  from sys_log t WHERE date_format('"+start+"','%Y%m%d')<=date_format(t.USE_TIME,'%Y%m%d') and date_format(t.USE_TIME,'%Y%m%d')<=date_format('"+end+"','%Y%m%d') " +
		"and t.NOTE ='登录系统成功' group by date_format(t.USE_TIME,'%Y%m%d')";
		List<PublicPojo> list=baseDao.findBySql(sql);
		return list;
	}
	
	public List<TimeBean> getTimeNo(String start, String end){
		List<TimeBean> list=new ArrayList<TimeBean>();
		String sql="select date_format(a.USE_TIME,'%Y-%m-%d')as aa, count(case when 7<DATE_FORMAT(a.USE_TIME,'%H') and DATE_FORMAT(a.USE_TIME,'%H')<=12  then '1' end) as a1 ," +
				"count(case when 12<DATE_FORMAT(a.USE_TIME,'%H') and DATE_FORMAT(a.USE_TIME,'%H')<=14  then '1' end) as a2 ," +
				"count(case when 14<DATE_FORMAT(a.USE_TIME,'%H') and DATE_FORMAT(a.USE_TIME,'%H')<=18  then '1' end) as a3 ," +
				"count(case when 18<DATE_FORMAT(a.USE_TIME,'%H') and DATE_FORMAT(a.USE_TIME,'%H')<=20  then '1' end) as a4 ," +
				"count(case when 20<DATE_FORMAT(a.USE_TIME,'%H') and DATE_FORMAT(a.USE_TIME,'%H')<24  then '1' end) as a5 ," +
				"count(case when 0<=DATE_FORMAT(a.USE_TIME,'%H') and DATE_FORMAT(a.USE_TIME,'%H')<=8  then '1' end) as a6 " +
				"from sys_log a  WHERE date_format('"+start+"','%Y%m%d')<=date_format(a.USE_TIME,'%Y%m%d') and date_format(a.USE_TIME,'%Y%m%d')<=date_format('"+end+"','%Y%m%d') " +
				"and a.NOTE ='登录系统成功' group by date_format(a.USE_TIME,'%Y%m%d')";
		List<Object[]> list1=(List<Object[]>)baseDao.findBySql(sql);
		if(list1!=null&&list1.size()>0){
			for(int i=0;i<list1.size();i++){
				int a=0;int b=0;int c=0;int d=0;int e=0;int f=0;
				TimeBean bean=new TimeBean();
					bean.setTimeName(list1.get(i)[0].toString());
					bean.setNo1(list1.get(i)[6].toString());
					bean.setNo2(list1.get(i)[1].toString());
					bean.setNo3(list1.get(i)[2].toString());
					bean.setNo4(list1.get(i)[3].toString());
					bean.setNo5(list1.get(i)[4].toString());
					bean.setNo6(list1.get(i)[5].toString());
					
				list.add(bean);
			}
		}	
		return list;
	}
	public Object getTimeDate(String start, String end, Long no1, Long no2){
		String sql="select count(case when "+no1+"<DATE_FORMAT(a.USE_TIME,'%H') and DATE_FORMAT(a.USE_TIME,'%H')<="+no2+"  then '1' end) as a1 " +
		"from sys_log a  WHERE date_format('"+start+"','%Y%m%d')<=date_format(a.USE_TIME,'%Y%m%d') and date_format(a.USE_TIME,'%Y%m%d')<=date_format('"+end+"','%Y%m%d') " +
		"and a.NOTE ='登录系统成功' group by date_format(a.USE_TIME,'%Y%m%d')";
		List<PublicPojo> pojo=baseDao.findBySql(sql);
		return pojo;
	}
	public List<Date> getLogDate(String start, String end){
		String sql="SELECT  date_format(t.USE_TIME,'%Y-%m-%d') as a  from sys_log t WHERE date_format('"+start+"','%Y%m%d')<=date_format(t.USE_TIME,'%Y%m%d') and date_format(t.USE_TIME,'%Y%m%d')<=date_format('"+end+"','%Y%m%d') " +
		"and t.NOTE ='登录系统成功' group by date_format(t.USE_TIME,'%Y%m%d')";
		List<Date> list=baseDao.findBySql(sql);
		return list;
	}
	
	public List<TimeBean> getPeople(String start, String end){
		List<TimeBean> listbean=new ArrayList<TimeBean>();
		//查询上周的日期//
		Calendar c1=Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			c1.setTime(df.parse(start));
			c1.add(Calendar.DATE, -7);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String sql="SELECT a.name," +
				"(SELECT COUNT(*) from sys_user b ,student c where b.id=c.id and  date_format('"+start+"','%Y%m%d')<=date_format(b.createTime,'%Y%m%d') and  date_format(b.createTime,'%Y%m%d')<=date_format('"+end+"','%Y%m%d') and  c.sourceArea=c.id)as aa ," +
				"(SELECT COUNT(*) from sys_user b ,student c where b.id=c.id and date_format('"+df.format(c1.getTime())+"','%Y%m%d')<=date_format(b.createTime,'%Y%m%d') and date_format(b.createTime,'%Y%m%d')<date_format('"+start+"','%Y%m%d') and  c.sourceArea=a.id) as bb,"+
				"(SELECT COUNT(*) from sys_user b ,student c where b.id=c.id and   c.sourceArea=a.id) as cc"+
				" from area a ";//先查地区
		List<Object[]> list=(List<Object[]>)baseDao.findBySql(sql);
		for(int i=0;i<list.size();i++){
			TimeBean bean=new TimeBean();
			bean.setTimeName(list.get(i)[0].toString());
			bean.setNo1(list.get(i)[1].toString());//本周
			bean.setNo2(list.get(i)[2].toString());//上周
			bean.setNo3(String.valueOf(Long.valueOf(list.get(i)[1].toString())-Long.valueOf(list.get(i)[2].toString())));
			bean.setNo4(list.get(i)[3].toString());
			listbean.add(bean);
		}
		return listbean;
	}
	
	
	public List<TimeBean> getCourse(String start, String end){
		List<TimeBean> listbean=new ArrayList<TimeBean>();
		
		String sql="SELECT a.name,(SELECT COUNT(*) from courset_schedule b where b.courseId=a.id  and b.praiseStatus='Y' and b.recordStatus='Y') as aa from course a where a.recordStatus='Y'";
		List<Object[]> list=(List<Object[]>)baseDao.findBySql(sql);
		for(int i=0;i<list.size();i++){
			TimeBean bean=new TimeBean();
			bean.setTimeName(list.get(i)[0].toString());
			bean.setNo1(list.get(i)[1].toString());
			listbean.add(bean);
		}
		return listbean;
	}
	
	
	public HSSFWorkbook courseExcel(){
		HSSFWorkbook workbook=new HSSFWorkbook();
		HSSFSheet sheet=workbook.createSheet();
		sheet.setDefaultColumnWidth((short)20);
		HSSFRow titleRow=sheet.createRow(0);
		String[] titleName=new String[]{"课程名称","课程累计赞人数"};
		int length=titleName.length;
		HSSFCellStyle style1 = workbook.createCellStyle();
		style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFCellStyle headStyle = workbook.createCellStyle();
		headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont font=workbook.createFont();
        font.setColor(HSSFColor.RED.index);
        font.setFontHeightInPoints((short)10);
        headStyle.setFont(font);
        for(short i=0;i<length;i++){
			HSSFCell cell=titleRow.createCell(i);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
          //  cell.setEncoding((short)1);
			cell.setCellValue(titleName[i]);
			cell.setCellStyle(headStyle);
		}
        String sql="SELECT a.name,(SELECT COUNT(*) from courset_schedule b where b.courseId=a.id  and b.praiseStatus='Y' and b.recordStatus='Y') as aa  from course a where a.recordStatus='Y'";
       
		List<Object[]> list = (List<Object[]>)baseDao.findBySql(sql);
		int size=list.size();
		if(null!=list && size>0){
			for(int i=0;i<size;i++){
				HSSFRow row = sheet.createRow(i+1);//创建一行
				for(short j=0;j<length;j++){
					HSSFCell cell = row.createCell(j);//创建一列
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		           // cell.setEncoding((short)1);
					cell.setCellStyle(style1);
	                cell.setCellValue(list.get(i)[j]+"");
				}
			}
		}
        
        return workbook;
		
	}
	public List<TimeBean> getLstw(String start, String end){
		List<TimeBean> listbean=new ArrayList<TimeBean>();
		//查询上周的日期//
		Calendar c1=Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			c1.setTime(df.parse(start));
			c1.add(Calendar.DATE, -7);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String sql="SELECT zz.name,zz.aa,zz.bb,(zz.aa-zz.cc)as a1,(zz.bb-zz.dd)as a2 from (SELECT a.name," +
				"(select count(*) from course_question c where c.courseId=a.id and c.userId in(SELECT d.id from sys_user d where d.type!='Student') and date_format('"+start+"','%Y%m%d')<=date_format(c.createTime,'%Y%m%d') and  date_format(c.createTime,'%Y%m%d')<=date_format('"+end+"','%Y%m%d') )as aa ," +
				"(select count(*) from course_answer c,course_question d where c.questionId=d.id and d.courseId=a.id and c.userId in(SELECT d.id from sys_user d where d.type!='Student') and date_format('"+start+"','%Y%m%d')<=date_format(c.createTime,'%Y%m%d') and  date_format(c.createTime,'%Y%m%d')<=date_format('"+end+"','%Y%m%d')) as bb ," +
				"(select count(*) from course_question c where c.courseId=a.id and c.userId in(SELECT d.id from sys_user d where d.type!='Student') and date_format('"+df.format(c1.getTime())+"','%Y%m%d')<=date_format(c.createTime,'%Y%m%d') and  date_format(c.createTime,'%Y%m%d')<date_format('"+start+"','%Y%m%d') )as cc ," +
				"(select count(*) from course_answer c,course_question d where c.questionId=d.id and d.courseId=a.id and c.userId in(SELECT d.id from sys_user d where d.type!='Student') and date_format('"+df.format(c1.getTime())+"','%Y%m%d')<=date_format(c.createTime,'%Y%m%d') and  date_format(c.createTime,'%Y%m%d')<date_format('"+start+"','%Y%m%d')) as dd " +
				"from course a  where a.recordStatus='Y')zz";
		List<Object[]> list=(List<Object[]>)baseDao.findBySql(sql);
		for(int i=0;i<list.size();i++){
			TimeBean bean=new TimeBean();
			bean.setTimeName(list.get(i)[0].toString());
			bean.setNo1(list.get(i)[1].toString());
			bean.setNo2(list.get(i)[2].toString());
			bean.setNo3(list.get(i)[3].toString());
			bean.setNo4(list.get(i)[4].toString());
			listbean.add(bean);
		}
		return listbean;
	}
	
	public HSSFWorkbook workExcel(Long l){
		HSSFWorkbook workbook=new HSSFWorkbook();
		HSSFSheet sheet=workbook.createSheet();
		sheet.setDefaultColumnWidth((short)20);
		HSSFRow titleRow=sheet.createRow(0);
		String[] titleName;
		 String sql="";
		if(l==1l){
		  titleName=new String[]{"课程名称","老师提问次数","老师回复次数"};
		  sql="SELECT zz.name,zz.aa,zz.bb,(zz.aa-zz.cc)as a1,(zz.bb-zz.dd)as a2 from (SELECT a.name," +
			"(select count(*) from course_question c where c.courseId=a.id and c.userId in(SELECT d.id from sys_user d where d.type!='Student') )as aa ," +
			"(select count(*) from course_answer c,course_question d where c.questionId=d.id and d.courseId=a.id and c.userId in(SELECT d.id from sys_user d where d.type!='Student')) as bb ," +
			"(select count(*) from course_question c where c.courseId=a.id and c.userId in(SELECT d.id from sys_user d where d.type!='Student')  )as cc ," +
			"(select count(*) from course_answer c,course_question d where c.questionId=d.id and d.courseId=a.id and c.userId in(SELECT d.id from sys_user d where d.type!='Student') ) as dd " +
			"from course a  where a.recordStatus='Y')zz";

		}else{
			titleName=new String[]{"课程名称","学生提问次数","学生回复次数"};
			sql="SELECT zz.name,zz.aa,zz.bb,(zz.aa-zz.cc)as a1,(zz.bb-zz.dd)as a2 from (SELECT a.name," +
			"(select count(*) from course_question c where c.courseId=a.id and c.userId in(SELECT d.id from sys_user d where d.type='Student') )as aa ," +
			"(select count(*) from course_answer c,course_question d where c.questionId=d.id and d.courseId=a.id and c.userId in(SELECT d.id from sys_user d where d.type='Student')) as bb ," +
			"(select count(*) from course_question c where c.courseId=a.id and c.userId in(SELECT d.id from sys_user d where d.type='Student')  )as cc ," +
			"(select count(*) from course_answer c,course_question d where c.questionId=d.id and d.courseId=a.id and c.userId in(SELECT d.id from sys_user d where d.type='Student') ) as dd " +
			"from course a  where a.recordStatus='Y')zz";

		}
		int length=titleName.length;
		HSSFCellStyle style1 = workbook.createCellStyle();
		style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFCellStyle headStyle = workbook.createCellStyle();
		headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont font=workbook.createFont();
        font.setColor(HSSFColor.RED.index);
        font.setFontHeightInPoints((short)10);
        headStyle.setFont(font);
        for(short i=0;i<length;i++){
			HSSFCell cell=titleRow.createCell(i);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
          //  cell.setEncoding((short)1);
			cell.setCellValue(titleName[i]);
			cell.setCellStyle(headStyle);
		}
         
		List<Object[]> list = (List<Object[]>)baseDao.findBySql(sql);
		int size=list.size();
		if(null!=list && size>0){
			for(int i=0;i<size;i++){
				HSSFRow row = sheet.createRow(i+1);//创建一行
				for(short j=0;j<length;j++){
					HSSFCell cell = row.createCell(j);//创建一列
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		           // cell.setEncoding((short)1);
					cell.setCellStyle(style1);
	                cell.setCellValue(list.get(i)[j]+"");
				}
			}
		}
        
        return workbook;
	}
	
	public List<TimeBean> getXstw(String start, String end){
		List<TimeBean> listbean=new ArrayList<TimeBean>();
		//查询上周的日期//
		Calendar c1=Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			c1.setTime(df.parse(start));
			c1.add(Calendar.DATE, -7);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String sql="SELECT zz.name,zz.aa,zz.bb,(zz.aa-zz.cc)as a1,(zz.bb-zz.dd)as a2 from (SELECT a.name," +
				"(select count(*) from course_question c where c.courseId=a.id and c.userId in(SELECT d.id from sys_user d where d.type='Student') and date_format('"+start+"','%Y%m%d')<=date_format(c.createTime,'%Y%m%d') and  date_format(c.createTime,'%Y%m%d')<=date_format('"+end+"','%Y%m%d') )as aa ," +
				"(select count(*) from course_answer c,course_question d where c.questionId=d.id and d.courseId=a.id and c.userId in(SELECT d.id from sys_user d where d.type='Student') and date_format('"+start+"','%Y%m%d')<=date_format(c.createTime,'%Y%m%d') and  date_format(c.createTime,'%Y%m%d')<=date_format('"+end+"','%Y%m%d')) as bb ," +
				"(select count(*) from course_question c where c.courseId=a.id and c.userId in(SELECT d.id from sys_user d where d.type='Student') and date_format('"+df.format(c1.getTime())+"','%Y%m%d')<=date_format(c.createTime,'%Y%m%d') and  date_format(c.createTime,'%Y%m%d')<date_format('"+start+"','%Y%m%d') )as cc ," +
				"(select count(*) from course_answer c,course_question d where c.questionId=d.id and d.courseId=a.id and c.userId in(SELECT d.id from sys_user d where d.type='Student') and date_format('"+df.format(c1.getTime())+"','%Y%m%d')<=date_format(c.createTime,'%Y%m%d') and  date_format(c.createTime,'%Y%m%d')<date_format('"+start+"','%Y%m%d')) as dd " +
				"from course a  where a.recordStatus='Y')zz";
		List<Object[]> list=(List<Object[]>)baseDao.findBySql(sql);
		for(int i=0;i<list.size();i++){
			TimeBean bean=new TimeBean();
			bean.setTimeName(list.get(i)[0].toString());
			bean.setNo1(list.get(i)[1].toString());
			bean.setNo2(list.get(i)[2].toString());
			bean.setNo3(list.get(i)[3].toString());
			bean.setNo4(list.get(i)[4].toString());
			listbean.add(bean);
		}
		return listbean;
	}
	
	/*********************************************活跃趋势***************************************************/
	public List<Date> getZCQS(String areaId,String end,String start){
		String sql="";
		if(!"".equals(areaId)||!"".equals(end)||!"".equals(start)){
			sql="SELECT DISTINCT DATE_FORMAT(a.createTime,'%Y-%m-%d') FROM sys_user a ,student b where a.id=b.id ";
			if(!"".equals(areaId)){
				sql+=" and b.sourceArea='"+areaId+"'";
			}
			if(!"".equals(start)){
				sql+=" and DATE_FORMAT('"+start+"','%Y%m%d')<= DATE_FORMAT(a.createTime,'%Y%m%d')";
			}
			if(!"".equals(end)){
				sql+=" and DATE_FORMAT(a.createTime,'%Y%m%d')<=DATE_FORMAT('"+end+"','%Y%m%d')";
			}
			sql+=" and a.recordStatus='Y' ORDER BY a.createTime DESC";
		}else{
			sql="SELECT DISTINCT DATE_FORMAT(a.createTime,'%Y-%m-%d') FROM sys_user a  where a.recordStatus='Y' ORDER BY a.createTime DESC ";
		}
		List<Date> pojo=baseDao.findBySql(sql);
		return pojo;
	}
	public Object getPeople(String areaId,String end,String start){
		String sql="";
		if(!"".equals(areaId)||!"".equals(end)||!"".equals(start)){
			sql="SELECT aa from (SELECT a.createTime,count(*) as aa from sys_user a,student b where a.id =b.id AND a.recordStatus='Y' ";
			if(!"".equals(areaId)){
				sql+=" AND b.sourceArea='"+areaId+"'";
			}
			if(!"".equals(start)){
				sql+=" and DATE_FORMAT('"+start+"','%Y%m%d')<= DATE_FORMAT(a.createTime,'%Y%m%d')";
			}
			if(!"".equals(end)){
				sql+=" and DATE_FORMAT(a.createTime,'%Y%m%d')<=DATE_FORMAT('"+end+"','%Y%m%d')";
			}
			sql+=" GROUP BY DATE_FORMAT(a.createTime,'%Y-%m-%d'))bb ORDER BY bb.createTime desc";
		}else{
			sql="SELECT aa.a1 from (SELECT a.createTime,count(*) as a1 from sys_user a where a.recordStatus='Y'  GROUP BY DATE_FORMAT(a.createTime,'%Y-%m-%d'))aa ORDER BY DATE_FORMAT(aa.createTime,'%Y-%m-%d') DESC";
		}
		List<PublicPojo> pojo=baseDao.findBySql(sql);
		return pojo;
	}
	public Object getTotal(String areaId,String end,String start){
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		Map<String,Object> map=new HashMap<String,Object>();
		
		String sql="SELECT a.id,a.name from area a where  a.id    in  (SELECT b.sourceArea from student b  ) "; //查询地区
		if(!"".equals(areaId)){
			sql+="and id='"+areaId+"'";
		}
		List<Object[]> pojo=(List<Object[]>)baseDao.findBySql(sql);
		for(int i=0;i<pojo.size();i++){
			List<Integer> list1=new ArrayList<Integer>();
			map=new HashMap<String,Object>();
			List<Object> obj=new ArrayList<Object>();
			map.put("name", pojo.get(i)[1]);
			
			sql="SELECT * FROM (SELECT DATE_FORMAT(a.createTime,'%Y-%m-%d') as a1 from sys_user a ,student b where b.id=a.id  and a.recordStatus='Y' " ;
			if(!"".equals(areaId)){
				sql+="and b.sourceArea='"+areaId+"'";
			}
			if(!"".equals(start)){
				sql+=" and DATE_FORMAT('"+start+"','%Y%m%d')<= DATE_FORMAT(a.createTime,'%Y%m%d')";
			}
			if(!"".equals(end)){
				sql+=" and DATE_FORMAT(a.createTime,'%Y%m%d')<=DATE_FORMAT('"+end+"','%Y%m%d')";
			}
			sql+=" GROUP BY DATE_FORMAT(a.createTime,'%Y-%m-%d') )a  ORDER BY a.a1 desc";
			List<Object> p1=(List<Object>)baseDao.findBySql(sql);
			for(int j=0;j<p1.size();j++){
				Long count=baseDao.countBySql("SELECT count(*) from sys_user a ,student b where b.id=a.id  and a.recordStatus='Y' and DATE_FORMAT(a.createTime,'%Y-%m-%d')=? and b.sourceArea=?", new Object[]{p1.get(j),pojo.get(i)[0]});
				list1.add(Integer.valueOf(count+""));
			}
			map.put("data",list1);
			list.add(map);
		}
		return list;
	}
	
	
	public Object getXL(String areaId,String end,String start){
		String sql="";
		if(!"".equals(areaId)||!"".equals(end)||!"".equals(start)){
			sql="SELECT name,(SELECT count(*) from sys_user b,student c where b.recordStatus='Y' and b.id=c.id and c.education=a.name " ;
			if(!"".equals(areaId)){
				sql+=" and c.sourceArea='"+areaId+"' ";
			}
			if(!"".equals(start)){
				sql+=" and DATE_FORMAT('"+start+"','%Y%m%d')<=DATE_FORMAT(b.createTime,'%Y%m%d')";			
			}
			if(!"".equals(end)){
				sql+=" and DATE_FORMAT(b.createTime,'%Y%m%d')<=DATE_FORMAT('"+end+"','%Y%m%d')";
			}
			sql+=") FROM degree a where recordStatus is null";
		}else{
			sql="SELECT name,(SELECT count(*) from sys_user b,student c where b.recordStatus='Y' and b.id=c.id and c.education=a.name) FROM degree a where recordStatus is null";
		}
		List<PublicPojo> pojo=baseDao.findBySql(sql);
		return pojo;
	}
	public Object getSF(String areaId,String end,String start){
		String sql="";
		if(!"".equals(areaId)){
			sql="SELECT name,(SELECT count(*) from sys_user b,student c where b.id=c.id and b.recordStatus='Y' and c.position=a.id and c.sourceArea='"+areaId+"')as aa from identity a where recordStatus ='Y'";
		}else{
			sql="SELECT name,(SELECT count(*) from sys_user b,student c where b.id=c.id and b.recordStatus='Y' and c.position=a.id)as aa from identity a where recordStatus ='Y' ";
		}
		List<PublicPojo> pojo=baseDao.findBySql(sql);
		return pojo;
	}
	
	public List<Date> getDLQS(String areaId,String end,String start){
		String sql="";
		if(!"".equals(areaId)||!"".equals(end)||!"".equals(start)){
			sql="SELECT DISTINCT DATE_FORMAT(a.USE_TIME,'%Y-%m-%d') from sys_log a,student b where a.USER_ID=b.id ";
			if(!"".equals(areaId)){
				sql+=" and b.sourceArea='"+areaId+"' ";
			}
			if(!"".equals(start)){
				sql+=" and DATE_FORMAT('"+start+"','%Y%m%d')<= DATE_FORMAT(a.USE_TIME,'%Y%m%d')";
			}
			if(!"".equals(end)){
				sql+=" and DATE_FORMAT(a.USE_TIME,'%Y%m%d')<=DATE_FORMAT('"+end+"','%Y%m%d')";
			}
			sql+=" and  a.NOTE='登录系统成功'  ORDER BY a.USE_TIME DESC";
		}else{
			sql="SELECT DISTINCT DATE_FORMAT(a.USE_TIME,'%Y-%m-%d') from sys_log a where a.NOTE='登录系统成功'  ORDER BY a.USE_TIME DESC";
		}
		List<Date> pojo=baseDao.findBySql(sql);
		return pojo;
	}
	public Object getDlqsPeople(String areaId,String end,String start){
		String sql="";
		if(!"".equals(areaId)||!"".equals(end)||!"".equals(start)){
			sql="SELECT a1.bb from (SELECT  DATE_FORMAT(a.USE_TIME,'%Y-%m-%d')as aa,count(*) as bb from sys_log a ,student b where a.USER_ID=b.id ";
			if(!"".equals(areaId)){
				sql+=" and b.sourceArea='"+areaId+"' ";
			}
			if(!"".equals(start)){
				sql+=" and DATE_FORMAT('"+start+"','%Y%m%d')<= DATE_FORMAT(a.USE_TIME,'%Y%m%d')";
			}
			if(!"".equals(end)){
				sql+=" and DATE_FORMAT(a.USE_TIME,'%Y%m%d')<=DATE_FORMAT('"+end+"','%Y%m%d')";
			}
			sql+=" and a.NOTE='登录系统成功'  GROUP BY DATE_FORMAT(a.USE_TIME,'%Y-%m-%d'))a1 ORDER BY a1.aa DESC";
		}else{
			sql="SELECT a1.bb from (SELECT  DATE_FORMAT(a.USE_TIME,'%Y-%m-%d')as aa,count(DISTINCT a.USER_ID) as bb from sys_log a  where a.NOTE='登录系统成功'  GROUP BY DATE_FORMAT(a.USE_TIME,'%Y-%m-%d'))a1 ORDER BY a1.aa DESC";
		}
		List<PublicPojo> pojo=baseDao.findBySql(sql);
		return pojo;
	}
	/**********************************************课程情况*********************************************************************/
	public List<Date> getXkqstime(String areaId, String courseId){
		String sql="SELECT  DISTINCT a.addTime as c from  course_student a,student b where a.studentId=b.id ";
		if(!"".equals(areaId)){
			sql+="  AND b.sourceArea='"+areaId+"'";
		}
		if(!"".equals(courseId)){
			sql+="  and courseId='"+courseId+"' ";
		}
		List<Date> pojo=baseDao.findBySql(sql);
		return pojo;
	}
	public Object getXkqsPeople(String areaId, String courseId){
		String sql="SELECT   count(*) as a  from  course_student z,student bb WHERE z.studentId=bb.id   ";
		if(!"".equals(areaId)){
			sql+="  AND bb.sourceArea='"+areaId+"'";
		}
		if(!"".equals(courseId)){
			sql+="  and z.courseId='"+courseId+"' ";
		}
		sql+="  GROUP BY addTime";
		List<PublicPojo> pojo=baseDao.findBySql(sql);
		return pojo;
	}
	public Object getXl(String areaId, String courseId){
		String sql="select  name, ( select count(*) from student a where a.education=name ";
		if(!"".equals(areaId)){
			sql+="  and a.sourceArea='"+areaId+"'";
		}
		sql+=" and a.id in(select c.studentId from course_student c";
		if(!"".equals(courseId)){
			sql+="  where c.courseId='"+courseId+"' ";
		}
		 sql+=")) as a from degree  where recordStatus is null";
		List<PublicPojo> pojo=baseDao.findBySql(sql);
		return pojo;
	}

	public Object getSf(String areaId, String courseId){
		String sql="select  a.name,( select count(*) from student b where b.position=a.id ";
		if(!"".equals(areaId)){
			sql+="  and b.sourceArea='"+areaId+"'";
		}
		sql+=" and b.id in(select c.studentId from course_student c ";
		if(!"".equals(courseId)){
			sql+="  where c.courseId='"+courseId+"' ";
		}
		 sql+=")) as a from identity a where a.recordStatus ='Y'";
		List<PublicPojo> pojo=baseDao.findBySql(sql);
		return pojo;
	}
	public Object getDW(String areaId, String courseId){
		String sql="select a.name,(SELECT count(*) from student b where b.sourceArea=a.id ) from area a";
		
		List<PublicPojo> pojo=baseDao.findBySql(sql);
		return pojo;
	}
	
	public Object getKsqk(String areaId, String courseId){
		String Nums = "[A,B,C,D,E,F,G,H,I,J]";
		String sql="";
		sql="select count(*), CASE  when score>=90 and score<=100 then 'A' " +
				"when score>=80 and score<=89 then 'B'	" +
				"when score>=70 and score<=79 then 'C'	" +
				"when score>=60 and score<=69 then 'D'	" +
				"when score>=50 and score<=59 then 'E'	" +
				"when score>=40 and score<=49 then 'F'	" +
				"when score>=30 and score<=39 then 'G'	" +
				"when score>=20 and score<=29 then 'H'	" +
				"when score>=10 and score<=19 then 'I'	else 'J'	" +
				"END as scoreDj FROM student_exam a INNER JOIN student b on a.studentId=b.id " ;
		if(!"".equals(areaId)){
			sql+=" AND b.sourceArea='"+areaId+"'";
		}
		sql+=" INNER JOIN exam e on examId=e.id  where e.courseId='"+courseId+"' group by scoreDj";
		List<Object[]> list = (List<Object[]>)baseDao.findBySql(sql);
		if (null != list && list.size() > 0) {
			for (int j = 0; j < list.size(); j++) {
				Nums = Nums.replace(list.get(j)[1]+"", list.get(j)[0]+"");
			}
		}
		Nums = Nums.replaceAll("[a-zA-Z]", "0");
		return Nums;
	}
	//----------------------------//
	private String returnHomeworkNum(String areaId,String courseId,Long stuId){
		String homeworkNum = "";
		String sql="";
		List<Object> objL = new ArrayList<Object>();
		if(stuId == -1l){
			sql="select  count(*) from course_chapter where id  in(select ct.chapterId from chapter_testQuestion ct INNER JOIN testQuestion t on t.id= ct.testQuestionId  and t.recordStatus='Y') and courseId="+courseId+"   and pid is not  NULL and chapterTime is not null ";
			objL = (List<Object>)baseDao.findBySql(sql);
		}else{//听完课 做完作业 数
			sql="SELECT count(*) from courset_schedule where  chapterId  in(select ct.chapterId from chapter_testQuestion ct INNER JOIN testQuestion t on t.id= ct.testQuestionId  and t.recordStatus='Y') and homeworkResult='Y' and   courseId="+courseId+" and studentId='"+stuId+"'";
			objL = (List<Object>)baseDao.findBySql(sql);
		}
		if(objL != null && objL.size()>0){
			homeworkNum = objL.get(0) + "";
		}
		return homeworkNum;
	}
	//----------------------------//
	
	//*********************************************考试情况****************************************************************//
	public List<Date> getWckstime(String areaId, String courseId){
		String sql="select DISTINCT DATE_FORMAT(a.examTime,'%Y-%m-%d') as aa from student_exam a  INNER JOIN student b ON a.studentId=b.id  ";
		if(!"".equals(areaId)){
			sql+="  and  b.sourceArea='"+areaId+"'";
		}
		sql+=" INNER JOIN exam c on a.examId=c.id ";
		if(!"".equals(courseId)){
			sql+="  and c.courseId='"+courseId+"' ";
		}
		List<Date> pojo=baseDao.findBySql(sql);
		return pojo;
	}
	public Object getWcksPeople(String areaId, String courseId){
		String sql="select count(*) from student_exam a  INNER JOIN student b ON a.studentId=b.id  ";
		if(!"".equals(areaId)){
			sql+="  AND b.sourceArea='"+areaId+"'";
		}
		sql+=" INNER JOIN exam c on a.examId=c.id";
		if(!"".equals(courseId)){
			sql+="   and c.courseId='"+courseId+"' ";
		}
		sql+="  GROUP BY DATE_FORMAT(a.examTime,'%Y-%m-%d')";
		List<PublicPojo> pojo=baseDao.findBySql(sql);
		return pojo;
	}
	
	public String toLearningEffectCharts(String areaId, Long courseId){
		String courseStuNum = "0";//选课人数	1
		String examZgStuNum = "0";//具备参考资格人数	2
		String examCkStuNum = "0";//参考人数	3
		String examHgStuNum = "0";//合格人数	4
		String examLhStuNum = "0";//良好人数	5
		String examYxStuNum = "0";//优秀人数	6
		String examScoreNums = "[A,B,C,D,E,F,G,H,I,J]";//相应分数 人数 7
		Long courseHomeworkNum = baseDao.countBySql("select count(DISTINCT cc.id)  from (course_chapter as cc inner join chapter_testquestion as ct on ct.chapterId=cc.id ) inner join course as c on c.id=cc.courseId where c.id=?",new Object[]{courseId});
		/*选课人数*/
		String sql1 = " SELECT count(*) from course_student where courseId= ?";
		/*具备参考资格人数*/
		String sql2 = " SELECT count(*),1 from course_student where courseId= "+courseId;
		if(courseHomeworkNum > 0){//要先完成作业,才有资格考试
			sql2 = "select count(*) as bb,1 from student_homework  as sh inner join course_chapter as cc on cc.id=sh.chapterId  where cc.courseId="+courseId+" group by sh.studentId  having bb >="+courseHomeworkNum;
		}
		/*参考人数*/
		String sql3 = " select count(*) from student_exam se INNER JOIN exam e on se.examId=e.id where e.courseId=?";
		/*合格人数*/
		String sql4 = "	select count(*) from student_exam se INNER JOIN exam e on se.examId=e.id   where score>=60 and e.courseId=?";
		/*良好人数*/
		String sql5 = " select count(*) from student_exam se INNER JOIN exam e on se.examId=e.id   where score>=80 and score<90 and e.courseId=?";
		/*优秀人数*/
		String sql6 = " select count(*) from student_exam se INNER JOIN exam e on se.examId=e.id   where score>=90 and e.courseId=?";
		/*相应分数 人数*/
		String sql7="";
		sql7="select count(*), CASE  when score>=90 and score<=100 then 'A' " +
				"when score>=80 and score<=89 then 'B'	" +
				"when score>=70 and score<=79 then 'C'	" +
				"when score>=60 and score<=69 then 'D'	" +
				"when score>=50 and score<=59 then 'E'	" +
				"when score>=40 and score<=49 then 'F'	" +
				"when score>=30 and score<=39 then 'G'	" +
				"when score>=20 and score<=29 then 'H'	" +
				"when score>=10 and score<=19 then 'I'	else 'J'	" +
				"END as scoreDj FROM student_exam a INNER JOIN student b on a.studentId=b.id " ;
		if(!"".equals(areaId)){
			sql7+=" AND b.sourceArea='"+areaId+"'";
		}
		sql7+=" INNER JOIN exam e on examId=e.id  where e.courseId='"+courseId+"' group by scoreDj";
		courseStuNum = baseDao.countBySql(sql1, new Object[]{courseId}) + "";
		List<Object[]> obj2 = baseDao.findBySql(sql2);
		if (null != obj2 && obj2.size() > 0) {
			examZgStuNum = obj2.get(0)[0]+"";
		}
		examCkStuNum = baseDao.countBySql(sql3, new Object[]{courseId}) + "";
		examHgStuNum = baseDao.countBySql(sql4, new Object[]{courseId}) + "";
		examLhStuNum = baseDao.countBySql(sql5, new Object[]{courseId}) + "";
		examYxStuNum = baseDao.countBySql(sql6, new Object[]{courseId}) + "";
		List<Object[]> objExamScoreNums = baseDao.findBySql(sql7);
		if (null != objExamScoreNums && objExamScoreNums.size() > 0) {
			for (int j = 0; j < objExamScoreNums.size(); j++) {
				examScoreNums = examScoreNums.replace(objExamScoreNums.get(j)[1]+"", objExamScoreNums.get(j)[0]+"");
			}
		}
		examScoreNums = examScoreNums.replaceAll("[a-zA-Z]", "0");
		return courseStuNum+"BB"+examZgStuNum+"BB"+examCkStuNum+"BB"+examHgStuNum+"BB"+examLhStuNum+"BB"+examYxStuNum+"BB"+examScoreNums;
		
	}
	public String LeaningCount(String areaId, String courseId){
		String sql="";
		sql="SELECT count(*) from student b where b.id in (SELECT  a.studentId from course_student a where a.joinRemoveWayName like '%添加' " ;
		if(!"".equals(courseId)){
			sql+=" and a.courseId='"+courseId+"'";
		}
		sql+=" )";
		if(!"".equals(areaId)){
			sql+="  and b.sourceArea='"+areaId+"'";
		}
		Long aa=baseDao.countBySql(sql,new Object[]{}); //选课人数
		return aa+"," ;
	}
	public List<TimeBean> Pf(String courseId){
		List<TimeBean> bean=new ArrayList<TimeBean>();
		List<Object[]> list=(List<Object[]>)baseDao.findBySql("select count(*),sum(evaluation),sum(evaluation1),sum(evaluation2),sum(evaluation3)    from course_evaluation  where courseId= "+courseId);
		TimeBean a=new TimeBean();
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				TimeBean b=new TimeBean();
				b.setNo1(String.valueOf(2*Float.parseFloat(list.get(i)[1]+"")/Long.parseLong(list.get(i)[0]+"")));
				b.setNo2(String.valueOf(2*Float.parseFloat(list.get(i)[2]+"")/Long.parseLong(list.get(i)[0]+"")));
				b.setNo3(String.valueOf(2*Float.parseFloat(list.get(i)[3]+"")/Long.parseLong(list.get(i)[0]+"")));
				b.setNo4(String.valueOf(2*Float.parseFloat(list.get(i)[4]+"")/Long.parseLong(list.get(i)[0]+"")));
				bean.add(b);
			}
		}else{
			a.setNo1("10.0");a.setNo2("10.0");
			a.setNo3("10.0");a.setNo4("10.0");bean.add(a);
		}
		return bean;
	}



	/**************************************统计月报********************************************************************************/
	public String getCountLeaning(String start, String end){
		Long a1=baseDao.countBySql("SELECT count(*) from sys_user a where a.recordStatus='Y' ",new Object[]{});
		Long a2=baseDao.countBySql("SELECT count(*) from sys_user a where a.recordStatus='Y' and  ?<=DATE_FORMAT(a.createTime,'%Y-%m-%d') and DATE_FORMAT(a.createTime,'%Y-%m-%d')<= ?",new Object[]{start,  end});
		Long a3=baseDao.countBySql("SELECT count(DISTINCT a.USER_ID) from sys_log a where  a.NOTE='登录系统成功'  and ?<=DATE_FORMAT(a.USE_TIME,'%Y-%m-%d') and DATE_FORMAT(a.USE_TIME,'%Y-%m-%d')<=?", new Object[]{start,  end});
		Long a4=baseDao.countBySql("SELECT count(*) from course a where  a.recordStatus='Y'  ", new Object[]{});
		Long a5=baseDao.countBySql("SELECT count(*) from course a where  a.recordStatus='Y'  and ?<=DATE_FORMAT(a.createTime,'%Y-%m-%d') and DATE_FORMAT(a.createTime,'%Y-%m-%d')<=? ", new Object[]{start,  end});
		Long a6=baseDao.countBySql("SELECT count(*) from course_chapter a where a.recordStatus='Y' AND a.videoName is not null ", new Object[]{});
		Long a7=baseDao.countBySql("SELECT count(*) from course_chapter a where a.recordStatus='Y' and ?<=DATE_FORMAT(a.modifyTime,'%Y-%m-%d') and DATE_FORMAT(a.modifyTime,'%Y-%m-%d')<=? AND a.videoName is not null ", new Object[]{start,  end});
		Long a8=baseDao.countBySql("SELECT count(*) from exam a where a.recordStatus='Y' ", new Object[]{});
		Long a9=baseDao.countBySql("SELECT count(*) from exam a where a.recordStatus='Y' and ?<=DATE_FORMAT(a.createTime,'%Y-%m-%d') and DATE_FORMAT(a.createTime,'%Y-%m-%d')<=? ", new Object[]{start,  end});
		Long a10=baseDao.countBySql("SELECT count(*) from course_question a where a.recordStatus='Y' ", new Object[]{});
		Long a11=baseDao.countBySql("SELECT count(*) from course_question a where a.recordStatus='Y' and ?<=DATE_FORMAT(a.createTime,'%Y-%m-%d') and DATE_FORMAT(a.createTime,'%Y-%m-%d')<=? ", new Object[]{start,  end});
		
		
		return a1+","+a2+","+(a2*100/a1)+","+a3+","+(a3*100/a1)+","+a4+","+a5+","+a6+","+a7+","+a8+","+a9+","+a10+","+a11;
	}
	
	public List<PublicPojo> getZcrs(){
		List<PublicPojo> p=new ArrayList<PublicPojo>();
		String sql="SELECT c.name ,( SELECT count(*) FROM sys_user a,student b WHERE a.id=b.id and b.sourceArea=c.id) as  a  FROM  area c ";
		List<Object[]> pojo=(List<Object[]>)baseDao.findBySql(sql);
		for(int i=0;i<pojo.size();i++){
			PublicPojo Pojo=new PublicPojo();
			Pojo.setName(pojo.get(i)[0].toString());
			Pojo.setA(pojo.get(i)[1].toString());
			p.add(Pojo);
		}
		return p;
	}
	/**************************整体统计********************************************************/
	public String getAllLeaning(String start, String end){
		String sql="";
		String sql2="";
		if(!"".equals(start)){
			sql+=" and DATE_FORMAT('"+start+"','%Y%m%d') <= DATE_FORMAT(a.createTime,'%Y%m%d') ";
			sql2+=" and DATE_FORMAT('"+start+"','%Y%m%d') <= DATE_FORMAT(a.USE_TIME,'%Y%m%d')  ";
		}
		if(!"".equals(end)){
			sql+=" and  DATE_FORMAT(a.createTime,'%Y%m%d')<=DATE_FORMAT('"+end+"','%Y%m%d') ";
			sql2+=" and  DATE_FORMAT(a.USE_TIME,'%Y%m%d')<=DATE_FORMAT('"+end+"','%Y%m%d')  ";
		}
		Long a1=baseDao.countBySql("SELECT count(*) from sys_user a where a.recordStatus='Y' ", new Object[]{});//总的注册人数
		Long a2=baseDao.countBySql("SELECT count(*) from course  a where a.recordStatus='Y' ", new Object[]{});//总的课程数
		Long a3=baseDao.countBySql("SELECT count(*) from sys_user a where a.recordStatus='Y' and a.type='Teacher'", new Object[]{});//累计教师
		Long a4=baseDao.countBySql("SELECT count(*) FROM sys_log a,sys_user b where a.USER_ID=b.id and b.recordStatus='Y' and b.type='Student' and a.NOTE='登录系统成功'"+sql2, new Object[]{});//学生登录数
		Long a5=baseDao.countBySql("SELECT count(*) from sys_user a where a.recordStatus='Y' and a.type='Student'"+sql, new Object[]{});//学生注册数
		Long a6=baseDao.countBySql("SELECT count(*) FROM course  a where a.recordStatus='Y' and  a.resourceId='Public' "+sql, new Object[]{});//公开课
		Long a7=baseDao.countBySql("SELECT count(*) FROM course  a where a.recordStatus='Y' and  a.resourceId='Mk' "+sql, new Object[]{});//慕课
		Long a8=baseDao.countBySql("SELECT count(*) FROM course  a where a.recordStatus='Y' and  a.resourceId='Wk' "+sql, new Object[]{});//微课
		Long a9=baseDao.countBySql("SELECT count(*) FROM course  a where a.recordStatus='Y' and  a.resourceId='Layer' "+sql, new Object[]{});//多媒体资源
		Long a10=baseDao.countBySql("SELECT TO_DAYS(STR_TO_DATE(NOW(),'%Y-%m-%d'))-TO_DAYS(STR_TO_DATE(min(a.USE_TIME),'%Y-%m-%d')) as a1 from sys_log a", new Object[]{});//总体运行时间
			
		return a1+","+a2+","+a3+","+a4+","+a5+","+a6+","+a7+","+a8+","+a9+","+a10;
	}
	
	public List<TimeBean> getCountDW(String start, String end){
		List<TimeBean> list=new ArrayList<TimeBean>();
		String sql="SELECT a.name,(SELECT count(*) from student b,sys_user c where c.id=b.id and b.sourceArea=a.id " ;
		if(!"".equals(start)){
			sql+="  and   DATE_FORMAT('"+start+"','%Y%m%d')<= DATE_FORMAT(c.createTime,'%Y%m%d') ";
		}
		if(!"".equals(end)){
			sql+="  and DATE_FORMAT(c.createTime,'%Y%m%d') <= DATE_FORMAT('"+end+"','%Y%m%d') ";
		}
		sql+=" ) as a1 from area a";
		List<Object[]> list1=(List<Object[]>)baseDao.findBySql(sql);
		for(int i=0;i<list1.size();i++){
			TimeBean t=new TimeBean();
			t.setTimeName(list1.get(i)[0].toString());
			t.setNo1(list1.get(i)[1].toString());
			list.add(t);
		}
		return list;
	}
	/***********************************END********************************************************************/
	public static void main(String[] args){
		Calendar c=Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			c.setTime(df.parse("2016-05-05"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.add(Calendar.DATE, -7);
		System.out.println(c.getTime());
	
	}
	
	public List<TimeBean> beanList(String courseId){
		List<TimeBean> list=new ArrayList<TimeBean>();
		List<Object[]> obj=(List<Object[]>)baseDao.findBySql("SELECT a.id,a.name from area a"); 
		List<String> list1=(List<String>)baseDao.findBySql("select  count(*) from course_chapter where courseId="+courseId+"   and pid is not  NULL and chapterTime is not null ");
		for(int i=0;i<obj.size();i++){
			Long e=0l;
			TimeBean bean=new TimeBean();
			bean.setTimeName(obj.get(i)[1].toString());
			//总的章节
			Long a=baseDao.countBySql("SELECT count(*) from courset_schedule c,student b where c.courseId=? and b.id=c.studentId and b.sourceArea=?", new Object[]{courseId,obj.get(i)[0].toString()});
			//学习完章节
			Long b=baseDao.countBySql("SELECT count(*) from courset_schedule c,student b where c.courseId=? and c.studyStatusName='已完成' and c.homeworkResult='Y' and b.id=c.studentId and b.sourceArea=?", new Object[]{courseId,obj.get(i)[0].toString()});
			//学习的学生
			Long c=baseDao.countBySql("SELECT count(DISTINCT c.studentId) from courset_schedule c,student b where c.courseId=?  and b.id=c.studentId and b.sourceArea=?", new Object[]{courseId,obj.get(i)[0].toString()});
			bean.setNo1(String.valueOf(a)+"节");
			bean.setNo2(String.valueOf(b)+"节");
			if(a==0 && b==0){
				bean.setNo3("0%");
			}else{ bean.setNo3(String.valueOf(b*100/a)+"."+String.valueOf(b%a)+"%");}
			bean.setNo4(String.valueOf(c)+"人");
			//已考试情况
			Long d=baseDao.countBySql("SELECT COUNT(a.studentId) FROM student_exam a,exam b,student c where a.examId =b.id and a.studentId=c.id and b.courseId=? and c.sourceArea=?", new Object[]{courseId,obj.get(i)[0].toString()});
			bean.setNo5(String.valueOf(d)+"人");
			List<Object> list2=(List<Object> )baseDao.findBySql("select DISTINCT a.studentId  from courset_schedule a ,student b where a.studentId=b.id and b.sourceArea='"+obj.get(i)[0].toString()+"' and a.courseId='"+courseId+"'");
			for(int j=0;j<list2.size();j++){
				String cc=returnStatuNum(courseId,list2.get(j).toString());
				if(cc==list1.get(0)){e++;};
			}
			bean.setNo6(String.valueOf(e)+"人");
			if(c==0&&e==0){ 
				bean.setNo7("0%"); 
			}else{bean.setNo7(String.valueOf(e*100/c)+"."+String.valueOf(e%c)+"%"); }
			
			list.add(bean);
		}
		return list;
	}
	private String returnStatuNum(String coreseId,String stuId){
		String returns = "";
		List<Object> objL = new ArrayList<Object>();
		objL = (List<Object>)baseDao.findBySql("select count(*) from courset_schedule where courseId='"+coreseId+"' and studentId='"+stuId+"' and homeworkResult='Y'");
		if(objL != null && objL.size()>0){
			returns = objL.get(0) + "";
		}
		return returns;
	}
	public HSSFWorkbook courseExcelAll(Long id){
		HSSFWorkbook workbook=new HSSFWorkbook();
		HSSFSheet sheet=workbook.createSheet();
		sheet.setDefaultColumnWidth((short)20);
		HSSFRow titleRow=sheet.createRow(0);
		String[] titleName=new String[]{"单位","学习人数","总的节数","完成节数","学习百分比","学完人数","学完人数百分比","已考人数"};
		int length=titleName.length;
		HSSFCellStyle style1 = workbook.createCellStyle();
		style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFCellStyle headStyle = workbook.createCellStyle();
		headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont font=workbook.createFont();
        font.setColor(HSSFColor.RED.index);
        font.setFontHeightInPoints((short)10);
        headStyle.setFont(font);
        for(short i=0;i<length;i++){
			HSSFCell cell=titleRow.createCell(i);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
          //  cell.setEncoding((short)1);
			cell.setCellValue(titleName[i]);
			cell.setCellStyle(headStyle);
		}
        List<TimeBean> list=new ArrayList<TimeBean>();;
        if(id!=null){
        	list= beanList(String.valueOf(id));
        }
		int size=list.size();
		if(null!=list && size>0){
			for(int i=0;i<size;i++){
				HSSFRow row = sheet.createRow(i+1);//创建一行
				
				HSSFCell cell = row.createCell(0);//创建1列
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style1);
                cell.setCellValue(list.get(i).getTimeName()+"");
                
                cell = row.createCell(1);//创建2列
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style1);
                cell.setCellValue(list.get(i).getNo4()+"");
                
                cell = row.createCell(2);//创建3列
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style1);
                cell.setCellValue(list.get(i).getNo1()+"");
                
                cell = row.createCell(3);//创建4列
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style1);
                cell.setCellValue(list.get(i).getNo2()+"");
                
                cell = row.createCell(4);//创建5列
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style1);
                cell.setCellValue(list.get(i).getNo3()+"");
                
                cell = row.createCell(5);//创建6列
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style1);
                cell.setCellValue(list.get(i).getNo6()+"");
                
                cell = row.createCell(6);//创建7列
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style1);
                cell.setCellValue(list.get(i).getNo7()+"");
                
                cell = row.createCell(7);//创建8列
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style1);
                cell.setCellValue(list.get(i).getNo5()+"");
			}
		}
        
        return workbook;
	}
	
}
