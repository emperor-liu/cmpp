/**
 * Project Name pushServer
 * File Name package-info.java
 * Package Name com.lljqiu.tools.pushServer.action
 * Create Time 2018年3月15日
 * Create by name：liujie -- email: jie_liu1@asdc.com.cn
 * Copyright © 2006, 2017, ASDC DAI. All rights reserved.
 */
package com.lljqiu.tools.cmpp.gateway.action;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lljqiu.tools.cmpp.gateway.exception.GateWayException;
import com.lljqiu.tools.cmpp.gateway.stack.BaseMessage;
import com.lljqiu.tools.cmpp.gateway.stack.ClientInfo;
import com.lljqiu.tools.cmpp.gateway.utils.EhCache;
import com.lljqiu.tools.cmpp.gateway.utils.Utils;

/** 
 * ClassName: ActionFactoy.java <br>
 * Description: <br>
 * Create by: name：liujie <br>email: jie_liu1@asdc.com.cn <br>
 * Create Time: 2017年6月5日<br>
 */
public abstract class ActionFactoy implements ActionService {
	private static Logger logger = LoggerFactory.getLogger(ActionFactoy.class);
	
    protected IoSession session;
    protected BaseMessage message;
    protected IoBuffer ioBuffer;
    
    protected void checkConnectRequest(){
    	// 获取客户端 IP，根据客户端 IP 来获取其配置
    	String clientIp = message.getClientIp();
    	ClientInfo clientInfo = (ClientInfo) EhCache.get(EhCache.CACHE_NAME, clientIp);
    	GateWayException.checkCondition(clientInfo == null, 0x0002, "非源地址");
    	// 企业编码 也就是 SP_Id
    	String sourceAddr = message.getBodys().getString("sourceAddr");
    	// 客户端签名
    	String authenticatorSource = message.getBodys().getString("authenticatorSource");
    	String timestamp = message.getBodys().getString("timestamp");
    	logger.debug("request sourceAddr ={},AuthenticatorSource={}",sourceAddr,authenticatorSource);
		//（Source_Addr+9 字节的0 +shared secret+timestamp）
    	String data = sourceAddr+"\0\0\0\0\0\0\0\0\0"+clientInfo.getSharedSecret() + timestamp;
    	String sign =  Utils.encryptMD5(data);
        boolean flag = sign.equals(authenticatorSource);
        GateWayException.checkCondition(!flag, 0x0003, "认证失败");
        String clientVersion = message.getBodys().getString("version");
        GateWayException.checkCondition(clientVersion.equals(clientInfo.getVersion()), 0x0004, "版本不一致");
    }
    
    protected abstract void exec() throws Exception;
    
    protected abstract <T> T readMessage() throws Exception;
    
    public void doProcess(IoSession session, BaseMessage message) throws Exception{
        try {
            this.session = session;
            this.message = message;
            exec();
        } catch (Exception e) {
        	throw new GateWayException("doProcess msg connect error "+ e.getMessage());
        } 
    }
    public <T> T readMessage(IoBuffer ioBuffer) throws Exception{
    	try {
    		this.ioBuffer = ioBuffer;
    		readMessage();
    	} catch (Exception e) {
    		throw new GateWayException("read msg connect error "+ e.getMessage());
    	}
		return null; 
    }
}
