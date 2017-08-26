package com.qiyu.serviceImpl;


import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qiyu.bean.Activity;
import com.qiyu.bean.FileInfo;
import com.qiyu.dao.IActivityDao;
import com.qiyu.dao.IAdminDao;
import com.qiyu.service.IActivityService;
import com.qiyu.util.exception.BizException;
import com.qiyu.util.http.StringUtils;



@Service("activityService")
@Transactional
public class ActivityServiceImpl  implements IActivityService {

	private static Logger logger = LoggerFactory.getLogger(ActivityServiceImpl.class);
	
	@Autowired
	private IActivityDao activityDao;

	@Autowired
	private IAdminDao adminDao;



	@Override
	public void addActivity(Map<String, Object> map) {
		
		if(StringUtils.isBlank(map.get("level"))||!map.get("level").toString().equals(3)){
			throw new BizException("430", "只有门店才能发布活动");
		}
		
		if(StringUtils.isBlank(map.get("title"))){
			throw new BizException("430", "主题");
		}
		if(StringUtils.isBlank(map.get("startTime"))){
			throw new BizException("430", "开始时间");
		}
		if(StringUtils.isBlank(map.get("endTime"))){
			throw new BizException("430", "结束时间");
		}
		if(StringUtils.isBlank(map.get("phone"))){
			throw new BizException("430", "活动电话");
		}
		if(StringUtils.isBlank(map.get("reminderTime"))){
			throw new BizException("430", "提醒时间");
		}
		if(StringUtils.isBlank(map.get("introduce"))){
			throw new BizException("430", "活动介绍");
		}
		if(StringUtils.isBlank(map.get("tag"))){
			throw new BizException("430", "标签");
		}
		if(StringUtils.isBlank(map.get("cover"))){
			throw new BizException("430", "封面配图");
		}
		
		activityDao.addActivity(map);
		
		
	}
	
	@Override
	public void delActivity(Map<String, Object> map) {
		String level=map.get("level")==null?null:map.get("level").toString();
		if(StringUtils.isBlank(level)||(!level.equals("1")&&!level.equals("2"))){
			throw new BizException("430", "无限权限删除活动");
		}
		if(StringUtils.isBlank(map.get("id"))){
			throw new BizException("430", "缺少选中活动id");
		}
		activityDao.delActivity(map);
		
	}
	
	@Override
	public void updateActivity(Map<String, Object> map) {
		String level=map.get("level")==null?null:map.get("level").toString();
		if(StringUtils.isBlank(level)||(!level.equals("1")&&!level.equals("2"))){
			throw new BizException("430", "无限权限修改活动");
		}
		if(StringUtils.isBlank(map.get("id"))){
			throw new BizException("430", "缺少选中活动id");
		}
		activityDao.updateActivity(map);
		
	}
	
	@Override
	public Activity getActivity(Map<String, Object> map) {
		if(StringUtils.isBlank(map.get("id"))){
			throw new BizException("430", "缺少选中活动id");
		}
		Activity activity = activityDao.getActivity(map);
		if(activity==null){
			throw new BizException("440", "该活动不存在");
		}
		return activity;
		
	}
	
	@Override
	public void isTop(Map<String, Object> map) {
		if(StringUtils.isBlank(map.get("id"))){
			throw new BizException("430", "缺少选中活动id");
		}
		String level=map.get("level")==null?null:map.get("level").toString();
		if(StringUtils.isBlank(level)||(!level.equals("1")&&!level.equals("2"))){
			throw new BizException("430", "无限权");
		}
		try{
			map.put("isTop", 1);
		 activityDao.updateActivity(map);
		}catch(Exception e){
			throw new BizException("440", "置顶失败");
		}
		
	}
	
	@Override
	public void isSignUp(Map<String, Object> map){
		String userId = map.get("userId").toString();
		
		if(StringUtils.isBlank(map.get("id"))){
			throw new BizException("430", "缺少选中活动id");
		}
		Activity activity = activityDao.getActivity(map);
		if(activity ==null){
			throw new BizException("430", "该活动已过期");
		}
		String signUpIds = activity.getSignUpIds();
		if(!StringUtils.isBlank(signUpIds)){
			if(Pattern.matches("(^|^.*[^0-9]{1})("+userId+"{1})($|[^0-9]{1}.*$)", signUpIds)){
				return;
			}
		}
		map.put("signUpId", Long.valueOf(userId));
		activityDao.updateActivity(map);
		
	}
	
	
	@Override
	public List<Activity> getActivityList(Map<String, Object> map) {
		
		//type 1.过期；2.没过期
		String type=map.get("type")==null?null:map.get("type").toString();
		if(StringUtils.isBlank(type)){
			throw new BizException("430", "缺少参数type");
		}
		
		
		List<Activity> activityList = activityDao.getActivityList(map);

		return activityList;
		
	}
	
	
	
	@Override
	public Activity getActivityDetail(Map<String, Object> map) {
		String id=map.get("id")==null?null:map.get("id").toString();
		if(StringUtils.isBlank(id)){
			throw new BizException("430", "缺少参数id");
		}
		
		
		Activity activity = activityDao.getActivityDetail(map);
		
		if(activity==null){
			throw new BizException("430", "活动已结束");
		}
		if(activity.getSignUpNum()>0){
			String[] fileIds = activity.getSignUpIds()
							.substring(1, activity.getSignUpIds().length()-1)
							.replace(" ", "").split(",");
			map.put("fileIds", fileIds);
			List<FileInfo> getfile = adminDao.getfile(map);
			activity.setSignUpList(getfile);
		}

		return activity;
		
	}
	
	@Override
	public List<Activity> getUserActivityList(Map<String, Object> map) {
		String building=map.get("building")==null?null:map.get("building").toString();
		String userId=map.get("userId")==null?null:map.get("userId").toString();
		if(StringUtils.isBlank(building)){
			throw new BizException("430", "缺少参数building");
		}
		
		List<Activity> userActivityList = activityDao.getUserActivityList(map);
		
		for (Activity activity : userActivityList) {
			String signUpIds = activity.getSignUpIds();
			if(!StringUtils.isBlank(signUpIds)&&signUpIds.length()>2){
				if(Pattern.matches("(^|^.*[^0-9]{1})("+userId+"{1})($|[^0-9]{1}.*$)", signUpIds)){
					activity.setIsSsignUp("1");
				}else{
					activity.setIsSsignUp("0");
				}
			}
		}
		
		return userActivityList;
		
	}
	
	
	
	
	public static void main(String[] args) {
		String s= "xxxxx.cc";
		String substring = s.substring(s.lastIndexOf(".")+1,s.length());
		System.out.println(substring.toString());
	}
}


