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

/**
 * TCyhcRtuSlaveInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_cyhc_rtu_slave_info")
public class RtuSlaveInfo implements java.io.Serializable {

	/***/
	private static final long serialVersionUID = 7164517107286720636L;
	// Fields
	private Long id;
	private String name;//名称
//	private Long rtuId;//模组信息
	private RtuBaseInfo rtu;//模组对象
	private Integer num;//序号
	private Integer type;//类型
	private Integer sid;
	private Integer cmd;
	private Integer start;//开始地址
	private Integer readsNum;//读取数量
	private Integer connModel;//连接方式
	private Timestamp creatTime;//创建时间
	private Long creatUserId;//创建者ID
	
	private Long modifyUserId;//修改者用户id
	private Timestamp modifyTime;//修改者用户id

	// Constructors

	/** default constructor */
	public RtuSlaveInfo() {
	}

	/** minimal constructor */
	public RtuSlaveInfo(Long id) {
		this.id = id;
	}


	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(generator = "pk")
	@GenericGenerator(name = "pk", strategy = "com.yp.sys.util.IdGenerator")
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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



	@Column(name = "num")
	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	@Column(name = "type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "sid")
	public Integer getSid() {
		return this.sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	@Column(name = "cmd")
	public Integer getCmd() {
		return this.cmd;
	}

	public void setCmd(Integer cmd) {
		this.cmd = cmd;
	}

	@Column(name = "start")
	public Integer getStart() {
		return this.start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	


	@Column(name = "conn_model")
	public Integer getConnModel() {
		return this.connModel;
	}

	public void setConnModel(Integer connModel) {
		this.connModel = connModel;
	}

	@Column(name = "creat_time", length = 19)
	public Timestamp getCreatTime() {
		return this.creatTime;
	}

	public void setCreatTime(Timestamp creatTime) {
		this.creatTime = creatTime;
	}

	@Column(name = "creat_user_id")
	public Long getCreatUserId() {
		return this.creatUserId;
	}

	public void setCreatUserId(Long creatUserId) {
		this.creatUserId = creatUserId;
	}
	@Column(name="modify_user_id")
	public Long getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(Long modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	@Column(name="modify_time")
	public Timestamp getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}
	@Column(name = "reads_num")
	public Integer getReadsNum() {
		return readsNum;
	}

	public void setReadsNum(Integer readsNum) {
		this.readsNum = readsNum;
	}
	
	

}