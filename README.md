# cmpp
cmpp3.0短信网关模拟器及客户端

## 配置文件说明
cmpp-client
> #网关地址
> cmpp.gateway.spIp=127.0.0.1
> #网关端口
> cmpp.gateway.port=7891
> #由短信网关分配的SPID等同于登录账号
> cmpp.gateway.spId=123456
> #访问短信网关需要的密码
> cmpp.gateway.sharedSecret=123456
> #由短信网关分配的SPCODE,即用户接受到的短信显示的主叫号码,短号码段spChannel
> cmpp.gateway.spCode=100860012345
> #企业代码
> cmpp.gateway.serviceId=test
> #SOCKET超时链接时间，可根据需求自由修改，建议6000，单位为毫秒
> cmpp.gateway.timeOut=6000
> #SOCKET链接失败重试次数，及短信发送失败重新发送的次数
> cmpp.gateway.connectCount=3
