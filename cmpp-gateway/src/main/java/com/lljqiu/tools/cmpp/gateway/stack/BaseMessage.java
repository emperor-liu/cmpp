package com.lljqiu.tools.cmpp.gateway.stack;

import org.apache.mina.core.buffer.IoBuffer;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by liujie on 2018/3/19.
 */
public class BaseMessage {

	private String clientIp;
    private Integer  MsgCommand;
    private Integer  totalLength;
    private Integer  sequenceId;
    private IoBuffer ioBuffer;
    private JSONObject bodys;
    
    

    public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
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
