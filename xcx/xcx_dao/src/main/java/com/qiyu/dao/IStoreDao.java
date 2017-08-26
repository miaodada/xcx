package com.qiyu.dao;

import java.util.List;
import java.util.Map;

import com.qiyu.bean.Store;

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
	
	/**
	 * 修改门店
	 * @param map
	 */


	void updateStore(Map<String, Object> map);
	
	
	/**
	 * 门店详情
	 * @param map
	 */
	Store getStore(Map<String, Object> map);
	
	/**
	 * 门店列表
	 * @param map
	 */
	List<Store> getStoreList(Map<String, Object> map);
	/**
	 * 门店列表数量
	 * @param map
	 */
	int getStoreListNum(Map<String, Object> map);
}
