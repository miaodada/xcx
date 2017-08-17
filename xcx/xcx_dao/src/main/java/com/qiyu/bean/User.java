package com.qiyu.bean;

import java.io.Serializable;

//游客对象
public class User implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id ; 
	
	private String name ;
	
	private String token ; //微信授权码
	private String headImg ; //头像
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
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	
	
	
}
