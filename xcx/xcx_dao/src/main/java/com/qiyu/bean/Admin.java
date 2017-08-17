package com.qiyu.bean;

import java.io.Serializable;

//权限用户对象
public class Admin implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id ; 
	
	private String name ; // 大楼名或店铺名
	private String level ; // 用户级别 1.超级管理员 2。大楼管理员 3.店铺管理员
	private String account ; // 帐号
	private String pwd ; // 密码
	private String buildingId ; // 大楼Id 超级管理员 为0
	private String storeId ; // 门店Id    超级管理员/大楼管理员为 0
	private String initUpdate ; // 初始化门店或大楼 信息 0 ，未初始化 1.初始化
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getInitUpdate() {
		return initUpdate;
	}
	public void setInitUpdate(String initUpdate) {
		this.initUpdate = initUpdate;
	}
	
	
	
	
	
	
}
