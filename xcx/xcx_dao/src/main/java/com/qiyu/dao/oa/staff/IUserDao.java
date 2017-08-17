package com.qiyu.dao.oa.staff;

import java.util.Map;

/**
 * 员工表dao
 * 
 * @author 维斯
 *	2016年10月17日	 下午6:13:31
 * @param <Birth>
 */
public interface IUserDao {
	
	/**
	 * 检测账号是否存在
	 * 
	 */
	int getAccountAlready(Map<String, Object> map);
}
