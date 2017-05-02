package com.yp.sys.pojo;

import java.sql.Timestamp;

import com.yp.sys.entity.rtu.RtuBaseInfo;

public class RtuSlaveInfoBean {
	private Long id;
	private String name;//名称
	private Long rtuId;//模组信息
	private Integer num;//序号
	private Integer type;//类型
	private Integer sid;
	private Integer cmd;
	private Integer start;//开始地址
	private Integer readsNum;//读取数量
	private Integer connModel;//连接方式
	private Timestamp creatTime;//创建时间
	private Long creatUserId;//创建者ID
	
	private Integer delayTime;//延时
	private Integer reserveColumn;//预留字段
	
	private Long modifyUserId;//修改者用户id
	private Timestamp modifyTime;//修改者用户id
	
	
	 private RtuBaseInfo rtu;//模组对象
	 
	 
	public RtuBaseInfo getRtu() {
		return rtu;
	}
	public void setRtu(RtuBaseInfo rtu) {
		this.rtu = rtu;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getRtuId() {
		return rtuId;
	}
	public void setRtuId(Long rtuId) {
		this.rtuId = rtuId;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public Integer getCmd() {
		return cmd;
	}
	public void setCmd(Integer cmd) {
		this.cmd = cmd;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getReadsNum() {
		return readsNum;
	}
	public void setReadsNum(Integer readsNum) {
		this.readsNum = readsNum;
	}
	public Integer getConnModel() {
		return connModel;
	}
	public void setConnModel(Integer connModel) {
		this.connModel = connModel;
	}
	public Timestamp getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(Timestamp creatTime) {
		this.creatTime = creatTime;
	}
	public Long getCreatUserId() {
		return creatUserId;
	}
	public void setCreatUserId(Long creatUserId) {
		this.creatUserId = creatUserId;
	}
	public Long getModifyUserId() {
		return modifyUserId;
	}
	public void setModifyUserId(Long modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	public Timestamp getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}
	public Integer getDelayTime() {
		return delayTime;
	}
	public void setDelayTime(Integer delayTime) {
		this.delayTime = delayTime;
	}
	public Integer getReserveColumn() {
		return reserveColumn;
	}
	public void setReserveColumn(Integer reserveColumn) {
		this.reserveColumn = reserveColumn;
	}
	
	
}
