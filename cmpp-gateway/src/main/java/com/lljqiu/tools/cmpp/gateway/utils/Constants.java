/**
 * Project Name cmpp-gateway
 * File Name Constants.java
 * Package Name com.lljqiu.tools.cmpp.gateway.utils
 * Create Time 2018年8月30日
 * Create by name：liujie -- email: liujie@lljqiu.com
 * Copyright © 2015, 2017, www.lljqiu.com. All rights reserved.
 */
package com.lljqiu.tools.cmpp.gateway.utils;

/** 
 * ClassName: Constants.java <br>
 * Description: 常量类<br>
 * Create by: name：liujie <br>email: liujie@lljqiu.com <br>
 * Create Time: 2018年8月30日<br>
 */
public class Constants {
	
	public final static byte CMPP_VERSION = 0x30;

	/**
	 * action 常量
	 * ClassName: Constants.java <br>
	 * Description: <br>
	 * Create by: name：liujie <br>email: liujie@lljqiu.com <br>
	 * Create Time: 2018年8月30日<br>
	 */
	public static class ActionConstants{
		/** 心跳链接 */
		public final static String ACTIVE = "Active";
		/** 短信链接*/
		public final static String CONNECT = "Connect";
		/** 终止链接*/
		public final static String TERMINATE = "Terminate";
		/** 提交短信--下行*/
		public final static String SUBMIT = "Submit";
		/** 短信下发*/
		public final static String DELIVER = "Deliver";
	}
	
	/**
	 * 消息体长度 常量
	 * ClassName: Constants.java <br>
	 * Description: <br>
	 * Create by: name：liujie <br>email: liujie@lljqiu.com <br>
	 * Create Time: 2018年8月30日<br>
	 */
	public static class MessageTotalLength{
		public final static Integer CONNECT = 12 + 4 + 16 + 1;
		public final static Integer ACTIVE_TEST = 12 + 1;
	}
}
