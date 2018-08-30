/**
 * Project Name cmpp-client
 * File Name Test.java
 * Package Name com.lljqiu.tools.cmpp.client
 * Create Time 2018年8月30日
 * Create by name：liujie -- email: liujie@lljqiu.com
 * Copyright © 2015, 2017, www.lljqiu.com. All rights reserved.
 */
package com.lljqiu.tools.cmpp.client;

import com.lljqiu.tools.cmpp.client.stack.MsgHead;
import com.lljqiu.tools.cmpp.client.utils.MsgCommand;
import com.lljqiu.tools.cmpp.client.utils.MsgUtils;
import com.lljqiu.tools.cmpp.client.utils.SocketClient;

/** 
 * ClassName: Test.java <br>
 * Description: <br>
 * Create by: name：liujie <br>email: liujie@lljqiu.com <br>
 * Create Time: 2018年8月30日<br>
 */
public class Test {

	public static void main(String[] args) {
		MsgHead head = new MsgHead();
		head.setTotalLength(12);// 消息总长度，级总字节数:4+4+4(消息头)+6+16+1+4(消息主体)
		head.setCommandId(MsgCommand.CMPP_ACTIVE_TEST);// 标识创建连接
		head.setSequenceId(MsgUtils.getSequence());// 序列，由我们指定
		SocketClient.getInstance().sendMessage(head.toByteArry());
	}
}
