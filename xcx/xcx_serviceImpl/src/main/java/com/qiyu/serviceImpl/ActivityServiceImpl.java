package com.qiyu.serviceImpl;


import java.util.ArrayList;
import java.util.HashMap;
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
import com.qiyu.dao.IUserDao;
import com.qiyu.service.IActivityService;
import com.qiyu.util.common.Pagination;
import com.qiyu.util.exception.BizException;
import com.qiyu.util.http.StringUtils;



@Service("activityService")
@Transactional
public class ActivityServiceImpl  implements IActivityService {

	private static Logger logger = LoggerFactory.getLogger(ActivityServiceImpl.class);
	
	@Autowired
	private IActivityDao activityDao;


	@Autowired
	private IUserDao userDao;

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
		if(StringUtils.isBlank(map.get("userId"))){
			throw new BizException("400", "请登陆");
		}
		

		String signUpIds = activityDao.getSignUpIds(map);
		if(!StringUtils.isBlank(signUpIds)){
			if(Pattern.matches("(^|^.*[^0-9]{1})("+userId+"{1})($|[^0-9]{1}.*$)", signUpIds)){
				return;
			}
		}else if (StringUtils.isBlank(signUpIds)){
			throw new BizException("430", "活动已过期");
		}
		map.put("signUpId", Long.valueOf(userId));
		activityDao.updateActivity(map);
		
	}
	
	
	@Override
	public List<Activity> getActivityList(Map<String, Object> map) {
		
		String level=map.get("level")==null?null:map.get("level").toString();
		if(StringUtils.isBlank(level)||(!level.equals("1")&&!level.equals("2"))){
			throw new BizException("430", "无限权");
		}
		
		//type 1.过期；2.没过期
		String type=map.get("type")==null?null:map.get("type").toString();
		if(StringUtils.isBlank(type)){
			throw new BizException("430", "缺少参数type");
		}
		Map<String,Object> resultMap = new HashMap<>();
		List<Activity> activityList = new ArrayList<>();
		
		int num = activityDao.getActivityListNum(map);
		
		Pagination pg=new Pagination(num,map.get("pageNum"),map.get("pageSize"));
		 map.put("pageSize", pg.getPageSize());
		 map.put("startRow", pg.getStartPos());
		
		if(num>0){
			 activityList = activityDao.getActivityList(map);
		}
		resultMap.put("list", activityList);
		resultMap.put("page", pg);	

		return activityList;
		
	}
	
	
	
	@Override
	public Activity getActivityDetail(Map<String, Object> map) {
		String id=map.get("id")==null?null:map.get("id").toString();
		if(StringUtils.isBlank(id)){
			throw new BizException("430", "缺少参数id");
		}
		if(!StringUtils.isBlank(map.get("level"))){
			throw new BizException("430", "请用游客账号");
		}
		
		
		Activity activity = activityDao.getActivityDetail(map);
		
		if(activity==null){
			throw new BizException("430", "活动已结束");
		}
		if(activity.getSignUpNum()!=null&&activity.getSignUpNum()>0){
			String[] userIds = activity.getSignUpIds()
							.substring(1, activity.getSignUpIds().length()-1)
							.replace(" ", "").split(",");
			//只展示前10个报名人的头像
			if(userIds!=null &&userIds.length>10){
				String[] tenIds =new String[10];
				System.arraycopy(userIds, 0, tenIds, 0, 9);
				map.put("userIds", tenIds);
			}else{
				
				map.put("userIds", userIds);
			}
			List<FileInfo> Imgs = userDao.getHeadImgs(map);
			activity.setSignUpList(Imgs);
		}

		return activity;
		
	}
	
	@Override
	public Map<String, Object> getUserActivityList(Map<String, Object> map) {
		String buildingId=map.get("buildingId")==null?null:map.get("buildingId").toString();
		String userId=map.get("userId")==null?null:map.get("userId").toString();
		if(StringUtils.isBlank(buildingId)){
			throw new BizException("430", "缺少参数buildingId");
		}
		
		Map<String,Object> resultMap = new HashMap<>();
		
		List<Activity> userActivityList = new ArrayList();
		//分页
		int num = activityDao.getUserActivityListNum(map);
		Pagination pg=new Pagination(num,map.get("pageNum"),map.get("pageSize"));
		 map.put("pageSize", pg.getPageSize());
		 map.put("startRow", pg.getStartPos());
		if(num>0){
			//列表
			userActivityList = activityDao.getUserActivityList(map);
			for (Activity activity : userActivityList) {
				String signUpIds = activity.getSignUpIds();
				if(!StringUtils.isBlank(signUpIds)&&signUpIds.length()>2&&!StringUtils.isBlank(userId)){
					if(Pattern.matches("(^|^.*[^0-9]{1})("+userId+"{1})($|[^0-9]{1}.*$)", signUpIds)){
						activity.setIsSsignUp("1");
					}else{
						activity.setIsSsignUp("0");
					}
				}else {
					activity.setIsSsignUp("0");
				}
			}
		}
		
		
		resultMap.put("list", userActivityList);
		resultMap.put("page", pg);	
		
		return resultMap;
		
	}
	
	
	
	
	public static void main(String[] args) {
		int[] s = {1,2,3,4,5,6,7,8,9,10,1,2,3,4};
		int[] ss= new int[10];
		System.arraycopy(s, 0, s, 0, 10);
		System.out.println(ss[9]);
		
	}
}


