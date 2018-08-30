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

import com.lljqiu.tools.cmpp.gateway.exception.GateWayException;
import com.lljqiu.tools.cmpp.gateway.stack.MsgSubmit;

/** 
 * ClassName: ConnectAction.java <br>
 * Description: <br>
 * Create by: name：liujie <br>email: liujie@lljqiu.com <br>
 * Create Time: 2018年8月30日<br>
 */
public class SubmitAction extends ActionFactoy {
	
	private final static Logger logger = LoggerFactory.getLogger(SubmitAction.class);

	/* (non-Javadoc)
	 * @see com.lljqiu.tools.cmpp.gateway.action.ActionFactoy#exec()
	 */
	@Override
	protected void exec() throws Exception {
		logger.info("接收 submi消息");
		logger.info("submit={}",message);
	}

	/* (non-Javadoc)
	 * @see com.lljqiu.tools.cmpp.gateway.action.ActionFactoy#readMessage()
	 */
	@SuppressWarnings({ "finally", "unchecked" })
	@Override
	protected <T> T readMessage() throws Exception {
        MsgSubmit submit = new MsgSubmit();

        try {
			//12+8+1+1+1+1+10+1+32+1+1+1+1+6+2+6+17+17+21+1+32+1+1+msg.getBytes("GBK").length+20
			submit.setMsgId(ioBuffer.getLong());//8
			submit.setPkTotal(ioBuffer.get());//1
			submit.setPkNumber(ioBuffer.get());//1
			submit.setRegisteredDelivery(ioBuffer.get());//1
			submit.setMsgLevel(ioBuffer.get());//1
			byte[] serviceId = new byte[10];
			ioBuffer.get(serviceId);
			submit.setServiceId(new String(serviceId)); //10
			byte[] feeTerminalId = new byte[32];
			ioBuffer.get(feeTerminalId);
			submit.setFeeTerminalId(new String(feeTerminalId)); //32
			submit.setFeeTerminalType(ioBuffer.get()); //1
			submit.setTpPId(ioBuffer.get()); //1
			submit.setTpUdhi(ioBuffer.get()); //1
			submit.setMsgFmt(ioBuffer.get()); //1
			byte[] msgRsc = new byte[6];
			ioBuffer.get(msgRsc);
			submit.setMsgSrc(new String(msgRsc));//6
			byte[] feeType = new byte[2];
			ioBuffer.get(feeType);
			submit.setFeeType(new String(feeType));//2
			byte[] feeCode = new byte[6];
			ioBuffer.get(feeCode);
			submit.setFeeCode(new String(feeCode));//6
			byte[] ValId_Time = new byte[17];
			ioBuffer.get(ValId_Time);
			submit.setValIdTime(new String(ValId_Time));//17
			byte[] At_Time = new byte[17];
			ioBuffer.get(At_Time);
			submit.setAtTime(new String(At_Time));//17
			byte[] src_id = new byte[21];
			ioBuffer.get(src_id);
			submit.setSrcId(new String(src_id));//21
			byte DestUsrTl = ioBuffer.get();
			submit.setDestUsrTl(DestUsrTl); //1
			Integer msisdnLength = (int) DestUsrTl * 32;
			byte[] Dest_terminal_Id = new byte[msisdnLength];
			ioBuffer.get(Dest_terminal_Id);
			submit.setDestTerminalId(new String(Dest_terminal_Id));//32*DestUsrTl
			submit.setDestTerminalType(ioBuffer.get());//1
			byte messageLength = ioBuffer.get();
			submit.setMsgLength(messageLength);
			
			byte[] Msg_Content = new byte[messageLength];
			ioBuffer.get(Msg_Content);
			submit.setMsgContent(new String(Msg_Content));
			byte[] LinkID = new byte[20];
			ioBuffer.get(LinkID);
			submit.setLinkID(new String(LinkID));
			logger.debug("client submit info is {}",submit.toString());
		} catch (Exception e) {
			logger.error("read msg submit error" + e.getMessage());
			throw new GateWayException("read msg submit error" + e.getMessage());
		}finally {
			return (T) submit;
		}

    }

}
