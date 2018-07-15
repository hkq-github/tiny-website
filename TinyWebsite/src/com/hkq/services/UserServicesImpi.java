package com.hkq.services;

import com.hkq.dao.UserDao;
import com.hkq.dao.UserDaoImpi;
import com.hkq.model.User;
import com.hkq.util.DigestUtils;
import com.hkq.util.ValidateUserInfo;

import java.util.Random;
/**
 * UserServices接口实现类
 * 
 * @author hkq
 */

public class UserServicesImpi implements UserServices {
	private static final int saltLength = 16;
	private UserDao userDao = new UserDaoImpi();
	
	@Override
	public User findUser(String id) {
		return userDao.findById(id);
	}

	@Override
	public String allowUserLogin(String id, String pass) {
		User user = userDao.findById(id);
		if(user == null) {
			return "用户不存在";
		}
		
		if(user.isFreeze()) {
			return "该账户已被冻结，请联系管理员";
		}
		
		String passDigest = DigestUtils.sha256Digest(user.getSalt() + pass);
		if(user.getPass().equals(passDigest)) {
			return null;
		} else {
			return "用户名或密码错误";
		}
	}
	
	@Override
	public String getAutoLoginCookieValue(String id) {
		User user = findUser(id);
		if(user == null || user.isFreeze()) {
			return null;
		}
		
		String digest = DigestUtils.sha256Digest(user.getSalt() + id);
		if(digest == null) {
			return null;
		} else {
			return digest + "@" + id;
		}
	}

	@Override
	public String allowAutoLogin(String cookieValue) {
		if(cookieValue == null) {
			return null;
		}
		String[] mess = cookieValue.split("@");
		if(mess.length != 2) {
			return null;
		}
		String id = mess[1];
		
		String newCookieValue = getAutoLoginCookieValue(id);
		if(newCookieValue == null || !newCookieValue.equals(cookieValue) ) {
			return null;
		} else {
			return id;
		}
	}
	
	@Override
	public String changeUserPassword(String id, String oldPass, String newPass) {
		User user = findUser(id);
		if(user == null) {
			return "用户不存在";
		}
		
		String passDigest = DigestUtils.sha256Digest(user.getSalt() + oldPass);
		if(!passDigest.equals(user.getPass()) ) {
			return "当前密码不正确";
		}
		
		String mess = ValidateUserInfo.validatePassword(newPass);
		if(mess != null) {	// 新密码格式不正确
			return mess;
		}
		
		String salt = randomString(saltLength);	
		String newPassDigest = DigestUtils.sha256Digest(salt + newPass);
		user.setSalt(salt);
		user.setPass(newPassDigest);
		
		if(userDao.update(user)) {
			return null;
		} else {
			return "出错了";
		}
	}
	
	@Override
	public String changeUserInfo(String id, String newName, String newEmail, String newIntro) {
		User user = userDao.findById(id);
		String temp;
		if(user == null) {
			return "用户不存在";
		}
		
		// 验证邮箱
		if((temp = ValidateUserInfo.validateEmail(newEmail)) != null) {
			return temp;
		}
		if(!user.getEmail().equals(newEmail) && userDao.emailExist(newEmail)) {
			return "该邮箱已被注册";
		}	
		// 验证用户名
		if((temp = ValidateUserInfo.validateName(newName)) != null) {
			return temp;
		} 
		// 验证intro
		if((temp = ValidateUserInfo.validateIntro(newIntro)) != null) {
			return temp;
		}
		
		user.setName(newName);
		user.setEmail(newEmail);
		user.setIntro(newIntro);
		if(userDao.update(user)) {
			return null;
		} else {
			return "出错了！！！";
		}
	}

	@Override
	public String signup(String id, String pass, String name, String email, String sex, String intro) {
		String mess = ValidateUserInfo.validate(id, pass, name, email, sex, intro);
		if(mess != null) {
			return mess;
		}
		
		if(userDao.idExist(id)) {
			return "用户名" + id + "已经被注册";
		}
		if(userDao.emailExist(email)) {
			return "邮箱" + email + "已经被注册";
		}
		
		String salt = randomString(saltLength);
		String passDigest = DigestUtils.sha256Digest(salt + pass);
		
		User user = new User(id, salt, passDigest, false, name, email, sex, intro);
		if(userDao.add(user)) {
			return null;
		} else {
			return "添加出错了";
		}
	}
	
	/**
	 * 生成长度为n的随机字符串
	 */
	private String randomString(int n) {
		String base = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ~!@#$%^&*()_+=<>/,./;'[]{}|\\\\";
		int length = base.length();
		
		char[] buff = new char[n];
		Random r = new Random();
		for(int i = 0; i < n; i++) {
			buff[i] = base.charAt(r.nextInt(length));
		}
		
		return new String(buff);
	}

	
	
//	public static void main(String[] args) {
//		UserServices services = new UserServicesImpi();
		/* signup */
//		System.out.println(services.signup("T001", "", "", "", "", ""));
//		System.out.println(services.signup("T001", null, null, null, null, null));
//		System.out.println(services.signup("T001", "1234eeee", "fsdjflsjlsdjfld", "apple", "lala", "jianjie"));
//		System.out.println(services.signup("T001", "123pass{}", "Apple", "Apple@qq.com", "男", "介绍"));
//		System.out.println(services.signup("T002", "123pass{}", "Banana","Banana@souhu.com", "女", "BananaBanana"));
//		System.out.println(services.signup("T003", "123pass{}", "小红","xiaohong@qq.com", "女", "xiaohongxiaohong"));
		/* allowUserLogin */
//		System.out.println(services.allowUserLogin("T001", "123pass{}"));
//		System.out.println(services.allowUserLogin("T004", "123pass{}"));
//		System.out.println(services.allowUserLogin("T001", "12345678"));
		
//		System.out.println(services.changeUserPassword("T001", "sss", "XXX"));
//		System.out.println(services.changeUserPassword("nouser", "sss", "sss"));
//		System.out.println(services.changeUserPassword("T001", "123pass{}", "fdsfffffff"));
//		System.out.println(services.changeUserPassword("T001", "123pass{}", "123456p{}"));
		
//		System.out.println(services.allowUserLogin("T001", "123456p{}"));
	
//		System.out.println(services.changeUserInfo("nouser", "", "", ""));
//		System.out.println(services.changeUserInfo("T001", "dfdf", "xiaohong@qq.com", "newnewnww"));
//		System.out.println(services.changeUserInfo("T001", "dfdf", "Apple@qq.com", "newnewnww"));
//		System.out.println(services.findUser("T001"));
//		 
//	}
}
