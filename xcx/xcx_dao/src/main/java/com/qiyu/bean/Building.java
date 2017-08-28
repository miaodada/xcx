package com.qiyu.bean;

import java.io.Serializable;
import java.util.List;

//大楼对象
public class Building implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id ; 
	
	private String name ; // 大楼名
	private String shortName ; // 简称
	private String province ; // 省
	private String city ; // 市
	private String businessType ; // 商业类型
	private String acreage ; // 面积
	private String  businessTime ; // 开业时间
	private String startTime ; // 营业时间(开始)
	private String endTime ; // 营业时间（结束）
	private String address ; // 项目地址
	private String parkingLot ; // 车位
	private String atm ; // ATM
	private String informationDesk ; // 服务台
	private String stair ; // 楼梯
	private String staircase ; // 扶梯
	private String square ; // 外广场 	
	private String atrium ; // 内中庭
	private String roomOfMomAndInfant ; // 母婴室
	private String logo ; // 大楼LOGO
	private String pic ; // 大楼配图
	private String introduce ; // 商圈介绍
	private String activityNum ; // 活动数
	private String signUpNum ; // 报名人数
	private String storeNum ; // 门店数量
	
	private List<FileInfo> logoList ; // 大楼Logo集合
	private List<FileInfo> picList ; // 大楼配图集合
	
	
	
	
	
	
	public Long getId() {
		return id;
	}
	
	public String getStoreNum() {
		return storeNum;
	}

	public void setStoreNum(String storeNum) {
		this.storeNum = storeNum;
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
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public String getAcreage() {
		return acreage;
	}
	public void setAcreage(String acreage) {
		this.acreage = acreage;
	}
	public String getBusinessTime() {
		return businessTime;
	}
	public void setBusiness(String businessTime) {
		this.businessTime = businessTime;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getParkingLot() {
		return parkingLot;
	}
	public void setParkingLot(String parkingLot) {
		this.parkingLot = parkingLot;
	}
	public String getAtm() {
		return atm;
	}
	public void setAtm(String atm) {
		this.atm = atm;
	}
	public String getInformationDesk() {
		return informationDesk;
	}
	public void setInformationDesk(String informationDesk) {
		this.informationDesk = informationDesk;
	}
	public String getStair() {
		return stair;
	}
	public void setStair(String stair) {
		this.stair = stair;
	}
	public String getStaircase() {
		return staircase;
	}
	public void setStaircase(String staircase) {
		this.staircase = staircase;
	}
	public String getSquare() {
		return square;
	}
	public void setSquare(String square) {
		this.square = square;
	}
	public String getAtrium() {
		return atrium;
	}
	public void setAtrium(String atrium) {
		this.atrium = atrium;
	}
	public String getRoomOfMomAndInfant() {
		return roomOfMomAndInfant;
	}
	public void setRoomOfMomAndInfant(String roomOfMomAndInfant) {
		this.roomOfMomAndInfant = roomOfMomAndInfant;
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

	public List<FileInfo> getLogoList() {
		return logoList;
	}

	public void setLogoList(List<FileInfo> logoList) {
		this.logoList = logoList;
	}

	public List<FileInfo> getPicList() {
		return picList;
	}

	public void setPicList(List<FileInfo> picList) {
		this.picList = picList;
	}

	public void setBusinessTime(String businessTime) {
		this.businessTime = businessTime;
	}
	
	
}
