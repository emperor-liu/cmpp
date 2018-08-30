/**
 * Project Name cmpp-gateway
 * File Name GateWayException.java
 * Package Name com.lljqiu.tools.cmpp.gateway.exception
 * Create Time 2018年3月19日
 * Create by name：liujie -- email: liujie@lljqiu.com
 * Copyright © 2015, 2017, www.lljqiu.com. All rights reserved.
 */
package com.lljqiu.tools.cmpp.gateway.exception;

/** 
 * ClassName: GateWayException.java <br>
 * Description: <br>
 * @author name：liujie <br>email: liujie@lljqiu.com <br>
 * Create Time: 2018年3月19日<br>
 */
public class GateWayException extends RuntimeException {
    /**
     * Copyright © 2015, 2017, www.lljqiu.com. All rights reserved.
     */
    private static final long serialVersionUID = 1L;
    protected Integer         errorCode;

    public GateWayException(Throwable cause) {
        this(null, null, cause);
    }

    public GateWayException(Integer errorCode) {
        this(errorCode, null, null);
    }

    public GateWayException(String errorMessage) {
        this(null, errorMessage, null);
    }

    public GateWayException(Integer errorCode, Throwable cause) {
        this(errorCode, null, cause);
    }

    public GateWayException(String errorMessage, Throwable cause) {
        this(null, errorMessage, cause);
    }

    public GateWayException(Integer errorCode, String errorMessage) {
        this(errorCode, errorMessage, null);
    }

    public GateWayException(Integer errorCode, String errorMessage, Throwable cause) {
        super(errorMessage, cause);
        this.errorCode = errorCode;

    }

    /** 
     * Description：
     * @param expression 条件成立抛出异常
     * @param errorCode
     * @param errorMessage
     * @return void
     * @author name：liujie <br>email: jie_liu1@asdc.com.cn
     **/
    public static void checkCondition(boolean expression, String errorMessage) {
        if (expression) {
            throw new GateWayException(errorMessage);
        }
    }

    /** 
     * Description：
     * @param expression 条件成立抛出异常
     * @param errorCode
     * @param errorMessage
     * @return void
     * @author name：liujie <br>email: jie_liu1@asdc.com.cn
     **/
    public static void checkCondition(boolean expression, Integer errorCode, String errorMessage) {
        if (expression) {
            throw new GateWayException(errorCode, errorMessage);
        }
    }

    public Integer getErrorCode() {
        return errorCode;
    }
}
