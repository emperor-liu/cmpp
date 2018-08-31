/**
 * Project Name cmpp-client
 * File Name package-info.java
 * Package Name com.lljqiu.tools.cmpp.gateway.codec
 * Create Time 2018年3月19日
 * Create by name：liujie -- email: liujie@lljqiu.com
 * Copyright © 2015, 2017, www.lljqiu.com. All rights reserved.
 */
package com.lljqiu.tools.cmpp.client.codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.lljqiu.tools.cmpp.client.action.ActionService;
import com.lljqiu.tools.cmpp.client.handler.MessageFactory;
import com.lljqiu.tools.cmpp.client.stack.BaseMessage;
import com.lljqiu.tools.cmpp.client.stack.MsgConnectResp;
import com.lljqiu.tools.cmpp.client.stack.MsgHead;
import com.lljqiu.tools.cmpp.client.utils.MsgCommand;
import com.lljqiu.tools.cmpp.client.utils.Utils;

/** 
 * ClassName: MPMessageDecoder.java <br>
 * Description: <br>
 * Create by: name：liujie <br>email: liujie@lljqiu.com <br>
 * Create Time: 2017年6月6日<br>
 */
public class MPMessageDecoder extends CumulativeProtocolDecoder {
    private static Logger logger   = LoggerFactory.getLogger(MPMessageDecoder.class);

    private String charset;

    public MPMessageDecoder() {
        this.setCharset("UTF-8");
    }

    public MPMessageDecoder(String charset) {
        this.setCharset(charset);
    }

    @Override
    protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
    	if (in.remaining() < 4) {
            return false;
        }
        in.mark();
        BaseMessage message = new BaseMessage();
        Integer totalLength = in.getInt();
        logger.debug("totalLength={}",totalLength);
        message.setTotalLength(totalLength);
        Integer commadnId = in.getInt();
        logger.debug("commadnId={}",commadnId);
        message.setMsgCommand(commadnId);
        Integer sequenceId = in.getInt();
        logger.debug("sequenceId={}",sequenceId);
        message.setSequenceId(sequenceId);
        
        MsgHead head = new MsgHead(totalLength,commadnId,sequenceId);
        ActionService service = MessageFactory.createService(commadnId);
        switch (commadnId) {
        	case MsgCommand.CMPP_ACTIVE_TEST_RESP:
        		logger.info("<心跳响应，不予处理>");
        		break;
        	case MsgCommand.CMPP_CONNECT_RESP:
        		logger.info("<链接网关响应>");
        		MsgConnectResp connectResp = service.readMessage(in);
        		connectResp.setHead(head);
        		message.setBodys((JSONObject)JSONObject.toJSON(connectResp));
        		logger.info("<{} 链接短信网关,状态:{} ,序列号：{}", Utils.getNowData() ,connectResp.getStatusStr(), sequenceId);
        		break;
        	case MsgCommand.CMPP_SUBMIT_RESP:
        		logger.info("<发送下行消息响应>");
        		service.readMessage(in);
        		break;
        }
        
        out.write(message);
        in.mark();

        if(in.remaining() > 0){
            return true;
        }
        
        return false;
    }

    /**
     * @return the charset
     */
    public String getCharset() {
        return charset;
    }

    /**
     * @param charset the charset to set
     */
    public void setCharset(String charset) {
        this.charset = charset;
    }

}
