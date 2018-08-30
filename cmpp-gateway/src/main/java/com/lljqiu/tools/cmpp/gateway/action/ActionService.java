/**
 * Project Name pushServer
 * File Name package-info.java
 * Package Name com.lljqiu.tools.pushServer.action
 * Create Time 2018年3月15日
 * Create by name：liujie -- email: jie_liu1@asdc.com.cn
 * Copyright © 2006, 2017, ASDC DAI. All rights reserved.
 */
package com.lljqiu.tools.cmpp.gateway.action;

import org.apache.mina.core.session.IoSession;

import com.lljqiu.tools.cmpp.gateway.stack.BaseMessage;

/** 
 * ClassName: ActionService.java <br>
 * Description: <br>
 * Create by: name：liujie <br>email: jie_liu1@asdc.com.cn <br>
 * Create Time: 2017年6月5日<br>
 */
public interface ActionService {

    public void doProcess(IoSession session, BaseMessage command) throws Exception;
}
