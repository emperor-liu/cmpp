/**
 * Project Name cmpp-client
 * File Name package-info.java
 * Package Name com.lljqiu.tools.cmpp.gateway.codec
 * Create Time 2018年3月19日
 * Create by name：liujie -- email: liujie@lljqiu.com
 * Copyright © 2015, 2017, www.lljqiu.com. All rights reserved.
 */
package com.lljqiu.tools.cmpp.client.codec;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/** 
 * ClassName: MPCodecFactory.java <br>
 * Description: <br>
 * Create by: name：liujie <br>email: liujie@lljqiu.com <br>
 * Create Time: 2017年6月6日<br>
 */
public class MPCodecFactory implements ProtocolCodecFactory {

    /**
     * 编码器
     */
    private MPMessageEncoder encoder;
    /**
     * 解码器
     */
    private MPMessageDecoder decoder;

    public MPCodecFactory(String charset) {
        encoder = new MPMessageEncoder(charset);
        decoder = new MPMessageDecoder(charset);
    }

    public ProtocolEncoder getEncoder(IoSession session) throws Exception {
        return encoder;
    }

    public ProtocolDecoder getDecoder(IoSession session) throws Exception {
        return decoder;
    }

}
