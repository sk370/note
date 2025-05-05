## 1. 安装docker

linux系统不同发行版安装的命令不一样：

### 1.1 Ubuntu

1. 更新软件包索引：`sudo apt update`
2. 安装依赖包：`sudo apt install apt-transport-https ca-certificates curl software-properties-common`
3. 添加 Docker 的官方 GPG 密钥：`curl -fsSL https://mirrors.aliyun.com/docker-ce/linux/ubuntu/gpg | sudo gpg --dearmor -o /usr/share/keyrings/docker-archive-keyring.gpg`
4. 添加 Docker 的 APT 仓库：`echo "deb [arch=amd64 signed-by=/usr/share/keyrings/docker-archive-keyring.gpg] https://mirrors.aliyun.com/docker-ce/linux/ubuntu $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null`
5. 更新软件包索引：`sudo apt update`
6. 安装 Docker：`sudo apt install docker-ce docker-ce-cli containerd.io`
7. 查看是否安装成功：`docker --version`

- 设置开机自启：`systemctl enable docker.service`或者`sudo systemctl enable docker`
- 关闭开机自启：`systemctl disable docker.service`
- 查看是否设置开机自启：`systemctl list-unit-files | grep enable`
- 查看已启动的服务：`systemctl list-units --type=service`
- 配置加速镜像su 镜像源：在 /etc/docker/ 中的 daemon.json 中添加下面内容 

```json
sudo mkdir -p /etc/docker
sudo tee /etc/docker/daemon.json <<-'EOF'
{
  "registry-mirrors": ["https://bnzui6g7.mirror.aliyuncs.com"]
}
EOF
sudo systemctl daemon-reload
sudo systemctl restart docker
```

### 1.2 CentOS

[CentOS Docker 安装 | 菜鸟教程 (runoob.com)](https://www.runoob.com/docker/centos-docker-install.html)

## 2. docker应用

### 2.1 docker可视化工具

[三种docker可视化工具（全网最详细）_CN-FuWei的博客-CSDN博客](https://blog.csdn.net/zfw_666666/article/details/126538026)

#### 2.1.1 docker.ui

- 官网：[GitHub - gohutool/docker.ui](https://github.com/gohutool/docker.ui)

查找镜像：`docker search docker.ui`

![](./assets/2023-08-07.png)

拉取镜像：`docker image pull joinsunsoft/docker.ui`

![](./assets/2023-08-07-1.png)

运行容器并不安装（ctrl + c会退出）：`docker container run --rm --name docker.ui -v /var/run/docker.sock:/var/run/docker.sock -p 8999:8999 joinsunsoft/docker.ui`

运行容器并后台运行：`docker container run -it -d --name docker.ui -v /var/run/docker.sock:/var/run/docker.sock -p 8999:8999 joinsunsoft/docker.ui`

**不要修改挂载容器数据卷的linux地址，会启动不了**

![](./assets/2023-08-07-4.png)

访问 `http://ip:8999` 即可打开gui界面，账号/密码：`ginghan/123456`

![](./assets/2023-08-07-5.png)

#### 2.2 Portainer

- 官网（CE版——社区开源）：[Install Portainer CE with Docker on Linux - Portainer Documentation](https://docs.portainer.io/start/install-ce/server/docker/linux)
- 官网（BE版——商业收费）：[Getting Started with Portainer Business](https://install.portainer.io/)——**可以了解容器知识**

查找镜像：`docker search portainer`

![](./assets/2023-08-07-6.png)

创建容器数据卷：`docker volume create portainer_data`

![](./assets/2023-08-07-7.png)

运行容器：`docker run -d -p 8000:8000 -p 9443:9443 --name portainer --restart=always -v /var/run/docker.sock:/var/run/docker.sock -v portainer_data:/data portainer/portainer-ce:latest`

**不要修改挂载容器数据卷的linux地址，会启动不了**

![](./assets/2023-08-07-8.png)

访问gui页面设置密码：`https://ip:9443`，账号admin，密码Courage@1115

![](./assets/2023-08-07-9.png)

重启容器：`docker restart portainer`

![](./assets/2023-08-07-10.png)

二者对比：

- docker ui：可以直接从镜像启动容器。
- portainer：可以直接展示未使用容器数据卷。

### 2.3 安装mysql

1. 启动容器：`docker run -p 3306:3306 --name mysql -e MYSQL_ROOT_PASSWORD=123456 -d mysql`

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

#### 2.3.1 开启允许远程用户连接

1. 参考连接：[MySQL8.0允许远程连接_mysql8.0允许远程访问-CSDN博客](https://blog.csdn.net/sinat_41721615/article/details/99417075) 
2. 进入mysql
3. 执行`mysql -u root -p`
4. 输入密码
5. `use mysql`
6. `select user,host from mysql.user;`

#### 2.3.2 修改内存占用

1. `docker stats`可以查看容器中各个应用占用的内存大小。

2. 当物理机内存小，而docker应用占用内存过多时，会经常杀死应用，同时如果docker设置了`--restart unless-stopped`属性，则应用会不断被杀死，不断重启，而数据会丢失。

   - 数据丢失的原因没找到，按理说设置了文件挂载，数据会持久化保存。

3. 进入MySQL容器内部：`docker exec -it mysql bash`

4. 切换至MySQL配置文件目录：`cd /etc/mysql/conf.d`

5. 修改配置文件内容：`vim docker.cnf`

   - 如果没有改文件，创建即可。
   - 如果设置了文件挂在，也可不进入容器内操作。

6. 添加参数：

   ```bash
   [mysqld]
   performance_schema_max_table_instances=400  
   table_definition_cache=400    #缓存
   performance_schema=off    #用于监控MySQL server在一个较低级别的运行过程中的资源消耗、资源东西
   table_open_cache=64    #打开表的缓存
   innodb_buffer_pool_chunk_size=64M    #InnoDB缓冲池大小调整操作的块大小
   innodb_buffer_pool_size=64M    #InnoDB 存储引擎的表数据和索引数据的最大内存缓冲区大小
   ```

### 2.4 安装EMQX

EMQX是一款完全开源，高可用低时延的百万级分布式物联网 MQTT 5.0 消息服务器。

官方文档：[产品概览 | EMQX 5.1 文档](https://docs.emqx.com/zh/emqx/latest/deploy/install-docker.html)

快速开始：

```bash
docker run -d --name emqx -p 1883:1883 -p 8083:8083 -p 8883:8883 -p 8084:8084 -p 18083:18083 emqx/emqx
```

访问dashboard：`http://ip:18083`。账号：admin，密码：public

挂载和端口映射：

```bash
docker run -d --name emqx \
  -p 1883:1883 -p 8083:8083 \
  -p 8084:8084 -p 8883:8883 \
  -p 18083:18083 \
  -v $PWD/emqx/data:/opt/emqx/data \
  -v $PWD/emqx/log:/opt/emqx/log \
  --privileged=true \
  emqx/emqx:5.6.1
```

> - 左侧都是本机
> - $PWD表示执行命令的当前目录（绝对路径）
> - `--privileged=true`表示容器具有root权限，避免容器无本机写入权限。

但是，按照上面的指令执行完毕后，容器并不能启动成功，查看日志有报错：

![alt text](assets/image.png)

原因是docker命令使用root账户运行，但emqx的容器使用的是emqx用户，在linux的系统中，创建文件不成功。参考：https://coala.top/archives/docker%E4%B8%8B%E8%BF%90%E8%A1%8Cemqx%E6%95%B0%E6%8D%AE%E5%8D%B7%E6%8C%82%E8%BD%BD%E6%8F%90%E7%A4%BA%E6%9D%83%E9%99%90%E4%B8%8D%E8%B6%B3%E8%A7%A3%E5%86%B3

修改之后的创建方式：

```bash
docker volume create emqx-data

sudo docker run -d --name emqx \
   -v emqx-data:/opt/emqx/data \
   -v emqx-data:/opt/emqx/log \
   -p 1883:1883 -p 8083:8083 \
   -p 8084:8084 -p 8883:8883 \
   -p 18083:18083  \
   emqx/emqx:5.6.1
```

> 这里emqx-data后面不能再写路径。

### 2.5 安装zentao

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

### 2.6 安装Redis

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

### 2.7 安装Nginx

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

3. 删除容器：`docker rm nginx`

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

### 2.8 安装minio

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

### 2.9 安装code-server

1. 浏览器版本的vscode
2. docker cli方式：

   ```bash
   docker run -d \
   --name=code-server \
   -e PUID=1000 \
   -e PGID=1000 \
   -e TZ=Etc/UTC \
   -e PASSWORD=password `#optional` \
   -e HASHED_PASSWORD= `#optional` \
   -e SUDO_PASSWORD=password `#optional` \
   -e SUDO_PASSWORD_HASH= `#optional` \
   -e PROXY_DOMAIN=code-server.my.domain `#optional` \
   -e DEFAULT_WORKSPACE=/config/workspace `#optional` \
   -p 8443:8443 \
   -v /path/to/code-server/config:/config \
   --restart unless-stopped \
   lscr.io/linuxserver/code-server:latest
   ```

   - `PUID`：设置用户ID，默认为1000。
   - `PGID`：设置用户组ID，默认为1000。
   - `TZ`：设置时区。
   - `PASSWORD`：设置密码。
   - `HASHED_PASSWORD`：设置密码的哈希值。
   - `SUDO_PASSWORD`：设置sudo密码。
   - `PROXY_DOMAIN`：设置代理域名。
   - `DEFAULT_WORKSPACE`：设置默认工作空间。
   - `/path/to/code-server/config`：设置配置文件路径。
   - `--restart unless-stopped`：设置容器在关闭后自动重启。
   - `lscr.io/linuxserver/code-server:latest`：镜像名称。
   - `8443:8443`：映射端口。

   ```bash
   docker run -d \
   --name=code-server \
   -e PUID=1000 \
   -e PGID=1000 \
   -e TZ=Etc/UTC \
   -e PASSWORD=918049 `#optional` \
   -e SUDO_PASSWORD=918049 `#optional` \
   -e DEFAULT_WORKSPACE=/config/workspace `#optional` \
   -p 8443:8443 \
   -v /root/docker/code-server/config:/config \
   --restart unless-stopped \
   lscr.io/linuxserver/code-server:latest
   ```

3. 访问：ip/8443

### 2.10 安装simplemindmap

1. 在线思维导图
2. docker cli

```bash
docker run -d -p 8081:8080 shuiche/mind-map:latest
```

3. 访问：http://ip:8081

### 2.11 安装showdoc

1. 在线知识库。
2. 官方文档：[ShowDoc](https://www.showdoc.cc/help/manual/install)   
3. dicker cli创建：

   ```bash
   docker run -d --name showdoc --user=root --restart=always --privileged=true -p 4999:80 \
   -v /root/docker/showdoc_data/html:/var/www/html/ star7th/showdoc
   ```

4. 访问：http://123.249.82.18:4999/。账户密码是showdoc/123456

### 2.12 安装onlyoffice

1. 在线文档编辑器。
2. 参考文档：
   - [OnlyOffice](https://helpcenter.onlyoffice.com/installation/docs-community-install-docker.aspx)
   - [实践](https://blog.51cto.com/u_16213372/12861068)
3. dicker cli创建：

   ```bash
   docker run -i -t -d -p 82:80 --restart=always \
    -v /root/onlyoffice/DocumentServer/logs:/var/log/onlyoffice  \
    -v /root/onlyoffice/DocumentServer/data:/var/www/onlyoffice/Data  \
    -v /root/onlyoffice/DocumentServer/lib:/var/lib/onlyoffice \
    -v /root/onlyoffice/DocumentServer/db:/var/lib/postgresql \
    -e DB_TYPE=mysql -e DB_HOST=127.0.0.1 -e DB_NAME=onlyoffice -e DB_USER=root -e DB_PASS=123456 \
    -e JWT_SECRET=my_jwt_secret onlyoffice/documentserver
   ```

   ```bash
   docker run -i -t -d -p 82:80 --restart=always \
    -v /root/onlyoffice/DocumentServer/logs:/var/log/onlyoffice  \
    -v /root/onlyoffice/DocumentServer/data:/var/www/onlyoffice/Data  \
    -v /root/onlyoffice/DocumentServer/lib:/var/lib/onlyoffice \
    -v /root/onlyoffice/DocumentServer/db:/var/lib/mysql \
    -e DB_TYPE=mysql -e DB_HOST=127.0.0.1 -e DB_NAME=onlyoffice -e DB_USER=root -e DB_PASS=123456 \
    -e JWT_SECRET=my_jwt_secret onlyoffice/documentserver
   ```

#### 2.13 安装mosquitto


```bash
docker run -it --name=mosquitto \
   --privileged  \
   -p 1883:1883 -p 9001:9001 \
   -v /root/docker/mosquitto/config/mosquitto.conf:/mosquitto/config/mosquitto.conf  \
   -v /root/docker/mosquitto/data:/mosquitto/data \
   -v /root/docker/mosquitto/log:/mosquitto/log \
   -d  eclipse-mosquitto
```

#### 2.14 安装mongodb


#### 2.15 安装rabbitmq


## 3.常见问题

### 3.1 关闭不掉进程

**问题：**

执行`systemctl stop docker`或`service docker stop`提示：`Warning: Stopping docker.service, but it can still be activated by: docker.socket`

**原因：**

Docker默认开启自动唤醒机制，即docker默认在关闭状态下被访问会自动唤醒Docker。

查看Docker是否开启自动唤醒机制：`systemctl status docker`

如果出现如下图的状态：即为开启自动唤醒机制

![c4759495d01d67c9cc8348a3881fe300.png](../_resources/c4759495d01d67c9cc8348a3881fe300.png)

**解决方式：**

1. 停用Docker自动唤醒机制：`systemctl stop docker.socket`
2. 查看Docker自动唤醒机制是否关闭：`systemctl status docker`
3. 停用Docker：`systemctl stop docker`
