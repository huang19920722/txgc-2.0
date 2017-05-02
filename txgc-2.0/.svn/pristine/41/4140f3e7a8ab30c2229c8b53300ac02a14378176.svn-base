package com.yp.sys.entity.rtu;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * TCyhcRtuSlaveProerty entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_cyhc_rtu_slave_proerty")
public class RtuSlaveProerty implements java.io.Serializable {

	// Fields

	/***/
	private static final long serialVersionUID = 303292469727793444L;
	private Long id;
	private Integer treeLevel;
	private Long treeId;
	private Long treePid;
	private String treeName;
	private String propertyKey;
	private String propertyName;
	private String propertyVal;
	private String propertyType;
	private Long slaveId;
	
	private int checkType;//1为选中,2为不选中

	// Constructors

	/** default constructor */
	public RtuSlaveProerty() {
	}

	/** minimal constructor */
	public RtuSlaveProerty(Long id) {
		this.id = id;
	}

	/** full constructor */
	public RtuSlaveProerty(Long id, Integer treeLevel, Long treeId,
			Long treePid, String treeName, String propertyKey,
			String propertyName, String propertyVal, String propertyType,
			Long slaveId) {
		this.id = id;
		this.treeLevel = treeLevel;
		this.treeId = treeId;
		this.treePid = treePid;
		this.treeName = treeName;
		this.propertyKey = propertyKey;
		this.propertyName = propertyName;
		this.propertyVal = propertyVal;
		this.propertyType = propertyType;
		this.slaveId = slaveId;
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

	@Column(name = "tree_level")
	public Integer getTreeLevel() {
		return this.treeLevel;
	}

	public void setTreeLevel(Integer treeLevel) {
		this.treeLevel = treeLevel;
	}

	@Column(name = "tree_id")
	public Long getTreeId() {
		return this.treeId;
	}

	public void setTreeId(Long treeId) {
		this.treeId = treeId;
	}

	@Column(name = "tree_pid")
	public Long getTreePid() {
		return this.treePid;
	}

	public void setTreePid(Long treePid) {
		this.treePid = treePid;
	}

	@Column(name = "tree_name", length = 50)
	public String getTreeName() {
		return this.treeName;
	}

	public void setTreeName(String treeName) {
		this.treeName = treeName;
	}

	@Column(name = "property_key", length = 50)
	public String getPropertyKey() {
		return this.propertyKey;
	}

	public void setPropertyKey(String propertyKey) {
		this.propertyKey = propertyKey;
	}

	@Column(name = "property_name", length = 50)
	public String getPropertyName() {
		return this.propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	@Column(name = "property_val", length = 100)
	public String getPropertyVal() {
		return this.propertyVal;
	}

	public void setPropertyVal(String propertyVal) {
		this.propertyVal = propertyVal;
	}

	@Column(name = "property_type", length = 50)
	public String getPropertyType() {
		return this.propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	@Column(name = "slave_id")
	public Long getSlaveId() {
		return this.slaveId;
	}

	public void setSlaveId(Long slaveId) {
		this.slaveId = slaveId;
	}
@Column(name="check_type")
	public int getCheckType() {
		return checkType;
	}

	public void setCheckType(int checkType) {
		this.checkType = checkType;
	}
	
	

}