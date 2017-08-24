package com.qiyu.serviceImpl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qiyu.bean.Building;
import com.qiyu.dao.IAdminDao;
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
	@Autowired
	private IAdminDao adminDao;
	
	





	@Override
	public void updateBuilding(Map<String, Object> map) {
		String level=map.get("level")==null?null:map.get("level").toString();
		String initUpdate=map.get("initUpdate")==null?null:map.get("initUpdate").toString();
		
		
		if(StringUtils.isBlank(level)||level.equals("2")&&!initUpdate.equals("0")||!level.equals("1")&&!level.equals("2")){
			throw new BizException("430", "无权限修改大楼信息");
		}
		//1J管理员修改需要大楼ID 2。第一次修改，取session
		if(StringUtils.isBlank("id")&&level.equals("1")){
			throw new BizException("430", "缺少选中大楼id");
		} else if(level.equals("2")){
			map.put("id", map.get("buildingId"));
		}
		map.put("storeId", 0);
		buildingDao.updateBuilding(map);
		adminDao.updateAdmin(map);
		
	}
	
	@Override
	public Building getBuilding(Map<String, Object> map) {
		
		if(StringUtils.isBlank("id")){
			throw new BizException("430", "缺少选中大楼id");
		}
		Building building =buildingDao.getBuilding(map);
		return building;
		
	}

	@Override
	public List<Building> getBuildingList(Map<String, Object> map) {
		
		String level = map.get("level")==null?null:map.get("level").toString();
		if(StringUtils.isBlank(level)||!level.equals("1")){
			throw  new BizException("430", "无权限");
		}
		List<Building> building =buildingDao.getBuildingList(map);
		return building;
		
	}
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		String s= "     1         ";
		int length = s.trim().length();
		
		 System.out.println(length);
	}
}
