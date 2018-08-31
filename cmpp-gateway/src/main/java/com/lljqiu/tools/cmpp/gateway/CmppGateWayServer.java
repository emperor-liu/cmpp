/**
 * Project Name cmpp-gateway
 * File Name MCPPServer.java
 * Package Name com.lljqiu.tools.cmpp.gateway
 * Create Time 2018年3月19日
 * Create by name：liujie -- email: liujie@lljqiu.com
 * Copyright © 2015, 2017, www.lljqiu.com. All rights reserved.
 */
package com.lljqiu.tools.cmpp.gateway;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.lljqiu.tools.cmpp.gateway.acceptor.CmccAcceptor;
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
        logger.info("<start server ...>");
        List<ClientInfo> clientConfig = ReadYamlUtils.getClientConfig();
        for (ClientInfo clientInfo : clientConfig) {
        	EhCache.put(EhCache.CACHE_NAME, clientInfo.getSpIp(), clientInfo);
		}
        CmccAcceptor acceptor = new CmccAcceptor();
        acceptor.start();
    }
    
}
