package com.qiyu.service;

import java.util.List;
import java.util.Map;

import com.qiyu.bean.Store;

/**
 * 
 * 
 * @author 维斯
 *	2016年10月20日	 下午6:12:52
 */
public interface IStoreService {
	
	//修改门店
	void updateStore(Map<String, Object> map);
	
	//门店列表
	List<Store> getStoreList(Map<String, Object> map);
	
	//门店详情
	Store getStore(Map<String, Object> map);
}
