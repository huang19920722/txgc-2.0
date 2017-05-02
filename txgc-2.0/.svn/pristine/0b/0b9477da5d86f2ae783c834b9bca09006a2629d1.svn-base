package com.yp.sys.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "redisuser")
public class RedisUser implements Serializable {  
    
    private static final long serialVersionUID = -6011241820070393952L;  
  
    private String id;  
      
    private String name;  
      
    private String password;  
  
    /** 
     * <br>------------------------------<br> 
     */  
    public RedisUser() {  
          
    }  
      
    /** 
     * <br>------------------------------<br> 
     */  
    public RedisUser(String id, String name, String password) {  
        super();  
        this.id = id;  
        this.name = name;  
        this.password = password;  
    }  
  
    /** 
     * 获得id 
     * @return the id 
     */  
    @Id
	@GeneratedValue(generator = "pk")
	@GenericGenerator(name = "pk", strategy = "com.yp.sys.util.IdGenerator")
    public String getId() {  
        return id;  
    }  
  
    /** 
     * 设置id 
     * @param id the id to set 
     */  
    public void setId(String id) {  
        this.id = id;  
    }  
  
    /** 
     * 获得name 
     * @return the name 
     */  
    @Column(name="name",length=50)
    public String getName() {  
        return name;  
    }  
  
    /** 
     * 设置name 
     * @param name the name to set 
     */  
    public void setName(String name) {  
        this.name = name;  
    }  
  
    /** 
     * 获得password 
     * @return the password 
     */ 
    @Column(name="password",length=50)
    public String getPassword() {  
        return password;  
    }  
  
    /** 
     * 设置password 
     * @param password the password to set 
     */  
    public void setPassword(String password) {  
        this.password = password;  
    }  
} 
