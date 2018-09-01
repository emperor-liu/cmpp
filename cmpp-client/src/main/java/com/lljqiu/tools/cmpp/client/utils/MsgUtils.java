/**
 * Project Name cmpp-client
 * File Name package-info.java
 * Package Name com.lljqiu.tools.cmpp.gateway.utils
 * Create Time 2018年3月19日
 * Create by name：liujie -- email: liujie@lljqiu.com
 * Copyright © 2015, 2017, www.lljqiu.com. All rights reserved.
 */
package com.lljqiu.tools.cmpp.client.utils;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lljqiu.tools.cmpp.client.stack.MsgSubmit;

/**
 * 短信接口辅助工具类
 */
public class MsgUtils {
	private static Logger logger = LoggerFactory.getLogger(MsgUtils.class);
	private static int sequenceId = 0;// 序列编号

	/**
	 * 序列 自增
	 */
	public static int getSequence() {
		++sequenceId;
		if (sequenceId > 255) {
			sequenceId = 0;
		}
		return sequenceId;
	}

	/**
	 * 时间戳的明文,由客户端产生,格式为MMDDHHMMSS，即月日时分秒，10位数字的整型，右对齐 。
	 */
	public static String getTimestamp() {
		DateFormat format = new SimpleDateFormat("MMddhhmmss");
		return format.format(new Date());
	}

	/**
	 * 用于鉴别源地址。其值通过单向MD5 hash计算得出，表示如下： AuthenticatorSource = MD5（Source_Addr+9
	 * 字节的0 +shared secret+timestamp） Shared secret
	 * 由中国移动与源地址实体事先商定，timestamp格式为：MMDDHHMMSS，即月日时分秒，10位。
	 * 
	 * @return
	 */
	public static byte[] getAuthenticatorSource(String spId, String secret, String timestamp) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			String data = spId + "\0\0\0\0\0\0\0\0\0" + secret + timestamp;
			byte[] digest = md5.digest(data.getBytes());
			return digest;
		} catch (NoSuchAlgorithmException e) {
			logger.error("SP链接到ISMG拼接AuthenticatorSource失败:{}", e);
			return null;
		}
	}

	/**
	 * 向流中写入指定字节长度的字符串，不足时补0
	 * 
	 * @param dous:要写入的流对象
	 * @param s:要写入的字符串
	 * @param len:写入长度,不足补0
	 */
	public static void writeString(DataOutputStream dous, String s, int len) {

		try {
			byte[] data = s.getBytes("gb2312");
			if (data.length > len) {
				logger.error("向流中写入的字符串超长！要写长度" + len + " 字符串是:" + s);
			}
			int srcLen = data.length;
			dous.write(data);
			while (srcLen < len) {
				dous.write('\0');
				srcLen++;
			}
		} catch (IOException e) {
			logger.error("向流中写入指定字节长度的字符串失败：" + e.getMessage());
		}
	}

	/**
	 * 从流中读取指定长度的字节，转成字符串返回
	 * 
	 * @param ins:要读取的流对象
	 * @param len:要读取的字符串长度
	 * @return:读取到的字符串
	 */
	public static String readString(java.io.DataInputStream ins, int len) {
		byte[] b = new byte[len];
		try {
			ins.read(b);
			String s = new String(b);
			s = s.trim();
			return s;
		} catch (IOException e) {
			return "";
		}
	}

	/**
	 * 截取字节
	 * 
	 * @param msg
	 * @param start
	 * @param end
	 * @return
	 */
	public static byte[] getMsgBytes(byte[] msg, int start, int end) {
		byte[] msgByte = new byte[end - start];
		int j = 0;
		for (int i = start; i < end; i++) {
			msgByte[j] = msg[i];
			j++;
		}
		return msgByte;
	}

	/**
	 * UCS2解码
	 * 
	 * @param src
	 *            UCS2 源串
	 * @return 解码后的UTF-16BE字符串
	 */
	public static String DecodeUCS2(String src) {
		byte[] bytes = new byte[src.length() / 2];
		for (int i = 0; i < src.length(); i += 2) {
			bytes[i / 2] = (byte) (Integer.parseInt(src.substring(i, i + 2), 16));
		}
		String reValue = "";
		try {
			reValue = new String(bytes, "UTF-16BE");
		} catch (UnsupportedEncodingException e) {
			reValue = "";
		}
		return reValue;

	}

	/**
	 * UCS2编码
	 * 
	 * @param src
	 *            UTF-16BE编码的源串
	 * @return 编码后的UCS2串
	 */
	public static String EncodeUCS2(String src) {
		byte[] bytes;
		try {
			bytes = src.getBytes("UTF-16BE");
		} catch (UnsupportedEncodingException e) {
			bytes = new byte[0];
		}
		StringBuffer reValue = new StringBuffer();
		StringBuffer tem = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			tem.delete(0, tem.length());
			tem.append(Integer.toHexString(bytes[i] & 0xFF));
			if (tem.length() == 1) {
				tem.insert(0, '0');
			}
			reValue.append(tem);
		}
		return reValue.toString().toUpperCase();
	}

	public static List<byte[]> toLongMessage(String msg, String cusMsisdn) {
		try {
			byte[] allByte = msg.getBytes("UTF-16BE");
			int msgLength = allByte.length;
			int maxLength = 140;
			int msgSendCount = msgLength % (maxLength - 6) == 0 ? msgLength / (maxLength - 6)
					: msgLength / (maxLength - 6) + 1;
			// 短信息内容头拼接
			byte[] msgHead = new byte[6];
			msgHead[0] = 0x05;
			msgHead[1] = 0x00;
			msgHead[2] = 0x03;
			msgHead[3] = (byte) MsgUtils.getSequence();
			msgHead[4] = (byte) msgSendCount;
			msgHead[5] = 0x01;
			int seqId = MsgUtils.getSequence();
			List<byte[]> dataList=new ArrayList<byte[]>();
			for (int i = 0; i < msgSendCount; i++) {
				// msgHead[3]=(byte)MsgUtils.getSequence();
				msgHead[5] = (byte) (i + 1);
				byte[] needMsg = null;
				// 消息头+消息内容拆分
				if (i != msgSendCount - 1) {
					int start = (maxLength - 6) * i;
					int end = (maxLength - 6) * (i + 1);
					needMsg = MsgUtils.getMsgBytes(allByte, start, end);
				} else {
					int start = (maxLength - 6) * i;
					int end = allByte.length;
					needMsg = MsgUtils.getMsgBytes(allByte, start, end);
				}
				int subLength = needMsg.length + msgHead.length;
				byte[] sendMsg = new byte[needMsg.length + msgHead.length];
				System.arraycopy(msgHead, 0, sendMsg, 0, 6);
				System.arraycopy(needMsg, 0, sendMsg, 6, needMsg.length);
				MsgSubmit submit = new MsgSubmit();
				submit.setTotalLength(12 + 8 + 1 + 1 + 1 + 1 + 10 + 1 + 32 + 1 + 1 + 1 + 1 + 6 + 2 + 6 + 17 + 17 + 21
						+ 1 + 32 + 1 + 1 + subLength + 20);
				submit.setCommandId(MsgCommand.CMPP_SUBMIT);
				submit.setSequenceId(seqId);
				submit.setPkTotal((byte) msgSendCount);
				submit.setPkNumber((byte) (i + 1));
				submit.setRegisteredDelivery((byte) 0x00);
				submit.setMsgLevel((byte) 0x01);
				submit.setFeeUserType((byte) 0x00);
				submit.setFeeTerminalId("");
				submit.setFeeTerminalType((byte) 0x00);
				submit.setTpPId((byte) 0x00);
				submit.setTpUdhi((byte) 0x01);
				submit.setMsgFmt((byte) 0x08);
				submit.setMsgSrc(Constants.ServerConfig.gatewayConfig.getSpId());
				submit.setSrcId(Constants.ServerConfig.gatewayConfig.getSpCode());
				submit.setDestTerminalId(cusMsisdn);
				submit.setMsgLength((byte) subLength);
				submit.setMsgContent(sendMsg);
				dataList.add(submit.toByteArry());
			}
			return dataList;
		} catch (UnsupportedEncodingException e) {
			logger.error("拼接 sendLongMsg 错误{}", e);
			return null;
		}
	}

	public static byte[] toShortMessage(String msg, String cusMsisdn) {
		try {
			int seq = MsgUtils.getSequence();
			MsgSubmit submit = new MsgSubmit();
			submit.setTotalLength(Constants.MessageTotalLength.SUBMIT + msg.getBytes("GBK").length);
			submit.setCommandId(MsgCommand.CMPP_SUBMIT);// 8
			submit.setSequenceId(seq); // 1
			submit.setPkTotal((byte) 0x01);
			submit.setPkNumber((byte) 0x01);
			submit.setRegisteredDelivery((byte) 0x00);
			submit.setMsgLevel((byte) 0x01);
			submit.setFeeUserType((byte) 0x00);
			submit.setFeeTerminalId("");
			submit.setFeeTerminalType((byte) 0x00);
			submit.setTpPId((byte) 0x00);
			submit.setTpUdhi((byte) 0x00);
			submit.setMsgFmt((byte) 0x0f);
			submit.setMsgSrc(Constants.ServerConfig.gatewayConfig.getSpId());
			submit.setSrcId(Constants.ServerConfig.gatewayConfig.getSpCode());
			submit.setDestTerminalId(cusMsisdn);
			
			submit.setMsgLength((byte) (msg.getBytes("GBK").length));
			submit.setMsgContent(msg.getBytes("GBK"));
			submit.setServiceId(Constants.ServerConfig.gatewayConfig.getServiceId());
			
			logger.debug("submit info{}",ToStringBuilder.reflectionToString(submit));
			return submit.toByteArry();
		} catch (UnsupportedEncodingException e) {
			logger.error("拼接shortmessage 错误{}", e);
			return null;
		}
	}
}
