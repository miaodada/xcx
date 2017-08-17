package com.qiyu.service;

import java.util.Map;

/**
 * 
 * 
 * @author 维斯
 *	2016年10月20日	 下午6:12:52
 */
public interface IUserService {
    /**
     * 游客登陆 （判断是否新增，同步微信）
     * @author 维斯
     * @param map
     * @return
     * 2016年11月4日  下午3:25:04
     */

	Map<String, Object> loginUser(Map<String, Object> map);

	
}
