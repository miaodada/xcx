package com.qiyu.bean;

import java.io.Serializable;

public class FileInfo implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private Long id;
    
    private String url;


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

    
	
	

}