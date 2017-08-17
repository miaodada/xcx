package com.qiyu.bean;

import java.io.Serializable;
import java.util.List;

//活动对象
public class Activity implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id ; 
	
	private String title ; // 主题
	private String buildingId ; // 大楼Id 
	private String storeId ; // 门店Id 
	private String startTime ; // 开始时间
	private String endTime ; // 结束时间
	private String  phone ; // 活动电话
	private String reminderTime ; // 提醒时间
	private String createTime ; // 创建时间
	private String pic ; // 活动配图
	private String introduce ; // 活动介绍
	private String tag ; // 标签
	private String cover ; // 封面配图
	private String signUpNum ; // 报名人数
	private String isSsignUp ; // 是否报名
	private List<FileInfo> picList ; // 活动配图集合
	private List<FileInfo> coverList ; // 封面配图集合
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getReminderTime() {
		return reminderTime;
	}
	public void setReminderTime(String reminderTime) {
		this.reminderTime = reminderTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
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
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public String getSignUpNum() {
		return signUpNum;
	}
	public void setSignUpNum(String signUpNum) {
		this.signUpNum = signUpNum;
	}
	public String getIsSsignUp() {
		return isSsignUp;
	}
	public void setIsSsignUp(String isSsignUp) {
		this.isSsignUp = isSsignUp;
	}
	public List<FileInfo> getPicList() {
		return picList;
	}
	public void setPicList(List<FileInfo> picList) {
		this.picList = picList;
	}
	public List<FileInfo> getCoverList() {
		return coverList;
	}
	public void setCoverList(List<FileInfo> coverList) {
		this.coverList = coverList;
	}
	
	
	
	
}
