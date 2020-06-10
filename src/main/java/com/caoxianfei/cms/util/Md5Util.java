package com.caoxianfei.cms.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 *    		md5加密工具
 * @author (caoxianfei) email:(948315767@qq.com)
 * @version v1.0      
 * @date 
 *	  创建于:2020年6月5日下午7:15:10
 */
public class Md5Util {
	/**
	 * 直接对密码进行散列，那么黑客可以对通过获得这个密码散列值，
	 * 然后通过查散列值字典（例如MD5密码破解网站），得到某用户的密码。
	 * 加Salt可以一定程度上解决这个问题
	 */
	private static final String salt="a1b2c3";

	public static String md5Encode(String password) {
	
		return DigestUtils.md5Hex(password +salt);
	}

	public static void main(String[] args) {
		Md5Util.md5Encode("1");
		Md5Util.md5Encode("1");
		Md5Util.md5Encode("123456");
	}
}
