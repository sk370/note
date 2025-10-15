*JavaWeb 是指，所有通过 Java 语言编写可以通过浏览器访问的程序的总称，叫 JavaWeb。JavaWeb 是基于请求和响应来开发的。*

> 本项目所使用到到的外部jar包![image.png](javaweb/image.png)

## 1. 前端基础

> 参见 Wbe 前端知识库：[Web 前端](https://www.yuque.com/zhuyuqi/lfm0mh?view=doc_embed)

1. 表单提交后，数据未提交（发给服务器）的三种情况：
   - 表单标签没有 name 属性
   - 单选、复选、下拉列表（`<option>`）都添加上 value 属性
   - 表单标签没有放在`<form>`标签内
   - action 属性不能带参数时，可以设计隐藏域`<input type="hidden">`使用
![image.png](javaweb/image-1669755009678.png)
2. GET 请求的特点是：
   - 浏览器地址栏中的地址是：action 属性[+?+请求参数]
   - 请求参数的格式是：name=value&name=value
   - 提交内容明文显示，不安全
   - 它有数据长度的限制
3. POST 请求的特点是：
   - 浏览器地址栏中只有 action 属性值
   - 相对于 GET 请求要安全
   - 理论上没有数据长度的限
4. &&运算符：全为真时，返回最后一个表达式的值：
   - `alert("abc"&&true)`，返回 true
   - `alert(true&&"abc")`，返回 abc
5. &&运算符：全为假时，返回第一个表达式为假的值：
   - `alert("abc"&&false)`，返回 false
6. || 运算符：
   - 当表达式全为假时，返回最后一个表达式的值
   - 只要有一个表达式为真，就会把回第一个为真的表达式的值
7. js 中变量未初始化，默认为 undefined。
8. 隐形参数 arguments：
   - js 中定义函数时声明形参个数与调用时参数无关，即声明时不传参数，但调用可传入参数，或声明传入参数、调用不传入参数。
   - 在函数内部使用 arguments 可以获取实际传入的参数个数，arguments 是一个数组，可以遍历。
   - 相当于定义函数时：`function(arguments)`，arguments 是一个可变长参数
9. 表单验证不合法时阻止提交：本质——`onsubmit="return false"`
```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <title>Title</title>
    <script type="text/javascript">
      // 静态注册表单提交事务
      function onsubmitFun() {
        // 要验证所有表单项是否合法，如果，有一个不合法就阻止表单提交
        alert("静态注册表单提交事件----发现不合法");

        return flase;
      }

      window.onload = function () {
        //1 获取标签对象
        var formObj = document.getElementById("form01");
        //2 通过标签对象.事件名 = function(){}
        formObj.onsubmit = function () {
          // 要验证所有表单项是否合法，如果，有一个不合法就阻止表单提交
          alert("动态注册表单提交事件----发现不合法");

          return false;
        };
      };
    </script>
  </head>
  <body>
    <!--return false 可以阻止 表单提交 -->
    <form
      action="http://localhost:8080"
      method="get"
      onsubmit="return onsubmitFun();"
    >
      <input type="submit" value="静态注册" />
    </form>
    <form action="http://localhost:8080" id="form01">
      <input type="submit" value="动态注册" />
    </form>
  </body>
</html>
```
10. 验证提示效果：
```html
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title></title>
    <link type="text/css" rel="stylesheet" href="../../static/css/style.css" />
    <script
      type="text/javascript"
      src="../../static/script/jquery-1.7.2.js"
    ></script>
    <script type="text/javascript">
      // 页面加载完成之后
      $(function () {
        // 给注册绑定单击事件
        $("#sub_btn").click(function () {
          // 验证用户名：必须由字母，数字下划线组成，并且长度为5到12位
          //1 获取用户名输入框里的内容
          var usernameText = $("#username").val();
          //2 创建正则表达式对象
          var usernamePatt = /^\w{5,12}$/;
          //3 使用test方法验证
          if (!usernamePatt.test(usernameText)) {
            //4 提示用户结果
            $("span.errorMsg").text("用户名不合法！");

            return false;
          }

          // 验证密码：必须由字母，数字下划线组成，并且长度为5到12位
          //1 获取用户名输入框里的内容
          var passwordText = $("#password").val();
          //2 创建正则表达式对象
          var passwordPatt = /^\w{5,12}$/;
          //3 使用test方法验证
          if (!passwordPatt.test(passwordText)) {
            //4 提示用户结果
            $("span.errorMsg").text("密码不合法！");

            return false;
          }

          // 验证确认密码：和密码相同
          //1 获取确认密码内容
          var repwdText = $("#repwd").val();
          //2 和密码相比较
          if (repwdText != passwordText) {
            //3 提示用户
            $("span.errorMsg").text("确认密码和密码不一致！");

            return false;
          }

          // 邮箱验证：xxxxx@xxx.com
          //1 获取邮箱里的内容
          var emailText = $("#email").val();
          //2 创建正则表达式对象
          var emailPatt =
            /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
          //3 使用test方法验证是否合法
          if (!emailPatt.test(emailText)) {
            //4 提示用户
            $("span.errorMsg").text("邮箱格式不合法！");

            return false;
          }

          // 验证码：现在只需要验证用户已输入。因为还没讲到服务器。验证码生成。
          var codeText = $("#code").val();

          //去掉验证码前后空格
          // alert("去空格前：["+codeText+"]")
          codeText = $.trim(codeText);
          // alert("去空格后：["+codeText+"]")

          if (codeText == null || codeText == "") {
            //4 提示用户
            $("span.errorMsg").text("验证码不能为空！");

            return false;
          }

          // 去掉错误信息
          $("span.errorMsg").text("");
        });
      });
    </script>
    <style type="text/css">
      .login_form {
        height: 420px;
        margin-top: 25px;
      }
    </style>
  </head>
  <body>
    <div id="login_header">
      <img class="logo_img" alt="" src="../../static/img/logo.gif" />
    </div>

    <div class="login_banner">
      <div id="l_content">
        <span class="login_word">欢迎注册</span>
      </div>

      <div id="content">
        <div class="login_form">
          <div class="login_box">
            <div class="tit">
              <h1>注册尚硅谷会员</h1>
              <span class="errorMsg"></span>
            </div>
            <div class="form">
              <form action="http://localhost:8080">
                <label>用户名称：</label>
                <input
                  class="itxt"
                  type="text"
                  placeholder="请输入用户名"
                  autocomplete="off"
                  tabindex="1"
                  name="username"
                  id="username"
                />

                <label>用户密码：</label>
                <input
                  class="itxt"
                  type="password"
                  placeholder="请输入密码"
                  autocomplete="off"
                  tabindex="1"
                  name="password"
                  id="password"
                />

                <label>确认密码：</label>
                <input
                  class="itxt"
                  type="password"
                  placeholder="确认密码"
                  autocomplete="off"
                  tabindex="1"
                  name="repwd"
                  id="repwd"
                />

                <label>电子邮件：</label>
                <input
                  class="itxt"
                  type="text"
                  placeholder="请输入邮箱地址"
                  autocomplete="off"
                  tabindex="1"
                  name="email"
                  id="email"
                />

                <label>验证码：</label>
                <input
                  class="itxt"
                  type="text"
                  style="width: 150px;"
                  id="code"
                />
                <img
                  alt=""
                  src="../../static/img/code.bmp"
                  style="float: right; margin-right: 40px"
                />

                <input type="submit" value="注册" id="sub_btn" />
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div id="bottom">
      <span> 尚硅谷书城.Copyright ©2015 </span>
    </div>
  </body>
</html>
```
11. 正则表达式：[常用正则表达式.txt](https://www.yuque.com/attachments/yuque/0/2022/txt/1604140/1656635437474-a756d548-72b5-4d16-a716-2b86802009db.txt)
    - `patt.test(str)`：验证 str 是否满足 patt 的规则。
12. `<base>`标签：
    - 设置当前页面中所有相对路径的参照路径
13. web 中`/`的不同含义：代表绝对路径
    - 浏览器解析：【<http://ip:port/>】
    - 服务器解析：【<http://ip:port/>工程路径】
      - 服务器重定向时（`response.sendRediect("/")`）：表示把`/`发送给浏览器解析，得到的是【<http://ip:port/>】

## 2. BS 和 CS

### 2.1 BS

1. 浏览器服务器架构模式
   - 优点：客户端不需要安装，升级维护成本较低
   - 缺点：所有计算和存储都放在服务器端，服务器计算完成后将结果传输给客户端，服务器符合较重，且客户端与服务器端会进行非常频繁的数据通信，导致网络负荷较重。

### 2.2 CS

1. 客户端服务器架构模式
   - 优点：充分来利用客户端机器资源，减轻服务器的负荷。
     - 一部分安全要求不高的计算任务、存储任务放在客户端执行，减轻服务器的压力，也减轻网络负荷。
   - 缺点：需要安装客户端程序，且升级维护成本较高。

## 3. Tomcat 的使用

### 3.1 Tomcat 简介

1. Tomcat 服务器是一个免费的开放源代码的 Web 应用服务器，属于轻量级应用服务器，在中小型系统和并发访问用户不是很多的场合下被普遍使用，是开发和调试 JSP 程序的首选。
2. java 官方提供。
3. 版本
   - ![image.png](javaweb/image-1669755020672.png)
4. import 包时，tomcat8 和 tomcat10 的包名不一样

### 3.2 Tomcat 使用

1. 下载：
   - 使用 JDK8 开发，本文下载了 Tomcat8.5 版本。
   - ![image.png](javaweb/image-1669755022813.png)
2. 安装：解压至非中文、无空格、特殊字符路径下。
3. 目录介绍：
   - ![image.png](javaweb/image-1669755030888.png)
     - bin：程序运行文件夹
     - lib：程序运行的依赖库
     - conf：配置文件
     - logs：运行日志
     - temp：缓存文件目录
     - webapps：项目布署目录
     - work：工作目录
4. 配置环境变量：
   - 前提：需要配置`JAVA_HOME`环境变量。虽然安装 java 时，环境变量`Path`下已经有默认配置，能够在任何位置的 cmd 终端执行`java`命令，但配置 Tomcat 仍然需要重新配置`JAVA_HOME`，否则会出现执行 Tomcat 的开启命令会出现一闪而过的情况，Tomcat 也不会运行起来。
     - ![image.png](javaweb/image-1669755033711.png)
   - 配置了 java 环境变量后，执行`bin/startup.bat`
     - ![image.png](javaweb/image-1669755037156.png)
     - 乱码问题：修改`conf/logging.properties`文件，添加`java.util.logging.ConsoleHandler.encoding = GBK`指令。
       - ![image.png](javaweb/image-1669755040109.png)
   - 访问：[http://localhost:8080/](http://localhost:8080/)
     - ![image.png](javaweb/image-1669755042050.png)
5. 新建 Web 项目，并布署：
   - 在`webapps`路径下，新建`网址名/WEB-INF`，如`webdesign`，将写好的网页放到该目录下
     - ![image.png](javaweb/image-1669755046538.png)
     - ![image.png](javaweb/image-1669755048912.png)
     - `WEB-INF`不能修改。

## 4. IDEA 中新建 Web 项目

### 4.1 创建项目

#### 4.1.1 方式一

1. 不同版本的 IDEA 创建 web 项目的操作方式，本文以 IDEA2022.1.3 为例。
2. 新建项目（模块），指明存放路径，jdk 版本
   - ![image.png](javaweb/image-1669755051914.png)
   - ![image.png](javaweb/image-1669755054219.png)
3. 右键项目（模块），添加 Web 框架支持
   - ![image.png](javaweb/image-1669755055863.png)
   - ![image.png](javaweb/image-1669755058136.png)
   - ![image.png](javaweb/image-1669755060470.png)

#### 4.1.2 方式二

1. 新建模块：
   - ![image.png](javaweb/image-1669755062234.png)
2. 设置项目结构
   - ![image.png](javaweb/image-1669755064089.png)
3. 添加 Web 程序：
   - ![image.png](javaweb/image-1669755065821.png)
4. 生成部署包（工件、atrifacts）：
   - ![image.png](javaweb/image-1669755067486.png)
   - 拓展：war 包，压缩后的 web 应用程序包，打包放到 tomcat 的 webapps 路径下，会自动解压
     - ![image.png](javaweb/image-1669755069785.png)

#### 4.1.2 导入其他人项目

1. 同 4.1.1 操作
2. ![image.png](javaweb/image-1669755071776.png)

### 4.2 运行配置（Tomcat 配置）

1. 添加 Tomcat 本地服务器
   - ![image.png](javaweb/image-1669755078275.png)
   - ![](javaweb/2022-11-30-18-26-42.png)
   - ![image.png](javaweb/image-1669755080857.png)
2. 配置访问地址：
   - ![image.png](javaweb/image-1669755082876.png)
   - url 表示默认打开浏览器时的地址。
3. 部署服务器端的文件（使服务器能打开本地文件）
   - ![image.png](javaweb/image-1669755085025.png)
   - ![image.png](javaweb/image-1669755088305.png)
   - `应用程序上下文`：设置为`/`时，表示访问服务器根目录即能访问到该 web 程序。 
   - 如果找不到工件，则按照下面的方式 
      - ![image.png](javaweb/image-1669755089820.png)![image.png](javaweb/image-1669755091528.png)

> 配置总结：
>
> - 这个页面的地址最后带不带/都没有关系![image.png](javaweb/image-1669755099005.png)
> - 这个页面的这个名称要写到第一个图的地址后面![image.png](javaweb/image-1669755106196.png)
> - 这个界面的路径要到`web`目录下![image.png](javaweb/image-1669755108860.png)

### 4.3 热更新配置

1. 设置热更新：
   - ![image.png](javaweb/image-1669755111640.png)

### 4.4 配置 servlet api

1. 要能写服务端程序，则要求改程序必须实现自 servlet 接口或继承自 GenericServlet 类和 HttpServlet 类（参见 5.1）
2. 方式一：添加`servlet-api.jar`至 lib 文件夹，同 Druid
   - ![image.png](javaweb/image-1669755116473.png)
3. 方式二：
   - ![image.png](javaweb/image-1669755118613.png)
   - ![image.png](javaweb/image-1669755120832.png)
   - 添加后会显示为外部库文件
     - ![image.png](javaweb/image-1669755123782.png)

### 4.5 部分错误类型及解决方式

#### 4.5.1 未添加 Servlet 运行程序（容易 404）

1. ![image.png](javaweb/image-1669755126135.png)
2. ![image.png](javaweb/image-1669755129528.png)

## 5. Servlet 入门

[06*尚硅谷\_Servlet*王振国 - 课堂笔记.pdf](https://www.yuque.com/attachments/yuque/0/2022/pdf/1604140/1656685756637-9de0d9a4-333f-4269-a645-e8560308393e.pdf)
![](javaweb/20181022211418240)

### 5.1 Servlet 简介

#### 5.1.1 Servlet 介绍

1. 作用：Servlet 是 Server Applet 的缩写，称为服务器端小程序，是一种使用 Java 语言来开发动态网站的技术。
2. Serlet 几乎可以使用 Java 的所有 API。
3. 出现背景：使用 Java 原生代码开发动态网站需要手动解析 HTTP 请求报头、需要分析用户的请求参数、需要加载数据库组件……过程极其麻烦，基于上述原因，Java 官方推出了 Servlet 技术，对开发动态网站需要使用的 JavaAPI 进行了封装，形成了一套新的 API，称为 Servlet API。
4. 本质：Servlet 是一套 Java Web 的开发规范（开发技术标准），只有实现（编写代码实现 Servlet 规范中的各类功能——类、方法、属性）了该规范才能进行 web 开发。
5. 体现：一个 Servlet 程序实际上是按照 Servlet 规范编写的 Java 类，Servlet 程序需要编译成字节码文件（`.class`）才能部署到服务器运行。
6. Servlet 功能实现方式：实现 Servlet 接口能获得 Servlet 的所有功能。但需要实现很多方法，比较麻烦，所以 Servlet 规范提供了两个实现了 Servlet 接口常用功能（方法）的类：GenericServlet 类和 HttpServlet 类，其中 HttpServlet 类的使用更为方便。
   - ![image.png](javaweb/image-1669755133673.png)
7. Servlet 就是 JavaWeb 三大组件之一。三大组件分别是：Servlet 程序、Filter 过滤器、Listener 监听器 。

#### 5.1.2 Servlet 继承关系

1. 类图：
   - ![image.png](javaweb/image-1669755135547.png)
   - ![image.png](javaweb/image-1669755138062.png)
   - Tomcat8 中，包为`javax`，Tomcat10 中，包为`jakarta`
2. 相关方法：
   - `javax.servlet.Servlet`接口:
     - `void init(config)` - 初始化方法
     - `void service(request,response)`- 服务方法
     - `void destory()`- 销毁方法
   - `javax.servlet.GenericServlet`抽象类：
     - `void service(request,response)`- 仍然是抽象的
   - `javax.servlet.http.HttpServlet` 抽象子类：
     - `void service(request,response)`- 不是抽象的
3. 当有请求过来时，service 方法会自动响应（其实是 tomcat 容器调用的）

### 5.2 Servlet 容器（Web 容器）

#### 5.2.1 Web 服务器

1. web 服务器功能比较单一，一般只能提供 http(s)服务，使用户可以访问静态资源（HTML 文档、图片、CSS 文件、JavaScript 文件等），它们不能执行任何编程语言，也不能访问数据库，更不能让用户注册和登录。——静态网站。
2. 要部署动态网站，还要有编程语言运行环境和数据库管理系统的支持。
   - 运行环境：支持脚本语言运行的部件（解释器、垃圾回收器、标准库等）的统称。又称运行时（Runtime)
   - 数据库：mysql 等
3. 常见 web 服务器：
   - 运行 PHP 网站一般选择 Apache 或者 Nginx；
   - 运行 ASP/ASP.NET 网站一般选择 IIS；
   - 运行 Python 网站一般选择内置的 WSGI 服务器模块——wsgiref。
4. 动态网站三大组件：Web 服务器+脚本语言运行环境+数据库

#### 5.2.2 web 容器

1. servlet 容器：servlet 代码的运行环境，包括：
   - jre（JVM、Java 核心类库、一些属性文件）~~，并不支持 Servlet 规范？~~
   - 实现 Servlet 规范定义的各类接口和类：
   - 管理用户编写的 Servlet 类，以及实例化以后的对象——我们编写的 Servlet 类没有 main()，不能独立运行，知道能当作一个模块加载到 Servlet 容器，由容器进行实例化并调用方法。
   - 提供 HTTP 服务，相当于简化 web 服务器。
2. 常见 web 容器：Tomcat、Jboss、Jetty、WebLogic 等，实现了 Servlet 规范。
3. 工作过程：一个动态页面对应一个 Servlet 类，开发一个动态页面就是编写一个 Servlet 类，当用户请求到达时，Servlet 容器会根据配置文件（web.xml）来决定调用哪个类。

### 5.3 url 地址到 Servlet 程序的访问

#### 5.3.1 Servlet 部署

1. Servlet 规范规定，JavaWeb 应用必须采用固定的目录结构，即每种组件在 JavaWeb 应用中都有固定的存放目录。![image.png](javaweb/image-1669755141829.png)

| 目录 | 描述 | 是否必需 |
| --- | --- | --- |
| \\servletDemo | Web 应用的根目录，属于该 Web 应用的所有资源都存放在这个目录下。 | 是 |
| \\servletDemo\\WEB-INF | 存放 web.xml、lib 目录以及 classes 目录等。 | 是 |
| \\servletDemo\\WEB-INF\\classes | 存放各种 .class 文件或者包含 .class 文件的目录，Servlet 类的 .class 文件也存放在此。 | 否 |
| \\servletDemo\\WEB-INF\\lib | 存放应用所需的各种 jar 包，例如 JDBC 驱动程序的 jar 包。 | 否 |
| \\servletDemo\\WEB-INF\\web.xml | web.xml 中包含应用程序的配置和部署信息。 | 是 |

2. 部署（手动不使用 IDEA 工具的方式）：将 servletDemo 文件夹复制到`/webapps`目录下
   - 实质是在`/webapps`目录下编写程序。

#### 5.3.2 Servlet 访问配置

![image.png](javaweb/image-1669755144697.png)

#### 5.3.3 Servlet 虚拟路径映射

1. Servlet 单一映射：
   - 方式一：在 web.xml 文件中配置
   - 方式二：使用@WebServlet：`@WebServlet("/MyServlet")`，省略了 urlPatterns =
2. Servlet 多重映射：
   - 方式一：在 web.xml 文件中配置多个`<servlet-mapping>`元素
   - 方式一：在 web.xml 文件中配置多个`<url-pattern>`元素
   - 方式二：使用@WebServlet：`@WebServlet(urlPatterns = {"/MyServlet", "/MyServlet2"})`

#### 5.3.4 项目中地址的使用

1. 开发中一般使用绝对路径+相对路径的形式，或者绝对路径的形式
2. `<base>`标签设置在 html 文件中，只对当前的 html 页面生效。写路径时`/`配合`<base>`标签一起看。
   - 一般不写`/`
   - 但也可以写为`./`
   - html 页面的所有地址【HTML 的和 servlet 的】，都要配合`<base>`标签一起看。
   - 建议规范：html 页都不带`/`
3. 在 servlet 程序中，默认为工程路径（Tomcat 配置的 http 全路径）【忽略了 src 目录、各个包目录，直接到类】，写路径时带不带`/`都不影响【有影响，但规律还没把握】
   - 建议规范：servlet 页面都带`/`
   - `<%@include file="/pages/common/manager_meun.jsp"%>`必须以`/`开头
4. `/`被 web 解析，表示`http://ip:port/`，被服务器解析，表示`http://ip:port/工程路径`

### 5.4 Servlet 的生命周期

![image.png](javaweb/image-1669755148358.png)

1. Servlet 构造器方法（创建 Servlet 实例——由于是 tomcat 去创建，所以构造器不能用 private 修饰）
   - 第一次访问时调用。
2. init 初始化方法
   - 第一次访问时调用。
   - 作用：建立数据库连接，获取配置信息等
3. service 方法
   - 每次访问都会调用。
4. destroy 销毁方法
   - web 工程停止的时候调用（工程停止不是切换浏览器的访问地址）
5. 默认情况下，第一此请求时 tomcat 才会创建 Servlet 实例化对象、调用`init()`，便于提高启动速度，但会造成首次请求等待时间较长。
6. xml 文件中，配置`<load-on-startup>`控制创建实例对象、初始化方法的时机【见 5.7.4】。这样的好处是提高了首次请求的响应时间，但带来问题是启动变慢。
7. Servlet 在容器中是单例、线程不安全的。
   - 单例：所有的请求都是同一个实例去响应
   - 线程不安全：一个线程需要根据这个实例中的某个成员变量值去做逻辑判断。但是在中间某个时机，另一个线程改变了这个成员变量的值，从而导致第一个线程的执行路径发生了变化
     - 尽量的不要在 servlet 中定义成员变量。如果不得不定义成员变量，那么：① 不要去修改成员变量的值 ② 不要去根据成员变量的值做一些逻辑判断

### 5.5 GET 和 POST 请求分发处理

1. 实现 Servlet 接口的 HttpServlet 类中的 service 方法，会根据 http 数据传输方式不同做不同的处理。

```java
protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String method = req.getMethod();
    long lastModified;
    if (method.equals("GET")) {
        lastModified = this.getLastModified(req);
        if (lastModified == -1L) {
            this.doGet(req, resp);
        } else {
            long ifModifiedSince;
            try {
                ifModifiedSince = req.getDateHeader("If-Modified-Since");
            } catch (IllegalArgumentException var9) {
                ifModifiedSince = -1L;
            }

            if (ifModifiedSince < lastModified / 1000L * 1000L) {
                this.maybeSetLastModified(resp, lastModified);
                this.doGet(req, resp);
            } else {
                resp.setStatus(304);
            }
        }
    } else if (method.equals("HEAD")) {
        lastModified = this.getLastModified(req);
        this.maybeSetLastModified(resp, lastModified);
        this.doHead(req, resp);
    } else if (method.equals("POST")) {
        this.doPost(req, resp);
    } else if (method.equals("PUT")) {
        this.doPut(req, resp);
    } else if (method.equals("DELETE")) {
        this.doDelete(req, resp);
    } else if (method.equals("OPTIONS")) {
        this.doOptions(req, resp);
    } else if (method.equals("TRACE")) {
        this.doTrace(req, resp);
    } else {
        String errMsg = lStrings.getString("http.method_not_implemented");
        Object[] errArgs = new Object[]{method};
        errMsg = MessageFormat.format(errMsg, errArgs);
        resp.sendError(501, errMsg);
    }

}
```

2. 如果后端的处理方法（do 方法）与请求方法不一致，会报 405 错误

### 5.6 IDEA 创建 Servlet 程序

1. 确认模块添加了 Tomcat 依赖（步骤 4.4，即添加了 servlet api）
   - ![image.png](javaweb/image-1669755152347.png)
2. 右键 src 目录，新建 servlet 程序
   - ![image.png](javaweb/image-1669755155199.png)

### 5.7 Servlet 底层原理

#### 5.7.1 Servlet 类的继承关系

![image.png](javaweb/image-1669755157292.png)

#### 5.7.2 ServletConfig 类

1. servlet 程序的配置信息类
2. Servlet 程序和 ServletConfig 对象都是由 Tomcat 创建的，程序员负责使用
   - Servlet 程序默认是第一次访问时创建
   - ServletConfig 是每个 Servlet 程序创建时创建
3. 获取（假设 MyServlet 继承自 HttpServlet）：
   - 方式一：定义`private ServletConfig servletConfig`，重写`init()`方法
     - 如果不声明`servletConfig`，则必须在重写`init()`方法时，显式的调用`super.init(config)`，否则会空指针异常
   - 方式二：调用 GenericServlet 提供的 getServletConfig()方法：`ServletConfig servletConfig = this.getServletConfig()`
4. 配置 Servlet 初始化参数：
   - 方式一：在 web.xml 文件中配置
   - 方式二：使用@WebServlet 配置
5. 作用：
   - 获取 Servlet 程序的别名`<servlet-name>`的值。（web.xml 文件中）
     - 使用`servletConfig.getServletName()`
   - 获取初始化参数`<init-param>`
     - 使用`servletConfig.getInitParameter("参数名")`——参数名是 web.xml 文件中配置的`<param-name>`，获取到的值是`<param-value>`
   - 获取 ServletContext 对象:getServletContext() 方法
     - ![](javaweb/2022-11-30-19-12-04.png)

#### 5.7.3 ServletContext 类

1. 表示 Servlet 上下文对象的一个接口，一个 web 工程，只有一个 ServletContext 实例。
   - Servlet 上下文：Servlet 容器启动时，为每个 Web 应用（webapps 文件夹下的每个目录）创建一个唯一的 ServletContext 对象，该对象称为 Servlet 上下文
2. ServletContext 对象是一个域对象
   - 域对象：可以像 map 一样存储数据的对象，域指的是存储数据的操作范围，域对象是值服务器在内存上创建的存储空间，该空间用于不同资源之间共享数据。
   - ServletContext 的域是整个工程。所以不同 Servlet 之间可以通过 ServletContext 对象实现数据通信。
     - 域对象特性：![image.png](javaweb/image-1669755162284.png)
   - 另外两个域对象时 reques 和 session
3. ServletContext 在 web 工程启动时创建，停止时销毁。
4. 获取 ServletContext 对象：
   - 通过 GenericServlet 提供的 getServletContext() 方法
   - 通过 ServletConfig 提供的 getServletContext() 方法
   - 通过 HttpSession 提供的 getServletContext() 方法
   - 通过 HttpServletRequest 提供的 getServletContext() 方法
5. 作用：
   - 获取 web.xml 中配置的上下文参数 context-param
     - `getInitParameter(str)`
   - 获取当前的工程路径，格式: `/工程路径`
     - ![](javaweb/2022-11-30-19-14-02.png)
     - `getContextPath()`
   - 获取工程部署后在服务器硬盘上的绝对路径，`D:\实践练习\07.JavaWeb\out\artifacts\servlet1_8_war_exploded\`
     - `getRealPath("/")`
   - 像 Map 一样存取数据
6. 获取上下文初始化参数：
   - 设置上下文初始化参数：
     - 方式一：在 web.xml 文件中，使用`<context-param>`及其子标签
     - 方式二：在 Servlet 程序中，使用 ServletContext 对象的`setAtrribute()`方法
   - 获取上下文初始化参数：
     - 方式一：![image.png](javaweb/image-1669755166488.png)
     - 方式二：![image.png](javaweb/image-1669755168049.png)
   - 两种方式对比：
     - ![image.png](javaweb/image-1669755169892.png)
7. 读取 web 应用下的资源文件
   - ![image.png](javaweb/image-1669755171972.png)
   - 参数 path 代表资源文件的虚拟路径，它以正斜线`/`开始，`/`表示当前 Web 应用的根目录。

#### 5.7.4 load-on-startup 元素

1. 作用：控制 Servlet 启动优先级
2. 使用在 web.xml 文件中，是`<servlet>`的子元素节点。
3. 取值规则
   - 它的取值必须是一个整数；
   - 当值小于 0 或者没有指定时，则表示容器在该 Servlet 被首次请求时才会被加载；
   - 当值大于 0 或等于 0 时，表示容器在启动时就加载并初始化该 Servlet，取值越小，优先级越高；
   - 当取值相同时，容器就会自行选择顺序进行加载。

#### 5.7.5 HttpServletRequest 类

1. 作用：每次 Servlet 容器接收到 HTTP 请求，就会创建一个 HttpServletRequest 对象，通过该对象，可以获取对象传递给服务器的信息。
2. 生命周期：Servlet 容器接到 HTTP 请求时创建，Servlet 容器将响应信息返回给客户端后销毁。
3. 获取请求行的方法：
   - ![image.png](javaweb/image-1669755174435.png)
   - `getRemoteHost()`：在浏览器中使用 localhost、127.0.0.1 访问时，返回 127.0.0.1，使用真实 ip 访问时，返回真实的客户端 ip
4. 获取请求头的方法
   - ![image.png](javaweb/image-1669755176290.png)
5. 获取表单数据的方法：
   - ![image.png](javaweb/image-1669755178348.png)
6. 中文乱码问题：
   - GET 请求：Get 请求将请求数据附加到 URL 后面作为参数，浏览器发送文字时采用的编码格式与页面编码保持一致（utf-8）。如果 Tomcat 没有设置字符集，接收 URL 时默认使用 ISO-8859-1 进行解码，ISO-8859-1 不兼容中文
   - POST 请求：POST 提交的数据在请求体中，其所使用的编码格式时页面一致（即 utf-8）。request 对象接收到数据之后，会将数据放到 request 缓冲区，缓冲区的默认字符集是 ISO-8859-1（该字符集不支持中文），两者使用的字符集不一致导致乱码。
   - GET 请求：【jdk8 之前，jdk8 及之后版本不会乱码】
     - 方式一：修改 tomcat/conf/server.xml 中的配置：`<Connector port="80" protocol="HTTP/1.1" connectionTimeout="20000" redirectPort="8443" URIEncoding="UTF-8"/>`【70 行左右】
     - 方式二：使用 URLEncoder 和 URLDecoder 进行编码和解码的操作（逆向编解码）：
       - `String username = request.getParameter("username");`
       - `username = URLEncoder.encode(username, "ISO8859-1");`
       - `username = URLDecoder.decode(username, "UTF-8");`
     - 方式三：使用 String 的构造方法：String(byte[] bytes, String charset) ，对字节数组（bytes）按照指定的字符集（charset）进行解码，返回解码后的字符串
       - `String username = request.getParameter("username");`
       - `username = new String(username.getBytes("ISO-8859-1"),"UTF-8");`
   - POST 请求：
     - 在获取请求参数之前设置 request 缓冲区字符集为 utf-8
       - `request.setCharacterEncoding("utf-8");`
       - `String username = request.getParameter("username");`

#### 5.7.6 请求转发

1. 含义：容器接收请求后，Servlet 会先对请求做一些预处理，然后将请求传递给其他 Web 资源，来完成包括生成响应在内的后续工作。
2. 对象：RequestDispatcher 对象由 Servlet 容器创建，用于封装由路径所标识的 Web 资源。利用 RequestDispatcher 对象可以把请求转发给其他的 Web 资源。
3. 获取 RequestDispatcher 对象：
   - 调用 ServletContext 的 getRequestDispatcher(String path) 方法，参数 path 指定目标资源的路径，必须为绝对路径——以`/`打头；
   - 调用 ServletRequest 的 getRequestDispatcher(String path) 方法，参数 path 指定目标资源的路径，可以为绝对路径，也可以为相对路径。
   - 绝对路径是指以符号“/”开头的路径，“/”表示当前 Web 应用的根目录。相对路径是指相对当前 Web 资源的路径，不以符号“/”开头。
4. RequestDispatcher 对象的方法：
   - ![image.png](javaweb/image-1669755182186.png)
5. 请求转发特点：
   - 请求转发不支持跨域访问，只能跳转到当前应用中的资源。
   - 请求转发之后，浏览器地址栏中的 URL 不会发生变化，因此浏览器不知道在服务器内部发生了转发行为，更无法得知转发的次数。
   - 参与请求转发的 Web 资源之间共享同一 request 对象和 response 对象。
   - 由于 forward() 方法会先清空 response 缓冲区，因此只有转发到最后一个 Web 资源时，生成的响应才会被发送到客户端。
6. request 域对象：
   - 作用：不同 Servlet 程序间传递数据
   - 方法：
     - ![image.png](javaweb/image-1669755184218.png)
   - 与 Context 域对象的不同点：
     - ![image.png](javaweb/image-1669755185959.png)

#### 5.7.7 HttpServletResponse 类

1. 作用：每次 Servlet 容器接收到 HTTP 请求，就会创建一个 HttpServletResponse 对象，通过该对象，可以向客户端传递信息。
2. 生命周期：Servlet 容器接到 HTTP 请求时创建，Servlet 容器将响应信息返回给客户端后销毁。
3. 设置响应行的方法：
   - ![image.png](javaweb/image-1669755188439.png)
4. 设置响应头的方法
   - ![image.png](javaweb/image-1669755189891.png)
5. 向客户端传递信息（响应体设置）的方法：
   - ![image.png](javaweb/image-1669755192928.png)
   - getOutputStream() 和 getWriter() 方法互相排斥，不可同时使用，否则会发生 IllegalStateException 异常。
6. 中文乱码问题：
   - 字节流输出：不一定乱码，取决于服务器 Servlet 程序的字符集是否与浏览器字符集一致。
   - 字符流输出：一定乱码。response 缓冲区的默认字符集是 ISO-8859-1，该字符集不支持中文。
   - 字节流解决方案：将中文转成字节数组时和浏览器默认采用的字符集保持一致
     - `response.setHeader("Content-Type", "text/html;charset=UTF-8");`
     - `byte[] str = "编程帮 www.biancheng.net".getBytes("UTF-8");`
   - 字符流解决方案：将 resopnse 缓冲区编码与浏览器设置一致
     - 方式一：
       - `response.setCharacterEncoding("UTF-8");`
       - `response.setHeader("Content-Type", "text/html;charset=UTF-8");`
     - 方式二：
       - `response.setContentType("text/html;charset=UTF-8");`

#### 5.7.8 请求重定向

1. 含义：服务器端接收到请求后，通知客户端去请求另外的地址。
2. 属于客户端行为。
3. 本质上是 2 次 Http 请求。
4. 工作流程：
   - ![image.png](javaweb/image-1669755195614.png)
5. 重定向和转发的区别：
   - ![image.png](javaweb/image-1669755197348.png)
6. 方法：
   - 方式一：
     - ![image.png](javaweb/image-1669755200714.png)
   - 方式二：（推荐）
     - ![image.png](javaweb/image-1669755205721.png)
     - 注意路径问题，该路径由浏览器解析
7. **经典案例：生成验证码**

### 5.8 @WebServlet 注解（Servlet 注解）

#### 5.8.1 Servlet 注解介绍

1. 出现原因：解决 Servlet 程序过多，web.xml 文件过长的问题。
2. 使用方式：注解写在 Servlet 程序（类）中，只对当前类有效。
3. 出现版本：Servlet3.0，使用注解使 web.xml 不再是必选项。
   - @WebServlet
   - @WebInitParm
   - @WebFilter
   - @WebLitener 等

#### 5.8.2 @WebServlet 注解的属性

![image.png](javaweb/image-1669755208601.png)

#### 5.8.3 @WebServlet 注解的使用

1. 在 web.xml 文件中，将属性`metadata-complete`设置为 fasle 或不配置时，表示启用注解支持，配置为 true 时，表示容器部署时至依赖 web.xml
2. 示例：
```java
    @WebServlet(
      asyncSupported =**true**,
      name ="myServlet",
      description ="name 描述",
      loadOnStartup =1,
      urlPatterns ={"/MyServlet","/\*"},
      initParams ={
      @WebInitParam(name ="编程帮", value ="www.biancheng.net", description ="init 参数 1"),
      @WebInitParam(name ="京东", value ="www.jd.com", description ="init 参数 2")
    })
```
3. 注意点：
   - 通过实现 Serlvet 接口或继承 GenericServlet 创建的 Servlet 类无法使用 @WebServlet 注解。
   - 通过 web.xml 文件和注解同时配置属性时，若取值相同则忽略注解中的配置。

### 5.9 Listener 监听器

#### 5.9.1 介绍

1. 本质：JavaEE 的规范（接口）
2. 作用：监听另一个 Java 对象的方法调用或属性改变，当监听到上述事件，监听器的方法立即执行。

#### 5.9.2 分类

1. Servlet 规范中定义了 8 个监听器接口，可以用于监听 ServletContext、HttpSession 和 ServletRequest 对象的生命周期和属性变化事件。
2. 开发 Servlet 监听器需要实现相应的监听器接口并重写接口中的方法。
3. 按照监听的事件划分，分为三类：
   - 监听对象创建和销毁的监听器
   - 监听对象中属性变更的监听器
   - 监听 HttpSession 中的对象状态改变的监听器

#### 5.9.3 监听对象创建和销毁的监听器

![image.png](javaweb/image-1669755218446.png)

- 目前仅有`ServletContextListener`有使用机会。
  - 作用：监听`SrvletContext`对象的创建和销毁
  - 生命周期：web 工程启动时创建，web 工程停止时销毁

#### 5.9.4 监听属性变更的监听器

![image.png](javaweb/image-1669755220195.png)

#### 5.9.5 监听 Session 中对象状态改变的监听器

![image.png](javaweb/image-1669755221963.png)

#### 5.9.6 注册监听器

1. 在 web.xml 中注册监听器；
   - ![image.png](javaweb/image-1669755225284.png)
2. 使用 @WebListener 注册监听器。
3. 使用 HttpSessionBindingListener 和 HttpSessionActivationListener 时，不必进行注册，直接创建 Java 类实现这两个接口即可。

#### 5.9.7 设置监听器参数

1. 在 xml 文件中设置初始化参数：

```java
@WebListener
public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //1.获取ServletContext对象
        ServletContext application = servletContextEvent.getServletContext();
        //2.获取上下文的初始化参数
        String path = application.getInitParameter("contextConfigLocation");
        //3.创建IOC容器
        BeanFactory beanFactory = new ClassPathXmlApplicationContext(path);
        //4.将IOC容器保存到application作用域
        application.setAttribute("beanFactory",beanFactory);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
```

#### 5.9.7 经典案例——统计在线人数

### 5.x 其他技术

#### 5.x.1 JSP

1. Servlet 是第一代 Java Web 开发技术，编写 HTML 代码相当于拼接字符串，最后以字符串的形式向外输出。
2. JSP 是第二代 Java Web 开发技术。允许 HTML 代码和 JSP 代码分离，让程序员能够在 HTML 文档中直接嵌入 JSP 代码。 分离了还是嵌入了？？？？

#### 5.x.2 Applet

1. Applet 是客户端小程序，一般嵌入 HTML 页面，运行在支持 Java 的浏览器（安装了 Java 虚拟机）中。
2. 目前已经被 JavaScript 全面代替。

## 6. XML

[05*尚硅谷\_xml*王振国 - 课堂笔记.pdf](https://www.yuque.com/attachments/yuque/0/2022/pdf/1604140/1656817155842-f32022ea-88f2-4c60-9aee-e42ffa537ff8.pdf)

### 6.1 作用

1. 用来保存数据，而且这些数据具有自我描述性
2. 它还可以做为项目或者模块的配置文件
3. 还可以做为网络传输数据的格式（现在 JSON 为主）。

### 6.2 语法

#### 6.2.1 文档声明

1. `<?xml version="1.0" encoding="UTF-8"?>`
   - `<?xml`要连写在一起，否则会报错
   - `version`版本号
   - `encoding`编码
   - `standalone="yes/no"`表示这个 xml 文件是否是独立的 xml 文件
2. xml 是强语言，必须符合语法规则。使用浏览器打开会进行语法校验。
3. xml 文档必须要有一对根标签（顶级标签——没有父级标签的标签），且必须唯一。

#### 6.2.2 xml 注释

1. 同 HTML

#### 6.2.3 xml 元素

1. 元素是指从开始标签到结束标签的内容。
2. 特殊字符语法同 HTML
3. 文本区域（CDATA）：告诉 xml 解析器，改区域内的内容为纯文本，不需要解析
   - `<![CDATA[ 内容xxxx )>`

#### 6.2.4 xml 标签

1. 标签名可以含字母、数字以及其他的字符
2. 标签名不能以数字或标点符号开始
3. ~~标签名不能以`xml`、`XML`、`Xml`开始~~——**验证可行**。
4. 标签名不能包含空格。
5. 标签分为单标签和双标签，但必须闭合。
6. 标签的属性值必须使用`""`包裹
7. 标签对大小写敏感
8. 标签必须正确嵌套

### 6.3 xml 解析技术

#### 6.3.1 dom 解析技术

1. w3c 制定

#### 6.3.2 Sax 解析技术

1. sun 公司在 jdk5 版本对 dom 技术进行了升级：Sax（Simple api for xml）
2. 使用了类似事件机制，边读取边解析。

#### 6.3.4 dom4j

1. 第三方解析
2. 是对 jdom 的封装，jdom 是对 dom 的封装
3. 使用：
   - 引入 jar 包
   - 创建 xml 对应的类（JavaBean）
   - 创建一个 SAXReader 对象：`SAXReader reader = new SAXReader()`
   - 通过 SAXReader 对象读取 xml 文件，并获取 document 对象：`Document document = reader.read(url)`
   - 通过 document 对象，获取根元素对象（Element 类型）：`Element root = document.getRootElement()`
     - 根元素对象的`asXML()`方法可以将 Element 对象转换为 String
   - 通过根元素对象获取所有子元素集合：`List<Element> books = root.elements()`
     - 单个子元素使用`element()`方法
     - 可传入参数，传入参数时，参数名与子元素标签名一致。
   - 循环遍历得到每个 book 对象的各类属性对象，仍然使用`elements()`或`element()`方法
   - 使用`getText`方法得到元素的内容。
     - `element()`和`getText()`可以整合成一个`elementText()`
   - 获取属性值：`attributeValue()`

```java
public class Dom4jParseXML {
    public static void main(String[] args) throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read("file:\\D:\\实践练习\\07.JavaWeb\\parsexml\\src\\books.xml");
        Element rootElement = document.getRootElement();
        List<Element> books = rootElement.elements();
        for(Element book : books){
            String sn = book.attributeValue("sn");
            Element nameElement = book.element("name");
            String nameElementText = nameElement.getText();
            Element priceElement = book.element("price");
            String priceElementText = priceElement.getText();
            String authorElement = book.elementText("author");
            System.out.println(new Book(sn,nameElementText,Double.parseDouble(priceElementText),authorElement));
        }
    }
}
```

#### 6.3.5 pull

1. 类似于 sax，利用事件机制，用于 Android 开发

## 7. HTTP 协议

### 7.1 Http 协议简述

1. HyperText Transfer Protocol：超文本传输协议
2. 基于 TCP/IP 通信协议来传递数据
3. http 是无状态的，服务器无法判断两次请求时同一客户端发送，还是不同客户端发送。
   - 通过会话跟踪技术解决无状态问题【session】

### 7.2 Http 请求

#### 7.2.1 请求结构

1. 由四个部分组成，分别为请求行、请求头、空行和请求体![](javaweb/5-200929133350160.gif)

   - 上面的`URL`应该是`URI`？

#### 7.2.2 请求行

1. 请求行是由请求方法、请求 URI（URI 全称为 Universal Resource Identifier，中文含义为“统一资源标志符”，是用来标识抽象或物理资源的字符串）和 HTTP 协议版本三个部分组成，每个部分使用空格分隔，在请求行的最后以回车符与换行符结尾。
2. 8 种请求方法：

| 方法 | 描述 |
| --- | --- |
| GET | 请求指定 URI 所指向的资源，并返回 |
| HEAD | 与 GET 请求类似，但只获取由 URI 所指向资源的响应消息报头 |
| POST | 将数据提交到服务器（例如提交表单或者上传文件），数据被包含在请求体中 |
| PUT | 使用从客户端向服务器传送的数据替换指定文档的内容 |
| DELETE | 请求服务器删除 URI 所指向的目标资源 |
| CONNECT | HTTP/1.1 协议中预留的能够将连接改为管道方式的代理服务器 |
| OPTIONS | 允许客户端查看服务器的性能 |
| TRACE | 回显服务器收到的请求，主要用于测试或诊断 |
| PATCH | 是对 PUT 方法的补充，用来对已知资源进行局部更新 |

#### 7.2.3 请求头

1. 请求头是客户端传递给服务器的一系列有关本次请求和客户端本身的相关信息。请求头一般由头部字段名、冒号（:）、空格、值组成

![image.png](javaweb/image-1669755237320.png)

#### 7.2.4 空行

- 由回车符和换行符组成，无特殊含义，分隔请求行和请求体

#### 7.2.5 请求体

- 一般出现在 POST 请中，表示提交给服务器的数据，以`key=value&key=value`的形式传输
- get 请求：没有请求体，但有一个 queryString

#### 7.2.6 GET 请求

1. 由请求行和请求头两部分组成
2. 常见组成内容含义：
   - ![image.png](javaweb/image-1669755241404.png)
3. GET 请求有哪些：
   - form 标签 method=get
   - a 标签
   - link 标签引入 css
   - Script 标签引入 js 文件
   - img 标签引入图片
   - iframe 引入 html 页面
   - 在浏览器地址栏中输入地址后敲回车

#### 7.2.7 POST 请求

1. 由请求行、请求头、请求体三部分组成
2. 常见组成内容含义：
   - ![image.png](javaweb/image-1669755244049.png)
3. POST 请求有哪些
   - form 标签 method=post

### 7.3 HTTP 响应

#### 7.3.1 响应结构

1. 由响应行（状态行）、响应头、空行和响应体组成

![](javaweb/5-20092913350O96.gif)

#### 7.3.2 响应行

1. 由 HTTP 协议版本、表示响应状态的状态码和形容这个状态词三部分组成
   - 协议版本号，当前一般为`HTTP1.1`
   - 响应状态：
     - ![image.png](javaweb/image-1669755248629.png)
     - 状态词：OK、Not Modified、Found 等。

#### 7.3.3 响应头

1. 由头部字段名、冒号、空格和值组成。

![image.png](javaweb/image-1669755250570.png)

#### 7.3.4 空行

- 同请求空行

#### 7.3.5 响应体

- 服务器根据客户端的请求返回给客户端的具体数据。

#### 7.3.6 响应示例

![image.png](javaweb/image-1669755253738.png)

#### 7.3.7 常见响应状态码

1. 200：请求成功。
2. 302：请求重定向
3. 404：服务器收到了请求，但客户端需要的资源不存在（请求地址有误）
4. 500：服务器收到了请求，但服务器内部错误（代码错误）

### 7.4 MIME 类型

1. Multipurpose Internet Mail Extensions，多用途互联网邮件扩展类型”，它是一种标准，用来表示文档、文件或字节流的性质和格式。
2. 设定在请求头的 Accept 中，格式如下：
    - `Accept: type/subtype [q=qvalue]`
    - [q=qvalue]是可选项，表示 MIME 优先顺序（权重），取值范围为 0~1
    - 多个 MIME 使用`;`隔开
3. 常见类型：
    - ![image.png](javaweb/image-1669755256461.png)

## 8. JSP

### 8.1 JSP 入门

#### 8.1.1 JSP 介绍

1. JSP 是 Java Servlet Pages 的所系，是一种动态网页开发技术。
   - 动态网页显式内容可以随时间、环境或数据库操作的结果而发生改变。
2. 本质：JSP 本质是一个 Java 类（Servlet），可以在 JSP 页面嵌入 Java 代码。
3. 运行过程：客户端请求 JSP 时，服务器内部会经历一次动态资源（JSP）到静态资源（HTML）的转化。服务器会自动把 JSP 中的 HTML 片段和数据拼接成静态资源响应给浏览器。也就是说，JSP 运行在服务器端，但最终发给客户端的是已经转换好的 HTML 静态页面。
4. 组成：包括 Java Bean、自定义标签（Custom Tags）、EL 表达式（Expression Language）、JSTL 标准标签类库（Java Standard Tag Library）等。
5. JSP 与 Servlet 的异同：
   - 相同点：都用于生成动态网页
   - 不同点：![image.png](javaweb/image-1669755260037.png)

#### 8.1.2 JSP 生命周期

1. 编译：当浏览器请求 JSP 时，JSP 容器会首先检查是否需要编译页面。如果该页面从未被编译过，或者自上次编译以来对其进行了修改，则编译该页面。（JSP 容器就是 Servlet 容器——Tomcat）
   - 解析 JSP：JSP 容器解析 JSP 文件，查看是否有语法错误
   - 翻译 JSP：JSP 容器把 JSP 文件翻译为 Servlet 类
   - 编译 Servlet
2. 初始化：当容器加载 JSP 时，它将在处理任何请求之前调用 jspInit() 方法。一般情况下，程序只初始化一次。与 Servlet init 方法一样，我们通常在 jspInit() 方法中初始化数据库连接、打开文件。
3. 执行：SP 页面完成初始化后，JSP 将会调用 \_jspService() 方法。\_jspService() 以 HttpServletRequest 对象和 HttpServletResponse 对象作为方法参数。
4. 销毁：从容器中删除 JSP。jspDestroy() 方法等效于 Servlet 中的 destroy() 方法。通常在 jspDestroy() 方法中释放数据库连接、关闭打开的文件。

### 8.2 JSP 语句

#### 8.2.1 JSP 脚本

1. 含义：在 JSP 页面中，写入 Java 代码。
2. 作用：在 jsp 页面中编写 java 功能
3. 特点：
   - 脚本翻译之后都在\_jspService 方法中
   - 在\_jspService()方法中的现有对象都可以直接使用。
   - 多个 jsp 脚本块组合完成一个完整的 java 语句。
   - 和 jsp 表达式组合使用，在 jsp 中数据数据。
4. JSP 脚本可以包含任意数量的 Java 语句，变量、方法和表达式。JSP 脚本会把包含的内容插入到 Servlet 的 service() 方法中。
5. 语法：`<% Java语句 %>`
   - 等价于`<jsp:script> Java语句 </jsp:script>`
   - 任何文本、HTML 标签和 JSP 元素（声明，表达式等）都必须在脚本程序之外。

#### 8.2.2 JSP 声明

1. 作用：用于声明一个或多个变量、方法，以供后面的 Java 代码使用。
   - 声明属性
   - 声明 static 代码块
   - 声明方法
   - 声明内部类
2. 语法：`<%! Java语句 %>`
   - 等价于`<jsp:declaration> Java语句 </jsp:declaration>`
3. 与 JSP 脚本的区别：
   - JSP 脚本只能声明变量，不能声明方法。JSP 声明语句可以声明变量和方法。
     - JSP 脚本声明的内容会插入到 Servlet 的 service()方法中，而 Java 中方法不允许嵌套方法
     - JSP 声明声明的内容会添加到 Servlet 类中，是类的成员。

#### 8.2.3 JSP 表达式

1. 作用：把变量或者表达式输出到网页上，不需要 out.print() 就能输出数据。通常用于打印变量和方法的值。
2. 语法：`<%= Java语句 %>`
   - 等价于`<jsp:expression> Java语句 </jsp:expression>`
3. 特点：
   - 都会被翻译到\_jspService() 方法中
   - 都会被翻译成为 out.print()输出到页面上
   - \_jspService()方法中的对象都可以直接使用。
   - 表达式不能以分号结束。

#### 8.2.4 JSP 注释

1. JSP 页面中，可以有四种注释。
   - HTML 注释：`<-- 注释内容 -->`。 会被翻译到 java 源代码中。在\_jspService 方法里，以 out.writer 输出到客户端。
     - 客户端访问看不到，客户端检查源码可以看到
   - HTM 注释中嵌套 JSP 表达式：`<-- 注释内容 <%= java语句 %> -->`
     - 客户端访问看不到，客户端检查源码可以看到翻译后的 JSP 表达式
   - 隐藏注释：`<%-- 注释内容 --%>`
     - 客户端访问看不到，客户端检查源码也看不到
   - 脚本程序（Scriptlet）中的注释：客户端访问看不到，客户端检查源码也看不到
     - 单行注释：`//`
     - 多行注释：`/* */`
     - 文档注释：`/** */`

### 8.3 JSP 指令

1. 作用：告诉 Web 服务器如何处理 JSP 页面的请求和响应。服务器会根据 JSP 指令来编译 JSP，生成 Java 文件。JSP 指令不产生任何可见输出，在生成的 Java 文件中，不存在 JSP 指令。
2. 语法：`<%@ directive attribute = "value" [attribute2 = "value2" ...]%>`
3. 分类：
   - ![image.png](javaweb/image-1669755270670.png)

#### 8.3.1 JSP page 指令

1. 作用：定义当前页面的相关属性。可以在 JSP 页面的任意位置编写，通常放在页面的顶部
2. 语法：`<%@ page attribute = "value" %>`
3. 常用属性：
   - ![image.png](javaweb/image-1669755273355.png)
   - import 可以声明多个，其他只能出现一个

#### 8.3.2 JSP include 指令

1. 作用：在 JSP 页面引入其它内容，可以是 JSP 文件、html 文件和文本文件等，相当于把文件的内容复制到 JSP 页面。引入的文件和 JSP 页面同时编译运行。
2. 语法：`<%@ include file="URL" %>` ，url 代表相对路径，必须以`/`开头
3. 优点：
   - 增加代码的可重用性
   - 使 JSP 页面的代码结构清晰易懂
   - 维护简单
4. 特点：静态包含的页面会出现在包含它的 jsp 页面翻译后的 servlet 程序中
   - out.write()

#### 8.3.3 JSP taglib 指令

1. 作用：声明并引入标签库。Java API 允许在页面中自定义标签，标签库就是自定义标签的集合。
2. 语法：`<%@ taglib uri="tagliburl" prefix="tagPre" %>`
   - uri 指定自定义标签库的存放位置；prefix 指定标签库的前缀。

### 8.4 JSP 动作

未学

### 8.5 JSP 九大内置对象

#### 8.5.1 九大内置对象介绍

1. 作用：简化页面开发过程。
2. 特点：JSP 内置对象又称为隐式对象，它们由容器实现和管理。在 JSP 页面中，这些内置对象不需要预先声明，也不需要进行实例化，可以直接在脚本和表达式中使用。
   - Tomcat 将 jsp 页面翻译成 Servlet 源码后，内部提供
   - 不能在 JSP 声明中使用
3. 分类：含 4 大域对象
   - ![image.png](javaweb/image-1669755278215.png)
4. 四大域对象：pageContext、request（对应 servlet 的 request 域对象）、session（对应 servlet 的 session 域对象）、以及 application（对应 servlet 的 application 域对象）
   - 操作域对象的 3 个方法：
     - ![image.png](javaweb/image-1669755281038.png)
   - 4 个域对象的不同点：
     - ![image.png](javaweb/image-1669755283217.png)
   - 使用原则：从小到大。page 到 application。对于内存的优化

#### 8.5.2 pageContext 对象

1. javax.servlet.jsp.PageContext 的实例对象。
2. 本质是 Servlet 的 ServletContext
3. 常用方法：
   - ![image.png](javaweb/image-1669755284989.png)

#### 8.5.3 request 对象

1. javax.servlet.http.HttpServletRequest 的实例对象
2. 常用方法：
   - ![image.png](javaweb/image-1669755286571.png)
   - `getScheme()`：获取请求协议
   - `getServerName()`：获取服务器 IP 地址
   - `getServerPort()`：获取服务器端口号
   - `getContextPath()`：获取工程路径（名）

#### 8.5.4 session 对象

1. javax.servlet.http.HttpSession 的实例对象，主要用来访问用户数据，记录客户的连接信息。
2. HTTP 协议是一种无状态的协议（即不保存连接状态的协议）。每次用户向服务器发出请求，且服务器接收请求并返回响应后，该连接就被关闭了，服务器端与客户端的连接被断开。此时，服务器端不保留连接的有关信息，要想记住客户的连接信息，就用到了 session 对象。
3. 常用方法：
   - ![image.png](javaweb/image-1669755289963.png)

#### 8.5.5 application 对象

1. javax.servlet.ServletContext 的实例对象。一般用于保存应用程序的公用数据。非常占用服务器资源，实际开发中一般不用。
2. application 是 pageContext 的一种，获取 pageContext 的参数可以获得 application\session\request\page 范围的所有参数；而获取 application 参数则不能获取其他三个范围的参数。
3. 常用方法：
   - ![image.png](javaweb/image-1669755292736.png)

#### 8.5.6 config 对象

1. javax.servlet.ServletConfig 的实例对象，一般用于获取页面和 Servlet 的初始化参数。
2. 常用方法：
   - ![image.png](javaweb/image-1669755294478.png)

#### 8.5.7 response 对象

1. javax.servlet.http.HttpServletResponse 的实例对象
2. 常用方法：
    - ![image.png](javaweb/image-1669755296160.png)

#### 8.5.8 out 对象

1. javax.servlet.jsp.JspWriter 的实例，常用于输出内容到 HTML 中
2. 常用方法：
   - ![image.png](javaweb/image-1669755297781.png)
3. out.write()和 resopnse.getWrite().write()对象的区别
   - 两个方法都有自己的缓冲区
   - jsp 页面中输出内容时即使不用 write()，jsp 页面翻译成 servlet 程序时，都是用的是 out.write()
   - 输出是 Tomcat 服务器默认输出 response 缓冲区的内容，而 out 缓冲区的内容会追写到 response 的缓冲区中。【表现：总是 response 输出的内容先输出】
     - jsp 页面的内容执行完后，会默认执行`out.flush()`，将 out 缓冲区内容追写到 response 缓缓从区中。
4. `out.write()`和`out.print()`
   - `print()`方法输出数据时，都会转为 String 类型
   - `write()`方法输出数据时，如果为非 String 类型，则有乱码问题（如 int 类型，输出的是整数型对应的 ascii 码）
     - 缓冲区使用字符数组存放数据

#### 8.5.9 page 对象

1. 实质是 java.lang.Object 对象相当于 Java 中的 this 关键字。page 对象是指当前的 JSP 页面本身，在实际开发中并不常用。
2. 常用方法：
    - ![image.png](javaweb/image-1669755305687.png)

#### 8.5.10 exception 对象

### 8.6 JSP 标签

#### 8.6.1 动态包含

1. 语法：`<jsp:include page="url"></js:include>`
2. 特点：动态包含的页面会出现在包含它的 jsp 页面翻译后的 servlet 程序中
   - 出现形式：`JspRuntimeLibrary.include(request, response, "url", out, false)`
3. 底层原理：
   - ![image.png](javaweb/image-1669755308990.png)
   - 被包含的页面 out 对象是父页面传过去的，是同一个对象，所以共享缓冲区。
4. 传递参数：
   - ![image.png](javaweb/image-1669755311521.png)
   - 父页面中，使用 request 的 getParameter("str")可以获取属性值。
     - 两个页面 request 是同一个

#### 8.6.2 转发

1. 语法：`<jsp:forward page="url"></jsp:fowrard>`
2. 作用：请求转发

#### 8.6.3 自定义标签

没学会

### 8.7 EL 表达式

#### 8.7.1 简介

1. Excepssion Language，简化 jsp 页面，主要用于代替 jsp 输出页面的数据【主要是域对象的数据】
2. 语法：`${}`
3. 等同于`<%= %>`
4. 不同：EL 表达式在输出 null 值时，输出为空串，jsp 表达式输出时，输出 null【用户体验不好】

#### 8.7.2 运算

1. 关系运算
   - ![image.png](javaweb/image-1669755314051.png)
2. 逻辑运算
   - ![image.png](javaweb/image-1669755316671.png)
3. 算数运算
   - ![image.png](javaweb/image-1669755319000.png)
4. 条件表达式：`${ 条件表达式? true : false }`
5. 其他运算符
   - empty：判断对象或变量是否为 null
     - ![image.png](javaweb/image-1669755322439.png)
   - `.`和`[]`：访问 JavaBean 中的属性和隐式对象的数据。
     - `.`访问 JavaBean 属性【实际上调用的是属性的 geteer 或 isValue()，如果未设置会报错】或 Map 类型的值
     - `[]`用来访问数组或者列表的元素。

#### 8.7.3 内置对象

![image.png](javaweb/image-1669755324671.png)

#### 8.7.4 pageContext 域对象的使用

![image.png](javaweb/image-1669755327267.png)

- EL 域对象调用属性，其实是调用的该属性的 getter，如果是布尔值，则调用的是 isValue()

#### 8.7.5 禁用 EL 表达式

1. 禁用单个表达式：`\${2+3}`页面会输出`${2+3}`
2. 当前页面禁用：配置 page 指令中的`isELIgnored`属性
   - `<%@ page isELIgnored = "true" %>`
3. 整个 web 禁用：
   - ![image.png](javaweb/image-1669755329967.png)

### 8.8 文件传输

#### 8.8.0 Commons-FileUpload 组件 API

1. ServletFileUpload 类：解析文件上传的数据
   | 方 法 | 说 明 |
   | --- | --- |
   | public void setSizeMax(long sizeMax) | 设置上传文件总量的最大值 (包含文件和表单数据) |
   | public List parseRequest(HttpServletRequest req) | 解析 form 表单提交的数据，返回一个 FileItem 实例的集合 |
   | public static final boolean isMultipartContent(HttpServletRequest req) | 判断请求信息中的内容是否是”multipart/form-data“类型，是则返回 true，否则返回 false。 |
   | public void setHeaderEncoding(String encoding) | 设置转换时所使用的字符集编码 |

2. FileItemFactory 接口与实现类：创建 ServletFileUpload 实例，DiskFileItemFactory 是 FileItemFactory 接口的实现类
   | 方 法 | 说 明 |
   | --- | --- |
   | public void setSizeThreshold(int sizeThreshold) | 设置内存缓冲区的大小 |
   | public void setRepository(String path) | 设置临时文件存放的目录 |

3. FileItem 接口：一个表单字段对应一个 FileItem 实例，其实现类之一是 DiskFileItem
   | 方 法 | 说 明 |
   | --- | --- |
   | public boolean isFormField() | 用于判断 FileItem 类对象封装的数据是一个普通文本表单字段，还是一个文件表单字段，如果是普通表单字段则返回 true，否则返回 false。因此，可以使用该方法判断是否为普通表单域，还是文件上传表单域。 |
   | public String getName() | 获取文件上传的文件名 |
   | public String getFieldName() | 返回表单字段元素的 name 属性值 |
   | public long getSize() | 获取上传文件的大小 |
   | public String getString() | 将 FileItem 对象中保存的主体内容以一个字符串返回。其重载方法 public String getString(String encoding) 中的参数用指定的字符集编码方式 |
   | public void write() | 将 FileItem 对象中保存的主体内容保存到指定的文件中。 |

#### 8.8.1 文件上传表单设计

1. `<form>`标签属性为`method="post"`、`enctype="multipart/form-data"`
   - get 方法也能传，但是长度有限，太短了不够用。
   - 设置了 enctype="multipart/form-data"后，提交给服务器的数据变为二进制流的形式，使用`request.getParameter`不能获取属性值【为 null】
2. `<input>`标签属性为`type="file"`

#### 8.8.2 文件上传请求体介绍

![image.png](javaweb/image-1669755345844.png)

#### 8.8.3 使用第三方包完成上传

1. 导入文件上传包和 io 包：
   - ![image.png](javaweb/image-1669755351486.png)
2. 创建表单 JSP 文件和文件上传 Servlet 类

```java
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
IOException {
    //1 先判断上传的数据是否多段数据（只有是多段的数据，才是文件上传的）
    if (ServletFileUpload.isMultipartContent(req)) {
        // 创建 FileItemFactory 工厂实现类
        FileItemFactory fileItemFactory = new DiskFileItemFactory();
        // 创建用于解析上传数据的工具类 ServletFileUpload 类
        ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
        try {
            // 解析上传的数据，得到每一个表单项 FileItem
            List<FileItem> list = servletFileUpload.parseRequest(req);
            // 循环判断，每一个表单项，是普通类型，还是上传的文件
            for (FileItem fileItem : list) {
                if (fileItem.isFormField()) {
                    // 普通表单项
                    System.out.println("表单项的 name 属性值：" + fileItem.getFieldName());
                    // 参数 UTF-8.解决乱码问题
                    System.out.println("表单项的 value 属性值：" + fileItem.getString("UTF-8"));
                } else {
                    // 上传的文件
                    System.out.println("表单项的 name 属性值：" + fileItem.getFieldName());
                    System.out.println("上传的文件名：" + fileItem.getName());
                    fileItem.write(new File("e:\\" + fileItem.getName()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

#### 8.8.4 使用第三方包完成下载

1. response.getOutputStream();
2. servletContext.getResourceAsStream();
3. `servletContext.getMimeType()`：获取要下载的文件类型
4. `response.setContentType()`：设置返回客户端的数据类型

```java
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //        1、获取要下载的文件名
    String downloadFileName = "2.jpg";
    //        2、读取要下载的文件内容 (通过ServletContext对象可以读取)
    ServletContext servletContext = getServletContext();
    // 获取要下载的文件类型
    String mimeType = servletContext.getMimeType("/file/" + downloadFileName);
    System.out.println("下载的文件类型：" + mimeType);
    //        4、在回传前，通过响应头告诉客户端返回的数据类型
    resp.setContentType(mimeType);
    //        5、还要告诉客户端收到的数据是用于下载使用（还是使用响应头）
    // Content-Disposition响应头，表示收到的数据怎么处理
    // attachment表示附件，表示下载使用
    // filename= 表示指定下载的文件名
    // url编码是把汉字转换成为%xx%xx的格式
    if (req.getHeader("User-Agent").contains("Firefox")) {
        // 如果是火狐浏览器使用Base64编码
        resp.setHeader("Content-Disposition", "attachment; filename==?UTF-8?B?" + new BASE64Encoder().encode("中国.jpg".getBytes("UTF-8")) + "?=");
    } else {
        // 如果不是火狐，是IE或谷歌，使用URL编码操作
        resp.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode("中国.jpg", "UTF-8"));
    }
    /**
         * /斜杠被服务器解析表示地址为http://ip:prot/工程名/  映射 到代码的Web目录
         */
    InputStream resourceAsStream = servletContext.getResourceAsStream("/file/" + downloadFileName);
    // 获取响应的输出流
    OutputStream outputStream = resp.getOutputStream();
    //        3、把下载的文件内容回传给客户端
    // 读取输入流中全部的数据，复制给输出流，输出给客户端
    IOUtils.copy(resourceAsStream, outputStream);
}
```

## 9. JSTL

### 9.1 JSTL 简介

#### 9.1.1 JSTL 介绍

1. JSP Standard Tag Library，核心标签库，替换代码脚本，优化 JSP 页面编写
2. 使用：使用 JSTL 需要引入 JSTL 的 JAR 包和标签库描述符文件（扩展名为 .tld），标签库描述符文件内包含标签库中所有标签的定义、标签名、功能类及各种属性。
   - `taglibs-standard-impl-1.2.1.jar`和`taglibs-standard-spec-1.2.1.jar`
   - JSTL 1.2 及之后版本只需要引入 jstl 包？
3. 地址：
4. 组成：
   - ![image.png](javaweb/image-1669755357306.png)

#### 9.1.2 使用

1. 导入 taglibs-standard-impl-1.2.1.jar 和 taglibs-standard-spec-1.2.1.jar
   - JSTL 的两个库文件只支持 Servlet4.0，Tomcat10 创建的 Servlet 程序为 5.0 版本，所以需要单独导入 servlet4.0 的 jar 包</div>
2. 使用 JSP taglib 指令引入使用的标签库地址
   - CORE 标签库：`<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>`
   - FMT 标签库：`<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/fmt" %>`
   - FUNCTIONS 标签库：`<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/functions" % >`
   - SQL 标签库：`<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/sql" %>`
   - XML 标签库：`<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/xml" %>`

### 9.2 各库介绍

> **注：下文中表达式指 EL 表达式。**

#### 9.2.1 CORE 核心库

![image.png](javaweb/image-1669755363025.png)

1. `<c:set var="varname" value="表达式" [scope="request|page|session|application"]>表达式</c:set>`：
   - 等价于：`<c:set var="varname" value="表达式" [scope="request|page|session|application"]/>`
   - var：定义变量或属性名称
   - value：变量或属性值
   - scope：可选项，表示属性的作用域，默认为 page
2. `<c:if test="判断条件" [var="varname"] [scope="request|page|session|application"]> 代码块 </c:if>`
   - test：指定判断条件，返回值为 boolean
   - var：可选项，判断条件的执行结果
   - scope：可选项，执行结果的作用域
3. `<c:choose><c:when>`和`<c:otherwise>`标签
   - ![image.png](javaweb/image-1669755366395.png)
   - 嵌套判断时，`<c:when>`的直接父标签只能是`<c:choose>`，且必须使用。
   - 不存在穿透问题。
   - 多个判断语句同时为 true 时，只会执行第一个判断为 true 的语句（同 java 只有一个入口）
4. `<c:forEach>`
   - ![image.png](javaweb/image-1669755368561.png)
   - items：要被循环的信息，可以是数组、Java 集合等；
   - var：可选项，指定迭代之的别名；
   - varStatus：可选项，当前迭代的状态信息；
     - ![image.png](javaweb/image-1669755372809.png)
     - varStatus 可调用的方法，根据 EL 表达式的`.`运算符，可以直接写属性
   - begin：可选项，迭代开始的元素，起始下标为 0；
   - end：可选项，迭代结束的元素；
   - step：可选项，迭代的步长；

#### 9.2.2 FMT 格式化库

![image.png](javaweb/image-1669755390877.png)

#### 9.2.3 FUNCTIONS 函数库

![image.png](javaweb/image-1669755388436.png)

#### 9.2.4 SQL 数据库

![image.png](javaweb/image-1669755382329.png)

## 10. BeanUtils

> 依赖
>
> - [https://commons.apache.org/proper/commons-beanutils/](https://commons.apache.org/proper/commons-beanutils/)（引入）
> - [https://commons.apache.org/proper/commons-collections/](https://commons.apache.org/proper/commons-collections/)（引入）
> - [https://commons.apache.org/proper/commons-logging/](https://commons.apache.org/proper/commons-logging/)（不知道高版本还需要不需要引入）

![image.png](javaweb/image-1669755404624.png)
注入原理：调用 JavaBean 的 seteer
作用：将参数注入到 JavaBean 的对象中，方便创建对象

## 11. MVC

### 11.1 MVC 的概念

1. MVC 全称：Model 模型、 View 视图、 Controller 控制器。
2. MVC 最早出现在 JavaEE 三层中的 Web 层，它可以有效的指导 Web 层的代码如何有效分离，单独工作。
3. Model 模型：将与业务逻辑相关的数据封装为具体的 JavaBean 类，其中不掺杂任何与数据处理相关的代码—— JavaBean/domain/entity/pojo。
4. View 视图：只负责数据和界面的显示，不接受任何与显示数据无关的代码，便于程序员和美工的分工合作—— JSP/HTML。
5. Controller 控制器：只负责接收请求，调用业务层的代码处理请求，然后派发页面，是一个“调度者”的角色——Servlet。 转到某个页面。或者是重定向到某个页面。

![image.png](javaweb/image-1669755410360.png)

## 12. Cookie

### 12.1 Cookie 概述

#### 12.1.1 Cookie 概念

1. 服务器通知客户端保存键值对的一种技术。web 应用程序利用 Cookie 在客户端缓存服务器端的文件。
2. Cookie 时由服务器发送给客户端的，客户端保存时注明了 Cookie 的来源。
3. 客户端再次访问服务器时，客户端的 Cookie 会保存在请求协议中，服务器端可以获取上次存储的缓存文件内容。

#### 12.1.2 用途

1. 管理浏览网站的人数（其中包含有多少人访问过，多少人是新用户等）
2. 电子商城中购物车功能（每买一样商品，保存一个 Cookie）
3. 用户自动登录功能（第一次登录时，将用户名和密码存储在 Cookie）

#### 12.1.3 缺点

1. 多人共用一台计算机（例如导致用户名和密码不安全等问题）。
2. Cookie 被删除时，利用 Cookie 统计用户数量出现偏差。
3. 一人使用多台计算机（网站会将看成多个用户等问题）
4. Cookie 会被附加在每次 Http 请求协议中，增加流量。
5. Cookie 使用明文（未加密）传递的，安全性低。
6. Cookie 的大小限制在 4KB 左右，无法存储复杂需求。

#### 12.1.4 Cookie 规范

1. Cookie 存储的大小上限为 4KB。
2. 一个服务器最多在客户端浏览器中可以保存 20 个 Cookie。
3. 一个浏览器最多可以保存 300 个 Cookie。
4. 不同浏览器之间不能共享 Cookie</div>warning
   注意点：Cookie 规范时 Http 协议提供的，浏览器厂商对该 Cookie 规范进行了一些“扩展”。</div>

### 12.2 使用 Cookie

#### 12.2.1 服务器端创建 Cookie

1. 创建 Cookie 对象：`Cookie cookie= new Cookie("name", "value");`
2. 发送给客户端保存：`response.addCookie(cookie);`

#### 12.2.2 服务器端获取 Cookie

1. 浏览器是通过请求头将 Cookie 发送给服务器的，所以可以使用获取请求头的方法：
   - `request.getCokies()`：返回 Cookie 的对象数组
   - 遍历 Cookie 数组，使用 equals 方法比较名字是否一致
2. 获取 Cookie 的工具类：

```java
public class CookieUtils {
    /**
    * 查找指定名称的 Cookie 对象
    * @param name
    * @param cookies
    * @return
    */
    public static Cookie findCookie(String name , Cookie[] cookies){
        if (name == null || cookies == null || cookies.length == 0) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (name.equals(cookie.getName())) {//上面判断的为空的情况，所以不会出现空指针异常
            return cookie;
            }
        }
        return null;
    }
}
```

#### 12.2.3 修改 Cookie 的值

1. 方式一：创建同名的新 cookie 进行覆盖
   - 创建 Cookie 对象：`Cookie cookie= new Cookie("name", "value");`
   - 发送给客户端保存：`response.addCookie(cookie);`
2. 方式二：查找到指定的 Cookie，使用`setValue()`方法
   - 查找指定 Cookie（使用工具类）：`Cookie cookie = CookieUtils.findCookie("key",request.getCookies());`
   - 发送给客户端保存：`response.addCookie(cookie);`
3. cookie 不能保存**空格、方括号、圆括号、等号、逗号、双引号、斜杠、问号、at 符号、冒号和分号以及汉字，同时空值在不同浏览器上表现页不一定一样。**
   - 要保存上述类型的值，可以使用 BSE64 编码对上述文本进行处置。

#### 12.2.4 Cookie 的生命周期控制

1. 使用 cookie 的`setMaxAge(num)`可以设置 cookie 如何过期：
   - 正数，表示在指定的秒数后过期
   - 负数，表示浏览器一关，Cookie 就会被删除（默认值是-1）
   - 零，表示马上删除 Cookie

#### 12.2.5 Cookie 有效 Path 的设置

![image.png](javaweb/image-1669755434570.png)

- `setPath("路径")`

## 13. Session

### 13.1 Session 概述

#### 13.1.1 Session 会话介绍

1. Session 就一个接口（HttpSession）。
2. Session 就是会话。它是用来维护一个客户端和服务器之间关联的一种技术。
3. 每个客户端都有自己的一个 Session 会话。
4. Session 会话中，我们经常用来保存用户登录之后的信息。

### 13.2 Session 的使用

#### 13.2.1 Session 的创建、获取、判断、id

1. 创建和获取 Session，使用的方法（API）是一样的：`request.getSession()`
   - 第一次调用该方法：创建 Session 会话
   - 后续掉调用该方法：获取已经存在的 Session
2. 判断是否是新创建的 Session：`session.isNew()`
   - true：标识新创建的
   - false：标识之前已经存在的
3. 获取 sessionID：`session.getId()`
   - 每个 session 都有一个 id，且唯一存在

#### 13.2.2 Session 域数据存取

1. 存储：调用 session 对象的`setAttribute("name", "value")`
2. 获取：调用 session 对象的`getAttribute("name", "value")`

#### 13.3.3 Session 生命周期控制

1. 获取存活时间（超时后销毁）：`session.getMaxInactiveInterval()`，返回整数 s。
   - Tomcat 服务器中进行了默认配置：1800s（30min）
   - 超时指的是客户端两次请求的最大间隔时长，否则请求一次会创建一次，时间重新开始算。
2. 设置存活时间：`session.setMaxInactiveInterval(int interval)`，超过指定秒数，就销毁该 session
3. 设置马上失效：`session.invalidate()`

### 13.3 应用

#### 13.3.1 登录验证码

1. 原理：
   - 第一次访问表单时，给表单生成一个随机验证码，并保存到 session 域中
   - 提交表单后，将 session 提交给服务器，服务器获得该验证码，同时服务器清空 sessionu 域（移除该 session 的值）
   - 后续点击表单时，再随机生成验证码保存到 session 域中，提交表单后与服务器上的进行比较，是否相等（字符串内容）
2. 谷歌验证码的使用（kaptcha）：
   - 导入谷歌验证码的 jar 包 kaptcha-2.3.2.ja
   - 在 web.xml 中去配置用于生成验证码的 Servlet 程
     - ![image.png](javaweb/image-1669755440256.png)
   - 在表单中使用 img 标签去显示验证码图片并使用它
     - ![image.png](javaweb/image-1669755442208.png)
   - 在服务器获取谷歌生成的验证码和客户端发送过来的验证码比较使用。
     - ![image.png](javaweb/image-1669755443991.png)

## 14. Filter 过滤器

### 14.1 Filter 概述

#### 14.1.1 Filter 介绍

1. JavaWeb 的三大组件之一。三大组件分别是：Servlet 程序、Listener 监听器、Filter 过滤器
2. 是 JavaEE 的规范。也就是接口，`**javax.servlet.Filter**`
3. Filter 过滤器它的作用是：拦截请求，过滤响应 。对 Servlet 容器传给 web 资源的 request 对象和 response 对象进行检查和修改
4. 常见拦截请求场景：
   - 权限检查
   - 日记操作
   - 事务管理
5. 特点：
   - Filter 不能直接访问，本身也不能生成 request 和 response 对象。
     - 在 Web 资源被访问前，检查 request 对象，修改请求头和请求正文，或对请求进行预处理操作。
     - 将请求传递到下一个过滤器或目标资源。
     - 在 Web 资源被访问后，检查 response 对象，修改响应头和响应正文。

#### 14.1.2 Filter 的使用

- 编写类，实现 Filter 接口
- 实现过滤方法 doFilter()
- 在`web.xml`中注册 Filter 拦截路径

#### 14.1.3 xml 配置

1. 语法结构：

```xml
<filter>
    <filter-name>myFilter</filter-name>
    <filter-class>com.baidu.www.MyFilter</filter-class>
    <init-param>
        <param-name>name</param-name>
        <param-value>123</param-value>
    </init-param>
    <init-param>
        <param-name>URL</param-name>
        <param-value>www.baidu.com</param-value>
    </init-param>
</filter>

<filter-mapping>
    <filter-name>myFilter</filter-name>
    <url-pattern>/login</url-pattern>
    <dispatcher>REQUEST</dispatcher>
    <dispatcher>FORWARD</dispatcher>
</filter-mapping>

<filter-mapping>
    <filter-name>myFilter</filter-name>
    <servlet-name>ServletDemo</servlet-name>
</filter-mapping>
```

2. 注意点：
   - `<init-param>`标签内配置 filter 过滤器的初始化参数，可以配置多个
   - `dispatcher`指定给 filter 拦截的资源被 Servlet 容器调用的方式【Filter 什么时候工作】，默认值为`REQUEST`，可以设置多个。
     - `REQUERST`：用户直接访问地址时，容器调用过滤器
     - `INCLUDE`：通过请求转发`RequestDispatcher`的`include()`方法时调用
     - `FORWARD`通过请求转发`RequestDispatcher`的`forward()`方法时调用
     - `ERROR`：通过声明式异常处理机制访问，该过滤器被调用。
3. 匹配方式：`<url-pattern>`的值：
   - 精确匹配：如`<url-pattern>/login.</url-pattern>`
   - 目录匹配：如`<url-pattern>/admin/*</url-pattern>`
   - 后缀名匹配：如`<url-pattern>*.html</url-pattern>`

#### 14.1.4 注解配置

| 属性名          | 类型           | 描述                                                                                                                 |
| --------------- | -------------- | -------------------------------------------------------------------------------------------------------------------- |
| filterName      | String         | 指定过滤器的 name 属性，等价于 <filter-name>。                                                                       |
| urlPatterns     | String[]       | 指定过滤器的 URL 匹配模式。等价于 <url-pattern> 标签。                                                               |
| value           | String[]       | 该属性等价于 urlPatterns 属性，但是两者不能同时使用。                                                                |
| servletNames    | String[]       | 指定过滤器将应用于哪些 Servlet。取值是 @WebServlet 中 filterName 属性的取值，或者 web.xml 中 <servlet-name> 的取值。 |
| dispatcherTypes | DispatcherType | 指定过滤器拦截的资源被 Servlet 容器调用的方式。具体取值包括： ASYNC、ERROR、FORWARD、INCLUDE、REQUEST。              |
| initParams      | WebInitParam[] | 指定一组过滤器初始化参数，等价于 <init-param> 标签。                                                                 |
| asyncSupported  | boolean        | 声明过滤器是否支持异步操作模式，等价于 <async-supported> 标签。                                                      |
| description     | String         | 指定过滤器的描述信息，等价于 <description> 标签。                                                                    |
| displayName     | String         | 指定过滤器的显示名，等价于 <display-name> 标签。                                                                     |

- value、urlPatterns、servletNames 三者必需至少包含一个，且 value 和 urlPatterns 不能共存，如果同时指定，通常忽略 value 的取值。
- 其余均为可选项
- 通过@WebFilter 注解注册的 Filter，其加载顺序与执行顺序无关
- 通过@WebFilter 注解注册的 Filter，其加载顺序与注解的 filterName 值相关（底层通过 HashMap 存储，key 值即 filterName 值）
- 通过@WebFilter 注解注册的 Filter，其执行顺序与类名有关，按照类名的字典顺序执行

### 14.2 Filter 的生命周期

#### 14.2.1 Filter 工作流程

![image.png](javaweb/image-1669755450932.png)

1. 客户端请求访问容器内的 Web 资源。
2. Servlet 容器接收请求，并针对本次请求分别创建一个 request 对象和 response 对象。
3. 请求到达 Web 资源之前，先调用 Filter 的 doFilter() 方法，检查 request 对象，修改请求头和请求正文，或对请求进行预处理操作。
4. 在 Filter 的 doFilter() 方法内，调用 FilterChain.doFilter() 方法，将请求传递给下一个过滤器或目标资源。
5. 目标资源生成响应信息返回客户端之前，处理控制权会再次回到 Filter 的 doFilter() 方法，执行 FilterChain.doFilter() 后的语句，检查 response 对象，修改响应头和响应正文。
6. 响应信息返回客户端。

#### 14.2.2 Filter 生命周期阶段

1. 初始化阶段：
   - Servlet 容器负责加载和实例化 Filter。容器启动时，读取 web.xml 或 @WebFilter 的配置信息对所有的过滤器进行加载和实例化。
   - 加载和实例化完成后，Servlet 容器调用 init() 方法初始化 Filter 实例。
   - 在 Filter 的生命周期内，初始化阶段只执行一次。
   - 执行 Filter 构造器方法和 init 方法
2. 拦截和过滤阶段：
   - 客户端请求的 URL 与过滤器映射匹配时，容器将该请求的 request 对象、response 对象以及 FilterChain 对象以参数的形式传递给 Filter 的 doFilter() 方法，并调用该方法对请求/响应进行拦截和过滤。
   - 拦截一次执行一次。
   - 执行`doFilter()`方法
3. 销毁阶段：
   - Filter 对象创建后会驻留在内存中，直到容器关闭或应用被移除时销毁。
   - 销毁 Filter 对象之前，容器会先调用 destory() 方法，释放过滤器占用的资源。
   - 在 Filter 的生命周期内，destory() 只执行一次。

### 14.3 FilterConfig 类

- Tomcat 创建 Filter 类时，同时会创建一个 FilterConig 类，包含有 Filter 配置文件的配置信息
- 作用：获取 filter 过滤器的配置内容
  - 获取 Filter 的名称——`<filter-name>`的内容
  - 获取 Filter 中配置的`<init-param>`参数
  - 获取 ServletContext 对象
    | 返回值类型 | 方法 | 描述 |
    | --- | --- | --- |
    | String | getInitParameter(String name) | 根据初始化参数名 name，返回对应的初始化参数值。 |
    | Enumeration | getInitParameterNames() | 返回过滤器的所有初始化参数名的枚举集合。 |
    | ServletContext | getServletContext() | 返回 Servlet 上下文对象的引用。 |
    | String | getFilterName() | 返回过滤器的名称。 |

### 14.4 FilterChain——过滤器链

#### 14.4.1 过滤器链概念

- 多个 Filter 都拦截同一目标资源，则它们就组成了一个 Filter 链（也称过滤器链）。
- 过滤器链中的每个过滤器负责特定的操作和任务，客户端的请求在这些过滤器之间传递，直到传递给目标资源。
- `**javax.servlet**`

#### 14.4.2 Filter 链拦截过程

![image.png](javaweb/image-1669755455605.png)

#### 14.4.3 Filter 链中 Filter 中的执行顺序

1. 通过 web.xml 配置的 Filter 过滤器，执行顺序由 `<filter-mapping>` 标签的配置顺序决定。`<filter-mapping>` 靠前，则 Filter 先执行，靠后则后执行。
2. 通过 @WebFilter 注解配置的 Filter 过滤器，无法进行排序。
3. 默认情况下，一个 Filter 链中的 Filter 只有一个线程——在同一个线程中
4. 默认情况下，一个 Filter 链中的 Filter 只有一个 request 对象和一个 response 对象——即共享 request 域对象

## 15. ThreadLocal

1. 作用：解决多线程的数据安全问题——事务操作的线程安全问题。称为本地线程。
2. 特点：
   - 可以给当前线程关联一个数据（可以是普通变量、对象、数组、集合等）
   - 每个 ThreadLocal 对象，只能为当前线程关联一个数据，需要关联多个线程，则需要多个 ThreadLocal 对象实例。
   - 每个 ThreadLocal 对象实例定义后，一般为 static 类型
   - ThreadLocal 对象实例销毁后，保存的数据由 JVM 虚拟机自动释放
3. `set(obj)`：在当前线程存储数据

```java
public void set(T value) {
    Thread t = Thread.currentThread(); //获取当前的线程
    ThreadLocalMap map = getMap(t);    //每一个线程都维护各自的一个容器（ThreadLocalMap）
    if (map != null)
        map.set(this, value);          //这里的key对应的是ThreadLocal，因为我们的组件中需要传输（共享）的对象可能会有多个（不止Connection）
    else
        createMap(t, value);           //默认情况下map是没有初始化的，那么第一次往其中添加数据时，会去初始化
}
```

4. `get()`：在当前线程上获取数据

```java
public T get() {
    Thread t = Thread.currentThread(); //获取当前的线程
    ThreadLocalMap map = getMap(t);    //获取和这个线程（企业）相关的ThreadLocalMap（也就是工作纽带的集合）
    if (map != null) {
        ThreadLocalMap.Entry e = map.getEntry(this);   //this指的是ThreadLocal对象，通过它才能知道是哪一个工作纽带
        if (e != null) {
            @SuppressWarnings("unchecked")
            T result = (T)e.value;     //entry.value就可以获取到工具箱了
            return result;
        }
    }
    return setInitialValue();
}
```

## 16. JSON

### 16.1 JSON 概述

1. JSON (JavaScript Object Notation) 是一种轻量级的数据交换格式。易于人阅读和编写。同时也易于机器解析和生成。JSON 采用完全独立于语言的文本格式，而且很多语言都提供了对 json 的支持（包括 C, C++, C#, Java, JavaScript, Perl, Python 等）。 这样就使得 JSON 成为理想的数据交换格式。

- 数据交换：客户端和服务器之间的业务数据传递

2. 数据格式：json 是由键值对组成，并且由花括号（大括号）包围。每个键由引号引起来，键和值之间使用冒号进行分隔， 多组键值对之间进行逗号进行分隔。
3. 保存形式：
   - 对象形式：json 对象——常用于 json 数据操作
   - 字符串形式：json 字符串——常用于数据交换
4. JSON 常用方法：
   - `JSON.stringify(xxx)`：对象转为字符串
   - `JSON.parse(xxx)`字符串转为对象

### 16.2 操作 JSON

#### 16.2.1 客户端操作 JSON

1. 浏览器操作 JSON 由 JavaScript 完成。
2. 操作方式：`json对象.key`或`json对象[key]`

#### 16.2.1 服务器端操作 JSON

> - jar 包：gson-2.2.4.jar

1. JavaBean 和 json 相互转换：
   - 创建 Gson 对象：`Gson gson = new Gson();`
   - JavaBean 转换为 json 字符串：调用`gson.toJson(JavaBean对象);`
   - json 字符串转换为 JavaBean：调用`gson.formJson(json字符串, JavaBean.class);`
2. List 和 json 相互转换
   - 创建 Gson 对象：`Gson gson = new Gson();`
   - List 转换为 json 字符串：调用`gson.toJson(List对象);`
   - json 字符串转换为 List：调用`gson.formJson(json字符串, List.class);`——得到的是一个
     - 注意：此时得到的数据类型为`LinkedTreeMap`，而不是 List 中保存的对象类型
   - `gson.formJson(json字符串, new PersonListType().getType());`此时得到才是 List 类型，使用`get(n)`方法可以获取到具体的对象
     - PersonListType 类的定义：`class PersonListType extends TypeToken<List<Person>>{}`——反射机制
   - 匿名内部类省去创建 PersonListType 类：`gson.formJson(json字符串, new TypeToken<ArrayList<Integer, Person>>(){}.getType())`
3. List 和 json 相互转换
   - 创建 Gson 对象：`Gson gson = new Gson();`
   - Map 转换为 json 字符串：调用`gson.toJson(Map对象);`
   - json 字符串转换为 Map：调用`gson.formJson(json字符串, Map.class);`——得到的是一个
     - 注意：此时得到的数据类型为`LinkedTreeMap`，而不是 List 中保存的对象类型
   - `gson.formJson(json字符串, new PersonListType().getType());`此时得到才是 List 类型，使用`get(n)`方法可以获取到具体的对象
     - PersonListType 类的定义：`class PersonListType extends TypeToken<List<Person>>{}`——反射机制
   - 匿名内部类省去创建 PersonListType 类：`gson.formJson(json字符串, new TypeToken<HashMap<Integer, Person>>(){}.getType())`
4. 举例：

```java
public void test3(){
    Map<Integer,Person> personMap = new HashMap<>();
    personMap.put(1, new Person(1, "国哥好帅"));
    personMap.put(2, new Person(2, "康师傅也好帅"));

    Gson gson = new Gson();

    // 把map集合转换成为json 字符串
    String personMapJsonString = gson.toJson(personMap);
    System.out.println(personMapJsonString);

    //Map<Integer,Person> personMap2 = gson.fromJson(personMapJsonString, new PersonMapType().getType());
    Map<Integer,Person> personMap2 = gson.fromJson(personMapJsonString, new
    TypeToken<HashMap<Integer,Person>>(){}.getType());

    System.out.println(personMap2);
    Person p = personMap2.get(1);
    System.out.println(p);
}
```

## 17. Ajax

普通请求：后端处理完成后返回页面，浏览器使用使用页面替换整个窗口中的内容
Ajax 请求：后端处理完成后通常返回 JSON 数据， jQuery 代码使用 JSON 数据对页面局部更新

### 17.1 Ajax 概述

- AJAX 即“Asynchronous Javascript And XML”（异步 JavaScript 和 XML），是指一种创建交互式网页应用的网页开发技术。
  - ajax 是一种浏览器通过 js 异步发起请求，局部更新页面的技术。
  - Ajax 请求的局部更新，浏览器地址栏不会发生变化。
  - 局部更新不会舍弃原来页面的内容。
- 同步与异步的区别：
  - 同步：用户发送请求后，页面不可使用，等待服务器响应完成
  - 异步：页面还可以继续使用。
  - 这也就是与重定向回原页面的区别，而且在响应完成后，页面的数据还是旧的。

### 17.2 Ajax 使用

#### 17.2.1 原生 Ajax 请求演示

1. 创建 ajax 对象：`var ajax= new XMLHttpRequest();`
2. 调用 open 方法设置请求参数：`ajax.open("GET", url&param);`
3. 绑定 onreadystatechange 事件：`ajax.onreadystatechange = function(){if (xmlhttprequest.readyState == 4 && xmlhttprequest.status == 200) { ajax.send(); }}|`
   - 调用 send 方法发送请求：`ajax.send()`

```html
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="pragma" content="no-cache" />
    <meta http-equiv="cache-control" content="no-cache" />
    <meta http-equiv="Expires" content="0" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Insert title here</title>
    <script type="text/javascript">
      // 在这里使用javaScript语言发起Ajax请求，访问服务器AjaxServlet中javaScriptAjax
      function ajaxRequest() {
        //     1、我们首先要创建XMLHttpRequest
        var xmlhttprequest = new XMLHttpRequest();
        //     2、调用open方法设置请求参数
        xmlhttprequest.open(
          "GET",
          "http://localhost:8080/16_json_ajax_i18n/ajaxServlet?action=javaScriptAjax",
          true
        );
        //     4、在send方法前绑定onreadystatechange事件，处理请求完成后的操作。
        xmlhttprequest.onreadystatechange = function () {
          if (xmlhttprequest.readyState == 4 && xmlhttprequest.status == 200) {
            alert("收到服务器返回的数据：" + xmlhttprequest.responseText);
            var jsonObj = JSON.parse(xmlhttprequest.responseText);
            // 把响应的数据显示在页面上
            document.getElementById("div01").innerHTML =
              "编号：" + jsonObj.id + " , 姓名：" + jsonObj.name;
          }
        };
        //     3、调用send方法发送请求
        xmlhttprequest.send();
        alert("我是最后一行的代码");
      }
    </script>
  </head>
  <body>
    <button onclick="ajaxRequest()">ajax request</button>
    <div id="div01"></div>
  </body>
</html>
```

#### 17.2.2 jQuery 中 ajax 演示

1. `$.ajax({参数})`
   - `url`：
   - `type`：请求类型
   - `data`：发送给服务区的数据
     - 格式一：`key1=value1&key2=value2`
     - 格式二：`{key1:value1, key2;value2}`
   - `success`：请求成功时，响应的回调函数，服务器返回的`data`，以参数的形式被 success 使用
   - `dataType`：响应数据类型
     - `text`
     - `xml`
     - `json`
2. `$.get({参数})`和`$.post({参数})`：对`$.ajax({参数})`的进一步封装
   - `url`：
   - `data`：发送给服务区的数据
     - 格式一：`key1=value1&key2=value2`
     - 格式二：`{key1:value1, key2;value2}`
   - `callback`：请求成功时，响应的回调函数，服务器返回的`data`，以参数的形式被 callback 使用
   - `type`：响应数据类型
     - `text`
     - `xml`
     - `json`
3. `$.getJSON({参数})`：对`$.get({参数})`的进一步封装
   - `url`：
   - `data`：发送给服务区的数据
     - 格式一：`key1=value1&key2=value2`
     - 格式二：`{key1:value1, key2;value2}`
   - `callback`：请求成功时，响应的回调函数，服务器返回的`data`，以参数的形式被 callback 使用
4. `serialize()`：表单序列化——将表单的内容以`key1=value1&key2=value2`的形式拼接【表单的方法】

## 18. 国际化（i18）

![image.png](javaweb/image-1669755466585.png)

```properties
username=username
password=password
sex=sex
age=age
regist=regist
boy=boy
email=email
girl=girl
reset=reset
submit=submit
```

```properties
username=用户名
password=密码
sex=性别
age=年龄
regist=注册
boy=男
girl=女
email=邮箱
reset=重置
submit=提交
```

- 配置文件命名规则：`basename_en_US.pproperties`
- 获取语言国家信息（en_US 和 zh_CN)：
  - `java.utli.Locale`类实例化`Locale locale = Local.getDefault();`
  - `Locale`的静态常量获取：
    - `Locale.CHINA`
    - `Locale.US`
- 通过指定的 basename 和 Locale 对象，读取配置文件及文件内容
  - `ResourceBundle bundle = ResourceBundle.getBundle("i18n", locale);`
  - `bundle.getString("key")`
- 方式一：使用请求头实现国际化：
- 方式二：使用手动选择实现国际化：
- 方式三：使用 JSTL 标签库实现国际化：需要使用 fmt——格式化库，即导入相应包

![](javaweb/image-1669755470456.png)
[16*尚硅谷\_JSON、Aajx、i18n*王振国 - 课堂笔记.pdf](https://www.yuque.com/attachments/yuque/0/2022/pdf/1604140/1657613355584-8bdaac8e-f448-476e-b78e-32ca64c4567a.pdf)

## 20. IoC

1. 耦合/依赖
   - 依赖指的是某某某离不开某某某。
   - 在软件系统中，层与层之间是存在依赖的。我们也称之为耦合。
   - 系统架构或者是设计的一个原则是： 高内聚低耦合。
   - 层内部的组成应该是高度聚合的，而层与层之间的关系应该是低耦合的，最理想的情况 0 耦合（就是没有耦合）
2. IOC - 控制反转 / DI - 依赖注入
   - 控制反转：
     - 之前在 Servlet 中，我们创建 service 对象 ， FruitService fruitService = new FruitServiceImpl();
     - 这句话如果出现在 servlet 中的某个方法内部，那么这个 fruitService 的作用域（生命周期）应该就是这个方法级别；
     - 如果这句话出现在 servlet 的类中，也就是说 fruitService 是一个成员变量，那么这个 fruitService 的作用域（生命周期）应该就是这个 servlet 实例级别
     - 之后我们在 applicationContext.xml 中定义了这个 fruitService。然后通过解析 XML，产生 fruitService 实例，存放在 beanMap 中，这个 beanMap 在一个 BeanFactory 中
     - 因此，我们转移（改变）了之前的 service 实例、dao 实例等等他们的生命周期。控制权从程序员转移到 BeanFactory。这个现象我们称之为控制反转
   - 依赖注入：
     - 之前我们在控制层出现代码：FruitService fruitService = new FruitServiceImpl()；
     - 那么，控制层和 service 层存在耦合。
     - 之后，我们将代码修改成 FruitService fruitService = null ;
     - 然后，在配置文件中配置:
         ```xml
         <bean id="fruit" class="FruitController">
            <property name="fruitService" ref="fruitService"/>
         </bean>
         ```

## 开发经验

### 1. 表单重复提交

#### 1.1 情况一

**问题描述：** web提交完表单。服务器使用请求转来进行页面跳转。这个时候，用户按下功能键 F5，就会发起最后一次的请求。 造成表单重复提交问题

**发生原因：** 浏览器的地址带了请求参数，没有发生变化，刷新页面相当于再次提交请求。

**解决方案：** 使用重定向来进行跳转，改变浏览器ip

#### 1.2 情况二

**问题描述：** 用户正常提交服务器，但是由于网络延迟等原因，迟迟未收到服务器的响应，这个时候，用户以为提交失败， 就会着急，然后多点了几次提交操作，也会造成表单重复提交

**发生原因：**

**解决方案：** 使用验证码

#### 1.3 情况三

**问题描述：** 用户正常提交服务器。服务器也没有延迟，但是提交完成后，用户回退浏览器。重新提交。也会造成表单重复提交

**发生原因：**

**解决方案：** 使用验证码

### 2. 排错

- 如果发现访问不到资源，工程启动后不经过 servlet，问题多半是工件是 web exploded，将它新建为 war exploded 即可。

![image.png](javaweb/image-1669755478099.png)

- **一个问题：idea 的项目部署的 web exploded 和 war exploded 和 war 的区别是啥？啥时候用哪个？**
