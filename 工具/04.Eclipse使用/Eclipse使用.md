## 1. 反编译

### 1.1 下载jd-eclipse-2.0.0.zip文件

在官网[http://java-decompiler.github.io](http://java-decompiler.github.io/)下载Java Decompiler压缩包，进入官网拉到最下面，看到JD-Eclipse，点击下载压缩包

![](attachments/Pasted%20image%2020230512163139.png)

### 在eclipse中安装插件

在eclipse中选择Help-->Install New Software...，点击add按钮在弹出的Add Repository窗口点击Archive...

Name : 名称随便填写

Location : 选择下载的jd-eclipse-2.0.0.zip压缩包文件

![](attachments/Pasted%20image%2020230512163126.png)

勾选需要安装的插件，去掉下面Contact all update sites during......前面的勾

![](attachments/Pasted%20image%2020230512163224.png)

### 安装完成后重启eclipse

如果还是查看不了源代码，在eclipse中选择Window-->Preferences-->General-->Editors-->File Associations,根据下图配置

![](attachments/Pasted%20image%2020230512163042.png)

![](attachments/Pasted%20image%2020230512163017.png)

注意：如果没有看到JD Class File Viewer这个选项，点击Add按钮搜索一下，如下图

![](attachments/Pasted%20image%2020230512162949.png)