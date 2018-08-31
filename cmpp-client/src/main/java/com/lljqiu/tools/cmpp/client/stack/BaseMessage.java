/**
 * Project Name cmpp-client
 * File Name package-info.java
 * Package Name com.lljqiu.tools.cmpp.client.stack
 * Create Time 2018年8月30日
 * Create by name：liujie -- email: liujie@lljqiu.com
 * Copyright © 2015, 2017, www.lljqiu.com. All rights reserved.
 */
package com.lljqiu.tools.cmpp.client.stack;

import org.apache.mina.core.buffer.IoBuffer;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by liujie on 2018/3/19.
 */
public class BaseMessage {

    private Integer  MsgCommand;
    private Integer  totalLength;
    private Integer  sequenceId;
    private IoBuffer ioBuffer;
    private JSONObject bodys;
    

    @Override
	public String toString() {
		return "BaseMessage [MsgCommand=" + MsgCommand + ", totalLength=" + totalLength + ", sequenceId=" + sequenceId
				+ ", bodys=" + bodys.toJSONString() + "]";
	}

	public JSONObject getBodys() {
        return bodys;
    }

    public void setBodys(JSONObject bodys) {
        this.bodys = bodys;
    }

    public Integer getMsgCommand() {
        return MsgCommand;
    }

    public void setMsgCommand(Integer msgCommand) {
        MsgCommand = msgCommand;
    }

    public Integer getTotalLength() {
        return totalLength;
    }

    public void setTotalLength(Integer totalLength) {
        this.totalLength = totalLength;
    }

    public Integer getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(Integer sequenceId) {
        this.sequenceId = sequenceId;
    }

    public IoBuffer getIoBuffer() {
        return ioBuffer;
    }

    public void setIoBuffer(IoBuffer ioBuffer) {
        this.ioBuffer = ioBuffer;
    }

}
