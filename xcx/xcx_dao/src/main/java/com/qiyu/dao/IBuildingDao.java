package com.qiyu.dao;

import java.util.List;
import java.util.Map;

import com.qiyu.bean.Building;

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
	Building getBuilding(Map<String, Object> map);
	
	/**
	 * 大楼列表
 	 * 
	 */
	List<Building> getBuildingList(Map<String, Object> map);
	
	/**
	 * 大楼列表数量
 	 * 
	 */
	int getBuildingListNum(Map<String, Object> map);
}

