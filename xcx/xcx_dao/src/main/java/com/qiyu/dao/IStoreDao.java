package com.qiyu.dao;

import java.util.Map;

/**
 * 员工表dao
 * 
 * @author 维斯
 *	2016年10月17日	 下午6:13:31
 * @param <Birth>
 */
public interface IStoreDao {
	
	/**
	 * 新增门店
	 * @param map
	 */

	void addStore(Map<String, Object> map);
}
