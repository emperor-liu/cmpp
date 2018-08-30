/**
 * Project Name cmpp-gateway
 * File Name PutMsgService.java
 * Package Name com.lljqiu.tools.cmpp.gateway.service
 * Create Time 2018年3月19日
 * Create by name：liujie -- email: liujie@lljqiu.com
 * Copyright © 2015, 2017, www.lljqiu.com. All rights reserved.
 */
package com.lljqiu.tools.cmpp.gateway.service;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lljqiu.tools.cmpp.gateway.exception.GateWayException;
import com.lljqiu.tools.cmpp.gateway.stack.MsgCommand;
import com.lljqiu.tools.cmpp.gateway.stack.MsgConnect;
import com.lljqiu.tools.cmpp.gateway.stack.MsgConnectResp;

/** 
 * ClassName: PutMsgService.java <br>
 * Description: <br>
 * @author name：liujie <br>email: liujie@lljqiu.com <br>
 * Create Time: 2018年3月19日<br>
 */
public class PutMsgService extends BaseService {
    private static Logger logger = LoggerFactory.getLogger(PutMsgService.class);

    /** 
     * Description：拼接连接请求
     * @param connectReq
     * @return
     * @return byte[]
     * @author name：liujie <br>email: liujie@lljqiu.com
     **/
    public static byte[] setConnectResp(MsgConnect connectReq) {
        MsgConnectResp connectResp = new MsgConnectResp();
        connectResp.setTotalLength(12 + 4 + 16 + 1);//消息总长度，级总字节数:4+4+4(消息头)+6+16+1+4(消息主体)
        connectResp.setCommandId(MsgCommand.CMPP_CONNECT_RESP);//标识创建连接
        connectResp.setSequenceId(connectReq.getSequenceId());//序列，由我们指定
        int status = 0x0000;
        try {
            checkConnectRequest(connectReq);
        } catch (GateWayException e) {
            status = e.getErrorCode();
        }
        connectResp.setStatus(status);
        connectResp.setAuthenticatorISMG(connectReq.getAuthenticatorSource());
        connectReq.setVersion(connectReq.getVersion());
        logger.debug("{}响应消息{}", MsgCommand.CMPP_CONNECT_RESP,
                ToStringBuilder.reflectionToString(connectResp));
        return connectResp.toByteArry();
    }
}
