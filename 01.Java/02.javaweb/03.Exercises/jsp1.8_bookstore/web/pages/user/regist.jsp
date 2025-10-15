<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>尚硅谷会员注册页面</title>
    <%@ include file="/pages/common/head.jsp" %>
    <script>
        $(function () {
            //给验证码的图片绑定单击事件，刷新验证码
            $("#code_img").click(function (){
                this.src="kaptcha.jpg";
            });
            //ajax验证用户名是否可用
            $("#username").blur(function (){
                var username = this.value;
                $.getJSON("${pageContext.request.contextPath}/userServlet", "action=ajaxExistUserName&username=" + username, function (data){//按道理这里地址不能写死
                    // console.log(data);
                    if(data.usernameResult){//这个data代表服务器端传过来的json，usernameResult代表键
                        $("span.errorMsg").text("用户名已存在！");
                    }else{
                        $("span.errorMsg").text("用户名可用");
                    }
                });
            })

            $("#sub_btn").click(function () {
                // 验证用户名：必须由字母，数字下划线组成，并且长度为5到12位
                var usernameText = $("#username").val();
                var usernamePatt = /^\w{5,12}$/;
                if (!usernamePatt.test(usernameText)) {
                    $("span.errorMsg").text("用户名不合法！");
                    return false;
                }

                // 验证密码：必须由字母，数字下划线组成，并且长度为5到12位
                var passwordText = $("#password").val();
                var passwordPatt = /^\w{5,12}$/;
                if (!passwordPatt.test(passwordText)) {
                    $("span.errorMsg").text("密码不合法！");

                    return false;
                }

                // 验证确认密码：和密码相同
                var repwdText = $("#repwd").val();
                if (repwdText != passwordText) {
                    $("span.errorMsg").text("确认密码和密码不一致！");

                    return false;
                }

                // 邮箱验证：xxxxx@xxx.com
                var emailText = $("#email").val();
                var emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
                if (!emailPatt.test(emailText)) {
                    $("span.errorMsg").text("邮箱格式不合法！");

                    return false;
                }

                // 验证码：现在只需要验证用户已输入。因为还没讲到服务器。验证码生成。
                var codeText = $("#code").val();
                codeText = $.trim(codeText);
                if (codeText == null || codeText == "") {
                    $("span.errorMsg").text("验证码不能为空！");
                    return false;
                }

                // 去掉错误信息
                $("span.errorMsg").text("");
            })
        })
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
    <img class="logo_img" alt="" src="static/img/logo.gif">
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
                    <span class="errorMsg">
                        ${requestScope.msg}
                    </span>
                </div>
                <div class="form">
                    <form action="userServlet" method="post">
                        <input type="hidden" name="action" value="regist">
                        <label>用户名称：</label>
                        <input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1"
                               name="username" value="${requestScope.username}"
                               id="username"/>
                        <br/>
                        <br/>
                        <label>用户密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1"
                               name="password" value="${requestScope.password}"
                               id="password"/>
                        <br/>
                        <br/>
                        <label>确认密码：</label>
                        <input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1"
                               name="repwd" value="${requestScope.password}"
                               id="repwd"/>
                        <br/>
                        <br/>
                        <label>电子邮件：</label>
                        <input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1"
                               name="email" value="${requestScope.email}"
                               id="email"/>
                        <br/>
                        <br/>
                        <label>验证码：</label>
                        <input class="itxt" type="text" style="width: 120px;" id="code" name="code"/>
                        <img id="code_img" alt="" src="kaptcha.jpg" style="float: right; margin-right: 40px; width: 120px;height: 38px" title="看不清刷新？">
                        <br/>
                        <br/>
                        <input type="submit" value="注册" id="sub_btn"/>
<%--                        <a href="pages/user/login.jsp">返回登录</a>--%>

                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
<%@ include file="/pages/common/foot.jsp" %>
</body>

</html>