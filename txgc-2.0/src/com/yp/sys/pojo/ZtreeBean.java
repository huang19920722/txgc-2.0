package com.yp.sys.pojo;

import java.util.List;

public class ZtreeBean {

	private String id;
	private String name;// 树节点名称
	private String pid;
	private String pname;
	private String url;//菜单的url地址
	private String iconCls;// 前面的小图标样式
	private Boolean checked = false;// 是否勾选状态
	private List<ZtreeBean> children;// 子节点
	private Boolean chkDisabled=false;//是否可用
	private String state = "open";// 是否展开(open,closed)
	private String target = "rightFrame";// 打开的目标
	private String slaveId;
	private String addFlag;//用于判定该节点是否能添加新增按钮
	private Object basicInfo;
	public ZtreeBean(){
		
	}
	
	public ZtreeBean(String name, List<ZtreeBean> children) {
		super();
		this.name = name;
		this.children = children;
	}
	
	public Boolean getChkDisabled() {
		return chkDisabled;
	}

	public void setChkDisabled(Boolean chkDisabled) {
		this.chkDisabled = chkDisabled;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	
	public List<ZtreeBean> getChildren() {
		return children;
	}
	public void setChildren(List<ZtreeBean> children) {
		this.children = children;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}

	public String getSlaveId() {
		return slaveId;
	}

	public void setSlaveId(String slaveId) {
		this.slaveId = slaveId;
	}

	public String getAddFlag() {
		return addFlag;
	}

	public void setAddFlag(String addFlag) {
		this.addFlag = addFlag;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Object getBasicInfo() {
		return basicInfo;
	}

	public void setBasicInfo(Object basicInfo) {
		this.basicInfo = basicInfo;
	}
	
}
