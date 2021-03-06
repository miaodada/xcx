package com.qiyu.dao;

import java.util.List;
import java.util.Map;

import com.qiyu.bean.FileInfo;
import com.qiyu.bean.User;

/**
 * 员工表dao
 * 
 * @author 维斯
 *	2016年10月17日	 下午6:13:31
 * @param <Birth>
 */
public interface IUserDao {
	
	/**
	 * 游客是否存在
 	 * 
	 */

	List<User> getUser(Map<String, Object> map);
	
	/**
	 * 新增用户
 	 * 
	 */

	void addUser(Map<String, Object> map);
	/**
	 * 修改游客
 	 * 
	 */
	void updateUser(Map<String, Object> map);

	/**
	    * 获取图片
	    * @author 维斯
	    * @param map
	    * @return
	    * 2016年11月4日  下午3:25:04
	    */
	List<FileInfo> getHeadImgs(Map<String, Object> map);
}
