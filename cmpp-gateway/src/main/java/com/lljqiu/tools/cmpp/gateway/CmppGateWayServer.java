/**
 * Project Name cmpp-gateway
 * File Name MCPPServer.java
 * Package Name com.lljqiu.tools.cmpp.gateway
 * Create Time 2018年3月19日
 * Create by name：liujie -- email: liujie@lljqiu.com
 * Copyright © 2015, 2017, www.lljqiu.com. All rights reserved.
 */
package com.lljqiu.tools.cmpp.gateway;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.lljqiu.tools.cmpp.gateway.acceptor.CmccAcceptor;
import com.lljqiu.tools.cmpp.gateway.service.ReadMsgService;
import com.lljqiu.tools.cmpp.gateway.stack.ClientInfo;
import com.lljqiu.tools.cmpp.gateway.utils.EhCache;
import com.lljqiu.tools.cmpp.gateway.utils.ReadYamlUtils;

/** 
 * ClassName: MCPPServer.java <br>
 * Description: <br>
 * @author name：liujie <br>email: liujie@lljqiu.com <br>
 * Create Time: 2018年3月19日<br>
 */
@ComponentScan(basePackages ="com.lljqiu.tools.cmpp.gateway")
@SpringBootApplication
public class CmppGateWayServer {
    private static final Logger logger = LoggerFactory.getLogger(CmppGateWayServer.class);
    
    public static void main(String[] args) {
        SpringApplication.run(CmppGateWayServer.class, args);
        logger.info("start server ...");
        List<ClientInfo> clientConfig = ReadYamlUtils.getClientConfig();
        for (ClientInfo clientInfo : clientConfig) {
        	EhCache.put(EhCache.CACHE_NAME, clientInfo.getSpIp(), clientInfo);
		}
        CmccAcceptor acceptor = new CmccAcceptor();
        acceptor.start();
        
    }
    
    public static void socketServer(int port) throws Exception {
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            // 读取客户端数据    
            Socket socket = serverSocket.accept();
            String spIp = socket.getInetAddress().getHostAddress();
            DataInputStream input = new DataInputStream(socket.getInputStream());
            byte[] readRequestMessage = ReadMsgService.readRequestMessage(input, spIp);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.write(readRequestMessage);
//            out.close();
            //input.close();
        }
    }

    /** 
     * Description：启动server服务
     * @return void
     * @author name：liujie <br>email: liujie@lljqiu.com
     **/
    public static void start() {
        try {
            logger.info("start server port={}",ReadYamlUtils.getGatewayPort());
            socketServer(ReadYamlUtils.getGatewayPort());
        } catch (Exception e) {
            logger.error("init server error{}",e.getMessage());
        }
        
    } 
}
