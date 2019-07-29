<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="app" value="${pageContext.request.contextPath}"></c:set>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>持明法洲登陆</title>
    <!-- CSS -->
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
    <link rel="stylesheet" href="${app}/login/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${app}/login/assets/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${app}/login/assets/css/form-elements.css">
    <link rel="stylesheet" href="${app}/login/assets/css/style.css">
    <link rel="shortcut icon" href="${app}/statics/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="${app}/login/assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="${app}/login/assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="${app}/login/assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="${app}/login/assets/ico/apple-touch-icon-57-precomposed.png">
    <script src="${app}/login/assets/js/jquery-2.2.1.min.js"></script>
    <script src="${app}/login/assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="${app}/login/assets/js/jquery.backstretch.min.js"></script>
    <script src="${app}/login/assets/js/scripts.js"></script>
    <script src="${app}/login/assets/js/jquery.validate.min.js"></script>
    <script>

        $(function () {
            $("#captchaImage").click(function () {
                $("#captchaImage").prop("src", "${app}/code/getCode?time=" + new Date().getTime());
            });
        });

        $(function () {
            $("#loginButtonId").click(function () {
                $.ajax({
                    <%--url: "${app}/admin/login",--%>
                    url: "${app}/shiroAdmin/login",
                    type: "POST",
                    data: $("#loginForm").serialize(),
                    dataType: "json",
                    success: function (data) {
                        if (data.code == 200){
                            location.href = "${app}/index.jsp"
                        }else {
                            //location.href = "$ {app}/login/login.jsp"
                            $("#msgDiv").html(data.msg);
                        }
                    }
                })
            })
        })
    </script>
</head>

<body>

<!-- Top content -->
<div class="top-content">

    <div class="inner-bg">
        <div class="container">
            <div class="row">
                <div class="col-sm-8 col-sm-offset-2 text">
                    <h1><strong>CMFZ</strong> Login Form</h1>
                    <div class="description">
                        <p>
                            <a href="#"><strong>CMFZ</strong></a>
                        </p>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-6 col-sm-offset-3 form-box">
                    <div class="form-top" style="width: 450px">
                        <div class="form-top-left">
                            <h3>Login to showall</h3>
                            <p>Enter your username password to log on:</p>
                        </div>
                        <div class="form-top-right">
                            <i class="fa fa-key"></i>
                        </div>
                    </div>
                    <div class="form-bottom" style="width: 450px">

                        <form role="form" action="${app}/shiroAdmin/login" method="post"
                              class="login-form" id="loginForm">

                            <span id="msgDiv" style="color: red"></span>
                            <div class="form-group">
                                <label class="sr-only" for="form-username">Username</label>
                                <input type="text" name="adminName" placeholder="请输入用户名..."
                                       class="form-username form-control" id="form-username" required>
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="form-password">Password</label>
                                <input type="password" name="password" placeholder="请输入密码..."
                                       minlength="2" class="form-password form-control" id="form-password" required>
                            </div>
                            <div class="form-group">
                                <img id="captchaImage" style="height: 48px" class="captchaImage"
                                     src="${app}/code/getCode">
                                <input style="width: 289px;height: 50px;border:3px solid #ddd;border-radius: 4px;"
                                       type="test" name="enCode" id="form-code" required>
                            </div>
                            <input type="button" style="width: 400px;border:1px solid #9d9d9d;border-radius: 4px;"
                                   id="loginButtonId" value="登录">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

</body>

</html>
<%--<form role="form" action="${app}/admin/login" method="post"--%>
