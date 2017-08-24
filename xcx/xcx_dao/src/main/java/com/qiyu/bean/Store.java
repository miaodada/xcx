package com.qiyu.bean;

import java.io.Serializable;

//店铺对象
public class Store implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id ; 
	private String buildingId ; // 大楼Id 
	private String name ; // 门店名
	private String formatType ; // 业态类型
	private String  floor ; // 楼层
	private String storeNo ; // 编号
	private String logo ; // 门店LOGO
	private String pic ; // 门店配图
	private String introduce ; // 门店介绍
	private String activityNum ; // 活动数
	private String signUpNum ; // 报名人数
	private String buildingName ; // 大楼名
	
	
	
	
	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	public String getActivityNum() {
		return activityNum;
	}
	public void setActivityNum(String activityNum) {
		this.activityNum = activityNum;
	}
	public String getSignUpNum() {
		return signUpNum;
	}
	public void setSignUpNum(String signUpNum) {
		this.signUpNum = signUpNum;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFormatType() {
		return formatType;
	}
	public void setFormatType(String formatType) {
		this.formatType = formatType;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getStoreNo() {
		return storeNo;
	}
	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	
	
	
	
}
