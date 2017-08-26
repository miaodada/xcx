package com.qiyu.serviceImpl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qiyu.bean.Activity;
import com.qiyu.bean.Store;
import com.qiyu.dao.IActivityDao;
import com.qiyu.dao.IAdminDao;
import com.qiyu.dao.IBuildingDao;
import com.qiyu.dao.IStoreDao;
import com.qiyu.dao.IUserDao;
import com.qiyu.service.IAdminService;
import com.qiyu.service.IStoreService;
import com.qiyu.service.IUserService;
import com.qiyu.util.common.Pagination;
import com.qiyu.util.exception.BizException;
import com.qiyu.util.http.StringUtils;



@Service("storeService")
@Transactional
public class StoreServiceImpl  implements IStoreService {

	private static Logger logger = LoggerFactory.getLogger(StoreServiceImpl.class);
	@Autowired
	private IAdminDao adminDao;
	@Autowired
	private IStoreDao storeDao;
	





	@Override
	public void updateStore(Map<String, Object> map) {
		String level=map.get("level")==null?null:map.get("level").toString();
		String initUpdate=map.get("initUpdate")==null?null:map.get("initUpdate").toString();
		
		
		if(StringUtils.isBlank(level)||level.equals("3")&&!initUpdate.equals("0")||!level.equals("3")&&!level.equals("1")&&!level.equals("2")){
			throw new BizException("430", "无权限修改大楼信息");
		}
		//1J管理员修改需要大楼ID 2。第一次修改，取session
		if(StringUtils.isBlank("id")&&level.equals("1")){
			throw new BizException("430", "缺少选中门店id");
		} else if(StringUtils.isBlank("id")&&level.equals("2")){
			throw new BizException("430", "缺少选中门店id");
		}else if (level.equals("3")){
			map.put("id", map.get("storeId"));
		}
		storeDao.updateStore(map);
		adminDao.updateAdmin2(map);
		
		
	}






	@Override
	public Map<String, Object> getStoreList(Map<String, Object> map) {
		Map<String,Object> resultMap = new HashMap<>();
		String level = map.get("level")==null?null:map.get("level").toString();
		if(StringUtils.isBlank(level)||!level.equals("1")&&!level.equals("2")){
			throw  new BizException("430", "无权限");
		}
		
		
		int num = storeDao.getStoreListNum(map);
		 Pagination pg=new Pagination(num,map.get("pageNum"),map.get("pageSize"));
		 map.put("pageSize", pg.getPageSize());
		 map.put("startRow", pg.getStartPos());
		List<Store> storeList = new ArrayList();
		if(num>0){
			
			 storeList = storeDao.getStoreList(map);
		}
		resultMap.put("list", storeList);
		resultMap.put("page", pg);
		
		
		return resultMap;
		
	}






	@Override
	public Store getStore(Map<String, Object> map) {
		if(StringUtils.isBlank(map.get("id"))){
			throw new BizException("430", "缺少门店id");
		}
		
		
		Store store = storeDao.getStore(map);
		
		
		return store;
		
	}
	
	

}
