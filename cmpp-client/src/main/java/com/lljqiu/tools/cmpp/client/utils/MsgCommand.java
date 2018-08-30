/**
 * Project Name cmpp-gateway
 * File Name package-info.java
 * Package Name com.lljqiu.tools.cmpp.gateway.stack
 * Create Time 2018年3月19日
 * Create by name：liujie -- email: liujie@lljqiu.com
 * Copyright © 2015, 2017, www.lljqiu.com. All rights reserved.
 */
package com.lljqiu.tools.cmpp.client.utils;

/** 
 * ClassName: MsgCommand.java <br>
 * Description: 短信命令代码标识<br>
 *               链接请求、终止连接请求、提交短信请求、长链接激活等<br>
 * Create by: name：liujie <br>email: liujie@lljqiu.com <br>
 * Create Time: 2018年3月19日<br>
 */
public interface MsgCommand {

    /** 请求连接 */
    int CMPP_CONNECT                   = 0x00000001;
    /** 请求连接应答 */
    int CMPP_CONNECT_RESP              = 0x80000001;
    /** 终止连接 */
    int CMPP_TERMINATE                 = 0x00000002;
    /** 终止连接应答 */
    int CMPP_TERMINATE_RESP            = 0x80000002;
    /** 提交短信 */
    int CMPP_SUBMIT                    = 0x00000004;
    /** 提交短信应答 */
    int CMPP_SUBMIT_RESP               = 0x80000004;
    /** 短信下发 */
    int CMPP_DELIVER                   = 0x00000005;
    /** 下发短信应答 */
    int CMPP_DELIVER_RESP              = 0x80000005;
    /** 发送短信状态查询 */
    int CMPP_QUERY                     = 0x00000006;
    /** 发送短信状态查询应答 */
    int CMPP_QUERY_RESP                = 0x80000006;
    /** 删除短信 */
    int CMPP_CANCEL                    = 0x00000007;
    /** 删除短信应答 */
    int CMPP_CANCEL_RESP               = 0x80000007;
    /**    激活测试 */
    int CMPP_ACTIVE_TEST_RESP          = 0x80000008;
    /**    激活测试应答*/
    int CMPP_ACTIVE_TEST               = 0x00000008;
    /**消息前转*/
    int CMPP_FWD                       = 0x00000009;
    /**    消息前转应答*/
    int CMPP_FWD_RESP                  = 0x80000009;
    /**MT路由请求*/
    int CMPP_MT_ROUTE                  = 0x00000010;
    /**    MT路由请求应答*/
    int CMPP_MT_ROUTE_RESP             = 0x80000010;
    /**MO路由请求*/
    int CMPP_MO_ROUTE                  = 0x00000011;
    /**    MO路由请求应答*/
    int CMPP_MO_ROUTE_RESP             = 0x80000011;
    /**获取MT路由请求*/
    int CMPP_GET_MT_ROUTE              = 0x00000012;
    /**  获取MT路由请求应答*/
    int CMPP_GET_MT_ROUTE_RESP         = 0x80000012;
    /**    MT路由更新*/
    int CMPP_MT_ROUTE_UPDATE           = 0x00000013;
    /**    MT路由更新应答*/
    int CMPP_MT_ROUTE_UPDATE_RESP      = 0x80000013;
    /**    MO路由更新*/
    int CMPP_MO_ROUTE_UPDATE           = 0x00000014;
    /**  MO路由更新应答*/
    int CMPP_MO_ROUTE_UPDATE_RESP      = 0x80000014;
    /**    MT路由更新*/
    int CMPP_PUSH_MT_ROUTE_UPDATE      = 0x00000015;
    /**    MT路由更新应答*/
    int CMPP_PUSH_MT_ROUTE_UPDATE_RESP = 0x80000015;
    /**    MO路由更新*/
    int CMPP_PUSH_MO_ROUTE_UPDATE      = 0x00000016;
    /**    MO路由更新应答*/
    int CMPP_PUSH_MO_ROUTE_UPDATE_RESP = 0x80000016;
    /**    获取MO路由请求*/
    int CMPP_GET_MO_ROUTE              = 0x00000017;
    /**获取MO路由请求应答*/
    int CMPP_GET_MO_ROUTE_RESP         = 0x80000017;

}
