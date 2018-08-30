/**
 * Project Name cmpp-gateway
 * File Name package-info.java
 * Package Name com.lljqiu.tools.cmpp.gateway.utils
 * Create Time 2018年3月19日
 * Create by name：liujie -- email: liujie@lljqiu.com
 * Copyright © 2015, 2017, www.lljqiu.com. All rights reserved.
 */
package com.lljqiu.tools.cmpp.gateway.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.lljqiu.tools.cmpp.gateway.stack.ClientInfo;

/** 
 * ClassName: ReadYamlUtils.java <br>
 * Description: 读取配置文件<br>
 * @author name：liujie <br>email: liujie@lljqiu.com <br>
 * Create Time: 2018年3月19日<br>
 */
@Component
@SuppressWarnings("rawtypes")
public class ReadYamlUtils {

    static Map configMap   = new HashMap();
    static {
        try {
            File dumpFile = new File(ReadYamlUtils.class.getClassLoader().getResource("").getPath()
                    + "application.yaml");
            Yaml yaml = new Yaml();
            Map father = yaml.loadAs(new FileInputStream(dumpFile), HashMap.class);
            String configFileName = "application-dev.yaml";
            for (Object key : father.keySet()) {
                if ("spring".equals(key)) {
                    Map applicationMap = (Map) father.get(key);
                    Map config = (Map) applicationMap.get("profiles");
                    String type = (String) config.get("active");
                    switch (type) {
                        case "dev":
                            configFileName = "application-dev.yaml";
                            break;
                        case "prod":
                            configFileName = "application-prod.yaml";
                            break;
                        case "test":
                            configFileName = "application-test.yaml";
                            break;
                        default:
                            configFileName = "application-dev.yaml";
                            break;
                    }
                }
            }
            File applyFile = new File(ReadYamlUtils.class.getClassLoader().getResource("").getPath()
                    + configFileName);
            Map applyFather = yaml.loadAs(new FileInputStream(applyFile), HashMap.class);
            for (Object key : applyFather.keySet()) {
                if ("gatewar".equals(key)) {
                    configMap = (Map) applyFather.get(key);
                }
                
            }
        } catch (FileNotFoundException e) {
            LoggerFactory.getLogger(ReadYamlUtils.class).error("读取配置文件异常", e);
            System.exit(1);
        }
    }

    /** 
    * Description：实例化客户端信息
    * @return String
    * @author name：liujie <br>email: liujie@lljqiu.com
    **/
    public static List<ClientInfo> getClientConfig() {
        List<ClientInfo> list = new ArrayList<ClientInfo>();
        List configList = (ArrayList) configMap.get("clientConfig");
        for (int i = 0; i < configList.size(); i++) {
            String jsonString = new Gson().toJson(configList.get(i));
            list.add(JSONObject.parseObject(jsonString, ClientInfo.class));
        }
        return list;
    }
    /** 
     * Description：获取互联网短信网关端口号
     * @return Integer
     * @author name：liujie <br>email: liujie@lljqiu.com <br>
     * Create Time: 2018年3月19日<br>
     **/
    public static Integer getGatewayPort(){
        return (Integer) configMap.get("port");
    }
    
    /** 
     * Description：获取短信网关版本
     * @return Integer
     * @author name：liujie <br>email: liujie@lljqiu.com <br>
     * Create Time: 2018年3月19日<br>
     **/
    public static Integer getGatewayVersion(){
        return (Integer) configMap.get("version");
    }
    
    public static void main(String[] args) throws Exception {
        List<ClientInfo> clientConfig = getClientConfig();
        System.out.println(ToStringBuilder.reflectionToString(clientConfig.get(0)));
    }

    
   
}
