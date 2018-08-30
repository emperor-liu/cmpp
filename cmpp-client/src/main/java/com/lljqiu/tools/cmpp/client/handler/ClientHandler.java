package com.lljqiu.tools.cmpp.client.handler;

import java.util.Arrays;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.LoggerFactory;

import com.lljqiu.tools.cmpp.client.stack.BaseMessage;



/** 
 * ClassName: ClientHandler.java <br>
 * Description: <br>
 * Create by: name：liujie <br>email: jie_liu1@asdc.com.cn <br>
 * Create Time: 2017年6月6日<br>
 */
public class ClientHandler extends IoHandlerAdapter {
    
    public void messageReceived(IoSession session, Object message) throws Exception {
    	System.out.println("messageReceived==>>"+Arrays.toString((byte[])message));
        BaseMessage baseMessage = (BaseMessage) message;
        LoggerFactory.getLogger(ClientHandler.class).info("client receive a message is : " + ToStringBuilder.reflectionToString(baseMessage));
        LoggerFactory.getLogger(ClientHandler.class).debug("client receive a message is : " + baseMessage.getBodys());
    }

    /**
     * 客户端接收服务端的消息
     */
    public void messageSent(IoSession session, Object message) throws Exception {
    	System.out.println("messageSent"+Arrays.toString((byte[])message));
    	LoggerFactory.getLogger(ClientHandler.class).info("messageSent -> ：" + Arrays.toString((byte[])message));
    }
}
