/**
 * Project Name cmpp-gateway
 * File Name ConnectAction.java
 * Package Name com.lljqiu.tools.cmpp.gateway.action
 * Create Time 2018年8月30日
 * Create by name：liujie -- email: liujie@lljqiu.com
 * Copyright © 2015, 2017, www.lljqiu.com. All rights reserved.
 */
package com.lljqiu.tools.cmpp.gateway.action;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lljqiu.tools.cmpp.gateway.exception.GateWayException;
import com.lljqiu.tools.cmpp.gateway.stack.ClientInfo;
import com.lljqiu.tools.cmpp.gateway.stack.MsgCommand;
import com.lljqiu.tools.cmpp.gateway.stack.MsgConnect;
import com.lljqiu.tools.cmpp.gateway.stack.MsgConnectResp;
import com.lljqiu.tools.cmpp.gateway.utils.Constants;
import com.lljqiu.tools.cmpp.gateway.utils.EhCache;
import com.lljqiu.tools.cmpp.gateway.utils.MsgUtils;

/** 
 * ClassName: ConnectAction.java <br>
 * Description: cmpp 认证操作<br>
 * Create by: name：liujie <br>email: liujie@lljqiu.com <br>
 * Create Time: 2018年8月30日<br>
 */
public class ConnectAction extends ActionFactoy {
	
	private final static Logger logger = LoggerFactory.getLogger(ConnectAction.class);

	/* (non-Javadoc)
	 * @see com.lljqiu.tools.cmpp.gateway.action.ActionFactoy#exec()
	 */
	@Override
	protected void exec() throws Exception {
		logger.info("接收 Connect 消息");
		logger.info("Connect={}",message);
		MsgConnectResp connectResp = new MsgConnectResp();
        connectResp.setTotalLength(Constants.MessageTotalLength.CONNECT);//消息总长度，级总字节数:4+4+4(消息头)+6+16+1+4(消息主体)
        connectResp.setCommandId(MsgCommand.CMPP_CONNECT_RESP);//标识创建连接
        connectResp.setSequenceId(message.getSequenceId());//序列，由我们指定
        int status = 0x0000;
        try {
            checkConnectRequest();
        } catch (GateWayException e) {
            status = e.getErrorCode();
        }
        connectResp.setStatus(status);
        String authenticatorSource = message.getBodys().getString("authenticatorSource");
        String clientIp = message.getClientIp();
    	ClientInfo clientInfo = (ClientInfo) EhCache.get(EhCache.CACHE_NAME, clientIp);
    	String sharedSecret = clientInfo.getSharedSecret();
    	
        connectResp.setAuthenticatorISMG(MsgUtils.getAuthenticatorISMG(status, authenticatorSource, sharedSecret));
        connectResp.setVersion(Constants.CMPP_VERSION);
        logger.debug("{}响应消息{}",MsgCommand.CMPP_CONNECT_RESP,ToStringBuilder.reflectionToString(connectResp));
        session.write(connectResp.toByteArry());
        
	}
	
	

	/* (non-Javadoc)
	 * @see com.lljqiu.tools.cmpp.gateway.action.ActionFactoy#readMessage()
	 */
	@SuppressWarnings({ "finally", "unchecked" })
	@Override
	protected <T> T readMessage() throws Exception {

        MsgConnect msgConnect = new MsgConnect();

        try {
            byte[] sourceAddr = new byte[6];
            ioBuffer.get(sourceAddr);
            msgConnect.setSourceAddr(new String(sourceAddr));
            byte[] aiByte = new byte[16];
            ioBuffer.get(aiByte);
            msgConnect.setAuthenticatorSource(aiByte);
            msgConnect.setVersion(ioBuffer.get());
            msgConnect.setTimestamp(ioBuffer.get());

        } catch (Exception e) {
            logger.info("read msg connect error{}" , e.getMessage());
            throw new GateWayException("read msg connect error" + e.getMessage());
        }finally {
        	return (T) msgConnect;
		}
	}

}
