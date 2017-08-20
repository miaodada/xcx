package com.qiyu.serviceImpl;


import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qiyu.bean.Activity;
import com.qiyu.dao.IActivityDao;
import com.qiyu.service.IActivityService;
import com.qiyu.util.exception.BizException;
import com.qiyu.util.http.StringUtils;



@Service("activityService")
@Transactional
public class ActivityServiceImpl  implements IActivityService {

	private static Logger logger = LoggerFactory.getLogger(ActivityServiceImpl.class);
	
	@Autowired
	private IActivityDao activityDao;





	@Override
	public void addActivity(Map<String, Object> map) {
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
		if(StringUtils.isBlank(level)||(!(level.equals("1")&&!level.equals("2")))){
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
		if(StringUtils.isBlank(level)||(!(level.equals("1")&&!level.equals("2")))){
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
}


