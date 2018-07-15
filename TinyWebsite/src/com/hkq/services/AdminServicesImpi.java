package com.hkq.services;

import java.util.ArrayList;
import java.util.List;

import com.hkq.dao.AdminDao;
import com.hkq.dao.AdminDaoImpi;
import com.hkq.dao.UserDao;
import com.hkq.dao.UserDao.Type;
import com.hkq.dao.UserDaoImpi;
import com.hkq.model.Admin;
import com.hkq.model.User;
import com.hkq.util.DigestUtils;
import com.hkq.util.UserPaging;

/**
 * AdminServices接口实现类
 * 
 * @author hkq
 *
 */

public class AdminServicesImpi implements AdminServices {
	
	private AdminDao adminDao = new AdminDaoImpi();
	private UserDao userDao = new UserDaoImpi();
	
	@Override
	public Admin findAdmin(String id) {
		return adminDao.findById(id);
	}

	@Override
	public String allowAdminLogin(String id, String pass) {
		Admin admin = findAdmin(id);
		if(admin == null) {
			return "该用户不存在";
		}
		
		if(admin.getPass().equals(pass)) {
			return null;
		} else {
			return "密码错误";
		}
	}

	@Override
	public String resetUserPassword(String id, String newPass) {
		User user = userDao.findById(id);
		if(user == null) {
			return "用户不存在";
		}
		
		String digest = DigestUtils.sha256Digest(user.getSalt() + newPass);
		user.setPass(digest);
		if(digest == null || userDao.update(user) == false) {
			return "重置密码失败，请重试";
		} else {
			return null;
		}	
	}

	@Override
	public String freezeUser(String id) {
		User user = userDao.findById(id);
		if(user == null) {
			return "用户不存在";
		}
		
		if(user.isFreeze()) {
			return "该用户已经被冻结，未执行任何操作";
		}
		
		user.setFreeze(true);
		if(userDao.update(user)) {
			return null;
		} else {
			return "冻结用户失败，请重试";
		}	
	}

	@Override
	public String recoverUser(String id) {
		User user = userDao.findById(id);
		if(user == null) {
			return "用户不存在";
		}
		
		if(!user.isFreeze()) {
			return "该用户未被冻结，未执行任何操作";
		}
		
		user.setFreeze(false);
		if(userDao.update(user)) {
			return null;
		} else {
			return "恢复用户失败，请重试";
		}	
	}

	@Override
	public String deleteUser(String id) {
		if(userDao.delete(id)) {
			return null;
		} 
		return "删除用户失败，请重试";
	}

	@Override
	public UserPaging searchUser(int searchBy, String searchText, boolean isFuzzySearch, int pageNum, int pageSize) {
		if(pageSize < 1) {
			throw new RuntimeException("分页大小为" + pageSize + "，至少应为1");
		}
		
		UserDao.Type type = Type.ID;
		if(searchBy == SEARCH_BY_ID ) {
			type = Type.ID;
		} else if(searchBy == SEARCH_BY_NAME) {
			type = Type.NAME;
		} else if(searchBy == SEARCH_BY_EMAIL) {
			type = Type.EMAIL;
		}
		
		int totalRecord = userDao.findCount(searchText, type, isFuzzySearch);
		if(totalRecord == 0) { // 表中没有一条记录
			return new UserPaging(0, 0, pageSize, -1, new ArrayList<>());
		}
		
		// 总分页数为totalRecord / pageSie 向上取整
		int totalPage = totalRecord / pageSize;
		if(totalRecord % pageSize != 0) {
			totalPage++;
		}
		if(pageNum < 1 || pageNum > totalPage) {
			pageNum = 1;
		}
		
		int offset = (pageNum - 1) * pageSize;
		List<User> data = null;
		
		data = userDao.findRange(searchText, type, isFuzzySearch, offset, pageSize);
		return new UserPaging(totalRecord, totalPage, pageSize, pageNum, data);
	}
	
	@Override
	public UserPaging searchAllUser(int pageNum, int pageSize) {
		return searchUser(SEARCH_BY_ID, "", true, pageNum, pageSize);
	}
}
