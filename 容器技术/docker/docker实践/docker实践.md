## 安装docker

**注意：docker及其实例都需要使用管理员权限安装、运行**

[CentOS Docker 安装 | 菜鸟教程 (runoob.com)](https://www.runoob.com/docker/centos-docker-install.html)

- 设置开机自启：`systemctl enable docker.service`
- 关闭开机自启：`systemctl disable docker.service`
- 查看是否设置开机自启：`systemctl list-unit-files | grep enable`
- 查看已启动的服务：`systemctl list-units --type=service`

## docker应用

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

#### Portainer

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

1. 启动容器：`docker run -p 3306:3306 --name mysql -e MYSQL_ROOT_PASSWORD=123456  -d mysql`

2. 拷贝配置文件到宿主机：`docker cp  mysql:/etc/mysql /root/docker/mysql`

3. 停止旧容器：`docker stop mysql`

4. 删除旧容器：`docker rm mysql`

5. 重新启动mysql：

   ```sql
   docker run \
   -p 3306:3306 \
   --name mysql \
   --privileged=true \
   --restart unless-stopped \
   -v /root/docker/mysql/mysql:/etc/mysql \
   -v /root/docker/mysql/logs:/logs \
   -v /root/docker/mysql/data:/var/lib/mysql \
   -v /etc/localtime:/etc/localtime \
   -e MYSQL_ROOT_PASSWORD=123456 \
   -d mysql
   ```
   
   - 创建容器时设置开机自启：`docker run --restart=always`
   
   - 已经创建了容器设置开机自启：`docker update --restart=always 容器id/容器name`
   
   - 账号：root，密码：123456。
   
   - 查看mysql版本：[Linux系统中查看Mysql数据库版本号的四种方法（图文完整版）_linux查看mysql版本-CSDN博客](https://blog.csdn.net/weixin_50093343/article/details/116751228) 
   

开启允许远程用户连接：

1. 参考连接：[MySQL8.0允许远程连接_mysql8.0允许远程访问-CSDN博客](https://blog.csdn.net/sinat_41721615/article/details/99417075) 
2. 进入mysql
3. 执行`mysql -u root -p`
4. 输入密码
5. `use mysql`

### 安装EMQX

EMQX是一款完全开源，高可用低时延的百万级分布式物联网 MQTT 5.0 消息服务器。

官方文档：[产品概览 | EMQX 5.1 文档](https://www.emqx.io/docs/zh/v5/)

快速开始：

```bash
docker run -d --name emqx -p 1883:1883 -p 8083:8083 -p 8883:8883 -p 8084:8084 -p 18083:18083 emqx/emqx
```

访问dashboard：`http://ip:18083`。账号：admin，密码：public

### 安装zentao

> 官方文档：[官方文档](https://www.zentao.net/book/zentaopms/455.html)

1. 使用内置数据库：`docker run -itd -v $PWD/data:/data -p 80:80 -e MYSQL_INTERNAL=true hub.zentao.net/app/zentao`
    - `$PWD`表示当前目录。

2. 使用外置mysql数据库（这里使用了docker安装的mysql：
    - 注意：ZT_MYSQL_HOST不能设置成127.0.0.1。

    ```bash
    docker run -itd \
        -v /home/vagrant/zentao/data:/data \
        -p 80:80 \
        -e MYSQL_INTERNAL=false \
        -e ZT_MYSQL_HOST=192.168.56.10 \
        -e ZT_MYSQL_PORT=3306\
        -e ZT_MYSQL_USER=root \
        -e ZT_MYSQL_PASSWORD=123456 \
        -e ZT_MYSQL_DB=zentao \
        easysoft/zentao
    ```

3. 访问`http://ip:80`进行配置。

### 安装Redis

> 官方文档：[在 Docker 上运行 Redis 堆栈 |雷迪斯](https://redis.io/docs/install/install-stack/docker/) 

- `docker run -itd --name redis-test -p 6379:6379 redis`

- 创建redis.conf配置文件：

  - `mkdir -p /root/docker/redis/conf`
  - `touch /root/docker/redis/conf/redis.conf`

- 启动容器：

  ```bash
  docker run \
  -d \
  --name redis \
  -p 6379:6379 \
  --restart unless-stopped \
  -v /root/docker/redis/data:/data \
  -v /root/docker/redis/conf/redis.conf:/etc/redis/redis.conf \
  redis:latest redis-server /etc/redis/redis.conf \
  --requirepass 123456 --appendonly yes
  ```

### 安装Nginx

1. 创建Ngnix配置文件：

   1. `mkdir -p /root/docker/nginx/conf`
   2. `mkdir -p /root/docker/nginx/log`
   3. `mkdir -p /root/docker/nginx/html`

2. 生成容器：`docker run --name nginx -p 9001:80 -d nginx `

   ```bash
   # 将容器nginx.conf文件复制到宿主机
   docker cp nginx:/etc/nginx/nginx.conf /root/docker/nginx/conf/nginx.conf
   # 将容器conf.d文件夹下内容复制到宿主机
   docker cp nginx:/etc/nginx/conf.d /root/docker/nginx/conf/conf.d
   # 将容器中的html文件夹复制到宿主机
   docker cp nginx:/usr/share/nginx/html /root/docker/nginx/
   ```

3. 删除容器：

4. 重新运行：

   ```bash
   docker run \
   -p 81:80 \
   --name nginx \
   -v /root/docker/nginx/conf/nginx.conf:/etc/nginx/nginx.conf \
   -v /root/docker/nginx/conf/conf.d:/etc/nginx/conf.d \
   -v /root/docker/nginx/log:/var/log/nginx \
   -v /root/docker/nginx/html:/usr/share/nginx/html \
   -d nginx
   ```

### 安装minio

1. apache对象存储

2. 启动：

   ```bash
   docker run \
      -p 9000:9000 \
      -p 9001:9001 \
      --name minio \
      -v /root/docker/minio/data:/data \
      -e "MINIO_ROOT_USER=admin" \
      -e "MINIO_ROOT_PASSWORD=12345678" \
      minio/minio server /data --console-address ":9001"
   
   ```

3. 访问：http://117.72.38.236:9001/login