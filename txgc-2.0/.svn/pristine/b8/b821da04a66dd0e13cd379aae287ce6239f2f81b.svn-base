package com.yp.sys.entity.rtu;

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

/**
 * TCyhcRtuConfigInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_cyhc_rtu_config_info")
public class RtuConfigInfo implements java.io.Serializable {

	// Fields

	/***/
	private static final long serialVersionUID = 55934438600050504L;
	private Long cid;
	private RtuBaseInfo rtu;//rtu基础信息ID
	private String content;//rtu配置JSON字符串
	private Timestamp createTime;//创建时间
	private User createUser;//创建人
	private int configStatus;//配置状态 1未下发 2已下发 3停用
	private Timestamp issueTime;//下发时间
	private int configType;//配置类型  1:模组配置;2:采集配置;
	
	private int heartDelay;//心跳延时
	private int pubDelay;//上传延时

	// Constructors

	/** default constructor */
	public RtuConfigInfo() {
	}

	/** minimal constructor */
	public RtuConfigInfo(Long cid, Long rtuId) {
		this.cid = cid;
	}

	/** full constructor */
	public RtuConfigInfo(Long cid, RtuBaseInfo rtu, String content,
			Timestamp createTime, User createUser) {
		this.cid = cid;
		this.rtu = rtu;
		this.content = content;
		this.createTime = createTime;
		this.createUser = createUser;
	}

	@Id
	@Column(name = "cid", unique = true, nullable = false)
	@GeneratedValue(generator = "pk")
	@GenericGenerator(name = "pk", strategy = "com.yp.sys.util.IdGeneratorForRtu")
	public Long getCid() {
		return this.cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "rtu_id")
	public RtuBaseInfo getRtu() {
		return rtu;
	}

	public void setRtu(RtuBaseInfo rtu) {
		this.rtu = rtu;
	}

	@Column(name = "content", length = 65535)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
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
	@JoinColumn(name = "create_user_id")
	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	@Column(name="config_status")
	public int getConfigStatus() {
		return configStatus;
	}

	public void setConfigStatus(int configStatus) {
		this.configStatus = configStatus;
	}

	
	@Column(name="issue_time")
	public Timestamp getIssueTime() {
		return issueTime;
	}

	public void setIssueTime(Timestamp issueTime) {
		this.issueTime = issueTime;
	}
	
	@Column(name="config_type")
	public int getConfigType() {
		return configType;
	}

	public void setConfigType(int configType) {
		this.configType = configType;
	}
	@Column(name="heart_delay")
	public int getHeartDelay() {
		return heartDelay;
	}

	public void setHeartDelay(int heartDelay) {
		this.heartDelay = heartDelay;
	}
	@Column(name="pub_delay")
	public int getPubDelay() {
		return pubDelay;
	}

	public void setPubDelay(int pubDelay) {
		this.pubDelay = pubDelay;
	}
	
	
	
	

}