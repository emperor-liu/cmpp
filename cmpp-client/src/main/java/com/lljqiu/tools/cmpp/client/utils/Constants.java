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

	public static class ServerConfig{
		public static GateWayConfig gatewayConfig = new GateWayConfig(ReadPropertiesUtil.getInstance().getCmppConfig());
	}
}
