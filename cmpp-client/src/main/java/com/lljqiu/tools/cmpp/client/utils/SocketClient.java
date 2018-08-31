/**
 * Project Name cmpp-client
 * File Name SocketClient.java
 * Package Name com.lljqiu.tools.cmpp.client.utils
 * Create Time 2018年8月30日
 * Create by name：liujie -- email: liujie@lljqiu.com
 * Copyright © 2015, 2017, www.lljqiu.com. All rights reserved.
 */
package com.lljqiu.tools.cmpp.client.utils;

import java.net.InetSocketAddress;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lljqiu.tools.cmpp.client.codec.MPCodecFactory;
import com.lljqiu.tools.cmpp.client.handler.ClientHandler;

/** 
 * ClassName: SocketClient.java <br>
 * Description: <br>
 * Create by: name：liujie <br>email: liujie@lljqiu.com <br>
 * Create Time: 2018年8月30日<br>
 */
public class SocketClient {
	public final static Logger log = LoggerFactory.getLogger(SocketClient.class);
	
	private static SocketClient instance;
	private IoSession serverSession = null;
    private NioSocketConnector connector = null; 
    GateWayConfig gatewayConfig = new GateWayConfig(ReadPropertiesUtil.getInstance().getCmppConfig());
    
	public synchronized static SocketClient getInstance() {
        if (instance == null) {
            instance = new SocketClient();
        }
        return instance;
    }

    public SocketClient() {
        if (serverSession == null)
            connPushServer();
    }
    
    public void connPushServer() {
        try {
            // 创建客户端连接器.
//          NioSocketConnector connector = new NioSocketConnector();
            connector = new NioSocketConnector();
            connector.getFilterChain().addLast("logger", new LoggingFilter());
            connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new MPCodecFactory("UTF-8")));

            // 设置连接超时检查时间
            connector.setConnectTimeoutCheckInterval(30);
            connector.setHandler(new ClientHandler());

            // 建立连接
            log.info("connection server ip={},port{}", gatewayConfig.getSpIp(), gatewayConfig.getPort());
            ConnectFuture cf = connector.connect(new InetSocketAddress(gatewayConfig.getSpIp(), gatewayConfig.getPort()));
            // 等待连接创建完成
            cf.awaitUninterruptibly();
            serverSession = cf.getSession();
        } catch (Exception e) {
            log.error("connection server error {}", e.getMessage());
            closeSession();
        }
    }
    
    public void closeSession(){
        serverSession.close(true);
        serverSession = null;
        connector.dispose(true);
    }
    
    public void sendMessage(byte[] data){
    	try {
			if (serverSession == null) {
			    connPushServer();
			}
			serverSession.write(data);
//			closeSession();
		} catch (Exception e) {
			log.error("sendMessage error {}", e.getMessage());
//			closeSession();
		}
    }
}
