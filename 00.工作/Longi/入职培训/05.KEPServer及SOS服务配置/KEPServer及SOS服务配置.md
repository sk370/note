## 1. KEPServer介绍

### 1.1 西咸切片项目服务器架构图

![](attachments/316580817245951.png)

### 1.2 设备数据与MES连通过程

![](attachments/545913508236551.png)

- 对于设备：kepserver是一个客户端
- 对于mes：kepserver是一个服务端

### 1.3 KepserverEX是什么

- 作用：kepserver是一款连接平台，可作为单一来源向所有应用程序提供工业自动化数据。用于连接、管理、监视、控制各种自动化设备和软件应用程序（各类PLC）。KEPServerEX 利用 OPC（自动化行业的互操作性标准）和以 IT 为中心的通信协议（如 SNMP、ODBC 和 Web 服务）为用户提供工业数据的单一来源。
- 简单理解：kepserver用于从PLC读取和写入数据。相当于人机交互界面与PLC之间的服务中间件。
- 人机交互界面的数据不能直接写入plc的一大原因是：要能写入则要求设备支持PLC的UA协议，而实际PLC UA协议的支持是通过Kepserver实现。
- UA协议的稳定性较DA更好，配置时需要逐一配置，工作量大。DA配置时可以整体配置，工作量小，但逻辑复杂。

### 1.4 当前情况

当前KEPServe采集切片机、脱胶机、插片机3类设备的数据：

- 10.22.0.233：切片机kepserver服务器
   - 1-3排西门子设备
   - 4-5排汇川设备
   - 目前第一排到第四排使用DA协议配置
   - 第五排使用UA协议配置
- 10.22.0.234：插片机、脱胶kepserver服务器
- 由于是Windows server系统，所以只能单用户登录服务器

插片机：

- 本项目中插片机使用了其他的通讯协议，配置方式和切片机不同，它把所有点位都配置到一个设备下，没有进行分组（只有一个分组）。
- 插片机网段映射（转换）：不同网段的设备进行通讯。（晓辉知道）

## 2. kepserver连接设备采集数据

### 2.1 从0开始连接

1. 新建通道：
    - ![](attachments/569873608256717.png)
2. 选择设备支持的PLC驱动协议：本项目中使用OPC UA Client协议（设备作为OPC  Server、KepServer作为Client）
    - ![](attachments/36293708249386.png)
3. 设定通道名称：
    -  ![](attachments/106633708245941.png)
4. 选择默认设置：
    - ![](attachments/214133708241695.png)
5. 设置连接的设备地址参数：
   - ip:端口号：按照实际配置，注意需要设备开放
   - 安全策略：与设备的安全策略保持一致，本项目的切片机未配置安全策略
   - 消息模式：选择无。
    - ![](attachments/328993708259575.png)
    - 使用默认：
        - ![](attachments/20223808257179.png)
    - 配置设备（服务端）的账号密码、本项目无：
        - ![](attachments/57033908254681.png)
    - 确认信息，点击完成：
        - ![](attachments/191943908235922.png)
6. 上述完成了通道的建立，下面添加设备：
    - 指定设备名字：
        - ![](attachments/328293908258362.png)
    - 使用默认值：
        - ![](attachments/448213908253498.png)
    - 使用默认值：
        - ![](attachments/147454008247044.png)
    - 使用默认值：
        - ![](attachments/337514008240178.png)
    - 使用默认值：
        - ![](attachments/516324008230708.png)
    - 使用默认：
        - ![](attachments/30424108233212.png)
    - 使用默认：
        - ![](attachments/167364108242159.png)
    - 使用默认：
        - ![](attachments/297294108235044.png)
    - 确认信息：
        - ![](attachments/489664108235653.png)
7. 给设备添加点位：西门子和汇川看起来用的plc一样，但实际点位不一样
    - ![](attachments/29464208262608.png)
    - ![](attachments/135764208244821.png)
    - 根据与厂家协商的结果，找到要获取数据的点位名称进行添加。
8. 创建分组：
    - 默认添加的点位层级嵌套比较深，且未进行分组管理。可以创建分组，将点位直接拖到该分组下。
原始情况：
        - ![](attachments/474704208234119.png)
    - 创建分组：
        - ![](attachments/36094308230370.png)
    - 设定分组名称：
        - ![](attachments/173524308230509.png)
    - 将地址拖进新分组：
        - ![](attachments/356734308242599.png)
    - 删除旧分组：
        - ![](attachments/494444308247638.png)
9. 修改地址的名称：
    - ![](attachments/143384408248933.png)
    - ![](attachments/218124408249935.png)
### 2.2 快速创建连接

拷贝一个既有的，只需要更改名称和ip：

1. 选择要复制的设备进行ctrl + c
2. 选中连续性，进行 ctrl + v
3. 修改通道名称
    - ![](attachments/363834408250112.png)
4. 修改地址：
    - ![](attachments/505934408235664.png)
5. 修改设备名称：
    - ![](attachments/89594508251943.png)

### 2.3 分组含义

一个分组对应一个设备的hmi操作屏界面：

- Download：切片机下料（无对应HMI界面）
- Exc：过程异常
- Fgmt：切片碎片录入
- GX_DN：钢线下机
- GX_UP：钢线上料
- Heart：心跳（无对应HMI界面）
- Run：开始切割（无对应HMI界面）
- Upload：切片上料
- Waste：钢线损耗

### 2.4 点位含义

![](attachments/278864508244988.png)

- MES_IS_OPEN：控制激活配置
- QPBTN_HADNLE_SHARK：触发信号（hmi屏幕点击的按钮，根据值判断触发哪个）

### 2.5 查看实时点位数据状态

![](attachments/417454508241755.png)

修改实时数据：
![](attachments/477524508232086.png)

### 2.6 切片上料反馈码

![](attachments/38714608250129.png)
MES定义反馈码在MES中的名称，及plc对应的数值，MES给PLC只发送该数值，PLC接收中文字符存在不便，机台PLC收到MES接收的数值后，由机台上位机转换为中文字符。

### 2.7 指定FTSP配置名

![](attachments/20230707141951.png)

这里配置的名称，用于FTSP连接的地址和XML配置的地址。

即：首先指定FTSP配置地址，其次FTSP使用该地址，最后xml文件连接该地址。

## 3. 连接Kepserver服务

登录FTSP（10.22.0.220）服务器，比如在本机220上获取切片223服务器的数据：

### 3.1 连接KEPServer服务器

![](attachments/231354608255884.png)
![](attachments/295794608256516.png)

### 3.2 配置SOS服务

PD中一个Event Sheets对应一个SOS服务：
![](attachments/406664608247252.png)

1. 解压安装包
    - ![](attachments/546014608240111.png)
2. 配置wrapper.conf
    - ![](attachments/310664708258920.png)
    - ![](attachments/379714708254028.png)
3. 配置shopOperationsServer.xml
    - ![](attachments/531914708261539.png)
4. 手动启动服务
    - ![](attachments/17874808260550.png)

## 4.FTSP

#### 4.1 系统要求

![](attachments/05ee6119a2bd5f557d53c1b739202ad.png)

注：本机安装成功的环境

![](attachments/20230330110629.png)

## 扩展

- PLC数据类型：[PLC基本数据类型有几种_plc_电工之家(dgzj.com)](https://www.dgzj.com/plc/101747.html)
- PLC厂家：[全球PLC制造商TOP20，国产有几名呢？-知乎(zhihu.com)](https://zhuanlan.zhihu.com/p/373193871)

