package com.qiyu.serviceImpl;


import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qiyu.bean.Building;
import com.qiyu.dao.IBuildingDao;
import com.qiyu.dao.IUserDao;
import com.qiyu.service.IAdminService;
import com.qiyu.service.IBuildingService;
import com.qiyu.service.IUserService;
import com.qiyu.util.exception.BizException;
import com.qiyu.util.http.StringUtils;



@Service("buildingService")
@Transactional
public class BuildingServiceImpl  implements IBuildingService {

	private static Logger logger = LoggerFactory.getLogger(BuildingServiceImpl.class);
	@Autowired
	private IBuildingDao buildingDao;
	
	





	@Override
	public void updateBuilding(Map<String, Object> map) {
		String level=map.get("level")==null?null:map.get("level").toString();
		String initUpdate=map.get("initUpdate")==null?null:map.get("initUpdate").toString();
		
		
		if(StringUtils.isBlank(level)||level.equals("2")&&!initUpdate.equals("0")||!level.equals("1")&&!level.equals("2")){
			throw new BizException("430", "无权限修改大楼信息");
		}
		
		if(StringUtils.isBlank("id")){
			throw new BizException("430", "缺少选中大楼id");
		}
		buildingDao.updateBuilding(map);
		
	}
	
	@Override
	public Building getBuilding(Map<String, Object> map) {
		
		if(StringUtils.isBlank("id")){
			throw new BizException("430", "缺少选中大楼id");
		}
		buildingDao.getBuilding(map);
		return null;
		
	}

	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<>();
		map.put("level", 1);
		map.put("initUpdate", 0);
		
		 new BuildingServiceImpl().updateBuilding(map);
	}
}
