/**
 * Project Name cmpp-gateway
 * File Name package-info.java
 * Package Name com.lljqiu.tools.cmpp.gateway.stack
 * Create Time 2018年3月19日
 * Create by name：liujie -- email: liujie@lljqiu.com
 * Copyright © 2015, 2017, www.lljqiu.com. All rights reserved.
 */
package com.lljqiu.tools.cmpp.gateway.stack;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;

/**
 * ISMG以CMPP_CONNECT_RESP消息响应SP的链接请求。<br/>
 * status响应状态状态 0：正确 1：消息结构错 2：非法源地址 3：认证错 4：版本太高 5~ ：其他错误<br/>
 * authenticatorISMG SMG认证码，用于鉴别ISMG。 其值通过单向MD5 hash计算得出，表示如下： Authent
 * icatorISMG =MD5（Status+AuthenticatorSource+shared secret），Shared secret 由中国移动与源地址实体事先商定，
 * AuthenticatorSource为源地址实体发送给ISMG的对应消息CMPP_Connect中的值。 认证出错时，此项为空。<br/>
 * version服务器支持的最高版本号，对于3.0的版本，高4bit为3，低4位为0<br/>9 
 * Create by: name：liujie <br>email: liujie@lljqiu.com <br>
 * Create Time: 2017年5月12日<br>
 */
public class MsgConnectResp extends MsgHead {
    private static Logger logger = Logger.getLogger(MsgConnectResp.class);
    private int           status;                                         //响应状态状态 0：正确 1：消息结构错 2：非法源地址 3：认证错 4：版本太高 5~ ：其他错误
    private byte[]        authenticatorISMG;                              //	ISMG认证码，用于鉴别ISMG。 其值通过单向MD5 hash计算得出，表示如下： AuthenticatorISMG =MD5（Status+AuthenticatorSource+shared secret），Shared secret 由中国移动与源地址实体事先商定，AuthenticatorSource为源地址实体发送给ISMG的对应消息CMPP_Connect中的值。 认证出错时，此项为空。
    private byte          version;                                        //	服务器支持的最高版本号，对于3.0的版本，高4bit为3，低4位为0

    public byte[] toByteArry() {
        ByteArrayOutputStream bous = new ByteArrayOutputStream();
        DataOutputStream dous = new DataOutputStream(bous);
        try {
            dous.writeInt(this.getTotalLength());
            dous.writeInt(this.getCommandId());
            dous.writeInt(this.getSequenceId());
            dous.writeInt(status);
            dous.write(authenticatorISMG);
            dous.writeInt(version);
            dous.close();
        } catch (IOException e) {
            logger.error("封装链接二进制数组失败。");
        }
        return bous.toByteArray();
    }
    
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }

    public byte[] getAuthenticatorISMG() {
        return authenticatorISMG;
    }

    public void setAuthenticatorISMG(byte[] authenticatorISMG) {
        this.authenticatorISMG = authenticatorISMG;
    }

    public byte getVersion() {
        return version;
    }

    public void setVersion(byte version) {
        this.version = version;
    }

}
