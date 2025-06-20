## 1. node-red安装

官方文档：[Node-RED](https://nodered.17coding.net/)

### 1.1 非全局安装

1. 官方文档：[Node-RED](https://nodered.17coding.net/)
2. 下载windows版本：[下载](https://github.com/node-red/node-red/releases/latest)
    - ![](attachments/2023-05-30.png)
3. 将node-red解压到想要安装的文件夹，如：
    - ![](attachments/2023-05-30-1.png)
4. 该文件夹下执行：`npm install --production`
5. 启动：在该文件夹下执行`node red.js`
    - ![](attachments/2023-05-30-3.png)
6. 访问：浏览器地址栏输入：[打开](http://localhost:1880/)

### 1.2 全局安装

1. 安装：
    - ![](attachments/2023-07-08.png)
2. 运行
    - ![](attachments/2023-07-08-1.png)
3. 后台运行：

    1. `nohup node red.js &`：
        - nohup 命令，在默认情况下（非重定向时），会输出一个名叫 nohup.out 的文件到当前目录下，如果当前目录的 nohup.out 文件不可写，输出重定向到 `HOME/nohup.out`文件中。
        - `&`：让命令在后台执行，终端退出后命令仍旧执行。
        - ![](./assets/2023-07-17.png)
    2. `ps -ef | grep red*`：查看进程是否运行。
        - ![](./assets/2023-07-17-1.png)
    3. `tail -f nohup.out`：查看输出日志。

### 1.3 离线安装节点模块

使用UI界面下载和使用命令行安装node-red节点模块，都会下载到：`C:\Users\zhuyuqi2\.node-red\node_modules`，对于windows系统，将`C:\Users\zhuyuqi2\.node-red`打包放到对应的位置即可。

### 1.4 docker安装

```bash
docker run -it -p 1880:1880 --name mynodered nodered/node-red-docker
```

