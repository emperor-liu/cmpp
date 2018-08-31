/**
 * Project Name cmpp-gateway
 * File Name package-info.java
 * Package Name com.lljqiu.tools.cmpp.gateway.codec
 * Create Time 2018年3月19日
 * Create by name：liujie -- email: liujie@lljqiu.com
 * Copyright © 2015, 2017, www.lljqiu.com. All rights reserved.
 */
package com.lljqiu.tools.cmpp.gateway.codec;

import java.net.InetSocketAddress;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.lljqiu.tools.cmpp.gateway.action.ActionService;
import com.lljqiu.tools.cmpp.gateway.context.MessageFactory;
import com.lljqiu.tools.cmpp.gateway.stack.BaseMessage;
import com.lljqiu.tools.cmpp.gateway.stack.MsgCommand;
import com.lljqiu.tools.cmpp.gateway.stack.MsgConnect;
import com.lljqiu.tools.cmpp.gateway.stack.MsgHead;
import com.lljqiu.tools.cmpp.gateway.stack.MsgSubmit;
import com.lljqiu.tools.cmpp.gateway.utils.Utils;

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
        InetSocketAddress socketAddress = (InetSocketAddress) session.getRemoteAddress();
        message.setClientIp(socketAddress.getAddress().getHostAddress());
        
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
        	case MsgCommand.CMPP_ACTIVE_TEST:
        		logger.info("<{} active test,序列号：{}>" ,Utils.getNowData(), sequenceId);
	            break;
            case MsgCommand.CMPP_CONNECT:
            	MsgConnect msgConnect = service.readMessage(in);
                msgConnect.setHead(head);
                logger.info("<{} 链接短信网关,version:{},序列号：{}>" ,Utils.getNowData(), msgConnect.getVersion(), sequenceId);
                message.setBodys((JSONObject)JSONObject.toJSON(msgConnect));
                
                break;
            case MsgCommand.CMPP_SUBMIT:
            	MsgSubmit submitInfo = service.readMessage(in);
            	submitInfo.setHead(head);
                logger.info("<{} 链接短信网关,序列号：{}>" ,Utils.getNowData(), sequenceId);
                message.setBodys((JSONObject)JSONObject.toJSON(submitInfo));
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
