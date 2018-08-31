/**
 * Project Name cmpp-client
 * File Name Utils.java
 * Package Name com.lljqiu.tools.cmpp.client.utils
 * Create Time 2018年8月30日
 * Create by name：liujie -- email: liujie@lljqiu.com
 * Copyright © 2015, 2017, www.lljqiu.com. All rights reserved.
 */
package com.lljqiu.tools.cmpp.client.utils;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

/** 
 * ClassName: Utils.java <br>
 * Description: <br>
 * Create by: name：liujie <br>email: liujie@lljqiu.com <br>
 * Create Time: 2018年8月30日<br>
 */
public class Utils {
	private final static char[] hexDigits        = "0123456789ABCDEF".toCharArray();
	
	public static void main(String[] args) {
	}
	
	public static String getNowData(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
	public static String encryptMD5(String data) {
        return data == null ? null : hashEncrypt(data, "MD5");
    }
	
	/** 
	 * Description：hash 加密
	 * @param data 待加密数据
	 * @param algorithm 加密算法
	 * @return String
	 * @author name：liujie <br>email: liujie@lljqiu.com
	 **/
	private static String hashEncrypt(String data, String algorithm) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(data.getBytes());
            return bytes2Hex(md.digest());
        } catch (Exception never) {
            throw new RuntimeException("加密失败");
        }
    }
	
	private static String bytes2Hex(byte[] bytes) {
        int len = bytes.length;
        char[] str = new char[len * 2];
        for (int i = 0; i < len; i++) {
            byte b = bytes[i];
            str[i * 2] = hexDigits[b >>> 4 & 0xF];
            str[i * 2 + 1] = hexDigits[b & 0xF];
        }
        return new String(str);
    }
}
