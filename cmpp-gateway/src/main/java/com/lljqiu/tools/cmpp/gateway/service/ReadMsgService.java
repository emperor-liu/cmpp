/**
 * Project Name cmpp-gateway
 * File Name ReadMsgService.java
 * Package Name com.lljqiu.tools.cmpp.gateway.service
 * Create Time 2018年3月19日
 * Create by name：liujie -- email: liujie@lljqiu.com
 * Copyright © 2015, 2017, www.lljqiu.com. All rights reserved.
 */
package com.lljqiu.tools.cmpp.gateway.service;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import org.apache.mina.core.buffer.IoBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lljqiu.tools.cmpp.gateway.stack.MsgConnect;
import com.lljqiu.tools.cmpp.gateway.stack.MsgHead;
import com.lljqiu.tools.cmpp.gateway.stack.MsgSubmit;

/** 
 * ClassName: ReadMsgService.java <br>
 * Description: <br>
 * @author name：liujie <br>email: liujie@lljqiu.com <br>
 * Create Time: 2018年3月19日<br>
 */
public class ReadMsgService {
    private static Logger    logger   = LoggerFactory.getLogger(ReadMsgService.class);

    @SuppressWarnings("finally")
    public static MsgHead readHead(byte[] data){
        MsgHead head = new MsgHead();
        ByteArrayInputStream bins = new ByteArrayInputStream(data);
        DataInputStream dins = new DataInputStream(bins);
        try {
            head.setTotalLength(data.length + 4);
            head.setCommandId(dins.readInt());
            head.setSequenceId(dins.readInt());
            dins.close();
            bins.close();
        } catch (IOException e) {
            logger.error("read message head error{}",e.getMessage());
        } finally {
            return head;
        }
    }
    
    /** 
     * Description：读取请求消息,并且根据connand返回响应
     * @throws IOException
     * @return void
     * @author name：liujie <br>email: liujie@lljqiu.com
     **/
    public static byte[] readRequestMessage(DataInputStream input, String spIp) throws IOException{
//        byte[] requestData = null;
//        int len = input.readInt();
//        if (null != input && 0 != len) {
//            byte[] data = new byte[len - 4]; //减4的目的是去除消息长度  头4个字节为消息的长度标识
//            input.read(data);
//            logger.debug("客户端发送内容为："+Arrays.toString(data));
//            requestData   = data;
//        }
//
//        byte[] result = null;
//        logger.debug("请求消息长度："+requestData.length);
//        MsgHead head = readHead(requestData);
//        logger.debug("请求头消息："+ToStringBuilder.reflectionToString(head));
//        switch (head.getCommandId()) {
//            case MsgCommand.CMPP_CONNECT:
//                MsgConnect connectReq = readConnect(requestData);
//                logger.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "链接短信网关,version:"
//                        + connectReq.getVersion() + " 序列号：" + connectReq.getSequenceId());
//                result = PutMsgService.setConnectResp(connectReq);
//                break;
//            case MsgCommand.CMPP_SUBMIT:
//                MsgSubmit submitInfo = readSubmit(requestData);
//                logger.info("{}链接短信网关,序列号：{}" ,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), submitInfo.getSequenceId());
//                break;
//        }
//        return result;
        return null;
    }
    
    /** 
     * Description：接收客户端发送的短信
     * @param requestData
     * @return MsgConnect
     * @author name：liujie <br>email: liujie@lljqiu.com <br>
     * Create Time: 2018年3月19日<br>
     * @throws IOException 
     **/
//    public static MsgSubmit readSubmit(IoBuffer requestData) throws IOException {
    public static MsgSubmit readSubmit(IoBuffer requestData) throws IOException {
        MsgSubmit submit = new MsgSubmit();

        //12+8+1+1+1+1+10+1+32+1+1+1+1+6+2+6+17+17+21+1+32+1+1+msg.getBytes("GBK").length+20

        // 数字表述字节，
        submit.setTotalLength(requestData.getInt()); //4
        submit.setCommandId(requestData.getInt());// 4
        submit.setSequenceId(requestData.getInt());//4
        submit.setMsgId(requestData.getLong());//8
        submit.setPkTotal(requestData.get());//1
        submit.setPkNumber(requestData.get());//1
        submit.setRegisteredDelivery(requestData.get());//1
        submit.setMsgLevel(requestData.get());//1
        byte[] serviceId = new byte[10];
        requestData.get(serviceId);
        submit.setServiceId(new String(serviceId)); //10
        byte[] feeTerminalId = new byte[32];
        requestData.get(feeTerminalId);
        submit.setFeeTerminalId(new String(feeTerminalId)); //32
        submit.setFeeTerminalType(requestData.get()); //1
        submit.setTpPId(requestData.get()); //1
        submit.setTpUdhi(requestData.get()); //1
        submit.setMsgFmt(requestData.get()); //1
        byte[] msgRsc = new byte[6];
        requestData.get(msgRsc);
        submit.setMsgSrc(new String(msgRsc));//6
        byte[] feeType = new byte[2];
        requestData.get(feeType);
        submit.setFeeType(new String(feeType));//2
        byte[] feeCode = new byte[6];
        requestData.get(feeCode);
        submit.setFeeCode(new String(feeCode));//6
        byte[] ValId_Time = new byte[17];
        requestData.get(ValId_Time);
        submit.setValIdTime(new String(ValId_Time));//17
        byte[] At_Time = new byte[17];
        requestData.get(At_Time);
        submit.setAtTime(new String(At_Time));//17
        byte[] src_id = new byte[21];
        requestData.get(src_id);
        submit.setSrcId(new String(src_id));//21
        byte DestUsrTl = requestData.get();
        submit.setDestUsrTl(DestUsrTl); //1
        Integer msisdnLength = (int) DestUsrTl * 32;
        byte[] Dest_terminal_Id = new byte[msisdnLength];
        requestData.get(Dest_terminal_Id);
        submit.setDestTerminalId(new String(Dest_terminal_Id));//32*DestUsrTl
        submit.setDestTerminalType(requestData.get());//1
        byte messageLength = requestData.get();
        submit.setMsgLength(messageLength);
        
        byte[] Msg_Content = new byte[messageLength];
        requestData.get(Msg_Content);
        submit.setMsgContent(new String(Msg_Content));
        byte[] LinkID = new byte[20];
        requestData.get(LinkID);
        submit.setLinkID(new String(LinkID));
        logger.debug("client submit info is {}",submit.toString());

//        return (JSONObject) JSONObject.toJSON(submit);
        return submit;
    }

    /** 
     * Description：读取线路连接消息
     * @param requestData
     * @return
     * @throws IOException
     * @return MsgConnect
     * @author name：liujie <br>email: liujie@lljqiu.com
     **/
    public static MsgConnect readConnect(IoBuffer requestData) throws IOException{
        MsgConnect msgConnect = new MsgConnect();

        try {
//            msgConnect.setTotalLength(requestData.getInt());
//            msgConnect.setCommandId(requestData.getInt());
//            msgConnect.setSequenceId(requestData.getInt());
            byte[] sourceAddr = new byte[6];
            requestData.get(sourceAddr);
            logger.debug("sourceAddr:"+new String(sourceAddr));
            msgConnect.setSourceAddr(new String(sourceAddr));
            byte[] aiByte = new byte[16];
            requestData.get(aiByte);
            msgConnect.setAuthenticatorSource(aiByte);

            msgConnect.setVersion(requestData.get());
            msgConnect.setTimestamp(requestData.get());

        } catch (Exception e) {
            logger.info("read msg connect error{}" + e.getMessage());
        }

        return msgConnect;
    }
}


//    MsgSubmit submit = new MsgSubmit();
//        if (requestData.length == 8 + 6 + 16 + 1 +4) {
//                }
//                //12+8+1+1+1+1+10+1+32+1+1+1+1+6+2+6+17+17+21+1+32+1+1+msg.getBytes("GBK").length+20
//                ByteArrayInputStream bins = new ByteArrayInputStream(requestData);
//                DataInputStream dins = new DataInputStream(bins);
//                // 数字表述字节，
//                submit.setTotalLength(requestData.length); //4
//                submit.setCommandId(dins.readInt());// 4
//                submit.setSequenceId(dins.readInt());//4
//                submit.setMsgId(dins.readLong());//8
//                submit.setPkTotal(dins.readByte());//1
//                submit.setPkNumber(dins.readByte());//1
//                submit.setRegisteredDelivery(dins.readByte());//1
//                submit.setMsgLevel(dins.readByte());//1
//                byte[] serviceId = new byte[10];
//                dins.read(serviceId);
//                submit.setServiceId(new String(serviceId)); //10
//                byte[] feeTerminalId = new byte[32];
//                dins.read(feeTerminalId);
//                submit.setFeeTerminalId(new String(feeTerminalId)); //32
//                submit.setFeeTerminalType(dins.readByte()); //1
//                submit.setTpPId(dins.readByte()); //1
//                submit.setTpUdhi(dins.readByte()); //1
//                submit.setMsgFmt(dins.readByte()); //1
//                byte[] msgRsc = new byte[6];
//                dins.read(msgRsc);
//                submit.setMsgSrc(new String(msgRsc));//6
//                byte[] feeType = new byte[2];
//                dins.read(feeType);
//                submit.setFeeType(new String(feeType));//2
//                byte[] feeCode = new byte[6];
//                dins.read(feeCode);
//                submit.setFeeCode(new String(feeCode));//6
//                byte[] ValId_Time = new byte[17];
//                dins.read(ValId_Time);
//                submit.setValIdTime(new String(ValId_Time));//17
//                byte[] At_Time = new byte[17];
//                dins.read(At_Time);
//                submit.setAtTime(new String(At_Time));//17
//                byte[] src_id = new byte[21];
//                dins.read(src_id);
//                submit.setSrcId(new String(src_id));//21
//                byte DestUsrTl = dins.readByte();
//                submit.setDestUsrTl(DestUsrTl); //1
//                Integer msisdnLength = (int) DestUsrTl * 32;
//                byte[] Dest_terminal_Id = new byte[msisdnLength];
//                dins.read(Dest_terminal_Id);
//                submit.setDestTerminalId(new String(Dest_terminal_Id));//32*DestUsrTl
//                submit.setDestTerminalType(dins.readByte());//1
//                byte messageLength = dins.readByte();
//                submit.setMsgLength(messageLength);
//
//                byte[] Msg_Content = new byte[messageLength];
//                dins.read(Msg_Content);
//                submit.setMsgContent(new String(Msg_Content));
//                byte[] LinkID = new byte[20];
//                dins.read(LinkID);
//                submit.setLinkID(new String(LinkID));
//                logger.debug("client submit info is {}",submit.toString());
//                return submit;