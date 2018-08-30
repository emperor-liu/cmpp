/**
 * Project Name cmpp-gateway
 * File Name ClientInfo.java
 * Package Name com.lljqiu.tools.cmpp.gateway.stack
 * Create Time 2018年3月19日
 * Create by name：liujie -- email: liujie@lljqiu.com
 * Copyright © 2015, 2017, www.lljqiu.com. All rights reserved.
 */
package com.lljqiu.tools.cmpp.gateway.stack;

import java.io.Serializable;
import java.util.List;

/** 
 * ClassName: ClientInfo.java <br>
 * Description: cmpp 链接客户端信息<br>
 * @author name：liujie <br>email: liujie@lljqiu.com <br>
 * Create Time: 2018年3月19日<br>
 */
public class ClientInfo implements Serializable {

    /**
     * Copyright © 2015, 2017, www.lljqiu.com. All rights reserved.
     */
    private static final long serialVersionUID = 1L;

    private String            spIp;
    private String            spId;
    private String            sharedSecret;
    private String            spCode;
    private String            serviceId;

    /**
     * @return the spIp
     */
    public String getSpIp() {
        return spIp;
    }

    /**
     * @param spIp the spIp to set
     */
    public void setSpIp(String spIp) {
        this.spIp = spIp;
    }

    /**
     * @return the spId
     */
    public String getSpId() {
        return spId;
    }

    /**
     * @param spId the spId to set
     */
    public void setSpId(String spId) {
        this.spId = spId;
    }

    /**
     * @return the sharedSecret
     */
    public String getSharedSecret() {
        return sharedSecret;
    }

    /**
     * @param sharedSecret the sharedSecret to set
     */
    public void setSharedSecret(String sharedSecret) {
        this.sharedSecret = sharedSecret;
    }

    /**
     * @return the spCode
     */
    public String getSpCode() {
        return spCode;
    }

    /**
     * @param spCode the spCode to set
     */
    public void setSpCode(String spCode) {
        this.spCode = spCode;
    }

    /**
     * @return the serviceId
     */
    public String getServiceId() {
        return serviceId;
    }

    /**
     * @param serviceId the serviceId to set
     */
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ClientInfo [spIp=" + spIp + ", spId=" + spId + ", sharedSecret=" + sharedSecret
                + ", spCode=" + spCode + ", serviceId=" + serviceId + "]";
    }
    private List<ClientInfo> clientInfoList;
    
    public void addClientInfo(ClientInfo clientInfo){
        clientInfoList.add(clientInfo);
    }

}
