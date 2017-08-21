package com.qiyu.dao;

import java.util.Map;

/**
 * 员工表dao
 * 
 * @author 维斯
 *	2016年10月17日	 下午6:13:31
 * @param <Birth>
 */
public interface IAdminDao {
	
	/**
	 * 检测账号或名字是否存在（账号不能相同， level为3，不同大楼可以有重复名字）
 	 * 
	 */
	int getAccountAlready(Map<String, Object> map);
	
	/**
	 * 新增管理员
 	 * 
	 */
	void addAdmin(Map<String, Object> map);
	
	/**
	 * 修改密码
 	 * 
	 */
	void updatePwd(Map<String, Object> map);
	
	/**
	 * 修改管理员
 	 * 
	 */
	void updateAdmin(Map<String, Object> map);
	
	/**
	 * 删除管理员
 	 * 
	 */
	void delAdmin(Map<String, Object> map);
}
