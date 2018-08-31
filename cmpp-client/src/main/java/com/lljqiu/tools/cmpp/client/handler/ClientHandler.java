/**
 * Project Name cmpp-client
 * File Name package-info.java
 * Package Name com.lljqiu.tools.cmpp.client.handler
 * Create Time 2018年8月30日
 * Create by name：liujie -- email: liujie@lljqiu.com
 * Copyright © 2015, 2017, www.lljqiu.com. All rights reserved.
 */
package com.lljqiu.tools.cmpp.client.handler;

import java.util.Arrays;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lljqiu.tools.cmpp.client.stack.BaseMessage;



/** 
 * ClassName: ClientHandler.java <br>
 * Description: <br>
 * Create by: name：liujie <br>email: liujie@lljqiu.com <br>
 * Create Time: 2017年6月6日<br>
 */
public class ClientHandler extends IoHandlerAdapter {
	private final static Logger logger = LoggerFactory.getLogger(ClientHandler.class);
	
    public void messageReceived(IoSession session, Object message) throws Exception {
        BaseMessage baseMessage = (BaseMessage) message;
        logger.info("client receive a message is : " + ToStringBuilder.reflectionToString(baseMessage));
        ActionHandler.process(session, baseMessage);
    }

    public void messageSent(IoSession session, Object message) throws Exception {
    	logger.info("messageSent -> ：" + Arrays.toString((byte[])message));
    }
}
