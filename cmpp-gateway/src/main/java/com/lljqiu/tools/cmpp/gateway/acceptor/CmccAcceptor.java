package com.lljqiu.tools.cmpp.gateway.acceptor;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lljqiu.tools.cmpp.gateway.codec.MPCodecFactory;
import com.lljqiu.tools.cmpp.gateway.context.MessageHandler;
import com.lljqiu.tools.cmpp.gateway.filter.ConnectionFilter;
import com.lljqiu.tools.cmpp.gateway.filter.KeepAliveMessageFactoryImpl;
import com.lljqiu.tools.cmpp.gateway.utils.ReadYamlUtils;

/**
 * Created by liujie on 2018/3/19.
 */
public class CmccAcceptor {
    private static Logger LOGGER = LoggerFactory.getLogger(CmccAcceptor.class);

    public void start(){
        try {
            IoAcceptor acceptor = new NioSocketAcceptor();
            acceptor.getFilterChain().addLast("logger", new LoggingFilter());
            acceptor.getFilterChain().addLast("myfliter", new ConnectionFilter());
            acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new MPCodecFactory("UTF-8")));// 指定编码过滤器
            acceptor.getFilterChain().addLast("threadPool", new ExecutorFilter(Executors.newCachedThreadPool()));
            acceptor.getSessionConfig().setReadBufferSize(2048*5000);//发送缓冲区10M
//            acceptor.getSessionConfig().set
            KeepAliveMessageFactoryImpl kamfi = new KeepAliveMessageFactoryImpl();
            KeepAliveFilter kaf = new KeepAliveFilter(kamfi, IdleStatus.BOTH_IDLE);
            /** 是否回发 */
            kaf.setForwardEvent(true);
            acceptor.getFilterChain().addLast("heart", kaf);

            acceptor.setHandler(new MessageHandler());// 指定业务逻辑处理器
            acceptor.setDefaultLocalAddress(new InetSocketAddress(ReadYamlUtils.getGatewayPort()));// 设置端口号

            acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 600);

            acceptor.bind();// 启动监听

            LOGGER.info("<server start success, port is ={}>" , ReadYamlUtils.getGatewayPort());

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }
}
