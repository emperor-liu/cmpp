/**
 * Project Name cmcc-client
 * File Name package-info.java
 * Package Name com.lljqiu.tools.pushServer.action
 * Create Time 2018年3月15日
 * Create by name：liujie -- email: liujie@lljqiu.com
 * Copyright © 2015, 2017, www.lljqiu.com. All rights reserved.
 */
package com.lljqiu.tools.cmpp.client.action;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import com.lljqiu.tools.cmpp.client.stack.BaseMessage;


/** 
 * ClassName: ActionFactoy.java <br>
 * Description: <br>
 * Create by: name：liujie <br>email: liujie@lljqiu.com <br>
 * Create Time: 2017年6月5日<br>
 */
public abstract class ActionFactoy implements ActionService {
	
    protected IoSession session;
    protected BaseMessage message;
    protected IoBuffer ioBuffer;
    
    
    protected abstract void exec() throws Exception;
    
    protected abstract <T> T readMessage() throws Exception;
    
    public void doProcess(IoSession session, BaseMessage message) throws Exception{
        try {
            this.session = session;
            this.message = message;
            exec();
        } catch (Exception e) {
        	throw new RuntimeException("doProcess msg connect error "+ e.getMessage());
        } 
    }
    public <T> T readMessage(IoBuffer ioBuffer) throws Exception{
    	try {
    		this.ioBuffer = ioBuffer;
    		return readMessage();
    	} catch (Exception e) {
    		throw new RuntimeException("read msg connect error "+ e.getMessage());
    	}
    }
}
