/**
 * Project Name cmpp-client
 * File Name GetaWayConfig.java
 * Package Name utils
 * Create Time 2018年8月30日
 * Create by name：liujie -- email: liujie@lljqiu.com
 * Copyright © 2015, 2017, www.lljqiu.com. All rights reserved.
 */
package com.lljqiu.tools.cmpp.client.utils;

import java.util.Map;

/**
 * ClassName: GetaWayConfig.java <br>
 * Description: <br>
 * Create by: name：liujie <br>
 * email: liujie@lljqiu.com <br>
 * Create Time: 2018年8月30日<br>
 */

public class GateWayConfig {
	private  String spIp;
	private  Integer port;
	private  String spId;
	private  String sharedSecret;
	private  String spCode;
	private  String serviceId;
	private  Integer timeOut;
	private  Integer connectCount;
	
	
	public GateWayConfig(Map<String, Object> cmppMap) {
		super();
		this.connectCount = new Integer((String) cmppMap.get("connectCount"));
		this.spIp = (String) cmppMap.get("spIp");
		this.port = new Integer((String) cmppMap.get("port"));
		this.spId = (String) cmppMap.get("spId");
		this.sharedSecret = (String) cmppMap.get("sharedSecret");
		this.spCode = (String) cmppMap.get("spCode");
		this.serviceId = (String) cmppMap.get("serviceId");
		this.timeOut = new Integer((String) cmppMap.get("timeOut"));
	}

	public String getSpIp() {
		return spIp;
	}
	
	public void setSpIp(String spIp) {
		this.spIp = spIp;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getSpId() {
		return spId;
	}

	public void setSpId(String spId) {
		this.spId = spId;
	}

	public String getSharedSecret() {
		return sharedSecret;
	}

	public void setSharedSecret(String sharedSecret) {
		this.sharedSecret = sharedSecret;
	}

	public String getSpCode() {
		return spCode;
	}

	public void setSpCode(String spCode) {
		this.spCode = spCode;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public Integer getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(Integer timeOut) {
		this.timeOut = timeOut;
	}

	public Integer getConnectCount() {
		return connectCount;
	}

	public void setConnectCount(Integer connectCount) {
		this.connectCount = connectCount;
	}

	@Override
	public String toString() {
		return "GetaWayConfig [spIp=" + spIp + ", port=" + port + ", spId=" + spId + ", sharedSecret=" + sharedSecret
				+ ", spCode=" + spCode + ", serviceId=" + serviceId + ", timeOut=" + timeOut + ", connectCount="
				+ connectCount + "]";
	}
	
	

}
