/**
 * Project Name pushServer
 * File Name ActionHandler.java
 * Package Name com.lljqiu.tools.pushServer.context
 * Create Time 2018年3月15日
 * Create by name：liujie -- email: jie_liu1@asdc.com.cn
 * Copyright © 2006, 2017, ASDC DAI. All rights reserved.
 */
package com.lljqiu.tools.cmpp.gateway.context;

import org.apache.mina.core.session.IoSession;

import com.lljqiu.tools.cmpp.gateway.action.ActionService;
import com.lljqiu.tools.cmpp.gateway.stack.BaseMessage;


/** 
 * ClassName: ActionHandler.java <br>
 * Description: 消息分发器<br>
 * @author name：liujie <br>email: jie_liu1@asdc.com.cn <br>
 * @date: 2018年3月15日<br>
 */
public class ActionHandler {
    /** 
     * Description：消息处理-消息分发
     * @param session
     * @param command
     * @throws Exception
     * @return void
     * @author name：liujie <br>email: jie_liu1@asdc.com.cn
     **/
    public static void process(IoSession session, BaseMessage command) throws Exception {

        ActionService service = MessageFactory.createService(command.getMsgCommand());
        service.doProcess(session, command);

    }
}
