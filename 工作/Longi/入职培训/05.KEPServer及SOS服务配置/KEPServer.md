![](attachments/cm1671116355_226_187e0829159c7e9116deedab4aad6d0bf6a6d8a3_5.html_att6.png)
东方鼎晨 - 韩源 - 13699146636（技术）
![](attachments/Pasted%20image%2020230302133903.png)
kepserverex：
red：OPC DA服务器之间的数据冗余，方便切换活动和stand by节点之间的切换
link ：网桥，收集多个OPC DA服务器的数据
![](attachments/Pasted%20image%2020230302134504.png)
插件：
![](attachments/Pasted%20image%2020230302135023.png)
- Advanced Tags：设备到设备之间数据传输等
- DataLogger：ODBC接口
- IOT Gateway：云平台

![](attachments/Pasted%20image%2020230302135910.png)
勾选框时，表示在线激活。
激活时与设备绑定的，如果安装新设备等情况需要转移许可码，需要在KEPWare网站进行设备解绑等。
![](attachments/Pasted%20image%2020230302141230.png)
![](attachments/Pasted%20image%2020230302141835.png)
采集点数分多少个通道、多少个设备、多少的采集频率，没有确定的数量，通常受设备性能、网络条件、采集的数据量大小等影响。一般一个Device（设备）下≤5000个，一般一个Channel配一个Device（通过创建多个Channel并行采集），一般KEPServerEX采集点位数2~5万常见，到达10万时建议分配额外节点（其他的KEPServerEX上。
![](attachments/Pasted%20image%2020230302143551.png)
- Admin：应用级别设置项
	- 授权
	- OPC UA配置
	- 用户管理
	- 启动、停止运行模式
	- 时间和诊断日志
- Configuration：项目级别的设置
	- 创建
- Event Log：日志文件初步排错
- Runtime：后台服务，核心进程
![](attachments/Pasted%20image%2020230302150240.png)

![](attachments/Pasted%20image%2020230302150505.png)

- OPC DA：实时数据，受限于Windows平台，局限性非常大（受环境影响）
- OPC AE：报警数据

![](attachments/Pasted%20image%2020230302150839.png)
![](attachments/Pasted%20image%2020230302152436.png)