## CMPP3.0 模拟短信网关
    采用 byte 作为消息体传输
    
## 配置
### cmpp-gateway

application.yaml
```
spring: 
  profiles:
    active: dev
  jackson:
    date-format: yyyy-MM-dd HH:mm:ss
    time-zone: Asia/Shanghai
application:
  name: cmpp-gateway
  version: 1.0.0

# gatewar config
# 这里是配置客户端的信息，也可以改为在数据库中读取。
gatewar:
  port: 7891
  clientConfig: [{"spIp":"127.0.0.1","spId":"100861","sharedSecret":"100861","spCode":"106510086","serviceId":"test","version":"48"}]
```
CmppGateWayServer.java
```
读取客户端的信息，用于做 connect 消息验证
List<ClientInfo> clientConfig = ReadYamlUtils.getClientConfig();
for (ClientInfo clientInfo : clientConfig) {
	EhCache.put(EhCache.CACHE_NAME, clientInfo.getSpIp(), clientInfo);
}
```
### cmpp-client
在 application.properties 中修改一下配置
```
application.name=cmpp-client
application.version=1.0.0

spring.profiles.active=dev
spring.jackson.date-format=yyyy-MM-dd HH:mm:ss
spring.jackson.time-zone=Asia/Shanghai

logging.config=classpath:logback.xml

#网关地址
cmpp.gateway.spIp=127.0.0.1
#网关端口
cmpp.gateway.port=7891
#由短信网关分配的SPID等同于登录账号
cmpp.gateway.spId=100861
#访问短信网关需要的密码
cmpp.gateway.sharedSecret=100861
#由短信网关分配的SPCODE,即用户接受到的短信显示的主叫号码,短号码段spChannel
cmpp.gateway.spCode=106510086
#企业代码
cmpp.gateway.serviceId=test
#SOCKET超时链接时间，可根据需求自由修改，建议6000，单位为毫秒
cmpp.gateway.timeOut=6000
#SOCKET链接失败重试次数，及短信发送失败重新发送的次数
cmpp.gateway.connectCount=3

```
