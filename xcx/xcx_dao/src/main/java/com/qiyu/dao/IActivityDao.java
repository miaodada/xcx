package com.qiyu.dao;

import java.util.List;
import java.util.Map;

import com.qiyu.bean.Activity;

/**
 * 员工表dao
 * 
 * @author 维斯
 *	2016年10月17日	 下午6:13:31
 * @param <Birth>
 */
public interface IActivityDao {
	
	
	/**
	 * 发布活动
 	 * 
	 */

	void addActivity(Map<String, Object> map);
	/**
	 * 删除活动
 	 * 
	 */
	void delActivity(Map<String, Object> map);
	/**
	 * 修改活动
 	 * 
	 */
	
	void updateActivity(Map<String, Object> map);
	/**
	 * 活动详情
 	 * 
	 */
	Activity getActivity(Map<String, Object> map);
	/**
	 * 活动列表
 	 * 
	 */
	
	List<Activity> getActivityList(Map<String, Object> map);
	
	 /**
     * 获取活动详（游客点击查看）
     */
	Activity getActivityDetail(Map<String, Object> map);
	
	/**
     * 活动列表（游客查看列表展示）
     */
	List<Activity> getUserActivityList(Map<String, Object> map);
	
	
}
