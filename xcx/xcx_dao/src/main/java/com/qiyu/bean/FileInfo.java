package com.qiyu.bean;

import java.io.Serializable;

public class FileInfo implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private Long id;
    
    private String url;

    /**
     * 类型
     */
    private String type;

	private String buildingId ; // 大楼Id 超级管理员 为0
	private String storeId ; // 门店Id    超级管理员/大楼管理员为 0
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
    
	
	

}