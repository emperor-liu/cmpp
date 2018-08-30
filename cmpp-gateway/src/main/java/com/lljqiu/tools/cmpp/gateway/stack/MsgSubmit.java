/**
 * Project Name cmpp-gateway
 * File Name package-info.java
 * Package Name com.lljqiu.tools.cmpp.gateway.stack
 * Create Time 2018年3月19日
 * Create by name：liujie -- email: liujie@lljqiu.com
 * Copyright © 2015, 2017, www.lljqiu.com. All rights reserved.
 */
package com.lljqiu.tools.cmpp.gateway.stack;

/**
 * 
 * Submit消息结构定义
 */
public class MsgSubmit extends MsgHead {
    private long          msgId              = 0;                                //信息标示
    private byte          pkTotal            = 0x01;                             //相同的msgId总数，从1开始
    private byte          pkNumber           = 0x01;                             //想用的msgId序号，从1开始
    private byte          registeredDelivery = 0x00;                             //是否要求返回状态报告，0不需要，1需要
    private byte          msgLevel           = 0x01;                             //信息级别
    private String        serviceId          = "";                               //业务标示，企业代码QYN4799901
    private byte          feeUserType        = 0x00;                             //用户计费类型， 谁接收，计谁的费
    private String        feeTerminalId      = "";                               //被计费的号码
    private byte          feeTerminalType    = 0x00;                             //被计费号码的类型，真实号码或者虚拟号码
    private byte          tpPId              = 0x00;
    private byte          tpUdhi             = 0x00;
    private byte          msgFmt             = 0x0f;                             //信息格式 15含GB汉字
    private String        msgSrc;

    // 01：对“计费用户号码”免费；
    // 02：对“计费用户号码”按条计信息费；
    // 03：对“计费用户号码”按包月收取信息费
    private String        feeType            = "01";                             //资费类型， 默认为按条计费
    private String        feeCode            = "5";
    private String        valIdTime          = "";                               // 暂不支持
    private String        atTime             = "";                               // 暂不支持
    // SP的服务代码或前缀为服务代码的长号码,
    // 网关将该号码完整的填到SMPP协议Submit_SM消息相应的source_addr字段，该号码最终在用户手机上显示为短消息的主叫号码。
    private String        srcId;
    private byte          destUsrTl          = 0x01;                             // 不支持群发
    private String        destTerminalId;                                        // 接收手机号码，
    private byte          destTerminalType   = 0x00;                             // 真实号码
    private byte          msgLength;
    private String        msgContent;                                            //信息内容
    // 点播业务使用的LinkID，非点播类业务的MT流程不使用该字段
    private String        linkID             = "";

    public long getMsgId() {
        return msgId;
    }

    public void setMsgId(long msgId) {
        this.msgId = msgId;
    }

    public byte getPkTotal() {
        return pkTotal;
    }

    public void setPkTotal(byte pkTotal) {
        this.pkTotal = pkTotal;
    }

    public byte getPkNumber() {
        return pkNumber;
    }

    public void setPkNumber(byte pkNumber) {
        this.pkNumber = pkNumber;
    }

    public byte getRegisteredDelivery() {
        return registeredDelivery;
    }

    public void setRegisteredDelivery(byte registeredDelivery) {
        this.registeredDelivery = registeredDelivery;
    }

    public byte getMsgLevel() {
        return msgLevel;
    }

    public void setMsgLevel(byte msgLevel) {
        this.msgLevel = msgLevel;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public byte getFeeUserType() {
        return feeUserType;
    }

    public void setFeeUserType(byte feeUserType) {
        this.feeUserType = feeUserType;
    }

    public String getFeeTerminalId() {
        return feeTerminalId;
    }

    public void setFeeTerminalId(String feeTerminalId) {
        this.feeTerminalId = feeTerminalId;
    }

    public byte getFeeTerminalType() {
        return feeTerminalType;
    }

    public void setFeeTerminalType(byte feeTerminalType) {
        this.feeTerminalType = feeTerminalType;
    }

    public byte getTpPId() {
        return tpPId;
    }

    public void setTpPId(byte tpPId) {
        this.tpPId = tpPId;
    }

    public byte getTpUdhi() {
        return tpUdhi;
    }

    public void setTpUdhi(byte tpUdhi) {
        this.tpUdhi = tpUdhi;
    }

    public byte getMsgFmt() {
        return msgFmt;
    }

    public void setMsgFmt(byte msgFmt) {
        this.msgFmt = msgFmt;
    }

    public String getMsgSrc() {
        return msgSrc;
    }

    public void setMsgSrc(String msgSrc) {
        this.msgSrc = msgSrc;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getFeeCode() {
        return feeCode;
    }

    public void setFeeCode(String feeCode) {
        this.feeCode = feeCode;
    }

    public String getValIdTime() {
        return valIdTime;
    }

    public void setValIdTime(String valIdTime) {
        this.valIdTime = valIdTime;
    }

    public String getAtTime() {
        return atTime;
    }

    public void setAtTime(String atTime) {
        this.atTime = atTime;
    }

    public String getSrcId() {
        return srcId;
    }

    public void setSrcId(String srcId) {
        this.srcId = srcId;
    }

    public byte getDestUsrTl() {
        return destUsrTl;
    }

    public void setDestUsrTl(byte destUsrTl) {
        this.destUsrTl = destUsrTl;
    }

    public String getDestTerminalId() {
        return destTerminalId;
    }

    public void setDestTerminalId(String destTerminalId) {
        this.destTerminalId = destTerminalId;
    }

    public byte getDestTerminalType() {
        return destTerminalType;
    }

    public void setDestTerminalType(byte destTerminalType) {
        this.destTerminalType = destTerminalType;
    }

    public byte getMsgLength() {
        return msgLength;
    }

    public void setMsgLength(byte msgLength) {
        this.msgLength = msgLength;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getLinkID() {
        return linkID;
    }

    public void setLinkID(String linkID) {
        this.linkID = linkID;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "MsgSubmit [msgId=" + msgId + ", pkTotal=" + pkTotal + ", pkNumber=" + pkNumber
                + ", registeredDelivery=" + registeredDelivery + ", msgLevel=" + msgLevel
                + ", serviceId=" + serviceId + ", feeUserType=" + feeUserType + ", feeTerminalId="
                + feeTerminalId + ", feeTerminalType=" + feeTerminalType + ", tpPId=" + tpPId
                + ", tpUdhi=" + tpUdhi + ", msgFmt=" + msgFmt + ", msgSrc=" + msgSrc + ", feeType="
                + feeType + ", feeCode=" + feeCode + ", valIdTime=" + valIdTime + ", atTime="
                + atTime + ", srcId=" + srcId + ", destUsrTl=" + destUsrTl + ", destTerminalId="
                + destTerminalId + ", destTerminalType=" + destTerminalType + ", msgLength="
                + msgLength + ", msgContent=" + msgContent + ", linkID=" + linkID + "]";
    }
    
    
}
