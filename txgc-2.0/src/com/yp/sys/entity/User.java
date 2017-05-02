
package com.yp.sys.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonManagedReference;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;

import com.yp.sys.common.BaseEntity;
import com.yp.sys.entity.organization.Organization;

/**
 * 
* @ClassName: User 
* @Description: 用户实体 
* @author huangmx
* @date 2017年5月2日 上午11:06:41 
* @version V1.0
 */
@Entity
@Table(name="SYS_USER")
@Where(clause="recordStatus='Y'")
@Inheritance(strategy=InheritanceType.JOINED)
public class User extends BaseEntity{
	/***/
	private static final long serialVersionUID = 8453704950404240438L;
	
	private String loginAcct;

	private String mobile;
	private String email;
	private String password;
	
	private String nickName;//姓名
	private String sex;//性别编码
	/** 审批状态ID*/
	private String statusId;
	/** 审批状态名字*/
	private String statusName;

	
	private String type;
	private List<Role> roles;//用户拥有的角色
	private Organization organization;//所属机构
	private String content;
	
	private Organization createOrg;//创建机构
	
	/**头像*/
	private String img;
	
	@Column(length=32)
	public String getLoginAcct() {
		return loginAcct;
	}

	public void setLoginAcct(String loginAcct) {
		this.loginAcct = loginAcct;
	}
	@Column(length=32)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@Column(length=20)
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	@Column(length=10)
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name="orgId")
	public Organization getOrganization() {
		return organization;
	}
	
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name="create_org_id")
	public Organization getCreateOrg() {
		return createOrg;
	}

	public void setCreateOrg(Organization createOrg) {
		this.createOrg = createOrg;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JsonManagedReference
	@JoinTable(name = "sys_user_to_role", joinColumns = {@JoinColumn(name = "SYUSER_ID") }, inverseJoinColumns = { @JoinColumn(name = "SYROLE_ID") })
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	@Column(length=20)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	@Column(length=20)
	public String getStatusId() {
		return statusId;
	}
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	@Column(length=20)
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	@Column(length=20)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Column(length=30)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	@Column(length=200)
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	@Column(length=200)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}