package com.qiyu.serviceImpl;


import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qiyu.dao.IBuildingDao;
import com.qiyu.dao.IUserDao;
import com.qiyu.service.IAdminService;
import com.qiyu.service.IStoreService;
import com.qiyu.service.IUserService;
import com.qiyu.util.exception.BizException;
import com.qiyu.util.http.StringUtils;



@Service("storeService")
@Transactional
public class StoreServiceImpl  implements IStoreService {

	private static Logger logger = LoggerFactory.getLogger(StoreServiceImpl.class);
	@Autowired
	private IBuildingDao accountDao;
	
	





	@Override
	public void addAdmin(Map<String, Object> map) {
		
		
		
	}
}
