package com.qiyu.dao;

import java.util.Map;

/**
 * 员工表dao
 * 
 * @author 维斯
 *	2016年10月17日	 下午6:13:31
 * @param <Birth>
 */
public interface IBuildingDao {
	
	/**
	 * 添加大楼
 	 * 
	 */

	void addBuilding(Map<String, Object> map);
	/**
	 * 修改大楼
 	 * 
	 */
	void updateBuilding(Map<String, Object> map);
	
	/**
	 * 详情大楼
 	 * 
	 */
	void getBuilding(Map<String, Object> map);
}

