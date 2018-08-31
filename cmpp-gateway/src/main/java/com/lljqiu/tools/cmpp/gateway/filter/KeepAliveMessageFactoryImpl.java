/**
 * Project Name pushServer
 * File Name KeepAliveMessageFactoryImpl.java
 * Package Name com.lljqiu.tools.pushServer.filter
 * Create Time 2018年3月15日
 * Create by name：liujie -- email: liujie@lljqiu.com
 * Copyright © 2015, 2017, www.lljqiu.com. All rights reserved.
 */
package com.lljqiu.tools.cmpp.gateway.filter;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;

import com.lljqiu.tools.cmpp.gateway.stack.BaseMessage;
import com.lljqiu.tools.cmpp.gateway.stack.MsgCommand;

/** 
 * ClassName: KeepAliveMessageFactoryImpl.java <br>
 * Description: <br>
 * @author name：liujie <br>email: liujie@lljqiu.com <br>
 * @date: 2018年3月15日<br>
 */
public class KeepAliveMessageFactoryImpl implements KeepAliveMessageFactory {

    private static Log log = LogFactory.getLog(KeepAliveMessageFactoryImpl.class);

    public boolean isRequest(IoSession session, Object message) {
       
        BaseMessage command = null;
        try {
            command = (BaseMessage) message;
        } catch (Exception e) {
            return false;
        }
        if(command.getMsgCommand() == MsgCommand.CMPP_ACTIVE_TEST_RESP){
            log.info("request keepalive ...");
            return true;
        }
        return false;
    }

    public boolean isResponse(IoSession session, Object message) {
        return false;
    }

    public Object getRequest(IoSession session) {
        return null;
    }

    public Object getResponse(IoSession session, Object request) {
        BaseMessage requestMessage = (BaseMessage) request;
        
        log.debug("服务端接收心跳请求数据：" + ToStringBuilder.reflectionToString(requestMessage));

//        JSONObject json = new JSONObject();
//        json.put("status", 200);
//        json.put("result", "keep live success");
//        //ResponseMessage responseMessage = new ResponseMessage(Constants.SUCCESS,Constants.T101);
//        try {
//            requestMessage.setBodys(json);
//            requestMessage.setMsgCommand(MsgCommand.CMPP_CONNECT_RESP);
//        } catch (UnsupportedEncodingException e) {
//            log.debug("响应心跳请求异常" + e);
//        }
//        return requestMessage.toByte();
        return null;
    }

}
