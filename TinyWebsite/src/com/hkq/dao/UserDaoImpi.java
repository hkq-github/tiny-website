package com.hkq.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.sql.SQLException;
import java.sql.Types;

import com.hkq.model.User;

public class UserDaoImpi implements UserDao {
	@Override
	public boolean add(User user) {
		String sql = "INSERT INTO user VALUES(?, ?, ?, ?, ?, ?, ?, ?)"; 
		Object[] params = new Object[] {
				user.getId(), user.getSalt(), user.getPass(), user.isFreeze(),
				user.getName(), user.getEmail(), user.getSex(), user.getIntro()
		};
		int[] types = new int[] {
				Types.CHAR, Types.CHAR, Types.CHAR, Types.BIT, 
				Types.CHAR, Types.VARCHAR, Types.CHAR, Types.VARCHAR
		};
		int result = 0;
		try {
			result = DBUtil.executeUpdateWithNull(sql, params, types);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result == 1;
	}
	
	@Override
	public boolean delete(String id) {
		String sql = "DELETE FROM user WHERE id=?";
		int result = 0;
		try {
			result = DBUtil.executeUpdate(sql, new Object[] {id});
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result == 1;
	}
	
	@Override
	public boolean update(User user) {
		String sql = "UPDATE user SET salt=?, pass=?, freeze=?, name=?, email=?, sex=?, intro=? WHERE id=?";
		Object[] params = new Object[] {
				user.getSalt(), user.getPass(), user.isFreeze(),
				user.getName(), user.getEmail(), user.getSex(), user.getIntro(),
				user.getId() 
		};
		int[] types = new int[] {
				Types.CHAR, Types.CHAR, Types.BIT, 
				Types.CHAR, Types.VARCHAR, Types.CHAR, Types.VARCHAR,
				Types.CHAR
		};
		int result = 0;
		try {
			result = DBUtil.executeUpdateWithNull(sql, params, types);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result == 1;
	}
	
	@Override
	public User findById(String id) {
		String sql = "SELECT * FROM user WHERE id=?";
		List<User> result = search(sql, new Object[] {id});
		if(result.size() == 1) {
			return result.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<User> findAll() {
		String sql = "SELECT * FROM user";
		return search(sql, new Object[] {});
	}
	
	@Override
	public int findCount(String value, Type type, boolean isFuzzySearch) {
		String sql = "SELECT COUNT(*) num FROM user WHERE";
		if(type == Type.ID) {
			sql += " id like ?";
		} else if(type == Type.NAME) {
			sql += " name like ?";
		} else {
			sql += " email like ?";
		}
		if(isFuzzySearch) {
			value = addFuzzy(value);
		}
		
		List<Map<String, Object>> result = null;
		try {
			result = DBUtil.executeQuery(sql, new Object[] {value});
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(result == null || result.size() != 1) {
			return 0;
		} else {
			return (int)(long)result.get(0).get("num");
		}
	}

	@Override
	public List<User> findRange(String value, Type type, boolean isFuzzySearch, int offset, int limit) {
		String sql = "SELECT * FROM user WHERE";
		if(type == Type.ID) {
			sql += " id like ?";
		} else if(type == Type.NAME) {
			sql += " name like ?";
		} else {
			sql += " email like ?";
		}
		if(offset >= 0 && limit > 0) {
			sql += (" limit " + limit + " offset " + offset);
		}
		
		if(isFuzzySearch) {
			value = addFuzzy(value);
		}
		
		return search(sql, new Object[] {value});	
	}
	
	@Override
	public int userCount() {
		String sql = "SELECT COUNT(*) num FROM user";
		List<Map<String, Object>> result = null;
		try {
			result = DBUtil.executeQuery(sql, new Object[] {});
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(result == null || result.size() != 1) {
			return 0;
		}
		
		return (int)(long)result.get(0).get("num");
	}
	
	
	@Override
	public boolean idExist(String id) {
		String sql = "SELECT COUNT(*) AS num FROM user WHERE id = ?";
		List<Map<String, Object>> list = null;
		try {
			list = DBUtil.executeQuery(sql, new Object[] {id});
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(list == null) {
			return true;
		}
		
		long num = (long)(list.get(0).get("num"));
		return num > 0;
	}
	
	@Override
	public boolean emailExist(String email) {
		String sql = "SELECT COUNT(*) AS num FROM user WHERE email = ?";
		List<Map<String, Object>> list = null;
		try {
			list = DBUtil.executeQuery(sql, new Object[] {email});
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(list == null) {
			return true;
		}
		
		long num = (long)(list.get(0).get("num"));
		return num > 0;
	}
	
	/**
	 * 向一个字符串开始、结束之间加%。如果value为null或空字符串，返回%<br/>
	 * 如：abc 转换为 %abc%<br/>
	 */
	private String addFuzzy(String value) {
		if("".equals(value)) {
			return "%";
		} else {
			return "%" + value + "%";
		}
	}
	
	/**
	 * 对DBUtil.executeQuery()和toUsers()的封装
	 * 
	 * @return not null，未查找到数据时返回空List
	 */
	private List<User> search(String sql, Object[] params) {
		List<Map<String, Object>> table = null;
		try {
			table = DBUtil.executeQuery(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return toUsers(table);
	}
	
	/**
	 * 将record记录转化为User对象。
	 */
	private User toUser(Map<String, Object> record) {
		String id = toStringAndTrime(record.get("id"));
		String salt = toStringAndTrime(record.get("salt"));
		String pass = toStringAndTrime(record.get("pass"));
		boolean freeze = (boolean)record.get("freeze");
		String name =toStringAndTrime(record.get("name"));
		String email = toStringAndTrime(record.get("email"));
		String sex = toStringAndTrime(record.get("sex"));
		String intro = toStringAndTrime(record.get("intro"));
		
		return new User(id, salt, pass, freeze, name, email, sex, intro);
	}
	
	/**
	 * 将List<Map<String, Object>> 对象转化为List<User>对象
	 * 
	 * @return not null，如果table == null || table.size == 0，返回空List
	 */
	private List<User> toUsers(List<Map<String, Object>> table) {
		List<User> result = new ArrayList<>();
		if(table == null) {
			return result;
		}
		
		Iterator<Map<String, Object>> iter = table.iterator();
		while(iter.hasNext()) {
			result.add(toUser(iter.next()));
		}
		return result;
	}
	
	/**
	 * 将o强制转换为String，并调用String.trime()方法。返回null, 当o为null时。
	 */
	public String toStringAndTrime(Object o) {
		if(o == null) {
			return null;
		}
		return ((String) o).trim();
	}
}
