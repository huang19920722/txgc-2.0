package com.yp.sys.entity.workorder;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.yp.sys.entity.User;
import com.yp.sys.entity.organization.Organization;
import com.yp.sys.entity.terminal.TerminalInfo;

/**
 * 工单信息
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_cyhc_work_order_info")
public class WorkOrderInfo implements java.io.Serializable {

	// Fields

	private Long id;
	private TerminalInfo terminal;//终端信息
	private Long workerParameterId;//标准工参
	private User user;//系统工单生成人员
	private String workOrderNum;//工单编号
	private String orderState;//工单状态   注：   空为待下达 ； 0：任务已下达； 1 ：任务已签收；2：数据已上传；3：待审核；4：已完成；
	private Timestamp completeTime;//完成日期
	private Timestamp timeLimit;//预计完成时间
	private String issuedWay;//下发方式  工单下达方式(1.从APP 0.从系统)
	private String issuedRemark;//下发备注
	private String explainState;//审核状态 1: 通过，2：不通过',
	private Timestamp explainTime;//审核时间
	private String explainRemark;//审核备注
	private Timestamp createTime;//创建时间
	private Organization org;//机构
	private String excFlag;//异常标识
	private Timestamp issuedTime;//下发时间
	private User explainUser;//审核人员
	private String networkStandard;//网络制适
	

	// Constructors




	/** default constructor */
	public WorkOrderInfo() {
	}

	/** minimal constructor */
	public WorkOrderInfo(Long id) {
		this.id = id;
	}

	/** full constructor */
	public WorkOrderInfo(Long id, TerminalInfo terminalId,
			Long workerParameterId, User userId, String workOrderNum,
			String orderState, Timestamp completeTime, Timestamp timeLimit,
			String issuedWay, String issuedRemark, String explainState,
			Timestamp explainTime, String explainRemark, Timestamp createTime,
			Organization org, String excFlag,String networkStandard) {
		this.id = id;
		this.terminal = terminalId;
		this.workerParameterId = workerParameterId;
		this.user = userId;
		this.workOrderNum = workOrderNum;
		this.orderState = orderState;
		this.completeTime = completeTime;
		this.timeLimit = timeLimit;
		this.issuedWay = issuedWay;
		this.issuedRemark = issuedRemark;
		this.explainState = explainState;
		this.explainTime = explainTime;
		this.explainRemark = explainRemark;
		this.createTime = createTime;
		this.org = org;
		this.excFlag = excFlag;
		this.networkStandard= networkStandard;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator = "pk")
	@GenericGenerator(name = "pk", strategy = "com.yp.sys.util.IdGenerator")
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "terminal_id")
	public TerminalInfo getTerminal() {
		return terminal;
	}

	public void setTerminal(TerminalInfo terminal) {
		this.terminal = terminal;
	}

	@Column(name = "worker_parameter_id")
	public Long getWorkerParameterId() {
		return this.workerParameterId;
	}

	public void setWorkerParameterId(Long workerParameterId) {
		this.workerParameterId = workerParameterId;
	}

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "explain_user_id")
	public User getExplainUser() {
		return explainUser;
	}

	public void setExplainUser(User explainUser) {
		this.explainUser = explainUser;
	}



	@Column(name = "work_order_num", length = 50)
	public String getWorkOrderNum() {
		return this.workOrderNum;
	}


	public void setWorkOrderNum(String workOrderNum) {
		this.workOrderNum = workOrderNum;
	}

	@Column(name = "order_state", length = 50)
	public String getOrderState() {
		return this.orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	@Column(name = "complete_time", length = 19)
	public Timestamp getCompleteTime() {
		return this.completeTime;
	}

	public void setCompleteTime(Timestamp completeTime) {
		this.completeTime = completeTime;
	}

	@Column(name = "time_limit")
	public Timestamp getTimeLimit() {
		return this.timeLimit;
	}

	public void setTimeLimit(Timestamp timeLimit) {
		this.timeLimit = timeLimit;
	}

	@Column(name = "issued_way", length = 20)
	public String getIssuedWay() {
		return this.issuedWay;
	}

	public void setIssuedWay(String issuedWay) {
		this.issuedWay = issuedWay;
	}

	@Column(name = "Issued_remark", length = 500)
	public String getIssuedRemark() {
		return this.issuedRemark;
	}

	public void setIssuedRemark(String issuedRemark) {
		this.issuedRemark = issuedRemark;
	}

	@Column(name = "explain_state", length = 20)
	public String getExplainState() {
		return this.explainState;
	}

	public void setExplainState(String explainState) {
		this.explainState = explainState;
	}

	@Column(name = "explain_time", length = 19)
	public Timestamp getExplainTime() {
		return this.explainTime;
	}

	public void setExplainTime(Timestamp explainTime) {
		this.explainTime = explainTime;
	}

	@Column(name = "explain_remark", length = 500)
	public String getExplainRemark() {
		return this.explainRemark;
	}

	public void setExplainRemark(String explainRemark) {
		this.explainRemark = explainRemark;
	}

	@Column(name = "create_time", length = 19)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "org_id")
	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}



	@Column(name = "exc_flag")
	public String getExcFlag() {
		return this.excFlag;
	}

	public void setExcFlag(String excFlag) {
		this.excFlag = excFlag;
	}
	
	@Column(name = "issued_time")
	public Timestamp getIssuedTime() {
		return issuedTime;
	}

	public void setIssuedTime(Timestamp issuedTime) {
		this.issuedTime = issuedTime;
	}
	
	@Column(name = "network_standard")
	public String getNetworkStandard() {
		return networkStandard;
	}

	public void setNetworkStandard(String networkStandard) {
		this.networkStandard = networkStandard;
	}


}