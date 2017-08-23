package com.qiyu.service;

import java.util.List;
import java.util.Map;

import com.qiyu.bean.Activity;

/**
 * 
 * 
 * @author 维斯
 *	2016年10月20日	 下午6:12:52
 */
public interface IActivityService {
    /**
     * 发布活动
     * @author 维斯
     * @param map
     * @return
     * 2016年11月4日  下午3:25:04
     */

	void addActivity(Map<String, Object> map);
	 /**
     * 删除活动
     * @author 维斯
     * @param map
     * @return
     * 2016年11月4日  下午3:25:04
     */
	void delActivity(Map<String, Object> map);
	
	 /**
     * 修改活动
     * @author 维斯
     * @param map
     * @return
     * 2016年11月4日  下午3:25:04
     */
	void updateActivity(Map<String, Object> map);
	
	 /**
     * 获取活动详情
     */
	Activity getActivity(Map<String, Object> map);
	
	 /**
     * 置顶
     */
	
	void isTop(Map<String, Object> map);
	
	 /**
     * 报名
     */
	void isSignUp(Map<String, Object> map);
	
	 /**
     * 活动列表
     */
	List<Activity> getActivityList(Map<String, Object> map);
	
}
