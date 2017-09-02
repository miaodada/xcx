package com.qiyu.dao;

import java.util.List;
import java.util.Map;

import com.qiyu.bean.Admin;
import com.qiyu.bean.FileInfo;

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
	
	void updateAdmin2(Map<String, Object> map);
	/**
	 * 删除管理员
 	 * 
	 */
	void delAdmin(Map<String, Object> map);

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
	
	/**
	    * 保存图片
	    * @author 维斯
	    * @param map
	    * @return
	    * 2016年11月4日  下午3:25:04
	    */

	void saveFileInfo(FileInfo fileInfo);

	
	/**
	    * 管理员列表数量
	    * @author 维斯
	    * @param map
	    * @return
	    * 2016年11月4日  下午3:25:04
	    */
	int getAdminListNum(Map<String, Object> map);
	


}
