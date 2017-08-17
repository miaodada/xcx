package com.qiyu.serviceImpl.staff;


import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qiyu.service.staff.IUserService;
import com.qiyu.util.exception.BizException;
import com.qiyu.util.http.StringUtils;



@Service("userService")
@Transactional
public class UserServiceImpl  implements IUserService {

	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	
	
	





	@Override
	public void addUser(Map<String, Object> map) {
		String level=map.get("level")==null?null:map.get("level").toString();
		if(StringUtils.isBlank(level)||!(level.equals("1")||!level.equals("2"))){
			throw new BizException("430", "无法添加权限用户");
		}
		if(StringUtils.isBlank(map.get("account"))||StringUtils.isBlank(map.get("pwd"))||StringUtils.isBlank(map.get("name"))){
			throw new BizException("430", "未输入账号、密码或名字");
		}
		
	}
}
