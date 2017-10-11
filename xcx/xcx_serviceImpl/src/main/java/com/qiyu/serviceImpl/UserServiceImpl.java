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

import com.alibaba.fastjson.JSONObject;
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
			String urlStr = "https://api.weixin.qq.com/sns/jscode2session?appid=wx20587440d6232a35&secret=f5e4c27ca3ec5079b7bb6d3b2791f91f&"
							+ "js_code="+map.get("token").toString()+"&grant_type=authorization_code";
			String doGet = StringUtils.doGet(urlStr);
			JSONObject jsonObject = JSONObject.parseObject(doGet);
			if(!StringUtils.isBlank(jsonObject.get("errcode"))){
				throw new BizException("430", "调问微信接口失败");
			}
			map.put("token", jsonObject.getString("openid")) ;  
			resultMap.put("sessionKey", jsonObject.getString("session_key")) ;   
			resultMap.put("token", jsonObject.getString("openid")) ;   
			
		
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
	
	public static void main(String[] args) {
		UserServiceImpl u = new UserServiceImpl();
		Map<String,Object> map = new HashMap<>();
		map.put("name", "123123");
		map.put("token", "123123");
		u.loginUser(map);
	}
}
