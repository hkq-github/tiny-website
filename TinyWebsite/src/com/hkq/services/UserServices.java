package com.hkq.services;

import com.hkq.model.User;

/**
 * 与用户相关的业务逻辑接口
 * 
 * @author hkq
 *
 */

public interface UserServices {
	/**
	 * 根据用户名id查找用户。若未找到返回null
	 */
	public User findUser(String id);
	
	/**
	 * 根据用户名和密码判断是否允许登录
	 * 
	 * @return 若允许登录返回null，否则返回出错信息
	 */
	public String allowUserLogin(String id, String pass);
	
	/**
	 * 生成该用户自动登录的Cookie的值
	 * 
	 * @return 若用户不存在或该用户已经被冻结，返回null
	 */
	public String getAutoLoginCookieValue(String id);
	
	/**
	 * 根据Cookie的值判断是否允许自动登录，返回该用户的id
	 * 
	 * @return 允许登录返回该用户id，否则返回null
	 */
	public String allowAutoLogin(String cookieValue);
	
	/**
	 * 修改id的密码
	 * 
	 * @return 若修改成功返回null，否则返回出错信息
	 */
	public String changeUserPassword(String id, String oldPass, String newPass);
	
	/**
	 * 修改id的昵称、邮箱、简介。<br/>
	 * 
	 * @return 若修改成功返回null，否则返回出错信息
	 */
	public String changeUserInfo(String id, String newName, String newEmail, String newIntro);
	
	/**
	 * 注册
	 * 
	 * @return 若注册成功返回null，否则返回出错信息
	 */
	public String signup( String id, String pass, String name, String emial, String sex, String intro);
}
