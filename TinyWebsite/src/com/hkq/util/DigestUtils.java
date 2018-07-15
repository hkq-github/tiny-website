package com.hkq.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5 or SHA加密工具类
 * 
 * @author hkq
 */

public class DigestUtils {
	
	public static final String MD5 = "MD5", SHA1 = "SHA-1", SHA256 = "SHA-256"; 
	
	/**
	 * 使用MD5或SHA加密<br/>
	 * 
	 * @return 返回加密后的16进制表示字符串，出错返回null
	 */
	public static String digest(String mess, String method) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance(method);
			md.update(mess.getBytes());
			byte[] digest = md.digest();
			
			// 将digest转换为十六进制字符串形式
			char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',  
	                'A', 'B', 'C', 'D', 'E', 'F' };
			char[] result = new char[digest.length * 2]; 
			int k = 0;  
            for (int i = 0; i < digest.length; i++) { 
                byte byte0 = digest[i];
                result[k++] = hexDigits[byte0 >>> 4 & 0xf];
                result[k++] = hexDigits[byte0 & 0xf];
            }  
			
            return new String(result);
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 使SHA-256加密<br/>
	 * 
	 * @return 返回加密后的16进制表示字符串，出错返回null
	 */
	public static String sha256Digest(String mess) {
		return digest(mess, SHA256);
	}
}
