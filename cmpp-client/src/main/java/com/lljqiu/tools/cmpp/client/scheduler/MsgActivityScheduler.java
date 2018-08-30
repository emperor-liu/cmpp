/**
 * Project Name cmpp-client
 * File Name MsgActivityScheduler.java
 * Package Name com.lljqiu.tools.cmpp.client.scheduler
 * Create Time 2018年8月30日
 * Create by name：liujie -- email: liujie@lljqiu.com
 * Copyright © 2015, 2017, www.lljqiu.com. All rights reserved.
 */
package com.lljqiu.tools.cmpp.client.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.lljqiu.tools.cmpp.client.stack.MsgHead;
import com.lljqiu.tools.cmpp.client.utils.Constants;
import com.lljqiu.tools.cmpp.client.utils.MsgCommand;
import com.lljqiu.tools.cmpp.client.utils.MsgUtils;
import com.lljqiu.tools.cmpp.client.utils.SocketClient;

/**
 * ClassName: MsgActivityScheduler.java <br>
 * Description: <br>
 * Create by: name：liujie <br>
 * email: liujie@lljqiu.com <br>
 * Create Time: 2018年8月30日<br>
 */
@Configuration
@EnableScheduling
public class MsgActivityScheduler {

	private static final Logger logger = LoggerFactory.getLogger(MsgActivityScheduler.class);

	@Scheduled(cron = "0 0/3 * * * ?")
	public void executeCollectionTask() {
		logger.info("<开始链路检查>");
		int count = 0;
		boolean result = activityTestISMG();
		while (!result) {
			count++;
			result = activityTestISMG();
			// 如果再次链路检查次数超过两次则终止连接
			if (count >= (Constants.ServerConfig.gatewayConfig.getConnectCount() - 1)) {
				break;
			}
		}
		logger.info("<链路检查结束>");
	}

	@SuppressWarnings("finally")
	public boolean activityTestISMG() {
		boolean result = false;
		try {
			MsgHead head = new MsgHead();
			head.setTotalLength(12);// 消息总长度，级总字节数:4+4+4(消息头)+6+16+1+4(消息主体)
			head.setCommandId(MsgCommand.CMPP_ACTIVE_TEST);// 标识创建连接
			head.setSequenceId(MsgUtils.getSequence());// 序列，由我们指定
			SocketClient.getInstance().sendMessage(head.toByteArry());
			result = true;
		} catch (Exception e) {
			logger.error("<链路检查异常{}>" , e);
		} finally {
			return result;
		}
	}
}
