package com.yp.sys.entity.rtu;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_cyhc_rtu_property_refer")
public class RtuPropertyRefer implements java.io.Serializable {
	private static final long serialVersionUID = 2452155080542465427L;
	private Long id; //主键,
	private String propertyKey;//属性英文表示,
	private String propertyText;//属性描述
	
	public RtuPropertyRefer(){
		
	}
	public RtuPropertyRefer(Long id){
		this.id=id;
	}
	public RtuPropertyRefer(Long id,String propertyKey,String propertyText){
		this.id=id;
		this.propertyKey=propertyKey;
		this.propertyText=propertyText;
	}
	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(generator = "pk")
	@GenericGenerator(name = "pk", strategy = "com.yp.sys.util.IdGenerator")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "property_key")
	public String getPropertyKey() {
		return propertyKey;
	}
	public void setPropertyKey(String propertyKey) {
		this.propertyKey = propertyKey;
	}
	@Column(name = "property_text")
	public String getPropertyText() {
		return propertyText;
	}
	public void setPropertyText(String propertyText) {
		this.propertyText = propertyText;
	}
	
}
