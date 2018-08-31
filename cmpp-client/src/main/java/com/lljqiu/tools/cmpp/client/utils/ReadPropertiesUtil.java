/**
 * Project Name cmpp-client
 * File Name package-info.java
 * Package Name com.lljqiu.tools.cmpp.client.utils
 * Create Time 2018年8月30日
 * Create by name：liujie -- email: liujie@lljqiu.com
 * Copyright © 2015, 2017, www.lljqiu.com. All rights reserved.
 */
package com.lljqiu.tools.cmpp.client.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.LoggerFactory;

/** 
 * ClassName: ReadPropertiesUtil.java <br>
 * Description: 读取配置文件 (.Properties)<br>
 * Create by: name：liujie <br>email: liujie@lljqiu.com <br>
 * Create Time: 2017年3月29日<br>
 */
public class ReadPropertiesUtil {

	protected Map<String,Object> cmppMap = null;
    protected static ReadPropertiesUtil instance; 
    
    public synchronized static ReadPropertiesUtil getInstance() {
        if (instance == null) {
            instance = new ReadPropertiesUtil();
        }
        return instance;
    }
    
    public ReadPropertiesUtil(){
        if(cmppMap == null){
        	cmppMap = new HashMap<String,Object>();
            initCmppProperties();
        }
    }
    
    @SuppressWarnings("rawtypes")
    public void initCmppProperties(){
        try {
            InputStream in = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("application.properties");
            Properties p = new Properties();
            p.load(in);
            Enumeration en = p.propertyNames(); //得到配置文件的名字
            
            while(en.hasMoreElements()) {
                String strKey = (String) en.nextElement();
                if(strKey.indexOf("cmpp") != -1){
                	cmppMap.put(strKey.replace("cmpp.gateway.", ""), p.getProperty(strKey));
                }
            }
            in.close();
        } catch (IOException e) {
        	LoggerFactory.getLogger(ReadPropertiesUtil.class).error("读取邮件配置信息失败", e);
        }
    }
    
    public Map<String,Object> getCmppConfig(){
    	return ReadPropertiesUtil.instance.cmppMap;
    }
    
}
