package com.qiyu.serviceImpl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qiyu.bean.User;
import com.qiyu.dao.IUserDao;
import com.qiyu.service.IUserService;
import com.qiyu.util.exception.BizException;
import com.qiyu.util.http.StringUtils;



@Service("userService")
@Transactional
public class UserServiceImpl  implements IUserService {

	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private IUserDao userDao;
	
	





	@Override
	public Map<String, Object> loginUser(Map<String, Object> map) {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		if(StringUtils.isBlank(map.get("token"))){
			throw new BizException("430", "缺少token");
		}
		if(StringUtils.isBlank(map.get("name"))){
			throw new BizException("430", "缺少name");
		}
		try{
			
		
			 List<User> users = userDao.getUser(map);
			
			if(users!=null&&users.size()>0){ //表示用户存在，同步微信信息，然后登陆成功
				
				for (User user : users) {
					
					if(StringUtils.isBlank(user.getId())){
						throw new BizException("430", "数据错误");
					}
					
					resultMap.put("id", user.getId());
					resultMap.put("name", map.get("name"));
					resultMap.put("headImg", map.get("headImg")==null?"":map.get("headImg").toString());
					//同步微信信息
					map.put("userId", user.getId());
					userDao.updateUser(map);
					
					return resultMap;
				}
				
				
			}else{//新增用户，然后登陆成功
				userDao.addUser(map);
				
				resultMap.put("id", map.get("id"));
				resultMap.put("name", map.get("name"));
				resultMap.put("headImg", map.get("headImg")==null?"":map.get("headImg").toString());
				
				return resultMap;
			}
		}catch (Exception e) {
			throw new BizException("430", "授权登陆失败");
		}
		
		return resultMap;
		
	}
}
