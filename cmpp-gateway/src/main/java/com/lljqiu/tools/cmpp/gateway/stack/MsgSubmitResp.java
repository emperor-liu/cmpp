/**
 * Project Name cmpp-gateway
 * File Name package-info.java
 * Package Name com.lljqiu.tools.cmpp.gateway.stack
 * Create Time 2018年3月19日
 * Create by name：liujie -- email: liujie@lljqiu.com
 * Copyright © 2015, 2017, www.lljqiu.com. All rights reserved.
 */
package com.lljqiu.tools.cmpp.gateway.stack;

import org.apache.log4j.Logger;

/** 
 * ClassName: MsgSubmitResp.java <br>
 * Description: Submit消息结构应答定义<br>
 * Create by: name：liujie <br>email: jie_liu1@asdc.com.cn <br>
 * Create Time: 2017年4月24日<br>
 */
public class MsgSubmitResp extends MsgHead {
    private static Logger logger = Logger.getLogger(MsgSubmitResp.class);
    private long          msgId;
    private int           result;                                        //结果 0：正确 1：消息结构错 2：命令字错 3：消息序号重复 4：消息长度错 5：资费代码错 6：超过最大信息长 7：业务代码错 8：流量控制错 9：本网关不负责服务此计费号码 10：Src_Id错误 11：Msg_src错误 12：Fee_terminal_Id错误 13：Dest_terminal_Id错误

    public long getMsgId() {
        return msgId;
    }

    public void setMsgId(long msgId) {
        this.msgId = msgId;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
