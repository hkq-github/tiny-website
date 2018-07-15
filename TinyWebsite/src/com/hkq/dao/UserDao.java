package com.hkq.dao;

import java.util.List;
import com.hkq.model.User;

/**
 * user表数据访问层接口
 * 
 * @author hkq
 *
 */
public interface UserDao {
	/**
	 * 向数据库添加user记录，返回添加是否成功
	 */
	public boolean add(User user);
	
	/**
	 * 从数据库中删除主键为id的记录，返回删除是否成功
	 */
	public boolean delete(String id);
	
	/**
	 * 更新数据库中user记录的信息，返回更新是否成功
	 */
	public boolean update(User user);
	
	/**
	 * 根据主键id查找用户，未找到返回null。
	 */
	public User findById(String id);
	
	/**
	 * 查找数据库中user表的所有记录
	 * 
	 * @return not null，若无记录返回空List
	 */
	public List<User> findAll();
	
	/**
	 * 用户名、昵称、邮箱
	 */
	public static enum Type {ID, NAME, EMAIL};
	
	/** 
	 * 根据用户名、昵称、邮箱查找满足条件的记录数
	 * 
	 * @param isFuzzySezrch 是否为模糊查找 
	 * @param type 根据什么来查找？用户名、邮箱、昵称
	 * 
	 * @return not null，若无记录返回空List
	 */
	public int findCount(String value, Type type, boolean isFuzzySearch);
	
	/**
	 * 根据用户名or昵称or邮箱查找满足条件的指定范围的记录<br/>
	 * offset >= 0且limit > 0，否则等价于find(value, type. isFuzzySearch)
	 * 
	 * @param isFuzzySearch 是否为模糊查找
	 * @param type 根据什么来查找？用户名、邮箱、昵称
	 * @param offset 跳过offset条记录 offset >= 0
	 * @param limit 返回limit条记录 limit > 0
	 * 
	 * @return not null，若无记录返回空List
	 */
	public List<User> findRange(String value, Type type, boolean isFuzzySearch, int offset, int limit);
	
	/**
	 * 返回user表的记录数
	 */
	public int userCount();
	
	/*============ 用户id和邮箱必须唯一 ========= */
	/**
	 * 用户名id是否已经存在
	 */
	public boolean idExist(String id);
	
	/**
	 * 邮箱email是否已经存在
	 */
	public boolean emailExist(String email);
}
