package com.yp.sys.entity.rtu;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;


/**
 * TCyhcRtuProperty entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_cyhc_rtu_property")
public class RtuProperty implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -2905749178703656070L;
	private Long id;
	private String propKey;//属性key
	private String propType;//属性类型
	private String propDesc;//属性描述
	private String propDefault;//默认值
	private Long pid;//父级ID
	private Integer propOrder;//属性排序
	private String isRelateRefer;//是否关联属性码表
	
	private List<RtuRefer> listRtuRefer;//关联的属性值

	// Constructors

	/** default constructor */
	public RtuProperty() {
	}

	/** minimal constructor */
	public RtuProperty(Long id) {
		this.id = id;
	}

	/** full constructor */
	public RtuProperty(Long id, String propKey, String propType,
			String propDesc, String propDefault, Long pid, Integer propOrder,
			String isRelateRefer) {
		this.id = id;
		this.propKey = propKey;
		this.propType = propType;
		this.propDesc = propDesc;
		this.propDefault = propDefault;
		this.pid = pid;
		this.propOrder = propOrder;
		this.isRelateRefer = isRelateRefer;
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

	@Column(name = "prop_key", length = 50)
	public String getPropKey() {
		return this.propKey;
	}

	public void setPropKey(String propKey) {
		this.propKey = propKey;
	}

	@Column(name = "prop_type", length = 50)
	public String getPropType() {
		return this.propType;
	}

	public void setPropType(String propType) {
		this.propType = propType;
	}

	@Column(name = "prop_desc", length = 100)
	public String getPropDesc() {
		return this.propDesc;
	}

	public void setPropDesc(String propDesc) {
		this.propDesc = propDesc;
	}

	@Column(name = "prop_default", length = 100)
	public String getPropDefault() {
		return this.propDefault;
	}

	public void setPropDefault(String propDefault) {
		this.propDefault = propDefault;
	}

	@Column(name = "pid")
	public Long getPid() {
		return this.pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	@Column(name = "prop_order")
	public Integer getPropOrder() {
		return this.propOrder;
	}

	public void setPropOrder(Integer propOrder) {
		this.propOrder = propOrder;
	}

	@Column(name = "is_relate_refer", length = 2)
	public String getIsRelateRefer() {
		return this.isRelateRefer;
	}

	public void setIsRelateRefer(String isRelateRefer) {
		this.isRelateRefer = isRelateRefer;
	}
	@Transient
	public List<RtuRefer> getListRtuRefer() {
		return listRtuRefer;
	}

	public void setListRtuRefer(List<RtuRefer> listRtuRefer) {
		this.listRtuRefer = listRtuRefer;
	}
}