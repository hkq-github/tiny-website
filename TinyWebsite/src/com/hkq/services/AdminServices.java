package com.hkq.services;

import com.hkq.model.Admin;
import com.hkq.util.UserPaging;

/**
 * 与管理员相关的业务逻辑接口
 * 
 * @author hkq
 *
 */
public interface AdminServices {
	/**
	 * 根据管理员id查找管理员。若未找到返回null
	 */
	public Admin findAdmin(String id);
	
	/**
	 * 根据管理员的用户名和密码判断是否允许登录
	 * 
	 * @return 若允许登录返回null，否则返回出错信息
	 */
	public String allowAdminLogin(String id, String pass);
	
	/**
	 * 重置用户名id的密码为newPassword
	 * 
	 * @return 若修改成功返回null，否则返回出错信息
	 */
	public String resetUserPassword(String id, String newPass);
	
	/**
	 * 冻结用户名为id的用户
	 * 
	 * @return 若成功返回null，否则返回出错信息
	 */
	public String freezeUser(String id);
	
	/**
	 * 恢复用户名为id的用户
	 * 
	 * @return 若成功返回null，否则返回出错信息
	 */
	public String recoverUser(String id);
	
	/**
	 * 删除用户名为id的用户
	 * 
	 * @return 若成功返回null，否则返回出错信息
	 */
	public String deleteUser(String id);
	
	public static final int SEARCH_BY_ID = 0, SEARCH_BY_EMAIL = 1, SEARCH_BY_NAME = 2;
	/**
	 * 查找user表中pageNum页的数据。<br/>
	 * 如果pageSize < 1，引发RuntimeException异常
	 * 如果参数非法（如pageNum小于1或大于最大分页数), 返回第一页数据。<br/>
	 * 
	 * @param searchBy 根据用户名or邮箱or昵称搜索
	 * @param searchText 搜索的文本
	 * @param pageNum 1 <= pageNum <= 小于最大分页数
	 * @param pageSize pageSize >= 1
	 * @param isFuzzySearch 是否为模糊搜索
	 * 
	 * @exception RuntimeException 当pageSize < 1时
	 * 
	 * @return not null
	 */
	public UserPaging searchUser(int searchBy, String searchText, boolean isFuzzySearch, int pageNum, int pageSize);
	
	/**
	 * 查找user表中pageNum页的数据。<br/>
	 * 如果pageSize < 1，引发RuntimeException异常
	 * 如果参数非法（如pageNum小于1或大于最大分页数), 返回第一页数据。<br/>
	 * 
	 * @param pageNum 1 <= pageNum <= 小于最大分页数
	 * @param pageSize pageSize >= 1
	 * 
	 * @exception RuntimeException 当pageSize < 1时
	 * 
	 * @return not null
	 */
	public UserPaging searchAllUser(int pageNum, int pageSize);
	
	
}
