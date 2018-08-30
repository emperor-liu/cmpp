/**
 * Project Name pushServer
 * File Name MessageFactory.java
 * Package Name com.lljqiu.tools.pushServer.context
 * Create Time 2018年3月15日
 * Create by name：liujie -- email: jie_liu1@asdc.com.cn
 * Copyright © 2006, 2017, ASDC DAI. All rights reserved.
 */
package com.lljqiu.tools.cmpp.gateway.context;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.lljqiu.tools.cmpp.gateway.action.ActionService;
import com.lljqiu.tools.cmpp.gateway.stack.MsgCommand;

/** 
 * ClassName: MessageFactory.java <br>
 * Description: 消息工厂<br>
 * @author name：liujie <br>email: jie_liu1@asdc.com.cn <br>
 * @date: 2018年3月15日<br>
 */
public class MessageFactory {
private static String BASE_PACKAGE = "com.lljqiu.tools.cmpp.gateway.action.";
    
    public final static String CLASS_HEAD = "Action";
    
    /**
     * 缓存存在的class信息
     */
    private static final Map<String, Class<?>> clazz = new ConcurrentHashMap<String, Class<?>>();

    /**
     * 缓存不存在的class信息
     */
    private static final Map<String, Boolean> clazz_null = new ConcurrentHashMap<String, Boolean>();

    
    
    protected static Class<?> getClazz(String head, int id) {
        String key = head + id;
        Class<?> c = clazz.get(key);
        if (c == null) {
            if (clazz_null.get(key) != null)
                return null;
            try {
                c = Class.forName(getBasePackage(head) + generateSubPackage(head, id));
            } catch (ClassNotFoundException e) {
                clazz_null.put(key, true);
                return null;
            }
            clazz.put(key, c);
        }
        return c;
    }
    
    /**
     * 获取package
     *
     * @param head
     * @return
     */
    private static String getBasePackage(String head) {
        return BASE_PACKAGE;
    }

    /**
     * 按规则 组装全局 类名
     *
     * @param head
     * @param commandCode
     * @return
     */
    private static String generateSubPackage(String head, int commandCode) {
        return head + commandCode;
    }

    /** 
     * Description：
     * @param cid
     * @return
     * @return ActionService
     * @author name：liujie <br>email: jie_liu1@asdc.com.cn
     **/
    public static ActionService createService(int msgid) throws Exception {
        int id = 001;
        switch (msgid) {
            case MsgCommand.CMPP_CONNECT:
                id = 001;
                break;
            case MsgCommand.CMPP_SUBMIT:
                id = 002;
                break;
            default:
                break;
        }
        Class<?> c = getClazz(CLASS_HEAD, id);
        if (c == null)
            return null;
        else
            return (ActionService) c.newInstance();
    }
}
