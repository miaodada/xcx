package com.qiyu.service;

import java.util.Map;

import com.qiyu.bean.Building;

/**
 * 
 * 
 * @author 维斯
 *	2016年10月20日	 下午6:12:52
 */
public interface IBuildingService {
    /**
     * 修改大楼
     */

	void updateBuilding(Map<String, Object> map);
	 /**
     * 大楼详情
     */
	Building getBuilding(Map<String, Object> map);
	
}













