package com.qiyu.serviceImpl;


import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qiyu.bean.Admin;
import com.qiyu.dao.IAdminDao;
import com.qiyu.dao.IBuildingDao;
import com.qiyu.dao.IStoreDao;
import com.qiyu.dao.IUserDao;
import com.qiyu.service.IAdminService;
import com.qiyu.service.IUserService;
import com.qiyu.util.exception.BizException;
import com.qiyu.util.http.StringUtils;



@Service("adminService")
@Transactional
public class AdminServiceImpl  implements IAdminService {

	private static Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);
	@Autowired
	private IAdminDao adminDao;
	
	@Autowired
	private IBuildingDao buildingDao;
	@Autowired
	private IStoreDao storeDao;




	@Override
	@Transactional
	public void addAdmin(Map<String, Object> map) {
		String level=map.get("level")==null?null:map.get("level").toString();
		if(StringUtils.isBlank(level)||(!(level.equals("1")&&!level.equals("2")))){
			throw new BizException("430", "无法添加权限用户");
		}
		if(StringUtils.isBlank(map.get("account"))||StringUtils.isBlank(map.get("pwd"))||StringUtils.isBlank(map.get("name"))){
			throw new BizException("430", "未输入账号、密码或名字");
		}
		
		if(level.equals("1")){
			//添加用户，只能上级添加下级，level要加1
			map.put("level",2);
		}
		if(level.equals("2")){
			map.put("level",3);
		}
		try{
			int num = adminDao.getAccountAlready(map);
			
			if(num>0){
				throw new BizException("430", "名字已存在");
			}
			
			if(level.equals("1")){
				buildingDao.addBuilding(map);
				//map 返回大楼新增ID
				map.put("storeId",0);
				map.put("buildingId",map.get("id"));
				adminDao.addAdmin(map);
					
			}
			if(level.equals("2")){
				storeDao.addStore(map);
				//map 返回大楼新增ID
				map.put("storeId",map.get("id"));
				adminDao.addAdmin(map);
			}
			
		}catch (Exception e) {
			throw new BizException("430", "新增管理员失败");
		}
	}
	
	@Override
	public void updatePwd(Map<String, Object> map) {
		String level=map.get("level")==null?null:map.get("level").toString();
		if(StringUtils.isBlank(level)||(!(level.equals("1")&&!level.equals("2")))){
			throw new BizException("430", "无限权限修改用户密码");
		}
		if(StringUtils.isBlank(map.get("pwd"))){
			throw new BizException("430", "未输入密码");
		}
		if(StringUtils.isBlank(map.get("id"))){
			throw new BizException("430", "缺少选中用户id");
		}
		try{
			
			adminDao.updatePwd(map);
		}catch (Exception e) {
			throw new BizException("430", "修改密码失败");
		}
	}
	
	@Override
	public void delAdmin(Map<String, Object> map) {
		String level=map.get("level")==null?null:map.get("level").toString();
		if(StringUtils.isBlank(level)||(!(level.equals("1")&&!level.equals("2")))){
			throw new BizException("430", "无限权限删除用户");
		}
		
		if(StringUtils.isBlank(map.get("id"))){
			throw new BizException("430", "缺少选中用户id");
		}
		try{
			
			adminDao.delAdmin(map);
		}catch (Exception e) {
			throw new BizException("430", "修改密码失败");
		}
	}
	
	@Override
	public void updateAdmin(Map<String, Object> map) {
		
		
	}
	
	
	@Override
	public List<Admin> getAdminList(Map<String, Object> map) {
		String level=map.get("level")==null?null:map.get("level").toString();
		if(StringUtils.isBlank(level)||(!(level.equals("1")&&!level.equals("2")))){
			throw new BizException("430", "无限权限");
		}
		List<Admin> adminList = adminDao.getAdminList(map);
		
		
		return adminList;
		
		
	}
	
	@Override
	public Admin loginAdmin(Map<String, Object> map) {
		String level=map.get("level")==null?null:map.get("level").toString();
		if(StringUtils.isBlank(level)||(!(level.equals("1")&&!level.equals("2")))){
			throw new BizException("430", "无权限");
		}
		if(StringUtils.isBlank(map.get("account"))||StringUtils.isBlank(map.get("pwd"))){
			throw new BizException("430", "请输入正确的帐号和密码");
		}
		Admin Admin = adminDao.loginAdmin(map);
		
		if(Admin==null){
			throw new BizException("430", "请输入正确的帐号和密码");
		}
		
		return Admin;
		
		
	}
}
