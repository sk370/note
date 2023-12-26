## 安装docker

**注意：docker及其实例都需要使用管理员权限安装、运行**

[CentOS Docker 安装 | 菜鸟教程 (runoob.com)](https://www.runoob.com/docker/centos-docker-install.html)

- 设置开机自启：`systemctl enable docker.service`
- 关闭开机自启：`systemctl disable docker.service`
- 查看是否设置开机自启：`systemctl list-unit-files | grep enable`
- 查看已启动的服务：`systemctl list-units --type=service`

### docker可视化工具

[三种docker可视化工具（全网最详细）_CN-FuWei的博客-CSDN博客](https://blog.csdn.net/zfw_666666/article/details/126538026)

#### docker.ui

- 官网：[GitHub - gohutool/docker.ui](https://github.com/gohutool/docker.ui)

查找镜像：`docker search docker.ui`

![](./attachments/2023-08-07.png)

拉取镜像：`docker image pull joinsunsoft/docker.ui`

![](./attachments/2023-08-07-1.png)

运行容器并不安装（ctrl + c会退出）：`docker container run --rm --name docker.ui -v /var/run/docker.sock:/var/run/docker.sock -p 8999:8999 joinsunsoft/docker.ui`
运行容器并后台运行：`docker container run -it -d --name docker.ui -v /var/run/docker.sock:/var/run/docker.sock -p 8999:8999 joinsunsoft/docker.ui`

**不要修改挂载容器数据卷的linux地址，会启动不了**

![](./attachments/2023-08-07-4.png)

访问 `http://ip:8999` 即可打开gui界面，账号/密码：`ginghan/123456`

![](./attachments/2023-08-07-5.png)

#### Portainer**

- 官网（CE版——社区开源）：[Install Portainer CE with Docker on Linux - Portainer Documentation](https://docs.portainer.io/start/install-ce/server/docker/linux)
- 官网（BE版——商业收费）：[Getting Started with Portainer Business](https://install.portainer.io/)——**可以了解容器知识**

查找镜像：`docker search portainer`

![](./attachments/2023-08-07-6.png)

创建容器数据卷：`docker volume create portainer_data`

![](./attachments/2023-08-07-7.png)

运行容器：`docker run -d -p 8000:8000 -p 9443:9443 --name portainer --restart=always -v /var/run/docker.sock:/var/run/docker.sock -v portainer_data:/data portainer/portainer-ce:latest`

**不要修改挂在容器数据卷的linux地址，会启动不了**

![](./attachments/2023-08-07-8.png)

访问gui页面设置密码：`https://ip:9443`，账号admin，密码Courage@1115

![](./attachments/2023-08-07-9.png)

重启容器：`docker restart portainer`

![](./attachments/2023-08-07-10.png)

二者对比：

- docker ui：可以直接从镜像启动容器。
- portainer：可以直接展示未使用容器数据卷。

### 安装mysql

[Docker 安装 MySQL | 菜鸟教程 (runoob.com)](https://www.runoob.com/docker/docker-install-mysql.html)

- 创建容器时设置开机自启：`docker run --restart=always`
- 已经创建了容器设置开机自启：`docker update --restart=always 容器id/容器name`

### 安装EMQX

EMQX是一款完全开源，高可用低时延的百万级分布式物联网 MQTT 5.0 消息服务器。

官方文档：[产品概览 | EMQX 5.1 文档](https://www.emqx.io/docs/zh/v5/)

快速开始：

```bash
docker run -d --name emqx -p 1883:1883 -p 8083:8083 -p 8883:8883 -p 8084:8084 -p 18083:18083 emqx/emqx
```

访问dashboard：`http://ip:18083`。账号：admin，密码：public
