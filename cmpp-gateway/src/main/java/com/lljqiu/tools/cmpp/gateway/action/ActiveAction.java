/**
 * Project Name cmpp-gateway
 * File Name ConnectAction.java
 * Package Name com.lljqiu.tools.cmpp.gateway.action
 * Create Time 2018年8月30日
 * Create by name：liujie -- email: liujie@lljqiu.com
 * Copyright © 2015, 2017, www.lljqiu.com. All rights reserved.
 */
package com.lljqiu.tools.cmpp.gateway.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lljqiu.tools.cmpp.gateway.stack.MsgActiveTestResp;
import com.lljqiu.tools.cmpp.gateway.utils.Constants;

/** 
 * ClassName: ConnectAction.java <br>
 * Description: cmpp心跳监测<br>
 * Create by: name：liujie <br>email: liujie@lljqiu.com <br>
 * Create Time: 2018年8月30日<br>
 */
public class ActiveAction extends ActionFactoy {
	
	private final static Logger logger = LoggerFactory.getLogger(ActiveAction.class);

	/* (non-Javadoc)
	 * @see com.lljqiu.tools.cmpp.gateway.action.ActionFactoy#exec()
	 */
	@Override
	protected void exec() throws Exception {
		logger.info("<客户端心跳信息>");
		MsgActiveTestResp active = new MsgActiveTestResp();
		active.setTotalLength(Constants.MessageTotalLength.ACTIVE_TEST);
		active.setCommandId(message.getMsgCommand());
		active.setSequenceId(message.getSequenceId());
		active.setReserved((byte)0);
		session.write(active.toByteArry());
        
	}

	/* (non-Javadoc)
	 * @see com.lljqiu.tools.cmpp.gateway.action.ActionFactoy#readMessage()
	 */
	@Override
	protected <T> T readMessage() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
