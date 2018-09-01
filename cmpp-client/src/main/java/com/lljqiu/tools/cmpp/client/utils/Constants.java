/**
 * Project Name cmpp-client
 * File Name Constants.java
 * Package Name com.lljqiu.tools.cmpp.client.utils
 * Create Time 2018年8月30日
 * Create by name：liujie -- email: liujie@lljqiu.com
 * Copyright © 2015, 2017, www.lljqiu.com. All rights reserved.
 */
package com.lljqiu.tools.cmpp.client.utils;

/** 
 * ClassName: Constants.java <br>
 * Description: <br>
 * Create by: name：liujie <br>email: liujie@lljqiu.com <br>
 * Create Time: 2018年8月30日<br>
 */
public class Constants {
	
	public static class MessageTotalLength{
		public final static Integer SUBMIT = 12 + 8 + 1 + 1 + 1 + 1 + 10 + 1 + 32 + 1 + 1 + 1 + 1 + 6 + 2 + 6 + 17 + 17 + 21 + 1
				+ 32 + 1 + 1 + 20;
		public final static Integer ACTIVE_TEST = 12 + 1;
	}
	
	public static class ServerConfig{
		public static GateWayConfig gatewayConfig = new GateWayConfig(ReadPropertiesUtil.getInstance().getCmppConfig());
	}
	
	/**
	 * action 常量
	 * ClassName: Constants.java <br>
	 * Description: <br>
	 * Create by: name：liujie <br>email: liujie@lljqiu.com <br>
	 * Create Time: 2018年8月30日<br>
	 */
	public static class ActionConstants{
		/** 心跳链接 */
		public final static String ACTIVE_RESP = "ActiveResp";
		/** 短信链接*/
		public final static String CONNECT_RESP = "ConnectResp";
		/** 终止链接*/
		public final static String TERMINATE_RESP = "TerminateResp";
		/** 提交短信--下行*/
		public final static String SUBMIT_RESP = "SubmitResp";
		/** 短信下发*/
		public final static String DELIVER_RESP = "DeliverResp";
	}
}
