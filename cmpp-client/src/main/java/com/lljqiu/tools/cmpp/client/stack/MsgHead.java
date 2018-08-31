/**
 * Project Name cmpp-client
 * File Name MsgHead.java
 * Package Name com.lljqiu.tools.cmpp.client.stack
 * Create Time 2018年8月30日
 * Create by name：liujie -- email: liujie@lljqiu.com
 * Copyright © 2015, 2017, www.lljqiu.com. All rights reserved.
 */
package com.lljqiu.tools.cmpp.client.stack;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * ClassName: MsgHead.java <br>
 * Description: <br>
 * Create by: name：liujie <br>email: liujie@lljqiu.com <br>
 * Create Time: 2018年8月30日<br>
 */
public class MsgHead {
    protected Logger logger = LoggerFactory.getLogger(MsgHead.class);
    private int    totalLength;                             //Unsigned Integer 消息总长度
    private int    commandId;                               //Unsigned Integer 命令类型
    private int    sequenceId;                              //Unsigned Integer 消息流水号,顺序累加,步长为1,循环使用（一对请求和应答消息的流水号必须相同）

    public byte[] toByteArry() {
        ByteArrayOutputStream bous = new ByteArrayOutputStream();
        DataOutputStream dous = new DataOutputStream(bous);
        try {
            dous.writeInt(this.getTotalLength());
            dous.writeInt(this.getCommandId());
            dous.writeInt(this.getSequenceId());
            dous.close();
        } catch (IOException e) {
            logger.error("封装CMPP消息头二进制数组失败。");
        }
        return bous.toByteArray();
    }

    public MsgHead(byte[] data) {
        ByteArrayInputStream bins = new ByteArrayInputStream(data);
        DataInputStream dins = new DataInputStream(bins);
        try {
            this.setTotalLength(data.length + 4);
            this.setCommandId(dins.readInt());
            this.setSequenceId(dins.readInt());
            dins.close();
            bins.close();
        } catch (IOException e) {
        }
    }

    public MsgHead() {
        super();
    }

    /**
	 * @param totalLength2
     * @param commandId 
	 * @param sequenceId2
	 */
	public MsgHead(Integer totalLength,  Integer commandId, Integer sequenceId) {
		super();
		this.totalLength = totalLength;
		this.commandId = commandId;
		this.sequenceId = sequenceId;
	}
	
	public void setHead(MsgHead head){
    	this.totalLength = head.getTotalLength();
		this.commandId = head.getCommandId();
		this.sequenceId = head.getSequenceId();
    }

	public int getTotalLength() {
        return totalLength;
    }

    public void setTotalLength(int totalLength) {
        this.totalLength = totalLength;
    }

    public int getCommandId() {
        return commandId;
    }

    public void setCommandId(int commandId) {
        this.commandId = commandId;
    }

    public int getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(int sequenceId) {
        this.sequenceId = sequenceId;
    }
}
