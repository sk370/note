## 使用anaconda开发本地python

1. 本机不安装python。
2. vscode编写python文件。
3. 点击运行，找到anaconda的python文件路径。
4. 打开`anaconda prompt`面板，执行`conda install -c anaconda xlrd`。（这样安装完了，所有使用anaconda环境的python环境的编辑器都能使用该模块）

## conda命令

conda 是 Anaconda 发行版中的包管理器，用于安装、更新、卸载软件包，以及创建和管理不同的 Python 环境。

### 环境管理

| 需求                  | 指令                                          |
| ------------------- | ------------------------------------------- |
| 创建一个名为 "myenv" 的新环境 | `conda create --name myenv`                 |
| 创建指定版本的环境           | `conda create --name myenv python=3.8`      |
| 激活环境                | `conda activate myenv`                      |
| 退出当前环境              | `deactivate`                                |
| 查看所有环境              | `conda env list`                            |
| 复制环境                | `conda create --name myclone --clone myenv` |
| 删除环境                | `conda env remove --name myenv`             |

### 包管理

| 需求       | 指令                                 |
| -------- | ---------------------------------- |
| 安装包      | `conda install package_name`       |
| 安装指定版本的包 | `conda install package_name=1.2.3` |
| 更新包      | `conda update package_name`        |
| 卸载包      | `conda remove package_name`        |
| 查看已安装的包  | `conda list`                       |

### 其他常用命令

| 需求          | 指令                          | 备注                     |
| ----------- | --------------------------- | ---------------------- |
| 查看帮助        | `conda --help`              |                        |
| 查看 conda 版本 | `conda --version`           |                        |
| 搜索包         | `conda search package_name` |                        |
| 清理不再需要的包    | `conda clean --all`         | 清理 conda 缓存，删除不再需要的软件包 |
| 查看已安装的包     | `conda list`                |                        |
