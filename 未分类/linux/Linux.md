> > - 在这个虚拟机中
> - ![image.png](https://cdn.nlark.com/yuque/0/2022/png/1604140/1661515813449-5316fb89-43dd-4f1e-8aa6-e09b25649eb7.png#averageHue=%23f4f2ef&clientId=u22a69086-656e-4&errorMessage=unknown%20error&from=paste&height=76&id=ube18a8ae&originHeight=76&originWidth=233&originalType=binary&ratio=1&rotation=0&showTitle=false&size=3557&status=error&style=none&taskId=ud02232d2-2f0e-4aaa-8937-a986b631806&title=&width=233)

## 第1章 初识Linux
### 1.1 Linux概述

1. Linxu基于unix发展而来。
2. GNU/Linux表示遵循GNU开源协议
3. 关系：

![image.png](C:\Users\iceri\assets\image.png)
### 1.2 Linix安装
> - 账户名：root
> - 密码：dimitre123
> - 账户名：zyq
> - 密码：dimitre123

#### 1.2.1 创建虚拟机
![image.png](C:\Users\iceri\assets\image-1691165691376.png)![image.png](C:\Users\iceri\assets\image-1691165697145.png)![image.png](C:\Users\iceri\assets\image-1691165699117.png)![image.png](C:\Users\iceri\assets\image-1691165702345.png)![image.png](C:\Users\iceri\assets\image-1691165704744.png)![image.png](C:\Users\iceri\assets\image-1691165706568.png)![image.png](C:\Users\iceri\assets\image-1691165708214.png)![image.png](C:\Users\iceri\assets\image-1691165709899.png)![image.png](C:\Users\iceri\assets\image-1691165712236.png)![image.png](C:\Users\iceri\assets\image-1691165715098.png)![image.png](C:\Users\iceri\assets\image-1691165717813.png)![image.png](C:\Users\iceri\assets\image-1691165719461.png)![image.png](C:\Users\iceri\assets\image-1691165721660.png)![image.png](C:\Users\iceri\assets\image-1691165723623.png)![image.png](C:\Users\iceri\assets\image-1691165725680.png)![image.png](C:\Users\iceri\assets\image-1691165728083.png)
#### 1.2.2 安装CentOS
![image.png](C:\Users\iceri\assets\image-1691165730291.png)![image.png](C:\Users\iceri\assets\image-1691165733281.png)![image.png](C:\Users\iceri\assets\image-1691165735923.png)![image.png](C:\Users\iceri\assets\image-1691165737801.png)![image.png](C:\Users\iceri\assets\image-1691165739804.png)
![CentOS安装.png](C:\Users\iceri\assets\CentOS安装.png)

- 主机可以起一个好记的名字，然后点应用，以太网要选择打开。

![image.png](C:\Users\iceri\assets\image-1691165744080.png)![image.png](C:\Users\iceri\assets\image-1691165747017.png)![image.png](C:\Users\iceri\assets\image-1691165748811.png)![image.png](C:\Users\iceri\assets\image-1691165750601.png)![image.png](C:\Users\iceri\assets\image-1691165752374.png)![image.png](C:\Users\iceri\assets\image-1691165754417.png)![image.png](C:\Users\iceri\assets\image-1691165758611.png)![image.png](https://cdn.nlark.com/yuque/0/2022/png/1604140/1650293542941-eacc8a53-ec72-4723-b115-438e83aea36d.png#averageHue=%23e7e7e6&clientId=u5f76eb2b-cb72-4&errorMessage=unknown%20error&from=paste&height=150&id=ubcf91b11&originHeight=946&originWidth=1718&originalType=binary&ratio=1&rotation=0&showTitle=false&size=251120&status=error&style=none&taskId=u0abc1143-d0ca-43fd-bd25-cb37ae78dcc&title=&width=272)![image.png](C:\Users\iceri\assets\image-1691165761563.png)![image.png](C:\Users\iceri\assets\image-1691165763803.png)![image.png](C:\Users\iceri\assets\image-1691165765576.png)![image.png](C:\Users\iceri\assets\image-1691165767930.png)![image.png](C:\Users\iceri\assets\image-1691165770540.png)![image.png](C:\Users\iceri\assets\image-1691165773905.png)![image.png](C:\Users\iceri\assets\image-1691165775781.png)![image.png](C:\Users\iceri\assets\image-1691165777592.png)
#### 1.2.3 安装Centos迷你版
极简安装：一路默认。
网络配置：

- centos7中，查看本机ip指令变成了`ip addr`
   - ![image.png](C:\Users\iceri\assets\image-1691165781058.png)
- 修改网卡配置，设置为开机即启动网卡：`vi /etc/sysconfig/network-scripts/ifcfg-ens33`
   - ![image.png](C:\Users\iceri\assets\image-1691165783274.png)
- 重启网路服务：`systemctl restart network`
#### 1.2.4 配置静态ip

- 修改网卡配置：`vi /etc/sysconfig/network-scripts/ifcfg-ens33`
   - ![image.png](C:\Users\iceri\assets\image-1691165787979.png)
   - 图中netmask写错了
- 重启网路服务：`systemctl restart network`
#### 1.2.5 解决设置静态IP后无法联网问题
无法联网表现：
![image.png](C:\Users\iceri\assets\image-1691165791430.png)
排查网关设置是否正确

1. ![image.png](https://cdn.nlark.com/yuque/0/2022/png/1604140/1661400924136-15cc9dc1-ac01-4b03-92a9-7e72221dfcee.png#averageHue=%23f2f0ef&clientId=u160651cb-a54d-4&errorMessage=unknown%20error&from=paste&height=162&id=u1ea2a7e8&originHeight=162&originWidth=297&originalType=binary&ratio=1&rotation=0&showTitle=false&size=11521&status=error&style=none&taskId=uff3c2a8c-7a86-43cc-a779-c8ce5341cbf&title=&width=297)
2. ![image.png](C:\Users\iceri\assets\image-1691165795481.png)
3. ![image.png](C:\Users\iceri\assets\image-1691165798311.png)

可以看到网关地址没有设置正确。按照0.3修改即可。
### 1.3 Linux前置基础
#### 1.3.1 网络连接的三种模式

1. 桥接模式：虚拟系统与本地主机一样，使用相同的ip区段。同一局域网内本地主机都开启桥接虚拟机网络，会导致ip区段使用容易达到上限。
   - 特点：虚拟机和本机可以互相访问
2. NAT模式：网络地址转换模式，虚拟机系统使用其他ip区段
   - 特点：虚拟机可以访问本地其他主机，但其他主机不能直接访问虚拟机
3. 主机模式：
   - 特点：不和外部发生联系
#### 1.3.2 虚拟机克隆

1. 方式一：直接拷贝创建虚拟机时保存的文件夹。
   - ![image.png](C:\Users\iceri\assets\image-1691165804154.png)
2. 方式二：![image.png](C:\Users\iceri\assets\image-1691165805957.png)
   - 注意：需要在虚拟机关机的情况下克隆。

#### 1.3.3 虚拟机快照

1. 创建方式
   - ![image.png](C:\Users\iceri\assets\image-1691165808707.png)
2. 恢复快照：
   - ![image.png](C:\Users\iceri\assets\image-1691165811179.png)
#### 1.3.4 虚拟机的删除和迁移
删除：从VMware卸载centos虚拟机的过程。
迁移：从F盘到G盘，将安装文件夹剪切即可。
然后在vmwere中，打开虚拟机的配置文件即可
![image.png](C:\Users\iceri\assets\image-1691165813336.png)
#### 1.3.5 安装vmtools

1. 作用：使得本机和虚拟机都能操作同一份文件夹
2. 步骤1：下载vmtools到虚拟机
   - ![image.png](C:\Users\iceri\assets\image-1691165815160.png)
   - 下载完成后虚拟机桌面会出现vmtools的图标，双击打开
   - ![image.png](C:\Users\iceri\assets\image-1691165818587.png)
3. 步骤二：将vmtools压缩文件拷贝至虚拟机opt目录下
   - ![image.png](C:\Users\iceri\assets\image-1691165820950.png)
4. 步骤四：解压vmtools压缩文件
   - ![image.png](C:\Users\iceri\assets\image-1691165822704.png)
5. 步骤五：安装vmtools。进入解压后的文件夹，在终端执行安装命令
   - ![image.png](C:\Users\iceri\assets\image-1691165824902.png)
   - ![image.png](C:\Users\iceri\assets\image-1691165827322.png)
   - 安装成功：
   - ![image.png](C:\Users\iceri\assets\image-1691165829507.png)
6. 设置共享文件夹：在vm虚拟机中指定共享文件夹
   - ![image.png](C:\Users\iceri\assets\image-1691165832623.png)
7. 在虚拟机中查找本地主机共享的文件夹。
   - ![image.png](C:\Users\iceri\assets\image-1691165834511.png)
## 第2章 Linux入门
### 2.1 Linux的目录结构
![image.png](C:\Users\iceri\assets\image-1691165836294.png)

- /boot：存放的是启动Linux时使用的一些核心文件，包括一些连接文件以及镜像文件。
- /dev：类似windows的设备管理器，把所有的硬件用文件的形式存储。
- /etc：所有的系统管理所需要的配置文件和子目录。
- /home：存放普通用户的主目录，在Linux中每个用户都有一个自己的目录，一般该目录名是以用户的账号命名的。
- /root：该目录为系统管理员，也称作超级权限者的用户主目录，其他用户的文件放在/home目录下。
- /usr：这是一个非常重要的目录，用户的很多应用程序和文件都放在这个目录下，类似与windows下的program files目录。
   - /bin：是Binary的缩写，这个目录存放着最经常使用的命令。
   - /sbin：s就是Super User的意思，这里存放的是系统管理员使用的系统管理程序。
   - /local：**这是另一个给主机额外安装软件所安装的目录，一般是通过编译源码的方式安装的程序。**
- /var：这个目录中存放着在不断扩充着的东西，习惯将经常被修改的目录放在这个目录下，包括各种日志文件。
   - /tmp：这个目录是用来存放一些临时文件的。
- /lib：系统开机所需要最基本的动态连接共享库，其作用类似于Windows里的DLL文件。几乎所有的应用程序都需要用到这些共享库。
- /lost+found：这个目录一般情况下是空的，当系统非法关机后，这里就存放了一些文件。
- /proc：这个目录是一个虚拟的目录，它是系统内存的映射，访问这个目录来获取系统信息。
- /srv：service的缩写，该目录存放一些服务启动之后需要提供的数据。
- /sys：这是linux2.6内核的一个很大的变化。该目录下安装了2.6内核中新出现的一个文件系统sysfs。
- /media：linux系统会自动识别一些设备，例如U盘光驱等等，当识别后，linux会把识别的设备挂载到这个目录下。
- /mnt：系统提供该目录是为了让用户临时挂载别的文件系统的，我们可以将外部的存储挂载在/mnt/上，然后进入该目录就可以查看里面的内容了。
- /opt：**这是给主机额外安装软件所摆放的目录，如安装ORACLE数据库就可放到该目录下。默认为空。**
- /selinux：SELinux是一种安全子系统，它能控制程序只能访问特定文件。

![image.png](C:\Users\iceri\assets\image-1691165846991.png)
个人守则：将所有安装都放到opt目录下，配置文件、数据文件root用户放到root目录下，其他用户放到home自己的目录下。
### 2.2 Linux远程登录
> 注意：每次登录，虚拟机的ip好像都会变

即xshell工具的使用
![image.png](C:\Users\iceri\assets\image-1691165849161.png)![image.png](C:\Users\iceri\assets\image-1691165851441.png)
### 2.3 Linux远程文件传输
即xftp的使用
![image.png](C:\Users\iceri\assets\image-1691165864467.png)
![image.png](C:\Users\iceri\assets\image-1691165869936.png)
解决乱码问题：
![image.png](C:\Users\iceri\assets\image-1691165873498.png)![image.png](https://cdn.nlark.com/yuque/0/2022/png/1604140/1660828897824-f42bf8c3-3139-4ecb-989b-12e79c22f5e7.png#averageHue=%23e4e0dd&clientId=u0e2f0b3f-c855-4&from=paste&height=150&id=u75126b3a&originHeight=187&originWidth=481&originalType=binary&ratio=1&rotation=0&showTitle=false&size=9833&status=done&style=none&taskId=u8bbb105e-4437-41c9-b142-88822b2a9ad&title=&width=384.8)
### 扩展：vscode替代xshell和xftp

1. 下载`Remote-SSH`插件，文件传输需要安装`sftp`插件
2. 连接配置：![image.png](C:\Users\iceri\assets\image-1691165877312.png)
### 2.4 Vim和Vi的使用
模式切换：
![image.png](C:\Users\iceri\assets\image-1691165880928.png)
文档修改：

- :wq	保存并退出
	 :q	退出并不保存
	 :q!	强制退出并不保存

一般模式操作：

- yy：		拷贝当前行
	 5yy：	拷贝当前行至后续4行
	 p：		粘贴拷贝的行
	 dd：	删除当前行
	 5dd：	删除当前行向下的5行
	 G：		到达文档最末行
	 gg：	到达文档最首行
	 u：		撤销输入
- 20 + shift + g：快速定位到第20行

命令行模式操作：

-  /（查找内容）：	在文件中查找某个单词，按n查找下一个
	 set nu：			设置文件行号
	 set nonu：		取消文件行号

### 2.5 Linux系统操作
#### 2.5.1 关机、重启
shutdown -h now：	立即关机
shutdown -h 1：		1分钟后关机
shutdown -r now：	立即重启
halt：				立即关机
reboot：				立即重启
sync：				把内存的数据同步到磁盘上
#### 2.5.2 登录、注销
logout：				注销用户，在图形运行级别无效，在运行级别3有效。
#### 2.5.3 用户管理
useradd 用户名：			添加用户【默认家目录是/home/用户名】
useradd -d 路径 用户名：	添加用户，指定家目录位置
useradd -g 组名 用户名：	添加用户并指定组
password 用户名：			给指定用户设置密码，不带用户名时为修改当前用户密码
userdel 用户名：			删除指定用户，需要root用户删除
userdel -r 用户名：		删除用户并删除其家目录，需要root用户删除
id 用户名：				查询指定用户信息
su - 用户名：				切换用户，高权限用户切换到低权限用户不需要密码
exit/logout:				退出当前用户
who am i：				显示登录到虚拟机的用户
groupadd 组名：			新增组
groupdel 组名：			删除组
usermod -g 组名 用户名：	移动用户到新组
> - 新建用户时，如果未指定组，则系统自动创建与用户名同名的组
> - /etc/passwd文件：记录用户信息的文件
> - /etc/shadow文件：记录用户活动信息的文件（文件经过系统处理）
> - /etc/group文件：记录组信息的文件

#### 2.5.4 运行级别

1. 关机
2. 单用户【找回丢失密码】
3. 多用户状态没有网络服务
4. 多用户状态有网络服务
5. 系统未使用保留给用户
6. 图形界面
7. 系统重启
> - /etc/inittab：运行级别配置文件

> - init [0,1,2,...]：切换运行级别
> - systemctl get-default：查看当前运行级别
> - systemctl set-default TARGET.target：设置指定的运行级别，
>    - TARGET：graphical（6）、multi-user（3）、

#### 2.5.5 找回root用户密码

1. 在启动界面，选择编辑界面，输入e进入
2. 在linux16开头的所在行，在末尾输入`init=/bin/sh`
3. ctrl+x进入单用户模式
4. 输入`mount -o remount,rw /`
5. 设置新密码
6. 输入`touch /.autorelabel`
7. 输入`exec /sbin/init`
#### 2.5.6 帮助指令
man [命令或配置文件]：
help [命令]：
which 命令
#### 2.5.7 文件目录指令

- pwd：显示当前工作目录的绝对路径。
- ls：显示当前目录所有的文件和目录，不包括隐藏的
   - -a：显示当前目录所有的文件和目录，包括隐藏的；
   - -l：以列表的方式显示信息。
   - ls -al：可以组合使用，其他指令同理
- cd 路径：
   - cd ~：回到自己的家目录；
   - cd ..：回到当前目录的上一级目录。
- mkdir 路径：创建一个目录，创建多层级目录时，如果层级中有两个及两个以上文件不存在则报错。如 /home/animal/tiger，当animal和tiger都不存在时报错。
   - -p 路径：创建多个目录。
- rmdir 路径：删除空目录。rmdir不能删除非空的目录。
   - rm -rf 路径。要删除非空的目录
- touch 文件名：创建空文件。可以一次性创建多个文件
- cp 文件名 路径：拷贝文件到指定目录；
   - cp -r 路径 目标路径：拷贝文件夹至指定目录。
   - \cp：强制覆盖不提示
- rm 文件名/路径：移除文件或目录；
   - -r：删除整个文件夹；
   - -f：强制删除不提示。
- mv 文件名/路径：移动文件与目录或重命名，两种功能！
   - 文件在同一目录下，则为重命名
- cat 文件名：查看文件内容。只能浏览文件，而不能修改文件。
   - cat -n 文件名：显示行号。
   - cat -n 文件名 | more：分页查看。
- more：是一个基于VI编辑器的文本过滤器，它以全屏幕的方式按页显示文本文件的内容。more还内置了很多快捷键：
   - 空白键（Space）	向下翻一页
   	 Enter			向下翻一行
   	 q				立刻离开more，不再显示该文件内容
   	 Ctrl + F			向下滚动一屏
   	 Ctrl + B			返回上一屏
   	 =				输出当前行的行号
   	 :f				输出文件名和当前行的行号
- less：用来分屏查看文件内容，与more相似，但是更强大，支持各种显示终端。less指令在显示文件内容时，并不是一次将整个文件加载之后才显示，而是根据显示需要加载内容。对于显示大型文件具有较高的效率。
   - ![image.png](C:\Users\iceri\assets\image-1691165892464.png)
- echo：输出内容到控制台
- head：显示文件开头部分，默认显示前10行
   - head -n 5 文件名：显示前5行内容
- tail：显示文件末尾部分，默认显示10行
   - tail -n 5 文件名：显示最后5行内容
   - tail -f 文件名：实时监控文件最后的内容
- >：覆盖。如果不存在会创建文件，否则会将原来的文件内容覆盖。
   - ls -l >文件名：
   - ls -al >>文件名：
   - cat 文件1 > 文件2：
- >>：追加。如果不存在会创建文件，否则不会覆盖原来的文件内容，而是追加到文件的尾部。
- ln -s地址 软连接名：创建软连接，类似windows快捷方式。删除软连接同删除文件
- history：查看已执行的历史命令
   - history 10：查看最近的10条指令
   - !5：执行曾今执行过的第5条指令
#### 2.5.7 时间日期指令
date：显示当前日期和时间
date "+%Y"：显示当前年份
date "+%d"：显示当前月份
date "+%Y-%m-%d %H:%M:%S"：显示年-月-日 时：分：秒
date -s "2022-11-12 20:34:15"：设置日期
cal：查看日历指令；
cal 年份：显示某一年一整年的日历
#### 2.5.8 搜索查找指令

- find 文件名：从当前目录向下递归的遍历其各个子目录，将满足条件的文件或者目录显示在终端。
   - find 路径 -name 文件名：按照指定的文件名查找模式查找文件。
   - find 路径 -user 用户名：查找指定用户创建的文件。
   - find 路径 -size +n/-n/n k/M/G：查找文件大小超过/小于/等于n的文件
- locate 文件名：
   - 可以快速定位文件路径。locate指令利用事先建立的系统中所有文件名称及路径的locate数据库实现快速定位给定的文件。locate指令无需遍历整个文件系统，查询速度较快。为了保证查询结果的准确度，管理员必须定期更新locate时刻。
   - 在第一次运行之前，必须使用updatedb指令创建locate数据库。
- grep [选项] 查找内容 源文件：过滤查找，表示将前一个命令的处理结果输出传递给后面的命令处理。经常跟管道一起使用。
   - -n：显示匹配行及行号。
   - -i：忽略大小写字母。
   - `cat hello.txt | grep "yes"`等价于` grep "yes" hello.txt`
#### 2.5.9 防火墙指令

1. 关闭防火墙：`systemctl stop firewalld.service`
2. 禁止防火墙开机启动：`systemctl disable firewalld.service`
3.  放行端口：`firewall-cmd --zone=public --add-port=80/tcp --permanent`
4. 重启防火墙：`firewall-cmd --reload`
5. 查看防火墙状态：`systemctl status firewalld`
6. 重载防火墙规则：`firewall-cmd --reload`
7. 查看已配置防火墙规则：`firewall-cmd --list-all`
8. 限定访问端口与访问ip：`firewall-cmd --permanent --add-rich-rule="rule family="ipv4" source address="192.168.44.101" port protocol="tcp" port="8080" accept`
9. 移除规则：`firewall-cmd --permanent --remove-rich-rule="rule family="ipv4" source address="192.168.44.101" port port="8080" protocol="tcp" accept`
#### 2.5.10 集群指令

1. `scp`：不同Linux之间复制文件和目录，`scp -r 用户名1@主机名1:文件路径 用户名2@主机名2:文件路径`
   - `-r`：递归复制整个目录
   - 指令含义：从主机1将所有指定文件拷贝至主机2指定路径
2. `rsync`：不同linux之间同步文件和目录，`sync -av 用户名1@主机名1:文件路径 用户名2@主机名2:文件路径`
   - `-a`：归档拷贝
   - `-v`：显式复制过程
3. `xrsync`脚本：如果要在任意路径下执行`xrsync 文件名`即可实现同步至指定虚拟机，则需要将`xrsync`脚本加到环境变量中去。
   - 在/root目录下创建/bin目录，这里时随便选的路径，编写脚本代码：
```bash
#!/bin/bash

#1. 判断参数个数
if [ $# -lt 1]
then
  echo Not Enough Argument!
  exit;
fi

#2. 遍历集群所有机器
for host in 虚拟机名1 虚拟机名2 虚拟机名3
do
  echo ============================ $host =================================
  #3. 遍历所有目录，挨个发送
  for file in $@
  do
    #4. 判断文件是否存在
    if [ -e $file ]
      then
        #5. 获取父目录
        pdir=$(cd -P $(dirname $file); pwd)
        #6. 获取当前文件的名称
        fname=$(basename $file)
        ssh $host "mkdir -p $pdir"
        rsync -av $pdir/$fname $host:$pdir
      else
        echo $file does not exists!
    fi
  done
done
```

   - 进入/root/bin目录，给脚本添加可执行权限：`chmod 777 xsync`
      - ![image.png](C:\Users\iceri\assets\image-1691165905755.png)
   - 此时即可使用`xrsync 文件名`分发文件。
   - 如果遇到分发文件权限不足，可使用`sudo /root/bin/xsync 文件名`的方式进行分发。
### 2.6 ssh免密登录

## 第x章 JavaEE开发
### x.1 安装jdk

1. 建立jdk文件夹保存安装包：`mkdir /opt/jdk`
2. 将jdk安装包放至该目录下
3. 进入/opt/jdk目录
4. 对jdk安装包进行解压：`tar -zxvf jdk-8u144-linux-x64.tar.gz`
5. 创建/usr/local/java目录，作为软件安装目录：`mkdir /usr/local/java`
6. 将解压的java文件夹移动到/usr/local/java：`mv jdk1.8.0_144/ /usr/local/java`
7. 此时进行进入/usr/local/java/jdk1.8.0_144/bin目录，就可以执行Java命令了。
8. 为了能够在任意路径下执行Java命令，需要将Java的环境变量写入/etc/profile文件中去。
9. 进行全局环境变量配置：
   - `vi /etc/profile`
   - ![image.png](C:\Users\iceri\assets\image-1691165911386.png)
10. 刷新环境变量：`source /etc/profile`
### x.2 安装tomcat

1. 复制文件：

![image.png](C:\Users\iceri\assets\image-1691165913303.png)

2. 解压缩：`tar -zxvf apache-tomcat-8.5.82.tar.gz `
3. 将解压目录移动至/usr/local/tomcat文件夹下：`mv apache-tomcat-8.5.82 /usr/local/tomcat`
   - `mv apache-tomcat-8.5.82 /usr/local/tomcat`与`mv apache-tomcat-8.5.82/ /usr/local/tomcat`的区别
   - `mv apache-tomcat-8.5.82 /usr/local/tomcat`会把apache-tomcat-8.5.82里面的内容移到tomcat文件夹下，apache-tomcat-8.5.82文件夹删除。
   - `mv apache-tomcat-8.5.82/ /usr/local/tomcat`会把apache-tomcat-8.5.82整个文件夹放在tomcat文件夹下
4. 进入tomcat/bin目录，执行`./startup.sh`
   - ![image.png](C:\Users\iceri\assets\image-1691165916513.png)
5. 访问测试：
   - ![image.png](C:\Users\iceri\assets\image-1691165919116.png)
   - ![image.png](C:\Users\iceri\assets\image-1691165946090.png)
   - 注：这里已经提前关闭里linux的防火墙。同时也设置了阿里云域名解析。
### x.x 安装Hadoop（大数据）
#### x.x.1 安装环境

1. 安装红帽系的rpm包软件仓库`epel-release`：`yum install -y epel-release`
2. 关闭防火墙、设置开机也关闭。
3. 解压hadoop3.2.4到/usr/local/hadoop目录下，作为安装目录：`tar -zxvf hadoop-3.2.4.tar.gz -C /usr/local/hadoop`
4. 配置环境变量：`vi /etc/profile`
   - ![image.png](C:\Users\iceri\assets\image-1691165940300.png)
6. 刷新环境变量：`source /etc/profile`
7. 执行hadoop测试是否安装成功：`hadop`
   - ![image.png](C:\Users\iceri\assets\image-1691165937241.png)
#### x.x.2 本地运行模式（演示——统计单词数）
> 统计word.txt文件中每个单词出现的次数

1. 在/usr/local/hadoop/hadoop-3.2.4/下创建wcinput文件夹
2. 在wcinput文件夹下创建word.txt文件，输入任意内容（如helloworld！）。
3. 执行`hadoop jar share/hadoop/mapreduce/hadoop-mapreduce-examples-3.2.4.jar wordcount wcinput/ ./wcoutput`
   - wordcount是Hadoop的命令
   - wcinput是要执行数据出路的文件路径
   - wcoutput是处理完数据存放的路径
      - ![image.png](C:\Users\iceri\assets\image-1691165925990.png)
   - 执行该命令时，wcoutput不能存在
4. 此时wcoutput路径下会生成两个文件
   - ![image.png](C:\Users\iceri\assets\image-1691165928294.png)
5. `cat part-r-0000`查看处理后的文件：
   - ![image.png](C:\Users\iceri\assets\image-1691165930579.png)
   - 表示helloworld!这个单词出现了一次
#### x.x.3 集群配置

