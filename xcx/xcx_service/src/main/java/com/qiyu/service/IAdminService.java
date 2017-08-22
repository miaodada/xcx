package com.qiyu.service;

import java.util.List;
import java.util.Map;

import com.qiyu.bean.Admin;

/**
 * 
 * 
 * @author 维斯
 *	2016年10月20日	 下午6:12:52
 */
public interface IAdminService {
    /**
     * 新增管理员
     * @author 维斯
     * @param map
     * @return
     * 2016年11月4日  下午3:25:04
     */

	void addAdmin(Map<String, Object> map);
	
	/**
     * 修改密码
     * @author 维斯
     * @param map
     * @return
     * 2016年11月4日  下午3:25:04
     */

	void updatePwd(Map<String, Object> map);
	/**
     * 删除管理员
     * @author 维斯
     * @param map
     * @return
     * 2016年11月4日  下午3:25:04
     */
	void delAdmin(Map<String, Object> map);
	/**
    * 修改管理员
    * @author 维斯
    * @param map
    * @return
    * 2016年11月4日  下午3:25:04
    */
	void updateAdmin(Map<String, Object> map);
	/**
	    * 管理员列表 按 level ：1展示所有大楼管理员   level:2 展示其下店铺管理员
	    * @author 维斯
	    * @param map
	    * @return
	    * 2016年11月4日  下午3:25:04
	    */
	List<Admin> getAdminList(Map<String, Object> map);
	/**
	    * 管理员登录
	    * @author 维斯
	    * @param map
	    * @return
	    * 2016年11月4日  下午3:25:04
	    */
	Admin loginAdmin(Map<String, Object> map);
	
}
