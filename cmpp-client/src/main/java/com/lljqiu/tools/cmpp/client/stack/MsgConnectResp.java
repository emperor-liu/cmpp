/**
 * Project Name cmpp-client
 * File Name MsgConnectResp.java
 * Package Name com.lljqiu.tools.cmpp.client.stack
 * Create Time 2018年8月31日
 * Create by name：liujie -- email: liujie@lljqiu.com
 * Copyright © 2015, 2017, www.lljqiu.com. All rights reserved.
 */
package com.lljqiu.tools.cmpp.client.stack;

/** 
 * ClassName: MsgConnectResp.java <br>
 * Description: 
 * <li>status响应状态状态 0：正确 1：消息结构错 2：非法源地址 3：认证错 4：版本太高 5~ ：其他错误</li>
 * <li>authenticatorISMG SMG认证码，用于鉴别ISMG。 其值通过单向MD5 hash计算得出，
 * 表示如下： AuthenticatorISMG =MD5（Status+AuthenticatorSource+shared secret），
 * Shared secret 由中国移动与源地址实体事先商定，</li>
 * <li>AuthenticatorSource为源地址实体发送给ISMG的对应消息CMPP_Connect中的值。 认证出错时，此项为空。</li>
 * <li>version服务器支持的最高版本号，对于3.0的版本，高4bit为3，低4位为0 </li> 
 * Create by: name：liujie <br>email: liujie@lljqiu.com <br>
 * Create Time: 2018年8月31日<br>
 */
public class MsgConnectResp extends MsgHead {
	private int status;
	private String statusStr;
	private byte[] authenticatorISMG;
	private byte version;                           
    
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
        switch (status) {
            case 0:
                statusStr = "正确";
                break;
            case 1:
                statusStr = "消息结构错";
                break;
            case 2:
                statusStr = "非法源地址";
                break;
            case 3:
                statusStr = "认证错";
                break;
            case 4:
                statusStr = "版本太高";
                break;
            case 5:
                statusStr = "其他错误";
                break;
            default:
                statusStr = status + ":未知";
                break;
        }
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

    public String getStatusStr() {
        return statusStr;
    }
}
