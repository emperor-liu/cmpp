/**
 * Project Name cmpp-client
 * File Name package-info.java
 * Package Name com.lljqiu.tools.cmpp.gateway.stack
 * Create Time 2018年3月19日
 * Create by name：liujie -- email: liujie@lljqiu.com
 * Copyright © 2015, 2017, www.lljqiu.com. All rights reserved.
 */
package com.lljqiu.tools.cmpp.client.stack;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.lljqiu.tools.cmpp.client.utils.MsgUtils;

/**
 * SP请求连接到ISMG消息体定义CMPP_CONNECT操作的目的是SP向ISMG注册作为一个合法SP身份，
 * 若注册成功后即建立了应用层的连接，此后SP可以通过此ISMG接收和发送短信。<br/>
 * Source_Addr:Octet String	源地址，此处为SP_Id，即SP的企业代码。<br/>
 * AuthenticatorSource:Octet String	用于鉴别源地址。其值通过单向MD5 hash计算得出，表示如下：
 * AuthenticatorSource =MD5（Source_Addr+9 字节的0 +shared secret+timestamp）
 * Shared secret 由中国移动与源地址实体事先商定，timestamp格式为：MMDDHHMMSS，即月日时分秒，10位。<br/>
 * Version:Unsigned Integer	双方协商的版本号(高位4bit表示主版本号,低位4bit表示次版本号)，对于3.0的版本，高4bit为3，低4位为0<br/>
 * Timestamp:Unsigned Integer	时间戳的明文,由客户端产生,格式为MMDDHHMMSS，即月日时分秒，10位数字的整型，右对齐 。<br/>
 * Create by: name：liujie <br>email: liujie@lljqiu.com <br>
 * Create Time: 2017年5月12日<br>
 */
public class MsgConnect extends MsgHead {
    private String sourceAddr;          //源地址，此处为SP_Id，即SP的企业代码。
    private byte[] authenticatorSource; //用于鉴别源地址。其值通过单向MD5 hash计算得出，表示如下：AuthenticatorSource = MD5（Source_Addr+9 字节的0 +shared secret+timestamp） Shared secret 由中国移动与源地址实体事先商定，timestamp格式为：MMDDHHMMSS，即月日时分秒，10位。
    private byte   version;             //双方协商的版本号(高位4bit表示主版本号,低位4bit表示次版本号)，对于3.0的版本，高4bit为3，低4位为0
    private int    timestamp;           //时间戳的明文,由客户端产生,格式为MMDDHHMMSS，即月日时分秒，10位数字的整型，右对齐 。

    public byte[] toByteArry() {
        ByteArrayOutputStream bous = new ByteArrayOutputStream();
        DataOutputStream dous = new DataOutputStream(bous);
        try {
            dous.writeInt(this.getTotalLength());
            dous.writeInt(this.getCommandId());
            dous.writeInt(this.getSequenceId());
            MsgUtils.writeString(dous, this.sourceAddr, 6);
            dous.write(authenticatorSource);
            dous.writeByte(0x30);
            dous.writeInt(timestamp);
            dous.close();
        } catch (IOException e) {
            logger.error("封装链接二进制数组失败。");
        }
        return bous.toByteArray();
    }
    
    public String getSourceAddr() {
        return sourceAddr;
    }

    public void setSourceAddr(String sourceAddr) {
        this.sourceAddr = sourceAddr;
    }

    public byte[] getAuthenticatorSource() {
        return authenticatorSource;
    }

    public void setAuthenticatorSource(byte[] authenticatorSource) {
        this.authenticatorSource = authenticatorSource;
    }

    public byte getVersion() {
        return version;
    }

    public void setVersion(byte version) {
        this.version = version;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }
}
