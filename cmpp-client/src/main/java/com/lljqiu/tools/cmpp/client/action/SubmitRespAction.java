/**
 * Project Name cmpp-client
 * File Name SubmitRespAction.java
 * Package Name com.lljqiu.tools.cmpp.client.action
 * Create Time 2018年9月1日
 * Create by name：liujie -- email: liujie@lljqiu.com
 * Copyright © 2015, 2017, www.lljqiu.com. All rights reserved.
 */
package com.lljqiu.tools.cmpp.client.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lljqiu.tools.cmpp.client.stack.MsgSubmitResp;

/** 
 * ClassName: SubmitRespAction.java <br>
 * Description: <br>
 * Create by: name：liujie <br>email: liujie@lljqiu.com <br>
 * Create Time: 2018年9月1日<br>
 */
public class SubmitRespAction extends ActionFactoy {
	
	private final static Logger logger = LoggerFactory.getLogger(SubmitRespAction.class);
	
	/* (non-Javadoc)
	 * @see com.lljqiu.tools.cmpp.client.action.ActionFactoy#exec()
	 */
	@Override
	protected void exec() throws Exception {
		logger.info("submit response {}", message.toString());

	}

	/* (non-Javadoc)
	 * @see com.lljqiu.tools.cmpp.client.action.ActionFactoy#readMessage()
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected <T> T readMessage() throws Exception {
		MsgSubmitResp submitResp = new MsgSubmitResp();
		submitResp.setMsgId(ioBuffer.getLong());
		submitResp.setResult(ioBuffer.getInt());
		return (T) submitResp;
	}

}
