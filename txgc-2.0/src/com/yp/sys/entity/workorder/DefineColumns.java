package com.yp.sys.entity.workorder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TCyhcDefineColumns entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_cyhc_define_columns", catalog = "txgc")
public class DefineColumns implements java.io.Serializable {

	// Fields

	private Integer id;
	private String appNode;
	private String defineContent;
	private Integer userId;

	// Constructors

	/** default constructor */
	public DefineColumns() {
	}

	/** minimal constructor */
	public DefineColumns(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public DefineColumns(Integer id, String appNode, String defineContent,
			Integer userId) {
		this.id = id;
		this.appNode = appNode;
		this.defineContent = defineContent;
		this.userId = userId;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "app_node", length = 50)
	public String getAppNode() {
		return this.appNode;
	}

	public void setAppNode(String appNode) {
		this.appNode = appNode;
	}

	@Column(name = "define_content", length = 500)
	public String getDefineContent() {
		return this.defineContent;
	}

	public void setDefineContent(String defineContent) {
		this.defineContent = defineContent;
	}

	@Column(name = "user_Id")
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}